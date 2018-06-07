package com.fh.core;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil 
{
	final static protected String sPrivateKey = "boyu100.com";
	
	final static public String INVALID_REQUEST = "非法请求";
	
	final static public String AUTH_USER = "Boyu";
	
	final static public String AUTH_PWD = "Boyu123456";
	
	/**
	 * 校验请求是否合法
	 *                       
	 * @Filename: ReqValidateUtil.java
	 * @Version: 1.0
	 * @Author: boyu
	 * @Email: 
	 *
	 */
	public static Boolean validate(HttpServletRequest request)
	{
		//获取请求中的参数信息checksum=md5(uid+ts+sPrivateKey)
		
		String key = request.getParameter("key");
		if(StringUtil.isEmpty(request.getParameter("key"))){
			key = "";
		}
		
		String uid = request.getParameter("uid");
		if(StringUtil.isEmpty(request.getParameter("uid"))){
			uid = "";
		}
		
		String ts = request.getParameter("ts");
		if(StringUtil.isEmpty(request.getParameter("ts"))){
			ts = "";
		}
		
		String nChecksum = Md5.getMd5String(uid.concat(ts).concat(sPrivateKey));

		if (nChecksum.equals(key))
			return true;
		else return false;
	}
	
	public static String getLang(HttpServletRequest request)
	{
		return request.getParameter("lang");
	}
	
	public static String covertMapToParamStr(Map<String, String> map) {
		String str = "";
		
		Entry<String, String> obj = null;
		
		for (Iterator<?> it = map.entrySet().iterator(); it.hasNext();) {
            obj = (Entry<String, String>) it.next();
            String value = obj.getValue();
            if (!StringUtil.isEmpty(value)) {
                // 对value值进行去除前后空处理
                str += obj.getKey() + "=" + value + "&";
            }
        }
		
		return str;
	}
}
