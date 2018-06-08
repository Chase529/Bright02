package com.fh.controller.app.dictionary;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.core.ErrorCode;
import com.fh.core.HttpJsonResult;
import com.fh.entity.system.Dictionaries;
import com.fh.service.system.appuser.AppuserManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.util.PageData;
import com.fh.util.consts.ApprovalConsts;
import com.fh.util.consts.DictionaryConsts;

@Controller
@RequestMapping(value="/appDictionary")
@SuppressWarnings({"rawtypes"})
public class AppDictionaryController extends BaseController{
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	
	/**
	 * 获取梁板性质
	 * @api {GET} /appDictionary/getBeamboardType 获取梁板性质
	 * @apiName getBeamboardType
	 * @apiGroup appDictionary
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getBeamboardType", method = RequestMethod.GET)
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getBeamboardType() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.BEAMBOARD_TYPE);
		PageData pd2 = dictionariesService.findByBianma(pd);
		List<Dictionaries> beamboardTypeList = null;
		if(pd2 != null){
			String parentId = pd2.getString("DICTIONARIES_ID");
			beamboardTypeList = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(beamboardTypeList);
		return jsonResult;
	}
	
	/**
	 * 获取设备类型
	 * @api {GET} /appDictionary/getDeviceType 获取设备类型
	 * @apiName getDeviceType
	 * @apiGroup appDictionary
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getDeviceType",  method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getDeviceType() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.DEVICE_TYPE);
		PageData pd2 = dictionariesService.findByBianma(pd);
		List<Dictionaries> deviceTypeList = null;
		if(pd2 != null){
			String parentId = pd2.getString("DICTIONARIES_ID");
			deviceTypeList = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(deviceTypeList);
		return jsonResult;
	}
	
	/**
	 * 获取设备规格
	 * @api {GET} /appDictionary/getDeviceSpecificate 获取设备规格
	 * @apiName getDeviceSpecificate
	 * @apiGroup appDictionary
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getDeviceSpecificate", method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getSpecificate() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.DEVICE_SPECIFICATION);
		PageData pd2 = dictionariesService.findByBianma(pd);
		List<Dictionaries> specificateList = null;
		if(pd2 != null){
			String parentId = pd2.getString("DICTIONARIES_ID");
			specificateList = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(specificateList);
		return jsonResult;
	}
	
	/**
	 * 获取设备性质
	 * @api {GET} /appDictionary/getDeviceNature 获取设备性质
	 * @apiName getDeviceNature
	 * @apiGroup appDictionary
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getDeviceNature", method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getDeviceNature() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.DEVICE_NATURE);
		PageData pd2 = dictionariesService.findByBianma(pd);
		List<Dictionaries> natureList = null;
		if(pd2 != null){
			String parentId = pd2.getString("DICTIONARIES_ID");
			natureList = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(natureList);
		return jsonResult;
	}
	
	
	/**
	 * 获取石油类别
	 * @api {GET} /appDictionary/getOilType 获取石油种类
	 * @apiName getDeviceNature
	 * @apiGroup appDictionary
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getOilType", method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getOilType() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.OIL_TYPE);
		PageData pd2 = dictionariesService.findByBianma(pd);
		List<Dictionaries> oilTypeList = null;
		if(pd2 != null){
			String parentId = pd2.getString("DICTIONARIES_ID");
			oilTypeList = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(oilTypeList);
		return jsonResult;
	}
	
	/**
	 * 获取巡查性质
	 * @api {GET} /appDictionary/getInspectNature 获取安全巡查性质
	 * @apiName getInspectNature
	 * @apiGroup appDictionary
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getInspectNature", method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getInspectNature() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.INSPECT_NATURE);
		PageData pd2 = dictionariesService.findByBianma(pd);
		List<Dictionaries> inspectNatureList = null;
		if(pd2 != null){
			String parentId = pd2.getString("DICTIONARIES_ID");
			inspectNatureList = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(inspectNatureList);
		return jsonResult;
	}
	
	
	/**
	 * 获取项目部门
	 * @api {GET} /appDictionary/getDeparments 获取部门信息
	 * @apiName getDeparments
	 * @apiGroup appDictionary
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getDeparments", method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getDeparments() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.GET_DEPARTMENTS);
		PageData pd2 = dictionariesService.findByBianma(pd);
		List<Dictionaries> deparmentList = null;
		if(pd2 != null){
			String parentId = pd2.getString("DICTIONARIES_ID");
			deparmentList = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(deparmentList);
		return jsonResult;
	}
	/**
	 * 获取职位信息
	 * @api {GET} /appDictionary/getPosition 获取职位信息
	 * @apiName getPosition
	 * @apiGroup appDictionary
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getPosition", method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getPosition() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.GET_POSITION);
		PageData pd2 = dictionariesService.findByBianma(pd);
		List<Dictionaries> positionList = null;
		if(pd2 != null){
			String parentId = pd2.getString("DICTIONARIES_ID");
			positionList = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(positionList);
		return jsonResult;
	}
	
	
	/**
	 * 获取学历信息
	 * @api {GET} /appDictionary/getEducation 获取学历信息
	 * @apiName getEducation
	 * @apiGroup appDictionary
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getEducation", method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getEducation() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.GET_EDUCATION);
		pd = dictionariesService.findByBianma(pd);
		List<Dictionaries> educationList = null;
		if(pd != null){
			String parentId = pd.getString("DICTIONARIES_ID");
			educationList = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(educationList);
		return jsonResult;
	}
	
	/**
	 * 获取专业信息
	 * @api {GET} /appDictionary/getProfession 获取专业信息
	 * @apiName getProfession
	 * @apiGroup appDictionary
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getProfession", method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getProfession() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.GET_PROFESSION);
		PageData pd2 = dictionariesService.findByBianma(pd);
		List<Dictionaries> professionList = null;
		if(pd2 != null){
			String parentId = pd2.getString("DICTIONARIES_ID");
			professionList = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(professionList);
		return jsonResult;
	}
	
	/**
	 * 获取设备类型
	 * @api {GET} /appDictionary/getSecurityCheckNature 获取安全日常检查性质
	 * @apiName getSecurityCheckNature
	 * @apiGroup appDictionary
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getSecurityCheckNature",  method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getSecurityCheckNature() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.SECURITY_CHECK_NATURE);
		PageData pd2 = dictionariesService.findByBianma(pd);
		List<Dictionaries> list = null;
		if(pd2 != null){
			String parentId = pd2.getString("DICTIONARIES_ID");
			list = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(list);
		return jsonResult;
	}

	/**
	 * 获取质量巡查性质
	 * @api {GET} /appDictionary/getQualityInspectNature 获取质量巡查性质
	 * @apiName getQualityInspectNature
	 * @apiGroup appDictionary
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getQualityInspectNature",  method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getQualityInspectNature() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.QUALITY_INSPECT_NATURE);
		PageData pd2 = dictionariesService.findByBianma(pd);
		List<Dictionaries> list = null;
		if(pd2 != null){
			String parentId = pd2.getString("DICTIONARIES_ID");
			list = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(list);
		return jsonResult;
	}
	
	/**
	 * 获取质量日常检查性质
	 * @api {GET} /appDictionary/getQualityCheckNature 获取质量日常检查性质
	 * @apiName getQualityCheckNature
	 * @apiGroup appDictionary
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getQualityCheckNature",  method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getQualityCheckNature() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.QUALITY_CHECK_NATURE);
		PageData pd2 = dictionariesService.findByBianma(pd);
		List<Dictionaries> list = null;
		if(pd2 != null){
			String parentId = pd2.getString("DICTIONARIES_ID");
			list = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(list);
		return jsonResult;
	}
	
	/**
	 * 获取物资科目
	 * @api {GET} /appDictionary/getMaterialSubjects 获取物资科目
	 * @apiName getMaterialSubjects
	 * @apiGroup appDictionary
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getMaterialSubjects",  method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getMaterialSubjects() throws Exception{
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		pd.put("BIANMA", DictionaryConsts.MATERIAL_SUBJECTS);
		PageData pd2 = dictionariesService.findByBianma(pd);
		List<Dictionaries> list = null;
		if(pd2 != null){
			String parentId = pd2.getString("DICTIONARIES_ID");
			list = dictionariesService.listSubDictByParentId(parentId);
		}
		jsonResult.setData(list);
		return jsonResult;
	}

	/**
	 * 获取请假类型
	 * @api {GET} /appDictionary/getLeaveType 获取请假类型
	 * @apiName getLeaveType
	 * @apiGroup appDictionary
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getLeaveType",  method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getLeaveType(){
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		try {
			pd.put("BIANMA", DictionaryConsts.LEAVE_TYPE);
			PageData pd2 = dictionariesService.findByBianma(pd);
			List<Dictionaries> list = null;
			if(pd2 != null){
				String parentId = pd2.getString("DICTIONARIES_ID");
				list = dictionariesService.listSubDictByParentId(parentId);
			}
			jsonResult.setData(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 获取请假类型
	 * @api {GET} /appDictionary/getBoxBeamCategory 获取箱梁类别
	 * @apiName getBoxBeamCategory
	 * @apiGroup appDictionary
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getBoxBeamCategory",  method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getBoxBeamCategory(){
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		try {
			pd.put("BIANMA", DictionaryConsts.BOX_BEAM_CATEGORY);
			PageData pd2 = dictionariesService.findByBianma(pd);
			List<Dictionaries> list = new ArrayList<Dictionaries>();
			if(pd2 != null){
				String parentId = pd2.getString("DICTIONARIES_ID");
				list = dictionariesService.listSubDictByParentId(parentId);
			}
			jsonResult.setData(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 获取设备维护类型
	 * @api {GET} /appDictionary/getDeviceMaintainType 获取设备维护类型
	 * @apiName getDeviceMaintainType
	 * @apiGroup appDictionary
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getDeviceMaintainType",  method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getDeviceMaintainType(){
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		try {
			pd.put("BIANMA", DictionaryConsts.DEVICE_MAINTAIN_TYPE);
			PageData pd2 = dictionariesService.findByBianma(pd);
			List<Dictionaries> list = new ArrayList<Dictionaries>();
			if(pd2 != null){
				String parentId = pd2.getString("DICTIONARIES_ID");
				list = dictionariesService.listSubDictByParentId(parentId);
			}
			jsonResult.setData(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 获取操作证类型
	 * @api {GET} /appDictionary/getOperatorCardType 获取操作证类型
	 * @apiName getOperatorCardType
	 * @apiGroup appDictionary
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getOperatorCardType",  method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getOperatorCardType(){
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		try {
			pd.put("BIANMA", DictionaryConsts.OPERATOR_CARD_TYPE);
			PageData pd2 = dictionariesService.findByBianma(pd);
			List<Dictionaries> list = new ArrayList<Dictionaries>();
			if(pd2 != null){
				String parentId = pd2.getString("DICTIONARIES_ID");
				list = dictionariesService.listSubDictByParentId(parentId);
			}
			jsonResult.setData(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
	
	
	/**
	 * 获取各个分区
	 * @api {GET} /appDictionary/getRegionName 获取工作区域
	 * @apiName getRegionName
	 * @apiGroup appDictionary
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getRegionName",  method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getRegionName(){
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		try {
			pd.put("BIANMA", DictionaryConsts.REGION_NAME);
			PageData pd2 = dictionariesService.findByBianma(pd);
			List<Dictionaries> list = new ArrayList<Dictionaries>();
			if(pd2 != null){
				String parentId = pd2.getString("DICTIONARIES_ID");
				list = dictionariesService.listSubDictByParentId(parentId);
			}
			jsonResult.setData(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}

	/**
	 * 获取难点类型
	 * @api {GET} /appDictionary/getDifficultyType 获取难点类型
	 * @apiName getLeaveType
	 * @apiGroup appDictionary
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getDifficultyType",  method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getDifficultyType(){
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		try {
			pd.put("BIANMA", DictionaryConsts.DIFFICALTY_TYPE);
			PageData pd2 = dictionariesService.findByBianma(pd);
			List<Dictionaries> list = null;
			if(pd2 != null){
				String parentId = pd2.getString("DICTIONARIES_ID");
				list = dictionariesService.listSubDictByParentId(parentId);
			}
			jsonResult.setData(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
	
	
	
	
	/**
	 * -------------------------审批字典项借口------------------------
	 */
	/**
	 * 获取所有的审批类型
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAllApprovalTypes",method= RequestMethod.POST)
	@ResponseBody
	public HttpJsonResult getAllApprovalTypes() throws Exception {
		List<PageData> date = dictionariesService.listAllByParentBianma(ApprovalConsts.APPROVAL_BIANMA);
		return this.getSuccessHttpResult(date);
	}
	
	/**
	 * 查询所有地区
	 */
	@RequestMapping(value = "finaAllAreas",method= RequestMethod.POST)
	@ResponseBody
	public HttpJsonResult finaAllAreas() throws Exception {
		List<PageData> parents = dictionariesService.listAllByParentBianma(ApprovalConsts.AREA_CODE);
		for(PageData parent: parents) {
			List<PageData> sons = dictionariesService.listAllByParentBianma(parent.getString("BIANMA"));
			parent.put("sonsArea", sons);
		}
		return this.getSuccessHttpResult(parents);
	}
	
	
	/**
	 * 根据父级查询所有子集
	 */
	@RequestMapping(value = "finaByParentBianma",method= RequestMethod.POST)
	@ResponseBody
	public HttpJsonResult finaByParentBianma(String parentBianma) throws Exception {
		List<PageData> parents = dictionariesService.listAllByParentBianma(parentBianma);
		return this.getSuccessHttpResult(parents);
	}
	
	
	/**
	 * 获取费用类型
	 * @api {GET} /appDictionary/getFeeType 获取费用类型
	 * @apiName getFeeType
	 * @apiGroup appDictionary
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getFeeType",  method = {RequestMethod.GET})
	public @ResponseBody HttpJsonResult<List<Dictionaries>> getFeeType(){
		HttpJsonResult<List<Dictionaries>> jsonResult = new HttpJsonResult<List<Dictionaries>>();
		PageData pd = new PageData();
		try {
			pd.put("BIANMA", DictionaryConsts.FEE_TYPE);
			PageData pd2 = dictionariesService.findByBianma(pd);
			List<Dictionaries> list = null;
			if(pd2 != null){
				String parentId = pd2.getString("DICTIONARIES_ID");
				list = dictionariesService.listSubDictByParentId(parentId);
			}
			jsonResult.setData(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 获取城市
	 * @api {POST} /appDictionary/getCities 获取城市
	 * @apiName getCities
	 * @apiGroup appDictionary
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCities",  method = {RequestMethod.POST})
	public @ResponseBody HttpJsonResult<List<PageData>> getCities(HttpServletRequest req){
		HttpJsonResult<List<PageData>> jsonResult = new HttpJsonResult<List<PageData>>();
		try {
		req.setCharacterEncoding("UTF-8");
		PageData pd = this.getPageData();
			System.out.println(pd.toString());
			List<PageData> list = null;
			String cityName = pd.getString("city");
			list = dictionariesService.getBianmaByName(cityName);
			jsonResult.setData(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			jsonResult.setCode(ErrorCode.SYSTEM_ERROR);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
}
