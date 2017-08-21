package com.ssopay.qywx.login.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssopay.qywx.base.Result;
import com.ssopay.qywx.jcaptcha.JCaptchaServiceSingleton;

/**
 * 
 * @ClassName: LoginController
 * @Description: TODO(负责打开登录页面(GET请求)和登录出错页面(POST请求)，真正登录的POST请求由Filter完成)
 * @author ssopay
 * @date 2017年7月6日 下午3:55:28
 *
 */
@Controller
public class LoginController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model) {
		//生成一个四位随机数给登录面使用，每次背景不同 1000-3999之间
		Random random = new Random();
		int x = random.nextInt(3999 - 1000 + 1) + 1000;
		model.addAttribute("random", x);
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public Result fail(String username, HttpServletRequest request) {
		Result result = new Result();
		Object obj = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String msg = "";
		if(obj != null){
			if("captcha.error".equals(obj))
				msg = "，验证码错误";
			else if("org.apache.shiro.authc.UnknownAccountException".equals(obj))
				msg = "，帐号或密码错误";
			else if("org.apache.shiro.authc.IncorrectCredentialsException".equals(obj))
				msg = "，帐号或密码错误";
			else if("org.apache.shiro.authc.AuthenticationException".equals(obj))
				msg = "，认证失败，请重试";
        }
		result.setMsg(username + "登录失败" + msg);
		return result;
	}
	

	@RequestMapping("code")
	public void code(HttpServletResponse response, HttpServletRequest request) throws Exception{
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
			String sid = request.getRequestedSessionId();
			BufferedImage challenge = JCaptchaServiceSingleton.getInstance().getImageChallengeForID(sid);
			ImageIO.write(challenge, "jpg", sos);
			sos.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if (sos != null)
				try {sos.close();} catch (IOException e) {e.printStackTrace();}
		}
	}

}
