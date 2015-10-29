package com.manager.admin.common.mapper;

import java.util.List;

import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

	List<Menu> selectByExample(Criteria criteria);
}