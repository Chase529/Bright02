package com.fh.controller.web.projectpano;

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

import com.fh.service.web.project.ProjectManager;
import com.fh.service.web.resource.ResourceManager;
import com.fh.util.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.web.projectpano.ProjectPanoManager;

/** 
 * 说明：项目全景图模块
 * 创建人：admin
 * 创建时间：2018-04-09
 */
@Controller
@RequestMapping(value="/projectpano")
public class ProjectPanoController extends BaseController {
	
	String menuUrl = "projectpano/list.do"; //菜单地址(权限用)
	@Resource(name="projectpanoService")
	private ProjectPanoManager projectpanoService;

	@Resource(name="projectService")
	private ProjectManager projectService;

	@Resource(name="resourceService")
	private ResourceManager resourceService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(@RequestParam("files")MultipartFile[] files,
							 @RequestParam("PROJECT_ID") String projectId,
							 HttpServletRequest request, HttpServletResponse response) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增ProjectPano");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {

				MultipartFile file = files[i];
				// 保存文件
				saveFile(projectId, file);
			}
		}
		pd.put("PANO_ID", this.get32UUID());
		pd.put("PROJECT_ID", projectId);
		pd.put("RESOURCE_ID", files.length>1?"多张":"单张");
		projectpanoService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}



	/***
	 * 保存文件
	 *
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private void saveFile(String projectID, MultipartFile file) throws Exception {
		String ffile = DateUtil.getDays(), fileName = ""; // ffile就是文件名称
		MultipartFile multipartFile = (CommonsMultipartFile) file;
		fileName = multipartFile.getOriginalFilename();
		String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String prefixLower = prefix.toLowerCase().trim(); // 文件的后缀名称 也就是文件名
		String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile; // 文件上传路径
		fileName = FileUpload.fileUp(file, filePath, this.get32UUID());// 执行上传
		String path = "/"+Const.FILEPATHIMG + ffile + "/" + fileName;
		System.err.println("------------>" + path);
		PageData saveResource = new PageData();
		String resourceId = this.get32UUID();
		saveResource.put("RESOURCE_ID", resourceId);
		saveResource.put("RECORD_ID", projectID);
		saveResource.put("URL", path);
		saveResource.put("TYPE", "1");
		saveResource.put("CREATE_TIME", this.getCurrentTime());
		saveResource.put("CREATE_USER_ID", "");
		saveResource.put("STATUS", "0");
		resourceService.save(saveResource);
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除ProjectPano");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		projectpanoService.delete(pd);
		resourceService.deleteByRecordId(pd.getString("PROJECT_ID")); //刪除照片
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@RequestParam("files")MultipartFile[] files,
							 @RequestParam("PROJECT_ID") String projectId,
							 HttpServletRequest request, HttpServletResponse response) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改ProjectPano");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		resourceService.deleteByRecordId(pd.getString("PROJECT_ID"));
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {

				MultipartFile file = files[i];
				// 保存文件
				saveFile(projectId, file);
			}
		}
		projectpanoService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表ProjectPano");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = projectpanoService.list(page);	//列出ProjectPano列表
		mv.setViewName("web/projectpano/projectpano_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData>proList =  projectService.listAll(pd);
		mv.setViewName("web/projectpano/projectpano_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		mv.addObject("proList", proList);
		return mv;
	}

	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData>proList =  projectService.listAll(pd);
		pd = projectpanoService.findById(pd);	//根据ID读取
		mv.setViewName("web/projectpano/projectpano_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		mv.addObject("proList", proList);
		return mv;
	}

	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除ProjectPano");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			projectpanoService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出ProjectPano到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("项目id");	//1
		titles.add("资源id");	//2
		dataMap.put("titles", titles);
		List<PageData> varOList = projectpanoService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("PROJECT_ID"));	    //1
			vpd.put("var2", varOList.get(i).getString("RESOURCE_ID"));	    //2
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
