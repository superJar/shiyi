package com.ruoyi.system.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * 拾壹商品表 (SysProduct)实体类
 *
 * @author makejava
 * @since 2021-01-06 15:55:17
 */
@Data
public class SysProduct implements Serializable {
    private static final long serialVersionUID = -51887229889240487L;
    /**
    * 主键ID
    */
    private Integer id;
    /**
    * 商品类型 0-酒水小吃类，1-桌游类，2-包房类
    */
    private Integer type;
    /**
    * 单价价格
    */
    private BigDecimal price;
    /**
    * 附加价格
    */
    private BigDecimal additionalPrice;
    /**
    * 商品图片
    */
    private String pic;
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
    /**
    * 备用字段1
    */
    private String spare1;
    /**
    * 备用字段2
    */
    private String spare2;
    /**
    * 备用字段3
    */
    private String spare3;

}