package com.ssopay.qywx.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * 结果数据
 * @author ssopay
 * @date 创建时间：Mar 13, 2014 11:14:54 AM
 */
public class ResultMap implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String parent;
	private String text;
	private String type;
	private Map<String, Boolean> state;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, Boolean> getState() {
		if (state == null || state.size() == 0) {
			state = new HashMap<>();
			state.put("selected", false);
		}
		return state;
	}
	public void setState(Map<String, Boolean> state) {
		this.state = state;
	}

}
