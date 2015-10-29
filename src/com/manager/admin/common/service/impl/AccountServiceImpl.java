package com.manager.admin.common.service.impl;

import com.manager.admin.common.mapper.AccountMapper;
import com.manager.admin.common.service.AccountService;
import com.manager.admin.common.to.Account;
import com.manager.admin.common.to.Criteria;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.accountMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public Account selectByPrimaryKey(Integer id) {
        return this.accountMapper.selectByPrimaryKey(id);
    }

    public List<Account> selectByExample(Criteria example) {
        return this.accountMapper.selectByExample(example);
    }

    public int updateByPrimaryKeySelective(Account record) {
        return this.accountMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Account record) {
        return this.accountMapper.updateByPrimaryKey(record);
    }

    public int insert(Account record) {
        return this.accountMapper.insert(record);
    }

    public int insertSelective(Account record) {
        return this.accountMapper.insertSelective(record);
    }

	@Override
	public void deleteByPrimaryKey(Integer id) {
		accountMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Account> getAdminId() {
		return this.accountMapper.getAdminId();
		
	}

	@Override
	public List<Account> selectRoleID() {
		return this.accountMapper.selectRoleID();
	}

	@Override
	public void updateSalary() {
		 accountMapper.updateSalary();
	}
}