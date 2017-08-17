package com.ssopay.qywx.index.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssopay.qywx.rule.bean.Rule;
import com.ssopay.qywx.user.bean.User;
import com.ssopay.qywx.user.service.UserService;

/**
 * 
 * @ClassName: UserController
 * @Description: TODO(用户相关操作)
 * @author ssopay
 * @date 2017年7月4日 下午4:02:58
 *
 */
@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
	public String index(Model model) {
		User user = userService.getCurrentUser();
		if (user != null) {
			List<Rule> myRules = userService.myRules(user.getId());
			model.addAttribute("myRules", myRules);
		}
		return "index";
	}
}
