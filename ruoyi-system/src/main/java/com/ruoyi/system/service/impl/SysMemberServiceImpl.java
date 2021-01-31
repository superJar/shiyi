package com.ruoyi.system.service.impl;


import com.ruoyi.system.domain.SysMember;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.mapper.SysMemberMapper;
import com.ruoyi.system.service.SysMemberService;
import com.ruoyi.system.service.SysTransactionDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
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
    @Resource
    private SysTransactionDetailService transactionDetailService;
    private final static String WOLF_KILL = "狼人杀";
    private final static String ROOM = "包房";

    @Override
    public List<SysMember> page(SysMember member) {
        return sysMemberMapper.queryCondition(member);
    }

    @Transactional
    @Override
    public Integer addOrEdit(SysMember member, String username) {
        return member.getId() == null ? insert(member, username) : update(member, username);
    }

    private Integer update(SysMember member, String username) {
        // 更新交易明细
        SysMember memberFromDB = sysMemberMapper.queryById(member.getId());
        transactionDetailService.updateMemberName(memberFromDB.getName(),member.getName());

        member.setUpdatedBy(username);
        member.setUpdatedTime(new Date());
        return sysMemberMapper.update(member);
    }



    private Integer insert(SysMember member, String username) {
        member.setCreatedBy(username);
        member.setCreatedTime(new Date());
        return sysMemberMapper.insertSelective(member);
    }

    @Transactional
    @Override
    public SysMember topUp(SysMember member) {
        //Amount from frontEnd
        BigDecimal topUpAmount = member.getTopUpAmount();
        float amount = topUpAmount.intValue();

        //obj from db
        SysMember memberFromDB = sysMemberMapper.queryById(member.getId());
        double sumOfTopUpFromDB = 0;
        if (memberFromDB.getSumOfTopUp() != null) {
            sumOfTopUpFromDB = memberFromDB.getSumOfTopUp().doubleValue();
        }
        double balanceFromDB = 0;
        if (memberFromDB.getBalance() != null) {
            balanceFromDB = memberFromDB.getBalance().doubleValue();
        }
//        Double sumOfTopUpFromDB = memberFromDB.getSumOfTopUp();
        double additionalBalanceFromDB = 0;
        if (memberFromDB.getAdditionalBalance() != null) {
            additionalBalanceFromDB = memberFromDB.getAdditionalBalance().doubleValue();
        }

        //充值总额
        //充值余额增加
        //附加充值余额增加
        Integer additional = 0;
        additional = this.getAdditionalAmount(additional, amount);


        memberFromDB.setAdditionalBalance((new BigDecimal(additionalBalanceFromDB + additional)));
        memberFromDB.setSumOfTopUp(topUpAmount.add(new BigDecimal(sumOfTopUpFromDB)));
//        memberFromDB.setSumOfTopUp(memberFromDB.getSumOfTopUp() + topUpAmount.doubleValue());
        memberFromDB.setBalance(topUpAmount.add(new BigDecimal(balanceFromDB)));
        sysMemberMapper.update(memberFromDB);

//        if (additional != 0) {
//            member.setAdditional(additional);
//        }
        memberFromDB.setTopUpAmount(topUpAmount);
        memberFromDB.setAdditional(additional);
        return memberFromDB;
    }


    /**
     * 消费
     *
     * @param member
     * @return
     */
    @Transactional
    @Override
    public SysMember consume(SysMember member) {
        //获取前端传的products并解析
        List<SysProduct> products = member.getProducts();
        if (CollectionUtils.isEmpty(products)) {
            throw new RuntimeException("系统检测到该会员没选取任何商品进行消费，因此无法进行消费操作！");
        }
        //找到数据库里的member
        SysMember memberFromDB = sysMemberMapper.queryById(member.getId());
        float extraBalanceFromDB = 0;
        if (memberFromDB.getAdditionalBalance() != null) {
            extraBalanceFromDB = memberFromDB.getAdditionalBalance().floatValue();
        }
        float balanceFromDB = 0;
        if (memberFromDB.getBalance() != null) {
            balanceFromDB = memberFromDB.getBalance().floatValue();
        }

        if (memberFromDB.getAdditionalBalance().intValue() == 0 && memberFromDB.getBalance().intValue() == 0) {
            throw new RuntimeException("该用户已经没钱扣了！");
        }

        // 判断用户余额是否足以扣除本次消费
        double sumOfExpenditure = sumOfExpenditure(products);

        // 本次消费不够扣，需要返回错误信息给前端
        if ((extraBalanceFromDB + balanceFromDB) < sumOfExpenditure) {
            throw new RuntimeException("该会员目前的余额不足以扣除本次消费，" +
                    "扣除完余额后还需要支付："
                    + ((extraBalanceFromDB + balanceFromDB) - sumOfExpenditure)
                    + "元");
        }

        //拿附加余额抵扣
        memberFromDB = deductionFromBalance(sumOfExpenditure, balanceFromDB, extraBalanceFromDB, memberFromDB);
        memberFromDB.setSumOfConsumption(memberFromDB.getSumOfConsumption() + sumOfExpenditure);
        sysMemberMapper.update(memberFromDB);

        memberFromDB.setSumOfExpenditure(sumOfExpenditure);
        return memberFromDB;
    }

    /**
     * 扣除余额
     *
     * @param sumOfExpenditure
     * @param balanceFromDB
     * @param extraBalanceFromDB
     * @param memberFromDB
     * @return
     */
    private SysMember deductionFromBalance(double sumOfExpenditure, float balanceFromDB, float extraBalanceFromDB, SysMember memberFromDB) {
        if (balanceFromDB > 0) {
            if (sumOfExpenditure > balanceFromDB) {
                //如果附加余额不够抵扣本次消费
                sumOfExpenditure -= balanceFromDB;
                extraBalanceFromDB -= sumOfExpenditure;
                memberFromDB.setBalance(new BigDecimal(0));
                memberFromDB.setAdditionalBalance(new BigDecimal(extraBalanceFromDB));
            } else {
                balanceFromDB -= sumOfExpenditure;
                memberFromDB.setBalance(new BigDecimal(balanceFromDB));
            }
        } else {
            extraBalanceFromDB -= sumOfExpenditure;
            memberFromDB.setAdditionalBalance(new BigDecimal(extraBalanceFromDB));
        }
        return memberFromDB;
    }

    /**
     * 算出本次消费金额总额
     *
     * @param products
     * @return
     */
    private Double sumOfExpenditure(List<SysProduct> products) {
        DoubleSummaryStatistics sum = products.stream().collect(Collectors.summarizingDouble(
                product -> {
                    LocalDateTime now = LocalDateTime.now();
                    int week = now.getDayOfWeek().getValue();
                    int hour = now.getHour();
                    double finalPrice;
                    // 狼人杀：
                    if (product.getName().equals(WOLF_KILL)) {
                        double price = product.getPrice().doubleValue();
                        double additionalPrice = product.getAdditionalPrice().doubleValue();

                        // 周五到周日：平时65，超过2点85/人
                        if (isWeekend(week)) {
                            if (isMidNight(hour)) {
                                finalPrice = price + additionalPrice + 20;
                            } else {
                                finalPrice = price + 20;
                            }
                        } else {
                            // 周一到周四：平时45，超过凌晨2点65/人
                            if (isMidNight(hour)) {
                                finalPrice = price + additionalPrice;
                            } else {
                                finalPrice = price;
                            }
                        }
                        return finalPrice;
                    }

                    // 包房：
                    if (product.getName().contains(ROOM)) {
                        if (isWeekend(week)) {
                            finalPrice = product.getAdditionalPrice().doubleValue();
                        } else {
                            finalPrice = product.getPrice().doubleValue();
                        }
                        return finalPrice;
                    }

                    return product.getPrice().doubleValue();
                }
        ));

        return sum.getSum();
    }


    /**
     * 是否超过凌晨2点
     *
     * @param hour
     * @return
     */
    private boolean isMidNight(int hour) {
        return hour >= 2 && hour <= 6;
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Transactional
    @Override
    public void batchDelete(List<Integer> ids) {
        sysMemberMapper.batchLogicDel(ids);
    }

    @Transactional
    @Override
    public void batchAdd(List<SysMember> members) {
        members.stream().forEach(member -> sysMemberMapper.insertSelective(member));
    }

    @Override
    public SysMember echo(Long id) {
        return sysMemberMapper.queryById(id);
    }

    @Transactional
    @Override
    public SysMember deduction(SysMember member) {
        SysMember memberFromDB = sysMemberMapper.queryById(member.getId());

        Double sumOfExpenditure = member.getSumOfExpenditure();
        float totalBalance = 0;
        if (memberFromDB.getAdditionalBalance() != null) {

            totalBalance = memberFromDB.getBalance().add(memberFromDB.getAdditionalBalance()).floatValue();
        } else {
            totalBalance = memberFromDB.getBalance().floatValue();
        }

        if (sumOfExpenditure > totalBalance) {
            throw new RuntimeException("里面不够钱扣了！");
        }

        if (memberFromDB.getAdditionalBalance() == null) {
            memberFromDB = deductionFromBalance(sumOfExpenditure, memberFromDB.getBalance().floatValue(), 0, memberFromDB);

        } else {

            memberFromDB = deductionFromBalance(sumOfExpenditure, memberFromDB.getBalance().floatValue(), memberFromDB.getAdditionalBalance().floatValue(), memberFromDB);
        }
        if (memberFromDB.getSumOfConsumption() != null) {
            memberFromDB.setSumOfConsumption(memberFromDB.getSumOfConsumption() + sumOfExpenditure);
        } else {
            memberFromDB.setSumOfConsumption(sumOfExpenditure);
        }
        memberFromDB.setSumOfExpenditure(sumOfExpenditure);
        sysMemberMapper.update(memberFromDB);
        return memberFromDB;
    }



    @Override
    public Integer findExist(String name, String cardNum,Long id) {
        return sysMemberMapper.getCountByCondition(name,cardNum,id);
    }

    /**
     * 是否周末
     *
     * @param week
     * @return
     */
    private boolean isWeekend(int week) {
        return week >= 5 && week <= 7;
    }

    /**
     * 获取所有充值金额
     *
     * @param additional
     * @param amount
     * @return
     */
    private Integer getAdditionalAmount(Integer additional, float amount) {
        if (amount < 500) {
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
            additional = getAdditionalAmount(additional, amount);
        }
        return additional;
    }
}