package com.manager.admin.common;

import java.util.ArrayList;
import java.util.List;


public class StaticCache
{
	//风控员缓存
	public static List<Integer> controlmanCache= new ArrayList<Integer>();
	public static Integer controlmanFlag=0;
	//业务员缓存
	public static List<Integer> documentaryCache = new ArrayList<Integer>();
	public static Integer documentaryFlag=0;
}
