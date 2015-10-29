package com.manager.admin.common.web.filter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.admin.common.to.Account;
import com.manager.admin.common.utils.DwzJsonUtil;
import com.manager.admin.common.utils.PathUtil;
import com.manager.admin.common.utils.RequestUtil;
import com.manager.admin.common.web.Funcs;
import com.manager.admin.common.web.JSONView;

public class LoginFilter implements Filter
{
	private static OutputStreamWriter fileWriter;

	private static String dateStr;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private List<String> ignoreUrlList = new ArrayList<String>();

	public void init(FilterConfig filterConfig) throws ServletException
	{
		String temp = filterConfig.getInitParameter("ignoreUrl");
		if (null != temp && !"".equals(temp))
		{
			String[] ts = temp.split(";");
			for (String t : ts)
			{
				this.ignoreUrlList.add(t);
			}
		}
	}

	public void destroy()
	{
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession(false);
		String servletPath = request.getServletPath();

		if (null != request.getPathInfo())
		{
			servletPath += request.getPathInfo();
		}

		if (ignoreUrlList.contains(servletPath))
		{
			chain.doFilter(request, response);
			return;
		}
		Account user = Funcs.getSessionLoginUser(session);
		if (null == user)
		{
		    String redirectUrl="";
		    String serverName = request.getServerName();
		    System.out.println(serverName);
    		redirectUrl="/login.html";
			if (RequestUtil.isAjax(request))
			{
				JSONView.writeJSONData(response, DwzJsonUtil.getSessionTimeOutMsg());
			}
			else
			{
				response.sendRedirect(request.getContextPath() + redirectUrl);
			}
			return;
		}
//		writeAccessLog(user, servletPath, request);
		chain.doFilter(request, response);
	}
	
//	private void writeAccessLog(Account user, String servletPath, HttpServletRequest request)
//	{
//	    Date now = new Date();
//	    
//	    String subIp = request.getHeader("Proxy-Client-IP");
//        if (subIp == null || subIp.length() == 0)
//        {
//            subIp = request.getHeader("X-Forwarded-For");
//        }
//        StringBuilder buf = new StringBuilder();
//        buf.append(dateTimeFormat.format(now)).append("|").append("userId:").append(user.getId()).append("|");
//        buf.append("username:").append(user.getUserName()).append("|").append(request.getRemoteAddr()).append("|").append(RequestUtil.getSubIpAddr(request)).append("|");
//        buf.append(servletPath).append("|").append(RequestUtil.getAllRequestParameter(request)).append("|");
//        buf.append("\r\n");
//        
//		if (dateStr == null || dateFormat.format(now) != dateStr)
//		{
//			dateStr = dateFormat.format(now);
//			try
//			{
//				if (fileWriter != null)
//				{
//					fileWriter.close();
//				}
//
//				File file = new File(PathUtil.getAccessLogPath(dateStr));
//				File dir = file.getParentFile();
//				if (!dir.exists())
//				{
//					dir.mkdirs();
//				}
//				fileWriter = new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8");
//				fileWriter.write(buf.toString());
//	            fileWriter.flush();
//			}
//			catch (Exception ex)
//			{
//				ex.printStackTrace();
//			}
//		}
//	}
}
