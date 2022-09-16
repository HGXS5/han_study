package cn.han.rpc;

import cn.han.util.HttpUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.tuple.Pair;

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
//        Map<String, Object> params = new HashMap<>();
//        String assetIds = "1234,1245,thy";
//        params.put("assetIds", assetIds);
//        JSONObject jsonObject = HttpRequestUtil.httpRequest(url, "POST", params.toString());
//        System.out.println(jsonObject);
        Pair<String, Exception> pair = null;
        try {
            pair = HttpUtil.doPost(url, "111", "text/plain", "utf-8", 5000, 5000, 5000);
            System.out.println(pair.getValue());
            System.out.println(pair.getRight());
            System.out.println(pair.getLeft());
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

//        System.out.println(pair.getKey());
    }
}
