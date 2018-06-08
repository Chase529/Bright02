package com.fh.util;

import java.util.List;

/**
 * 字符串相关方法
 *
 */
public class StringUtil {

	/**
	 * 去除字符串最后字符
	 */
	public static String noEndChar(String str) {
		return str.substring(0, str.length()-1);
	}
	
	/**
	 * 将数组转换为以逗号分隔的字符串
	 * @return
	 */
	public static String getAppendStr(List<PageData> list,String attrName) {
		StringBuilder sb = new StringBuilder();
		for(PageData pData: list) {
			sb.append(pData.get(attrName)+",");
		}
		String string = sb.toString();
		if(string.length()>1) {
			return noEndChar(string);
		}else {
			return "";
		}
	}
	
	
	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * @param valStr
	 * @return String[]
	 */
	public static String[] StrList(String valStr){
		if(valStr.equals("")){
			return new String[]{};
		}
	    int i = 0;
	    String TempStr = valStr;
	    String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
	    valStr = valStr + ",";
	    while (valStr.indexOf(',') > 0)
	    {
	        returnStr[i] = valStr.substring(0, valStr.indexOf(','));
	        valStr = valStr.substring(valStr.indexOf(',')+1 , valStr.length());
	        i++;
	    }
	    return returnStr;
	}
	
	/**获取字符串编码
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {      
	       String encode = "GB2312";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s = encode;      
	              return s;      
	           }      
	       } catch (Exception exception) {      
	       }      
	       encode = "ISO-8859-1";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s1 = encode;      
	              return s1;      
	           }      
	       } catch (Exception exception1) {      
	       }      
	       encode = "UTF-8";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s2 = encode;      
	              return s2;      
	           }      
	       } catch (Exception exception2) {      
	       }      
	       encode = "GBK";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s3 = encode;      
	              return s3;      
	           }      
	       } catch (Exception exception3) {      
	       }      
	      return "";      
	   } 
	
	/**
	 * 设置默认值
	 * @param string
	 * @param def
	 * @return
	 */
	public static String setDefault(String string,String def) {
		if(string == null || string.equals("")) {
			return def;
		}else {
			return string;
		}
	}
}
