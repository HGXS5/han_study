package cn.han.mysql;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlTest {
    @Test
    public void test(){
        String url = "jdbc:mysql://47.100.77.11:3306/test";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root",
                    "Han#222");
        if (connection!=null){
            System.out.println("连接成功");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

}
