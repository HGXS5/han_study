package cn.han.moreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Author han_s
 * @Date 2022/5/16 9:03
 * @ProName maven_test
 */
@Component
public class PartDemo {
    @Autowired
    private Map<String,Object> map;

    public void part() {
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            System.out.println(key+":"+map
            .get(key));
        }

    }

}
