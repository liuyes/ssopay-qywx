package com.ssopay.qywx.shiro.service;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

public interface ShiroDbRealm {
	  AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException;

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	 AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals);

	/**
	 * 更新用户授权信息缓存.
	 */
	void clearCachedAuthorizationInfo(String principal);

	/**
	 * 清除所有用户授权信息缓存.
	 */
	void clearAllCachedAuthorizationInfo();
}
