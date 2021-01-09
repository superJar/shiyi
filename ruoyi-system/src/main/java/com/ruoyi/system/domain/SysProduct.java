package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
    private Long id;
    /**
     * 商品名称
     */
    private String name;
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
    private String spare1;
    /**
    * 备用字段2
    */
    private String spare2;
    /**
    * 备用字段3
    */
    private String spare3;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(BigDecimal additionalPrice) {
        this.additionalPrice = additionalPrice;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}