package com.ssopay.qywx.jcaptcha;

import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

public class JCaptchaServiceSingleton {

	private static ImageCaptchaService instance = null;

	private JCaptchaServiceSingleton() {
	}

	// 使用synchronized关键字解决线程不安全
	public synchronized static ImageCaptchaService getInstance() {
		if (instance == null) {
			instance = new DefaultManageableImageCaptchaService(
					new FastHashMapCaptchaStore(), new GMailEngine(), 180,
					100000, 75000);
		}
		return instance;
	}

}
