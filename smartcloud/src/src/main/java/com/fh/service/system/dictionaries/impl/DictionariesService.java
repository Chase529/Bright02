package com.fh.service.system.dictionaries.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.Dictionaries;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.util.PageData;
import com.fh.util.StringUtil;
import com.fh.util.consts.ApprovalConsts;

/** 
 * 说明： 数据字典
 * 创建人：FH Q313596790
 * 创建时间：2015-12-16
 * @version
 */
@Service("dictionariesService")
public class DictionariesService implements DictionariesManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("DictionariesMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("DictionariesMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("DictionariesMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("DictionariesMapper.datalistPage", page);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("DictionariesMapper.findById", pd);
	}
	
	/**通过编码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByBianma(PageData pd)throws Exception{
		return (PageData)dao.findForObject("DictionariesMapper.findByBianma", pd);
	}
	
	/**
	 * 通过ID获取其子级列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Dictionaries> listSubDictByParentId(String parentId) throws Exception {
		return (List<Dictionaries>) dao.findForList("DictionariesMapper.listSubDictByParentId", parentId);
	}


	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> listAllDict(String parentId) throws Exception {
		List<Dictionaries> dictList = this.listSubDictByParentId(parentId);
		for(Dictionaries dict : dictList){
			dict.setTreeurl("dictionaries/list.do?DICTIONARIES_ID="+dict.getDICTIONARIES_ID());
			dict.setSubDict(this.listAllDict(dict.getDICTIONARIES_ID()));
			dict.setTarget("treeFrame");
		}
		return dictList;
	}
	
	/**排查表检查是否被占用
	 * @param pd
	 * @throws Exception
	 */
	public PageData findFromTbs(PageData pd)throws Exception{
		return (PageData)dao.findForObject("DictionariesMapper.findFromTbs", pd);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listAllByParentBianma(PageData pd) throws Exception{
		return (List<PageData>)dao.findForList("DictionariesMapper.listAllByParentBianma", pd);
	}
	/**
	 * 根据父类编码查询 
	 */
	public List<PageData> listAllByParentBianma(String bianma) throws Exception{
		PageData area = new PageData();
		area.put("BIANMA", bianma);
 		return this.listAllByParentBianma(area );
	}
	/**获取子目录的最大序号
	 * @param pd
	 * @throws Exception
	 */
	public PageData getSubDicMaxOrderByParent(PageData pd)throws Exception{
		return (PageData)dao.findForObject("DictionariesMapper.getSubDicMaxOrderByParent", pd);
	}

	@Override
	public PageData findBianmaByName(String name) throws Exception {
		return (PageData)dao.findForObject("DictionariesMapper.findBianmaByName", name);
	}

	@Override
	public String findIdByBianma(String bianma) throws Exception {
		return (String) dao.findForObject("DictionariesMapper.findIdByBianma", bianma);
	}

	/**
	 * 设置部门属性
	 */
	public void setDepartmentList(PageData pd) throws Exception {
		List<PageData> departmentList = this.listAllByParentBianma(ApprovalConsts.DEPARTMENT_CODE);
		
		pd.put("DEPARTMENT_BIANMAS", StringUtil.getAppendStr(departmentList,"BIANMA"));
		pd.put("DEPARTMENT_NAMES", StringUtil.getAppendStr(departmentList,"NAME"));
	}
	
	/**
	 * 设置:费用类型
	 * @param pd
	 * @throws Exception 
	 */
	public void setFeeTypes(PageData pd,String bianma) throws Exception {
		List<PageData> types = this.listAllByParentBianma(bianma );

		pd.put("ALL_FEE_TYPES_BIANMAS", StringUtil.getAppendStr(types, "BIANMA"));
		pd.put("ALL_FEE_TYPES_NAMES", StringUtil.getAppendStr(types, "NAME"));
	}
	
	/**
	 * 获取交通工具
	 * @param pd
	 * @throws Exception 
	 */
	public void setVehicle(PageData pd) throws Exception {
		List<PageData> types = this.listAllByParentBianma(ApprovalConsts.Vehicle );

		pd.put("all_vehicle_bianmas", StringUtil.getAppendStr(types, "BIANMA"));
		pd.put("all_vehicle_names", StringUtil.getAppendStr(types, "NAME"));
	}
	
	/**
	 * 获取交通工具
	 * @param pd
	 * @return 
	 * @throws Exception 
	 */
	public List<PageData> getParentAreas() throws Exception{
		return this.listAllByParentBianma(ApprovalConsts.AREA_CODE );
	}
	
	
	/**
	 * 根据编码查父编码
	 * @param bianma
	 * @throws Exception
	 */
	public String findParentBianaByBianma(String bianma) throws Exception{
		PageData pd = new PageData();
		pd.put("BIANMA", bianma);
		String pid = findByBianma(pd).getString("PARENT_ID");
		pd.put("DICTIONARIES_ID", pid);
		return this.findById(pd).getString("BIANMA");
	}

	@Override
	public List<PageData> getBianmaByName(String name) throws Exception {
		return  (List<PageData>)dao.findForList("DictionariesMapper.getBianmaByName", name);
	}
}

