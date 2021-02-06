package com.shiyi.system.service.impl;

import com.shiyi.system.domain.SysTransactionDetail;
import com.shiyi.system.mapper.SysOperLogMapper;
import com.shiyi.system.mapper.SysTransactionDetailMapper;
import com.shiyi.system.service.SysTransactionDetailService;
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
    @Resource
    private SysOperLogMapper operLogMapper;
    @Override
    public List<SysTransactionDetail> page(SysTransactionDetail transactionDetail) {
        return transactionDetailMapper.queryCondition(transactionDetail);
    }

    @Override
    public void update(SysTransactionDetail transactionDetail) {
        transactionDetailMapper.updateByPrimaryKeySelective(transactionDetail);
    }

    @Override
    public SysTransactionDetail echo(Integer id) {
        return transactionDetailMapper.echo(id);
    }

    @Override
    public void updateMemberName(String nameFromDB,String name) {
        transactionDetailMapper.updateMemberName(nameFromDB,name);
    }


}
