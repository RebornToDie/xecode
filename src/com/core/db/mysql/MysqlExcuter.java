package com.core.db.mysql;

import com.core.db.SqlExcuter;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.HashMap;

public class MysqlExcuter extends SqlExcuter {
    //sql执行时的乱码问题:在请求url中设置编码characterEncoding为utf-8
    public static void main(String[] args) throws UnsupportedEncodingException {
        HashMap<String , String> map = new HashMap<>();
        map.put("id","11");
        map.put("name","测试分类1");
        String tableName = "category";
        MysqlBuilder m = new MysqlBuilder();
        String sql = m.select(tableName,map);
        String s = new String(sql.getBytes("utf-8"),"utf-8");
        ResultSet rs = MysqlExcuter.query(s);
        System.out.print(rs);
    }
}
