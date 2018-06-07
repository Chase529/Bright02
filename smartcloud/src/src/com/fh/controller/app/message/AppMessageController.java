package com.fh.controller.app.message;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.core.HttpJsonResult;
import com.fh.entity.Page;
import com.fh.service.system.appuser.AppuserManager;
import com.fh.service.system.fhsms.FhsmsManager;
import com.fh.util.PageData;

@Controller
@RequestMapping("appMessage")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AppMessageController extends BaseController{

	@Resource(name="fhsmsService")
	private FhsmsManager fhsmsService;
	@Resource(name="appuserService")
    private AppuserManager appuserService;
	
	/**
	 * 根据用户id查询接收到的信息
	 * 参数：uid：当前用户
	 */
	@RequestMapping(value="/findSendMesssage",method=RequestMethod.POST)
	@ResponseBody
	public HttpJsonResult<List<PageData>> findSendMesssage(Page page,HttpServletRequest request) throws Exception{
		return getMessage(page,"FROM_USERNAME",request);
	}
	
	/**
	 * 根据用户id查询发送的信息
	 */
	@RequestMapping(value="/findReciveMesssage",method=RequestMethod.POST)
	@ResponseBody
	public HttpJsonResult<List<PageData>> findReciveMesssage(Page page,HttpServletRequest request) throws Exception{
		return getMessage(page,"TO_USERNAME",request);
	}
	
	/**
	 * 设置已读
	 * uid:用户id
	 * FHSMS_ID:消息id
	 */
	@RequestMapping(value="/setHaveRead",method=RequestMethod.POST)
	@ResponseBody
	public HttpJsonResult<List<PageData>> setHaveRead(HttpServletRequest request) throws Exception{
		HttpJsonResult<List<PageData>> jsonResult = new HttpJsonResult<List<PageData>>();
		this.baseAppValidate(jsonResult, new String[] {"FHSMS_ID"}, new String[] {"消息信息不能为空！"});
		
		if(!jsonResult.getSuccess()) {
			return jsonResult;
		}else {
			PageData pageData = this.getPageData();
			fhsmsService.edit(pageData);
			
			jsonResult.setSuccess(true);
			jsonResult.setMessage("修改成功！");
			return jsonResult;
		}
	}
	
	/**
	 * 添加消息
	 * 状态：2未读，1已读
	 * 参数：uid-------------当前用户(id)
	 * 		TO_USERIDS--接受者id(多个用逗号分隔)
	 * 		DEPARTMENT_BIANMAS---部门编码s(部门群发，和TO_USERIDS必有其一)
	 * 		CONTENT---------内容
	 * 		TITLE-----------标题，没有不传
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST) 
	@ResponseBody
	public HttpJsonResult<List<List<PageData>>> save(HttpServletRequest request) throws Exception{
		//非空校验，用户校验
		HttpJsonResult jsonResult = this.baseAppValidate(request,
				new String[] {"CONTENT"},
				new String[] {"内容不能为空！"});
		if(!jsonResult.getSuccess()) {
        		return jsonResult;
        }else {
        		PageData pd = this.getPageData();
	    		
	    		String string = pd.getString("DEPARTMENT_BIANMAS");
	    		//获取收件人
	    		List<String> toUserIds = new ArrayList<String>();
	    		if(string!=null&&!string.equals("")) {//部门群发
	    			String[] departmentIds = string.split(",");
	    			for(String str: departmentIds) {
	    				PageData pageData = new PageData();
	    				pageData.put("DEPARTMENT", str);
	    				List<PageData> findAllUserByDepartment = appuserService.findAllUserByDepartment(pd);
	    				for(PageData pData: findAllUserByDepartment) {
	    					toUserIds.add(pData.getString("USER_ID"));
	    				}
	    				pd.put("TYPE", 3);
	    			}
	    		}else {
	    			String[] sendToUserNames = pd.getString("TO_USERIDS").split(",");
	    			toUserIds.addAll(Arrays.asList(sendToUserNames));
	    			if(toUserIds.size()>1) {//多人群发
	    				pd.put("TYPE", 2);
	    			}else {//单发
	    				pd.put("TYPE", 1);
	    			}
	    		}
	    		for(String toUserId: toUserIds) {
	    			//保存数据
		    		pd.put("FROM_USERNAME", pd.get("uid"));	//发信人
		    		String title = pd.getString("TITLE");
		    		if(title!=null) {
		    			pd.put("TITLE", URLDecoder.decode(pd.getString("TITLE"),"utf-8"));
		    		}else {
		    			pd.put("TITLE", "");
		    		}
		    		pd.put("CONTENT", URLDecoder.decode(pd.getString("CONTENT"),"utf-8"));
		    		pd.put("TO_USERNAME", toUserId);
		    		fhsmsService.save(pd);
	    		}
	    		jsonResult.setMessage("保存完成！");
	    		jsonResult.setSuccess(true);
        }
        return jsonResult;
	}
	
	/**
	 * 查询未接受信息数量
	 * @throws Exception 
	 */
	@RequestMapping(value="/findNoReadCount",method=RequestMethod.POST)
	@ResponseBody
	public HttpJsonResult<Object> findNoReadCount(HttpServletRequest request) throws Exception{
		HttpJsonResult jsonResult = this.baseAppValidate(request, new String[]{}, new String[]{});
		if(!jsonResult.getSuccess()) {
			return jsonResult;
		}else {
			PageData pd = this.getPageData(request);
			Object count = fhsmsService.findFhsmsCount(pd.getString("uid")).get("fhsmsCount");
			return jsonResult.setSuccessResult(count);
		}
	}
	
	
	/**
	 * @param page
	 * @param userType:TO_USERNAME/FROM_USERNAME
	 * @return
	 * @throws Exception
	 */
	private HttpJsonResult getMessage( Page page,String userType,HttpServletRequest request) throws Exception {
		HttpJsonResult jsonResult = this.baseAppValidate(request, new String[]{}, new String[]{});
		if(!jsonResult.getSuccess()) {
			return jsonResult;
		}else {
			PageData pd = this.getPageData(request);
			PageData user = (PageData)jsonResult.getData();
	        pd.put(userType, user.get("USER_ID"));
	        page.setPd(pd);
			//查询
	        List<PageData>	varList = fhsmsService.findByFields(page);
	        return jsonResult.setSuccessResult(varList);
		}
	}
	
}
