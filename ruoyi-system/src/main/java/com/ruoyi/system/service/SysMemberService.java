package com.ruoyi.system.service;

import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.SysMember;

import java.util.List;

/**
 * 会员表 (SysMember)表服务接口
 *
 * @author makejava
 * @since 2021-01-06 17:43:49
 */
public interface SysMemberService {
    PageInfo<SysMember> page(Integer pageNum, Integer pageSize, String name, String nickname);

    Integer addOrEdit(SysMember member,String username);

    boolean topUp(SysMember member);

    boolean consume(SysMember member);


    void batchDelete(List<Integer> ids);
}