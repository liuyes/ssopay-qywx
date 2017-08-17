<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body class="inside-header inside-aside ">
	<div id="main" role="main">
		<div class="tab-content tab-addtabs">
			<div id="content">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<!-- RIBBON -->
						<div id="ribbon">
							<ol class="breadcrumb pull-left">
								<li><a href="${ctx}/dashboard.jsp" class="addtabsit"><i class="fa fa-dashboard"></i> 控制台</a></li>
							</ol>
							<ol class="breadcrumb pull-right">
								<c:forEach items="${breadcrumb}" var="item">
									<li><a href="javascript:;" data-url="#">${item}</a></li>
								</c:forEach>
							</ol>
						</div>
						<!-- END RIBBON -->
						<div class="content">
							<div class="panel panel-default panel-intro">
								<div class="panel-heading">
									<div class="panel-lead">
										<em>规则管理</em>规则通常对应一个控制器的方法,同时左侧的菜单栏数据也从规则中体现,通常建议通过控制台进行生成规则节点
									</div>
								</div>
								<div class="panel-body">
									<div id="myTabContent" class="tab-content">
										<div class="tab-pane fade active in" id="one">
											<div class="widget-body no-padding">
												<div id="toolbar" class="toolbar">
													<a href="javascript:;" class="btn btn-primary btn-refresh"><i class="fa fa-refresh"></i></a>
													<a href="javascript:;" class="btn btn-success btn-add"><i class="fa fa-plus"></i>添加</a>
													<a href="javascript:;" class="btn btn-success btn-edit btn-disabled disabled"><i class="fa fa-pencil"></i> 编辑</a>
													<a href="javascript:;" class="btn btn-danger btn-toggle-all"><i class="fa fa-plus"></i> 显示全部</a>
													<div class="dropdown btn-group">
														<a class="btn btn-primary btn-more dropdown-toggle btn-disabled disabled" data-toggle="dropdown"><i class="fa fa-cog"></i> 更多</a>
														<ul class="dropdown-menu text-left" role="menu">
															<li><a class="btn btn-link btn-multi btn-disabled disabled" href="javascript:;" data-params="1"><i class="fa fa-eye"></i> 设为有效</a></li>
															<li><a class="btn btn-link btn-multi btn-disabled disabled" href="javascript:;" data-params="-1"><i class="fa fa-eye-slash"></i> 设为无效</a></li>
														</ul>
													</div>
												</div>
												<table id="table" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
