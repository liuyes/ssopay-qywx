package com.ssopay.qywx.jcaptcha;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class CaptchaValidateFilter extends AccessControlFilter {
    private boolean jcaptchaEnabled = true;//是否开启验证码支持
    private String jcaptchaParam = "captcha";//前台提交的验证码参数名
    public void setJcaptchaEnabled(boolean jcaptchaEnabled) {
        this.jcaptchaEnabled = jcaptchaEnabled;
    }
    public void setJcaptchaParam(String jcaptchaParam) {
        this.jcaptchaParam = jcaptchaParam;
    }
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        // 设置验证码是否开启属性，页面可以根据该属性来决定是否显示验证码
        request.setAttribute("jcaptchaEbabled", jcaptchaEnabled);
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        // 判断验证码是否禁用 或不是表单提交（允许访问）
        if (jcaptchaEnabled == false || !"post".equalsIgnoreCase(httpServletRequest.getMethod())) {
            return true;
        }
        try {
        	// 此时是表单提交，验证验证码是否正确
	        return JCaptchaServiceSingleton.getInstance()
	        		.validateResponseForID(
	        				SecurityUtils.getSubject().getSession().getId().toString(), 
	        				httpServletRequest.getParameter(jcaptchaParam)
	        				);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 如果验证码失败了，存储失败key属性
        request.setAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, "captcha.error");
        return true;
    }
}
