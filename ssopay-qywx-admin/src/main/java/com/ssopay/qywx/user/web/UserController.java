package com.ssopay.qywx.user.web;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ssopay.qywx.base.Constants;
import com.ssopay.qywx.base.PageModel;
import com.ssopay.qywx.base.Result;
import com.ssopay.qywx.group.bean.Group;
import com.ssopay.qywx.group.service.GroupService;
import com.ssopay.qywx.user.bean.User;
import com.ssopay.qywx.user.dto.UserDto;
import com.ssopay.qywx.user.service.UserService;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ssopay
 * @since 2017-07-20
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private Result result = new Result();
	
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	
	@RequiresPermissions("user:view")
	@RequestMapping("")
	public String list(Model model) {
		String[] breadcrumb = {"系统管理", "用户管理"};
		model.addAttribute("breadcrumb", breadcrumb);
		return "user/list";
	}
	
	@RequiresPermissions("user:edit")
	@RequestMapping("json")
	@ResponseBody
	public PageModel json(UserDto dto) {
		return userService.list(dto);
	}
	
	@RequiresPermissions("user:edit")
	@RequestMapping("add")
	public String addForm(Model model) {
		model.addAttribute("groups", groupService.selectList(new EntityWrapper<Group>().eq("disable", Constants.DISABLE_NO)));
		model.addAttribute("user", new User());
		return "user/form";
	}
	
	@RequiresPermissions("user:edit")
	@RequestMapping("edit/{id}")
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("groups", groupService.selectList(new EntityWrapper<Group>().eq("disable", Constants.DISABLE_NO)));
		model.addAttribute("myGroups", userService.groups(id));
		model.addAttribute("user", userService.selectById(id));
		return "user/form";
	}
	
	@RequiresPermissions("user:edit")
	@RequestMapping({"save","save/{id}"})
	@ResponseBody
	public Result save(@ModelAttribute("user") User user) {
		userService.saveTx(user);
		//设置成功标识
		result.setCode(Constants.SUCCESS);
		return result;
	}
	
	@RequiresPermissions("user:edit")
	@RequestMapping("multi/{ids}")
	@ResponseBody
	public Result multi(@PathVariable("ids") String ids, Integer params) {
		String[] id = ids.split(",");
		if (id != null) {
			for (String userId : id) {
				User user = userService.selectById(Long.valueOf(userId));
				if (!"1".equals(userId) && user != null && params != null && 
						user.getDisable() != params && Math.abs(params) == 1) {
					user.setDisable(params);
					userService.saveTx(user);
				}
			}
			//设置成功标识
			result.setCode(Constants.SUCCESS);
		} else {
			result.setMsg("请至少选择一条记录");
		}
		return result;
	}
	
}
