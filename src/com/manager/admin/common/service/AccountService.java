package com.manager.admin.common.service;

import com.manager.admin.common.to.Account;
import com.manager.admin.common.to.Criteria;

import java.util.List;

public interface AccountService {
    int countByExample(Criteria example);

    Account selectByPrimaryKey(Integer id);

    List<Account> selectByExample(Criteria example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int insert(Account record);

    int insertSelective(Account record);

	void deleteByPrimaryKey(Integer accountId);
	//获得管理员和超级管理员的accountId
	List<Account> getAdminId();

	List<Account> selectRoleID();

	void updateSalary();
}