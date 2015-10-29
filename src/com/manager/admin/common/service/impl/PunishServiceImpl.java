package com.manager.admin.common.service.impl;

import com.manager.admin.common.mapper.PunishMapper;
import com.manager.admin.common.service.PunishService;
import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.Punish;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PunishServiceImpl implements PunishService {
    @Autowired
    private PunishMapper punishMapper;

    private static final Logger logger = LoggerFactory.getLogger(PunishServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.punishMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public Punish selectByPrimaryKey(Integer id) {
        return this.punishMapper.selectByPrimaryKey(id);
    }

    public List<Punish> selectByExample(Criteria example) {
        return this.punishMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.punishMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Punish record) {
        return this.punishMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Punish record) {
        return this.punishMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.punishMapper.deleteByExample(example);
    }

    public int insert(Punish record) {
        return this.punishMapper.insert(record);
    }

    public int insertSelective(Punish record) {
        return this.punishMapper.insertSelective(record);
    }
}