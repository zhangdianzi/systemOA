package com.manager.admin.common.mapper;

import com.manager.admin.common.to.CommonTo;
import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.OrderInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderInfoMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(OrderInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(OrderInfo record);

    /**
     * 根据条件查询记录集
     */
    List<OrderInfo> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    OrderInfo selectByPrimaryKey(Long id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") OrderInfo record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") OrderInfo record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(OrderInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(OrderInfo record);

	List<CommonTo> selectStateGroup(Criteria criteria);

	int countManagerWaitToDo(Integer dayNum);
}