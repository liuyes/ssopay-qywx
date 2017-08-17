package com.ssopay.qywx.rule.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 权限规则表
 * </p>
 *
 * @author ssopay
 * @since 2017-07-28
 */
@TableName("t_rule")
public class Rule extends Model<Rule> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private String name;
    /**
     * 规则，可以是网址或权限码
     */
	private String rule;
    /**
     * 类型 1主模块 2子功能 3子权限
     */
	private Integer type;
    /**
     * 图标
     */
	private String icon;
    /**
     * 规则条件
     */
	private String condition;
    /**
     * 规则说明
     */
	private String description;
	/**
     * 拼音
     */
	private String pinyin;
	/**
     * 拼音首字母
     */
	private String py;
	private Long createDate;
	private Long updateDate;
	private Integer disable;
	private Long creatorId;
	private Long parentId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getPy() {
		return py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	
	public Long getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getDisable() {
		return disable;
	}

	public void setDisable(Integer disable) {
		this.disable = disable;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Rule{" +
			"id=" + id +
			", name=" + name +
			", rule=" + rule +
			", type=" + type +
			", icon=" + icon +
			", condition=" + condition +
			", description=" + description +
			", pinyin=" + pinyin +
			", py=" + py +
			", createDate=" + createDate +
			", updateDate=" + updateDate +
			", disable=" + disable +
			", creatorId=" + creatorId +
			", parentId=" + parentId +
			"}";
	}
}
