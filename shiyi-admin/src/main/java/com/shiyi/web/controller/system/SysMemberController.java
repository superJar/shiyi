package com.shiyi.web.controller.system;


import com.shiyi.common.annotation.Log;
import com.shiyi.common.core.controller.BaseController;
import com.shiyi.common.core.domain.entity.SysUser;
import com.shiyi.common.core.domain.model.LoginUser;
import com.shiyi.common.core.domain.model.Result;
import com.shiyi.common.core.page.TableDataInfo;
import com.shiyi.common.utils.ServletUtils;
import com.shiyi.framework.web.service.TokenService;
import com.shiyi.system.domain.SysMember;
import com.shiyi.system.service.SysMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class SysMemberController extends BaseController {
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
            // 校验
            Result validation = validation(member);
            if(validation != null){
                return validation;
            }
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            Integer count = sysMemberService.addOrEdit(member, loginUser.getUsername());

            if (count == 0) {
                return Result.fail("操作失败！");
            }

            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }
    private Result validation(SysMember member) {
        Integer count = 0;
        if(StringUtils.isNotBlank(member.getName())){
            count = sysMemberService.findExist(member.getName(),null,member.getId());
            if(count > 0){
                return Result.fail("不允许有重复的名字！");
            }
        }

        if(StringUtils.isNotBlank(member.getCardNum())){
            count = sysMemberService.findExist(null,member.getCardNum(),member.getId());
            if(count > 0){
                return Result.fail("不允许有重复的卡号！");
            }
        }
        return null;
    }
    //分页查询
    @PreAuthorize("@ss.hasPermi('system:member:list')")
    @GetMapping("/list")
    public TableDataInfo page(SysMember member) {
        SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        if(user == null){
            throw new RuntimeException("没有用户信息！");
        }

        startPage();
        if(user.getUserId() != 1){
            member.setUserId(Integer.valueOf(String.valueOf(user.getUserId())));
        }

        List<SysMember> list = sysMemberService.page(member);
        return getDataTable(list);
    }

    /**
     * @description:充值
     * @author: SuperJar
     * @date: 2021/1/1 17:39
     * @return: com.el.common.Result
     */
    @Log
    @PreAuthorize("@ss.hasPermi('system:member:topup')")
    @PutMapping("/topUp")
    public Result<SysMember> topUp(@RequestBody SysMember member) {


        if (member == null || member.getId() == null) {
            return Result.fail();
        }
        SysMember sysMember = sysMemberService.topUp(member);

        return Result.ok(sysMember);

    }

    @PreAuthorize("@ss.hasPermi('system:member:save')")
    @GetMapping(value = { "/", "/{userId}" })
    public SysMember echo(@PathVariable(value = "userId", required = false) Long userId){
        if(userId == null){
//            throw new RuntimeException("id不能为空！");
            return null;
        }
        SysMember member = sysMemberService.echo(userId);
        return member;
    }

    @Log
    @PreAuthorize("@ss.hasPermi('system:member:consume')")
    @PutMapping("/deduction")
    public Result<SysMember> deduction(@RequestBody SysMember member){
        if(member == null){
            throw new RuntimeException("参数为空！");
        }
        SysMember sysMember = sysMemberService.deduction(member);
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
    @DeleteMapping("/{userIds}")
    public Result batchDelete(@PathVariable List<Integer> userIds) {

        try {
            sysMemberService.batchDelete(userIds);
            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }
}