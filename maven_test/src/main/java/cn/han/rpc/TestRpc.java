package cn.han.rpc;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author han_s
 * @Date 2022/7/5 11:19
 * @ProName maven_test
 */
public class TestRpc {
    public static void main(String[] args) {
        String url = "http://localhost:8080/youkuspFileManager/fullInjectTestOne";
        Map<String, Object> params = new HashMap<>();
        String assetIds = "1234,1245,thy";
        params.put("assetIds", assetIds);
        JSONObject jsonObject = HttpRequestUtil.httpRequest(url, "POST", params.toString());
        System.out.println(jsonObject);
    }
}
