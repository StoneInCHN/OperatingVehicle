<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@page import="java.util.UUID"%>
<%@page import="java.security.interfaces.RSAPublicKey"%>
<%@page import="org.apache.commons.lang.ArrayUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.ov.beans.Setting"%>
<%@page import="com.ov.utils.SettingUtils"%>
<%@page import="com.ov.utils.SpringUtils"%>
<%@page import="com.ov.beans.Setting.CaptchaType"%>
<%@page import="com.ov.service.RSAService"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String base = request.getContextPath();
String captchaId = UUID.randomUUID().toString();
ApplicationContext applicationContext = SpringUtils.getApplicationContext();
Setting setting = SettingUtils.get();
if (applicationContext != null) {
%>
<shiro:authenticated>
<%
	response.sendRedirect(base + "/console/common/main.jhtml");
%>
</shiro:authenticated>
<%
}
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh-cn" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="zh-cn" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="zh-cn"> <!--<![endif]-->
<!--[if lt IE 9]>

	<script src="<%=base%>/resources/js/excanvas.min.js"></script>

	<script src="<%=base%>/resources/js/respond.min.js"></script>  

	<![endif]-->  
<!-- BEGIN HEAD -->
<head>
	<%
if (applicationContext != null) {
	RSAService rsaService = SpringUtils.getBean("rsaServiceImpl", RSAService.class);
	RSAPublicKey publicKey = rsaService.generateKey(request);
	String modulus = Base64.encodeBase64String(publicKey.getModulus().toByteArray());
	String exponent = Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray());
	
	String message = null;
	String loginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	if (loginFailure != null) {
		if (loginFailure.equals("org.apache.shiro.authc.pam.UnsupportedTokenException")) {
			message = "ov.captcha.invalid";
		} else if (loginFailure.equals("org.apache.shiro.authc.UnknownAccountException")) {
			message = "ov.login.unknownAccount";
		} else if (loginFailure.equals("org.apache.shiro.authc.DisabledAccountException")) {
			message = "ov.login.disabledAccount";
		} else if (loginFailure.equals("org.apache.shiro.authc.LockedAccountException")) {
			message = "ov.login.lockedAccount";
		} else if (loginFailure.equals("org.apache.shiro.authc.IncorrectCredentialsException")) {
				message = "ov.login.incorrectCredentials";
		} else if (loginFailure.equals("org.apache.shiro.authc.AuthenticationException")) {
			message = "ov.login.authentication";
		}else{
			message = "ov.login.incorrectCredentials";
		}
	}
%>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title><%=SpringUtils.getMessage("ov.login.title")%> </title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="<%=base%>/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
	<link href="<%=base%>/resources/style/login.css" rel="stylesheet" type="text/css" />
	<link rel="shortcut icon" type="image/x-icon" href="<%=base%>/resources/images/carlife.ico" media="screen" /> 
	<script src="<%=base%>/resources/js/jquery.js" type="text/javascript"></script>   
	<script type="text/javascript" src="<%=base%>/resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=base%>/resources/js/jquery.placeholder.js"></script>
	<script type="text/javascript" src="<%=base%>/resources/js/jsbn.js"></script>
	<script type="text/javascript" src="<%=base%>/resources/js/prng4.js"></script>
	<script type="text/javascript" src="<%=base%>/resources/js/rng.js"></script>
	<script type="text/javascript" src="<%=base%>/resources/js/rsa.js"></script>
	<script type="text/javascript" src="<%=base%>/resources/js/base64.js"></script>
	<script type="text/javascript" src="<%=base%>/resources/js/common.js"></script>
	<script type="text/javascript" src="<%=base%>/resources/js/input.js"></script>
	<script src="<%=base%>/resources/js/jquery.backstretch.min.js" type="text/javascript"></script>
	<script type="text/javascript"> 
	function loadTopWindow(){
		if (window.top!=null && window.top.document.URL!=document.URL)
		{ window.top.location= document.URL; }
	}
	</script>  
