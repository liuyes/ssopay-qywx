package com.ssopay.qywx.rule.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ssopay.qywx.base.BaseDto;
import com.ssopay.qywx.base.Constants;
import com.ssopay.qywx.base.PageModel;
import com.ssopay.qywx.rule.bean.Rule;
import com.ssopay.qywx.rule.dao.RuleDao;
import com.ssopay.qywx.rule.service.RuleService;
import com.ssopay.qywx.util.PinYinUtils;

/**
 * <p>
 * 权限规则表 服务实现类
 * </p>
 *
 * @author ssopay
 * @since 2017-07-28
 */
@Service
public class RuleServiceImpl extends ServiceImpl<RuleDao, Rule> implements RuleService {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(RuleServiceImpl.class);
	
	@Autowired
	private RuleDao ruleDao;

	@Override
	public Long saveTx(Rule entity) {
		// 保存创建或更新时间
		if (entity != null && entity.getId() == null) {
			entity.setCreateDate(new Date().getTime());
		}
		if (entity != null && entity.getType() == 2) {
			entity.setPinyin(PinYinUtils.getFullSpell(entity.getName()));
			entity.setPy(PinYinUtils.getFirstSpell(entity.getName()));
		}
		entity.setUpdateDate(new Date().getTime());
		insertOrUpdate(entity);
		return entity.getId();
	}
	
	@Override
	public Rule findById(Long id) {
		return ruleDao.selectById(id);
	}
	
	@Override
	public PageModel list(BaseDto<Rule> dto) {
		/*
		 * {
            "id": "11181", 
            "type": "file", 
            "pid": "11180", 
            "name": "/admin/dashboard/index", 
            "title": " ├ 查看", 
            "icon": "fa fa-circle-o", 
            "condition": "", 
            "remark": "", 
            "ismenu": "0", 
            "createtime": "1497429920", 
            "updatetime": "1497429920", 
            "weigh": "136", 
            "status": "normal", 
            "spacer": " ├", 
            "haschild": 0
        }
		 */
		PageModel pm = new PageModel();
		List<Map<String, String>> rows = new ArrayList<>();
		List<Rule> mainRules = ruleDao.selectList(new EntityWrapper<Rule>().eq("type", 1));
		for (Rule mainRule : mainRules) {
			Map<String, String> mainMap = new HashMap<>();
			mainMap.put("id", mainRule.getId().toString());
			mainMap.put("type", mainRule.getType().toString());
			mainMap.put("pid", mainRule.getParentId() == null ? "0" : mainRule.getParentId().toString());
			mainMap.put("rule", mainRule.getRule());
			mainMap.put("name", mainRule.getName());
			mainMap.put("icon", mainRule.getIcon());
			mainMap.put("condition", mainRule.getCondition());
			mainMap.put("description", mainRule.getDescription());
			mainMap.put("disable", mainRule.getDisable().toString());
			List<Rule> subRules = ruleDao.selectList(new EntityWrapper<Rule>().eq("parent_id", mainRule.getId()));
			if (subRules.isEmpty()) {
				mainMap.put("haschild", "0");
			} else {
				mainMap.put("haschild", "1");
			}
			//加入结果集
			rows.add(mainMap);
			int i = 1;
			for (Rule subRule : subRules) {
				Map<String, String> subMap = new HashMap<>();
				subMap.put("id", subRule.getId().toString());
				subMap.put("type", subRule.getType().toString());
				subMap.put("pid", subRule.getParentId().toString());
				subMap.put("rule", subRule.getRule());
				subMap.put("name", (i == subRules.size() ? "&nbsp;&nbsp;└&nbsp;&nbsp;" : "&nbsp;&nbsp;├&nbsp;&nbsp;") + subRule.getName());
				subMap.put("icon", subRule.getIcon());
				subMap.put("condition", subRule.getCondition());
				subMap.put("description", subRule.getDescription());
				subMap.put("disable", subRule.getDisable().toString());
				List<Rule> threeRules = ruleDao.selectList(new EntityWrapper<Rule>().eq("parent_id", subRule.getId()));
				if (threeRules.isEmpty()) {
					subMap.put("haschild", "0");
				} else {
					subMap.put("haschild", "1");
				}
				//加入结果集
				rows.add(subMap);
				int j = 1;
				for (Rule rule : threeRules) {
					Map<String, String> map = new HashMap<>();
					map.put("id", rule.getId().toString());
					map.put("type", rule.getType().toString());
					map.put("pid", rule.getParentId().toString());
					map.put("rule", rule.getRule());
					map.put("name", (j == threeRules.size() ? "&nbsp;&nbsp;│&nbsp;&nbsp;└&nbsp;&nbsp;" : "&nbsp;&nbsp;│&nbsp;&nbsp;├&nbsp;&nbsp;") + rule.getName());
					map.put("icon", rule.getIcon());
					map.put("condition", rule.getCondition());
					map.put("description", rule.getDescription());
					map.put("disable", rule.getDisable().toString());
					map.put("haschild", "0");
					j++;
					//加入结果集
					rows.add(map);
				}
				i++;
			}
		}
		pm.setTotal(rows.size());
		pm.setRows(rows);
		return pm;
	}

	@Override
	public List<Rule> selectTree() {
		List<Rule> rules = new ArrayList<>();
		List<Rule> mainRules = ruleDao.selectList(new EntityWrapper<Rule>().eq("type", 1).eq("disable", Constants.DISABLE_NO));
		for (Rule mainRule : mainRules) {
			List<Rule> subRules = ruleDao.selectList(new EntityWrapper<Rule>().eq("parent_id", mainRule.getId()));
			//加入结果集
			rules.add(mainRule);
			int i = 1;
			for (Rule subRule : subRules) {
				subRule.setName((i == subRules.size() ? "&nbsp;&nbsp;└&nbsp;&nbsp;" : "&nbsp;&nbsp;├&nbsp;&nbsp;") + subRule.getName());
				//加入结果集
				rules.add(subRule);
				i++;
			}
		}
		return rules;
	}
	
}
