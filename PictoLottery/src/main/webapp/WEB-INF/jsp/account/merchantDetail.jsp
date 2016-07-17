<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<title>买单先生（商户版）</title>
	<style>
	body{ font-size:14px; font-family:"微软雅黑" }
	.login_form { border:1px solid #999; margin:50px auto; padding:15px 30px; width:260px }
	.login_form input { width:120px; }
	.login_head { color:#0066cc; font-size:20px; text-align:center; line-height:20px }
	.login_table{ clear:both; height:160px }
	.login_table dt{ float:left; clear:left; width:110px; height:40px; text-align:right; line-height:40px }
	.login_table dd{ float:left; clear:right; height:40px; line-height:40px; margin-left:20px; }
	.login_butt { text-align:center; margin:20px 0 20px }
	.login_butt input{ width:128px; height:40px; background:url('../../images/account/submit.png') no-repeat; border:0; text-indent:-9999px }
	</style>
</head>

<body>

<div class="login_form">
	<h1 class="login_head">数据统计</h1>
	<form action="<%=request.getContextPath()%>/doLogin.do" method="post">
		<dl class="login_table" style="height:100px; border-bottom:1px #ccc solid;">
			<dt>店铺名：</dt>
			<dd>${merchant.mechantName}</dd>
			<dt>品牌名：</dt>
			<dd>${merchant.brand}</dd>
		</dl>
		<div>
			<h1 style="color:#0011ef; font-size:17px; text-align:left; padding-left:30px; line-height:17px">剩余奖项</h1>
			<dl class="login_table">
				<c:forEach var="couponType" items="${couponTypes}">
					<dt>${couponType.name}：</dt>
					<dd>${couponType.restNum}</dd>
				</c:forEach>
			</dl>
		</div>
		<dl class="login_table" style="padding-top:60px;height:100px;">
			<dt>本日抽奖次数：</dt>
			<dd>${todayNum}</dd>
			<dt>本月累计次数：</dt>
			<dd>${monthNum}</dd>
		</dl>

	</form>
</div>

</body>

</html>