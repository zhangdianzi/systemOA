package com.manager.admin.ui.web.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.manager.admin.common.Constants;
import com.manager.admin.common.StaticCache;
import com.manager.admin.common.exception.AdminException;
import com.manager.admin.common.service.AccountService;
import com.manager.admin.common.service.OrderInfoService;
import com.manager.admin.common.to.Account;
import com.manager.admin.common.to.Criteria;
import com.manager.admin.common.to.OrderInfo;
import com.manager.admin.common.utils.DateUtil;
import com.manager.admin.common.utils.DwzJsonUtil;
import com.manager.admin.common.utils.RequestUtil;
import com.manager.admin.common.web.Funcs;

@Controller
@RequestMapping("/system/orderInfo")
public class OrderInfoController implements Constants {

    public static final int listPageSize = 20;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderInfoService orderInfoService;
    
/**
 * 
 {  "statusCode":"200",  "message":"\u64cd\u4f5c\u6210\u529f",//操作成功  "navTabId":"info",//客户信息管理链接中的rel="info"  "rel":"",  "callbackType":"closeCurrent",//关闭当前页面  "forwardUrl":"",  "confirmMsg":"" }
 */
    
    @RequestMapping("/toAdd.html")
    public ModelAndView toAdd(HttpServletRequest request)throws AdminException {
    	long order_id = RequestUtil.getLong(request, "order_id",-1);
    	OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(order_id);
    	request.setAttribute("orderInfo", orderInfo);
        return new ModelAndView("/pages/system/orderInfo_add");
    }
    @RequestMapping("/toDetail.html")
    public ModelAndView toDetail(HttpServletRequest request)throws AdminException {
    	long order_id = RequestUtil.getLong(request, "order_id",-1);
    	OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(order_id);
    	request.setAttribute("orderInfo", orderInfo);
    	return new ModelAndView("/pages/system/orderInfo_detail");
    }
    @RequestMapping("/toControl.html")
    public ModelAndView toControl(HttpServletRequest request)throws AdminException {
    	long order_id = RequestUtil.getLong(request, "order_id",-1);
    	OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(order_id);
    	if(orderInfo.getData_state()==10){
    		request.setAttribute("submit_note", orderInfo.getSubmit_note1());
    	}
    	if(orderInfo.getData_state()==20){
    		request.setAttribute("submit_note", orderInfo.getSubmit_note2());
    	}
    	if(orderInfo.getData_state()==30){
    		request.setAttribute("submit_note", orderInfo.getSubmit_note3());
    	}
    	if(orderInfo.getData_state()==40){
    		request.setAttribute("submit_note", orderInfo.getSubmit_note4());
    	}
    	if(orderInfo.getData_state()==50){
    		request.setAttribute("submit_note", orderInfo.getSubmit_note5());
    	}
    	request.setAttribute("orderInfo", orderInfo);
    	return new ModelAndView("/pages/system/orderInfo_control");
    }
    @RequestMapping("/toIssue.html")
    public ModelAndView toIssue(HttpServletRequest request)throws AdminException {
    	long order_id = RequestUtil.getLong(request, "order_id",-1);
    	OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(order_id);
    	if(orderInfo.getData_state()==11){
    		request.setAttribute("submit_note", orderInfo.getSubmit_note1());
    		request.setAttribute("controlman_note", orderInfo.getControlman_note1());
    	}
    	if(orderInfo.getData_state()==21){
    		request.setAttribute("submit_note", orderInfo.getSubmit_note2());
    		request.setAttribute("controlman_note", orderInfo.getControlman_note2());
    	}
    	if(orderInfo.getData_state()==31){
    		request.setAttribute("submit_note", orderInfo.getSubmit_note3());
    		request.setAttribute("controlman_note", orderInfo.getControlman_note3());
    	}
    	if(orderInfo.getData_state()==41){
    		request.setAttribute("submit_note", orderInfo.getSubmit_note4());
    		request.setAttribute("controlman_note", orderInfo.getControlman_note4());
    	}
    	if(orderInfo.getData_state()==51){
    		request.setAttribute("submit_note", orderInfo.getSubmit_note5());
    		request.setAttribute("controlman_note", orderInfo.getControlman_note5());
    	}
    	request.setAttribute("orderInfo", orderInfo);
    	return new ModelAndView("/pages/system/orderInfo_issue");
    }
    @RequestMapping("/toGendan.html")
    public ModelAndView toGendan(HttpServletRequest request)throws AdminException {
    	long order_id = RequestUtil.getLong(request, "order_id",-1);
    	OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(order_id);
    	request.setAttribute("orderInfo", orderInfo);
    	return new ModelAndView("/pages/system/orderInfo_gendan");
    }
    
