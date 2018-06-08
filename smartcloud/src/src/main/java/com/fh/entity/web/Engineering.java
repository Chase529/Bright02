package com.fh.entity.web;

import java.util.List;

import com.fh.util.PageData;

/** 
 * 说明：工程 实体类
 * 创建人：FH Q313596790
 * 创建时间：2017-12-13
 */
public class Engineering{ 
	
	private String ENGINEERING_ID;	//主键
	private String NAME;					//名称
	private String PARENT_ID;				//父类ID
	private int LEVEL;
	private double PLAN_NUMBER;
	private double ACTUAL_NUMBER;
	private double PLAN_OUTPUT;
	private double ACTUAL_OUTPUT;
	private String CREATE_TIME;	
	private String CREATE_USER_ID;	
	private String target;
	private Engineering engineering;
	private List<Engineering> subEngineering;
	private boolean hasEngineering = false;
	private String treeurl;
	private List<SubProject> subProject;
	private List<PageData> categories;
	public String getENGINEERING_ID() {
		return ENGINEERING_ID;
	}
	public void setENGINEERING_ID(String eNGINEERING_ID) {
		ENGINEERING_ID = eNGINEERING_ID;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}
	public int getLEVEL() {
		return LEVEL;
	}
	public void setLEVEL(int lEVEL) {
		LEVEL = lEVEL;
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
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Engineering getEngineering() {
		return engineering;
	}
	public void setEngineering(Engineering engineering) {
		this.engineering = engineering;
	}
	public List<Engineering> getSubEngineering() {
		return subEngineering;
	}
	public void setSubEngineering(List<Engineering> subEngineering) {
		this.subEngineering = subEngineering;
	}
	public boolean isHasEngineering() {
		return hasEngineering;
	}
	public void setHasEngineering(boolean hasEngineering) {
		this.hasEngineering = hasEngineering;
	}
	public String getTreeurl() {
		return treeurl;
	}
	public void setTreeurl(String treeurl) {
		this.treeurl = treeurl;
	}
	public List<SubProject> getSubProject() {
		return subProject;
	}
	public void setSubProject(List<SubProject> subProject) {
		this.subProject = subProject;
	}
	public List<PageData> getCategories() {
		return categories;
	}
	public void setCategories(List<PageData> categories) {
		this.categories = categories;
	}
	
}
