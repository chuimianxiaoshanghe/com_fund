package com.example.company_fund;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.company_fund.mapper")
@SpringBootApplication
public class CompanyFundApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyFundApplication.class, args);
	}

}