    @RequestMapping("/toUpdateState.html")
    public ModelAndView toUpdateState(HttpServletRequest request)throws AdminException {
    	long order_id = RequestUtil.getLong(request, "order_id",-1);
    	OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(order_id);
    	request.setAttribute("orderInfo", orderInfo);
    	return new ModelAndView("/pages/system/orderInfo_updateState");
    }
    @RequestMapping("/updateState.html")
    public ModelAndView updateState(HttpServletRequest request)throws AdminException {
    	long order_id = RequestUtil.getLong(request, "order_id",-1);
    	int data_state=RequestUtil.getInteger(request, "data_state", -1);
    	OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(order_id);
    	orderInfo.setData_state(data_state);
    	orderInfoService.updateByPrimaryKey(orderInfo);
    	Map<String, String> res = new HashMap<String, String>();
    	res.put("statusCode", "200");
        return new ModelAndView(JSON_VIEW, JSON_ROOT, res);
    }
    
    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws AdminException {
    	OrderInfo orderInfo=new OrderInfo();
    	Map<String, String> res = new HashMap<String, String>();
    	Account user = Funcs.getSessionLoginUser(request.getSession());
    	
    	
    	
    	long order_id = RequestUtil.getLong(request, "order_id",-1);
    	 String data_name = RequestUtil.getString(request, "data_name");
    	 String data_sfz = RequestUtil.getString(request, "data_sfz");
    	 String data_gzdw = RequestUtil.getString(request, "data_gzdw");
    	 String data_jtdz = RequestUtil.getString(request, "data_jtdz");
    	 String data_lxfs = RequestUtil.getString(request, "data_lxfs");
    	 int data_ywnr = RequestUtil.getInteger(request, "data_ywnr",0);
    	 int data_gfje = RequestUtil.getInteger(request, "data_gfje",0);
    	 int data_sqje = RequestUtil.getInteger(request, "data_sqje",0);
    	 int data_fkje = RequestUtil.getInteger(request, "data_fkje",0);
    	 String data_gfxx = RequestUtil.getString(request, "data_gfxx");
    	 int data_zxbg = RequestUtil.getInteger(request, "data_zxbg",0);
    	 int data_jhz = RequestUtil.getInteger(request, "data_jhz",0);
    	 int data_hkb = RequestUtil.getInteger(request, "data_hkb",0);
    	 int data_zczm = RequestUtil.getInteger(request, "data_zczm",0);
    	 int data_yhls = RequestUtil.getInteger(request, "data_yhls",0);
    	 int data_gfht = RequestUtil.getInteger(request, "data_gfht",0);
    	 int data_djsj = RequestUtil.getInteger(request, "data_djsj",0);
    	 int data_fcz = RequestUtil.getInteger(request, "data_fcz",0);
    	 String salesman_note = RequestUtil.getString(request, "salesman_note");
    	 
    	 String data_bgdh = RequestUtil.getString(request, "data_bgdh");
    	 String partner_name = RequestUtil.getString(request, "partner_name");
    	 String partner_sfz = RequestUtil.getString(request, "partner_sfz");
    	 String partner_gzdw = RequestUtil.getString(request, "partner_gzdw");
    	 String partner_lxfs = RequestUtil.getString(request, "partner_lxfs");
    	 String partner_jtdz = RequestUtil.getString(request, "partner_jtdz");
    	 int data_srzm = RequestUtil.getInteger(request, "data_srzm",0);
    	 if(order_id != -1){
    		 orderInfo=orderInfoService.selectByPrimaryKey(order_id);
    	 }else{
    		 orderInfo.setData_state(9);
    	 }
    	 
    	 orderInfo.setData_bgdh(data_bgdh);
    	 orderInfo.setPartner_gzdw(partner_gzdw);
    	 orderInfo.setPartner_jtdz(partner_jtdz);
    	 orderInfo.setPartner_lxfs(partner_lxfs);
    	 orderInfo.setPartner_name(partner_name);
    	 orderInfo.setPartner_sfz(partner_sfz);
    	 orderInfo.setData_srzm(data_srzm);
    	 orderInfo.setSubmit_note1(salesman_note);
    	 orderInfo.setUpdateTime(new Date());
    	 orderInfo.setData_djsj(data_djsj);
    	 orderInfo.setData_fcz(data_fcz);
    	 orderInfo.setData_gfht(data_gfht);
    	 orderInfo.setData_ywnr(data_ywnr);
    	 orderInfo.setData_gfje(data_gfje);
    	 orderInfo.setData_gzdw(data_gzdw);
    	 orderInfo.setData_hkb(data_hkb);
    	 orderInfo.setData_jhz(data_jhz);
    	 orderInfo.setData_jtdz(data_jtdz);
    	 orderInfo.setData_lxfs(data_lxfs);
    	 orderInfo.setData_name(data_name);
    	 orderInfo.setData_sfz(data_sfz);
    	 orderInfo.setData_sqje(data_sqje);
    	 orderInfo.setData_yhls(data_yhls);
    	 orderInfo.setData_zczm(data_zczm);
    	 orderInfo.setData_zxbg(data_zxbg);
    	 orderInfo.setSalesman(user.getId());
    	 orderInfo.setData_gfxx(data_gfxx);
    	 orderInfo.setData_fkje(data_fkje);
    	 
     	if(order_id != -1){
     		res.put("navTabId", "navtab_menu_9");
     		res.put("callbackType", "closeCurrent");
     		orderInfo.setId(order_id);
     		orderInfoService.updateByPrimaryKey(orderInfo);
     		res.put("message", "修改成功");
     	}else{
     		orderInfo.setCreateTime(new Date());
     		res.put("navTabId", "navtab_menu_6");
     		orderInfoService.insert(orderInfo);
     		res.put("message", "添加成功");
     	}
    	 
    	 
 		res.put("statusCode", "200");
        return new ModelAndView(JSON_VIEW, JSON_ROOT, res);
    }
    
    
    
