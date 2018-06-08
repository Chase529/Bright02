package com.fh.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {
	//手机号码
	public static final String REG_MOBILE = "^1\\d{10}$";
	
	//邮箱地址
	public static final String REG_EMAIL = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
	
	//密码
	public static final String REG_PASSWORD = "^[\\da-zA-Z]{6,8}$";
	
	//昵称
	public static final String REG_NICKNAME = "^[a-zA-Z\\u4e00-\\u9fa5]{2,10}$";
	

    /**
     * 图片文件正则表达式 
     */
    public static final String PIC_REG = "^(png|jpg|jpeg|gif|bmp)$";
	
	public static Boolean regCheck(String regStr, String value) {
		
		Pattern pr = Pattern.compile(regStr);
		
		Matcher mr = pr.matcher(value);
		
		if(!mr.find()) {
			return false;
		}
		
		return true;
	}
}
