package com.manager.admin.common.to;

import java.io.Serializable;
import java.util.Date;

public class Punish implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer account_id;

    /**
     * 1奖励2处罚
     */
    private Integer kinds;

    private Integer money;

    private Integer order_id;

    private String reason;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    /**
     * @return 1奖励2处罚
     */
    public Integer getKinds() {
        return kinds;
    }

    /**
     * @param kinds 
	 *            1奖励2处罚
     */
    public void setKinds(Integer kinds) {
        this.kinds = kinds;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}