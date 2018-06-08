package com.fh.util;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Get请求访问url地址上的接口，获取Json格式返回数据
 */
public class HttpGetUtil {

    /**
     *
     * @param url
     * @param param  map<key,value>
     * @return
     */
    @SuppressWarnings("unchecked")//忽略异常
    public static JSONObject getJsonFromUrl(String url, Map<String, String> param) {
        String reqUrl = url;
        if (null!=param){
            //拼接请求参数
            reqUrl = reqUrl + "?";
            for(Map.Entry<String, String> each : param.entrySet()){
                reqUrl += each.getKey() + "=" + each.getValue();
                reqUrl += "&";
            }
            reqUrl = reqUrl.substring(0, reqUrl.length() - 1);
        }
        System.out.println(reqUrl);
        StringBuffer json = new StringBuffer();
        try {
            URL u = new URL(reqUrl);
            URLConnection conn = u.openConnection();
            conn.setConnectTimeout(1000);//请求超时处理1.0秒
            conn.setReadTimeout(1000);//容许连接后的处理时间1.0秒(服务器连接后，请求需要在1.0秒内返回)
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(new String(inputLine.getBytes(), "UTF-8"));
            }
            //记得关闭连接
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(json);
        JSONObject obj= JSON.parseObject(json.toString());
        return obj;
    }


//
//
//    public static void main(String[] args) {
//        Map<String, String> p = new HashMap<>();
//        p.put("uid", "1");
//        p.put("status", "0");
//        System.out.println(HttpGetUtil.getJsonFromUrl("http://192.168.199.134:8080/appHomeCarousel/getHomeCarouselList", p).toString());
//
//    }


}
