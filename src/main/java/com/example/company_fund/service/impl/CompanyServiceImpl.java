package com.example.company_fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.company_fund.dto.Result;
import com.example.company_fund.entity.Company;
import com.example.company_fund.mapper.CompanyMapper;
import com.example.company_fund.service.ICompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

    @Override
    public Result queryHotCompany() {
        List<Company> companies = query().orderByDesc("org_est_date").list();
        return Result.ok(companies);
    }

    @Override
    public Result addCompany(Company company) {
        save(company);
        return Result.ok("添加成功");
    }

    @Override
    public Result queryCompanyById(Long id) {
        Company company = query().eq("id", id).one();
        return Result.ok(company);
    }

    @Override
    public Result updateCompany(Long id,Company company) {
        company.setId(id);
        updateById(company);
        return Result.ok("更新成功！");
    }

    @Override
    public Result deleteCompany(Long id) {
        removeById(id);
        return Result.ok("删除成功！");
    }
}
