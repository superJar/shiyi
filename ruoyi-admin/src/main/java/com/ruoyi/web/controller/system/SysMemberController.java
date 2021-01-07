package com.ruoyi.web.controller.system;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.domain.model.Result;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.SysMember;
import com.ruoyi.system.service.SysMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员表 (SysMember)表控制层
 *
 * @author makejava
 * @since 2021-01-06 17:43:49
 */
@Slf4j
@RestController
@RequestMapping("/system/member")
public class SysMemberController {
    /**
     * 服务对象
     */
    @Resource
    private SysMemberService sysMemberService;

    @Resource
    private TokenService tokenService;


    /**
     * @description:新增/更新
     * @author: SuperJar
     * @date: 2021/1/1 17:31
     * @return: com.el.common.Result
     */
    @PreAuthorize("@ss.hasPermi('system:member:save')")
    @PostMapping("/addOrUpdate")
    public Result addOrUpdate(@RequestBody SysMember member) {
        try {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            Integer count = sysMemberService.addOrEdit(member,loginUser.getUsername());

            if (count == 0) {
                return Result.fail("操作失败！");
            }

            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }

    //分页查询
    @Log
    @PreAuthorize("@ss.hasPermi('system:member:list')")
    @PostMapping("/list")
    public Result<PageInfo<SysMember>> page(@RequestBody JSONObject jsonObject) {
        try {
            int pageNum = jsonObject.getInteger("pageNum");
            int pageSize = jsonObject.getInteger("pageSize");
            String name = jsonObject.getString("name");
            String nickname = jsonObject.getString("nickname");
            PageInfo<SysMember> page = sysMemberService.page(pageNum, pageSize,name,nickname);

            return Result.ok(page);
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }

    /**
     * @description:充值
     * @author: SuperJar
     * @date: 2021/1/1 17:39
     * @return: com.el.common.Result
     */
    @Log
    @PreAuthorize("@ss.hasPermi('system:member:topup')")
    @PostMapping("/topUp")
    public Result<SysMember> topUp(@RequestBody SysMember member) {


        if (member == null || member.getId() == null) {
            return Result.fail();
        }
        SysMember sysMember = sysMemberService.topUp(member);

        return Result.ok(sysMember);

    }


    /**
     * @description:消费
     * @author: SuperJar
     * @date: 2021/1/1 17:40
     * @return: com.el.common.Result
     */
    @Log
    @PreAuthorize("@ss.hasPermi('system:member:consume')")
    @PostMapping("/consume")
    public Result<SysMember> consume(@RequestBody SysMember member) {

        if (member == null) {
            return Result.fail();
        }
        SysMember sysMember = sysMemberService.consume(member);

        return Result.ok(sysMember);

    }

    @PreAuthorize("@ss.hasPermi('system:member:del')")
    @DeleteMapping("/batchDelete")
    public Result batchDelete(@RequestBody List<Integer> ids){

        try {
            sysMemberService.batchDelete(ids);
            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }
}