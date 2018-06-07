package com.fh.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *                       
 * @Filename: HttpJsonResult.java
 * @Version: 1.0
 * @Author: 
 * @Email: 
 *
 */
public class HttpJsonResult<T> implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8637111820477625638L;

    public HttpJsonResult() {

    }

    public HttpJsonResult(T rows) {
        this.rows = rows;
    }

    public HttpJsonResult(String errorMessage) {
        this.success = false;
        this.message = errorMessage;
    }

    private Boolean success = true;

    public Boolean getSuccess() {
        return this.success;
    }

    public Boolean setSuccess(Boolean result){
    	this.success=result;
    	return success;
    }
    private T rows;

    public T getRows() {
        return rows == null ? (T) new ArrayList() : rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.success = false;
        this.message = message;
    }

    private Integer total = 0;

    public void setTotal(Integer count) {
        this.total = count;
    }

    public Integer getTotal() {
        return this.total;
    }

    private String backUrl;

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    private T footer;

    public T getFooter() {
        return footer == null ? (T) new ArrayList() : footer;
    }

    public void setFooter(T footer) {
        this.footer = footer;
    }
    
    private String code = "0000";//0000,请求正常

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 设置成功数据
	 * @param data
	 * @return 
	 */
	public HttpJsonResult<T> setSuccessResult(T data){
		this.setMessage("操作成功。");
		if(data!=null && data instanceof List) {
			this.setTotal(((List<?>) data).size());
		}
		this.setSuccess(true);
		this.setData(data);
		this.setCode("0000");
		return this;
	}
    
	/**
	 * 设置失败结果
	 * @param data
	 * @return 
	 */
	public HttpJsonResult<T> setFailResult(String msg){
		this.setSuccess(false);
		this.setMessage(msg);
		this.setCode("0001");
		return this;
	}
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
