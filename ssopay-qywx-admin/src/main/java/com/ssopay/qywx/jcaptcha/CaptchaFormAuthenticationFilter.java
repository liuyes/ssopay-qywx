package com.ssopay.qywx.jcaptcha;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {
	//当验证码验证失败时不再走身份认证拦截器
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		if (request.getAttribute(getFailureKeyAttribute()) != null) {
			return true;
		}
		return super.onAccessDenied(request, response, mappedValue);
	}
	//登录成功时
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
            ServletRequest request, ServletResponse response) throws Exception {
		//登录成功跳转到successUrl设置的页面
		//http://blog.csdn.net/babys/article/details/51490801
		WebUtils.issueRedirect(request, response, getSuccessUrl());
		return false;
	}

}