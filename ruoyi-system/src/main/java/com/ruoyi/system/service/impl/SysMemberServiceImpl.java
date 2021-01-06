package com.ruoyi.system.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.SysMember;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.mapper.SysMemberMapper;
import com.ruoyi.system.service.SysMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 会员表 (SysMember)表服务实现类
 *
 * @author makejava
 * @since 2021-01-06 17:43:49
 */
@Service("sysMemberService")
public class SysMemberServiceImpl implements SysMemberService {
    @Resource
    private SysMemberMapper sysMemberMapper;

    @Override
    public PageInfo<SysMember> page(Integer pageNum, Integer pageSize, String name, String nickname) {
        Page<SysMember> page = PageHelper.startPage(pageNum, pageSize);
        List<SysMember> list = sysMemberMapper.queryCondition("%"+name+"%","%"+nickname+"%");
        PageInfo<SysMember> pageInfo = page.toPageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public Integer addOrEdit(SysMember member) {
        return member.getId() == null ? sysMemberMapper.insertSelective(member) : sysMemberMapper.update(member);
    }

    @Override
    public boolean topUp(SysMember member) {
        //Amount from frontEnd
        BigDecimal topUpAmount = member.getTopUpAmount();
        float amount = topUpAmount.intValue();

        //obj from db
        SysMember memberFromDB = sysMemberMapper.queryById(member.getId());
        BigDecimal sumOfTopUpFromDB = memberFromDB.getSumOfTopUp();
        BigDecimal balanceFromDB = memberFromDB.getBalance();
        BigDecimal additionalBalanceFromDB = memberFromDB.getAdditionalBalance();

        //充值总额
        //充值余额增加
        //附加充值余额增加
        Integer additional = 0;
        additional = this.getAdditionalAmount(additional,amount);

        memberFromDB.setAdditionalBalance(additionalBalanceFromDB.add(new BigDecimal(additional)));
        memberFromDB.setSumOfTopUp(sumOfTopUpFromDB.add(topUpAmount));
        memberFromDB.setBalance(balanceFromDB.add(topUpAmount));
        int count = sysMemberMapper.update(memberFromDB);

        return count > 0;
    }

    @Transactional
    @Override
    public boolean consume(SysMember member) {
        //获取前端传的products并解析
        List<SysProduct> products = member.getProducts();
        if (CollectionUtils.isEmpty(products)) {
            throw new RuntimeException("系统检测到该会员没选取任何商品进行消费，因此无法进行消费操作！");
        }
        //找到数据库里的member
        SysMember memberFromDB = sysMemberMapper.queryById(member.getId());
        float extraBalanceFromDB = memberFromDB.getAdditionalBalance().floatValue();
        float balanceFromDB = memberFromDB.getBalance().floatValue();

        if(memberFromDB.getAdditionalBalance().intValue() == 0 && memberFromDB.getBalance().intValue() == 0){
            throw new RuntimeException("该用户已经没钱扣了！");
        }

        //判断用户余额是否足以扣除本次消费
        DoubleSummaryStatistics sum = products.stream().collect(Collectors.summarizingDouble(
                product -> product.getPrice().add(product.getAdditionalPrice()).floatValue()
        ));

        double sumOfExpenditure = sum.getSum();

        //如果本次消费不够扣，需要返回错误信息给前端
        if((extraBalanceFromDB + balanceFromDB) < sumOfExpenditure){
            throw new RuntimeException("该会员目前的余额不足以扣除本次消费，" +
                    "扣除完余额后还需要支付："
                    +((extraBalanceFromDB + balanceFromDB) - sumOfExpenditure)
                    +"元");
        }

        //拿附加余额抵扣
        if(extraBalanceFromDB > 0){
            if(sumOfExpenditure > extraBalanceFromDB){
                //如果附加余额不够抵扣本次消费
                sumOfExpenditure -= extraBalanceFromDB;
                balanceFromDB -= sumOfExpenditure;
                memberFromDB.setBalance(new BigDecimal(balanceFromDB));
                memberFromDB.setAdditionalBalance(new BigDecimal(0));
            }else{
                extraBalanceFromDB -= sumOfExpenditure;
                memberFromDB.setAdditionalBalance(new BigDecimal(extraBalanceFromDB));
            }
        }

        int count = sysMemberMapper.update(memberFromDB);

        return count > 0;
    }

    @Override
    public void batchDelete(List<String> ids) {
        sysMemberMapper.batchDelete(ids);
    }

    /**
     * 获取所有充值金额
     * @param additional
     * @param amount
     * @return
     */
    private Integer getAdditionalAmount(Integer additional, float amount) {
        if(amount < 500){
            return additional;
        }

        if (amount >= 500 && amount < 1000) {
            additional += 50;
        } else if (amount >= 1000 && amount < 2000) {
            additional += 150;
        } else if (amount >= 2000 && amount < 3000) {
            additional += 400;
        } else if (amount >= 3000) {
            additional += 700;
            //充值金额大于3500，需要拆分
            amount -= 3000;
            additional = getAdditionalAmount(additional,amount);
        }
        return additional;
    }
}