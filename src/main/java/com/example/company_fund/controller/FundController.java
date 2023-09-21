package com.example.company_fund.controller;

import com.example.company_fund.dto.Result;
import com.example.company_fund.service.IFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funds")
public class FundController {
    @Autowired
    private IFundService fundService;

    @GetMapping
    public Result queryFund(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            @RequestParam(value = "sortField") String sortField,
                            @RequestParam(value = "sortDirection") String sortDirection) {
        return fundService.queryFund(pageNum, pageSize, sortField, sortDirection);
    }
}
