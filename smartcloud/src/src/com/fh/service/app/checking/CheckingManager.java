package com.fh.service.app.checking;

import java.util.List;
import com.fh.entity.Page;
import com.fh.util.PageData;

/** 
 * 说明： 用户考勤接口
 * 创建人：FH Q313596790
 * 创建时间：2017-11-29
 * @version
 */
public interface CheckingManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public Object save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public Object delete(PageData pd)throws Exception;
	
	/**根据条件删除
	 * @param pd
	 * @throws Exception
	 */
	public Object conditionDelete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public Object edit(PageData pd)throws Exception;
	
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
	 * 根据用户与日期查询考勤记录 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByUser(PageData queryMap) throws Exception;


	
}

