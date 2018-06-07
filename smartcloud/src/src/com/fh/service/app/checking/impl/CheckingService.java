package com.fh.service.app.checking.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.fh.service.system.appuser.AppuserManager;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.app.checking.CheckingManager;

/** 
 * 说明： 用户考勤
 * 创建人：FH Q313596790
 * 创建时间：2017-11-29
 * @version
 */
@Service("checkingService")
public class CheckingService implements CheckingManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Resource(name="appuserService")
	private AppuserManager appuserService;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public Object save(PageData pd)throws Exception{
		return dao.save("CheckingMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public Object delete(PageData pd)throws Exception{
		return dao.delete("CheckingMapper.delete", pd);
	}
	
	/**条件删除
	 * @param pd
	 * @throws Exception
	 */
	public Object conditionDelete(PageData pd)throws Exception{
		return dao.delete("CheckingMapper.conditionDelete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public Object edit(PageData pd)throws Exception{
		return dao.update("CheckingMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CheckingMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CheckingMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CheckingMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CheckingMapper.deleteAll", ArrayDATA_IDS);
	}

	@Override
	/**
	 * @param pd
	 * @throws Exception 
	 */
	public PageData findByUser(PageData queryMap) throws Exception {
		
		return (PageData) dao.findForObject("CheckingMapper.findByUser", queryMap);
	}






}

