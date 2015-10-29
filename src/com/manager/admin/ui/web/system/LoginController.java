package com.manager.admin.ui.web.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.manager.admin.common.Constants;
import com.manager.admin.common.StaticCache;
import com.manager.admin.common.exception.AdminException;
import com.manager.admin.common.service.AccountMenuService;
import com.manager.admin.common.service.AccountService;
import com.manager.admin.common.service.LoginLogService;
import com.manager.admin.common.service.MenuService;
import com.manager.admin.common.service.OrderInfoService;
import com.manager.admin.common.to.Account;
import com.manager.admin.common.to.CommonTo;
import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.LoginLog;
import com.manager.admin.common.to.Menu;
import com.manager.admin.common.utils.DwzJsonUtil;
import com.manager.admin.common.utils.RequestUtil;
import com.manager.admin.common.web.Funcs;

@Controller
public class LoginController implements Constants  {

    private static final String LOGIN_DIALOG = "/login_dialog";
    
    private static final String WELCOME = "/welcome";
    
    private static final String INDEX = "/index";
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMenuService accountMenuService;
    
    @Autowired
    private MenuService menuService;
    
    @Autowired
    private LoginLogService loginLogService;
    
    @Autowired
    private OrderInfoService orderInfoService;
    

	@RequestMapping("/index.html")
    public String index(HttpServletRequest request) throws AdminException {
        Account user = Funcs.getSessionLoginUser(request.getSession());
        int roleID=user.getRoleID();
        List<Menu> menus = null;
        menus = accountMenuService.getMenuListByAccountId(user.getId());
        
        Criteria chaoShicri= new Criteria();
        if(roleID==1){
        	chaoShicri.put("waitToDoManager", 1);
        }
        if(roleID==2){
        	//业务员
        	chaoShicri.put("salesman", user.getId());
        	chaoShicri.put("data_buwanzheng", 1);
        }
        if(roleID==3){
        	//风控员
        	chaoShicri.put("waitToDoControlman", 1);
        	chaoShicri.put("controlman", user.getId());
        }
        if(roleID==4){
        	//跟单员
        	chaoShicri.put("documentary", user.getId());
        	chaoShicri.put("waitToDoGenDan", 1);
        }
        int waitToDo=orderInfoService.countByExample(chaoShicri);
        chaoShicri.put("data_chaoshi", 1);
        int chaoShiNum=orderInfoService.countByExample(chaoShicri);
        request.setAttribute("waitToDo", waitToDo);
        request.setAttribute("chaoShiNum", chaoShiNum);
        Criteria controlCri=new Criteria();
        controlCri.put("roleID", 3);
        List<Account> controlmanList=accountService.selectByExample(controlCri);
        if(StaticCache.controlmanCache.size() != controlmanList.size()){
        	System.out.println("controlmanCache.size() :"+StaticCache.controlmanCache.size() +"controlmanList.size():"+controlmanList.size());
        	StaticCache.controlmanCache.clear();
        for(Account a:controlmanList){
        	StaticCache.controlmanCache.add(a.getId());
        }
        }
        Criteria documentaryCri=new Criteria();
        documentaryCri.put("roleID", 4);
        List<Account> documentaryList=accountService.selectByExample(documentaryCri);
        if(StaticCache.documentaryCache.size() != documentaryList.size()){
        	System.out.println("documentaryCache.size() :"+StaticCache.documentaryCache.size() +"documentaryQueue.size():"+documentaryList.size());
        	StaticCache.documentaryCache.clear();
        	for(Account a:documentaryList){
        		StaticCache.documentaryCache.add(a.getId());
        	}
        }
        
        
        accountService.updateSalary();
        request.setAttribute("menus", menus);
        
        return INDEX;
    }
    
    
    @RequestMapping("/login.html")
    public String login(HttpServletRequest request) throws AdminException {
    
        return "/login";
    }

    
    @RequestMapping("/dologin.html")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws AdminException {
        try {
            doLogin(request, response);
        } catch (AdminException ex) {
            String errorMsg = ex.getMessage();
            if (RequestUtil.isAjax(request)) {
                return new ModelAndView(JSON_VIEW, JSON_ROOT, DwzJsonUtil.getErrorStatusMsg(errorMsg));
            } else {
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("loginErrInfo", errorMsg);
                
                String redirectUrl="login";
                
                return new ModelAndView(redirectUrl, "model", model);
            }
        }
        if (RequestUtil.isAjax(request)) {
            return new ModelAndView(JSON_VIEW, JSON_ROOT, DwzJsonUtil.getOkStatusMsg(null));
        } else {
            return new ModelAndView(new RedirectView(request.getContextPath() + "/index.html"), "model", null);
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws AdminException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String screenH = request.getParameter("screenH");
        String ip=RequestUtil.getUserIpAddr(request);
        if (null == username || username.trim().length() == 0
                || null == password || password.length() == 0) {
            throw new AdminException("请填写用户名和密码！");
        }
        
        Integer logFailNum=loginLogService.selectLogFailNumByIP(ip);
        if(logFailNum>10){
        	throw new AdminException("当日密码错误次数已达上限");
        }
        Criteria criteria = new Criteria();
        criteria.put("userName", username);
        criteria.put("password", password);
        criteria.put("state", 1);
        
        
    	String subIp = request.getHeader("Proxy-Client-IP");
        if (subIp == null || subIp.length() == 0)
        {
            subIp = request.getHeader("X-Forwarded-For");
        }
        
        LoginLog loginLog=new LoginLog();
        loginLog.setAccount(username);
        loginLog.setPassword(password);
        loginLog.setAction("登入");
        loginLog.setCreateTime(new Date());
        loginLog.setIp(ip);
//        if (ip.equals("127.0.0.1")||ip.equals("0:0:0:0:0:0:0:1")) {
//        	loginLog.setProvince("本地调试");
//            loginLog.setArea("本地调试");
//            loginLog.setNet("本地调试");
//		}else
//		{
//        List<Object> getByIp=loginLogService.getByIP(ip);
//        loginLog.setProvince(getByIp.get(0).toString());
//        loginLog.setArea(getByIp.get(1).toString());
//        loginLog.setNet(getByIp.get(2).toString());
//		}
        List<Account> accountList = accountService.selectByExample(criteria);
        
        if (accountList == null || accountList.size() == 0) {
        	loginLog.setContext("账号/密码错误");
        	loginLogService.insert(loginLog);
            throw new AdminException("账号/密码错误");
        }
        loginLog.setContext("登入成功");
        loginLogService.insert(loginLog);
        request.getSession().setAttribute(SESSION_LOGIN_USER, accountList.get(0));
    	
    	Account user = Funcs.getSessionLoginUser(request.getSession());
    	Criteria cri2 = new Criteria();
    	cri2.put("accountId", user.getId());
        
        request.getSession().setAttribute("SCREENH",screenH );
        
        
    }
    
    @RequestMapping("/login_dialog.html")
    public String loginDialog(HttpServletRequest request) {
        return LOGIN_DIALOG;
    }

    @RequestMapping("/logout.html")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String ip=RequestUtil.getSubIpAddr(request);
        Account user = Funcs.getSessionLoginUser(session);
        LoginLog loginLog=new LoginLog();
        loginLog.setAccount(user.getUserName());
        loginLog.setPassword(user.getPassword());
        loginLog.setAction("离开");
        loginLog.setCreateTime(new Date());
        loginLog.setIp(ip);
//        if (ip.equals("127.0.0.1")||ip.equals("0:0:0:0:0:0:0:1")) {
//        	loginLog.setProvince("本地调试");
//            loginLog.setArea("本地调试");
//            loginLog.setNet("本地调试");
//		}else{
//        List<Object> getByIp=loginLogService.getByIP(ip);
//        loginLog.setProvince(getByIp.get(0).toString());
//        loginLog.setArea(getByIp.get(1).toString());
//        loginLog.setNet(getByIp.get(2).toString());
//		}
        loginLogService.insert(loginLog);
        
        session.removeAttribute(SESSION_LOGIN_USER);
        session.removeAttribute("SCREENH");
        session.invalidate();
        
        return "/login";
    }
    
