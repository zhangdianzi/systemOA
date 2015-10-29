package com.manager.admin.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PathUtil
{
	private static String accessLogDir;

	private static String accessLogDirWindows;

	private static String apkTmpDir;

	private static String apkTmpDirWindows;

	private static String iconTmpDir;

	private static String iconTmpDirWindows;

	public static String apkUrlPrefix;

	public static String imgUrlPrefix;

	public static String clientUrlPrefix;
	
	public static String aaptPath;

	private static boolean isLinux;

	static
	{
		String os = System.getProperty("os.name");
		if (os != null && os.toUpperCase().indexOf("LINUX") > -1)
		{
			isLinux = true;
		}
		else
		{
			isLinux = false;
		}
	}

	/**
	 * 访问日志路径
	 * 
	 * @param dateStr
	 * @return String
	 */
	public static String getAccessLogPath(String dateStr)
	{
		if (isLinux)
		{
			return accessLogDir + "access_log_" + dateStr + ".txt";
		}
		else
		{
			return accessLogDirWindows + "access_log_" + dateStr + ".txt";
		}
	}

	/**
	 * Apk文件临时保存目录
	 * 
	 * @return
	 */
	public static String getApkTmpPath()
	{
		if (isLinux)
		{
			return apkTmpDir;
		}
		else
		{
			return apkTmpDirWindows;
		}
	}

	/**
	 * 图标保存目录
	 * 
	 * @return
	 */
	public static String getIconTmpPath()
	{
		if (isLinux)
		{
			return iconTmpDir;
		}
		else
		{
			return iconTmpDirWindows;
		}
	}

	@Value("#{moduleProperties['accessLogDir']}")
	public void setAccessLogDir(String accessLogDir)
	{
		PathUtil.accessLogDir = accessLogDir;
	}

	@Value("#{moduleProperties['accessLogDirWindows']}")
	public void setAccessLogDirWindows(String accessLogDirWindows)
	{
		PathUtil.accessLogDirWindows = accessLogDirWindows;
	}

	@Value("#{moduleProperties['apkTmpDir']}")
	public void setApkTmpDir(String apkTmpDir)
	{
		PathUtil.apkTmpDir = apkTmpDir;
	}

	@Value("#{moduleProperties['apkTmpDirWindows']}")
	public void setApkTmpDirWindows(String apkTmpDirWindows)
	{
		PathUtil.apkTmpDirWindows = apkTmpDirWindows;
	}

	@Value("#{moduleProperties['iconTmpDir']}")
	public void setIconTmpDir(String iconTmpDir)
	{
		PathUtil.iconTmpDir = iconTmpDir;
	}

	@Value("#{moduleProperties['iconTmpDirWindows']}")
	public void setIconTmpDirWindows(String iconTmpDirWindows)
	{
		PathUtil.iconTmpDirWindows = iconTmpDirWindows;
	}

	@Value("#{moduleProperties['apkUrlPrefix']}")
	public void setApkUrlPrefix(String apkUrlPrefix)
	{
		PathUtil.apkUrlPrefix = apkUrlPrefix;
	}

	@Value("#{moduleProperties['imgUrlPrefix']}")
	public void setImgUrlPrefix(String imgUrlPrefix)
	{
		PathUtil.imgUrlPrefix = imgUrlPrefix;
	}

	@Value("#{moduleProperties['clientUrlPrefix']}")
	public void setClientUrlPrefix(String clientUrlPrefix)
	{
		PathUtil.clientUrlPrefix = clientUrlPrefix;
	}

	@Value("#{moduleProperties['aaptPath']}")
    public void setAaptPath(String aaptPath) {
        PathUtil.aaptPath = aaptPath;
    }

}
