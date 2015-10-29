package com.manager.admin.common.service;

import com.manager.admin.common.to.CommonTo;
import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.OrderInfo;

import java.util.List;

public interface OrderInfoService {
    int countByExample(Criteria example);

    OrderInfo selectByPrimaryKey(Long id);

    List<OrderInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    int deleteByExample(Criteria example);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

	List<CommonTo> selectStateGroup(Criteria criteria);

}