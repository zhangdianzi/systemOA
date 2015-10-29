package com.manager.admin.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.manager.admin.common.mapper.AccountMenuMapper;
import com.manager.admin.common.mapper.MenuMapper;
import com.manager.admin.common.service.MenuService;
import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.Menu;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
    private MenuMapper menuMapper;
	@Autowired
	private AccountMenuMapper accountMenuMapper;
	
    @Override
    public List<Menu> getMenuList() {
        Criteria criteria=new Criteria();
        criteria.setOrderByClause(" parentIdPath, orderNo ");
        return menuMapper.selectByExample(criteria);
    }
    @Override
    public Menu getById(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }
    @Override
    public int updateByIdSelective(Menu record) {
        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void deleteById(Integer id){
        menuMapper.deleteByPrimaryKey(id);
        accountMenuMapper.deleteByMenuId(id);
    }
    @Cacheable(value = "commonCache", key = "'Menu_id_' + #id")
    @Override
    public Menu getByIdInCache(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }
    @Override
    public void saveOrUpdate(Menu record) {
        if(record.getId() == null || record.getId().intValue() < 1){
             menuMapper.insert(record);
        }else{
        	menuMapper.deleteByPrimaryKey(record.getId());
        	menuMapper.insert(record);
        }
    }

}
