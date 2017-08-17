package com.ssopay.qywx.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 结果数据模型
 * @author ssopay
 * @date 创建时间：Mar 13, 2014 11:14:54 AM
 */
public class Result implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer code = -1;
	private String msg;
	private String url;
	private Long time = new Date().getTime();
	private List<ResultMap> data;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public List<ResultMap> getData() {
		return data;
	}
	public void setData(List<ResultMap> data) {
		this.data = data;
	}

}