    @RequestMapping("/list.html")
    public ModelAndView list(HttpServletRequest request) throws AdminException {
    	HttpSession session = request.getSession();
    	String screenH=(String) session.getAttribute("SCREENH");
    	int listSize=(Integer.parseInt(screenH)-170)/26-2 ;
    	 int waittodoFlag = RequestUtil.getInteger(request, "waittodoFlag",0);
    	 String data_name = RequestUtil.getString(request, "data_name");
         String data_sfz = RequestUtil.getString(request, "data_sfz");
         Integer data_state = RequestUtil.getInteger(request, "data_state",-1);
        Integer pageNo = RequestUtil.getInteger(request, "pageNum",1);
        Date startTime = RequestUtil.getDate(request, "startTime", "yyyy-MM-dd");
        Date endTime = RequestUtil.getDate(request, "endTime", "yyyy-MM-dd");
        Criteria criteria = new Criteria();
        Criteria waitToDocriteria = new Criteria();
       
        criteria.put("startTime", startTime);
        criteria.put("endTime", endTime);
        waitToDocriteria.put("startTime", startTime);
        waitToDocriteria.put("endTime", endTime);
        if (StringUtils.isNotBlank(data_name)) {
            criteria.put("data_name", data_name);
            waitToDocriteria.put("data_name", data_name);
        }
        if (StringUtils.isNotBlank(data_sfz)) {
            criteria.put("data_sfz", data_sfz);
            waitToDocriteria.put("data_sfz", data_sfz);
        }
        if (data_state != -1) {
        	criteria.put("data_state", data_state);
        	waitToDocriteria.put("data_state", data_state);
		}
        Account user = Funcs.getSessionLoginUser(request.getSession());
        Integer roleID=user.getRoleID(); 
        
        if(roleID==1){
        	waitToDocriteria.put("waitToDoManager", 1);
        	if(waittodoFlag==1){
        		criteria.put("waitToDoManager", 1);
        	}
        }
        if(roleID==2){
        	waitToDocriteria.put("data_buwanzheng", 0);
        	waitToDocriteria.put("salesman", user.getId());
        	criteria.put("salesman", user.getId());
        	if(waittodoFlag==1){
        		criteria.put("data_buwanzheng", 0);
        	}
        }
        if(roleID==3){
        	waitToDocriteria.put("waitToDoControlman", 1);
        	waitToDocriteria.put("controlman", user.getId());
        	criteria.put("controlman", user.getId());
        	if(waittodoFlag==1){
        		criteria.put("waitToDoControlman", 1);
        	}
        }
        if(roleID==4){
        	waitToDocriteria.put("waitToDoGenDan", 1);
        	criteria.put("documentary", user.getId());
        	waitToDocriteria.put("documentary", user.getId());
        	if(waittodoFlag==1){
        		criteria.put("waitToDoGenDan", 1);
        	}
        }
        if(roleID==5){
        	criteria.put("groupName", user.getGroupName());
        }
        
        int totalCnt=orderInfoService.countByExample(criteria);
        
        int offset = listSize * (pageNo - 1);
        criteria.setMysqlOffset(offset);
        criteria.setMysqlLength(listSize);
        criteria.setOrderByClause(" id desc ");
        
        int waitToDo=orderInfoService.countByExample(waitToDocriteria);
        List<OrderInfo> list = orderInfoService.selectByExample(criteria);
        
        request.setAttribute("startTime", startTime);
        request.setAttribute("endTime", endTime);
        request.setAttribute("waittodoFlag", waittodoFlag);
        request.setAttribute("waitToDo", waitToDo);
        request.setAttribute("data_name", data_name);
        request.setAttribute("data_sfz", data_sfz);
        request.setAttribute("data_state", data_state);
        request.setAttribute("list", list);
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", listSize);
        request.setAttribute("totalCnt", totalCnt);
        return new ModelAndView("/pages/system/orderInfo_list");
        
    }
    
