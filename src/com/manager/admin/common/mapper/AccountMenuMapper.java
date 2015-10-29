package com.manager.admin.common.mapper;

import java.util.List;

import com.manager.admin.common.to.AccountMenu;
import com.manager.admin.common.to.Menu;

public interface AccountMenuMapper {

	void add(AccountMenu record);

	void deleteByAccountId(Integer accountId);

	void deleteByMenuId(Integer menuId);

	void addDefaultMenu(Integer accountId, Integer roleId);

	List<Menu> getMenuListByAccountId(Integer accountId);

}