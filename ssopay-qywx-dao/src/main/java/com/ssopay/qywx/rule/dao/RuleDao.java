package com.ssopay.qywx.rule.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ssopay.qywx.rule.bean.Rule;

/**
 * <p>
  * 权限规则表 Mapper 接口
 * </p>
 *
 * @author ssopay
 * @since 2017-07-28
 */
public interface RuleDao extends BaseMapper<Rule> {

	List<String> myRuleCodes(Long uid);
	List<Rule> mainRules(Long uid);
	List<Rule> subRules(@Param(value="pid")Long pid, @Param(value="uid")Long uid);
}