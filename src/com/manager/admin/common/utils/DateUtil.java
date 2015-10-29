package com.manager.admin.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil 
{	public static void main(String[] args) {
	Date nowDate=new Date();
	Date date=DateUtil.parseDateString("2015-1-22", "yyyy-MM-dd");
	/*	System.out.println(nowDate+"---");
	System.out.println(getSundayOfLastWeek());
	System.out.println(getMondayOfLastWeek());
	System.out.println(getFirstDayInLastMonth(13));
	System.out.println(getLastDayInLastMonth(12));
	System.out.println(getDayOfBefore(nowDate, 7));*/
	System.out.println(getMaxDate(nowDate, date));
	System.out.println(getMinDate(nowDate, date));
	System.out.println(getLastMonth(nowDate,1));
	System.err.println(getdays(date,nowDate));
}
public static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    
    public static final String DATE_FORMAT_2 = "yyyy-MM-dd";
	
    public static Date parseDateString(String dateStr, String formatStr)
    {
        if (dateStr == null || dateStr.trim().length() == 0)
        {
            return null;
        }
        DateFormat df = new SimpleDateFormat(formatStr);
        try
        {
            return df.parse(dateStr);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static String formatDate(Date date, String formatStr)
    {
    	if(date==null)
    		return null;
        DateFormat df = new SimpleDateFormat(formatStr);
        try
        {
            return df.format(date);
        }
        catch (Exception e)
        {
            return "";
        }
    }
    
    //返回两个日期中最大的
    public static Date getMaxDate(Date date1,Date date2){
    	if (date1.before(date2)) {
    		return date2;
		}
		return date1;
    	
    }
    //返回两个日期中最大的
    public static Date getMinDate(Date date1,Date date2){
    	if (date1.before(date2)) {
    		return date1;
		}
		return date2;
    	
    }
    
	/**
	 * 返回给定时间当前月的第一天
	 * 
	 * @param date
	 */
	public static Date getFirstDayInMonth(Date date)
	{	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//获取某个月的第一天
		calendar.set(Calendar.DAY_OF_MONTH, 1);		
		return calendar.getTime();
	}
	
	/**
	 * 返回当前月前n个月的第一天
	 * 
	 * @param date
	 */
	public static Date getFirstDayInLastMonth(int n)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -n);
		calendar.set(Calendar.DAY_OF_MONTH, 1);	
		return calendar.getTime();
	}
	/**
	 * 返回当前月前n个月的最后一天
	 * 
	 * @param date
	 */
	public static Date getLastDayInLastMonth(int n)
	{
		Calendar calendar = Calendar.getInstance();
		//获取某月的最后一天
		calendar.add(Calendar.MONTH, -n);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));   
		return calendar.getTime();
	}
 
	/**
	  * 得到本周周一
	  *
	  * @return yyyy-MM-dd
	  */
	 public static Date getMondayOfThisWeek(Date date) {
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 1);
	  return c.getTime();
	 }
		/**
	  * 得到上周周日
	  *
	  * @return yyyy-MM-dd
	  */
	 public static Date getSundayOfLastWeek() {
	  Calendar c = Calendar.getInstance();
	  c.set(Calendar.DAY_OF_WEEK, 1);
	  return c.getTime();
	 }

		/**
	  * 得到上周周一
	  *
	  * @return yyyy-MM-dd
	  */
	 public static Date getMondayOfLastWeek() {
	  Calendar c = Calendar.getInstance();
	  c.add(Calendar.WEEK_OF_MONTH, -1);
	  c.set(Calendar.DAY_OF_WEEK, 2);
	  return c.getTime();
	 }
	/**
	 * 返回给定时间当前月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayInMonth(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}
	//得到当前月前n个月月份（yyyy-mm）
	public static String getLastMonth(Date date,int n)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -n);
		return DateUtil.formatDate(calendar.getTime(), "yyyy-MM");
		//return calendar.getTime();
	}
	
	/**
	 * 获取每月的各个周的起止时间,不包含时、分、秒、毫秒
	 * 
	 * @param date
	 * @return
	 */
	public static Map<Integer, Date> getWeekInMonth(Date date)
	{
		Map<Integer, Date> weeksMap = new HashMap<Integer, Date>();
		//获取当前月的第一天
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);	
		
		//去除时、分、秒、毫秒
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		//获取当前月第一天为星期几
		int weekPos = calendar.get(Calendar.DAY_OF_WEEK);
		
		//获取一个月的实际天数
		int daysNum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		//对这个进行判断
		switch(weekPos)
		{
			case Calendar.SUNDAY :
			{
				getFirstLastDayInWeek(weeksMap, calendar, daysNum, 7);
				break;
			}
			case Calendar.MONDAY :
			{
				getFirstLastDayInWeek(weeksMap, calendar, daysNum, 1);
				break;
			}
			case Calendar.TUESDAY :
			{
				getFirstLastDayInWeek(weeksMap, calendar, daysNum, 2);
				break;
			}
			case Calendar.WEDNESDAY :
			{
				getFirstLastDayInWeek(weeksMap, calendar, daysNum, 3);
				break;
			}
			case Calendar.THURSDAY :
			{
				getFirstLastDayInWeek(weeksMap, calendar, daysNum, 4);
				break;
			}
			case Calendar.FRIDAY :
			{
				getFirstLastDayInWeek(weeksMap, calendar, daysNum, 5);
				break;
			}
			case Calendar.SATURDAY :
			{
				getFirstLastDayInWeek(weeksMap, calendar, daysNum, 6);
				break;
			}
		}
		
		return weeksMap;
	}

	/**
	 * 获取一个月中各个周的第一天和最后一天
	 * 
	 * @param weeksMap
	 * @param calendar
	 * @param daysNum
	 * @param tagNum
	 */
	private static void getFirstLastDayInWeek(Map<Integer, Date> weeksMap, Calendar calendar, int daysNum, int tagNum) 
	{
		//添加循环计数器
		int count = (int) Math.ceil(((double)(daysNum + tagNum-1))/7);
		//将calendar往后推1天，作为循环起始时间
		calendar.add(Calendar.DATE, -tagNum);
		for(int i=1; i<=count; i++)
		{
			//起始时间往前推一天
			calendar.add(Calendar.DATE, 1);
			//放入某一周的第一天
			weeksMap.put(2*i-1, calendar.getTime());
			
			//起始时间往前推六天
			calendar.add(Calendar.DATE, 6);
			//放入某一周的最后一天
			weeksMap.put(2*i, calendar.getTime());
		}
	}
	
	/**
     * 当前时间提前一天，年月日
     * 
     * @param date
     * @return
     */
    public static Date getDayOfBefore(Date date)
    {
        Calendar result = Calendar.getInstance();
        result.setTime(date);
        result.set(result.get(Calendar.YEAR), result.get(Calendar.MONTH), result.get(Calendar.DATE) - 1, 0, 0, 0);
        return result.getTime();
    }
    
    /**
     * 当前时间提前一天，年月日
     * 
     * @param date
     * @return
     */
    public static Date getDayOfBefore(Date date, int days)
    {
        Calendar result = Calendar.getInstance();
        result.setTime(date);
        result.set(result.get(Calendar.YEAR), result.get(Calendar.MONTH), result.get(Calendar.DATE) - days, 0, 0, 0);
        return result.getTime();
    }
    
    public static Date getDayOfAfter(Date date, int days)
    {
        Calendar result = Calendar.getInstance();
        result.setTime(date);
        result.set(result.get(Calendar.YEAR), result.get(Calendar.MONTH), result.get(Calendar.DATE) + days, 0, 0, 0);
        return result.getTime();
    }
    
    /***
	 * 获取指定date时间       前n天的日期
	 * */
	public static Date getDayOfNum(Date date , int n)
	{
		//String s = null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - n);
		return c.getTime();
	/*	s = DateUtil.formatDate(date, DATE_FORMAT_2);
		return s;*/
	}
	public static String getDayStringOfNum(Date date , int n)
	{
		String s = null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - n);
		date = c.getTime();
		s = DateUtil.formatDate(date, DATE_FORMAT_2);
		return s;
	}
	//获取两个日期之间的天数
	public static int getdays(Date startDate,Date endDate){
		long start = startDate.getTime();
		long end = endDate.getTime();
		Long day=(end-start)/(1000*24*60*60);
		int days=day.intValue();
		return days+1;
		
	}
	
}
