package com.ssopay.qywx.base;

import java.io.Serializable;
import java.util.List;
/**
 * 查询结果数据模型
 * @author ssopay
 * @date 创建时间：Mar 13, 2014 11:14:54 AM
 */
@SuppressWarnings("rawtypes")
public class PageModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 总记录数
	 */
	private Integer total;
	/**
	 * 数据
	 */
	private List rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}


}
