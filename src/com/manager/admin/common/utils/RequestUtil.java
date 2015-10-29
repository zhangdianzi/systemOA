package com.manager.admin.common.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class RequestUtil
{

	/**
	 * 判断是否为AJAX请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request)
	{
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))
				|| request.getParameter("ajax") != null;
	}

	/**
	 * 取参数的Long �?
	 * 
	 * @param request
	 * @param paraName
	 * @return
	 */
	public static Long getLong(HttpServletRequest request, String paraName,long defaultValue)
	{
		String tempStr = request.getParameter(paraName);
		if (!StringUtils.isBlank(tempStr))
		{
		    try {
                return Long.parseLong(tempStr);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
		}
		return defaultValue;
	}
	public static BigDecimal getBigDecimal(HttpServletRequest request, String paraName)
	{
		String tempStr = request.getParameter(paraName);
		if (!StringUtils.isBlank(tempStr))
		{
			try {
				return new BigDecimal(tempStr);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * 取参数的Long 值数�?
	 * 
	 * @param request
	 * @param paraName
	 * @return
	 */
	public static List<Long> getLongs(HttpServletRequest request, String paraName)
	{
		String tempStrArray[] = request.getParameterValues(paraName);
		List<Long> valueArray = new ArrayList<Long>();
		if (null != tempStrArray)
		{
			for (String tempStr : tempStrArray)
			{
				if (StringUtils.isNotBlank(tempStr) && StringUtils.isNumeric(tempStr))
				{
					valueArray.add(Long.parseLong(tempStr.trim()));
				}
			}
		}
		return valueArray;
	}

	/**
	 * 取参数�?
	 * 
	 * @param request
	 * @param paraName
	 * @return
	 */
	public static String getString(HttpServletRequest request, String paraName)
	{
		String tempStr = request.getParameter(paraName);
		if (null == tempStr)
		{
			return "";
		}
		if (StringUtils.isBlank(tempStr))
		{
			return "";
		}
		return tempStr;
	}

	/**
	 * 取参数的Integer�?
	 * 
	 * @param request
	 * @param paraName
	 * @return
	 */
	public static Integer getInteger(HttpServletRequest request, String paraName,Integer defaultValue)
	{
		String tempStr = request.getParameter(paraName);
		if (StringUtils.isBlank(tempStr) || !StringUtils.isNumeric(tempStr))
		{
			return defaultValue;
		}
		return Integer.parseInt(tempStr);
	}

	/**
	 * 取参数的Double�?
	 * 
	 * @param request
	 * @param paraName
	 * @return
	 */
	public static Double getDouble(HttpServletRequest request, String paraName)
	{
		String tempStr = request.getParameter(paraName);
		if (StringUtils.isBlank(tempStr))
		{
			return null;
		}
		try
		{
			return Double.parseDouble(tempStr);
		}
		catch (Exception ex)
		{
		}
		return null;
	}

	/**
	 * 取参数的Float�?
	 * 
	 * @param request
	 * @param paraName
	 * @return
	 */
	public static Float getFloat(HttpServletRequest request, String paraName)
	{
		String tempStr = request.getParameter(paraName);
		if (StringUtils.isBlank(tempStr))
		{
			return null;
		}
		try
		{
			return Float.parseFloat(tempStr);
		}
		catch (Exception ex)
		{
		}
		return null;
	}

	/**
	 * 取参数的Date�?
	 * 
	 * @param request
	 * @param paraName
	 * @param format
	 * @return
	 */
	public static Date getDate(HttpServletRequest request, String paraName, String format)
	{
		String tempStr = request.getParameter(paraName);
		if (StringUtils.isBlank(tempStr))
		{
			return null;
		}
		try
		{
			return new SimpleDateFormat(format).parse(tempStr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取参数的Calendar�?
	 * 
	 * @param request
	 * @param paraName
	 * @param format
	 * @return
	 */
	public static Calendar getCalendar(HttpServletRequest request, String paraName, String format)
	{
		Date date = getDate(request, paraName, format);
		Calendar cal = Calendar.getInstance();
		if (date != null)
		{
			cal.setTime(date);
		}
		else
		{
			cal = null;
		}
		return cal;
	}

	/**
	 * 取参数的boolean�?
	 * 
	 * @param request
	 * @param paraName
	 * @return
	 */
	public static boolean getBool(HttpServletRequest request, String paraName)
	{
		String tempStr = request.getParameter(paraName);
		if (tempStr == null)
		{
			return false;
		}
		tempStr = tempStr.trim();
		if ("true".equalsIgnoreCase(tempStr) || "1".equals(tempStr))
		{
			return true;
		}
		return false;
	}

	/**
	 * 取参数的Boolean�?
	 * 
	 * @param request
	 * @param paraName
	 * @return
	 */
	public static Boolean getBoolean(HttpServletRequest request, String paraName)
	{
		String tempStr = request.getParameter(paraName);
		if (StringUtils.isBlank(tempStr))
		{
			return null;
		}
		tempStr = tempStr.trim();
		if ("true".equalsIgnoreCase(tempStr) || "1".equals(tempStr))
		{
			return true;
		}
		return false;
	}

	/**
	 * 取参数的Integer值列�?
	 * 
	 * @param request
	 * @param paraName
	 * @return
	 */
	public static List<Integer> getIntegers(HttpServletRequest request, String paraName)
	{
		String tempStrArray[] = request.getParameterValues(paraName);
		List<Integer> valueArray = new ArrayList<Integer>();
		if (null != tempStrArray)
		{
			for (String tempStr : tempStrArray)
			{
				if (StringUtils.isNotBlank(tempStr) && StringUtils.isNumeric(tempStr))
				{
					valueArray.add(Integer.parseInt(tempStr.trim()));
				}
			}
		}
		return valueArray;
	}

	/**
	 * 取参数的Double值列�?
	 * 
	 * @param request
	 * @param paraName
	 * @return
	 */
	public static List<Double> getDoubles(HttpServletRequest request, String paraName)
	{
		String tempStrArray[] = request.getParameterValues(paraName);
		List<Double> valueArray = new ArrayList<Double>();
		if (null != tempStrArray)
		{
			for (String tempStr : tempStrArray)
			{
				if (StringUtils.isNotBlank(tempStr))
				{
					try
					{
						valueArray.add(Double.parseDouble(tempStr));
					}
					catch (Exception ex)
					{
					}
				}
			}
		}
		return valueArray;
	}

	/**
	 * 取参数的Float值列�?
	 * 
	 * @param request
	 * @param paraName
	 * @return
	 */
	public static List<Float> getFloats(HttpServletRequest request, String paraName)
	{
		String tempStrArray[] = request.getParameterValues(paraName);
		List<Float> valueArray = new ArrayList<Float>();
		if (null != tempStrArray)
		{
			for (String tempStr : tempStrArray)
			{
				if (StringUtils.isNotBlank(tempStr))
				{
					try
					{
						valueArray.add(Float.parseFloat(tempStr));
					}
					catch (Exception ex)
					{
					}
				}
			}
		}
		return valueArray;
	}

	/**
	 * 取参数的�?
	 * 
	 * @param request
	 * @param paraName
	 * @return
	 */
	public static String[] getStringArray(HttpServletRequest request, String paraName)
	{
		return request.getParameterValues(paraName);
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getAllRequestParameter(HttpServletRequest request)
	{
		Enumeration en = request.getParameterNames();
		String parameterName = null;
		StringBuilder buf = new StringBuilder();
		String valueArray[] = null;
		while (en.hasMoreElements())
		{
			parameterName = (String) en.nextElement();
			valueArray = request.getParameterValues(parameterName);
			for (String value : valueArray)
			{
				buf.append("&").append(parameterName).append("=").append(value);
			}
		}
		return buf.toString();
	}

	public static String getSubIpAddr(HttpServletRequest request)
	{
		String ip = request.getHeader("X-Forwarded-For");
		if (null != ip && ip.length() > 0)
		{
			return ip;
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getUserIpAddr(HttpServletRequest request)
	{
		String ip = request.getHeader("X-Forwarded-For");
		if (null != ip && ip.length() > 0)
		{
			String tmpIps[] = ip.split(",");
			for (String tmpIp : tmpIps)
			{
				tmpIp = tmpIp.trim();
				if (null != tmpIp && tmpIp.length() > 0 && !"unknown".equalsIgnoreCase(tmpIp))
				{
					ip = tmpIp;
					break;
				}
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static void printAllHeaders(HttpServletRequest request)
	{
		Enumeration en = request.getHeaderNames();
		String headerName = null;
		System.out.println("<------------------print header begin----------------------->");
		Enumeration valueArray = null;
		String value = null;
		while (en.hasMoreElements())
		{
			headerName = (String) en.nextElement();
			valueArray = request.getHeaders(headerName);
			while (valueArray.hasMoreElements())
			{
				value = (String) valueArray.nextElement();
				System.out.println(headerName + "=" + value);
			}
		}
		System.out.println("<------------------print header end------------------------->");
	}
	
	/**
     * 从cookie取值
     * 
     * @param request
     * @param paraName
     * @return
     */
    public static String getStringAtCookie(HttpServletRequest request, String paraName)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            for (Cookie cookie : cookies)
            {
                if (paraName.equals(cookie.getName()))
                {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    
    public static String getPostData(HttpServletRequest request)
    {
        StringBuffer data = new StringBuffer();
        try
        {
            InputStream in = request.getInputStream();
            BufferedInputStream buf = new BufferedInputStream(in);

            byte[] buffer = new byte[1024];
            int iRead;
            while ((iRead = buf.read(buffer)) != -1)
            {
                data.append(new String(buffer, 0, iRead, "UTF-8"));
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return data.toString();
    }
}
