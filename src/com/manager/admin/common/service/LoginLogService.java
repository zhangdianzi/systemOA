package com.manager.admin.common.service;

import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.LoginLog;

import java.util.List;

public interface LoginLogService {
    int countByExample(Criteria example);

    LoginLog selectByPrimaryKey(Long id);

    List<LoginLog> selectByExample(Criteria example);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);

    int deleteByExample(Criteria example);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

	List<Object> getByIP(String ip);

	Integer selectLogFailNumByIP(String ip);
}