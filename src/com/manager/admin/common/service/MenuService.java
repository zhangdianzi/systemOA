package com.manager.admin.common.service;

import java.util.List;

import com.manager.admin.common.to.Menu;


public interface MenuService {

	List<Menu> getMenuList();

	Menu getById(Integer id);

	void deleteById(Integer menuId);

	Menu getByIdInCache(Integer menuId);

	int updateByIdSelective(Menu record);

	void saveOrUpdate(Menu record);

}