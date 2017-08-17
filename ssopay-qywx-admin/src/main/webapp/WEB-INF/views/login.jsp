<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8">
		<title>登录</title>
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
			<shiro:user>
				window.location.href='${ctx}/index';
			</shiro:user>
			var ctxUrl = "${ctx}";
		    
			var require = {
					config : {
						"site" : {
							"name" : "qywx",
							"cdnurl" : "",
							"version" : "1.0.1",
						},
						"actionname" : "login",
						"jsname" : "backend\/index"
					}
				};
		</script>

        <style type="text/css">
            body {
                color:#fff;
                background:url('http://img.infinitynewtab.com/wallpaper/${random}.jpg');
                background-size:cover;
            }
            a {
                color:#fff;
            }
            .login-panel{margin-top:150px;}
            .login-screen {
                max-width:400px;
                padding:0;
                margin:100px auto 0 auto;

            }
            .login-screen .well {
                border-radius: 3px;
                -webkit-box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                background: rgba(255,255,255, 0.2);
            }
            .login-screen .copyright {
                text-align: center;
            }
            @media(max-width:767px) {
                .login-screen {
                    padding:0 20px;
                }
            }
            .profile-img-card {
                width: 100px;
                height: 100px;
                margin: 10px auto;
                display: block;
                -moz-border-radius: 50%;
                -webkit-border-radius: 50%;
                border-radius: 50%;
            }
            .profile-name-card {
                text-align: center;
            }

            #login-form {
                margin-top:20px;
            }
            #login-form .input-group {
                margin-bottom:15px;
            }
            
        </style>
    </head>
    <body>
        <div class="container">
            <div class="login-wrapper">
                <div class="login-screen">
                    <div class="well">
                        <div class="login-form">
                            <img id="profile-img" class="profile-img-card" src="${ctx}/assets/img/avatar.png" />
                            <p id="profile-name" class="profile-name-card"></p>
                            
                            <form action="" method="post" id="login-form">
                                <div id="errtips" class="hide"></div>
                                <div class="input-group">
                                    <div class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></div>
                                    <input type="text" class="form-control" id="c-username" placeholder="用户名" name="username" autocomplete="off" value="" data-rule="用户名:required;username" />
                                </div>

                                <div class="input-group">
                                    <div class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></div>
                                    <input type="password" class="form-control" id="c-password" placeholder="密码" name="password" autocomplete="off" value="" data-rule="密码:required;password" />
                                </div>

								<c:if test="${jcaptchaEbabled}">
									<div class="form-inline">
										<div class="input-group">
		                                    <div class="input-group-addon"><span class="fa fa-check" aria-hidden="true"></span></div>
		                                    <input type="text" class="form-control" id="c-captcha" placeholder="验证码" name="captcha" autocomplete="off" size="35" data-rule="验证码:required" />
		                                </div>
		                                <div class="input-group">
		                                    <img src="${ctx}/code" id="captchaImg" title="点击刷新验证码" />
		                                </div>
	                                </div>
                                </c:if>
                                
                                <div class="form-group">
                                    <label class="inline" for="rememberMe">
                                        <input type="checkbox" name="rememberMe" id="rememberMe" value="true" /> 保持会话
                                    </label>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-success btn-lg btn-block">登 录</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <p class="copyright"><a href="#">Powered By 采采</a></p>
                </div>
            </div>
        </div>
        <script src="${ctx}/assets/js/require.js" data-main="${ctx}/assets/js/require-backend.min.js"></script>
    </body>
</html>