package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.SysTransactionDetail;
import com.ruoyi.system.service.SysTransactionDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PreAuthorize("@ss.hasPermi('system:transaction:list')")
    @GetMapping("/list")
    public TableDataInfo page(SysTransactionDetail transactionDetail) {
        startPage();
        List<SysTransactionDetail> transactionDetails = transactionDetailService.page(transactionDetail);
        return getDataTable(transactionDetails);
    }
}
