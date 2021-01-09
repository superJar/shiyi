package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysMember;

import java.util.List;

/**
 * 会员表 (SysMember)表服务接口
 *
 * @author makejava
 * @since 2021-01-06 17:43:49
 */
public interface SysMemberService {
    List<SysMember> page(SysMember member);

    Integer addOrEdit(SysMember member,String username);

    SysMember topUp(SysMember member);

    SysMember consume(SysMember member);


    void batchDelete(List<Integer> ids);

    void batchAdd(List<SysMember> members);

    SysMember echo(Integer id);

    void deduction(SysMember member);
}