package com.ssopay.qywx.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ssopay.qywx.base.Constants;
import com.ssopay.qywx.base.PageModel;
import com.ssopay.qywx.rule.bean.Rule;
import com.ssopay.qywx.rule.dao.RuleDao;
import com.ssopay.qywx.shiro.service.impl.ShiroDbRealmImpl.ShiroUser;
import com.ssopay.qywx.user.bean.User;
import com.ssopay.qywx.user.bean.UserGroup;
import com.ssopay.qywx.user.dao.UserDao;
import com.ssopay.qywx.user.dao.UserGroupDao;
import com.ssopay.qywx.user.dto.UserDto;
import com.ssopay.qywx.user.service.UserService;
import com.ssopay.qywx.util.Digests;
import com.ssopay.qywx.util.Encodes;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ssopay
 * @since 2017-07-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserGroupDao userGroupDao;
	@Autowired
	private RuleDao ruleDao;
	
	@Override
	public User getCurrentUser() {
		try {
			ShiroUser shiroUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
			if(shiroUser != null){
				return findByUsername(shiroUser.getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Long saveTx(User entity) {
		User oldUser = findByUsername(entity.getUsername());
		// 更新密码
		if (oldUser == null || StringUtils.isNotEmpty(entity.getPassword())) {
			entity = entryptPassword(entity);
		} else {
			entity.setPassword(oldUser.getPassword());
			entity.setSalt(oldUser.getSalt());
		}
		// 用户名换为大写
		entity.setUsername(entity.getUsername().toUpperCase());
		// 保存创建或更新时间
		if (entity.getId() == null) {
			entity.setCreateDate(new Date().getTime());
		} else {
			userGroupDao.delete(new EntityWrapper<UserGroup>().eq("user_id", entity.getId()));
		}
		entity.setUpdateDate(new Date().getTime());
		insertOrUpdate(entity);
		// 写入用户的群组
		for (String group : entity.getGroup()) {
			UserGroup userGroup = new UserGroup();
			userGroup.setUserId(entity.getId());
			userGroup.setGroupId(Long.valueOf(group));
			userGroupDao.insert(userGroup);
		}
		
		return entity.getId();
	}
	
	@Override
	public User findByUsername(String username) {
		List<User> users = userDao.selectList(new EntityWrapper<User>().eq("username", username));
		if(!users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private User entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(Constants.SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));
		byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, Constants.HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
		return user;
	}

	@Override
	public PageModel list(UserDto dto) {
		Wrapper<User> wrapper = dto.getWrapper();
		PageModel pm = new PageModel();
		pm.setTotal(userDao.selectCount(wrapper));
		pm.setRows(userDao.selectPage(dto.getPage(), wrapper));
		return pm;
	}

	@Override
	public boolean deleteTx(Long id) {
		return deleteById(id);
	}

	@Override
	public List<Long> groups(Long uid) {
		List<UserGroup> list = userGroupDao.selectList(new EntityWrapper<UserGroup>().eq("user_id", uid));
		List<Long> groups = new ArrayList<>();
		for (UserGroup userGroup : list) {
			groups.add(userGroup.getGroupId());
		}
		return groups;
	}

	@Override
	public List<String> myRuleCodes(Long id) {
		return ruleDao.myRuleCodes(id);
	}

	@Override
	public List<Rule> myRules(Long uid) {
		List<Rule> myRules = new ArrayList<>();
		List<Rule> mainRules = ruleDao.mainRules(uid);
		for (Rule mainRule : mainRules) {
			myRules.add(mainRule);
			List<Rule> subRules = ruleDao.subRules(mainRule.getId(), uid);
			for (Rule subRule : subRules) {
				myRules.add(subRule);
			}
		}
		return myRules;
	}
}
