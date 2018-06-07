package com.fh.service.web.resource;

import com.fh.entity.Page;
import com.fh.util.PageData;

import java.util.List;

/** 
 * 说明： 保存图片管理接口
 * 创建人：FH Q313596790
 * 创建时间：2017-11-25
 * @version
 */
public interface ResourceManager{

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
	
	/**根据记录id删除
	 * @param pd
	 * @throws Exception
	 */
	public void deleteByRecordId(PageData pd)throws Exception;
	
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
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过recordId获取数据
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> findByRecordId(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;

	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
	/**
	 * 保存审批文件记录
	 * @param pd
	 * @throws Exception
	 */
	void saveFiles(String raletionId, List<List<String>> list, String userId) throws Exception ;
	
	/**
	 * 根据记录id删除附件
	 */
	public void deleteByRecordId(String approvalId) throws Exception ;

	public List<PageData> getResourceByRecordId(String recordId) throws Exception;
}

