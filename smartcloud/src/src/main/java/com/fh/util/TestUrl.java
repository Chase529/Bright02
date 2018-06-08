package com.fh.util;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class TestUrl {

//    public static void main(String[] args) {
//        String url = "http://192.168.199.134:8080/appHomeCarousel/getHomeCarouselList?uid=1&status=0";
//        System.out.println("URL：" + url);
//        StringBuffer json = new StringBuffer();
//        try {
//            //实例一个url和URLConnection
//            URL oracle = new URL(url);
//            //打开链接
//            URLConnection yc = oracle.openConnection();
//            //输入流作参数传进InputStreamReader并用BufferedReader接受
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    yc.getInputStream()));
//            String inputLine = null;
//            //一直读到空，并设置流编码是UTF8
//            while ((inputLine = in.readLine()) != null) {
//                json.append(new String(inputLine.getBytes(), "UTF-8"));
//            }
//            //记得关闭连接
//            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(json);
//    }


}
