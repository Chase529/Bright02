package com.fh.controller.base;


import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fh.core.HttpJsonResult;
import com.fh.core.StringUtil;
import com.fh.entity.Page;
import com.fh.service.system.appuser.AppuserManager;
import com.fh.util.ArrayUtil;
import com.fh.util.BeanUtil;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.fh.util.UuidUtil;

/**
 * @author FH Q313596790
 * 修改时间：2015、12、11
 */
@SuppressWarnings({ "rawtypes", "unchecked" ,"unused"})
public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = 6357869213649815390L;
	
	/** new PageData对象
	 * @return
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/** new PageData对象
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public PageData getPageData(HttpServletRequest request) {
		PageData pageData = new PageData(request);
		pageData.put("request", request);
		return pageData;
	}
	
	/**得到ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**得到request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}
	
	/**得到分页列表的信息
	 * @return
	 */
	public Page getPage(){
		return new Page();
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	
	/**获取系统当前的时间
	 * @return
	 */
	public String getCurrentTime(){
		Date date = new Date();   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String createTimeStr = sdf.format(date);
		return createTimeStr;
	}
	
	/**获取系统当前的时间
	 * @return
	 */
	public String getCurrentDate(){
		Date date = new Date();   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String createDateStr = sdf.format(date);
		return createDateStr;
	}
	/**获取系统当前的时间
	 * @return
	 */
	public String getCurrentMonth(){
		Date date = new Date();   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
        String createMonthStr = sdf.format(date);
		return createMonthStr;
	}
	
	/**
	 * 获取上或后n个月份
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public String getLastMonth(String month, Integer n) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
	    Calendar c = Calendar.getInstance();
	    c.setTime(format.parse(month));
	    c.add(Calendar.MONTH, n);
	    Date m = c.getTime();
	    String lastMonth = format.format(m);
		return lastMonth;
	}
	
	
	/**
	 * 参数非空校验
	 * 默认验证uid非空，且存在
	 * @throws Exception
	 */
	public PageData baseAppValidate( HttpJsonResult json,String[] attrs,String[] msg) throws Exception {
		AppuserManager appuserService = (AppuserManager)BeanUtil.getBean("appuserService"); 
		//基本参数非空校验
		PageData pd = this.getPageData();
		if(attrs==null) {
			attrs = new String[] {};
			msg = new String[] {};
		}
		
		List<String> msgList = ArrayUtil.array2List(msg);
		List<String> asList = ArrayUtil.array2List(attrs);
		asList.add("uid");//添加uid非空校验
		msgList.add("用户信息不能为空！");
		
  		for(int i=0;i<asList.size();i++) {
  			String attr = asList.get(i);
			String string = pd.getString(attr);
			boolean contains = attr.contains("||");
   			if(StringUtil.isEmpty(string, true)&&!contains){
				json.setMessage(msgList.get(i));
				json.setSuccess(false);
				return null;
	        }
   			//多个字段必有其一判断，字段用||分隔
    		String[] split = attr.split("\\|\\|");
    		boolean haveOne = false;
    		String attrStr = "";
    		for(String str: split) {
    			attrStr+=str+"、";
   			if(!StringUtil.isEmpty(pd.getString(str), true)){
   				haveOne = true;
   				break;
    	        }
    		}
    		if(!haveOne) {
    			json.setMessage(msgList.get(i));
				json.setSuccess(false);
				return null;
    		}
		}
		PageData res = null;
		//校验用户是否存在
		if(json.getSuccess()) {
			String uid = pd.getString("uid");
	        PageData user = new PageData();
	        user.put("USER_ID", uid);
	        res = appuserService.findById(user);
	        if(res == null) {
        		json.setMessage("用户不存在");
        		json.setSuccess(false);
	        }
		}
        return res;
	}
	/**
	 * 参数非空校验
	 * 默认验证uid非空，且存在
	 * @throws Exception
	 */
	public HttpJsonResult baseAppValidate(HttpServletRequest request,String[] attrs,String[] msg) throws Exception {
		HttpJsonResult json  = new HttpJsonResult();
		AppuserManager appuserService = (AppuserManager)BeanUtil.getBean("appuserService"); 
		//基本参数非空校验
		PageData pd = this.getPageData(request);
		if(attrs==null) {
			attrs = new String[] {};
			msg = new String[] {};
		}
		
		List<String> msgList = ArrayUtil.array2List(msg);
		List<String> asList = ArrayUtil.array2List(attrs);
		asList.add("uid");//添加uid非空校验
		msgList.add("用户信息不能为空！");
		
  		for(int i=0;i<asList.size();i++) {
  			String attr = asList.get(i);
			String string = pd.getString(attr);
			boolean contains = attr.contains("||");
   			if(StringUtil.isEmpty(string, true)&&!contains){
				json.setFailResult(msgList.get(i));
				return json;
	        }
   			//多个字段必有其一判断，字段用||分隔
    		String[] split = attr.split("\\|\\|");
    		boolean haveOne = false;
    		String attrStr = "";
    		for(String str: split) {
    			attrStr+=str+"、";
	   			if(!StringUtil.isEmpty(pd.getString(str), true)){
		   				haveOne = true;
		   				break;
	        	}
    		}
    		if(!haveOne) {
    			json.setFailResult(msgList.get(i));
				return json;
    		}
		}
		//校验用户是否存在
		if(json.getSuccess()) {
			String uid = pd.getString("uid");
	        PageData user = new PageData();
	        user.put("USER_ID", uid);
	        PageData res = appuserService.findById(user);
	        if(res == null) {
        		json.setFailResult("用户不存在");
	        }
	        json.setData(res);
		}
        return json;
	}
	/**
	 * 获取成功的http结果
	 * @param object
	 * @return
	 */
	public HttpJsonResult getSuccessHttpResult(Object object) {
		HttpJsonResult result = new HttpJsonResult();
		result.setSuccess(true);
		result.setData(object);
		result.setMessage("成功。");
		if(object!=null && object instanceof List) {
			result.setTotal(((List) object).size());
		}
		return result;
	}
	
}
