package com.manager.admin.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 用jackson处理json
 * 
 * @author huangfei@freeg.cn
 * 
 */
public class JsonUtil
{
	private static ObjectMapper mapper;
	static
	{
		mapper = new ObjectMapper();
		// 设置输出包含的属性
		mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		mapper.getDeserializationConfig().set(
			org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 设置时间格式
		mapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	public static String toJsonString(Object object)
	{
		String json = "{}";
		try
		{
			json = mapper.writeValueAsString(object);
		}
		catch (JsonGenerationException e)
		{
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return json;
	}

	public static Object parse(String json, @SuppressWarnings("rawtypes") Class className)
	{
		try
		{
			@SuppressWarnings("unchecked")
			Object object = mapper.readValue(json, className);
			return object;
		}
		catch (JsonParseException e)
		{
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