    @RequestMapping("/control")
    public ModelAndView control(HttpServletRequest request) throws AdminException {
    	Map<String, String> res = new HashMap<String, String>();
    	Account user = Funcs.getSessionLoginUser(request.getSession());
    	if(user.getRoleID()!=3){
    		res.put("statusCode", "300");
     		res.put("message", "对不起，您无权操作");
            return new ModelAndView(JSON_VIEW, JSON_ROOT, res);
    	}
    	
    	long order_id = RequestUtil.getLong(request, "order_id",-1);
    	long merge = RequestUtil.getLong(request, "merge",-1);
    	String controlman_note=RequestUtil.getString(request, "controlman_note");
    	OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(order_id);
    	if(merge==1){
    		if(orderInfo.getData_state()==10){
    			orderInfo.setData_state(11);//收集资料审核ok
    			orderInfo.setControlman_note1(controlman_note);
    		}
    		if(orderInfo.getData_state()==20){
    			orderInfo.setData_state(21);//银行业务审批(审核)ok
    			orderInfo.setControlman_note2(controlman_note);
    		}
    		if(orderInfo.getData_state()==30){
    			orderInfo.setData_state(31);//银行业务审批(审核)ok
    			orderInfo.setControlman_note3(controlman_note);
    		}
    		if(orderInfo.getData_state()==40){
    			orderInfo.setData_state(41);//银行业务审批(审核)ok
    			orderInfo.setControlman_note4(controlman_note);
    		}
    		if(orderInfo.getData_state()==50){
    			orderInfo.setData_state(51);//银行业务审批(审核)ok
    			orderInfo.setControlman_note5(controlman_note);
    		}
    	}
    	else if(merge ==0){
    		orderInfo.setData_state(2);//失败
    		orderInfo.setFail_note(controlman_note);;//失败备注
    	}
    	
    	
    	orderInfo.setUpdateTime(new Date());
    	orderInfoService.updateByPrimaryKey(orderInfo);
    	res.put("statusCode", "200");
 		res.put("message", "操作成功");
 		res.put("callbackType", "closeCurrent");
    	res.put("navTabId", "navtab_menu_9");
        return new ModelAndView(JSON_VIEW, JSON_ROOT, res);
    }
    @RequestMapping("/issue")
    public ModelAndView issue(HttpServletRequest request) throws AdminException {
    	Map<String, String> res = new HashMap<String, String>();
    	Account user = Funcs.getSessionLoginUser(request.getSession());
    	if(user.getRoleID()!=1){
    		res.put("statusCode", "300");
    		res.put("message", "对不起，您无权操作");
    		return new ModelAndView(JSON_VIEW, JSON_ROOT, res);
    	}
    	
    	long order_id = RequestUtil.getLong(request, "order_id",-1);
    	long merge = RequestUtil.getLong(request, "merge",-1);
    	String manager_note=RequestUtil.getString(request, "manager_note");
    	OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(order_id);
    	orderInfo.setUpdateTime(new Date());
    	if(merge==1){
    		if(orderInfo.getData_state()==11){
    			orderInfo.setData_state(12);//收集资料通过ok
    			orderInfo.setManager_note1(manager_note);
    			orderInfo.setDocumentary(StaticCache.documentaryCache.get(StaticCache.documentaryFlag++%StaticCache.documentaryCache.size()));
    		}
    		if(orderInfo.getData_state()==21){
    			orderInfo.setData_state(22);//收集资料通过ok
    			orderInfo.setManager_note2(manager_note);
    		}
    		if(orderInfo.getData_state()==31){
    			orderInfo.setData_state(32);//收集资料通过ok
    			orderInfo.setManager_note3(manager_note);
    		}
    		if(orderInfo.getData_state()==41){
    			orderInfo.setData_state(42);//收集资料通过ok
    			orderInfo.setManager_note4(manager_note);
    		}
    		if(orderInfo.getData_state()==51){
    			orderInfo.setData_state(52);//收集资料通过ok
    			orderInfo.setManager_note5(manager_note);
    		}
    	}
    	else if(merge ==0){
    		orderInfo.setData_state(2);
    		orderInfo.setFail_note(manager_note);
    	}
    	orderInfo.setManager(user.getId());
    	orderInfoService.updateByPrimaryKey(orderInfo);
    	res.put("statusCode", "200");
    	res.put("message", "操作成功");
    	res.put("callbackType", "closeCurrent");
    	res.put("navTabId", "navtab_menu_9");
    	return new ModelAndView(JSON_VIEW, JSON_ROOT, res);
    }
    @RequestMapping("/gendan")
    public ModelAndView gendan(HttpServletRequest request) throws AdminException {
    	Map<String, String> res = new HashMap<String, String>();
    	Account user = Funcs.getSessionLoginUser(request.getSession());
    	if(user.getRoleID()!=4){
    		res.put("statusCode", "300");
    		res.put("message", "对不起，您无权操作");
    		return new ModelAndView(JSON_VIEW, JSON_ROOT, res);
    	}
    	
    	long order_id = RequestUtil.getLong(request, "order_id",-1);
    	int data_fkje=RequestUtil.getInteger(request, "data_fkje",-1);
    	String submit_note=RequestUtil.getString(request, "submit_note");
    	OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(order_id);
    	orderInfo.setUpdateTime(new Date());
    	
    	if(orderInfo.getData_state()==12){
			orderInfo.setData_state(20);
			orderInfo.setSubmit_note2(submit_note);
		}
    	if(orderInfo.getData_state()==22){
    		orderInfo.setData_state(30);
    		orderInfo.setSubmit_note3(submit_note);
    	}
    	if(orderInfo.getData_state()==32){
    		orderInfo.setData_state(40);
    		orderInfo.setSubmit_note4(submit_note);
    	}
    	if(orderInfo.getData_state()==42){
    		orderInfo.setData_state(50);
    		orderInfo.setSubmit_note5(submit_note);
    	}
    	if(orderInfo.getData_state()==52){
    		orderInfo.setData_fkje(data_fkje);
    		orderInfo.setData_state(1);
    	}
    	orderInfoService.updateByPrimaryKey(orderInfo);
    	res.put("statusCode", "200");
    	res.put("message", "操作成功");
    	res.put("callbackType", "closeCurrent");
    	res.put("navTabId", "navtab_menu_9");
    	return new ModelAndView(JSON_VIEW, JSON_ROOT, res);
    }
    
