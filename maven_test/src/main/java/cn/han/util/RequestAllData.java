package cn.han.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;


/**
 * @Author han_s
 * @Date 2022/8/12 14:39
 * @ProName han-rabbitmq
 */
@Component
public class RequestAllData {

    private static final CloseableHttpClient httpClient;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setDefaultMaxPerRoute(10);
        cm.setMaxTotal(200);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }
    /**
     * 原生
     * @return
     */
    public JSONObject requestOriginal(){
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = null;
        try {
            String requestUrl = "http://localhost:8082/han/rabbitmq/request2";
            URL url = new URL(requestUrl);

            HttpURLConnection httpUrlConn = (HttpURLConnection) url
                    .openConnection();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setConnectTimeout(3000);
            httpUrlConn.setReadTimeout(3000);
            // 设置请求方式（GET/POST）
            String requestMethod = "POST";
            httpUrlConn.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod)){
                httpUrlConn.connect();
            }else{
                //设置请求头里面的数据，以下设置用于解决http请求code415的问题
//                httpUrlConn.setRequestProperty("Content-Type",
//                        "application/json");
                httpUrlConn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencode");
            }

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("ids", "1111");
            String outputStr = jsonObject1.toJSONString();
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

            jsonObject = JSONObject.parseObject(buffer.toString());

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

    /**
     * form-data
     */
    public void requestOne() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8082/han/rabbitmq/request2");
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("ids", "1111");
        StringEntity formEntity = new StringEntity(JSON.toJSONString(reqMap), "UTF-8");
        httpPost.setEntity(formEntity);
        StringEntity myEntity = new StringEntity("important message",
                ContentType.create("form-data", "UTF-8"));
        httpPost.setEntity(myEntity);
        CloseableHttpResponse response2 = null;
        try {
            response2 = httpclient.execute(httpPost);
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            EntityUtils.consume(entity2);
        } finally {
            if (response2!=null) {
                response2.close();
            }
        }
    }

    public  Pair<String, Exception> doPost(String url, List<NameValuePair> params, String contentType, String charset) {
        CloseableHttpResponse httpResponse = null;
        HttpPost httpPost = null;

        String responseText = null;
        Exception ex = null;
        try {
//            EntityBuilder eb = EntityBuilder.create();
////            eb.setContentType(ContentType.create(contentType, charset));
//            eb.setContentEncoding(charset);
////            eb.setText(content);
//
//            eb.setParameters(params);
            StringEntity entity = new UrlEncodedFormEntity(params, charset);
            httpPost = new HttpPost(url);
//            httpPost.setEntity(eb.build());
            httpPost.setEntity(entity);
            httpPost.setHeader("content-type",contentType);
            httpResponse = httpClient.execute(httpPost);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() > 300)
                throw new RuntimeException(format("StatusCode: %s", statusLine.getStatusCode()));

            responseText = EntityUtils.toString(httpResponse.getEntity(),"UTF-8").trim();
        } catch (Exception e) {
            ex = e;
        } finally {
            releaseAll(httpResponse, httpPost);
        }

        return Pair.of(responseText, ex);
    }
    private static void releaseAll(Object... releaseable) {
        for (Object obj : releaseable) {
            if (obj == null)
                continue;

            try {
                if (obj instanceof Closeable)
                    ((Closeable) obj).close();

                if (obj instanceof HttpRequestBase)
                    ((HttpRequestBase) obj).releaseConnection();
            } catch (Throwable t) {
            } finally {
                obj = null;
            }
        }
    }
}
