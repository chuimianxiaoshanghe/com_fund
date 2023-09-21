package com.example.company_fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.company_fund.dto.Result;
import com.example.company_fund.entity.Fund;

public interface IFundService extends IService<Fund> {
    Result queryFund(int pageNum,int pageSize,String sortField,String sortDirection);
}
