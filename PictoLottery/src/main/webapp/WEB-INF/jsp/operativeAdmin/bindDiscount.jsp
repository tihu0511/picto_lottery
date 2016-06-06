<%--
  Created by IntelliJ IDEA.
  User: wujigang
  Date: 2016/5/21
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>彼可托运营后台</title>
    <meta name = "format-detection" content = "telephone=no">
    <script src="/js/jquery-2.2.4.min.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
    <h1>绑定优惠</h1>
    <div id="top">
        <div id="topTitle">已有优惠</div>
        <div id="hadDiscounts">
            <table cellpadding="0" cellspacing="0">
                <thead>
                <tr>
                    <td width="5%">商户Id</td>
                    <td width="10%">优惠名称</td>
                    <td width="10%">优惠图标</td>
                    <td width="10%">优惠力度</td>
                    <td width="10%">重要信息</td>
                    <td width="10%">限制信息</td>
                    <td width="10%">有效期(小时)</td>
                    <td width="5%">是否可重用</td>
                    <td width="5%">是否可外发</td>
                    <td width="10%">备注</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${hadDiscounts}" var="discountProduct">
                    <tr>
                        <td>${discountProduct.merchantId}</td>
                        <td>${discountProduct.name}</td>
                        <td><img src="${discountProduct.icon}" /></td>
                        <td>${discountProduct.discount}</td>
                        <td>${discountProduct.useMsg}</td>
                        <td>${discountProduct.limitMsg}</td>
                        <td>${discountProduct.validity}</td>
                        <td>${discountProduct.isShared ? "是" : "否"}</td>
                        <td>${discountProduct.isSendout ? "是" : "否"}</td>
                        <td>${couponType.remark}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div id="main">
        <div id="mainTitle">选择其它优惠</div>
    </div>
</body>

</html>
