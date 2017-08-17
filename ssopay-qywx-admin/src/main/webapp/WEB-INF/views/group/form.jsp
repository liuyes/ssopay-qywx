<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body class="inside-header inside-aside is-dialog">
	<div id="main" role="main">
		<div class="tab-content tab-addtabs">
			<div id="content">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="content">
							<form class="form-horizontal form-ajax" role="form" data-before-submit="refreshrules" method="post" action="${ctx}/group/save">
								<input name="id" type="hidden" value="${group.id}">
								<input name="rules" type="hidden" value="" />
								<div class="form-group">
									<label for="c-name" class="control-label col-xs-12 col-sm-2">名称:</label>
									<div class="col-xs-12 col-sm-8">
										<input id="c-name" class="form-control" name="name" value="${group.name}" data-rule="required">
									</div>
								</div>
								<div class="form-group">
							        <label class="control-label col-xs-12 col-sm-2">规则:</label>
							        <div class="col-xs-12 col-sm-8">
							            <span class="text-muted"><input type="checkbox" name="" id="checkall" /> <label for="checkall"><small>选中全部</small></label></span>
							            <span class="text-muted"><input type="checkbox" name="" id="expandall" /> <label for="expandall"><small>展开全部</small></label></span>
							
							            <div id="treeview"></div>
							        </div>
							    </div>
								<div class="form-group">
							        <label class="control-label col-xs-12 col-sm-2">状态:</label>
							        <div class="radio radio-inline">
							            <label for="c-enable">
							            	<input id="c-enable" name="disable" type="radio" value="1" <c:if test="${group.disable eq 1}">checked="checked"</c:if>> 有效　
							            </label>
							            <label for="c-disable">
							            	<input id="c-disable" name="disable" type="radio" value="-1" <c:if test="${group.disable eq -1}">checked="checked"</c:if>> 无效 
							            </label>
									</div>
							    </div>
								<div class="form-group layer-footer">
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
