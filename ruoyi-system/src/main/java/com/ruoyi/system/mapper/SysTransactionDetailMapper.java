package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysTransactionDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysTransactionDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysTransactionDetail record);

    int insertSelective(SysTransactionDetail record);

    SysTransactionDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysTransactionDetail record);

    int updateByPrimaryKey(SysTransactionDetail record);
}