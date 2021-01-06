package com.ruoyi.web.controller.system;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.model.Result;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.service.SysProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 拾壹商品表 (SysProduct)表控制层
 *
 * @author makejava
 * @since 2021-01-06 15:55:29
 */
@Slf4j
@RestController
@RequestMapping("/system/product")
public class SysProductController {
    /**
     * 服务对象
     */
    @Resource
    private SysProductService sysProductService;

    /**
     * @description:新增/更新
     * @author: SuperJar
     * @date: 2021/1/1 17:45
     * @return: com.el.common.Result
     */
    @PreAuthorize("@ss.hasPermi('system:product:save')")
    @PostMapping("/addOrUpdate")
    public Result addOrUpdate(@RequestBody SysProduct product){
        try {
            sysProductService.addOrEdit(product);
            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }

    /**
     * @description:分页+模糊查询
     * @author: SuperJar
     * @date: 2021/1/2 14:27
     * @return: com.el.common.Result<com.baomidou.mybatisplus.core.metadata.IPage<com.el.entity.ElProduct>>
     */
    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/list")
    public Result<PageInfo<SysProduct>> list(@RequestBody JSONObject jsonObject){
        try {

            int pageNum = jsonObject.getInteger("pageNum");
            int pageSize = jsonObject.getInteger("pageSize");
            String queryString = jsonObject.getString("queryString");

            PageInfo<SysProduct> page = sysProductService.page(pageNum,pageSize,queryString);
            return Result.ok(page);
        } catch (JSONException e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }

    @PreAuthorize("@ss.hasPermi('system:product:del')")
    @DeleteMapping("/batchDelete")
    public Result batchDelete(@RequestBody List<String> ids){

        try {
            sysProductService.batchDelete(ids);
            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }
}