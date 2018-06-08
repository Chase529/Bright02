package com.fh.controller.system.appuser;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.core.Md5;
import com.fh.core.StringUtil;
import com.fh.util.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Dictionaries;
import com.fh.entity.system.Role;
import com.fh.service.system.appuser.AppuserManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.service.system.fhlog.FHlogManager;
import com.fh.service.system.role.RoleManager;
import com.fh.service.system.user.UserManager;

/** 
 * 类名称：会员管理
 * 创建人：FH Q313596790
 * 修改时间：2014年11月17日
 * @version
 */
@Controller
@RequestMapping(value="/happuser")
public class AppuserController extends BaseController {
	
	String menuUrl = "happuser/listUsers.do"; //菜单地址(权限用)
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	@Resource(name="roleService")
	private RoleManager roleService;

	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	
	@Resource(name="userService")
	private UserManager userService;
	
	@Resource(name="fhlogService")
	private FHlogManager FHLOG;
	/**显示用户列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/listUsers")
	public ModelAndView listUsers(Page page){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String keywords = pd.getString("keywords");							//检索条件 关键词
			if(null != keywords && !"".equals(keywords)){
				pd.put("keywords", keywords.trim());
			}
			page.setPd(pd);
			List<PageData>	userList = appuserService.listPdPageUser(page);		//列出会员列表
			pd.put("ROLE_ID", "2");
			List<Role> roleList = roleService.listAllRolesByPId(pd);			//列出会员组角色
			mv.setViewName("system/appuser/appuser_list");
			mv.addObject("userList", userList);
			mv.addObject("roleList", roleList);
			mv.addObject("pd", pd);
			mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 获取部门信息
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> getDepartment() throws Exception{
		PageData pd = new PageData();
		pd.put("BIANMA", "002");
		pd = dictionariesService.findByBianma(pd);
		List<Dictionaries> departmentList = null;
		if(pd != null){
			String parentId = pd.getString("DICTIONARIES_ID");
			departmentList = dictionariesService.listSubDictByParentId(parentId);
		}
		return departmentList;
	}
	
	/**
	 * 获取职位信息
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> getPosition() throws Exception{
		PageData pd = new PageData();
		pd.put("BIANMA", "009");
		pd = dictionariesService.findByBianma(pd);
		List<Dictionaries> positionList = null;
		if(pd != null){
			String parentId = pd.getString("DICTIONARIES_ID");
			positionList = dictionariesService.listSubDictByParentId(parentId);
			
		}
		return positionList;
	}
	
	/**
	 * 获取学历信息
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> getEducation() throws Exception{
		PageData pd = new PageData();
		pd.put("BIANMA", "011");
		pd = dictionariesService.findByBianma(pd);
		List<Dictionaries> educationList = null;
		if(pd != null){
			String parentId = pd.getString("DICTIONARIES_ID");
			educationList = dictionariesService.listSubDictByParentId(parentId);
			
		}
		return educationList;
	}
	
	/**
	 * 获取专业信息
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> getProfession() throws Exception{
		PageData pd = new PageData();
		pd.put("BIANMA", "012");
		pd = dictionariesService.findByBianma(pd);
		List<Dictionaries> professionList = null;
		if(pd != null){
			String parentId = pd.getString("DICTIONARIES_ID");
			professionList = dictionariesService.listSubDictByParentId(parentId);
			
		}
		return professionList;
	}
	
	/**.....
	 * @return
	 */
	@RequestMapping(value="/getDeparmentUsers")
	@ResponseBody
	public Object getDeparmentUsers(){
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			List<PageData> list = appuserService.listAllUser(pd);
			map.put("list", list);	
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}


