package com.example.company_fund.mapper;


import com.example.company_fund.entity.Fund;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class FundTest {
    @Autowired
    private FundMapper fundMapper;
    @Test
    public void testQueryVoucherOfShop() {
        List<Fund> funds = fundMapper.queryFunds();
        System.out.println(funds);
    }

    @Test
    public void testQueryThisYearFund(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2014);
        calendar.set(Calendar.MONTH,3);
        calendar.set(Calendar.DAY_OF_MONTH,14);
        Date date = calendar.getTime();
        Fund fund = fundMapper.queryThisYearFund("110033",date);
        System.out.println(fund);
    }

    @Test
    public void testQueryEarliestYearFund(){
        Fund fund = fundMapper.queryEarliestYearFund("110033");
        System.out.println(fund);
    }
}
