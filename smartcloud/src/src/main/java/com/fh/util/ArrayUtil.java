package com.fh.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {

	
	/**
	 * 数组转list
	 * @param strs
	 * @return
	 */
	public static List<String> array2List(String[] strs){
		List<String> msgList = new ArrayList<String>();
		msgList.addAll(Arrays.asList(strs));
		return msgList;
	}
	
	
}
