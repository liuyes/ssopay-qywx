package com.ssopay.qywx.group.service.impl;

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
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ssopay.qywx.base.BaseDto;
import com.ssopay.qywx.base.Constants;
import com.ssopay.qywx.base.PageModel;
import com.ssopay.qywx.base.Result;
import com.ssopay.qywx.base.ResultMap;
import com.ssopay.qywx.group.bean.Group;
import com.ssopay.qywx.group.bean.GroupRule;
import com.ssopay.qywx.group.dao.GroupDao;
import com.ssopay.qywx.group.dao.GroupRuleDao;
import com.ssopay.qywx.group.service.GroupService;
import com.ssopay.qywx.rule.bean.Rule;
import com.ssopay.qywx.rule.dao.RuleDao;

/**
 * <p>
 * 用户群组表 服务实现类
 * </p>
 *
 * @author ssopay
 * @since 2017-07-20
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupDao, Group> implements GroupService {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);
	
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private RuleDao ruleDao;
	@Autowired
	private GroupRuleDao groupRuleDao;

	@Override
	public Long saveTx(Group entity) {
		// 保存创建或更新时间
		if (entity != null && entity.getId() == null) {
			entity.setCreateDate(new Date().getTime());
		} else {
			groupRuleDao.delete(new EntityWrapper<GroupRule>().eq("group_id", entity.getId()));
		}
		// 写入群组的规则
		for (String rule : entity.getRules()) {
			GroupRule groupRule = new GroupRule();
			groupRule.setGroupId(entity.getId());
			groupRule.setRuleId(Long.valueOf(rule));
			groupRuleDao.insert(groupRule);
		}
		entity.setUpdateDate(new Date().getTime());
		insertOrUpdate(entity);
		return entity.getId();
	}
	
	@Override
	public Group findById(Long id) {
		return groupDao.selectById(id);
	}
	
	@Override
	public PageModel list(BaseDto<Group> dto) {
		Wrapper<Group> wrapper = dto.getWrapper();
		PageModel pm = new PageModel();
		pm.setTotal(groupDao.selectCount(wrapper));
		pm.setRows(groupDao.selectPage(dto.getPage(), wrapper));
		return pm;
	}
	
	@Override
	public Result ruleTree(Long groupId) {
		Result result = new Result();
		List<ResultMap> data = new ArrayList<>();
		List<Rule> rules = ruleDao.selectList(new EntityWrapper<Rule>().eq("disable", 1));
		List<Long> myRules = new ArrayList<>();
		if (groupId != null) {
			// 写入当前群组已分配的规则
			List<GroupRule> groupRules = groupRuleDao.selectList(new EntityWrapper<GroupRule>().eq("group_id", groupId));
			for (GroupRule groupRule : groupRules) {
				myRules.add(groupRule.getRuleId());
			}
		}
		for (Rule rule : rules) {
			ResultMap resultMap = new ResultMap();
			Map<String, Boolean> state = new HashMap<>();
			// 已分配的规则标记为true，只需要要标记子权限即可 type: 3
			if (rule.getType() == 3 && myRules.contains(rule.getId())) {
				state.put("selected", true);
			}
			resultMap.setId(rule.getId().toString());
			resultMap.setParent(rule.getParentId() == null ? "#" : rule.getParentId().toString());
			resultMap.setType("menu");
			resultMap.setText(rule.getName());
			resultMap.setState(state);
			data.add(resultMap);
		}
		result.setData(data);
		// 成功标识
		result.setCode(Constants.SUCCESS);
		return result;
	}
	
}