    @RequestMapping("/welcome.html")
    public String welcome() {
        return WELCOME;
    }
    
    @RequestMapping("/modifyPwd.html")
    public ModelAndView modifyPwd(HttpServletRequest request,
            HttpServletResponse response) throws AdminException 
    {
    	return new ModelAndView("/pages/system/password_edit");
    } 
    
    @RequestMapping("/modifyPassword.html")
    public ModelAndView modify(HttpServletRequest request) throws AdminException {
    	String oldPwd = request.getParameter("oldPwd");
    	String newPwd = request.getParameter("newPwd");
    	String confirmPwd = request.getParameter("confirmPwd");
    	Account user = (Account)request.getSession().getAttribute(SESSION_LOGIN_USER);
    	Integer id = user.getId() ;
    	Account a =  accountService.selectByPrimaryKey(id);
    	
    	if (!oldPwd.equals(a.getPassword())) {
            throw new AdminException("旧密码不正确！");
        }
        if(!newPwd.equals(confirmPwd))
        {
        	throw new AdminException("新密码和确认密码不一致！");
        }
    		a.setPassword(newPwd);
    		accountService.updateByPrimaryKeySelective(a);
        return new ModelAndView(JSON_VIEW, JSON_ROOT,DwzJsonUtil.getOkStatusMsgWithClose("修改成功"));
    }
    
}
