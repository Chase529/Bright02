package com.fh.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class JSONUtil {

	/**
	 * json转为list<String>
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getStringList(String json) {
		List<String> list = new ArrayList<String>();
		if(json!=null&&!json.equals("")) {
			return (List<String>)JSON.parse(json);
		}
		return list;
	}
	
	public static void main(String[] args) {
		String testString = "['029ws0301','029ws0301','029ws0303']";
		System.out.println(getStringList(testString));
	}
	
	
	
}
