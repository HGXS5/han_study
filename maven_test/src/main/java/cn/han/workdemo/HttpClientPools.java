package cn.han.workdemo;



import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zhongbo on 2019/1/15.
 */
public class HttpClientPools {
    private static Logger log = Logger.getLogger(HttpClientPools.class);

    private static String EMPTY_STR = "";
    private static String UTF_8 = "UTF-8";
    private static CloseableHttpClient httpClient;
    private static int max_connections = 2000;
    private static int default_maxRoute = 1000;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        int socketTimeOut = Integer.valueOf(PropertyManager.getProperty("http.socketTimeOut"));
        int connectTimeout = Integer.valueOf(PropertyManager.getProperty("http.connectTimeout"));
        int connectionRequestTimeout = Integer.valueOf(PropertyManager.getProperty("http.connectionRequestTimeout"));
            // 整个连接池最大连接数
            cm.setMaxTotal(max_connections);
            // 每路由最大连接数，默认值是1000
            cm.setDefaultMaxPerRoute(default_maxRoute);
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeOut)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setStaleConnectionCheckEnabled(true)
                .build();
        httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(defaultRequestConfig).build();
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * @param url
     * @return
     */
    public static String httpGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    /**
     *
     * @param header
     * @param url
     * @return
     */
    public static String getWithHeader(Header header,String url) {
        HttpGet httpGet = new HttpGet(url);
        return getResult(header, httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);
        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws URISyntaxException {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);
        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        return getResult(httpGet);
    }

    public static String httpPostRequest(String url) {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url,String json) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json,"utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

        return getResult(httpPost);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return pairs;
    }

    /**
     * 处理Http请求
     */
    private static String getResult(HttpRequestBase request) {
        long start = System.currentTimeMillis();
        CloseableHttpClient httpClient = getHttpClient();
        try {

            CloseableHttpResponse response = httpClient.execute(request);
            // response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // long len = entity.getContentLength();// -1 表示长度未知
                String result = EntityUtils.toString(entity);
                response.close();
                // httpClient.close();
                return result;
            }
        } catch (Exception e) {
            log.error("HttpClientPools------------>" + e.getMessage());
        } finally {
            log.info("runtime:" + (System.currentTimeMillis() - start) + ";\trequest:" + request.getURI());
        }
        return EMPTY_STR;
    }

    /**
     * 处理需要添加header的Http请求
     */
    private static String getResult(Header header, HttpRequestBase request) {
        long start = System.currentTimeMillis();
        CloseableHttpClient httpClient = getHttpClient();
        try {

            CloseableHttpResponse response = httpClient.execute(request);
            // response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // long len = entity.getContentLength();// -1 表示长度未知
                String result = EntityUtils.toString(entity);
                response.close();
                // httpClient.close();
                return result;
            }
        } catch (Exception e) {
            log.error("HttpClientPools------------>" + e.getMessage());
        } finally {
            log.info("runtime:" + (System.currentTimeMillis() - start) + ";\trequest:" + request.getURI());
        }
        return EMPTY_STR;
    }
}
