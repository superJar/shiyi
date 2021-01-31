package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysTransactionDetail;
import com.ruoyi.system.mapper.SysTransactionDetailMapper;
import com.ruoyi.system.service.SysTransactionDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:superJar
 * @date:2021/1/23
 * @time:22:49
 * @details:
 */
@Service
public class SysTransactionDetailServiceImpl implements SysTransactionDetailService {

    @Resource
    private SysTransactionDetailMapper transactionDetailMapper;
    @Override
    public List<SysTransactionDetail> page(SysTransactionDetail transactionDetail) {
        return transactionDetailMapper.queryCondition(transactionDetail);
    }
}
