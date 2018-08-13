package com.core.util.http;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * HTTP工具类
 * @author reborntodie
 */
public class HttpUtils {
    /**
     * http请求
     * @param url 请求地址
     * @param type http请求类型：GET、POST、PUT、DELETE
     * @return
     */
    public String http(String url , String type , JSON json){
        //String paramJson = JsonUtils.toJson(param);
        String paramJson = JSON.toJSONString(json);
        String result = "";
        if(url!=null){
            try {
                URL finalUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) finalUrl.openConnection();
                //http请求设置
                conn.setRequestMethod(type);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setConnectTimeout(5000);
                //json格式上传
                conn.setRequestProperty("Content-Type","application/json");
                conn.setRequestProperty("Accept-Charset","utf-8");
                conn.setRequestProperty("Transfer-Encoding","chunked");
                //设置请求参数
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                //out.writeBytes(paramJson);
                out.write(paramJson.getBytes());
                out.flush();
                out.close();

                int code = conn.getResponseCode();
                if(code == 200){
                    InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    String inputLine = null;
                    while((inputLine = br.readLine())!=null){
                        result += inputLine;
                    }
                    isr.close();
                    conn.disconnect();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
