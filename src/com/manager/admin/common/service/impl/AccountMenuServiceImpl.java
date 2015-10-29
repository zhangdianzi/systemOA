package com.manager.admin.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.admin.common.mapper.AccountMenuMapper;
import com.manager.admin.common.service.AccountMenuService;
import com.manager.admin.common.to.Account;
import com.manager.admin.common.to.AccountMenu;
import com.manager.admin.common.to.Menu;

@Service
public class AccountMenuServiceImpl implements AccountMenuService
{
	
	
	@Autowired
	private AccountMenuMapper accountMenuMapper;


    @Override
    public void add(Integer accountId, Integer menuId) {
        AccountMenu record=new AccountMenu();
        record.setAccountId(accountId);
        record.setMenuId(menuId);
        accountMenuMapper.add(record);
    }

    @Override
    public void deleteByAccountId(Integer accountId) {
        accountMenuMapper.deleteByAccountId(accountId);
    }

    @Override
    public void deleteByMenuId(Integer menuId) {
        accountMenuMapper.deleteByMenuId(menuId);
    }

    @Override
    public void saveAccountMenus(Account account, List<Integer> menuIds) {
        this.deleteByAccountId(account.getId());
        for(Integer menuId:menuIds){
            this.add(account.getId(), menuId);
        }
    }

    @Override
    public void addDefaultMenu(Integer accountId,Integer roleId) {
        accountMenuMapper.addDefaultMenu(accountId, roleId);        
    }

	@Override
	public List<Menu> getMenuListByAccountId(Integer id) {
		return accountMenuMapper.getMenuListByAccountId(id);
	}

}
