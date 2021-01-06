package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.SysProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 拾壹商品表 (SysProduct)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-06 15:55:26
 */
public interface SysProductMapper {


    Integer insertSelective(SysProduct product);

    Integer update(SysProduct product);

    List<SysProduct> queryByCondition(@Param("queryString") String queryString);

    void batchDelete(@Param("list") List<String> list);
}