    @RequestMapping("/redo.html")
    public ModelAndView redo(HttpServletRequest request) throws AdminException {
        long order_id = RequestUtil.getLong(request, "order_id",-1);
    	OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(order_id);
    	orderInfo.setControlman(StaticCache.controlmanCache.get(StaticCache.controlmanFlag++%StaticCache.controlmanCache.size()));
    	orderInfo.setControlman_note1(null);
    	orderInfo.setControlman_note2(null);
    	orderInfo.setControlman_note3(null);
    	orderInfo.setControlman_note4(null);
    	orderInfo.setControlman_note5(null);
    	orderInfo.setDocumentary(null);
    	orderInfo.setSubmit_note2(null);
    	orderInfo.setSubmit_note3(null);
    	orderInfo.setSubmit_note4(null);
    	orderInfo.setSubmit_note5(null);
    	orderInfo.setFail_note(null);
    	orderInfo.setUpdateTime(new Date());
    	orderInfo.setData_state(9);
    	orderInfoService.updateByPrimaryKey(orderInfo);
        return new ModelAndView(JSON_VIEW, JSON_ROOT,DwzJsonUtil.getOkStatusMsg("已打回"));
    }
    
    
	 @RequestMapping("/exportExcel.html")
	 public ModelAndView exportExcel(HttpServletRequest request) throws AdminException {
		 Date startTime = RequestUtil.getDate(request, "startTime", "yyyy-MM-dd");
		 Date endTime = RequestUtil.getDate(request, "endTime", "yyyy-MM-dd");
    	 String data_name = RequestUtil.getString(request, "data_name");
         String data_sfz = RequestUtil.getString(request, "data_sfz");
         Integer data_state = RequestUtil.getInteger(request, "data_state",-1);
         int waittodoFlag = RequestUtil.getInteger(request, "waittodoFlag",0);
         Criteria criteria = new Criteria();
		 criteria.put("startTime", startTime);
		 criteria.put("endTime", endTime);
		 
		 if(waittodoFlag==1){
     		criteria.put("waitToDoManager", 1);
     	}
        if (StringUtils.isNotBlank(data_name)) {
            criteria.put("data_name", data_name);
        }
        if (StringUtils.isNotBlank(data_sfz)) {
            criteria.put("data_sfz", data_sfz);
        }
        if (data_state != -1) {
        	criteria.put("data_state", data_state);
		}
        Account user = Funcs.getSessionLoginUser(request.getSession());
        if(user.getRoleID()==5){
        	criteria.put("groupName", user.getGroupName());
        }
        List<OrderInfo> list = orderInfoService.selectByExample(criteria);
		 OrderInfo orderInfo=null;
    	int rowCount=list.size()+1;
    	Map<String,Object> res=new HashMap<String,Object>();
    	res.put(EXCEL_FILE_NAME, new SimpleDateFormat("yyyyMMdd").format(new Date()));
        res.put(EXCEL_SHEET_NAME, "数据详情");
        String[][] content=new String[rowCount][50];
        content[0][0]="录入日期";
        content[0][1]="更新日期";
        content[0][2]="客户名";
        content[0][3]="身份证";
        content[0][4]="联系方式";
        content[0][5]="业务内容";
        content[0][6]="家庭地址";
        content[0][7]="工作单位";
        content[0][8]="办公电话";
        content[0][9]="配偶名";
        content[0][10]="配偶联系方式";
        content[0][11]="配偶身份证";
        content[0][12]="配偶工作单位";
        content[0][13]="配偶家庭地址";
        content[0][14]="购房金额";
        content[0][15]="申请金额";
        content[0][16]="放款金额";
        content[0][17]="购房信息";
        content[0][18]="征信报告";
        content[0][19]="结婚证/离婚证";
        content[0][20]="户口簿";
        content[0][21]="资产证明";
        content[0][22]="银行流水";
        content[0][23]="购房合同";
        content[0][24]="订金收据";
        content[0][25]="卖放房产证";
        content[0][26]="收入证明";
        content[0][27]="录单员";
        content[0][28]="风控员";
        content[0][29]="经理";
        content[0][30]="跟单员";
        content[0][31]="状态";
        content[0][32]="收集材料(录入)备注";
        content[0][33]="收集材料(审核)备注";
        content[0][34]="收集材料(通过)备注";
        content[0][35]="业务审批(提交)备注";
        content[0][36]="业务审批(审核)备注";
        content[0][37]="业务审批(通过)备注";
        content[0][38]="尽职审批(提交)备注";
        content[0][39]="尽职审批(审核)备注";
        content[0][40]="尽职审批(通过)备注";
        content[0][41]="风控审批(提交)备注";
        content[0][42]="风控审批(审核)备注";
        content[0][43]="风控审批(通过)备注";
        content[0][44]="放款审批(提交)备注";
        content[0][45]="放款审批(审核)备注";
        content[0][46]="放款审批(通过)备注";
        content[0][47]="失败备注";
         for(int i=1;i<rowCount;i++){
        	 orderInfo=list.get(i-1);
        	 content[i][0]=DateUtil.formatDate(orderInfo.getCreateTime(), "yyyy-MM-dd");
        	 content[i][1]=DateUtil.formatDate(orderInfo.getUpdateTime(), "yyyy-MM-dd");
        	 content[i][2]=orderInfo.getData_name();
        	 content[i][3]=orderInfo.getData_sfz();
        	 content[i][4]=orderInfo.getData_lxfs();
        	 content[i][5]=orderInfo.getData_ywnr()==1?"融资":orderInfo.getData_ywnr()==2?"0首付购房":"0首付购车";
        	 content[i][6]=orderInfo.getData_jtdz();
        	 content[i][7]=orderInfo.getData_gzdw();
        	 content[i][8]=orderInfo.getData_bgdh();
        	 content[i][9]=orderInfo.getPartner_name();
        	 content[i][10]=orderInfo.getPartner_lxfs();
        	 content[i][11]=orderInfo.getPartner_sfz();
        	 content[i][12]=orderInfo.getPartner_gzdw();
        	 content[i][13]=orderInfo.getPartner_jtdz();
        	 content[i][14]=orderInfo.getData_gfje()==null?"":orderInfo.getData_gfje()+"";
        	 content[i][15]=orderInfo.getData_sqje()==null?"":orderInfo.getData_sqje()+"";
        	 content[i][16]=orderInfo.getData_fkje()==null?"":orderInfo.getData_fkje()+"";
        	 content[i][17]=orderInfo.getData_gfxx()==null?"":orderInfo.getData_gfxx();
        	 content[i][18]=orderInfo.getData_zxbg()==1?"提供":"未提供";
        	 content[i][19]=orderInfo.getData_jhz()==1?"提供":"未提供";
        	 content[i][20]=orderInfo.getData_hkb()==1?"提供":"未提供";
        	 content[i][21]=orderInfo.getData_zczm()==1?"提供":"未提供";
        	 content[i][22]=orderInfo.getData_yhls()==1?"提供":"未提供";
        	 content[i][23]=orderInfo.getData_gfht()==1?"提供":"未提供";
        	 content[i][24]=orderInfo.getData_djsj()==1?"提供":"未提供";
        	 content[i][25]=orderInfo.getData_fcz()==1?"提供":"未提供";
        	 content[i][26]=orderInfo.getData_srzm()==1?"提供":"未提供";
        	 content[i][27]=Funcs.selecUserByAccountId(orderInfo.getSalesman()).getRealName();
        	 content[i][28]=Funcs.selecUserByAccountId(orderInfo.getControlman()).getRealName();
        	 content[i][29]=Funcs.selecUserByAccountId(orderInfo.getManager()).getRealName();
        	 content[i][30]=Funcs.selecUserByAccountId(orderInfo.getDocumentary()).getRealName();
        	 content[i][31]=Funcs.getNameByState(orderInfo.getData_state());
        	 content[i][32]=orderInfo.getSubmit_note1();
        	 content[i][33]=orderInfo.getControlman_note1();
        	 content[i][34]=orderInfo.getManager_note1();
        	 content[i][35]=orderInfo.getSubmit_note2();
        	 content[i][36]=orderInfo.getControlman_note2();
        	 content[i][37]=orderInfo.getManager_note2();
        	 content[i][38]=orderInfo.getSubmit_note3();
        	 content[i][39]=orderInfo.getControlman_note3();
        	 content[i][40]=orderInfo.getManager_note3();
        	 content[i][41]=orderInfo.getSubmit_note4();
        	 content[i][42]=orderInfo.getControlman_note4();
        	 content[i][43]=orderInfo.getManager_note4();
        	 content[i][44]=orderInfo.getSubmit_note5();
        	 content[i][45]=orderInfo.getControlman_note5();
        	 content[i][46]=orderInfo.getManager_note5();
        	 content[i][47]=orderInfo.getFail_note();
         }
        res.put("EXCEL_CONTENT_TITLE", EXCEL_CONTENT_TITLE_ROW);
 		res.put(EXCEL_CONTENT, content);
       
 		return new ModelAndView(EXCEL_VIEW, res);
	 }
	 
