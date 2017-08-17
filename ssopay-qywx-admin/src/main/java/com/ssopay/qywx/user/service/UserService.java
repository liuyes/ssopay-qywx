package com.ssopay.qywx.user.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.ssopay.qywx.base.PageModel;
import com.ssopay.qywx.rule.bean.Rule;
import com.ssopay.qywx.user.bean.User;
import com.ssopay.qywx.user.dto.UserDto;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ssopay
 * @since 2017-07-20
 */
public interface UserService extends IService<User> {
	User getCurrentUser();
	User findByUsername(String username);
	Long saveTx(User entity);
	boolean deleteTx(Long id);
	PageModel list(UserDto dto);
	List<Long> groups(Long uid);
	List<String> myRuleCodes(Long uid);
	List<Rule> myRules(Long uid);
}
