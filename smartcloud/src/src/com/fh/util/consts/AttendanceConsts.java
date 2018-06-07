package com.fh.util.consts;

public class AttendanceConsts {

	/**
	 * 验证票据
	 */
	public static final String CODE = "shfawo_user_attendance";
	
	public static final String ON_DUTY_NORMAL = "0";//正常上班
	public static final String OFF_DUTY_NORMAL = "1";//正常下班
	public static final String ON_DUTY_LATER = "2";//迟到
	public static final String OFF_DUTY_EARLY = "3";//早退
	public static final String ON_DUTY_AWAY = "4";//旷工
	public static final String ON_DUTY_LOSE = "5";//上班缺卡
	public static final String OFF_DUTY_LOSE = "6";//下班缺卡
	public static final String ON_DUTY_INVALID = "7";//上班重复打卡
	public static final String OFF_DUTY_INVALID = "8";//下班重复打卡
	
	/**
	 * 上班打卡时间(范围)
	 */
	public static final String[] onDutyBtw = new String[]{"00:00:00","11:59:59"};
	/**
	 * 上班时间(迟到时间)
	 */
	public static final String onDutyNormal = "09:00:01";
	
	/**
	 * 下班打卡时间(范围)
	 */
	public static final String[] offDutyBtw = new String[]{"12:00:00","23:59:59"};
	/**
	 * 下班时间(早退时间)
	 */
	public static final String offDutyNormal = "17:59:59";
	
	
}
