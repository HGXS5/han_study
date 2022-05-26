package cn.han.workdemo;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author binarii`<dupp@wasu.com>`
 * @version 1.7
 * @since 2017-06-26 15:19 GMT+8
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class HttpUtil {

    private static final String CHAR_UTF8 = "UTF-8";
    private static int socketTimeOut = Integer.valueOf(PropertyManager.getProperty("httpUtil.socketTimeOut"));
    private static int connectTimeout = Integer.valueOf(PropertyManager.getProperty("httpUtil.connectTimeout"));
    private static int connectionRequestTimeout = Integer.valueOf(PropertyManager.getProperty("httpUtil.connectionRequestTimeout"));
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static HttpClient getHttpClient() {
        return InstanceHolder.HTTP_CLIENT;
    }

    public static String doPost(String url) {
        return doPost(url, "");
    }
    //注入融合专用 需捕获SocketTimeoutException
    public static String doPost(JSONObject jsonObject, String url, String body) {
        HttpPost post = new HttpPost(url);
        Set<String> keys = jsonObject.keySet();
        for (String key: keys){
            post.setHeader(key, jsonObject.getString(key));
        }
        post.setEntity(new StringEntity(body, CHAR_UTF8));
        HttpResponse response = null;
        String responseString;
        try {
            response = getHttpClient().execute(post);
            responseString = EntityUtils.toString(response.getEntity());
            return responseString;
        } catch (SocketTimeoutException e) {
            return "0";
        } catch (Exception e){
            logger.error("postHttp", e);
            return "";
        }finally {
            release(response);
        }
    }

    public static String doPost(String url, String body) {
        HttpPost post = new HttpPost(url);
        post.setEntity(new StringEntity(body, CHAR_UTF8));

        return doPost(post);
    }

    public static String doPost(String url, Map<String, String> params) throws Exception {
        List<NameValuePair> formparams = new ArrayList<>();
        if (Objects.nonNull(params)) {
            Set<Entry<String, String>> entries = params.entrySet();
            for (Entry<String, String> entry : entries) {
                String name = entry.getKey(), value = entry.getValue();
                if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
                    formparams.add(new BasicNameValuePair(name, value));
                }
            }
        }

        HttpPost post = new HttpPost(url);
        post.setEntity(new UrlEncodedFormEntity(formparams, CHAR_UTF8));

        return doPost(post);
    }

    private static String doPost(HttpPost post) {
        HttpResponse response = null;
        String responseString;
        try {
            response = getHttpClient().execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            responseString = EntityUtils.toString(response.getEntity());
            if (statusCode == 200 || statusCode == 400) {
                return responseString;
            } else {
                throw new RuntimeException("Status code: " + statusCode);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            release(response);
        }
    }

    public static boolean download(String url, File targetFile) {
        HttpResponse response = null;
        try {
            response = getHttpClient().execute(new HttpGet(url));
            InputStream inputStream = response.getEntity().getContent();
            FileUtils.copyInputStreamToFile(inputStream, targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            release(response);
        }
        return true;
    }

    public static String doGetWithHttpContext(String url, HttpContext context) {
        return doGetWithHttpContext(url, null, context);
    }

    public static String doGetWithHttpContext(String url, Map<String, String> headers,
                                              HttpContext context) {

        HttpResponse response = null;
        long startTime = System.currentTimeMillis();
        String responseString;
        try {
            HttpGet httpGet = new HttpGet(url);
            if (Objects.nonNull(headers)) {
                Set<Entry<String, String>> entries = headers.entrySet();
                for (Entry<String, String> entry : entries) {
                    String name = entry.getKey(), value = entry.getValue();
                    if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
                        httpGet.setHeader(name, value);
                    }
                }
            }

            response = getHttpClient().execute(httpGet, context);
            responseString = EntityUtils.toString(response.getEntity());

            return responseString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            logger.info("runtime:" + (System.currentTimeMillis() - startTime) + ";\trequest:" + url);
            release(response);
        }
    }

    public static HttpContext createHttpContext(String ip, int port, String userName,
                                                String password) {

        HttpHost targetHost = new HttpHost(ip, port, "http");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                new UsernamePasswordCredentials(userName, password));
        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);

        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);

        return context;
    }

    public static String doGet(String url) {
        return doGet(url, null, null);
    }

    public static String doGet(String url, Map<String, String> params) {
        return doGet(url, params, null);
    }

    public static String doGet(String url, Map<String, String> params,
                               Map<String, Object> headers) {

        long startTime = System.currentTimeMillis();
        HttpClient httpClient = getHttpClient();
        HttpResponse response = null;
        try {
            if (params != null && !params.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (Entry<String, String> e : params.entrySet()) {
                    sb.append(e.getKey()).append("=").append(URLEncoder.encode(
                            StringUtils.defaultString(e.getValue(), ""), CHAR_UTF8));
                    sb.append("&");
                }
                sb.substring(0, sb.length() - 1);
                url = url + "?" + sb.toString();
            }

            HttpGet httpGet = new HttpGet(url);
            if (headers != null && !headers.isEmpty()) {
                setHeaders(headers, httpGet);
            }

            response = httpClient.execute(httpGet);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //logger.info("runtime:" + (System.currentTimeMillis() - startTime) + ";\trequest:" + url);
            release(response);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private static void setHeaders(Map<String, Object> headers, HttpRequest request) {
        for (Entry<String, Object> entry : headers.entrySet()) {
            if (!entry.getKey().equals("Cookie")) {
                request.addHeader(entry.getKey(), (String) entry.getValue());
            } else {
                Map<String, Object> cookies = (Map<String, Object>) entry.getValue();
                for (Entry<String, Object> entry1 : cookies.entrySet()) {
                    request.addHeader(new BasicHeader("Cookie", (String) entry1.getValue()));
                }
            }
        }
    }

    public static Map<String, String> getCookie(String url) {
        HttpClient httpClient = getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            Header[] headers = response.getAllHeaders();
            Map<String, String> cookies = new HashMap<>();
            for (Header header : headers) {
                cookies.put(header.getName(), header.getValue());
            }
            return cookies;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(response);
        }

        return null;
    }

    @SuppressWarnings("UnusedAssignment")
    private static void release(HttpResponse httpResponse) {
        try {
            if (httpResponse instanceof Closeable) {
                ((Closeable) httpResponse).close();
            }
        } catch (Throwable t) {
            // ignore
        } finally {
            httpResponse = null;
        }
    }

    // Use static inner class to hold the instance for lazy load
    private static class InstanceHolder {

        private static final HttpClient HTTP_CLIENT;

        static {
            // @formatter:off
            PoolingHttpClientConnectionManager clientConnectionManager =
                    new PoolingHttpClientConnectionManager();
            clientConnectionManager.setDefaultMaxPerRoute(20);
            clientConnectionManager.setMaxTotal(200);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(connectionRequestTimeout)
                    .setConnectTimeout(connectTimeout)
                    .setSocketTimeout(socketTimeOut)
                    .build();

            HttpRequestRetryHandler retryHandler = (exception, executionCount, context) -> {
                if (executionCount > 3) {
                    return false;
                }

                // @formatter:off
                return exception instanceof ConnectTimeoutException ||
                        exception instanceof NoHttpResponseException;
                // @formatter:on
            };

            HTTP_CLIENT = HttpClients.custom()
                    .setConnectionManager(clientConnectionManager)
                    .setSSLSocketFactory(buildSSLConnectionSocketFactory())
                    .setDefaultRequestConfig(requestConfig)
                    .setRetryHandler(retryHandler)
                    .setProxy(getHttpProxy())
                    .useSystemProperties()
                    .build();
            // @formatter:on
        }

        private static SSLConnectionSocketFactory buildSSLConnectionSocketFactory() {
            try {
                SSLContext sslContext = SSLContexts.custom()
                        .loadTrustMaterial((chain, authType) -> true)
                        .build();

                return new SSLConnectionSocketFactory(sslContext,
                        NoopHostnameVerifier.INSTANCE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private static HttpHost getHttpProxy() {
            String proxy = System.getProperty("http.proxy");
            if (StringUtils.isNotBlank(proxy)) {
                return HttpHost.create(proxy);
            }

            return null;
        }

    }

}
