package com.fh.service.system.appuser.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.service.system.appuser.AppuserManager;
import com.fh.util.PageData;

/**
 * 类名称：AppuserService
 * 
 * @author FH Q313596790 修改时间：2015年11月6日
 */
@Service("appuserService")
@SuppressWarnings("unchecked")
public class AppuserService implements AppuserManager { 

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/**
	 * 列出某角色下的所有会员
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllAppuserByRorlid(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppuserMapper.listAllAppuserByRorlid", pd);
	}

	/**
	 * 会员列表
	 * 
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPdPageUser(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AppuserMapper.userlistPage", page);
	}

	
	/**
	 * 根据部门查询全部会员，不分页
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> findAllUserByDepartment(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppuserMapper.findAllUserByDepartment", pd);
	}

	
	/**
	 * 通过用户名获取数据
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByUsername(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppuserMapper.findByUsername", pd);
	}

	/**
	 * 通过邮箱获取数据
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByEmail(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppuserMapper.findByEmail", pd);
	}

	/**
	 * 通过编号获取数据
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByNumber(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppuserMapper.findByNumber", pd);
	}

	/**
	 * 通过手机号获取数据
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByPhone(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppuserMapper.findByPhone", pd);
	}

	/**
	 * 保存用户
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void saveU(PageData pd) throws Exception {
		dao.save("AppuserMapper.saveU", pd);
	}

	/**
	 * 删除用户
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void deleteU(PageData pd) throws Exception {
		dao.delete("AppuserMapper.deleteU", pd);
	}

	/**
	 * 修改用户
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void editU(PageData pd) throws Exception {
		dao.update("AppuserMapper.editU", pd);
	}

	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppuserMapper.findById", pd);
	}
	/**通过id获取数据
	 */
	public PageData findById(String id)throws Exception{
		PageData pd = new PageData();
		pd.put("USER_ID",id);
		return this.findById(pd);
	}
	/**
	 * 全部会员
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllUser(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppuserMapper.listAllUser", pd);
	}

	/**
	 * 批量删除用户
	 * 
	 * @param USER_IDS
	 * @throws Exception
	 */
	public void deleteAllU(String[] USER_IDS) throws Exception {
		dao.delete("AppuserMapper.deleteAllU", USER_IDS);
	}

	/**
	 * 获取总数
	 */
	public PageData getAppUserCount(String value) throws Exception {
		return (PageData) dao.findForObject("AppuserMapper.getAppUserCount", value);
	}

	/**
	 * 上班会员
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getAttendanceNumber(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppuserMapper.getAttendanceNumber", pd);
	}

	/**
	 * 请假会员
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getLeaveNumber(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppuserMapper.getLeaveNumber", pd);
	}

	/**
	 * 获取部门人数
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getAppUserNumberByDeparment(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AppuserMapper.getAppUserNumberByDeparment", pd);
	}

	@Override
	public List<PageData> getAppUserByDept(String DepBianMa) throws Exception {
		return (List<PageData>) dao.findForList("AppuserMapper.getAppUserByDept", DepBianMa);
	}

	@Override
	public PageData getAvatarById(String user_id) throws Exception {
		return (PageData) dao.findForObject("AppuserMapper.findAvatarById", user_id);
	}

	/**
	 * 是否有权限修改预设
	 */
	@Override
	public PageData isHavePowerToEditPre(String uid) throws Exception {
		PageData pdData= new PageData();
		pdData.put("uid", uid);
		return (PageData) dao.findForObject("AppuserMapper.isHavePowerToEditPre", pdData);
	}
	
	/**
	 * 全部人员数量
	 */
	@Override
	public Object countAll() throws Exception {
		return this.getAppUserCount("").get("appUserCount");
	}
	

	@Override
	public PageData getUserInfoByCardId(String cardId) throws Exception {
		return (PageData) dao.findForObject("AppuserMapper.getUserInfoByCardId", cardId);
	}


	/**
	 * 部分分组人员数量
	 */
	@Override
	public List<PageData> countByDepartment() throws Exception {
		return (List<PageData>) dao.findForList("AppuserMapper.countByDepartment", null);
	}
}
