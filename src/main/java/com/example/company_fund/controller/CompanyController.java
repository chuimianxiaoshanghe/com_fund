package com.example.company_fund.controller;


import com.example.company_fund.dto.Result;
import com.example.company_fund.entity.Company;
import com.example.company_fund.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private ICompanyService companyService;

    @GetMapping
    public Result queryHotCompanies(){
        return companyService.queryHotCompany();
    }

    @PostMapping
    public Result addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @GetMapping("/{comId}")
    public Result queryCompanyById(@PathVariable("comId") Long id){
        return companyService.queryCompanyById(id);
    }

    @PutMapping("/{comId}")
    public Result updateCompany(@PathVariable("comId") Long id,@RequestBody Company company){
        return companyService.updateCompany(id,company);
    }

    @DeleteMapping("/{comId}")
    public Result deleteCompany(@PathVariable("comId")Long id){
        return companyService.deleteCompany(id);
    }
}
