package com.fh.util.consts;

/**
 * 审批常量类
 * @author wang_sheng
 */
public class ApprovalConsts {
	/**
	 * ---------审批类型及其子类型----------
	 */
	//审批类型
	public static final String APPROVAL_BIANMA	 = "033ws";
	
	public static final String REIMBURSEMENT_BIANMA = "033ws01";//报销
	public static final String BORROW_BIANMA = "033ws02";//借款
	public static final String PAY_BIANMA = "033ws03";//付款
	public static final String PAYMENT_BIANMA = "033ws04";//支款
	public static final String RECIVE_BIANMA = "033ws05";//招待费申请
	public static final String TRAVEL_BIANMA = "033ws06";//差旅费报销
	public static final String WAGES_BIANMA = "033ws07";//工资审批
	public static final String BUSINESS_TRIP_BIANMA = "033ws08";//出差申请
	public static final String IN_BIANMA = "033ws09";//人员进场
	public static final String OUT_BIANMA = "033ws10";//人员退场
	public static final String CONTRACT_BIANMA = "033ws11";//合同模块

	
	/**
	 * ------------审批单状态------------
	 */
	public static final int APPROVAL_WILL_APPLICANT = 0;//待申请
	public static final int APPROVAL_WILL_APPROVE = 1;//待审批
	public static final int APPROVAL_IN_APPROVING = 2;//审批中
	public static final int APPROVAL_HAVE_AGREE = 3;//审批通过
	public static final int APPROVAL_REJECT = 4;//驳回
	public static final int APPROVAL_DELETE = 5;//深处
	public static final int APPROVAL_REVOKE = 6;//驳回
	
	
	/**
	 * -------------人员类型-------------
	 */
	public static final int USER_TYPE_APPROVAL = 0;//审批人
	public static final int USER_TYPE_COPY = 1;//抄送人
	public static final int USER_TYPE_TOGETHER = 2;//同行人
	
	
	/**
	 * -------------审批人状态-------------
	 */
	public static final int USER_CANNTOT_APPROVE = 4;//还不能审批
	public static final int USER_WILL_APPROVE = 0;//待审批
	public static final int USER_AGREE = 1;//审批通过
	public static final int USER_REJECT = 2;//驳回
	
	//项目字典父编码
	public static final String MONITOR_AREA_CODE = "028ws";
	
	//部门编码
	public static final String DEPARTMENT_CODE = "002";
	//地区编码
	public static final String AREA_CODE = "003";
	
	
	/**
	 * --------------------费用编码--------------------
	 */
	public static final String RECIVE_FEE_CODE = "029ws03";//招待费，费用类型字典编号
	public static final String REIMBURSEMENT_FEE_CODE = "029ws01";//报销，费用编码
	public static final String BORROW_FEE_CODE = "029ws02";//借款,费用编码
	public static final String TRAVEL_FEE_CODE = "029ws04";//差旅费报销,费用编码
	
	public static final String SETTLEMENT_FEE_CODE = "029ws05";//结算单,费用编码
	
	//交通工具，父编码
	public static final String Vehicle = "030ws";
	
	
	//根据(审批单类型)查询(表名)
	public static String getTableNameByType(String type) throws Exception {
		switch (type) {
			case REIMBURSEMENT_BIANMA://报销
				return "TB_REIMBURSEMENT";
			case BORROW_BIANMA://借款
				return "TB_BORROW";
			case PAY_BIANMA://付款
				return "TB_PAY";
			case PAYMENT_BIANMA://支款
				return "TB_PAYMENT";
			case RECIVE_BIANMA://招待费申请
				return "TB_RECIVE";
			case TRAVEL_BIANMA://差旅费报销
				return "TB_TRAVEL";
			case WAGES_BIANMA://工资审批
				return "TB_WAGES";
			case BUSINESS_TRIP_BIANMA://出差申请
				return "TB_BUSINESS_TRIP";
			case IN_BIANMA://人员进场
				return "TB_IN_OUT";
			case OUT_BIANMA://人员退场
				return "TB_IN_OUT";
			case CONTRACT_BIANMA://合同模块
				return "TB_CONTRACT";
			default:
				throw new Exception("审批类型不存在");
		}
	}
	
	
	//根据(审批单类型)查询(表名)
	public static String getPrimaryNameByType(String type) throws Exception {
		switch (type) {
			case REIMBURSEMENT_BIANMA://报销
				return "REIMBURSEMENT_ID";
			case BORROW_BIANMA://借款
				return "BORROW_ID";
			case PAY_BIANMA://付款
				return "PAY_ID";
			case PAYMENT_BIANMA://支款
				return "PAYMENT_ID";
			case RECIVE_BIANMA://招待费申请
				return "RECIVE_ID";
			case TRAVEL_BIANMA://差旅费报销
				return "TRAVEL_ID";
			case WAGES_BIANMA://工资审批
				return "WAGES_ID";
			case BUSINESS_TRIP_BIANMA://出差申请
				return "BUSINESS_TRIP_ID";
			case IN_BIANMA://人员进场
				return "IN_OUT_ID";
			case OUT_BIANMA://人员退场
				return "IN_OUT_ID";
			case CONTRACT_BIANMA://合同模块
				return "CONTRACT_ID";
			default:
				throw new Exception("审批类型不存在");
		}
	}
}
