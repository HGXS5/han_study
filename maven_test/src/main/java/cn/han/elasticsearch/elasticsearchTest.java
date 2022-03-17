package cn.han.elasticsearch;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class elasticsearchTest {



    public String sr = "  \"properties\": {\n" +
            "         \"name\": {\n" +
            "            \"type\": \"text\",\n" +
            "            \"analyzer\": \"ik_max_word\",\n" +
            "            \"search_analyzer\": \"ik_smart\"\n" +
            "         },\n" +
            "         \"description\": {\n" +
            "            \"type\": \"text\",\n" +
            "            \"analyzer\": \"ik_max_word\",\n" +
            "            \"search_analyzer\": \"ik_smart\"\n" +
            "         },\n" +
            "         \"studymodel\": {\n" +
            "            \"type\": \"keyword\"\n" +
            "         },\n" +
            "          \"price\": {\n" +
            "              \"type\": \"float\"\n" +
            "         },\n" +
            "         \"timestamp\": {\n" +
            "              \"type\": \"date\",\n" +
            "              \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"\n" +
            "         }\n" +
            "      }";


}
