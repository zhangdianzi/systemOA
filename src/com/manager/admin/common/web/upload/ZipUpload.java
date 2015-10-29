package com.manager.admin.common.web.upload;

import java.io.File;
import java.text.SimpleDateFormat;
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
import org.springframework.stereotype.Component;

import com.manager.admin.common.exception.AdminException;
import com.manager.admin.common.utils.FtpClientUtil;
import com.manager.admin.common.utils.PathUtil;

/**
 * 图片上传，生成图片缩略图
 * 
 */
@Component
public class ZipUpload
{
	private static int apkMaxSize = 1024 * 500; // 单位K，apk大小在200M内

	private static String apkExpand = "*.zip;";

	private static String remotePath = "admin/zip";

	/**
	 * 上传APK文件
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws AdminException
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> doUpload(HttpServletRequest request, HttpServletResponse response) throws AdminException
	{
		int fileSizeTotal = request.getContentLength();
		if (fileSizeTotal > apkMaxSize * 1024)
		{ // 0表示没有限制
			throw new AdminException("上传的文件大小不能大于:" + apkMaxSize + "K");
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload uploader = new ServletFileUpload(factory);
		try
		{
			List<FileItem> items = uploader.parseRequest(request);
			// 循环list中的对象
			for (FileItem fileItem : items)
			{
				try
				{
					if (fileItem.isFormField())
					{// 如果该FileItem是表单域
						continue;
					}
					// 获取允许上传的文件
					// 获得上传文件的文件名
					String fileName = fileItem.getName().substring(
						fileItem.getName().replace("\\", "/").lastIndexOf("/") + 1);
					int ind = fileName.lastIndexOf('.');
					String exp = "";
					if (ind > 0)
					{
						exp = fileName.substring(ind).toLowerCase();
						if (apkExpand.indexOf("*" + exp + ";") < 0)
						{
							throw new AdminException("请上传以下类型的文件:" + apkExpand);
						}
					}
					else
					{
						throw new AdminException("请上传以下类型的文件:" + apkExpand);
					}

					String newName = this.generateRandomFilename() + exp; // 随机生成apk文件名称
					String localPath = PathUtil.getApkTmpPath(); // 本地临时目录
					File localTempFile = new File(localPath + newName); // 本地原图临时存放
					File dir = localTempFile.getParentFile();
					if (!dir.exists())
					{
						dir.mkdirs();
					}
					fileItem.write(localTempFile); // 保存文件

					long fileSize = fileItem.getSize(); // 文件大小
					fileItem.delete();// 内存中删除该数据流


					// 文件上传到服务器
					String remoteNewName = getDateStr() + "_" + exp; // 时间包名版本
					FtpClientUtil.uploadFile(localPath + newName, remotePath, remoteNewName); // 上传到服务器

					// 生成返回信息
					Map<String, String> apkInfo = new HashMap<String, String>();
					apkInfo.put("url", remotePath + "/" + remoteNewName);
					apkInfo.put("size", String.valueOf(fileSize)); // 大小单位:byte

					localTempFile.delete(); // 删除临时存放文件
					return apkInfo;
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					fileItem.delete();// 内存中删除该数据流
				}
			}
			return null;
		}
		catch (FileUploadException e)
		{
			throw new AdminException(e);
		}
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
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName = fmt.format(dt) + fourRandom;
		return fileName;
	}

	/**
	 * 根据时间和随机值生成文件名
	 */
	public String getDateStr()
	{
		Date dt = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmm");
		String fileName = fmt.format(dt);
		return fileName;
	}
}
