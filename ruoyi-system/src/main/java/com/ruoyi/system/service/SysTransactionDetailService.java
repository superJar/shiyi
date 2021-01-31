package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysTransactionDetail;

import java.util.List;

/**
 * @author:superJar
 * @date:2021/1/23
 * @time:22:48
 * @details:
 */
public interface SysTransactionDetailService {
    List<SysTransactionDetail> page(SysTransactionDetail transactionDetail);

    void update(SysTransactionDetail transactionDetail);

    SysTransactionDetail echo(Integer id);

    void updateMemberName(String nameFromDB,String name);
}
