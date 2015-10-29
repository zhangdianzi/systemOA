package com.manager.admin.common.to;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;

    private String password;

    /**
     * 1经理 2业务员 3风控员 4跟单员
     */
    private Integer roleID;

    private String realName;

    private String email;

    private String phone;

    private String address;

    private String groupName;

    private Integer curSalary;

    private Integer baseSalary;

    private Integer state;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return 1经理 2业务员 3风控员 4跟单员
     */
    public Integer getRoleID() {
        return roleID;
    }

    /**
     * @param roleid 
	 *            1经理 2业务员 3风控员 4跟单员
     */
    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getCurSalary() {
        return curSalary;
    }

    public void setCurSalary(Integer curSalary) {
        this.curSalary = curSalary;
    }

    public Integer getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Integer baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}