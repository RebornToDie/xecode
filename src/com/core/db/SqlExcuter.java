package com.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 执行sql接口
 * @author reborntodie
 */
public abstract class SqlExcuter {
    public static ResultSet query(String sql){
        ResultSet rs = excuteSql(sql);
        return rs;
    }

    private static ResultSet excuteSql(String sql){
        ResultSet rs = null;
        try{
            //加载MYSQL JDBC驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Success loading Mysql Driver!");
        }catch(Exception e) {
            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
        }
        try{
            Connection connect = DriverManager.getConnection(
                    //连接URL为jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
                    "jdbc:mysql://39.106.111.11/sinq?characterEncoding=utf-8","root","root");
            System.out.println("Success connect Mysql server!");
            Statement stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.getString("name"));
            }
        }
        catch(Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
        return rs;

    }

}
