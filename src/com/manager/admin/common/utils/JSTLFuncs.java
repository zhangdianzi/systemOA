package com.manager.admin.common.utils;

public class JSTLFuncs
{
	public static Boolean bitAnd(Integer value1, Integer value2, Integer value3)
	{
		if (value1 == null)
		{
			return false;
		}
		if (value2 == null)
		{
			return false;
		}
		if (value3 == null)
		{
			return false;
		}
		return (value1 & value2) == value3;
	}
}
