package com.manager.admin.ui.web.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.manager.admin.common.Constants;
import com.manager.admin.common.StaticCache;
import com.manager.admin.common.exception.AdminException;
import com.manager.admin.common.service.AccountMenuService;
import com.manager.admin.common.service.AccountService;
import com.manager.admin.common.service.LoginLogService;
import com.manager.admin.common.to.Account;
import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.LoginLog;
import com.manager.admin.common.utils.DwzJsonUtil;
import com.manager.admin.common.utils.RequestUtil;
import com.manager.admin.common.web.Funcs;

@Controller
@RequestMapping("/system/account")
public class AccountController implements Constants {

    public static final int listPageSize = 20;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMenuService accountMenuService;
    
    @Autowired
    private LoginLogService loginLogService;

    /**
     * 用户详情列表
     * 
     * @param request
     * @return ModelAndView
     * @throws AdminException
     */
    @RequestMapping("/list.html")
    public ModelAndView list(HttpServletRequest request) throws AdminException {
    	HttpSession session = request.getSession();
//    	String screenH=(String) session.getAttribute("SCREENH");
//    	int listSize=(Integer.parseInt(screenH)-170)/26-2 ;
    	int listSize=15;
    	Integer roleID = RequestUtil.getInteger(request, "roleID",0);
        String realName = RequestUtil.getString(request, "realName");
        String userName = RequestUtil.getString(request, "userName");
        Integer pageNo = RequestUtil.getInteger(request, "pageNum",1);
        Criteria criteria = new Criteria();
       
        if (StringUtils.isNotBlank(userName)) {
            criteria.put("userName", userName);
        }
        if (StringUtils.isNotBlank(realName)) {
            criteria.put("realName", realName);
        }
        if (roleID != 0) {
        	criteria.put("roleID", roleID);
		}
        Account user = Funcs.getSessionLoginUser(request.getSession());
        Integer roleId=user.getRoleID(); 
        int totalCnt=accountService.countByExample(criteria);
        
        int offset = listSize * (pageNo - 1);
        criteria.setMysqlOffset(offset);
        criteria.setMysqlLength(listSize);
        criteria.setOrderByClause(" id desc ");
        List<Account> list = accountService.selectByExample(criteria);
        
        request.setAttribute("roleId", roleId);
        request.setAttribute("roleID", roleID);
        request.setAttribute("list", list);
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", listSize);
        request.setAttribute("totalCnt", totalCnt);
        request.setAttribute("lastPage", totalCnt/listSize+1);
        return new ModelAndView("/pages/system/account_list");
        
    }

    /**
     * 删除用户
     * 
     * @param request
     * @return ModelAndView
     * @throws AdminException
     */
    @RequestMapping("/del.html")
    public ModelAndView del(HttpServletRequest request) throws AdminException {
        Integer accountId = RequestUtil.getInteger(request, "id",0);
        accountService.deleteByPrimaryKey(accountId);
        return new ModelAndView(JSON_VIEW, JSON_ROOT,DwzJsonUtil.getOkStatusMsg("删除成功"));
    }

    /**
     * 添加
     * 
     * @param request
     * @return
     * @throws AdminException
     */
    @RequestMapping("/goadd.html")
    public ModelAndView goAdd(HttpServletRequest request) throws AdminException {
    	Criteria criteria=new Criteria();
    	criteria.setGroupByClause(" groupName");
        List<Account> groupNameList = accountService.selectByExample(criteria);
        request.setAttribute("groupNameList", groupNameList);
        return new ModelAndView("/pages/system/account_add");
    }
    @RequestMapping("/toSalary.html")
    public ModelAndView toSalary(HttpServletRequest request) throws AdminException {
    	return new ModelAndView("/pages/system/account_salary");
    }

