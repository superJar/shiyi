package com.shiyi.web.controller.system;


import com.shiyi.common.core.controller.BaseController;
import com.shiyi.common.core.domain.model.LoginUser;
import com.shiyi.common.core.domain.model.Result;
import com.shiyi.common.core.page.TableDataInfo;
import com.shiyi.common.utils.ServletUtils;
import com.shiyi.framework.web.service.TokenService;
import com.shiyi.system.domain.SysProduct;
import com.shiyi.system.service.SysProductService;
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
public class SysProductController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private SysProductService sysProductService;

    @Resource
    private TokenService tokenService;

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

            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            if(loginUser == null){
                return Result.fail("用户为空！");
            }
            sysProductService.addOrEdit(product,loginUser.getUsername());
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
    @GetMapping("/list")
    public TableDataInfo list(SysProduct product){
            startPage();
            List<SysProduct> list = sysProductService.list(product);
            return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:product:del')")
    @DeleteMapping("/batchDelete")
    public Result batchDelete(@RequestBody List<Integer> ids){

        try {
            sysProductService.batchDelete(ids);
            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }

    @PreAuthorize("@ss.hasPermi('system:product:save')")
    @GetMapping("/echo")
    public SysProduct echo(Long id){
        if(id == null){
            throw new RuntimeException("id不能为空！;");
        }
        SysProduct product = sysProductService.echo(id);
        return product;
    }
}