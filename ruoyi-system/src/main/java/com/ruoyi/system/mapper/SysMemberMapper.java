package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员表 (SysMember)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-06 17:43:49
 */
public interface SysMemberMapper {


    List<SysMember> queryCondition(SysMember member);

    Integer update(SysMember member);

    Integer insertSelective(SysMember member);

    SysMember queryById(@Param("id") Long id);

    void batchDelete(@Param("list") List<Integer> list);
}