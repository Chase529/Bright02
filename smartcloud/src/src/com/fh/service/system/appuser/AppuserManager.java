package com.fh.service.system.appuser;

import java.util.List;

import com.fh.entity.Page;
import com.fh.util.PageData;


/** 会员接口类
 */
public interface AppuserManager { 
	
	/**列出某角色下的所有会员
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllAppuserByRorlid(PageData pd) throws Exception;
	
	/**会员列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPdPageUser(Page page)throws Exception;
	
	/**
	 * 根据部门查询全部会员，不分页
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> findAllUserByDepartment(PageData pd) throws Exception;
	
	/**通过用户名获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByUsername(PageData pd)throws Exception;
	
	/**通过邮箱获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByEmail(PageData pd)throws Exception;
	
	/**通过手机号获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByPhone(PageData pd)throws Exception;
	
	/**通过编号获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByNumber(PageData pd)throws Exception;
	
	/**保存用户
	 * @param pd
	 * @throws Exception
	 */
	public void saveU(PageData pd)throws Exception;
	
	/**删除用户
	 * @param pd
	 * @throws Exception
	 */
	public void deleteU(PageData pd)throws Exception;
	
	/**修改用户
	 * @param pd
	 * @throws Exception
	 */
	public void editU(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;

	/**通过id获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findById(String id)throws Exception;
	/**全部会员
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllUser(PageData pd)throws Exception;
	
	/**批量删除用户
	 * @param USER_IDS
	 * @throws Exception
	 */
	public void deleteAllU(String[] USER_IDS)throws Exception;
	
	/**获取总数
	 * @param pd
	 * @throws Exception
	 */
	public PageData getAppUserCount(String value)throws Exception;
	
	/**获取上班人数
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getAttendanceNumber(PageData pd)throws Exception;
	
	/**
	 * 获取请假人数
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getLeaveNumber(PageData pd)throws Exception;
	
	
	/** 获取部门人数
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getAppUserNumberByDeparment(PageData pd)throws Exception;
	
	/**
	 * 根据部门查询人员
	 * @param DepBianMa
	 * @return
	 */
	public List<PageData> getAppUserByDept(String DepBianMa)throws Exception;
	
	/**
	 * 根据id查询头像 
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	public PageData getAvatarById(String user_id)throws Exception;
	
	/**
	 * 是否有权限修改预设
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public PageData isHavePowerToEditPre(String uid) throws Exception;
	
	/**
	 * 全部人员数量
	 */
	public Object countAll() throws Exception ;
	
	/**
	 * 部分分组人员数量
	 */
	public List<PageData> countByDepartment() throws Exception;

	/**
	 * 根据考勤卡号id查询人员的信息
	 * @param cardId
	 * @return
	 * @throws Exception
	 */
	public PageData getUserInfoByCardId(String cardId) throws Exception;
}

