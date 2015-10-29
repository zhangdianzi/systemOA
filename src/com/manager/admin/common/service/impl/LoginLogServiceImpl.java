package com.manager.admin.common.service.impl;

import com.manager.admin.common.mapper.LoginLogMapper;
import com.manager.admin.common.service.LoginLogService;
import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.LoginLog;
import com.manager.admin.common.utils.HttpClientUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    private LoginLogMapper loginLogMapper;

    private static final Logger logger = LoggerFactory.getLogger(LoginLogServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.loginLogMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public LoginLog selectByPrimaryKey(Long id) {
        return this.loginLogMapper.selectByPrimaryKey(id);
    }

    public List<LoginLog> selectByExample(Criteria example) {
        return this.loginLogMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.loginLogMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(LoginLog record) {
        return this.loginLogMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(LoginLog record) {
        return this.loginLogMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.loginLogMapper.deleteByExample(example);
    }

    public int insert(LoginLog record) {
        return this.loginLogMapper.insert(record);
    }

    public int insertSelective(LoginLog record) {
        return this.loginLogMapper.insertSelective(record);
    }
	/**
	 * 根据IP更新对应的省份市和网络 
	 */
    
	public List<Object> getByIP(String ip) {
		List<Object> getByIp=new ArrayList<Object>();
			String url = "http://www.ip138.com/ips138.asp?ip="+ip+"&action=2";
			String s = HttpClientUtil.getSpiderInfo(url, "GET", "GBK");

			String regex = "<li>本站主数据：(.*?(省|市|自治区))(.*?)\\s(.*?)</li>";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(s);
			if (matcher.find()) {
				String group1 = matcher.group(1);
				String group3 = matcher.group(3);
				String group4 = matcher.group(4);
				System.out.println(group1+group3+group4);
				
				if(group1.contains("广西"))
					group1="广西";
				if(group1.contains("西藏"))
					group1="西藏";
				if(group1.contains("内蒙古"))
					group1="内蒙古";
				if(group1.contains("新疆"))
					group1="新疆";
				if(group1.contains("宁夏"))
					group1="宁夏";
				getByIp.add(0, group1);
				getByIp.add(1, group3);
				if(group4.contains("电信"))
					group4="电信";
				else if(group4.contains("移动"))
					group4="移动";
				else if(group4.contains("联通"))
					group4="联通";
				else
					group4="其他";
				getByIp.add(2, group4.trim());
			} else {
				getByIp.add(0, "未知");
				getByIp.add(1, "未知");
				getByIp.add(2, "未知");
			}
			return getByIp;
		
	}

	@Override
	public Integer selectLogFailNumByIP(String ip) {
		return loginLogMapper.selectLogFailNumByIP(ip);
	}
}