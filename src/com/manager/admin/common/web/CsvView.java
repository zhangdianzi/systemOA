package com.manager.admin.common.web;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.manager.admin.common.Constants;

public class CsvView extends AbstractView implements Constants
{

	public CsvView()
	{
		setContentType(CSV_CONTENT_TYPE);
	}

	public String getContentType()
	{
		return CSV_CONTENT_TYPE;
	}

	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		response.setContentType(getContentType());
		String cont = (String) model.get(CSV_CONTENT);
		String fileName = (String) model.get(CSV_FILE_NAME);
		response.setCharacterEncoding("GBK");
		response.addHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		OutputStream out = response.getOutputStream();
		out.write(cont.getBytes("GBK"));
		out.flush();
		out.close();
	}

}
