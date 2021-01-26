package com.ruoyi.system.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * sys_transaction_detail
 * @author 
 */
@Data
public class SysTransactionDetail implements Serializable {
    /**
     * 交易明细ID
     */
    private Integer id;

    /**
     * 用户编号
     */
    private String cardNum;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 交易类型 1.充值，2.消费
     */
    private Integer type;

    /**
     * 交易金额
     */
    private String transaction;

    /**
     * 交易时间
     */
    private Date transactionTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备用2
     */
    private String spare2;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}