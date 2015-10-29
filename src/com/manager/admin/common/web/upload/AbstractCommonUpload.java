package com.manager.admin.common.web.upload;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.manager.admin.common.Constants;
import com.manager.admin.common.exception.AdminException;
import com.manager.admin.common.utils.FtpClientUtil;

public abstract class AbstractCommonUpload implements Constants
{

	/**
	 * 文件上传 multipart/form-data
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> doFileUpload(HttpServletRequest request, HttpServletResponse response)
			throws AdminException
	{
		int total = request.getContentLength();
		int fileMaxSize = getFileMaxSize();
		if (fileMaxSize != 0 && total > fileMaxSize * 1024)
		{ // 0表示没有限制
			throw new AdminException("上传的文件大小不能大于:" + fileMaxSize + "K");
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try
		{
			List<FileItem> items = upload.parseRequest(request);
			// 循环list中的对象
			List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
			for (FileItem fileItem : items)
			{
				Map<String, String> fileInfo = doWithFiltItem(request, response, fileItem);
				if (null != fileInfo)
				{
					fileList.add(fileInfo);
				}
			}
			return fileList;
		}
		catch (FileUploadException e)
		{
			throw new AdminException(e);
		}
	}

	/**
	 * 文件上传过程处理
	 * 
	 * @param request
	 * @param response
	 * @param fileItem
	 * @return
	 * @throws AdminException
	 */
	private Map<String, String> doWithFiltItem(HttpServletRequest request, HttpServletResponse response,
			FileItem fileItem) throws AdminException
	{
		InputStream ins = null;
		try
		{
			if (fileItem.isFormField())
			{// 如果该FileItem是表单域
				return null;
			}
			// 获取允许上传的文件
			String fileExpand = this.getFileExpand();
			// 获得上传文件的文件名
			String fileName = fileItem.getName().substring(fileItem.getName().replace("\\", "/").lastIndexOf("/") + 1);
			int ind = fileName.lastIndexOf('.');
			String exp = "";
			if (ind > 0)
			{
				exp = fileName.substring(ind).toLowerCase();
				if (fileExpand.indexOf("*" + exp + ";") < 0)
				{
					throw new AdminException("请上传以下类型的文件:" + fileExpand);
				}
			}
			else
			{
				throw new AdminException("请上传以下类型的文件:" + fileExpand);
			}
			String remotePath = getUploadPath(request); // 服务器保存文件路径
			String newFileName = getNewFileName(request, exp.toLowerCase()); // 生成服务器保存文件名
			ins = fileItem.getInputStream();// 获得输入数据流文件
			if (FtpClientUtil.uploadFile(ins, remotePath, newFileName))
			{ // 上传成功
				String fileUrl = getFileUrl(request, newFileName);
				fileItem.delete();// 内存中删除该数据流
				Map<String, String> res = new HashMap<String, String>();
				res.put("filePath", remotePath);
				res.put("fileName", newFileName);
				res.put("fileUrl", fileUrl);
				return res;
			}
			fileItem.delete();// 内存中删除该数据流
		}
		catch (Exception ex)
		{
			fileItem.delete();// 内存中删除该数据流
		}
		finally
		{
			if (ins != null)
			{
				try
				{
					ins.close();
				}
				catch (IOException e)
				{
				}
			}
		}
		return null;
	}

	/**
	 * 编辑器上传实现
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws AdminException
	 */
	public Map<String, String> doXheditorFileUpload(HttpServletRequest request, HttpServletResponse response)
			throws AdminException
	{
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart)
		{
			List<Map<String, String>> fileList = doFileUpload(request, response);
			String fileUrl = "";
			if (fileList != null && fileList.size() > 0)
			{
				fileUrl = fileList.get(0).get("fileUrl");
			}
			return genXheditorResultMap("", fileUrl);
		}
		else
		{
			return xhEditorHtml5Upload(request, response);
		}
	}

	/**
	 * 编辑器html5方法上传处理
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private Map<String, String> xhEditorHtml5Upload(HttpServletRequest request, HttpServletResponse response)
	{
		InputStream ins = null;
		try
		{
			int maxSize = this.getFileMaxSize();
			int fileLength = request.getContentLength();
			if (maxSize > 0 && fileLength > maxSize * 1024)
			{ // 检查文件大小
				return genXheditorResultMap("上传文件的大小超出限制", "");
			}

			String dispoString = request.getHeader("Content-Disposition");
			int iFindStart = dispoString.indexOf("name=\"") + 6;
			int iFindEnd = dispoString.indexOf("\"", iFindStart);
			iFindStart = dispoString.indexOf("filename=\"") + 10;
			iFindEnd = dispoString.indexOf("\"", iFindStart);
			String sFileName = dispoString.substring(iFindStart, iFindEnd);
			String exp = sFileName.substring(sFileName.lastIndexOf(".")); // 获取文件扩展名
			String fileExt = this.getFileExpand();
			if (fileExt.indexOf("*" + exp + ";") < 0)
			{ // 检查文件类型
				return genXheditorResultMap("不允许上传此类型的文件", "");
			}

			ins = request.getInputStream();
			String remotePath = getUploadPath(request); // 服务器保存文件路径
			String newFileName = getNewFileName(request, exp.toLowerCase()); // 生成服务器保存文件名
			if (FtpClientUtil.uploadFile(ins, remotePath, newFileName))
			{ // 上传成功
				String fileUrl = getFileUrl(request, newFileName);
				return genXheditorResultMap("", fileUrl); // 成功返回
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			if (ins != null)
			{
				try
				{
					ins.close();
				}
				catch (IOException e)
				{
				}
			}
		}
		return genXheditorResultMap("失败", "");
	}

	/**
	 * 生成xheditor上传结果
	 * 
	 * @param err:错误消息
	 * @param msg:上传返回url
	 * @return
	 */
	private Map<String, String> genXheditorResultMap(String err, String msg)
	{
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("err", err);
		resultMap.put("msg", msg);
		return resultMap;
	}

	/**
	 * 根据时间和随机值生成文件名
	 */
	public String generateRandomFilename()
	{
		int randomNum = (int) (Math.random() * 10000);
		String fourRandom = randomNum + "";
		int randLength = fourRandom.length();
		if (randLength < 4)
		{
			for (int i = 1; i <= 4 - randLength; i++)
				fourRandom = fourRandom + "0";
		}
		Date dt = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = fmt.format(dt) + fourRandom;
		return fileName;
	}

	/**
	 * 生成服务器中保存的新的文件名
	 */
	public abstract String getNewFileName(HttpServletRequest request, String expand);

	/**
	 * 返回文件访问的URL
	 */
	public abstract String getFileUrl(HttpServletRequest request, String newFileName);

	/**
	 * 上传文件的保存路径
	 */
	public abstract String getUploadPath(HttpServletRequest request);

	/**
	 * 允许上传文件的扩展名
	 */
	public abstract String getFileExpand();

	/**
	 * 允许上传文件的总大小，单位K
	 */
	public abstract int getFileMaxSize();

}
