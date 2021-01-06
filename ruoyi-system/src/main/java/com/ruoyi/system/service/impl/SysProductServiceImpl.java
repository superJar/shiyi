package com.ruoyi.system.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.mapper.SysProductMapper;
import com.ruoyi.system.service.SysProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    public Integer addOrEdit(SysProduct product) {
        return product.getId() != null ? sysProductMapper.update(product) : sysProductMapper.insertSelective(product);
    }

    @Override
    public PageInfo<SysProduct> page(int pageNum, int pageSize, String queryString) {
        Page<SysProduct> page = PageHelper.startPage(pageNum, pageSize);
        List<SysProduct> list = sysProductMapper.queryByCondition("%"+queryString+"%");
        PageInfo<SysProduct> pageInfo = page.toPageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public void batchDelete(List<String> ids) {
        sysProductMapper.batchDelete(ids);
    }
}