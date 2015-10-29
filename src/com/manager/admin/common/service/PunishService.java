package com.manager.admin.common.service;

import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.Punish;
import java.util.List;

public interface PunishService {
    int countByExample(Criteria example);

    Punish selectByPrimaryKey(Integer id);

    List<Punish> selectByExample(Criteria example);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Punish record);

    int updateByPrimaryKey(Punish record);

    int deleteByExample(Criteria example);

    int insert(Punish record);

    int insertSelective(Punish record);
}