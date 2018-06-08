package com.fh.entity.web;

import java.util.List;

public class SubmitListRequest {
	private String uid;
	private List<SubProjectDetail> listArray;
	public String getUid() {
		return uid;
	}
	public List<SubProjectDetail> getListArray() {
		return listArray;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setListArray(List<SubProjectDetail> listArray) {
		this.listArray = listArray;
	}
	
}
