package com.fh.entity.web;

import java.util.List;

public class MaterialCategory {
	private String CODE;	
	private String NAME;	
	private String NAME_EN;	
	private String REMARK;	
	private String CREATE_TIME;	
	private String CREATE_USER_ID;	
	private String PARENT_ID;	
	private Integer STATUS;
	private String MATERIAL_CATEGORY_ID;
	private String target;
	private MaterialCategory category;
	private List<MaterialCategory> subCategory;
	private boolean hasCategory = false;
	private String treeurl;
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getNAME_EN() {
		return NAME_EN;
	}
	public void setNAME_EN(String nAME_EN) {
		NAME_EN = nAME_EN;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
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
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}
	public String getMATERIAL_CATEGORY_ID() {
		return MATERIAL_CATEGORY_ID;
	}
	public void setMATERIAL_CATEGORY_ID(String mATERIAL_CATEGORY_ID) {
		MATERIAL_CATEGORY_ID = mATERIAL_CATEGORY_ID;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public MaterialCategory getCategory() {
		return category;
	}
	public void setCategory(MaterialCategory category) {
		this.category = category;
	}
	public List<MaterialCategory> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(List<MaterialCategory> subCategory) {
		this.subCategory = subCategory;
	}
	public boolean isHasCategory() {
		return hasCategory;
	}
	public void setHasCategory(boolean hasCategory) {
		this.hasCategory = hasCategory;
	}
	public String getTreeurl() {
		return treeurl;
	}
	public void setTreeurl(String treeurl) {
		this.treeurl = treeurl;
	}
	
}
