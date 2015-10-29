package com.manager.admin.ui.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manager.admin.common.Constants;
import com.manager.admin.common.to.Account;
import com.manager.admin.common.web.Funcs;

public class BaseController implements Constants
{
	protected Account getLoginUser(HttpServletRequest request)
	{
		return Funcs.getSessionLoginUser(request.getSession(false));
	}

	protected void generateExceptionAjax(HttpServletRequest request, HttpServletResponse response, String message)
	{
		String navTabId = request.getParameter("navTabId");
		String callbackType = "closeCurrent";
		String forwardUrl = request.getParameter("forwardurl");
		if (forwardUrl != null && forwardUrl.trim().length() > 0)
		{
			callbackType = "forward";
		}
		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.print("{\"statusCode\":\"300\",\"message\":\"" + message + "\",\"navTabId\":\"" + navTabId
					+ "\",\"rel\":\"\",\"callbackType\":\"" + callbackType + "\",\"forwardUrl\":\"" + forwardUrl
					+ "\"}");
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
