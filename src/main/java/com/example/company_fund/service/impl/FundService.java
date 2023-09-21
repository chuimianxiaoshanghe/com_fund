package com.example.company_fund.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.company_fund.dto.Result;
import com.example.company_fund.entity.Fund;
import com.example.company_fund.mapper.FundMapper;
import com.example.company_fund.service.IFundService;
import com.example.company_fund.utils.Constants;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FundService extends ServiceImpl<FundMapper, Fund> implements IFundService {

    @Override
    public Result queryFund(int pageNum, int pageSize, String sortField, String sortDirection) {
        Page<Fund> iPage = new Page<>(pageNum, pageSize);
        Page<Fund> page;
        //1.获取每一个公司最新修改的结果（end_date为最新)
        QueryChainWrapper<Fund> queryChainWrapper = query().inSql("end_date", "select max(end_date) as end_date from qy_fund group by fund_code");
        if (sortDirection.toUpperCase() == "ASC") {
            page = queryChainWrapper.orderByAsc(sortField).page(iPage);
        } else {
            //默认降序排列
            page = queryChainWrapper.orderByDesc(sortField).page(iPage);
        }

        List<Fund> funds = page.getRecords();
        //2.对基金做计算，并回设数值
        funds.forEach(fund -> {
            for (int i = 0; i <= 8; i++)
                CalFluctuation(i, fund);
        });
        return Result.ok(funds);
    }

    private void CalFluctuation(int t, Fund fund) {
        //1.获取最新基金的信息
        LocalDateTime endDate = fund.getEndDate();
        Double unitNetVal = fund.getUnitNetVal();
        String fundCode = fund.getFundCode();

        //2.由于实体类中使用了localDateTime，需要转换成Date
        Calendar calendar = Calendar.getInstance();
        Date eDate = Date.from(endDate.atZone(ZoneId.systemDefault()).toInstant());
        calendar.setTime(eDate);
        //3.选择近七天，近一个月...等计算方案去找到基金
        Fund tFund=null;
        if (t < 6) {
            selectPlan(calendar, t);
            //4.找到近七天，近一个月...的基金
            Date time = calendar.getTime();
            tFund = getBaseMapper().queryFund(fundCode, time);
        }else if (t==6){
            tFund = getBaseMapper().queryThisYearFund(fundCode, eDate);
        }else {
            tFund = getBaseMapper().queryEarliestYearFund(fundCode);
        }

        //5.如果找不到，找临近日期
        if (tFund == null) {
            detailPlan(calendar, fundCode);
        }
        //6.如果临近日期也没有，默认基金变动为0
        if (tFund == null) {
            setPlan(t, fund, 0d);
            return;
        }
        //7.对基金进行计算，得到变动净值
        Double uNVal = tFund.getUnitNetVal();
        Double Fluctuation = (unitNetVal - uNVal) * 100 / uNVal;
        setPlan(t, fund, Fluctuation);
    }

    /**
     * 为近七天、近一个月...赋值
     *
     * @param t
     * @param fund
     * @param Fluctuation
     */

    private void setPlan(int t, Fund fund, Double Fluctuation) {
        switch (t) {
            case 0:
                fund.setWeekFluctuation(Fluctuation);
                break;
            case 1:
                fund.setMonthFluctuation(Fluctuation);
                break;
            case 2:
                fund.setThreeMonthFluctuation(Fluctuation);
                break;
            case 3:
                fund.setYearFluctuation(Fluctuation);
                break;
            case 4:
                fund.setTwoYearFluctuation(Fluctuation);
                break;
            case 5:
                fund.setThreeYearFluctuation(Fluctuation);
                break;
            case 6:
                fund.setThisYearFluctuation(Fluctuation);
                break;
            case 7:
                fund.setEarliestYearFluctuation(Fluctuation);
                break;
        }

    }


    /**
     * 如果日期不存在，选择最近的日期
     *
     * @param calendar
     * @param fundCode
     */
    private void detailPlan(Calendar calendar, String fundCode) {
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, i - 7);
            Date time = calendar.getTime();
            Fund tFund = getBaseMapper().queryFund(fundCode, time);
            if (tFund != null) {
                break;
            }
        }

    }

    /**
     * 选择近七天，近一个月...
     *
     * @param calendar
     * @param t
     */
    private void selectPlan(Calendar calendar, int t) {
        switch (t) {
            case 0:
                calendar.add(Calendar.DAY_OF_MONTH, -Constants.week);
                break;
            case 1:
                calendar.add(Calendar.MONTH, -Constants.month);
                break;
            case 2:
                calendar.add(Calendar.MONTH, -Constants.three_month);
                break;
            case 3:
                calendar.add(Calendar.YEAR, -Constants.year);
                break;
            case 4:
                calendar.add(Calendar.YEAR, -Constants.two_year);
                break;
            case 5:
                calendar.add(Calendar.YEAR, -Constants.three_year);
                break;
        }
    }
}
