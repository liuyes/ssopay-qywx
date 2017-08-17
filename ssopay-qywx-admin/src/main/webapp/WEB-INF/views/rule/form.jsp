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
							<form class="form-horizontal form-ajax" role="form" data-toggle="validator" method="post" action="${ctx}/rule/save">
								<input name="id" type="hidden" value="${rule.id}">
								<div class="form-group">
							        <label for="role_id" class="control-label col-xs-12 col-sm-2">父级:</label>
									<div class="col-xs-12 col-sm-8">
										<select class="form-control selectpicker" name="parentId">
											<option value="">请选择…</option>
											<c:forEach items="${rules}" var="item">
												<option value="${item.id}" <c:if test="${item.id eq rule.parentId}">selected="selected"</c:if>>${item.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="c-name" class="control-label col-xs-12 col-sm-2">名称:</label>
									<div class="col-xs-12 col-sm-8">
										<input id="c-name" class="form-control" name="name" value="${rule.name}" data-rule="required" />
									</div>
								</div>
								<div class="form-group">
									<label for="c-rule" class="control-label col-xs-12 col-sm-2">规则:</label>
									<div class="col-xs-12 col-sm-8">
										<input id="c-rule" class="form-control" name="rule" value="${rule.rule}" data-rule="required" />
									</div>
								</div>
								<div class="form-group">
							        <label for="c-icon" class="control-label col-xs-12 col-sm-2">图标:</label>
							        <div class="col-xs-12 col-sm-8">
							            <div class="input-group input-groupp-md">
							                <input type="text" class="form-control" id="c-icon" name="icon" value="${rule.icon}" data-rule="required" />
							                <a href="javascript:;" class="btn-search-icon input-group-addon">搜索图标</a>
							            </div>
							        </div>
							    </div>
								<div class="form-group">
							        <label class="control-label col-xs-12 col-sm-2">类型:</label>
							        <div class="radio radio-inline">
							            <label for="c-main">
							            	<input id="c-main" name="type" type="radio" value="1" <c:if test="${rule.type eq 1}">checked="checked"</c:if>> 主模块
							            </label>
							            <label for="c-sub">
							            	<input id="c-sub" name="type" type="radio" value="2" <c:if test="${rule.type eq 2}">checked="checked"</c:if>> 子功能
							            </label>
							            <label for="c-prem">
							            	<input id="c-prem" name="type" type="radio" value="3" <c:if test="${rule.type eq 3}">checked="checked"</c:if>> 子权限
							            </label>
									</div>
							    </div>
							    <div class="form-group">
							        <label for="c-condition" class="control-label col-xs-12 col-sm-2">规则条件:</label>
							        <div class="col-xs-12 col-sm-8">
							            <textarea id="c-condition" class="form-control" name="condition">${rule.condition}</textarea>
							        </div>
							    </div>
							    <div class="form-group">
							        <label for="c-description" class="control-label col-xs-12 col-sm-2">说明:</label>
							        <div class="col-xs-12 col-sm-8">
							            <textarea id="c-description" class="form-control" name="description">${rule.description}</textarea>
							        </div>
							    </div>
								<div class="form-group">
							        <label class="control-label col-xs-12 col-sm-2">状态:</label>
							        <div class="radio radio-inline">
							            <label for="c-enable">
							            	<input id="c-enable" name="disable" type="radio" value="1" <c:if test="${rule.disable eq 1}">checked="checked"</c:if>> 有效　
							            </label>
							            <label for="c-disable">
							            	<input id="c-disable" name="disable" type="radio" value="-1" <c:if test="${rule.disable eq -1}">checked="checked"</c:if>> 无效 
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
<style>
    #chooseicon {
        margin:10px;
    }
    #chooseicon ul {
        margin:5px 0 0 0;
    }
    #chooseicon ul li{
        width:30px;height:30px;
        line-height:30px;
        border:1px solid #ddd;
        padding:1px;
        margin:1px;
        text-align: center;
    }
    #chooseicon ul li:hover{
        border:1px solid #2c3e50;
        cursor:pointer;
    }
</style>
<script id="chooseicontpl" type="text/html">
    <div id="chooseicon">
        <div>
            <form onsubmit="return false;">
                <div class="input-group input-groupp-md">
                    <div class="input-group-addon">搜索图标</div>
                    <input class="js-icon-search form-control" type="text" placeholder="">
                </div>
            </form>
        </div>
        <div>
            <ul class="list-inline">
                <\% for(var i=0; i<iconlist.length; i++){ \%>
                    <li data-font="<\%=iconlist[i]\%>" title="<\%=iconlist[i]\%>">
                    <i class="fa fa-<\%=iconlist[i]\%>"></i>
                </li>
                <\% } \%>
            </ul>
        </div>

    </div>
</script>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
