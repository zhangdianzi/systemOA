package com.manager.admin.common.service;

import java.util.List;

import com.manager.admin.common.to.Account;
import com.manager.admin.common.to.Menu;


public interface AccountMenuService
{

	List<Menu> getMenuListByAccountId(Integer id);

	void addDefaultMenu(Integer accountId, Integer roleId);

	void add(Integer accountId, Integer menuId);

	void deleteByAccountId(Integer accountId);


	void saveAccountMenus(Account account, List<Integer> menuIds);

	void deleteByMenuId(Integer menuId);

}
