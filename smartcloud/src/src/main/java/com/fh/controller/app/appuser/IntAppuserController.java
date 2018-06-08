package com.fh.controller.app.appuser;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.core.Constants;
import com.fh.core.ErrorCode;
import com.fh.core.HttpJsonResult;
import com.fh.core.Md5;
import com.fh.core.RandomUtil;
import com.fh.core.RegUtil;
import com.fh.core.StringUtil;
import com.fh.entity.Page;
import com.fh.service.app.checking.CheckingManager;
import com.fh.service.app.verifycode.impl.VerifyCodeService;
import com.fh.service.system.appuser.AppuserManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.util.AppUtil;
import com.fh.util.PageData;
import com.fh.util.SmsUtil;
import com.fh.util.Tools;
import com.fh.util.facerecog.FaceRecogBiduUtil;

@Controller
@RequestMapping(value="/appuser")
public class IntAppuserController extends BaseController {
    
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	
	@Resource(name="verifycodeService")
	private VerifyCodeService verifyCodeService;
	
	@Resource(name="checkingService")
	private CheckingManager checkingService;
	
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	
	
	/**根据用户名获取会员信息
	 * @return
	 */
	@RequestMapping(value="/getAppuserByUm")
	@ResponseBody
	public Object getAppuserByUsernmae(){
		logBefore(logger, "根据用户名获取会员信息");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";
		try{
			if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("getAppuserByUsernmae", pd)){	//检查参数
					pd = appuserService.findByUsername(pd);
					map.put("pd", pd);
					result = (null == pd) ?  "02" :  "01";
				}else {
					result = "03";
				}
			}else{
				result = "05";
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	

	/**
	 * APP用户登录     TODO 备注：前端md5加密后传入
	 * 
	 * @apiParam
	 * String USERNAME 用户名
	 * String PASSWORD 密码
	 * 
	 * @returnParam
	 * String code 返回码 0000，成功
	 * Object data 返回数据
	 * 		String USER_ID 用户ID
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	@ResponseBody
	public HttpJsonResult<Object> login(HttpServletRequest req, HttpServletResponse rsp) {
		HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			
			if(pd.isEmpty() || StringUtil.isEmpty(pd.get("USERNAME").toString()) || StringUtil.isEmpty(pd.get("PASSWORD").toString())) {
				jsonResult.setCode(ErrorCode.PARAM_ERROR);
				return jsonResult;
			}
			String password = pd.get("PASSWORD").toString();
			
			PageData queryMap = new PageData();
			queryMap.put("PHONE", pd.getString("USERNAME"));
			pd = appuserService.findByPhone(queryMap);
			if(pd == null) {
				jsonResult.setCode(ErrorCode.USER_NAME_ERROR);
				return jsonResult;
			}
			if(!pd.get("PASSWORD").equals(password)) {
				jsonResult.setCode(ErrorCode.USER_PWD_ERROR);
				return jsonResult;
			}
			
			jsonResult.setData(pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @apiParam
	 * String USER_ID 用户ID
	 * 
	 * @returnParam
	 * String code 返回码 0000，成功
	 * Object data 返回数据
	 * 		String NAME 用户姓名
	 * 		String PHONE 手机号
	 * 		Number 性别 1.男 2.女
	 * 		String AVATAR 头像地址
	 * 		String DEPARTMENT 部门
	 *      String DEPARTMENT_NAME 部门名称
	 * 		String POSITION 职位
	 *      String POSITION_NAME职位名
	 * 		String EDU 学历
	 *      String EDU_NAME 学历 名称
	 * 		String MAJOR 专业 
	 *      String MAJOR_NAME 专业名称
	 * 		String SUPERIOR 上级
	 *      String SUPERIOR_NAME 上级名称
	 * 		String VIOLATION 违规情况
	 *      String SFID 身份证号码
	 */
	@RequestMapping(value="/getUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public HttpJsonResult<Object> getUserInfo(HttpServletRequest req, HttpServletResponse rsp) {
		HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
		PageData pd = new PageData();
		
		try {
			pd = this.getPageData();
			if(pd.isEmpty() || StringUtil.isEmpty(pd.getString("USER_ID"))) {
				jsonResult.setCode(ErrorCode.PARAM_ERROR);
				return jsonResult;
			}
			
			pd = appuserService.findById(pd);
			if(pd == null) {
				jsonResult.setCode(ErrorCode.USER_NOT_EXIST);
				return jsonResult;
			}
			
			jsonResult.setData(pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * 修改密码
	 * 
	 * @apiParam
	 * String USER_ID 用户ID
	 * String OLD_PWD 旧密码
	 * String NEW_PWD 新密码
	 * 
	 * @returnParam
	 * String code 返回码 0000，成功
	 * Object data 返回数据
	 */
	@RequestMapping(value="/updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public HttpJsonResult<Object> updatePwd(HttpServletRequest req, HttpServletResponse rsp) {
		HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
		PageData pd = new PageData();
		
		try {
			pd = this.getPageData();
			
			if(pd.isEmpty() || StringUtil.isEmpty(pd.getString("USER_ID")) || 
					StringUtil.isEmpty(pd.getString("OLD_PWD")) || StringUtil.isEmpty(pd.getString("NEW_PWD"))) {
				jsonResult.setCode(ErrorCode.PARAM_ERROR);
				return jsonResult;
			}
			
			PageData userInfo = appuserService.findById(pd);
			if(userInfo == null) {
				jsonResult.setCode(ErrorCode.USER_NOT_EXIST);
				return jsonResult;
			}
			
			if(!userInfo.getString("PASSWORD").equals(pd.getString("OLD_PWD"))) {
				jsonResult.setCode(ErrorCode.OLD_PWD_ERROR);
				return jsonResult;
			}
			
			if(!RegUtil.regCheck(RegUtil.REG_PASSWORD, pd.getString("NEW_PWD"))) {
				jsonResult.setCode(ErrorCode.NEW_PWD_FORMAT_ERROR);
				return jsonResult;
			}
			
			userInfo.put("PASSWORD", Md5.getMd5String(pd.getString("NEW_PWD")));
			appuserService.editU(userInfo);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * 忘记密码
	 * 
	 * @apiParam
	 * String MOBILE 手机号
	 * String NEW_PWD 新密码
	 * String SMS_CODE 短信验证码
	 * 
	 * @returnParam
	 * String code 返回码 0000，成功
	 */
	@RequestMapping(value="/forgotPwd", method = RequestMethod.POST)
	@ResponseBody
	public HttpJsonResult<Object> forgotPwd(HttpServletRequest req, HttpServletResponse rsp) {
		HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
		PageData pd = new PageData();
		
		try {
			pd = this.getPageData();
			if(pd.isEmpty() || StringUtil.isEmpty(pd.getString("MOBILE")) || 
					StringUtil.isEmpty(pd.getString("SMS_CODE")) || StringUtil.isEmpty(pd.getString("NEW_PWD"))) {
				jsonResult.setCode(ErrorCode.PARAM_ERROR);
				return jsonResult;
			}
			
			PageData queryMap = new PageData();
			queryMap.put("PHONE", pd.getString("MOBILE"));
			PageData userInfo = appuserService.findByPhone(queryMap);
			if(userInfo == null) {
				jsonResult.setCode(ErrorCode.MOBILE_ERROR);
				return jsonResult;
			}
			
			if(!RegUtil.regCheck(RegUtil.REG_PASSWORD, pd.getString("NEW_PWD"))) {
				jsonResult.setCode(ErrorCode.NEW_PWD_FORMAT_ERROR);
				return jsonResult;
			}
			
			//校验验证码
			PageData codeInfo = verifyCodeService.findByMobile(pd);
			if(codeInfo == null) {
				jsonResult.setCode(ErrorCode.MOBILE_ERROR);
				return jsonResult;
			}
			
			if(!codeInfo.getString("CODE").equals(pd.getString("SMS_CODE"))) {
				jsonResult.setCode(ErrorCode.SMS_CODE_ERROR);
				return jsonResult;
			}
			
			//更新密码
			userInfo.put("PASSWORD", Md5.getMd5String(pd.getString("NEW_PWD")));
			appuserService.editU(userInfo);
			//删除验证码
			verifyCodeService.delete(codeInfo);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * 发送短信验证码
	 * 
	 * @apiParam
	 * String MOBILE 手机号
	 * 
	 * @returnParam
	 * String code 返回码 0000，成功
	 */
	@RequestMapping(value="/sendVerifyCode", method = RequestMethod.POST)
	@ResponseBody
	public HttpJsonResult<Object> sendVerifyCode(HttpServletRequest req, HttpServletResponse rsp) {
		HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			
			if(pd.isEmpty() || StringUtil.isEmpty(pd.getString("MOBILE"))) {
				jsonResult.setCode(ErrorCode.PARAM_ERROR);
				return jsonResult;
			}
			
			PageData queryMap = new PageData();
			queryMap.put("PHONE", pd.getString("MOBILE"));
			PageData userInfo = appuserService.findByPhone(queryMap);
			if(userInfo == null) {
				jsonResult.setCode(ErrorCode.MOBILE_ERROR);
				return jsonResult;
			}
			
			//发送验证码
			String code = RandomUtil.randomNumber(6);
			String content = "您的验证码是："+code+"。请不要把验证码泄露给其他人。";
			boolean result = SmsUtil.sendSms2(pd.getString("MOBILE"), content);
			if(!result) {//短信发送失败
				jsonResult.setCode(ErrorCode.SMS_SEND_ERROR);
				return jsonResult;
			}
			
			//查询是否已发送过验证码
			PageData codeInfo = verifyCodeService.findByMobile(pd);
			pd.put("CODE", code);
			pd.put("CREATE_TIME", new Timestamp(System.currentTimeMillis()));
			if(codeInfo == null) {
				//插入新验证码
				verifyCodeService.save(pd);
			} else {
				//更新原有验证码
				verifyCodeService.edit(pd);
			}
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * @api {GET} /appuser/getAllUsersByDepartment 根据部门查找用户
	 * @apiName getAllUsersByDepartment
	 * @apiGroup appuser
	 * @apiParamExample {json} Request-Example
     * example
     * {
     * 		"uid": "123456",
     * 		"deparmentId": "1" 
     * }
	 * @param req
	 * @param rsp
	 * @param departmentId
	 * @return
	 * @throws Exception
	 * @apiSuccess {Boolean} success true or false，表示请求是否成功
     * @apiSuccess {Array} data 返回数据，用户列表
	 * @apiSuccess {String} message 当success=false时，返回提示信息
	 * @apiUse code
	 * @apiSuccessExample {json} Success-Response: 
	 * {
	 * 		"success": true,
	 * 		"total": 1,
	 * 		"message": "",
	 * 		"code": "0"
	 * 		"data": [
	 * 			{
	 * 				USER_ID,
	 *				USERNAME,
	 *				PASSWORD,
	 *				NAME,
	 *				RIGHTS,
	 *				ROLE_ID,
	 *				LAST_LOGIN,
	 *				IP,
	 *				STATUS,
	 *				BZ,
	 *				PHONE,
	 *				SFID,
	 *				START_TIME,
	 *				END_TIME,
	 *				YEARS,
	 *				EMAIL,
	 *				NUMBER,
	 *				POSITION,
	 *				GENDER,
	 *				DEPARTMENT,
	 *				EDU,
	 *				MAJOR,
	 *				SUPERIOR,
	 *				VIOLATION,
	 *				AVATAR,
	 *				"isChecking":"false(未签到)， true(已签到)"
	 * 			}
	 * 		]
	 * }
	 */
	@RequestMapping(value = "/getAllUsersByDepartment", method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<PageData>> getAllUsersByDepartment(HttpServletRequest req, HttpServletResponse rsp, String departmentId) throws Exception {
		HttpJsonResult<List<PageData>> jsonResult = new HttpJsonResult<List<PageData>>();
		String uid = req.getParameter("uid");
		if(StringUtil.isEmpty(uid, true)){
			jsonResult.setMessage("您还没有登录！");
			jsonResult.setCode(ErrorCode.USER_NOT_LOGIN);
			return jsonResult;
		}
		PageData user = new PageData();
		user.put("USER_ID", uid);
		user = appuserService.findById(user);
		if(user == null){
			jsonResult.setMessage("该用户不存在！");
			jsonResult.setCode(ErrorCode.USER_NOT_EXIST);
			return jsonResult;
		}
		if(StringUtil.isEmpty(departmentId, true)){
			jsonResult.setMessage("请选择部门！");
			jsonResult.setCode(ErrorCode.PARAM_ERROR);
			return jsonResult;
		}
		Page page = new Page();
		PageData pd = new PageData();
		pd.put("DEPARTMENT", departmentId);
		pd.put("STATUS", 1);
		page.setPd(pd);
		List<PageData>	list = appuserService.listAllUser(pd);
		List<PageData> usersList = new ArrayList<PageData>();
		if(list.size()>0){
			for(int i=0; i<list.size(); i++){
				PageData userEntity = list.get(i);
				if(user != null){
					String userId = userEntity.getString("USER_ID"); 
					Boolean isChecking = false;
					PageData queryMap = new PageData();
					queryMap.put("CHECK_USER", userId);
					queryMap.put("CHECK_TIME", this.getCurrentDate());
					PageData checkUser = checkingService.findByUser(queryMap);
					if(checkUser != null){
						isChecking = true;
					}
					userEntity.put("isChecking", isChecking);
					usersList.add(userEntity);
				}
			 }
		 }
		jsonResult.setData(usersList);
		jsonResult.setTotal(usersList.size());
		return jsonResult;
	}
	
	/**
	 * 查询所有部门以及部门下的用户
	 * @apiParam
	 * String uid 用户ID
	 * 
	 * @returnParam
	 * String code 返回码 0000，成功
	 * List data 数据列表
	 * 	   String ID 部门ID
	 * 	   String NAME 部门名称
	 * 	   List users 部门人员列表
	 *          String USER_ID 人员ID
	 *          String NAME 人员姓名
	 *          String AVATAR 头像
	 */
	@RequestMapping(value="/getAllDepartmentUsers")
	public @ResponseBody HttpJsonResult<List<PageData>> getAllDepartmentUsers(HttpServletRequest req, HttpServletResponse rsp) {
		HttpJsonResult<List<PageData>> jsonResult = new HttpJsonResult<List<PageData>>();
		
		PageData pd = new PageData();
		pd.put("BIANMA", "002");
		try {
			pd = dictionariesService.findByBianma(pd);
			
			List<PageData> deparmentList = dictionariesService.listAllByParentBianma(pd);
			if(deparmentList.size() > 0) {
				for(int i = 0; i < deparmentList.size(); i++) {
					PageData queryMap = new PageData();
					queryMap.put("DEPARTMENT", deparmentList.get(i).getString("BIANMA"));
					List<PageData> users = appuserService.listAllUser(queryMap);
					deparmentList.get(i).put("users", users);
				}
			}
			
			jsonResult.setData(deparmentList);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * 用户考勤
	 * 
	 * @apiParam
	 * String uid 用户ID
	 * String type 考勤类型 1.签到 2.签退
	 * String lat 纬度
	 * String log 经度
	 * String address 签到/签退地址
	 * 
	 * @returnParam
	 * String code 返回码 0000，成功
	 */
	@RequestMapping(value = "/checking", method = RequestMethod.POST)
	@ResponseBody
	public HttpJsonResult<Object> checking(HttpServletRequest req, HttpServletResponse rsp) {
		HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
		PageData pd = new PageData();
		
		try {
			pd = this.getPageData();
			
			if(StringUtil.isEmpty(pd.getString("uid")) || StringUtil.isEmpty(pd.getString("type")) ||
					StringUtil.isEmpty(pd.getString("lat")) || StringUtil.isEmpty(pd.getString("log")) ||
					StringUtil.isEmpty(pd.getString("address"))) {
				
				jsonResult.setCode(ErrorCode.PARAM_ERROR);
				return jsonResult;
			}
			
			//查询用户今日考勤记录
			PageData queryMap = new PageData();
			queryMap.put("CHECK_USER", pd.getString("uid"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			queryMap.put("CHECK_TIME", date);
			PageData dbData = checkingService.findByUser(queryMap);
			
			String time = new Timestamp(System.currentTimeMillis()).toString();
			if(dbData != null) {
				if(pd.getString("type").equals("1")) {
					if(dbData != null && !StringUtil.isEmpty(dbData.getString("SIGN_IN_TIME"))) {
						jsonResult.setCode(ErrorCode.SIGN_IN_EXIST);
						return jsonResult;
					}
					
					//签到
					dbData.put("SIGN_IN_TIME", time);
					dbData.put("SIGN_IN_LAT", pd.getString("lat"));
					dbData.put("SIGN_IN_LOG", pd.getString("log"));
					dbData.put("SIGN_IN_ADDRESS", pd.getString("address"));
				} else {
					if(dbData != null && !StringUtil.isEmpty(dbData.getString("SIGN_OUT_TIME"))) {
						jsonResult.setCode(ErrorCode.SIGN_OUT_EXIST);
						return jsonResult;
					}
					
					//签退
					dbData.put("SIGN_OUT_TIME", time);
					dbData.put("SIGN_OUT_LAT", pd.getString("lat"));
					dbData.put("SIGN_OUT_LOG", pd.getString("log"));
					dbData.put("SIGN_OUT_ADDRESS", pd.getString("address"));
				}
				
				int result = (Integer)checkingService.edit(dbData);
				if(result != 1) {
					jsonResult.setCode(ErrorCode.EDIT_FAILED);
				}
			} else {
				dbData = new PageData();
				if(pd.getString("type").equals("1")) {
					//签到
					dbData.put("SIGN_IN_TIME", time);
					dbData.put("SIGN_IN_LAT", pd.getString("lat"));
					dbData.put("SIGN_IN_LOG", pd.getString("log"));
					dbData.put("SIGN_IN_ADDRESS", pd.getString("address"));
				} else {
					
					//签退
					dbData.put("SIGN_OUT_TIME", time);
					dbData.put("SIGN_OUT_LAT", pd.getString("lat"));
					dbData.put("SIGN_OUT_LOG", pd.getString("log"));
					dbData.put("SIGN_OUT_ADDRESS", pd.getString("address"));
				}
				
				dbData.put("CHECK_USER", pd.getString("uid"));
				dbData.put("CHECK_ID", this.get32UUID());
				
				int result = (Integer)checkingService.save(dbData);
				
				if(result != 1) {
					jsonResult.setCode(ErrorCode.SAVE_FAILED);
				}
			}
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * 查询用户考勤列表
	 * 
	 * @apiParam
	 * String uid 用户ID
	 * String pageCode 页码
	 * String pageSize 每页记录数
	 * 
	 * @returnParam
	 * String code 返回码 0000，成功
	 * String data 返回数据列表
	 * 		String CHECK_ID 考勤ID
	 * 		String SIGN_IN_TIME	签到时间
	 */
	@RequestMapping(value = "/checkingList", method = RequestMethod.GET)
	@ResponseBody
	public HttpJsonResult<List<PageData>> checkingList(HttpServletRequest req, HttpServletResponse rsp) {
		HttpJsonResult<List<PageData>> jsonResult = new HttpJsonResult<List<PageData>>();
		PageData pd = new PageData();
		
		try {
			pd = this.getPageData();
			String uid = pd.getString("uid");
			
			if(StringUtil.isEmpty(pd.getString("uid")) || StringUtil.isEmpty(pd.getString("pageCode")) ||
					StringUtil.isEmpty(pd.getString("pageSize"))) {
				
				jsonResult.setCode(ErrorCode.PARAM_ERROR);
				return jsonResult;
			}
			
			PageData user = new PageData();
			user.put("USER_ID", uid);
			user = appuserService.findById(user);
			if(user == null){
				jsonResult.setMessage("该用户不存在！");
				jsonResult.setCode(ErrorCode.USER_NOT_EXIST);
				return jsonResult;
			}
			String pageCodeString = req.getParameter("pageCode");
			String pageSizeString = req.getParameter("pageSize");
			Integer pageCode = Constants.DEFAULT_PAGE_CODE;
			Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
			if(!StringUtil.isEmpty(pageCodeString, true)){
				pageCode = Integer.parseInt(pageCodeString);
			}
			if(!StringUtil.isEmpty(pageSizeString, true)){
				pageSize = Integer.parseInt(pageSizeString);
			}
			Page page = new Page();
			page.setCurrentPage(pageCode);
			page.setShowCount(pageSize);
			
			PageData listMap = new PageData();
			listMap.put("CHECK_USER", uid);
			page.setPd(listMap);
			List<PageData> list = checkingService.list(page);
			jsonResult.setData(list);
			jsonResult.setTotal(page.getTotalResult());
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		
		return jsonResult;
	}
	
	
	/**
	 * @api {GET} /appuser/getStatisticsUserByDepartment 根据部门查找用户
	 * @apiName getStatisticsUserByDepartment
	 * @apiGroup appuser
	 * @apiParamExample {json} Request-Example
     * example
     * {
     * 		"uid": "123456",
     * }
	 * @param req
	 * @param rsp
	 * @param deparmentId
	 * @return
	 * @throws Exception
	 * @apiSuccess {Boolean} success true or false，表示请求是否成功
     * @apiSuccess {Array} data 返回数据，用户列表
	 * @apiSuccess {String} message 当success=false时，返回提示信息
	 * @apiUse code
	 * @apiSuccessExample {json} Success-Response: 
	 * {
	 * 		"success": true,
	 * 		"total": 1,
	 * 		"message": "",
	 * 		"code": "0"
	 * 		"data": [
	 * 			{
	 * 				USER_ID,
	 *				USERNAME,
	 *				PASSWORD,
	 *				NAME,
	 *				RIGHTS,
	 *				ROLE_ID,
	 *				LAST_LOGIN,
	 * 			}
	 * 		]
	 * }
	 */
	@RequestMapping(value = "/getStatisticsUserByDepartment", method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<PageData>> getStatisticsUserByDepartment(HttpServletRequest req, HttpServletResponse rsp) {
		HttpJsonResult<List<PageData>> jsonResult = new HttpJsonResult<List<PageData>>();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			String uid = pd.getString("uid");
			if(StringUtil.isEmpty(uid, true)){
				jsonResult.setMessage("您还没有登录！");
				jsonResult.setCode(ErrorCode.USER_NOT_LOGIN);
				return jsonResult;
			}
			PageData user = new PageData();
			user.put("USER_ID", uid);
			user = appuserService.findById(user);
			if(user == null){
				jsonResult.setMessage("该用户不存在！");
				jsonResult.setCode(ErrorCode.USER_NOT_EXIST);
				return jsonResult;
			}
			
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * @api {GET} /appuser/faceCompare 人脸比对
	 * @apiName faceCompare
	 * @apiGroup appuser
	 * @apiParamExample {json} Request-Example
     * example
     * {
     * 		"uid": "123456",
     * 		"pic": "http://www.xx.com/pic.png"
     * }
	 * @param rsp
	 * @return
	 * @throws Exception
	 * @apiSuccess {Boolean} success true or false，表示请求是否成功
     * @apiSuccess {Array} data 返回数据，1:匹配成功  0:匹配失败
	 * @apiSuccess {String} message 当success=false时，返回提示信息
	 * @apiUse code
	 * @apiSuccessExample {json} Success-Response: 
	 * {
	 * 		"success": true,
	 * 		"message": "",
	 * 		"code": "0"
	 * 		"data": 1
	 * 		]
	 * }
	 */
	@RequestMapping(value="/faceCompare")
	public @ResponseBody HttpJsonResult<Integer> faceCompare(HttpServletRequest req, HttpServletResponse rsp) {
		HttpJsonResult<Integer> jsonResult = new HttpJsonResult<Integer>();
		jsonResult.setData(0);
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		if(pd.get("uid") == null || pd.get("pic") == null) {
			jsonResult.setCode(ErrorCode.PARAM_ERROR);
			return jsonResult;
		}
		
		//获取项目根路径
		String basePath = req.getSession().getServletContext().getRealPath("/");
		
		try{
			PageData user = appuserService.getAvatarById(pd.getString("uid"));
			
			String pic1Path = basePath + user.getString("AVATAR");//被对比人头像
			String pic2Path = basePath + pd.getString("pic");//对比人头像
			
			//相似度90%为匹配成功
			boolean result = FaceRecogBiduUtil.faceCompare(pic1Path, pic2Path, 90);
			jsonResult.setData(result?1:0);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		
		return jsonResult;
	}
}
	
 