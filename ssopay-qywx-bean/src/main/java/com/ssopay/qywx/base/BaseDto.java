package com.ssopay.qywx.base;

import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @ClassName: BaseDto
 * @Description: TODO(基础的数据传输类)
 * @author ssopay
 * @param <T>
 * @date 2017年7月5日 上午10:13:06
 *
 */
@SuppressWarnings({"unused"})
public class BaseDto<T> {
	private Integer offset;//当前页
	private Integer limit;//每页记录条数
	private Integer current;
	private Page<T> page;//记录范围
	private String search;//查询条件
	private String sort;//排序字段
	private String order;//升序、降序
	private String filter;//过滤
	private String op;//操作
	private Wrapper<T> wrapper;//拼接的查询条件
	
	public Integer getOffset() {
		if (offset == null || offset < 0) {
			offset = 0;
		}
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		if (limit == null || limit <= 0) {
			limit = 20;
		}
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getCurrent() {
		return getOffset() / getLimit() + 1;
	}
	public Page<T> getPage() {
		return new Page<T>(getCurrent(), limit);
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	@SuppressWarnings("unchecked")
	public Wrapper<T> getWrapper() {
		ObjectMapper mapper = new ObjectMapper();
		wrapper = new EntityWrapper<T>();
		if (filter != null) {
			try {
				//filter:{"username":"admin","name":"采飞扬"}
				//op:{"username":"=","name":"LIKE {0}"}
				Map<String, String> filterMap = mapper.readValue(filter, Map.class);
				Map<String, String> opMap = mapper.readValue(op, Map.class);
				for (String key : opMap.keySet()) {
					if("LIKE {0}".equals(opMap.get(key))) {
						wrapper.like(key, filterMap.get(key));
					} else if("BETWEEN".equals(opMap.get(key))) {
						//日期的区间搜索，列名对应
						String column = key.replace("D", "_d");
						String[] values = filterMap.get(key).split(",");
						wrapper.between(column, values[0], values[1]);
					} else  {
						wrapper.where(key + opMap.get(key) + "{0}", filterMap.get(key));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(wrapper.getSqlSegment());
		return wrapper;
	}
}
