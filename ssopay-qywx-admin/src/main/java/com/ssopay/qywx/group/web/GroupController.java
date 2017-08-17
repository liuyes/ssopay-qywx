package com.ssopay.qywx.group.web;


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
import com.ssopay.qywx.group.bean.Group;
import com.ssopay.qywx.group.service.GroupService;

/**
 * <p>
 * 用户群组表 前端控制器
 * </p>
 *
 * @author ssopay
 * @since 2017-07-20
 */
@Controller
@RequestMapping("/group")
public class GroupController {
	
	private Result result = new Result();
	
	@Autowired
	private GroupService groupService;
	
	@RequiresPermissions("group:view")
	@RequestMapping("")
	public String list(Model model) {
		String[] breadcrumb = {"系统管理", "群组管理"};
		model.addAttribute("breadcrumb", breadcrumb);
		return "group/list";
	}
	
	@RequiresPermissions("group:edit")
	@RequestMapping("json")
	@ResponseBody
	public PageModel json(BaseDto<Group> dto) {
		return groupService.list(dto);
	}
	
	@RequiresPermissions("group:edit")
	@RequestMapping("add")
	public String addForm(Model model) {
		model.addAttribute("group", new Group());
		return "group/form";
	}
	
	@RequiresPermissions("group:edit")
	@RequestMapping("edit/{id}")
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("group", groupService.findById(id));
		return "group/form";
	}
	
	@RequiresPermissions("group:edit")
	@RequestMapping({"save","save/{id}"})
	@ResponseBody
	public Result save(@ModelAttribute("group") Group group) {
		groupService.saveTx(group);
		//设置成功标识
		result.setCode(Constants.SUCCESS);
		return result;
	}
	
	
	@RequiresPermissions("group:edit")
	@RequestMapping("ruleTree")
	@ResponseBody
	public Result ruleTree(Long groupId) {
		return groupService.ruleTree(groupId);
	}

}
