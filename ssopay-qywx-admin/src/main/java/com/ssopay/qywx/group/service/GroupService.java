package com.ssopay.qywx.group.service;

import com.baomidou.mybatisplus.service.IService;
import com.ssopay.qywx.base.BaseDto;
import com.ssopay.qywx.base.PageModel;
import com.ssopay.qywx.base.Result;
import com.ssopay.qywx.group.bean.Group;

/**
 * <p>
 * 用户群组表 服务类
 * </p>
 *
 * @author ssopay
 * @since 2017-07-20
 */
public interface GroupService extends IService<Group> {
	Group findById(Long id);
	Long saveTx(Group entity);
	PageModel list(BaseDto<Group> dto);
	Result ruleTree(Long groupId);
}
