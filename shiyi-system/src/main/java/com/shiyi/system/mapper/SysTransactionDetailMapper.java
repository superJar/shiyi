package com.shiyi.system.mapper;

import com.shiyi.system.domain.SysTransactionDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysTransactionDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysTransactionDetail record);

    int insertSelective(SysTransactionDetail record);

    SysTransactionDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysTransactionDetail record);

    int updateByPrimaryKey(SysTransactionDetail record);

    List<SysTransactionDetail> queryCondition(SysTransactionDetail transactionDetail);

    SysTransactionDetail echo(@Param("id") Integer id);

    void updateMemberName(@Param("nameFromDB")String nameFromDB,@Param("name") String name);
}