package com.core.db;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 生成sql语句的接口类
 * @author reborntodie
 */
public abstract class SqlBuilder {
    /**
     * select 查询语句拼接
     * @param tableName
     * @param sqlBean
     * @return
     */
    public String select (String tableName , HashMap<String ,String> sqlBean){
        StringBuilder sb = new StringBuilder("select * ");
        sb.append("from ").append(tableName).append(" where 1 = 1 ");
        //查询关键字
        Set<Map.Entry<String , String >> set=sqlBean.entrySet();
        Iterator<Map.Entry<String , String >> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String , String> me = it.next();
            String key = me.getKey();
            String value = me.getValue();
            sb.append(" and ").append(key).append("=")
                    .append("'").append(value).append("'");
        }
        return sb.toString();
    }

}
