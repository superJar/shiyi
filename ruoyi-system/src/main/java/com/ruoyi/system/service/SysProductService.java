package com.ruoyi.system.service;

import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.SysProduct;

import java.util.List;

/**
 * 拾壹商品表 (SysProduct)表服务接口
 *
 * @author makejava
 * @since 2021-01-06 15:55:28
 */
public interface SysProductService {


    Integer addOrEdit(SysProduct product,String username);

    PageInfo<SysProduct> page(int pageNum, int pageSize, String queryString);

    void batchDelete(List<Integer> ids);
}