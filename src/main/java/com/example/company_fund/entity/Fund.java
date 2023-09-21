package com.example.company_fund.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("qy_fund")
public class Fund implements Serializable {
    /**
     * 基金代码
     */
    private String fundCode;
    /**
     * 基金简称
     */
    private String fundShortName;
    /**
     * 统计日期
     */
    private LocalDateTime endDate;
    /**
     * 单位净值
     */
    private Double unitNetVal;

    /**
     * 近一周
     */
    @TableField(exist = false)
    private Double weekFluctuation;
    /**
     * 近一月
     */
    @TableField(exist = false)
    private Double monthFluctuation;
    /**
     * 近三月
     */
    @TableField(exist = false)
    private Double threeMonthFluctuation;
    /**
     * 近一年
     */
    @TableField(exist = false)
    private Double yearFluctuation;
    /**
     * 近两年
     */
    @TableField(exist = false)
    private Double twoYearFluctuation;
    /**
     * 近三年
     */
    @TableField(exist = false)
    private Double threeYearFluctuation;
    /**
     * 今年来
     */
    @TableField(exist = false)
    private Double thisYearFluctuation;
    /**
     * 成立来
     */
    @TableField(exist = false)
    private Double earliestYearFluctuation;
}
