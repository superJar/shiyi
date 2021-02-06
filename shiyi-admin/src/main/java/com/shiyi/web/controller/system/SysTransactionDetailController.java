package com.shiyi.web.controller.system;

import com.shiyi.common.core.controller.BaseController;
import com.shiyi.common.core.page.TableDataInfo;
import com.shiyi.framework.web.service.TokenService;
import com.shiyi.system.domain.SysTransactionDetail;
import com.shiyi.system.service.SysTransactionDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:superJar
 * @date:2021/1/23
 * @time:22:53
 * @details:
 */
@RestController
@RequestMapping("/system/details")
@Slf4j
public class SysTransactionDetailController extends BaseController {

    @Resource
    private SysTransactionDetailService transactionDetailService;

    @Resource
    private TokenService tokenService;

    @PreAuthorize("@ss.hasPermi('system:details:list')")
    @GetMapping("/list")
    public TableDataInfo page(SysTransactionDetail transactionDetail) {
        startPage();
        List<SysTransactionDetail> list = transactionDetailService.page(transactionDetail);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:details:update')")
    @PostMapping("/update")
    public void update(@RequestBody SysTransactionDetail transactionDetail){
        transactionDetailService.update(transactionDetail);
    }

    @GetMapping("/echo/{id}")
    public SysTransactionDetail echo(@PathVariable("id") Integer id){
        if(id == null){
            throw  new RuntimeException("ID不能为空！！");
        }
        SysTransactionDetail transactionDetail = transactionDetailService.echo(id);
        return transactionDetail;
    }
}
