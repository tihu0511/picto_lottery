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
    <title>彼可托运营后台</title>
    <meta name = "format-detection" content = "telephone=no">
    <link rel="stylesheet" href="/css/oprativeAdmin/couponTypeList.css" />
    <script src="/js/jquery-2.2.4.min.js"></script>
    <script type="text/javascript">
        function choiceCouponType(couponTypeId) {
            var $radio = $("#radio" + couponTypeId);
            if ($radio.attr("checked") == "checked") {
                return ;
            }
            $radio.attr("checked", "checked");
            $radio.click();
            var $radios = $("input[name='couponTypeId']");
            $radios.each(function(i, r){
               if ($(r).attr("id") != $radio.attr("id")) {
                   $(r).removeAttr("checked");
               }
            });
            var $currTr = $($radio.parent("td").parent("tr"));
            $currTr.css("background", "gray");
            var trs = $currTr.siblings("tr");
            $(trs).each(function(i, tr){
               $(tr).css("background", "none");
            });
        }
    </script>
</head>
<body>
<h1>店铺信息完善</h1>
    <div id="choiceMerchant">
        <div id="choice">店铺名&nbsp;&nbsp;
            <select name="merchantId" onchange="choiceMerchant()">
                <option value="0">---请选择---</option>
                <c:forEach items="${allMerchants}" var="merchant">
                    <c:choose>
                        <c:when test="${selectedMerchantId != null and selectedMerchantId == merchant.id}">
                            <option value="${merchant.id}" selected="true">${merchant.mechantName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${merchant.id}">${merchant.mechantName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div id="account">用户名&nbsp;&nbsp;<span id="accountName"></span> </div>
    </div>
    <div id="main">
        <div id="top">
            <div id="tools">
                <img src="/images/create.png" />
                <img src="/images/refresh.png" />
                <img src="/images/delete.png" />
            </div>
            <div id="typeOrDiscount">
                <div id="type"><a href="">奖项</a></div>
                <div id="discount"><a href="">优惠</a></div>
            </div>
            <div style="clear:both;"></div>
        </div>
        <div id="couponTypes">
            <table cellpadding="0" cellspacing="0">
                <thead>
                    <tr>
                        <td width="5%"></td>
                        <td width="5%">商户Id</td>
                        <td width="10%">奖项名称</td>
                        <td width="10%">奖项图标</td>
                        <td width="10%">奖项数量</td>
                        <td width="10%">奖项占比</td>
                        <td width="5%">重置时间</td>
                        <td width="10%">奖项类型</td>
                        <td width="10%">是否即时优惠</td>
                        <td width="25%">备注</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${couponTypes}" var="couponType">
                        <tr onclick="choiceCouponType(${couponType.id})">
                            <td><input id="radio${couponType.id}" type="radio" name="couponTypeId" value="${couponType.id}" /></td>
                            <td>${couponType.merchantId}</td>
                            <td>${couponType.name}</td>
                            <td><img src="${couponType.icon}" /></td>
                            <td>${couponType.totalNum}</td>
                            <td>${couponType.percent}</td>
                            <td>${couponType.resetInterval}</td>
                            <td>${couponType.type}</td>
                            <td>${couponType.isImmediate ? "是" : "否"}</td>
                            <td>${couponType.remark}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>

</html>
