package com.ruoyi.system.service.impl;


import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.mapper.SysProductMapper;
import com.ruoyi.system.service.SysProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 拾壹商品表 (SysProduct)表服务实现类
 *
 * @author makejava
 * @since 2021-01-06 15:55:28
 */
@Service("sysProductService")
public class SysProductServiceImpl implements SysProductService {
    @Resource
    private SysProductMapper sysProductMapper;

    @Transactional
    @Override
    public Integer addOrEdit(SysProduct product,String username) {
        return product.getId() != null ? update(product,username) : insert(product,username);
    }

    private Integer insert(SysProduct product, String username) {
        product.setCreatedBy(username);
        product.setCreatedTime(new Date());
        return sysProductMapper.insertSelective(product);
    }

    private Integer update(SysProduct product,String username) {
        product.setUpdatedBy(username);
        product.setUpdatedTime(new Date());
        return sysProductMapper.update(product);
    }

    @Override
    public List<SysProduct> list(SysProduct product) {
        return sysProductMapper.queryByCondition(product);
    }

    @Override
    public void batchDelete(List<Integer> ids) {
        sysProductMapper.batchDelete(ids);
    }

    @Override
    public SysProduct echo(Integer id) {
        return sysProductMapper.queryById(id);
    }
}