package com.manager.admin.common.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * @see 在开发HTTPS应用时，时常会遇到两种情况
 * @see 1、要么测试服务器没有有效的SSL证书,客户端连接时就会抛异常
 * @see javax.net.ssl.SSLPeerUnverifiedException: peer not authenticated
 * @see 2、要么测试服务器有SSL证书,但可能由于各种不知名的原因,它还是会抛一堆烂码七糟的异常
 * @see 
 *      ==========================================================================
 *      ===========================
 * @see 由于我们这里使用的是HttpComponents-Client-4.1.2创建的连接，所以，我们就要告诉它使用一个不同的TrustManager
 * @see TrustManager是一个用于检查给定的证书是否有效的类
 * @see SSL使用的模式是X.509....对于该模式,Java有一个特定的TrustManager,称为X509TrustManager
 * @see 所以我们自己创建一个X509TrustManager实例
 * @see 而在X509TrustManager实例中
 *      ，若证书无效，那么TrustManager在它的checkXXX()方法中将抛出CertificateException
 * @see 既然我们要接受所有的证书,那么X509TrustManager里面的方法体中不抛出异常就行了
 * @see 然后创建一个SSLContext并使用X509TrustManager实例来初始化之
 * @see 接着通过SSLContext创建SSLSocketFactory，最后将SSLSocketFactory注册给HttpClient就可以了
 */
public class HttpClientUtil {
    
    public static String CONTENT_TYPE_DEFAULT = "application/octet-stream; charset=UTF-8";
    
    public static String CONTENT_TYPE_TEXT = "application/text; charset=UTF-8";
    
    public static String CONTENT_TYPE_XML = "application/xml; charset=UTF-8";
    
    public static String CONTENT_TYPE_JSON = "application/json; charset=UTF-8";
    
    public static String CONTENT_TYPE_FORM = "application/x-www.form-urlencoded";

    /**
     * 向HTTPS地址发送POST请求
     * 
     * @see 该方法会自动关闭连接,释放资源
     * @param reqURL
     *            请求地址
     * @param params
     *            请求参数
     * @return 响应内容
     */
    public static String sendSSLPost(String reqURL, String params) {
        String responseContent = ""; // 响应内容
        HttpClient httpClient = new DefaultHttpClient(); // 创建默认的httpClient实例
        X509TrustManager xtm = new X509TrustManager() { // 创建TrustManager
            public void checkClientTrusted(X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        try {
            // TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");

            // 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[] { xtm }, null);

            // 创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);

            // 通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
            httpClient.getConnectionManager().getSchemeRegistry()
                    .register(new Scheme("https", 443, socketFactory));

            HttpPost httpPost = new HttpPost(reqURL); // 创建HttpPost
            httpPost.addHeader("Content-Type", CONTENT_TYPE_XML);
            httpPost.setEntity(new StringEntity (params)); 
            
            HttpResponse response = httpClient.execute(httpPost); // 执行POST请求
            HttpEntity entity = response.getEntity(); // 获取响应实体

            if (null != entity) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity); // Consume response content
            }
            
            System.out.println("请求地址: " + httpPost.getURI());
            System.out.println("请求数据: " + params);
            System.out.println("响应状态: " + response.getStatusLine());
            System.out.println("响应内容: " + responseContent);
            
            StringBuffer sb = new StringBuffer();
            sb.append("请求地址: ").append(httpPost.getURI()).append("/r/n");
            sb.append("请求数据: ").append(params).append("/r/n");
            sb.append("响应状态: ").append(response.getStatusLine()).append("/r/n");
            sb.append("响应内容: ").append(responseContent).append("/r/n");
            
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown(); // 关闭连接,释放资源
        }
        return responseContent;
    }
    
    public static String post(String reqURL, String params) {
        return post(reqURL, params, null);
    }
    
    public static String post(String reqURL, String params, String contentType) {
        String responseContent = ""; // 响应内容
        HttpClient httpClient = new DefaultHttpClient(); // 创建默认的httpClient实例
        try {
            HttpPost httpPost = new HttpPost(reqURL); // 创建HttpPost
            if(contentType != null && contentType.length() > 0){
                httpPost.addHeader("Content-Type", contentType);
            }
            httpPost.setEntity(new StringEntity(params, "UTF-8")); 
            
            HttpResponse response = httpClient.execute(httpPost); // 执行POST请求
            HttpEntity entity = response.getEntity(); // 获取响应实体

            if (null != entity) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity); // Consume response content
            }
            System.out.println("请求地址: " + httpPost.getURI());
            System.out.println("请求数据: " + params);
            System.out.println("响应状态: " + response.getStatusLine());
            System.out.println("响应内容: " + responseContent);
            
            StringBuffer sb = new StringBuffer();
            sb.append("请求地址: ").append(httpPost.getURI()).append("/r/n");
            sb.append("请求数据: ").append(params).append("/r/n");
            sb.append("响应状态: ").append(response.getStatusLine()).append("/r/n");
            sb.append("响应内容: ").append(responseContent).append("/r/n");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown(); // 关闭连接,释放资源
        }
        return responseContent;
    }
    public static String getSpiderInfo(String urlText,String requestMethod,String textEncoding){
		StringBuffer infoText=new StringBuffer();
		HttpURLConnection httpUrlCon = null;
		DataInputStream dis = null;
		try {
			URL url=new URL(urlText);
			httpUrlCon=(HttpURLConnection) url.openConnection();
			httpUrlCon.setRequestMethod(requestMethod);
			
			httpUrlCon.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)");
			httpUrlCon.setRequestProperty("Accept", "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, text/vnd.wap.wml,*/*");
			httpUrlCon.setRequestProperty("Accept-Language", "zh-cn");
			httpUrlCon.setRequestProperty("UA-CPU", "x86");
			httpUrlCon.setRequestProperty("Content-type", "text/html");
			httpUrlCon.setRequestProperty("Content-Encoding",textEncoding);
			httpUrlCon.setRequestProperty("Connection", "close");
			
			httpUrlCon.setUseCaches(false);
			httpUrlCon.setConnectTimeout(30000);
			httpUrlCon.setReadTimeout(15000);
			httpUrlCon.setDoOutput(true);
			httpUrlCon.setDoInput(true);
			httpUrlCon.connect();
			
			dis=new DataInputStream(httpUrlCon.getInputStream());
			String info=dis.readLine();
			while(info!=null){
				info=new String(info.getBytes("ISO-8859-1"),textEncoding);
				infoText.append(info+"\n");
				info=dis.readLine();
			}
		}catch (SocketTimeoutException e) {
		} catch (IOException e) {
		}finally {
			try {
				if(null != dis){
					dis.close();
				}
				httpUrlCon.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return infoText.toString();
	}
    public static String getUrlText(String url) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
        HttpResponse response = httpClient.execute(httpGet);
        String result;
			result = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
			System.out.println(result);
		} catch (Exception e) {
		}
        httpClient.getConnectionManager().shutdown();
		return url;
     }
    
    public static void main(String[] args) {
			String text=getSpiderInfo("http://www.ip138.com/ips138.asp?ip=14.17.77.216","GET","GBK");
			String format="<li>本站主数据：(.*?)</li>";
			Pattern pattern = Pattern.compile(format);
			Matcher matcher = pattern.matcher(text);
			if(matcher.find()){
				System.out.println(matcher.group(1));
			}
    }
}