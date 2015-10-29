package com.manager.admin.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class EmailFormat {
	public static boolean emailFormat(String email)
    {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        if(!StringUtils.isEmpty(email))
        {
        	final Matcher mat = pattern.matcher(email);
        	if (!mat.find()) {
        		tag = false;
        	}
        	return tag;
        }
        else
        {
        	return false;
        }
    }
	
	public static void main(String args[])
	{
		System.out.println(emailFormat(""));
	}
}
