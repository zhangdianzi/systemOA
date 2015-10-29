package com.manager.admin.common.web;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manager.admin.common.Constants;
import com.manager.admin.common.service.AccountService;
import com.manager.admin.common.service.MenuService;
import com.manager.admin.common.to.Account;
import com.manager.admin.common.to.Menu;

@Component
public class Funcs implements Constants {

    private static MenuService menuService;
    private static AccountService accountService;
    
    public static Account getSessionLoginUser(HttpSession session) {
        if (null == session) {
            return null;
        }
        Account user = (Account) session.getAttribute(SESSION_LOGIN_USER);
        return user;
    }

    public static String formatDateTime(Date date, String parten) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(parten);
        return format.format(date);
    }
    public static String formatGameTime(Integer gameTime) {
		Integer hour=gameTime/3600;
		Integer minute=(gameTime%3600)/60;
		Integer second=gameTime%60;
		String resutlt="";
		if(hour>0){resutlt+=hour+"时";}
		if(minute>0){resutlt+=minute+"分";}
		if(second>0){resutlt+=second+"秒";}
    	return resutlt;
    }
    
    public static String divisionToDouble(Integer a,Integer b){
    	DecimalFormat df=new DecimalFormat("#.##");
    	if(b == null || b==0 ||a == null ){
    		return "0";
    	}
    
    	double c=(double)a/b;
    	return df.format(c);
    }
    
    //返回给定时间往后推n天的日期
    public static String getNextNumDate(Date date, Integer dayNum, String parten) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(parten);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, dayNum);
        return format.format(c.getTime());
    }

    //根据数字表示的年周返回特定周的日期
    public static String getDayOfWeekDate(Integer yearWeek, String parten, Integer dayOfWeek) 
    {
        if (0 == yearWeek) 
        {
            return "";
        }
        String tmp = String.valueOf(yearWeek);
        //获取年
        int year = Integer.valueOf(tmp.substring(0, 4));
        int week = Integer.valueOf(tmp.substring(4));
        Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		
        SimpleDateFormat format = new SimpleDateFormat(parten);
        return format.format(c.getTime());
    }
    
    public static String roundDouble(Double num, Integer r) {
        if (null == num) {
            return "";
        }
        BigDecimal b = new BigDecimal(num);
        num = b.setScale(r, BigDecimal.ROUND_HALF_UP).doubleValue();
        return num.toString();
    }

    public static boolean isContains(Object id, List<Object> ids) {
        if (ids == null || ids.size() == 0 || id == null) {
            return false;
        }
        if (ids.contains(id)) {
            return true;
        }
        return false;
    }


    public static String getMenuName(Integer menuId) {
        Menu m = menuService.getByIdInCache(menuId);
        if (m != null) {
            return m.getName();
        }
        return "";
    }

    public static String formatPrice(Double price){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price / 100);
    }
    
    public static String formatDoubleNumber(Double num, String fmtStr){
        DecimalFormat df = new DecimalFormat(fmtStr);
        return df.format(num);
    }
    
    public static Account selecUserByAccountId(Integer accountId){
    	if(accountId==null)
    		return new Account();
    	if(accountId==-1){
    		Account account1=new Account();
    		account1.setRealName("全体同事");
    		return account1;
    	}
    	Account account=accountService.selectByPrimaryKey(accountId);
    	if(account==null)
    		return new Account();
    	return account;
    }
    
    public static Long getServerBeginDate(Date date) {
    	long time1=date.getTime();  
        long time2=new Date().getTime()-3600*1000*24;  
        long diff=Math.abs(time2-time1);  
        diff /= 3600 * 1000 * 24;
        return diff;
    }
    
    
    public static String getNameByState(Integer state){
    	if(state==null){
    		return "未知";
    	}
    	String result = "";
    	switch(state){
    	case 1:{result="申请成功";break;}
    	case 2:{result="申请失败";break;}
    	case 9:{result="收集材料(录入)";break;}
    	case 10:{result="收集材料(提交)";break;}
    	case 11:{result="收集材料(审核)";break;}
    	case 12:{result="收集材料(通过)";break;}
    	case 20:{result="业务审批(提交)";break;}
    	case 21:{result="业务审批(审核)";break;}
    	case 22:{result="业务审批(通过)";break;}
    	case 30:{result="尽职审批(提交)";break;}
    	case 31:{result="尽职审批(审核)";break;}
    	case 32:{result="尽职审批(通过)";break;}
    	case 40:{result="风控审批(提交)";break;}
    	case 41:{result="风控审批(审核)";break;}
    	case 42:{result="风控审批(通过)";break;}
    	case 50:{result="放款审批(提交)";break;}
    	case 51:{result="放款审批(审核)";break;}
    	case 52:{result="放款审批(通过)";break;}
    	}
    	return result;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        Funcs.menuService = menuService;
    }
    @Autowired
    public void setAccountService(AccountService accountService) {
    	Funcs.accountService = accountService;
    }

    
    
}
