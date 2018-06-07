package com.fh.service.system.dictionaries;

import java.util.List;

import com.fh.entity.Page;
import com.fh.entity.system.Dictionaries;
import com.fh.util.PageData;

/** 
 * 说明： 数据字典接口类
 * 创建人：FH Q313596790
 * 创建时间：2015-12-16
 * @version
 */
public interface DictionariesManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;

	/**通过编码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByBianma(PageData pd)throws Exception;
	
	/**
	 * 通过ID获取其子级列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> listSubDictByParentId(String parentId) throws Exception;


	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Dictionaries> listAllDict(String parentId) throws Exception;
	
	/**排查表检查是否被占用
	 * @param pd
	 * @throws Exception
	 */
	public PageData findFromTbs(PageData pd)throws Exception;

	/**
	 * 根据父类编码查询 
	 */
	public List<PageData> listAllByParentBianma(PageData pd) throws Exception;
	
	/**
	 * 根据父类编码查询 
	 */
	public List<PageData> listAllByParentBianma(String bianma) throws Exception;
	
	/**获取子目录的最大序号
	 * @param pd
	 * @throws Exception
	 */
	public PageData getSubDicMaxOrderByParent(PageData pd)throws Exception;

	/**
	 * 通过名称查询对应的编码
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public PageData findBianmaByName(String name) throws Exception;

	/**
	 * 根据编码查询对应的ID,用于数据的导入。
	 * @param bianma
	 * @return
	 * @throws Exception
	 */
	public String findIdByBianma(String bianma) throws Exception;
	
	/**
	 * 设置部门属性
	 */
	public void setDepartmentList(PageData pd) throws Exception;
	
	/**
	 * 设置:费用类型
	 * @param pd
	 * @throws Exception 
	 */
	public void setFeeTypes(PageData pd,String bianma) throws Exception;
	
	/**
	 * 获取交通工具
	 * @param pd
	 * @throws Exception 
	 */
	public void setVehicle(PageData pd) throws Exception;
	
	/**
	 * 获取交通工具
	 * @param pd
	 * @throws Exception 
	 */
	public List<PageData> getParentAreas() throws Exception;
	
	/**
	 * 根据编码查父编码
	 * @param bianma
	 * @throws Exception
	 */
	public String findParentBianaByBianma(String bianma) throws Exception;

	/**
	 * 根据编码模糊查询名称
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getBianmaByName(String name) throws Exception;
}

