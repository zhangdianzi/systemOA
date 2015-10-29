package com.manager.admin.common.utils;

public class VersionInfoUtil
{
	/**
	 * 版本
	 */
	private String version;

	/**
	 * 主版本号
	 */
	private Integer majorVersion;

	/**
	 * 对应客户版本号,对应不用客户升级
	 */
	private Integer clientVersion;

	/**
	 * 时间版本号，yyMMdd:121201
	 */
	private Integer timeVersion;

	/**
	 * 子版本号
	 */
	private Integer childVersion;

	private VersionInfoUtil()
	{
		super();
	}

	public static VersionInfoUtil resolvedVersion(String version)
	{
		VersionInfoUtil info = new VersionInfoUtil();
		info.version = version;
		String[] arr = version.split("\\.");
		info.majorVersion = Integer.parseInt(arr[0]);
		info.clientVersion = Integer.parseInt(arr[1]);
		info.timeVersion = Integer.parseInt(arr[2]);
		info.childVersion = Integer.parseInt(arr[3]);
		return info;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public Integer getMajorVersion()
	{
		return majorVersion;
	}

	public void setMajorVersion(Integer majorVersion)
	{
		this.majorVersion = majorVersion;
	}

	public Integer getClientVersion()
	{
		return clientVersion;
	}

	public void setClientVersion(Integer clientVersion)
	{
		this.clientVersion = clientVersion;
	}

	public Integer getTimeVersion()
	{
		return timeVersion;
	}

	public void setTimeVersion(Integer timeVersion)
	{
		this.timeVersion = timeVersion;
	}

	public Integer getChildVersion()
	{
		return childVersion;
	}

	public void setChildVersion(Integer childVersion)
	{
		this.childVersion = childVersion;
	}

}
