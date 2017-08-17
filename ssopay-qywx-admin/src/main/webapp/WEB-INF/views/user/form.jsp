<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body class="inside-header inside-aside is-dialog">
	<div id="main" role="main">
		<div class="tab-content tab-addtabs">
			<div id="content">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="content">
							<form class="form-horizontal form-ajax" role="form" data-toggle="validator" method="post" action="${ctx}/user/save">
								<input name="id" type="hidden" value="${user.id}">
								<div class="form-group">
							        <label for="c-group" class="control-label col-xs-12 col-sm-2">群组:</label>
							        <div class="col-xs-12 col-sm-8">
										<select class="form-control selectpicker" name="group" multiple="" data-rule="required">
											<option value="">请选择…</option>
											<c:forEach items="${groups}" var="item">
												<option value="${item.id}" <c:if test="${fn:contains(myGroups, item.id)}">selected="selected"</c:if>>${item.name}</option>
											</c:forEach>
										</select>
									</div>
							    </div>
								<div class="form-group">
									<label for="c-username" class="control-label col-xs-12 col-sm-2">用户名:</label>
									<div class="col-xs-12 col-sm-8">
										<input id="c-username" class="form-control" name="username" value="${user.username}" data-rule="required;username">
									</div>
								</div>
								<div class="form-group">
									<label for="c-name" class="control-label col-xs-12 col-sm-2">姓名:</label>
									<div class="col-xs-12 col-sm-8">
										<input id="c-name" class="form-control" name="name" value="${user.name}" data-rule="required">
									</div>
								</div>
								<div class="form-group">
									<label for="c-passwordl" class="control-label col-xs-12 col-sm-2">密码:</label>
									<div class="col-xs-12 col-sm-8">
										<input id="c-password" class="form-control" name="password" type="password" <c:if test="${not empty user.id}">placeholder="不修改密码请留空"</c:if> data-rule="password">
									</div>
								</div>
								<div class="form-group">
									<label for="c-email" class="control-label col-xs-12 col-sm-2">邮箱:</label>
									<div class="col-xs-12 col-sm-8">
										<input id="c-email" class="form-control" name="email" value="${user.email}" data-rule="required;email">
									</div>
								</div>
								<div class="form-group">
									<label for="c-mobile" class="control-label col-xs-12 col-sm-2">电话:</label>
									<div class="col-xs-12 col-sm-8">
										<input id="c-mobile" class="form-control" name="mobile" value="${user.mobile}" data-rule="required;mobile">
									</div>
								</div>
							    <div class="form-group">
							        <label for="c-image" class="control-label col-xs-12 col-sm-2">头像:</label>
							        <div class="col-xs-12 col-sm-8">
							            <div class="form-inline">
							                <input id="c-image" class="form-control" size="50" name="image" type="text" value="${user.image}">
							                <span><button type="button" id="plupload-image" class="btn btn-danger plupload" data-multiple="false" data-input-id="c-image" data-preview-id="p-image"><i class="fa fa-upload"></i> 上传</button></span>
							                <span><button type="button" id="fachoose-image" class="btn btn-primary fachoose" data-multiple="false" data-input-id="c-image"><i class="fa fa-list-ul"></i> 选择</button></span>
							                <ul class="row list-inline plupload-preview" id="p-image"></ul>
							            </div>
							        </div>
							    </div>
							    <div class="form-group">
							        <label for="c-description" class="control-label col-xs-12 col-sm-2">说明:</label>
							        <div class="col-xs-12 col-sm-8">
							            <textarea id="c-description" class="form-control" name="description">${user.description}</textarea>
							        </div>
							    </div>
								<div class="form-group">
							        <label class="control-label col-xs-12 col-sm-2">状态:</label>
							        <div class="radio radio-inline">
							            <label for="c-enable">
							            	<input id="c-enable" name="disable" type="radio" value="1" <c:if test="${user.disable eq 1}">checked="checked"</c:if>> 有效
							            </label>
							            <label for="c-disable">
							            	<input id="c-disable" name="disable" type="radio" value="-1" <c:if test="${user.disable eq -1}">checked="checked"</c:if>> 无效
							            </label>
									</div>
							    </div>
								<div class="form-group layer-footer pull-right">
									<label class="control-label col-xs-12 col-sm-2"></label>
									<div class="col-xs-12 col-sm-8">
										<button type="submit" class="btn btn-success btn-embossed disabled">确定</button>
										<button type="reset" class="btn btn-default btn-embossed">重置</button>
									</div>
								</div>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
