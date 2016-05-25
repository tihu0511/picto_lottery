<%@ page import="com.picto.util.DateUtil" %>
<%@ page import="com.picto.entity.Coupon" %>
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
    <title>${merchant.brand}</title>
    <link rel="stylesheet" href="/css/front/couponInfo.css" />
    <style type="text/css">
        body{margin:0}
    </style>
</head>
<body>
</body>
    <div id="logo"><img src="/images/LOGO.png"></div>
    <div id="main">
        <div id="top">
            <div id="merchantInfo">
                <div id="location">
                    <img src="/images/location.png" /><span>${merchant.address}</span>
                </div>
                <div id="phone">
                    <img src="/images/phone.png" /><span>${merchant.phone}</span>
                </div>
            </div>
            <div id="exchangeText">
                <div>兑换时</div>
                <div>由店员点击</div>
            </div>
            <div id="exchangeBtn"><img src="/images/exchangeBtn.png" /></div>
            <div style="clear:both;" />
        </div>
        <div id="couponInfo">
            <div id="couponLeft">
                <img src="${coupon.icon}" /> <br />
                ${coupon.storeName}
            </div>
            <div id="couponRight">
                <div id="name">${coupon.name}</div>
                <div id="discount">${coupon.discount}</div>
                <div id="useMsg">${coupon.useMsg}</div>
            </div>
        </div>
        <div id="bannerAdvert"><img src="${merchant.bannerAdvert}" /></div>
        <div id="bottom">
            <div id="bottom1">
                <div id="expireDate">有效期：${expireDateStr}</div>
                <div id="limigMsg0">${coupon.limitMsg.split(",")[0]}</div>
                <div id="limigMsg1">${coupon.limitMsg.split(",")[1]}</div>
                <div id="limigMsg2">${coupon.limitMsg.split(",")[2]}</div>
                <div id="msg1">如何保存优惠？</div>
                <div id="msg2">长按右图「识别二维码」
                    <img src="/images/finger.gif" />
                </div>
            </div>
            <div id="bottom2">
                优惠码：${coupon.serialNumber} <br />
                <img src="/images/mr-prize_qrcode.png" />
            </div>
        </div>
    </div>
</html>