    /**
     * 详情
     * 
     * @param request
     * @return
     * @throws AdminException
     */
    @RequestMapping("/detail.html")
    public ModelAndView detail(HttpServletRequest request)
            throws AdminException {
        Integer id = RequestUtil.getInteger(request, "id",0);
        Account account = accountService.selectByPrimaryKey(id);
        request.setAttribute("account", account);
        
        Criteria criteria=new Criteria();
    	criteria.setGroupByClause(" groupName");
        List<Account> groupNameList = accountService.selectByExample(criteria);
        request.setAttribute("groupNameList", groupNameList);

        return new ModelAndView("/pages/system/account_detail");
    }
    @RequestMapping("/lookGroupName.html")
    public ModelAndView lookGroupName(HttpServletRequest request)
    		throws AdminException {
    	String groupName = RequestUtil.getString(request, "keyword");
    	
    	Criteria criteria=new Criteria();
    	criteria.put("groupNameLike", groupName);
    	criteria.setGroupByClause(" groupName");
    	List<Account> groupNameList = accountService.selectByExample(criteria);
    	request.setAttribute("groupNameList", groupNameList);
    	
    	 List<Map<String, String>> res = new ArrayList<Map<String, String>>();
         Map<String, String> item = new HashMap<String, String>();
         for (Account c : groupNameList)
         {
             item = new HashMap<String, String>();
             item.put("groupName", c.getGroupName());
             res.add(item);
         }
         return new ModelAndView(JSON_VIEW, JSON_ROOT, res);
    }
    

    /**
     * 添加
     * 
     * @param request
     * @return ModelAndView
     * @throws AdminException
     */
    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws AdminException {
    	Criteria criteria=new Criteria();
        String username = RequestUtil.getString(request, "username");
        criteria.put("userName", username);
        List<Account> extAccount = accountService.selectByExample(criteria);
        if (extAccount.size() != 0) {
            throw new AdminException("该用户名已被注册！");
        }
        String password = RequestUtil.getString(request, "passward");
        String realName = RequestUtil.getString(request, "realName");
        String email = RequestUtil.getString(request, "email");
        String phone = RequestUtil.getString(request, "phone");
        String address = RequestUtil.getString(request, "address");
        Integer roleID = RequestUtil.getInteger(request, "roleID",0);
        Integer curSalary = RequestUtil.getInteger(request, "curSalary",0);
        Integer baseSalary = RequestUtil.getInteger(request, "baseSalary",0);
        String groupName = RequestUtil.getString(request, "groupName");
        
        Account record = new Account();
        record.setUserName(username);
        record.setPassword(password);
        record.setRealName(realName);
        record.setEmail(email);
        record.setPhone(phone);
        record.setAddress(address);
        record.setRoleID(roleID);
        record.setState(1);
        record.setGroupName(groupName);
        record.setCurSalary(curSalary);
        record.setBaseSalary(baseSalary);
        record.setCreateTime(new Date());
        accountService.insert(record);
        
        Account record2=accountService.selectByExample(criteria).get(0);
        List<Integer>menuIds=new ArrayList<Integer>();
        if(roleID==1){//经理
        	menuIds.add(1);
        	menuIds.add(2);
        	menuIds.add(3);
        	menuIds.add(6);
        	menuIds.add(8);
        	menuIds.add(9);
        	menuIds.add(10);
        }else if(roleID==2){//业务员
        	menuIds.add(1);
	    	menuIds.add(3);
        	menuIds.add(6);
        	menuIds.add(9);
        	menuIds.add(10);
        }else if(roleID==3){//风控
        	menuIds.add(1);
	    	menuIds.add(3);
        	menuIds.add(9);
        	menuIds.add(10);
	    }else if(roleID==4){//跟单
	    	menuIds.add(1);
	    	menuIds.add(3);
	    	menuIds.add(9);
	    	menuIds.add(10);
	    }else if(roleID==5){
	    	menuIds.add(1);
	    	menuIds.add(3);
	    	menuIds.add(9);
	    	menuIds.add(10);
	    }
        accountMenuService.saveAccountMenus(record2, menuIds);
        
        
        Criteria controlCri=new Criteria();
        controlCri.put("roleID", 3);
        List<Account> controlmanList=accountService.selectByExample(controlCri);
        	StaticCache.controlmanCache.clear();
        for(Account a:controlmanList){
        	StaticCache.controlmanCache.add(a.getId());
        }
        Criteria documentaryCri=new Criteria();
        documentaryCri.put("roleID", 4);
        List<Account> documentaryList=accountService.selectByExample(documentaryCri);
        	StaticCache.documentaryCache.clear();
        	for(Account a:documentaryList){
        		StaticCache.documentaryCache.add(a.getId());
        	}
        	
        	accountService.updateSalary();
        return new ModelAndView(JSON_VIEW, JSON_ROOT, DwzJsonUtil.getOkStatusMsg("添加成功"));
    }

