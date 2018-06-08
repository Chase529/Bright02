package com.fh.entity.web;

public class SubProject {
	private String TYPE;
	private String STAKE;
	private String NUMBER;
	private String NAME;	
	private String CODE;	
	private String REMARK;	
	private String ENGINEERING_ID;	
	private String CREATE_TIME;	
	private String CREATE_USER_ID;
	private double PLAN_NUMBER;
	private double ACTUAL_NUMBER;	
	private double PLAN_OUTPUT;
	private double ACTUAL_OUTPUT;	
	private String SUB_PROJECT_ID;
	private String PLAN_MONTH;
	private Integer TEMPLATE_TYPE;
	private Integer STATUS;
	private double DIFFERENCE;//计划值和实际值得差额
	public String getTYPE() {
		return TYPE;
	}
	public String getSTAKE() {
		return STAKE;
	}
	public String getNUMBER() {
		return NUMBER;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public void setSTAKE(String sTAKE) {
		STAKE = sTAKE;
	}
	public void setNUMBER(String nUMBER) {
		NUMBER = nUMBER;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getENGINEERING_ID() {
		return ENGINEERING_ID;
	}
	public void setENGINEERING_ID(String eNGINEERING_ID) {
		ENGINEERING_ID = eNGINEERING_ID;
	}
	public String getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	public String getCREATE_USER_ID() {
		return CREATE_USER_ID;
	}
	public void setCREATE_USER_ID(String cREATE_USER_ID) {
		CREATE_USER_ID = cREATE_USER_ID;
	}
	
	public double getPLAN_NUMBER() {
		return PLAN_NUMBER;
	}
	public void setPLAN_NUMBER(double pLAN_NUMBER) {
		PLAN_NUMBER = pLAN_NUMBER;
	}
	public double getACTUAL_NUMBER() {
		return ACTUAL_NUMBER;
	}
	public void setACTUAL_NUMBER(double aCTUAL_NUMBER) {
		ACTUAL_NUMBER = aCTUAL_NUMBER;
	}
	public double getPLAN_OUTPUT() {
		return PLAN_OUTPUT;
	}
	public void setPLAN_OUTPUT(double pLAN_OUTPUT) {
		PLAN_OUTPUT = pLAN_OUTPUT;
	}
	public double getACTUAL_OUTPUT() {
		return ACTUAL_OUTPUT;
	}
	public void setACTUAL_OUTPUT(double aCTUAL_OUTPUT) {
		ACTUAL_OUTPUT = aCTUAL_OUTPUT;
	}
	public String getSUB_PROJECT_ID() {
		return SUB_PROJECT_ID;
	}
	public void setSUB_PROJECT_ID(String sUB_PROJECT_ID) {
		SUB_PROJECT_ID = sUB_PROJECT_ID;
	}
	public String getPLAN_MONTH() {
		return PLAN_MONTH;
	}
	public void setPLAN_MONTH(String pLAN_MONTH) {
		PLAN_MONTH = pLAN_MONTH;
	}
	public Integer getTEMPLATE_TYPE() {
		return TEMPLATE_TYPE;
	}
	public void setTEMPLATE_TYPE(Integer tEMPLATE_TYPE) {
		TEMPLATE_TYPE = tEMPLATE_TYPE;
	}
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}
	public double getDIFFERENCE() {
		return DIFFERENCE;
	}
	public void setDIFFERENCE(double dIFFERENCE) {
		DIFFERENCE = dIFFERENCE;
	}
	
	
}
