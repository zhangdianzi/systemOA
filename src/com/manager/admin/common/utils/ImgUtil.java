package com.manager.admin.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgUtil {
	public static String wrapImg(String str)
	{
		String regxpForTag = "<\\s*img\\s+([^>]*)\\s*>";
		String regxpForTagAttrib = "src=\"([^\"]+)\"";
		Pattern patternForTag = Pattern.compile(regxpForTag);
		Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
		Matcher matcherForTag = patternForTag.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result = matcherForTag.find();
		while (result) {
			StringBuffer sbreplace = new StringBuffer();
			String imgs = matcherForTag.group();
			String imgsSrc = matchIMGSrc(imgs);
//			System.out.println("imgs = "+imgs);
//			System.out.println("imgsSrc = "+imgsSrc);
			sbreplace.append("<a href="+imgsSrc +" class='group1'>"+imgs+"</a>");
			matcherForTag.appendReplacement(sb, sbreplace.toString());
			result = matcherForTag.find();
		}
		matcherForTag.appendTail(sb);
		return sb.toString();
	}
	public static String wrap(String s)
	{
		String[] ss = s.split("<p>");
	  	String result = "";
	  	String res = "";
	  	for(int i = 0 ;i<ss.length;i++)
	  	{
	  		if(ss[i].indexOf("<img")!= -1)
	  		{
	  			String s1 = ss[i].substring(0, ss[i].indexOf("<img"));
	  			String s2 = "<a href="+matchIMGSrc(ss[i])+" class='group1'>";
	  			String s3 = "";
	  			String s4 = "";
	  			String s5 = "";
	  			if(ss[i].indexOf("<br")!=-1 || ss[i].indexOf("</p") != -1)
	  			{
	  				if(ss[i].indexOf("<br")!=-1)
	  				{
	  					s3 = ss[i].substring(ss[i].indexOf("<img"), ss[i].indexOf("<br"));
	  					s4 = "</a>";
	  					s5 = ss[i].substring(ss[i].indexOf("<br"), ss[i].length());
	  				}
	  				else
	  				{
	  					s3 = ss[i].substring(ss[i].indexOf("<img"), ss[i].indexOf("</p"));
	  					s4 = "</a>";
	  					s5 = ss[i].substring(ss[i].indexOf("</p"), ss[i].length());
	  				}
	  			}
	  			result = s1+s2+s3+s4+s5;
	  			System.out.println("result  = "+result);
	  		}
	  		else
	  		{
	  			result = ss[i];
	  		}
	  		if(i != 0)
	  		{
	  			res=res+"<p>"+result;
	  		}
	  		else
	  		{
	  			res=res+result;
	  		}
	  	}
	  	return res;
	}
	public static String  matchIMGSrc(String content) {
		String result = "";
		if (content == null || "".equals(content))
			return null;
		// 匹配img标签;图片链接地址
		Matcher ma = Pattern.compile("<img.*src=(.*?)[^>]*?>").matcher(content);
		while (ma.find()) {
			// 匹配src 或用这个正则表达�?src=\"?(.*?)(\"|>|\\s+)
			ma = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(ma.group());
			while (ma.find()) {
//				System.out.println("图片的src= "+ma.group(1));
				result = ma.group(1);
			}
		}
		return result;
	}
}