<script type="text/javascript">
	$().ready( function() {
		var $loginBtnID = $("#loginBtnID");
		var $loginForm = $("#loginForm");
		var $enPassword = $("#enPassword");
		var $username = $("#username");
		var $password = $("#password");
		var $captcha = $("#captcha");
		var $captchaImage = $("#captchaImage");
		var $isRememberUsername = $("#isRememberUsername");
		var $alertError = $("#alertError");
		
		// 记住用户名
		if(getCookie("adminUsername") != null) {
			$isRememberUsername.prop("checked", true);
			$username.val(getCookie("adminUsername"));
			$password.focus();
		} else {
			$isRememberUsername.prop("checked", false);
			$username.focus();
		}
		
		// 更换验证码
		$captchaImage.click( function() {
			$captchaImage.attr("src", "<%=base%>/console/common/captcha.jhtml?captchaId=<%=captchaId%>&timestamp=" + (new Date()).valueOf());
		});
		//点击登录
		$loginBtnID.click(function(){
			$loginForm.submit();
		});
		// 表单验证、记住用户名
		$loginForm.submit( function() {
			if ($username.val() == "") {
				$alertError.removeClass("hide");
				$alertError.find("span").text("<%=SpringUtils.getMessage("ov.login.usernameRequired")%>");
				$username.addClass("error");
				$password.removeClass("error");
				$captcha.removeClass("error");
				return false;
			}
			if ($password.val() == "") {
				$alertError.removeClass("hide");
				$alertError.find("span").text("<%=SpringUtils.getMessage("ov.login.passwordRequired")%>");
				$password.addClass("error");
				$username.removeClass("error");
				$captcha.removeClass("error");
				return false;
			}
			if ($captcha.val() == "") {
				$alertError.removeClass("hide");
				$alertError.find("span").text("<%=SpringUtils.getMessage("ov.login.captchaRequired")%>");
				$captcha.addClass("error");
				$username.removeClass("error");
				$password.removeClass("error");
				return false;
			}
			if ($isRememberUsername.prop("checked")) {
				addCookie("adminUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
			} else {
				removeCookie("adminUsername");
			}
			
			var rsaKey = new RSAKey();
			rsaKey.setPublic(b64tohex("<%=modulus%>"), b64tohex("<%=exponent%>"));
			var enPassword = hex2b64(rsaKey.encrypt($password.val()));
			$enPassword.val(enPassword);
		});
		
		<%if (message != null) {%>
		$alertError.removeClass("hide");
		$alertError.find("span").text("<%=SpringUtils.getMessage(message)%>");
	<%}%>
	});
</script>
<%} else {%>
<title>提示信息 </title>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<link href="<%=base%>/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="<%=base%>/resources/style/login.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" type="image/x-icon" href="<%=base%>/resources/images/carlife.ico" media="screen" /> 
<%}%>
</head>
<body  class="login" onload="loadTopWindow()">
<div class="top_div"></div>
	<div style="background: #fff; margin: -180px auto auto; border: 1px solid #e7e7e7; width: 450px; height: 340px; text-align: center;">
		<div style="width: 165px; height: 96px; position: absolute;">
				<div class="operatingVehicleTitle">车队管理后台</div>
		</div>
		<form id="loginForm" action="login.jsp" method="post">
				<input type="hidden" id="enPassword" name="enPassword" />
				<input type="hidden" id="localUrl" />
				<%if (ArrayUtils.contains(setting.getCaptchaTypes(), CaptchaType.adminLogin)) {%>
						<input type="hidden" name="captchaId" value="<%=captchaId%>" />
				<%}%>
				<p style="padding: 40px 0px 15px;; position: relative;">
						<span title="用户名" class="u_logo"></span>        
						<input class="ipt" type="text" id="username" name="username" placeholder="请输入用户名" maxlength="30"> 
				</p>
				<p style="padding: 0px 0px 17px;position: relative;">
						<span title="密码" class="p_logo"></span>         
						<input class="ipt" type="password" id="password" placeholder="请输入密码">   
				</p>
				<p style="padding: 0px 0px 17px;position: relative;">      
						<input class="ipt" type="text" id="captcha" name="captcha" placeholder="请输入验证码"  style="padding: 10px 0 10px 10px;width: 239px;"> 
						<img class="captchaImg" id="captchaImage" src="<%=base%>/console/common/captcha.jhtml?captchaId=<%=captchaId%>" title="<%=SpringUtils.getMessage("ov.captcha.imageTitle")%>" />
				</p>
				<div id="alertError" class="alert alert-error hide">
					<button class="close" data-dismiss="alert"></button>
					<span>Enter any username and password.</span>
				</div>
				<div style="height: 45px; line-height: 50px; margin-top: 15px; border-top-color: #e7e7e7; border-top-width: 1px; border-top-style: solid;">
					<p style="margin: 0px 35px 20px 45px;">
							<span style="float: left;">
									<input type="checkbox" style="margin: 3px 3px" class="checkbox" id="isRememberUsername" value="true" /><font style="color: #969696;"> <%=SpringUtils.getMessage("ov.login.rememberUsername")%></font></span>
							<span style="float: right;">
				     				<a class="loginBtn" id="loginBtnID" href="#">登 录</a> 
				     		</span>
					</p>
				</div>
		</form>
	</div>	
	<div class="copyright">
		2015 &copy; rights reserved.
	</div>
</body>
</html>