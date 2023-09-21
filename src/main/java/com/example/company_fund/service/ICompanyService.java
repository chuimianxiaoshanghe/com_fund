package com.example.company_fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.company_fund.dto.Result;
import com.example.company_fund.entity.Company;

public interface ICompanyService extends IService<Company> {

    Result queryHotCompany();

    Result addCompany(Company company);

    Result queryCompanyById(Long id);

    Result updateCompany(Long id,Company company);

    Result deleteCompany(Long id);
}
