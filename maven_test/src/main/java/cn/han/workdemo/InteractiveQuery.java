package cn.han.workdemo;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 本类中全部都是对接其他系统的请求接口
 * Interactive
 */
public class InteractiveQuery {

	private static Logger log = LoggerFactory.getLogger(InteractiveQuery.class);

	/**
	 * 融合门户-获取华数ID
	 * @return
	 * {
	 * "success": true,
	 * "data": "CP22010020200817000058"
	 * }
	 */
	public static JSONObject getWasuId(){
		String url = PropertyManager.getProperty("sopplus_id_url") + "/getWasuIDForHotfolder";
		String resultText = HttpClientPools.httpGetRequest(url);
		return JSONObject.parseObject(resultText);
	}

	/**
	 * 融合门户-创建资料集等
	 * @param body
	 * @return
	 */
	public static String injectionContents(String body){
		GuidUtils guidUtils = new GuidUtils(true);
		String sysId = guidUtils.valueAfterMD5;
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("Content-Type","application/json");
		jsonObject.put("systemId",PropertyManager.getProperty("sopplus_systemId"));
		jsonObject.put("MessageId",sysId);
		String availIp = NetworkUtils.getAvailIp();
		log.info("注入融合回调地址,获取到的本地ip为:{}",availIp);
		jsonObject.put("Replyto","http://"+availIp+":8080/cp-node/sopPlus/injectCallBack");

		String url = PropertyManager.getProperty("sopplus_url");
		String resultText = HttpUtil.doPost(jsonObject,url,body);
		log.info("返回值={}",resultText);
		return resultText;
	}

	/**
	 * 融合门户-获取分类信息
	 * @param type type=1 通过分类信息name获取code;type=2 通过分类信息code获取name
	 * @param categoryInfos
	 * @return
	 */
//	public static JSONObject getCategory(int type,List<CategoryInfo> categoryInfos){
//		String url = PropertyManager.getProperty("sopplus_id_url") + "/getcategory";
//		JSONObject jsonObject =new JSONObject();
//		jsonObject.put("Content-Type","application/json");
//		jsonObject.put("Type",type);
//		jsonObject.put("CategoryInfos",categoryInfos);
//
//		String resultText = HttpClientUtil.doPost(jsonObject, url, jsonObject.toString());
//		return JSONObject.parseObject(resultText);
//	}
//
//	/**
//	 * 新华社-获取订购线路
//	 * @return
//	 */
//	public static String getCid(String body){
//		String url = PropertyManager.getProperty("xnAgency_cid_url");
//		log.info("去新华社请求线路列表。。。请求url为：{}，body为：{}",url,body);
//		String resultTest = HttpClientUtil.postHttpIc(url, body);
//		log.info("新华社线路列表的响应为。。。{}",resultTest);
//		return resultTest;
//	}
//
//	/**
//	 * 新华社-获取稿件列表
//	 * @return
//	 */
//	public static String getNewsList(String body){
//		String url = PropertyManager.getProperty("xnAgency_list_url");
//		log.info("根据线路列表获取稿件列表。。。。。请求url为：{}，body为：{}",url,body);
//		String resultTest = HttpClientUtil.postHttpIc(url, body);
//		log.info("新华社稿件列表的响应为。。。。。。{}",resultTest);
//		return resultTest;
//	}
//
//	/**
//	 * 新华社-获取稿件详情
//	 * @return
//	 */
//	public static String getNewDetail(String body){
//		String url = PropertyManager.getProperty("xnAgency_info_url");
//		String resultTest = HttpClientUtil.postHttpIc(url, body);
//		return resultTest;
//	}
//
//	public static void main(String[] args) {
////		injectionContents("");
//
//		String appId = "gNDy3x1xEbGEC6w4uGz1PxjHQizS5mYD";
//		String appKey = "8DV9A7IdIdYxuqElSaH3UzIWX47OLzHVxqZZWDywPjC47SNxAAPSdLQRVS9rV2Ra";
//		String sessionKey = "81hpyeyBMHmTet6f33yi646PmA1ta1slfaf9JpMG3efZk3wX87ds5jB7fE8IGANJ";
//		String timestamp = String.valueOf(System.currentTimeMillis());
//		String signBody = "appId="+appId+"&appKey="+appKey+"&sessionKey="+sessionKey+"&timestamp="+timestamp;
//		String sign = Sha1EncodeUtils.shaEncode(signBody);
//
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("appId", appId);
//		jsonObject.put("appKey", appKey);
//		jsonObject.put("timestamp", timestamp);
//		jsonObject.put("sign", sign);
//
//		String cidResult = getCid(jsonObject.toJSONString());
//		List<XinhuaCidInfo> cidList = JSONArray.parseArray(JSONObject.parseObject(cidResult).get("productCategoryId").toString(), XinhuaCidInfo.class);
//		//定义获取稿件列表的list
//		List<String> pCidList = new ArrayList<>();
//		for (XinhuaCidInfo xinhuaCidInfo : cidList){
//			pCidList.clear();
//			pCidList.add(xinhuaCidInfo.getCid());
//			jsonObject.put("productCategoryId", pCidList);
//			System.out.println(jsonObject.getString("productCategoryId"));
//			String result = getNewsList(jsonObject.toJSONString());
//		}

//		List<String> docList = new ArrayList<>();
//		List<XinhuaNewsListInfo> newsList = JSONArray.parseArray(JSONObject.parseObject(result).get("news").toString(), XinhuaNewsListInfo.class);
//		for (XinhuaNewsListInfo xinhuaNewsListInfo: newsList){
//			docList.add(xinhuaNewsListInfo.getDocId());
//		}
//
//		System.out.println("docId集合的数量：" + docList.size());
//
//		jsonObject.put("searchAfterId", resultJson.get("searchAfterId"));
//		String secResult = getNewsList(jsonObject.toJSONString());
//		System.out.println(secResult);

//		//获取详情
//		jsonObject.put("docId", "101002020123000002167");
//		JSONObject detailStr = JSONObject.parseObject(getNewDetail(jsonObject.toJSONString()));
//		JSONArray attacList = detailStr.getJSONArray("attachs");
//		System.out.println(JSONObject.parseObject(attacList.get(0).toString()).getString("downUrl"));

//		XhAgencyClient xhAgencyClient = new XhAgencyClient();
//		xhAgencyClient.startMethod();

//	}
}