	/**根据部门查询员工
	 * @return
	 */
	@RequestMapping(value="/getUsersByDep")
	@ResponseBody
	public Object getUsersByDep(){
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			List<PageData> list = appuserService.findAllUserByDepartment(pd);
			map.put("list", list);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 根据用户ID查询用户信息
	 * @return
	 */
	@RequestMapping(value="/getAppUserById")
	@ResponseBody
	public Object getAppUserById(){
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			PageData appUser = appuserService.findById(pd);
			map.put("appUser", appUser);	
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**去新增用户页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goAddU")
	public ModelAndView goAddU() throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ROLE_ID", "2");
		List<Role> roleList = roleService.listAllRolesByPId(pd);			//列出会员组角色
		mv.setViewName("system/appuser/appuser_edit");
		mv.addObject("msg", "saveU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);
		
		List<Dictionaries> deparmentList = this.getDepartment();
		mv.addObject("deparmentList", deparmentList);
		
		List<Dictionaries> positionList = this.getPosition();
		mv.addObject("positionList", positionList);
		
		List<Dictionaries> educationList = this.getEducation();
		mv.addObject("educationList", educationList);
		
		List<Dictionaries> professionList = this.getProfession();
		mv.addObject("professionList", professionList);
		return mv;
	}
	
	/**保存用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveU")
	public ModelAndView saveU(HttpServletRequest req, @RequestParam(required=false) MultipartFile avatar) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		logBefore(logger, Jurisdiction.getUsername()+"新增会员");
		ModelAndView mv = this.getModelAndView();
		
		String  ffile = DateUtil.getDays(), fileName = "",  avatarPath = "";
		if (null != avatar && ! avatar.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
			fileName = FileUpload.fileUp(avatar, filePath, this.get32UUID());				//执行上传
			avatarPath = Const.FILEPATHIMG + ffile + "/" + fileName;
		}
		
		PageData pd = new PageData();
		pd.put("USER_ID", this.get32UUID());	//ID
		pd.put("RIGHTS", "");					
		pd.put("LAST_LOGIN", "");				//最后登录时间
		pd.put("IP", "");						//IP
		pd.put("USERNAME", req.getParameter("USERNAME"));
		pd.put("NAME", req.getParameter("NAME"));
		pd.put("ROLE_ID", req.getParameter("ROLE_ID"));
		pd.put("STATUS", req.getParameter("STATUS"));
		pd.put("BZ", req.getParameter("BZ"));
		pd.put("PHONE", req.getParameter("PHONE"));
		pd.put("SFID", req.getParameter("SFID"));
		pd.put("START_TIME", req.getParameter("START_TIME"));
		pd.put("END_TIME", req.getParameter("END_TIME"));
		pd.put("YEARS", req.getParameter("YEARS"));
		pd.put("EMAIL", req.getParameter("EMAIL"));
		pd.put("NUMBER", req.getParameter("NUMBER"));
		pd.put("POSITION", req.getParameter("POSITION"));
		pd.put("GENDER", req.getParameter("GENDER"));
		pd.put("DEPARTMENT", req.getParameter("DEPARTMENT"));
		pd.put("EDU", req.getParameter("EDU"));
		pd.put("MAJOR", req.getParameter("MAJOR"));
		pd.put("SUPERIOR", req.getParameter("SUPERIOR"));
		pd.put("VIOLATION", req.getParameter("VIOLATION"));
		pd.put("AVATAR", avatarPath);
		pd.put("PASSWORD", MD5.md5(req.getParameter("PASSWORD")));
		if(null == appuserService.findByUsername(pd)){
			appuserService.saveU(pd);			//判断新增权限
			mv.addObject("msg","success");
		}else{
			mv.addObject("msg","failed");
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	/**判断用户名是否存在
	 * @return
	 */
	@RequestMapping(value="/hasU")
	@ResponseBody
	public Object hasU(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(appuserService.findByUsername(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**判断手机号是否存在
	 * @return
	 */
	@RequestMapping(value="/hasPhone")
	@ResponseBody
	public Object hasPhone(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(appuserService.findByPhone(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**判断邮箱是否存在
	 * @return
	 */
	@RequestMapping(value="/hasE")
	@ResponseBody
	public Object hasE(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(appuserService.findByEmail(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**判断编码是否存在
	 * @return
	 */
	@RequestMapping(value="/hasN")
	@ResponseBody
	public Object hasN(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(appuserService.findByNumber(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**删除用户
	 * @param out
	 * @throws Exception 
	 */
	@RequestMapping(value="/deleteU")
	public void deleteU(PrintWriter out) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		logBefore(logger, Jurisdiction.getUsername()+"删除会员");
		PageData pd = new PageData();
		pd = this.getPageData();
		appuserService.deleteU(pd);
		out.write("success");
		out.close();
	}
	
	/**修改用户
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editU")
	public ModelAndView editU(HttpServletRequest req, @RequestParam(required=false) MultipartFile avatar) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		logBefore(logger, Jurisdiction.getUsername()+"修改会员");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		String  ffile = DateUtil.getDays(), fileName = "",  avatarPath = "";
		if (null != avatar && ! avatar.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
			fileName = FileUpload.fileUp(avatar, filePath, this.get32UUID());				//执行上传
			avatarPath = Const.FILEPATHIMG + ffile + "/" + fileName;
		}
		pd.put("USER_ID", req.getParameter("USER_ID"));	//ID
		pd.put("RIGHTS", req.getParameter("RIGHTS"));					
		pd.put("LAST_LOGIN", req.getParameter("LAST_LOGIN"));				//最后登录时间
		pd.put("IP", req.getParameter("IP"));						//IP
		pd.put("USERNAME", req.getParameter("USERNAME"));
		pd.put("NAME", req.getParameter("NAME"));
		pd.put("ROLE_ID", req.getParameter("ROLE_ID"));
		pd.put("STATUS", req.getParameter("STATUS"));
		pd.put("BZ", req.getParameter("BZ"));
		pd.put("PHONE", req.getParameter("PHONE"));
		pd.put("SFID", req.getParameter("SFID"));
		pd.put("START_TIME", req.getParameter("START_TIME"));
		pd.put("END_TIME", req.getParameter("END_TIME"));
		pd.put("YEARS", req.getParameter("YEARS"));
		pd.put("EMAIL", req.getParameter("EMAIL"));
		pd.put("NUMBER", req.getParameter("NUMBER"));
		pd.put("POSITION", req.getParameter("POSITION"));
		pd.put("GENDER", req.getParameter("GENDER"));
		pd.put("DEPARTMENT", req.getParameter("DEPARTMENT"));
		pd.put("EDU", req.getParameter("EDU"));
		pd.put("MAJOR", req.getParameter("MAJOR"));
		pd.put("SUPERIOR", req.getParameter("SUPERIOR"));
		pd.put("VIOLATION", req.getParameter("VIOLATION"));
		pd.put("AVATAR", avatarPath);
		pd.put("PASSWORD", req.getParameter("PASSWORD"));
		if(pd.getString("PASSWORD") != null && !"".equals(pd.getString("PASSWORD"))){
			pd.put("PASSWORD", MD5.md5(pd.getString("PASSWORD")));
		}
		appuserService.editU(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**去修改用户页面
	 * @return
	 */
	@RequestMapping(value="/goEditU")
	public ModelAndView goEditU(){
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd.put("ROLE_ID", "2");
			List<Role> roleList = roleService.listAllRolesByPId(pd);//列出会员组角色
			pd = appuserService.findById(pd);						//根据ID读取
			mv.setViewName("system/appuser/appuser_edit");
			mv.addObject("msg", "editU");
			mv.addObject("pd", pd);
			mv.addObject("roleList", roleList);
			
			List<Dictionaries> deparmentList = this.getDepartment();
			mv.addObject("deparmentList", deparmentList);
			
			List<Dictionaries> positionList = this.getPosition();
			mv.addObject("positionList", positionList);
			
			List<Dictionaries> educationList = this.getEducation();
			mv.addObject("educationList", educationList);
			
			List<Dictionaries> professionList = this.getProfession();
			mv.addObject("professionList", professionList);
			mv.addObject("isEdit", "edit");
			return mv;
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**批量删除
	 * @return
	 */
	@RequestMapping(value="/deleteAllU")
	@ResponseBody
	public Object deleteAllU() {
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){} //校验权限
		logBefore(logger, Jurisdiction.getUsername()+"批量删除会员");
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String USER_IDS = pd.getString("USER_IDS");
			if(null != USER_IDS && !"".equals(USER_IDS)){
				String ArrayUSER_IDS[] = USER_IDS.split(",");
				appuserService.deleteAllU(ArrayUSER_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/**导出会员信息到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, Jurisdiction.getUsername()+"导出会员资料");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			if(Jurisdiction.buttonJurisdiction(menuUrl, "cha")){	
				String keywords = pd.getString("keywords");
				if(null != keywords && !"".equals(keywords)){
					pd.put("keywords", keywords.trim());
				}
				String lastLoginStart = pd.getString("lastLoginStart");
				String lastLoginEnd = pd.getString("lastLoginEnd");
				if(lastLoginStart != null && !"".equals(lastLoginStart)){
					pd.put("lastLoginStart", lastLoginStart+" 00:00:00");
				}
				if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
					pd.put("lastLoginEnd", lastLoginEnd+" 00:00:00");
				} 
				Map<String,Object> dataMap = new HashMap<String,Object>();
				List<String> titles = new ArrayList<String>();
				titles.add("用户名"); 		//1
				titles.add("编号");  		//2
				titles.add("姓名");			//3
				titles.add("手机号");		//4
				titles.add("身份证号");		//5
				titles.add("等级");			//6
				titles.add("邮箱");			//7
				titles.add("最近登录");		//8
				titles.add("到期时间");		//9
				titles.add("上次登录IP");	//10
				dataMap.put("titles", titles);
				List<PageData> userList = appuserService.listAllUser(pd);
				List<PageData> varList = new ArrayList<PageData>();
				for(int i=0;i<userList.size();i++){
					PageData vpd = new PageData();
					vpd.put("var1", userList.get(i).getString("USERNAME"));		//1
					vpd.put("var2", userList.get(i).getString("NUMBER"));		//2
					vpd.put("var3", userList.get(i).getString("NAME"));			//3
					vpd.put("var4", userList.get(i).getString("PHONE"));		//4
					vpd.put("var5", userList.get(i).getString("SFID"));			//5
					vpd.put("var6", userList.get(i).getString("ROLE_NAME"));	//6
					vpd.put("var7", userList.get(i).getString("EMAIL"));		//7
					vpd.put("var8", userList.get(i).getString("LAST_LOGIN"));	//8
					vpd.put("var9", userList.get(i).getString("END_TIME"));		//9
					vpd.put("var10", userList.get(i).getString("IP"));			//10
					varList.add(vpd);
				}
				dataMap.put("varList", varList);
				ObjectExcelView erv = new ObjectExcelView();
				mv = new ModelAndView(erv,dataMap);
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**打开上传EXCEL页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goUploadExcel")
	public ModelAndView goUploadExcel()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/appuser/uploadexcel");
		return mv;
	}
	
	/**下载模版
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/downExcel")
	public void downExcel(HttpServletResponse response)throws Exception{
		FileDownload.fileDownload(response, PathUtil.getClasspath() + Const.FILEPATHFILE + "appUsers.xls", "appUsers.xls");
	}
	/**从EXCEL导入到数据库
	 * @param file
	 * @return
	 * @throws Exception
	 *
	 * var0: 姓名
	 * var1: 性别
	 * var2: 身份证号码
	 * var3: 手机号
	 * var4: 专业
	 * var5: 学历
	 * var6: 部门
	 * var7: 角色
	 * var8: 职位
	 * var9: 直属上级
	 * var10: 违规情况
	 */
	@RequestMapping(value="/readExcel")
	public ModelAndView readExcel(
			@RequestParam(value="excel",required=false) MultipartFile file
			) throws Exception{
		FHLOG.save(Jurisdiction.getUsername(), "从EXCEL导入到数据库");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;}
		if (null != file && !file.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
			String fileName =  FileUpload.fileUp(file, filePath, "appUsersexcel");							//执行上传
			List<PageData> listPd = (List)ObjectExcelRead2.readExcel(filePath, fileName, 2, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
			System.err.println("listPd: "+listPd.toString());
			/*存入数据库操作======================================*/
			pd.put("RIGHTS", "");					//权限
			pd.put("LAST_LOGIN", "");				//最后登录时间
			pd.put("IP", "");						//IP
			pd.put("STATUS", "1");					//状态
			pd.put("SKIN", "default");				//默认皮肤
			for(int i=0;i<listPd.size();i++){
				pd.put("USER_ID",this.get32UUID());
				String name = listPd.get(i).getString("var0");//name
				if (StringUtil.isEmpty(name,true)) {
					continue;
				}
				pd.put("NAME",name);
				String gender = listPd.get(i).getString("var1");//性别
				if (!StringUtil.isEmpty(gender,true)) {
					if (gender.equals("男")){
						pd.put("GENDER",1);
					}else if(gender.equals("女")){
						pd.put("GENDER",2);
					}
				}
				String sfid = listPd.get(i).getString("var2");//身份证号码
				if (!StringUtil.isEmpty(sfid,true)) {
					pd.put("SFID",sfid);
				}

				String phoneNumber = listPd.get(i).get("var3").toString();//手机号码
				if (StringUtil.isEmpty(phoneNumber,true)) {
					continue;
				}else{
					pd.put("PHONE",phoneNumber);
					//根据电话号码去重
					PageData byPhone = appuserService.findByPhone(pd);
					if (byPhone != null) {
						continue;
					}
				}
				String major = listPd.get(i).getString("var4");//专业
				if (!StringUtil.isEmpty(major,true)) {
					//根据名称查询对应的编码
					PageData byNamePd = dictionariesService.findBianmaByName(major);
					//判断是否存在该名称
					if (byNamePd == null) {
						//1. 不存在,添加
						//1.1 获取父级ID
						String parentId = dictionariesService.findIdByBianma("012");
						if (!StringUtil.isEmpty(parentId,true)) {
							PageData max = new PageData();
							max.put("parentId",parentId);
							PageData maxPd = dictionariesService.getSubDicMaxOrderByParent(max);
							if (maxPd != null) {
								//开始调用数据字典的save方法
								PageData pageData = new PageData();
								pageData.put("DICTIONARIES_ID",this.get32UUID());
								pageData.put("PARENT_ID",parentId);
								pageData.put("BIANMA","0"+(Integer.parseInt(maxPd.get("BIANMA").toString())+1));
								pageData.put("NAME",major);
								pageData.put("NAME_EN",GetPinyin.getPingYin(major));
								pageData.put("ORDER_BY",Integer.parseInt(maxPd.get("ORDER_BY").toString())+1);
								dictionariesService.save(pageData);
								pd.put("MAJOR","0"+(Integer.parseInt(maxPd.get("BIANMA").toString())+1));
							}
						}

					}else{
						//2. 存在.取编码
						pd.put("MAJOR",byNamePd.get("BIANMA"));
					}
				}
				String edu = listPd.get(i).getString("var5");//学历
				if (!StringUtil.isEmpty(edu,true)) {
					//根据名称查询对应的编码
					PageData byNamePd = dictionariesService.findBianmaByName(edu);
					//判断是否存在该名称
					if (byNamePd == null) {
						//1. 不存在,添加
						//1.1 获取父级ID
						String parentId = dictionariesService.findIdByBianma("011");
						if (!StringUtil.isEmpty(parentId,true)) {
							PageData max = new PageData();
							max.put("parentId",parentId);
							PageData maxPd = dictionariesService.getSubDicMaxOrderByParent(max);
							if (maxPd != null) {
								//开始调用数据字典的save方法
								PageData pageData = new PageData();
								pageData.put("DICTIONARIES_ID",this.get32UUID());
								pageData.put("PARENT_ID",parentId);
								pageData.put("BIANMA","0"+(Integer.parseInt(maxPd.get("BIANMA").toString())+1));
								pageData.put("NAME",edu);
								pageData.put("NAME_EN",GetPinyin.getPingYin(edu));
								pageData.put("ORDER_BY",Integer.parseInt(maxPd.get("ORDER_BY").toString())+1);
								dictionariesService.save(pageData);
								pd.put("EDU","0"+(Integer.parseInt(maxPd.get("BIANMA").toString())+1));
							}
						}

					}else{
						//2. 存在.取编码
						pd.put("EDU",byNamePd.get("BIANMA"));
					}
				}
				String department = listPd.get(i).getString("var6");//部门
				if (!StringUtil.isEmpty(department,true)) {
					//根据名称查询对应的编码
					PageData byNamePd = dictionariesService.findBianmaByName(department);
					//判断是否存在该名称
					if (byNamePd == null) {
						//1. 不存在,添加
						//1.1 获取父级ID
						String parentId = dictionariesService.findIdByBianma("002");
						if (!StringUtil.isEmpty(parentId,true)) {
							PageData max = new PageData();
							max.put("parentId",parentId);
							PageData maxPd = dictionariesService.getSubDicMaxOrderByParent(max);
							if (maxPd != null) {
								//开始调用数据字典的save方法
								PageData pageData = new PageData();
								pageData.put("DICTIONARIES_ID",this.get32UUID());
								pageData.put("PARENT_ID",parentId);
								pageData.put("BIANMA","0"+(Integer.parseInt(maxPd.get("BIANMA").toString())+1));
								pageData.put("NAME",department);
								pageData.put("NAME_EN",GetPinyin.getPingYin(department));
								pageData.put("ORDER_BY",Integer.parseInt(maxPd.get("ORDER_BY").toString())+1);
								dictionariesService.save(pageData);
								pd.put("DEPARTMENT","0"+(Integer.parseInt(maxPd.get("BIANMA").toString())+1));
							}
						}

					}else{
						//2. 存在.取编码
						pd.put("DEPARTMENT",byNamePd.get("BIANMA"));
					}
				}
				String position = listPd.get(i).getString("var8");//职位
				if (!StringUtil.isEmpty(position,true)) {
					//根据名称查询对应的编码
					PageData byNamePd = dictionariesService.findBianmaByName(position);
					//判断是否存在该名称
					if (byNamePd == null) {
						//1. 不存在,添加
						//1.1 获取父级ID
						String parentId = dictionariesService.findIdByBianma("009");
						if (!StringUtil.isEmpty(parentId,true)) {
							PageData max = new PageData();
							max.put("parentId",parentId);
							PageData maxPd = dictionariesService.getSubDicMaxOrderByParent(max);
							if (maxPd != null) {
								//开始调用数据字典的save方法
								PageData pageData = new PageData();
								pageData.put("DICTIONARIES_ID",this.get32UUID());
								pageData.put("PARENT_ID",parentId);
								pageData.put("BIANMA","00"+(Integer.parseInt(maxPd.get("BIANMA").toString())+1));
								pageData.put("NAME",position);
								pageData.put("NAME_EN",GetPinyin.getPingYin(position));
								pageData.put("ORDER_BY",Integer.parseInt(maxPd.get("ORDER_BY").toString())+1);
								dictionariesService.save(pageData);
								pd.put("POSITION","00"+(Integer.parseInt(maxPd.get("BIANMA").toString())+1));
							}
						}

					}else{
						//2. 存在.取编码
						pd.put("POSITION",byNamePd.get("BIANMA"));
					}
				}
				String role = listPd.get(i).getString("var7");//角色
				if (!StringUtil.isEmpty(role,true)) {
					//根据名称查询对应的ID
					PageData roleIdByRoleNamePd = roleService.getRoleIdByRoleName(role);
					if (roleIdByRoleNamePd != null) {
						pd.put("ROLE_ID",roleIdByRoleNamePd.getString("ROLE_ID"));
					}
				}
				pd.put("SUPERIOR",listPd.get(i).getString("var9"));   //直属上级
				pd.put("VIOLATION",listPd.get(i).getString("var10"));   //违规情况
				pd.put("PASSWORD",Md5.getMd5String("123"));
				appuserService.saveU(pd);
			}
			/*存入数据库操作======================================*/
			mv.addObject("msg","success");
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
}
