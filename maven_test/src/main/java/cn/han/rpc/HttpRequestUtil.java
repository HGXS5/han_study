package cn.han.rpc;



import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;





/**
 * 发起http请求并获取结果
 * 
 * @author zhaomy
 * @date 20161022
 * 
 */
public class HttpRequestUtil {
	
	
	public static JSONObject httpGETRequest(String requestUrl) {
		
		return httpRequest(requestUrl,"GET","");
	}
	
    public static JSONObject httpPOSTRequest(String requestUrl,String reqDataJson) {
		
		return httpRequest(requestUrl,"POST",reqDataJson);
	}
	/**
	 * 发起http请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		InputStream inputStream = null;
		try {
			
			URL url = new URL(requestUrl);
			
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod)){
				httpUrlConn.connect();
			}else{
				//设置请求头里面的数据，以下设置用于解决http请求code415的问题 
				httpUrlConn.setRequestProperty("Content-Type",  
		                    "application/json");
			}
				

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

			jsonObject = JSONObject.fromObject(buffer.toString());
			
		} catch (ConnectException ce) {
			ce.printStackTrace();
			System.out.println("iuser server connection timed out");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("http request error:{}");
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jsonObject;
	}

}
