package com.manager.admin.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.util.apk.parser.ApkUtil;
import com.util.apk.parser.IconUtil;
import com.util.apk.parser.entity.ApkInfo;

@Component
public  class UploadUtil
{
	/**
	 * 上传文件，返回保存文件路径
	 * 
	 * @param request
	 * @param response
	 * @return 文件保存地址
	 * @throws AdminException
	 */
	/**
	 * 服务器文件储存名
	 */
	public String newFileName; //服务器文件储存名
	/**
	 * 上传文件的保存路径
	 */
	public String uploadPath; //上传文件的保存路径
	/**
	 * 允许上传文件的扩展名
	 */
	public String fileExpand; //允许上传文件的扩展名
	/**
	 * 允许上传文件的总大小，单位K
	 */
	public int fileMaxSize; 
	
	public String aaptPath="F:\\Apk\\aapt.exe";
	
	@SuppressWarnings("unchecked")
	public void doUpload(HttpServletRequest request, HttpServletResponse response) throws AdminException
	{
		int fileSizeTotal = request.getContentLength();
		int fileMaxSize = getFileMaxSize();
		if (fileSizeTotal > fileMaxSize * 1024)
		{ // 0表示没有限制
			throw new AdminException("上传的文件大小不能大于:" + fileMaxSize + "K");
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
					// 获得上传文件的文件名
					String fileName = fileItem.getName().substring(
						fileItem.getName().replace("\\", "/").lastIndexOf("/") + 1);
					int ind = fileName.lastIndexOf('.');
					String exp = "";
					String fileExpand = getFileExpand();
					if (ind > 0)
					{
						exp = fileName.substring(ind).toLowerCase();
						if (fileExpand.indexOf(exp) < 0)
						{
							throw new AdminException("请上传以下类型的文件:" + fileExpand);
						}
					}
					else
					{
						throw new AdminException("请上传以下类型的文件:" + fileExpand);
					}

					String newName = this.getNewFileName()+exp; // 随机生成文件名称
					String localPath = this.getUploadPath(); // 本地临时目录
					File localTempFile = new File(localPath + newName); // 本地原图临时存放
					File dir = localTempFile.getParentFile();
					if (!dir.exists())
					{
						dir.mkdirs();
					}
					fileItem.write(localTempFile); // 保存文件
					fileItem.delete();// 内存中删除该数据流

				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					fileItem.delete();// 内存中删除该数据流
				}
			}
		}
		catch (FileUploadException e)
		{
			e.printStackTrace();
			throw new AdminException(e);
		}
	}

	
	
	/**
	 * 上传APK文件
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws AdminException
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> doUploadApk(HttpServletRequest request, HttpServletResponse response) throws AdminException
	{
		int fileSizeTotal = request.getContentLength();
		if (fileSizeTotal > fileMaxSize * 1024)
		{ // 0表示没有限制
			throw new AdminException("上传的文件大小不能大于:" + fileMaxSize + "K");
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
					String fileName = fileItem.getName().substring(fileItem.getName().replace("\\", "/").lastIndexOf("/") + 1);
					int ind = fileName.lastIndexOf('.');
					String exp = "";
					if (ind > 0)
					{
						exp = fileName.substring(ind).toLowerCase();
						if (fileExpand.indexOf(exp) < 0)
						{
							throw new AdminException("请上传以下类型的文件:" + fileExpand);
						}
					}
					else
					{
						throw new AdminException("请上传以下类型的文件:" + fileExpand);
					}

					String apkName = this.generateRandomFilename().concat(exp); // 随机生成apk文件名称
					String localPath = this.getUploadPath(); 
					String apkPath = localPath.concat(apkName);
					
					//将APK临时存放
					File apkTempFile = new File(apkPath); 
					File dir = apkTempFile.getParentFile();
					if (!dir.exists())
					{
						dir.mkdirs();
					}
					fileItem.write(apkTempFile); // 保存文件

					long fileSize = fileItem.getSize(); // 文件大小
					fileItem.delete();// 内存中删除该数据流
					
					ApkUtil apkUtil = new ApkUtil();
					apkUtil.setmAaptPath(aaptPath); 
					ApkInfo apkInfo = apkUtil.getApkInfo(apkPath);
					String packageName = apkInfo.getPackageName();// apk包名
					String versionCode = apkInfo.getVersionCode(); // apk版本号
					String versionName = apkInfo.getVersionName(); // apk版本名称
					String name= apkInfo.getApplicationLable();
					String md5 = MD5Util.getFileMD5(apkTempFile);
					
//					String icon = apkInfo.getApplicationIcon();
//			        String suffix = icon.substring(icon.lastIndexOf("."));
//			        String iconName = apkInfo.getPackageName().concat(suffix);
//			        String iconPath = localPath.concat(iconName);
			        String applicationName = apkInfo.getApplicationLable();
//			        File iconTempFile = new File(iconPath);
				
//					IconUtil.saveIcon(apkPath, icon, iconPath);

					Map<String, String> apkInfoMap = new HashMap<String, String>();
//					// 把APK上传到服务器
					String remoteApkName = getDateStr().concat("_").concat(packageName).concat("_").concat(versionCode).concat(exp); // 时间包名版本
					FtpClientUtil.uploadFile(apkPath, uploadPath, remoteApkName); // 上传到服务器
//					
//					String remoteIconName = getDateStr().concat("_").concat(packageName).concat("_").concat(versionCode).concat(suffix);
//					//把图片上传到服务器
//					FtpClientUtil.uploadFile(iconPath, uploadPath, remoteIconName); // 上传到服务器
					
					// 生成返回信息
//					apkInfoMap.put("url", uploadPath.concat("/").concat(remoteApkName));
					apkInfoMap.put("size", String.valueOf(fileSize)); // 大小单位:byte
					apkInfoMap.put("packageName", packageName);
					apkInfoMap.put("name", name);
					apkInfoMap.put("versionCode", String.valueOf(versionCode));
					apkInfoMap.put("versionName", versionName);
					apkInfoMap.put("applicationName", applicationName);
					apkInfoMap.put("md5", md5);
//					apkInfoMap.put("icon", uploadPath.concat("/").concat(remoteIconName));
					
					apkTempFile.delete(); // 删除临时APK文件
					return apkInfoMap;
				}
				catch (Exception ex)
				{
					
					fileItem.delete();// 内存中删除该数据流\
					throw new AdminException(ex);
				}
			}
			return null;
		}
		catch (FileUploadException e)
		{
		    e.printStackTrace();
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

	
	
	
	
	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	
	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getFileExpand() {
		return fileExpand;
	}

	public void setFileExpand(String fileExpand) {
		this.fileExpand = fileExpand;
	}

	public int getFileMaxSize() {
		return fileMaxSize;
	}

	public void setFileMaxSize(int fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}
	
	 /**保存文件
     * @param stream
     * @param path
     * @param filename
     * @throws IOException
     */
    public static void SaveFileFromInputStream(InputStream stream,String path,String filename) 
    {      
        FileOutputStream fs;
		try {
			fs = new FileOutputStream( path + "/"+ filename);
        byte[] buffer =new byte[1024*1024];
        int byteread = 0; 
        while ((byteread=stream.read(buffer))!=-1)
        {
           fs.write(buffer,0,byteread);
           fs.flush();
        } 
        fs.close();
        stream.close();      
		} catch (Exception e) {
			e.printStackTrace();
		}
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