    /**
     * 更新
     * 
     * @param request
     * @return ModelAndView
     * @throws AdminException
     */
    @RequestMapping("/update.html")
    public ModelAndView update(HttpServletRequest request,
            HttpServletResponse response) throws AdminException {
        Integer id = RequestUtil.getInteger(request, "account_id",0);
        String password = RequestUtil.getString(request, "password");
        String realName = RequestUtil.getString(request, "realName");
        String email = RequestUtil.getString(request, "email");
        String phone = RequestUtil.getString(request, "phone");
        String address = RequestUtil.getString(request, "address");
        Integer roleID = RequestUtil.getInteger(request, "roleID",0);
        Integer state = RequestUtil.getInteger(request, "state",0);
        Integer curSalary = RequestUtil.getInteger(request, "curSalary",0);
        Integer baseSalary = RequestUtil.getInteger(request, "baseSalary",0);
        String groupName = RequestUtil.getString(request, "groupName");

        Account record = new Account();
        record.setId(id);
        if (StringUtils.isNotBlank(password)) {// 判断password是否为空
            record.setPassword(password);
        }
        if (StringUtils.isNotBlank(realName)) {// 判断realname是否为空
            record.setRealName(realName);
        }
        record.setEmail(email);
        record.setPhone(phone);
        record.setAddress(address);
        record.setRoleID(roleID);
        record.setState(state);
        record.setGroupName(groupName);
        record.setCurSalary(curSalary);
        record.setBaseSalary(baseSalary);
        accountService.updateByPrimaryKeySelective(record);
        accountService.updateSalary();
        return new ModelAndView(JSON_VIEW, JSON_ROOT, DwzJsonUtil.getOkStatusMsg("更新成功"));
    }

    @RequestMapping("/loginLog.html")
    public ModelAndView loginLog(HttpServletRequest request,
            HttpServletResponse response) throws AdminException 
    {
    	int listSize=LIST_PAGESIZE+3;
    	Integer pageNo = RequestUtil.getInteger(request, "pageNum",1);
    	Criteria criteria=new Criteria();
    	int totalCnt = loginLogService.countByExample(criteria);
        request.setAttribute("totalCnt", totalCnt);
        int offset = listSize * (pageNo - 1);
        criteria.setMysqlOffset(offset);
        criteria.setMysqlLength(listSize);
        criteria.setOrderByClause(" createTime desc ");
    	List<LoginLog> list=loginLogService.selectByExample(criteria);
    	request.setAttribute("list", list);
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", listSize);
        
        int lastPage=totalCnt/listSize+1;
        request.setAttribute("lastPage", lastPage);
    	return new ModelAndView("/pages/system/loginLog");
    } 
    
    /**
     * 选择修改密码
     * 
     * @param request
     * @return
     * @throws AdminException
     */
    @RequestMapping("/toModifyPassword.html")
    public ModelAndView toModifyPassword(HttpServletRequest request)
            throws AdminException {
        return new ModelAndView("/pages/system/password_edit");
    }
    
    
    /**
     * 修改密码
     * 
     * @param request
     * @return
     * @throws AdminException
     */
    @RequestMapping("/modifyPassword.html")
    public ModelAndView modifyPassword(HttpServletRequest request)throws AdminException {
    	Account user = (Account)request.getSession().getAttribute(SESSION_LOGIN_USER);
    	Integer id = user.getId() ;
    	  String password=RequestUtil.getString(request, "confirmPwd");
    	  Account record = new Account();
          record.setId(id);
          if (StringUtils.isNotBlank(password)) {// 判断password是否为空
              record.setPassword(password);
          }
          accountService.updateByPrimaryKeySelective(record);
    	return new ModelAndView(JSON_VIEW, JSON_ROOT, DwzJsonUtil.getOkStatusMsg("密码修改成功"));
    }
}
