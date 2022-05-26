package cn.han.workdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;


/**
 * @Author han_s
 * @Date 2022/4/21 14:35
 * @ProName maven_test
 */

public class TestEnitity {


    public static void main(String[] args) {
        CyxClient cyxClient = new CyxClient();
        File file = new File("D:\\huashu\\artStar_3455.imp");
//        cyxClient.readFileImd(file);
        cyxClient.startMethod();

    }
}
