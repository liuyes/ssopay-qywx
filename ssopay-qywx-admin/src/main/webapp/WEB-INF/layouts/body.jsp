<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="action" value="${pageContext.request.servletPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><sitemesh:title/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="renderer" content="webkit">
	
		<link rel="shortcut icon" href="${ctx}/assets/img/favicon.ico" />
		<!-- Loading Bootstrap -->
		<link href="${ctx}/assets/css/backend.min.css" rel="stylesheet">
	
		<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
		<!--[if lt IE 9]>
		<script src="${ctx}/assets/js/html5shiv.js"></script>
		<script src="${ctx}/assets/js/respond.min.js"></script>
		<![endif]-->
		<script type="text/javascript">
			var ctxUrl = "${ctx}";
			var actionUrl = "${action}";
			var actionname = "index";
			if (actionUrl.indexOf('add') > 0) {
				actionname = "add";
			} else if (actionUrl.indexOf('edit') > 0) {
				actionname = "edit";
			}
			//去掉前面的/，然后截取第一个/之前的字符；如/user/edit/2返回user
			actionUrl = actionUrl.substr(1);
			var n = actionUrl.indexOf('\/');
			if (n > 0) {
				actionUrl = actionUrl.substr(0, n);
			}
			var jsname = "backend\/" + actionUrl + "\/" + actionUrl;
			var require = {
				config : {
					"site" : {
						"name" : "qywx",
						"cdnurl" : "",
						"version" : "1.0.1"
					},
					"upload" : {
						"cdnurl" : "/",
						"uploadurl" : "${ctx}/upload",
						"maxsize" : "10mb",
						"mimetype" : "*",
						"multiple" : false
					},
					"moduleurl" : "\/" + actionUrl,
					"actionname" : actionname,
					"jsname" : jsname
				}
			};
			//alert("moduleurl：" + actionUrl);
			//alert("actionname：" + actionname);
			//alert("jsname：" + jsname);
		</script>
		<sitemesh:head/>
	</head>

<body>
	<sitemesh:body/>
	<script src="${ctx}/assets/js/require.js" data-main="${ctx}/assets/js/require-backend.min.js?v=${config.version}"></script>
</body>
</html>