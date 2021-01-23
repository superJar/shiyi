package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 会员表 (SysMember)实体类
 *
 * @author makejava
 * @since 2021-01-06 17:43:49
 */
@Data
public class SysMember implements Serializable {
    private static final long serialVersionUID = 390867400841503812L;
    /**
    * 主键ID
    */
    private Long id;
    /**
    * 卡号
    */
    private String cardNum;
    /**
    * 姓名
    */
    private String name;
    /**
    * 昵称
    */
    private String nickname;
    /**
    * 性别 0-女性，1-男性
    */
    private String gender;
    /**
    * 手机号
    */
    private String phone;
    /**
    * 余额
    */
    private BigDecimal balance;
    /**
    * 附加余额 该余额不算作正式余额
    */
    private BigDecimal additionalBalance;
    /**
    * 充值总计
    */
    private BigDecimal sumOfTopUp;
    /**
    * 消费总计
    */
    private Double sumOfConsumption;
    /**
    * 备注
    */
    private String remark;
    /**
    * 创建人
    */
    private String createdBy;
    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    /**
    * 更新人
    */
    private String updatedBy;
    /**
    * 更新时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
    /**
    * 备用字段1
    */
    private String status;
    /**
    * 备用字段2
    */
    private String spare2;
    /**
    * 备用字段3
    */
    private String spare3;
    /**
     * 本次充值的金额
     */
    private BigDecimal topUpAmount;

    private Integer additional;

    private Double sumOfExpenditure;
    /**
     * 会员对应的消费
     */
    private List<SysProduct> products;

}