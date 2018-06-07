package com.fh.service.web.projectpano.impl;

import java.util.List;
import javax.annotation.Resource;

import com.fh.service.web.resource.ResourceManager;
import com.fh.util.*;
import com.fh.util.consts.ApprovalConsts;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.service.web.projectpano.ProjectPanoManager;

/** 
 * 说明： 项目全景图模块
 * 创建人：FH admin
 * 创建时间：2018-04-09
 * @version
 */
@Service("projectpanoService")
public class ProjectPanoService implements ProjectPanoManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Resource(name="resourceService")
	private ResourceManager resourceService;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("ProjectPanoMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ProjectPanoMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("ProjectPanoMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ProjectPanoMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ProjectPanoMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		PageData res = (PageData)dao.findForObject("ProjectPanoMapper.findById", pd);
		this.setFiles(res.getString("PROJECT_ID"),res);
		return res;
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ProjectPanoMapper.deleteAll", ArrayDATA_IDS);
	}




	/**
	 * 设置关联属性
	 * @throws Exception
	 */
	public void setRelationAttr(PageData pd) throws Exception {
		//获取通用的审批单id
		String id = pd.getString("PROJECT_ID");
		//设置文件
		this.setFiles(id, pd);
	}

	/**
	 * 设置附件信息
	 */
	protected void setFiles(String recordId,PageData pd) throws Exception {
		PageData pageData = new PageData();
		pageData.put("RECORD_ID", recordId);
		List<PageData> files = resourceService.findByRecordId(pageData);

		pd.put("FILE_URLS", StringUtil.getAppendStr(files, "URL"));
		pd.put("FILE_NAMES", StringUtil.getAppendStr(files, "NAME"));
		pd.put("FILE_IDS", StringUtil.getAppendStr(files, "RESOURCE_ID"));
		pd.put("files", files);
	}



	
}

