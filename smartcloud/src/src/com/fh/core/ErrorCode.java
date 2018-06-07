package com.fh.core;

public class ErrorCode {
	/*系统模块 00xx*/
	public static final String SUCCESS = "0000";//成功
	
	public static final String SYSTEM_ERROR = "0001";//系统错误
	
	public static final String PARAM_ERROR  = "0002";//参数错误
	
	public static final String SAVE_FAILED  = "0003";//保存失败
	
	public static final String EDIT_FAILED 	= "0004";//编辑失败
	
	public static final String DELETE_FAILED = "0005";//删除失败
	
	public static final String SMS_SEND_ERROR = "0006";//短信发送失败
	
	public static final String FILE_UPLOAD_ERROR = "0007";//文件上传失败
	
	/*用户模块 01xx*/
	public static final String USER_NAME_ERROR = "0101";//用户名错误
	
	public static final String USER_PWD_ERROR = "0102";//密码错误
	
	public static final String USER_NOT_EXIST = "0103";//用户不存在
	
	public static final String OLD_PWD_ERROR = "0104";//旧密码错误
	
	public static final String NEW_PWD_FORMAT_ERROR = "0105";//新密码格式不正确
	
	public static final String MOBILE_ERROR = "0106";//手机号码错误
	
	public static final String SMS_CODE_ERROR = "0107";//短信验证码不正确
	
	public static final String USER_NOT_LOGIN = "0108";//用户没登录
	
	public static final String USER_NO_GRANT = "0109";//用户不存在
	
	/*梁场模块03xx*/
	public static final String BEAMBOARD_CODE_ALREADY_EXIST = "0301";//梁板code已经存在
	
	public static final String BEAMBOARD_CODE_NOT_EXIST 	= "0302";//梁板code不存在
	
	public static final String BEAMBOARD_NOT_EXIST = "0303";//梁板不存在
	
	public static final String BEAMAREA_NOT_EXIST = "0304";//存梁区域不存在
	
	public static final String BEAMAREA_BEAMBOARD_IS_FULL = "0305";//存梁区域存梁已满
	
	public static final String BEAMBOARD_ALREADY_EXIST_IN_BEAM = "0306";//梁板已经在存梁区
	/*安全巡查模块04xx*/
	public static final String SECURITY_MEET_NOT_EXIST = "0401";//安全会议不存在
	
	/*通知04xx*/
	
	/*设备模块05xx*/
	public static final String DEVICE_ALREADY_DISABLED = "0501"; //设备已停用
	public static final String DEVICE_DOING_WARRANTY = "0502"; //设备正在保修
	public static final String DEVICE_ALREADY_FAILURE = "0503"; //设备已停用
	/*地磅数据06xx*/
	
	/*梁场日报07xx*/
	
	/*用户考勤08xx*/
	public static final String SIGN_IN_EXIST = "0801";//已签到
	
	public static final String SIGN_OUT_EXIST = "0802";//已签退
	
	
	/*施工质量模块09xx*/ 
	public static final String QUALITY_MEET_NOT_EXIST = "0901";//会议不存在
	
	/*物资模块10xx*/
	public static final String PICKING_GREATER_THAN_STORAGE = "1001";//领料数量不能大于库存数量
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
