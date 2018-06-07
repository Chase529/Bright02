package com.fh.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Controller
public class WeatherUtil {
    //根据城市获取天气信息的java代码
    //cityName 是你要取得天气信息的城市的中文名字，如“北京”，“深圳”
    public static String  getWeatherInform(String cityName,String key){
        //API
        String weatherUrl = "http://v.juhe.cn/weather/index?cityname=%E8%A5%BF%E5%AE%89&dtype=&format=&key=d9346017e9e849d0eff6ff9a5989d172";
        //String key = "d9346017e9e849d0eff6ff9a5989d172";
        if(StringUtils.isEmpty(key)){
        	key = "d1f8f657b80a11ade49250b39eb7bd48";//默认值
        }
        String cityNameUTF8 = null;
        try {
			 cityNameUTF8 = new String(cityName.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    
        StringBuffer strBuf;
       
            weatherUrl = "http://v.juhe.cn/weather/index?cityname="+cityNameUTF8+"&dtype=&format=&key=" +key;
            System.err.println(weatherUrl);
        strBuf = new StringBuffer();
        try{
            URL url = new URL(weatherUrl);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//转码。
            String line = null;
            while ((line = reader.readLine()) != null)
                strBuf.append(line + " ");
            reader.close();
        }catch(MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        return strBuf.toString();
    }
    
 
    
}


