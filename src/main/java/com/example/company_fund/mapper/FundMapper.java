package com.example.company_fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.company_fund.entity.Fund;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface FundMapper extends BaseMapper<Fund> {
    List<Fund> queryFunds();

    Fund queryFund(@Param("fund_code") String fundCode,@Param("end_date") Date endDate);

    Fund queryThisYearFund(@Param("fund_code") String fundCode,@Param("end_date") Date endDate);

    Fund queryEarliestYearFund(@Param("fund_code") String fundCode);
}
