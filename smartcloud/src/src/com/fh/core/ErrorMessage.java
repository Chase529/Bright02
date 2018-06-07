package com.fh.core;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class ErrorMessage {
	private static Logger      log = LogManager.getLogger(ErrorMessage.class);
	
	//错误码
	public static final String PARAMS_ERROR = "params_error";
	
	public static final String PARAMS_ERROR_CODE = "0001";
	//APP不存在
	public static final String APP_NOT_EXIST = "app_not_exist";
	
	public static final String SYSTEM_ERROR = "system_error";
	
	public static final String SYSTEM_ERROR_CODE = "0002";
	
	public static final String USER_NOT_LOGIN = "user_not_login";
	
	public static final String USER_NOT_LOGIN_CODE = "0003";
	
	public static final String USERNAME_PWD_ERROR = "username_pwd_error";
	
	public static final String ACCOUNT_FREEZE = "account_freeze";
	
	public static final String MOBILE_ERROR = "mobile_error"; 
	
	public static final String EMAIL_ERROR = "email_error";
	
	public static final String PASSWORD_ERROR = "password_error";
	
	public static final String NAME_ERROR = "name_error";
	
	public static final String VERIFY_CODE_ERROR = "verify_code_error";
	
	public static final String REGISTER_FAIL = "register_fail";
	
	public static final String LOGOUT_FAIL = "logout_fail";
	
	public static final String MEMBER_NOT_EXIST = "member_not_exist";
	
	public static final String UPDATE_FAIL = "update_fail";
	
	public static final String SEND_SMS_ERROR = "send_sms_error";
	
	public static final String MOBILE_EXIST = "mobile_exist";
	
	public static final String EMAIL_EXIST = "email_exist";
	
	public static final String OLD_PWD_ERROR = "old_pwd_error";
	
	public static final String SAVE_ERROR = "save_error";
	
	public static final String UPLOAD_FAIL = "upload_fail";
	
	public static final String DEVICE_IS_BINDING = "device_is_binding";
	
	public static final String DEVICE_BIND_FAIL = "device_bind_fail";
	
	public static final String DEVICE_UNBIND_FAIL = "device_unbind_fail";
	
	public static final String DEVICE_NOT_EXIST = "device_not_exist";
	
	public static final String DEVICE_KEY_ERROR = "device_key_error";
	
	public static final String CHOOSE_ORDER_ERROR = "choose_order_error";
	
	public static final String PAY_FAIL = "pay_fail";
	
	public static final String APP_IS_OVERDUE = "app_is_overdue";
	
	public static final String ORDER_PAID = "order_paid";
	
	public static final String ORDER_NOT_EXIST = "order_not_exist";
	
	public static final String APP_IS_DOWN = "app_is_down";
	
	
	//错误信息
	public static final String PARAMS_ERROR_ZH_CN = "参数错误";
	
	public static final String PARAMS_ERROR_EN_US = "params error";
	
	public static final String SYSTEM_ERROR_ZH_CN = "服务异常，请联系管理员";
	
	public static final String SYSTEM_ERROR_EN_US = "system error, please contact administrator";
	
	public static final String USERNAME_PWD_ERROR_ZH_CN = "账号或密码错误";
	
	public static final String USERNAME_PWD_ERROR_EN_US = "username or password error";
	
	public static final String ACCOUNT_FREEZE_ZH_CN = "账号被冻结，请联系平台管理员";
	
	public static final String ACCOUNT_FREEZE_EN_US = "Account was freezed, please contact administrator";
	
	public static final String MOBILE_ERROR_ZH_CN = "手机号码格式错误";
	
	public static final String MOBILE_ERROR_EN_US = "mobile format error";
	
	public static final String EMAIL_ERROR_ZH_CN = "邮箱地址格式错误";
	
	public static final String EMAIL_ERROR_EN_US = "email format error";
	
	public static final String PASSWORD_ERROR_ZH_CN = "密码格式不符合规则";
	
	public static final String PASSWORD_ERROR_EN_US = "password format error";
	
	public static final String NAME_ERROR_ZH_CN = "昵称格式不符合规则";
	
	public static final String NAME_ERROR_EN_US = "nickname format error";
	
	public static final String VERIFY_CODE_ERROR_ZH_CN = "验证码错误";
	
	public static final String VERIFY_CODE_ERROR_EN_US = "verify code error";
	
	public static final String REGISTER_FAIL_ZH_CN = "注册失败,请稍后重试";
	
	public static final String REGISTER_FAIL_EN_US = "registe error, please try again later";
	
	public static final String LOGOUT_FAIL_ZH_CN = "退出登录失败，请稍后重试";
	
	public static final String LOGOUT_FAIL_EN_US = "logout fail, please try again later";
	
	public static final String MEMBER_NOT_EXIST_ZH_CN = "用户不存在";
	
	public static final String MEMBER_NOT_EXIST_EN_US = "member not exist";
	
	public static final String UPDATE_FAIL_ZH_CN = "更新失败";
	
	public static final String APP_NOT_EXIST_EN_US = "app not exist";
	public static final String APP_NOT_EXIST_ZH_CN = "APP不存在！";
	
	
	public static final String UPDATE_FAIL_EN_US = "update fail";
	
	public static final String SEND_SMS_ERROR_ZH_CN = "验证码发送失败，请稍后重试";
	
	public static final String SEND_SMS_ERROR_EN_US = "verify code send fail, please try again later";
	
	public static final String MOBILE_EXIST_ZH_CN = "手机号码已被注册";
	
	public static final String MOBILE_EXIST_EN_US = "mobile number be used";
	
	public static final String EMAIL_EXIST_ZH_CN = "邮箱已被注册";

	public static final String EMAIL_EXIST_EN_US = "email be used";
	
	public static final String USER_NOT_LOGIN_ZH_CN = "用户未登录";
	
	public static final String USER_NOT_LOGIN_EN_US = "user not login";
	
	public static final String OLD_PWD_ERROR_ZH_CN = "原密码不正确";
	
	public static final String OLD_PWD_ERROR_EN_US = "old password incorrect";
	
	public static final String SAVE_ERROR_ZH_CN = "保存失败";
	
	public static final String SAVE_ERROR_EN_US = "save error";
	
	public static final String UPLOAD_FAIL_ZH_CN = "上传失败";
	
	public static final String UPLOAD_FAIL_EN_US = "upload fail";
	
	public static final String DEVICE_IS_BINDING_ZH_CN = "设备已被绑定";
	
	public static final String DEVICE_IS_BINDING_EN_US = "device is binding";
	
	public static final String DEVICE_BIND_FAIL_ZH_CN = "设备绑定失败";
	
	public static final String DEVICE_BIND_FAIL_EN_US = "device bind fail";
	
	public static final String DEVICE_UNBIND_FAIL_ZH_CN = "设备解绑失败";
	
	public static final String DEVICE_UNBIND_FAIL_EN_US = "device unbind fail";
	
	public static final String DEVICE_NOT_EXIST_ZH_CN = "设备不存在";
	
	public static final String DEVICE_NOT_EXIST_EN_US = "device not exist";
	
	public static final String DEVICE_KEY_ERROR_ZH_CN = "设备激活码不正确";
	
	public static final String DEVICE_KEY_ERROR_EN_US = "device key error";
	
	public static final String CHOOSE_ORDER_ERROR_ZH_CN = "请选择要支付的订单";

	public static final String CHOOSE_ORDER_ERROR_EN_US = "choose order error";
	
	public static final String PAY_FAIL_ZH_CN = "pay fail";
	
	public static final String PAY_FAIL_EN_US = "支付失败";

	public static final String APP_IS_OVERDUE_EN_US = "app is overdue";
	public static final String APP_IS_OVERDUE_ZH_CN = "app过期了";
	
	public static final String ORDER_PAID_ZH_CN = "order paid";
	
	public static final String ORDER_PAID_EN_US = "订单已支付";
	
	public static final String ORDER_NOT_EXIST_EN_US = "order not exist";
	
	public static final String ORDER_NOT_EXIST_ZH_CN = "该订单不存在";

	public static final String APP_IS_DOWN_ZH_CN = "The APP be shelves or deleted";
	public static final String APP_IS_DOWN_EN_US = "APP已下架或删除";
	
	public static String get_msg(String name, HttpServletRequest req){
		
		String lang = RequestUtil.getLang(req);
		
		if(StringUtil.isEmpty(lang) || (!lang.equals("zh_cn") && !lang.equals("en_us"))) {
			lang = "zh_cn";
		}
		
		Field field;
		try {
			field = ErrorMessage.class.getDeclaredField((name + "_" + lang).toUpperCase());
			return field.get(new ErrorMessage()).toString();
		} catch (NoSuchFieldException e) {
			log.error(e.getMessage());
		} catch (SecurityException e) {
			log.error(e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
		} catch (IllegalAccessException e) {
			log.error(e.getMessage());
		}
		return SYSTEM_ERROR_EN_US;
	}
}
