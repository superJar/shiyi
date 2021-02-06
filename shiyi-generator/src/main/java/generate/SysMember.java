package generate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * sys_member
 * @author 
 */
public class SysMember implements Serializable {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 卡号
     */
    private String cardNum;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别 0-女性，1-男性
     */
    private String gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 附加余额 该余额不算作正式余额
     */
    private BigDecimal additionalBalance;

    /**
     * 充值总计
     */
    private BigDecimal sumOfTopUp;

    /**
     * 消费总计
     */
    private BigDecimal sumOfConsumption;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 备用字段1
     */
    private String spare1;

    /**
     * 备用字段2
     */
    private String spare2;

    /**
     * 备用字段3
     */
    private String spare3;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAdditionalBalance() {
        return additionalBalance;
    }

    public void setAdditionalBalance(BigDecimal additionalBalance) {
        this.additionalBalance = additionalBalance;
    }

    public BigDecimal getSumOfTopUp() {
        return sumOfTopUp;
    }

    public void setSumOfTopUp(BigDecimal sumOfTopUp) {
        this.sumOfTopUp = sumOfTopUp;
    }

    public BigDecimal getSumOfConsumption() {
        return sumOfConsumption;
    }

    public void setSumOfConsumption(BigDecimal sumOfConsumption) {
        this.sumOfConsumption = sumOfConsumption;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getSpare1() {
        return spare1;
    }

    public void setSpare1(String spare1) {
        this.spare1 = spare1;
    }

    public String getSpare2() {
        return spare2;
    }

    public void setSpare2(String spare2) {
        this.spare2 = spare2;
    }

    public String getSpare3() {
        return spare3;
    }

    public void setSpare3(String spare3) {
        this.spare3 = spare3;
    }
}