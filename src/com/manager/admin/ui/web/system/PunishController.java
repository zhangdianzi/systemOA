package com.manager.admin.ui.web.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.manager.admin.common.Constants;
import com.manager.admin.common.exception.AdminException;
import com.manager.admin.common.service.AccountService;
import com.manager.admin.common.service.PunishService;
import com.manager.admin.common.to.Account;
import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.Punish;
import com.manager.admin.common.utils.DwzJsonUtil;
import com.manager.admin.common.utils.RequestUtil;
import com.manager.admin.common.web.Funcs;

@Controller
@RequestMapping("/system/punish")
public class PunishController implements Constants {

    public static final int listPageSize = 20;

    @Autowired
    private PunishService punishService;
    @Autowired
    private AccountService accountService;
    
    
    @RequestMapping("/list.html")
    public ModelAndView list(HttpServletRequest request) throws AdminException {
    	HttpSession session = request.getSession();
    	String screenH=(String) session.getAttribute("SCREENH");
    	int listSize=(Integer.parseInt(screenH)-170)/26-2 ;
    	Integer pageNo = RequestUtil.getInteger(request, "pageNum",1);
    	String kinds = RequestUtil.getString(request, "kinds");
    	Account user = Funcs.getSessionLoginUser(request.getSession());
    	
    	Criteria criteria = new Criteria();
    	if(!kinds.isEmpty())
    	criteria.put("kinds", kinds);
    	if(user.getRoleID() ==2 || user.getRoleID() ==3 ||user.getRoleID() ==4 ){
    	criteria.put("account_id", user.getId());
    	}else if(user.getRoleID() ==5){
    		criteria.put("groupName", user.getGroupName());
    	}
    	
    	
    	Integer totalCnt=punishService.countByExample(criteria);
    	
    	int offset = listSize * (pageNo - 1);
        criteria.setMysqlOffset(offset);
        criteria.setMysqlLength(listSize);
        criteria.setOrderByClause(" id desc ");
    	List<Punish> list=punishService.selectByExample(criteria);
    	 request.setAttribute("list", list);
    	 request.setAttribute("kinds", kinds);
    	 request.setAttribute("pageNo", pageNo);
         request.setAttribute("pageSize", listSize);
         request.setAttribute("totalCnt", totalCnt);
         return new ModelAndView("/pages/system/punish_list");
    }
    
    
    @RequestMapping("/toAdd.html")
    public ModelAndView toAdd(HttpServletRequest request)throws AdminException {
    	Criteria criteria = new Criteria();
    	List<Account> accountList=accountService.selectByExample(criteria);
    	request.setAttribute("accountList", accountList);
        return new ModelAndView("/pages/system/punish_add");
    }
    @RequestMapping("/toDetail.html")
    public ModelAndView toDetail(HttpServletRequest request)throws AdminException {
    	Integer punish_id = RequestUtil.getInteger(request, "punish_id",-1);
    	Punish punish=punishService.selectByPrimaryKey(punish_id);
    	Criteria criteria = new Criteria();
    	List<Account> accountList=accountService.selectByExample(criteria);
    	request.setAttribute("accountList", accountList);
    	request.setAttribute("punish", punish);
    	return new ModelAndView("/pages/system/punish_detail");
    }
    
    
    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws AdminException {
    	Punish punish=new Punish();
    	Map<String, String> res = new HashMap<String, String>();
    	
    	Integer account_id = RequestUtil.getInteger(request, "account_id",-1);
    	Integer kinds = RequestUtil.getInteger(request, "kinds",-1);
    	Integer money = RequestUtil.getInteger(request, "money",null);
    	String reason = RequestUtil.getString(request, "reason");
    	punish.setAccount_id(account_id);
    	punish.setKinds(kinds);
    	punish.setMoney(money);
    	punish.setReason(reason);
    	punish.setCreateTime(new Date());
    	
    	punishService.insert(punish);
    	res.put("statusCode", "200");
  		res.put("message", "添加成功");
         return new ModelAndView(JSON_VIEW, JSON_ROOT, res);
    }
    
    @RequestMapping("/detail")
    public ModelAndView detail(HttpServletRequest request) throws AdminException {
    	Map<String, String> res = new HashMap<String, String>();
    	Integer account_id = RequestUtil.getInteger(request, "account_id",-1);
    	Punish punish=punishService.selectByPrimaryKey(account_id);
    	
    	Integer kinds = RequestUtil.getInteger(request, "kinds",-1);
    	Integer money = RequestUtil.getInteger(request, "money",null);
    	String reason = RequestUtil.getString(request, "reason");
    	punish.setAccount_id(account_id);
    	punish.setKinds(kinds);
    	punish.setMoney(money);
    	punish.setReason(reason);
    	punish.setCreateTime(new Date());
    	
    	punishService.insert(punish);
    	res.put("statusCode", "200");
    	res.put("message", "修改成功");
    	return new ModelAndView(JSON_VIEW, JSON_ROOT, res);
    }
    
    @RequestMapping("/del.html")
    public ModelAndView del(HttpServletRequest request) throws AdminException {
        Integer punishId = RequestUtil.getInteger(request, "id",0);
        punishService.deleteByPrimaryKey(punishId);
        return new ModelAndView(JSON_VIEW, JSON_ROOT,DwzJsonUtil.getOkStatusMsg("删除成功"));
    }
}
