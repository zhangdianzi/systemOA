package com.manager.admin.common;

import com.manager.admin.common.web.CsvView;
import com.manager.admin.common.web.ExcelView;
import com.manager.admin.common.web.JSONView;

public interface Constants
{

	String SESSION_LOGIN_USER = "SESSION_USER";
	
	String SESSION_MENUS = "SESSION_MENUS";
	
	String SESSION_ACCOUNTAPPID = "SESSION_ACCOUNTAPPID";
	
	String SESSION_CHANNEL = "SESSION_CHANNEL";
	
	String SESSION_ANNOUNCEMENT = "SESSION_ANNOUNCEMENT";

	int PAGE_SIZE = 25;

	String CSV_CONTENT = "CSV_CONTENT";

	String CSV_FILE_NAME = "CSV_FILE_NAME";

	String CSV_CONTENT_TYPE = "text/csv";

	CsvView CSV_VIEW = new CsvView();
	
	String EXCEL_FILE_NAME = "EXCEL_FILE_NAME";

	String EXCEL_SHEET_NAME = "EXCEL_SHEET_NAME";

	String EXCEL_CONTENT = "EXCEL_CONTENT";
	
	String EXCEL_CONTENT_TITLE_ROW = "row";
	
	String EXCEL_CONTENT_TITLE_COLUMN = "column";
	
	ExcelView EXCEL_VIEW = new ExcelView();

	String JSON_ROOT = "JSON_ROOT";

	JSONView JSON_VIEW = new JSONView();

	String SECRET_KEY = "2013";

	String ONLINE = "online";

	String OFFLINE = "offline";

	String TEMP_PREFIX = "temp_";
	
	Integer STATIS_TYPE_CHANNEL = 0;

	Integer STATIS_TYPE_SDK = 1;
	
	Long DEV_ADMIN_ROLE_ID=2L; // 开发者角色ID
	
	Long SUPER_ADMIN_ROLE_ID=1L; // 管理员角色ID
	
	Integer LIST_PAGESIZE = (((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height)-440)/20-1 ;
	
	Integer CHANNEL_LIST_PAGESIZE = 10;
	
	String LOGINUSER_TYPE="LOGINUSER_TYPE";
	
	String OPERATOR_LOGIN_PREFIX="op";
	
	String ADMIN_LOGIN_PREFIX="adm";
	
	//审核状态
	Integer CHECKED_TYPE = 1;
		
	//未审核状态
	Integer UN_CHECKED_TYPE = 0;
	
	Integer CHANNEL_ROLE_TYPE = 3 ;
	

}
