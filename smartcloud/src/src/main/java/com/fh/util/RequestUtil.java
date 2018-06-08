package com.fh.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class RequestUtil {
	
	public static String getValue(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		PageData pd = new PageData(request);
		
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Map.Entry entry; 
		String name = "";  
		String value = "";  
		
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next(); 
			name = (String) entry.getKey(); 
		}
		return name;
	}

	@SuppressWarnings("rawtypes")
	public static PageData linkedHashMapCovertToHashMap(LinkedHashMap<String, Object> map) {
		PageData pd = new PageData();
		
		Iterator iter = map.keySet().iterator();
		while(iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			
			String key = entry.getKey().toString();
			String val = entry.getValue().toString();
			
			pd.put(key, val);
		}
		
		return pd;
	}
	
	//转换JSON对象为hashmap
	@SuppressWarnings({ "rawtypes"})
	public static PageData covertJsonToHashMap(JSONObject json) {
		PageData pd = new PageData();
		
		Iterator iter = json.keySet().iterator();
		while(iter.hasNext()) {
			String key = (String)iter.next();
			if(key.equals("") || key == null) {
				continue;
			}
			
			if(json.get(key) instanceof JSONArray) {
				List<PageData> detailList = new ArrayList<PageData>();
				JSONArray array = json.getJSONArray(key);
				for(int i = 0; i < array.size(); i++) {
					detailList.add(covertJsonToHashMap(array.getJSONObject(i)));
				}
				pd.put(key.toUpperCase(), detailList);
			} else if(json.get(key) instanceof JSONObject) {
				pd.put(key.toUpperCase(), covertJsonToHashMap(json.getJSONObject(key)));
			} else {
				pd.put(key.toUpperCase(), json.getString(key));
			}
		}
		return pd;
	}
	
	//转换JSON对象为hashmap
	@SuppressWarnings({ "rawtypes"})
	public static PageData covertJsonToHashMap2(JSONObject json) {
		PageData pd = new PageData();
		
		Iterator iter = json.keySet().iterator();
		while(iter.hasNext()) {
			String key = (String)iter.next();
			if(key.equals("") || key == null) {
				continue;
			}
			
			if(json.get(key) instanceof ArrayList) {
				List<PageData> detailList = new ArrayList<PageData>();
				JSONArray array = json.getJSONArray(key);
				for(int i = 0; i < array.size(); i++) {
					detailList.add(covertJsonToHashMap2(array.getJSONObject(i)));
				}
				pd.put(key, detailList);
			} else if(json.get(key) instanceof JSONObject) {
				pd.put(key, covertJsonToHashMap2(json.getJSONObject(key)));
			} else {
				pd.put(key, json.getString(key));
			}
		}
		return pd;
	}
}