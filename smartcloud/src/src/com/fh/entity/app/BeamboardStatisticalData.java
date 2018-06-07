package com.fh.entity.app;

public class BeamboardStatisticalData {
	private String date; 
	private String typeCode;
	private String typeName;
	private Integer planNumber;
	private Integer actualNumber;
	private Integer notCompletedNumber;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getPlanNumber() {
		return planNumber;
	}
	public void setPlanNumber(Integer planNumber) {
		this.planNumber = planNumber;
	}
	public Integer getActualNumber() {
		return actualNumber;
	}
	public void setActualNumber(Integer actualNumber) {
		this.actualNumber = actualNumber;
	}
	public Integer getNotCompletedNumber() {
		return notCompletedNumber;
	}
	public void setNotCompletedNumber(Integer notCompletedNumber) {
		this.notCompletedNumber = notCompletedNumber;
	}
	
}
