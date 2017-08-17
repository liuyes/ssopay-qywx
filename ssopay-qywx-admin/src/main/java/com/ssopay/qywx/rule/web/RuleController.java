package com.ssopay.qywx.rule.web;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssopay.qywx.base.BaseDto;
import com.ssopay.qywx.base.Constants;
import com.ssopay.qywx.base.PageModel;
import com.ssopay.qywx.base.Result;
import com.ssopay.qywx.rule.bean.Rule;
import com.ssopay.qywx.rule.service.RuleService;

/**
 * <p>
 * 权限规则表 前端控制器
 * </p>
 *
 * @author ssopay
 * @since 2017-07-28
 */
@Controller
@RequestMapping("/rule")
public class RuleController {
	
	private Result result = new Result();
	
	@Autowired
	private RuleService ruleService;
	
	@RequiresPermissions("rule:view")
	@RequestMapping("")
	public String list(Model model) {
		String[] breadcrumb = {"系统管理", "规则管理"};
		model.addAttribute("breadcrumb", breadcrumb);
		return "rule/list";
	}
	
	@RequiresPermissions("rule:edit")
	@RequestMapping("json")
	@ResponseBody
	public PageModel json(BaseDto<Rule> dto) {
		return ruleService.list(dto);
	}
	
	@RequiresPermissions("rule:edit")
	@RequestMapping("add")
	public String addForm(Model model) {
		model.addAttribute("rules", ruleService.selectTree());
		model.addAttribute("rule", new Rule());
		return "rule/form";
	}
	
	@RequiresPermissions("rule:edit")
	@RequestMapping("edit/{id}")
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("rules", ruleService.selectTree());
		model.addAttribute("rule", ruleService.findById(id));
		return "rule/form";
	}
	
	@RequiresPermissions("rule:edit")
	@RequestMapping({"save","save/{id}"})
	@ResponseBody
	public Result save(@ModelAttribute("rule") Rule rule) {
		ruleService.saveTx(rule);
		//设置成功标识
		result.setCode(Constants.SUCCESS);
		return result;
	}

}
