package com.ssopay.qywx.upload.web;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssopay.qywx.base.Result;

/**
 * 
 * @ClassName: UploadController
 * @Description: TODO(文件上传类)
 * @author ssopay
 * @date 2017年8月7日 下午3:08:54
 *
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

	@RequiresUser
	@RequestMapping("")
	@ResponseBody
	public Result upload() {
		Result result = new Result();
		result.setCode(200);
		result.setMsg("文件上传成功");
		result.setUrl("/uploads/20170807/e7a0f6e961324b2282dd4c3e806841b7.jpg");
		return result;
	}

}