	    @RequestMapping("/del.html")
	    public ModelAndView del(HttpServletRequest request) throws AdminException {
	        long orderId = RequestUtil.getInteger(request, "order_id",0);
	        orderInfoService.deleteByPrimaryKey(orderId);
	        return new ModelAndView(JSON_VIEW, JSON_ROOT,DwzJsonUtil.getOkStatusMsg("删除成功"));
	    }
	    @RequestMapping("/genjin.html")
	    public ModelAndView genjin(HttpServletRequest request) throws AdminException {
	    	long order_id = RequestUtil.getLong(request, "order_id",-1);
	    	OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(order_id);
	    	orderInfo.setUpdateTime(new Date());
	    	orderInfoService.updateByPrimaryKey(orderInfo);
	    	return new ModelAndView(JSON_VIEW, JSON_ROOT,DwzJsonUtil.getOkStatusMsg("跟进成功"));
	    }
	    @RequestMapping("/tijiao.html")
	    public ModelAndView tijiao(HttpServletRequest request) throws AdminException {
	    	long order_id = RequestUtil.getLong(request, "order_id",-1);
	    	OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(order_id);
	    	orderInfo.setUpdateTime(new Date());
	    	orderInfo.setData_state(10);
	    	orderInfo.setControlman(StaticCache.controlmanCache.get(StaticCache.controlmanFlag++%StaticCache.controlmanCache.size()));
	    	System.out.println("orderInfo.getControlman():"+orderInfo.getControlman());
	    	orderInfoService.updateByPrimaryKey(orderInfo);
	    	return new ModelAndView(JSON_VIEW, JSON_ROOT,DwzJsonUtil.getOkStatusMsg("提交成功"));
	    }
}
