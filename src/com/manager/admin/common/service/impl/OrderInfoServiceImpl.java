package com.manager.admin.common.service.impl;

import com.manager.admin.common.mapper.OrderInfoMapper;
import com.manager.admin.common.service.OrderInfoService;
import com.manager.admin.common.to.CommonTo;
import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.OrderInfo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.orderInfoMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public OrderInfo selectByPrimaryKey(Long id) {
        return this.orderInfoMapper.selectByPrimaryKey(id);
    }

    public List<OrderInfo> selectByExample(Criteria example) {
        return this.orderInfoMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.orderInfoMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(OrderInfo record) {
        return this.orderInfoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(OrderInfo record) {
        return this.orderInfoMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.orderInfoMapper.deleteByExample(example);
    }

    public int insert(OrderInfo record) {
        return this.orderInfoMapper.insert(record);
    }

    public int insertSelective(OrderInfo record) {
        return this.orderInfoMapper.insertSelective(record);
    }

	@Override
	public List<CommonTo> selectStateGroup(Criteria criteria) {
		return orderInfoMapper.selectStateGroup(criteria);
	}

}