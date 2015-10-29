package com.manager.admin.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.manager.admin.common.Constants;

/**
 * url处理，相对地址带上前缀，替换特殊字符
 * 
 */
public class URLUtil implements Constants {
    private static Map<String, String> ILLEGAL_CHAR;

    private static String URL_PREFIX = "http://";

    static {
        ILLEGAL_CHAR = new HashMap<String, String>();
        ILLEGAL_CHAR.put(" ", "%20");
    }

    /**
     * 产生apk地址
     * 
     * @param relativeUrl
     * @return
     */
    public static String genApkUrl(String relativeUrl) {
        relativeUrl = replaceIllegalChar(relativeUrl);
        if (relativeUrl.startsWith(URL_PREFIX)) {
            return relativeUrl;
        }
        return PathUtil.apkUrlPrefix + relativeUrl;
    }

    /**
     * 产生图片地址
     * 
     * @param relativeUrl
     * @return
     */
    public static String genImgUrl(String relativeUrl) {
        relativeUrl = replaceIllegalChar(relativeUrl);
        if (relativeUrl.startsWith(URL_PREFIX)) {
            return relativeUrl;
        }
        return PathUtil.imgUrlPrefix + relativeUrl;
    }

    /**
     * 客户端下载地址
     * 
     * @param relativeUrl
     * @return
     */
    public static String genClientDownUrl(String relativeUrl) {
        relativeUrl = replaceIllegalChar(relativeUrl);
        if (relativeUrl.startsWith(URL_PREFIX)) {
            return relativeUrl;
        }
        return PathUtil.clientUrlPrefix + relativeUrl;
    }

    /**
     * 替换特殊字符
     * 
     * @param url
     * @return
     */
    public static String replaceIllegalChar(String url) {
        if (url == null || url.trim().length() == 0) {
            return "";
        }
        try {
            Set<String> keys = ILLEGAL_CHAR.keySet();
            for (String key : keys) {
                String value = ILLEGAL_CHAR.get(key);
                url = url.replaceAll(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
}
