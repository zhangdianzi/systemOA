package com.manager.admin.common.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.manager.admin.common.service.AccountService;
import com.manager.admin.common.service.OrderInfoService;
import com.manager.admin.common.service.PunishService;
import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.OrderInfo;
import com.manager.admin.common.to.Punish;

public class DayTask {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private PunishService punishService;
	@Autowired
	private OrderInfoService orderInfoService;
	/**
	 * 每日统计
	 */
	public void countChaoShi() {
		Criteria yewuCriteria=new Criteria();
		yewuCriteria.put("data_buwanzheng", 1);
		yewuCriteria.put("data_chaoshi", 1);
		List<OrderInfo> orderInfoList=orderInfoService.selectByExample(yewuCriteria);
		for(OrderInfo orderInfo:orderInfoList){
			Criteria cri =new Criteria();
			cri.put("account_id", orderInfo.getSalesman());
			cri.put("order_id", orderInfo.getId());
			Integer  isExist=punishService.countByExample(cri);
			if(isExist<1){
				Punish punish=new Punish();
				punish.setAccount_id(orderInfo.getSalesman());
				punish.setCreateTime(new Date());
				punish.setKinds(2);
				punish.setMoney(100);
				long aa=orderInfo.getId();
				Integer orderid=new Integer((int) aa);
				punish.setOrder_id(orderid);
				punish.setReason("订单"+orderInfo.getId()+"超时两天未处理。");
				punishService.insert(punish);
			}
		}
		
		
		Criteria gendanCriteria=new Criteria();
		gendanCriteria.put("waitToDoGenDan", 1);
		gendanCriteria.put("data_chaoshi", 1);
		List<OrderInfo> orderInfoList2=orderInfoService.selectByExample(gendanCriteria);
		for(OrderInfo orderInfo:orderInfoList2){
			Criteria cri =new Criteria();
			cri.put("account_id", orderInfo.getDocumentary());
			cri.put("order_id", orderInfo.getId());
			Integer  isExist=punishService.countByExample(cri);
			if(isExist<1){
				Punish punish=new Punish();
				punish.setAccount_id(orderInfo.getDocumentary());
				punish.setCreateTime(new Date());
				punish.setKinds(2);
				punish.setMoney(100);
				long aa=orderInfo.getId();
				Integer orderid=new Integer((int) aa);
				punish.setOrder_id(orderid);
				punish.setReason("订单"+orderInfo.getId()+"超时两天未处理。");
				punishService.insert(punish);
			}
		}
		accountService.updateSalary();
	}
}
