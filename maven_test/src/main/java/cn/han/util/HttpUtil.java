package cn.han.util;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static java.lang.String.format;

public class HttpUtil {

	private static final String DEFAULT_CONTENT_TYPE = "text/plain";
	private static final String DEFAULT_CHARSET = "utf-8";

	private static CloseableHttpClient httpClient;

	static {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setDefaultMaxPerRoute(10);
		cm.setMaxTotal(200);
		httpClient = HttpClients.custom().setConnectionManager(cm).build();
	}

	public static String get(String url) throws Exception {
		Pair<String, Exception> pair = doGet(new URI(url));
		if (pair.getRight() != null)
			throw pair.getRight();

		return pair.getLeft();
	}
	public static Pair<String, Exception> doPost(String url, String content, String contentType, String charset,int socketTimeout,int connectTimeout,int requestTimeout) {
		CloseableHttpResponse httpResponse = null;
		HttpPost httpPost = null;

		String responseText = null;
		Exception ex = null;
		try {
			EntityBuilder eb = EntityBuilder.create();
			eb.setContentType(ContentType.create(contentType, charset));
			eb.setContentEncoding(charset);
			eb.setText(content);
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(socketTimeout)
					.setConnectTimeout(connectTimeout)
					.setConnectionRequestTimeout(requestTimeout).build();
			httpPost = new HttpPost(url);
			httpPost.setEntity(eb.build());
			httpPost.setConfig(requestConfig);
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

	public static String get(String url, Map<String, String> params) throws Exception {
		URIBuilder ub = new URIBuilder();
		params.forEach((key, value) -> {
			ub.setParameter(key, value);
		});

		Pair<String, Exception> pair = doGet(ub.build());
		if (pair.getRight() != null)
			throw pair.getRight();

		return pair.getLeft();
	}

	public static void get(String url, ResponseHandler handler) {
		String responseText = null;
		Exception ex = null;
		try {
			Pair<String, Exception> pair = doGet(new URI(url));
			responseText = pair.getLeft();
			ex = pair.getRight();
		} catch (URISyntaxException e) {
			ex = e;
		}

		handler.handle(responseText, ex);
	}

	public static void get(String url, Map<String, String> params, ResponseHandler handler) {
		String responseText = null;
		Exception ex = null;
		try {
			URIBuilder ub = new URIBuilder();
			params.forEach((key, value) -> {
				ub.setParameter(key, value);
			});
			Pair<String, Exception> pair = doGet(ub.build());
			responseText = pair.getLeft();
			ex = pair.getRight();
		} catch (URISyntaxException e) {
			ex = e;
		}

		handler.handle(responseText, ex);
	}

	private static Pair<String, Exception> doGet(URI uri) {
		CloseableHttpResponse httpResponse = null;
		HttpGet httpGet = null;

		String responseText = null;
		Exception ex = null;
		try {
			httpResponse = httpClient.execute(httpGet = new HttpGet(uri));
			StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine.getStatusCode() > 300)
				throw new RuntimeException(format("StatusCode: ", statusLine.getStatusCode()));

			responseText = EntityUtils.toString(httpResponse.getEntity()).trim();
		} catch (Exception t) {
			ex = t;
		} finally {
			releaseAll(httpResponse, httpGet);
		}

		return Pair.of(responseText, ex);
	}

	public static String postXml(String url, String xml) throws Exception {
		return postXml(url, xml, DEFAULT_CHARSET);
	}

	public static String postXml(String url, String xml, String charset) throws Exception {
		Pair<String, Exception> pair = doPost(url, xml, "text/xml", charset);
		if (pair.getRight() != null)
			throw pair.getRight();

		return pair.getLeft();
	}

	public static void postXml(String url, String xml, ResponseHandler handler) {
		postXml(url, xml, DEFAULT_CHARSET, handler);
	}

	public static void postXml(String url, String xml, String charset, ResponseHandler handler) {
		Pair<String, Exception> pair = doPost(url, xml, "text/xml", charset);
		handler.handle(pair.getLeft(), pair.getRight());
	}

	public static String postJson(String url, String json) throws Exception {
		return postJson(url, json, DEFAULT_CHARSET);
	}

	public static String postJson(String url, String json, String charset) throws Exception {
		Pair<String, Exception> pair = doPost(url, json, "application/json", charset);
		if (pair.getRight() != null)
			throw pair.getRight();

		return pair.getLeft();
	}

	public static void postJson(String url, String json, ResponseHandler handler) {
		postJson(url, json, DEFAULT_CHARSET, handler);
	}

	public static void postJson(String url, String json, String charset, ResponseHandler handler) {
		Pair<String, Exception> pair = doPost(url, json, "application/json", charset);
		handler.handle(pair.getLeft(), pair.getRight());
	}

	public static String post(String url, String content) throws Exception {
		return post(url, content, DEFAULT_CONTENT_TYPE);
	}

	public static String post(String url, String content, String contentType) throws Exception {
		return post(url, content, contentType, DEFAULT_CHARSET);
	}

	public static String post(String url, String content, String contentType, String charset) throws Exception {
		Pair<String, Exception> pair = doPost(url, content, contentType, charset);
		if (pair.getRight() != null)
			throw pair.getRight();

		return pair.getLeft();
	}

	public static void post(String url, String content, ResponseHandler handler) {
		post(url, content, DEFAULT_CONTENT_TYPE, handler);
	}

	public static void post(String url, String content, String contentType, ResponseHandler handler) {
		post(url, content, contentType, DEFAULT_CHARSET, handler);
	}

	public static void post(String url, String content, String contentType, String charset, ResponseHandler handler) {
		Pair<String, Exception> pair = doPost(url, content, contentType, charset);
		handler.handle(pair.getLeft(), pair.getRight());
	}

	private static Pair<String, Exception> doPost(String url, String content, String contentType, String charset) {
		CloseableHttpResponse httpResponse = null;
		HttpPost httpPost = null;

		String responseText = null;
		Exception ex = null;
		try {
			EntityBuilder eb = EntityBuilder.create();
			eb.setContentType(ContentType.create(contentType, charset));
			eb.setContentEncoding(charset);
			eb.setText(content);

			httpPost = new HttpPost(url);
			httpPost.setEntity(eb.build());

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

	@FunctionalInterface
	public static interface ResponseHandler {
		void handle(String response, Throwable ex);
	}
public static void main(String[] args) {
	String json = "{\"providerId\":\"" + 1 + "\"," + "\"batchNo\":\"" + 1 + "\""
			+ "\"result\":\"" + 1 + "\"" + "\"message\":\"" + 1 + "\""
			+ "}";
	try {
		String postJson = postJson("http://localhost:8080/cp-node/assetTTTest", json);
		System.out.println(postJson);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
