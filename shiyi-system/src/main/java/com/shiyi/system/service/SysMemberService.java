package com.shiyi.system.service;

import com.shiyi.system.domain.SysMember;

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

    SysMember echo(Long id);

    SysMember deduction(SysMember member);



    Integer findExist(String name,String cardNum,Long id);
}