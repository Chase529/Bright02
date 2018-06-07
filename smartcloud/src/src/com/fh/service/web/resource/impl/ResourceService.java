package com.fh.service.web.resource.impl;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.service.system.user.UserManager;
import com.fh.service.web.resource.ResourceManager;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.fh.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/** 
 * 说明： 保存图片管理
 * 创建人：FH Q313596790
 * 创建时间：2017-11-25
 * @version
 */
@Service("resourceService")
public class ResourceService implements ResourceManager {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Autowired
	protected UserManager userService ;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("ResourceMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ResourceMapper.delete", pd);
	}
	
	/**根据记录id删除
	 * @param pd
	 * @throws Exception
	 */
	public void deleteByRecordId(PageData pd)throws Exception{
		dao.delete("ResourceMapper.deleteByRecordId", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("ResourceMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ResourceMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ResourceMapper.listAll", pd);
	}
	
	/**通过recordId获取数据
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> findByRecordId(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ResourceMapper.findByRecordId", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ResourceMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ResourceMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/**
	 * 保存审批文件记录
	 * @throws Exception
	 */
	public void saveFiles(String raletionId,List<List<String>> list,String userId) throws Exception {
		//保存文件信息
		List<String> urls = list.get(1);
		List<String> fileNames = list.get(0);
		for(int i=0;i<urls.size();i++) {
			if(!urls.get(i).equals("")) {
				PageData pageData = new PageData();
				pageData.put("RESOURCE_ID", UuidUtil.get32UUID());
				pageData.put("STATUS", 0);
				pageData.put("CREATE_TIME", DateUtil.getTime());
				pageData.put("URL", urls.get(i));
				pageData.put("NAME", fileNames.get(i));
				pageData.put("RECORD_ID", raletionId);
				pageData.put("CREATE_USER_ID", userId);
				this.save(pageData);
			}
		}
	}
	
	/**
	 * 根据记录id删除附件
	 */
	public void deleteByRecordId(String approvalId) throws Exception {
		PageData pageData = new PageData();
		pageData.put("RECORD_ID", approvalId);
		this.deleteByRecordId(pageData);
	}

	@Override
	public List<PageData> getResourceByRecordId(String recordId) throws Exception {
		return (List<PageData>)dao.findForList("ResourceMapper.getResourceByRecordId", recordId);
	}
}

