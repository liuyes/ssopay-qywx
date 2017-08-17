package com.ssopay.qywx.rule.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.ssopay.qywx.base.BaseDto;
import com.ssopay.qywx.base.PageModel;
import com.ssopay.qywx.rule.bean.Rule;

/**
 * <p>
 * 权限规则表 服务类
 * </p>
 *
 * @author ssopay
 * @since 2017-07-28
 */
public interface RuleService extends IService<Rule> {
	Rule findById(Long id);
	Long saveTx(Rule entity);
	PageModel list(BaseDto<Rule> dto);
	List<Rule> selectTree();
}
