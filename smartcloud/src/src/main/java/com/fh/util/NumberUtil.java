package com.fh.util;

import java.text.NumberFormat;


public class NumberUtil {
	public static String getPercent(Integer number, Double percent){
		//获取格式化对象
		NumberFormat nt = NumberFormat.getPercentInstance();
		//设置百分数精确度即保留两位小数
	    nt.setMinimumFractionDigits(number);
	    //最后格式化并输出
	    return nt.format(percent);
	}
}
