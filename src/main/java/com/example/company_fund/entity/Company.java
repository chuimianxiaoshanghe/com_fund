package com.example.company_fund.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@TableName("qy_company")
public class Company implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 公司字符编码
     */
    private String orgUniCode;
    /**
     * 公司全称
     */
    private String orgChiName;
    /**
     * 公司类型
     */
    private String induSmaPar;
    /**
     * 法定代表人
     */
    private String orgDele;
    /**
     * 注册资本
     */
    private Double regCap;
    /**
     * 成立日期
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime orgEstDate;


}
