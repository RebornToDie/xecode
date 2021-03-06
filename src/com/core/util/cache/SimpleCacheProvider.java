package com.core.util.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleCacheProvider implements ICacheProvider{
    /**
     * 存储缓存
     */
    private Map<String , Object> cacheMgr = new ConcurrentHashMap<>();
    /**
     * 键值缓存
     */
    private Map<String , List<String>> keyMap = new ConcurrentHashMap<>();

    /**
     * 创建构造方法
     */
    public SimpleCacheProvider(){

    }

    @Override
    public Object get(String type, String key) {
        return cacheMgr.get(type + key);
    }

    @Override
    public boolean set(String type, String key, Object val) {
        return set(type , key , val , -1);
    }

    @Override
    public boolean set(String type, String key, Object val, int expiry) {
        getKeyList(type).add(key);
        cacheMgr.put(type + key , val);
        return true;
    }

    @Override
    public boolean delete(String type, String key) {
        if(cacheMgr.containsKey(type + key)){
            cacheMgr.remove(type + key);
            return true;
        }
        return false;
    }

    @Override
    public boolean clear(String type) {
        List<String> keyList = getKeyList(type);
        for(String key : keyList){
            cacheMgr.remove(type + key);
        }
        return true;
    }

    @Override
    public boolean exists(String type, String key) {
        return cacheMgr.containsKey(type + key);
    }

    @Override
    public void shutdown() {
        cacheMgr = null;
    }

    @Override
    public int getSize(String type) {
        int count = 0 ;
        if(cacheMgr.containsKey(type)){
            count = this.getKeyList(type).size();
        }
        return count;
    }

    @Override
    public List<String> getKeyList(String type) {
        List<String> keys;
        if(keyMap.containsKey(type)){
            keys = keyMap.get(type);
        }else{
            keys = new ArrayList<String>();
            keyMap.put(type , keys);
        }
        return keys;
    }

    @Override
    public List<Object> getValueList(String type, int offset, int size) {
        List<String> list = getKeyList(type);
        if (offset >= list.size()) {
            return new ArrayList<Object>();
        }
        int len = size + offset;
        if (size <= 0) {
            len = list.size();
        } else if (len >= list.size()) {
            len = list.size();
        }

        List<Object> rtnList = new ArrayList<Object>();
        for (int i = offset; i < len; i++) {
            String key = list.get(i);
            rtnList.add(cacheMgr.get(type + key));
        }
        return rtnList;
    }

    @Override
    public Object getCacheMgr() {
        return cacheMgr;
    }
}
