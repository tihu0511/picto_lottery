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
    <script src="/js/jquery-2.2.4.min.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
    <div id="main">
        <input type="hidden" value="${merchant.id}" />
        <table id="merchantTab">
            <tr>
                <td>用户名</td>
                <td>${merchant.mechantName}</td>
                <td></td>
            </tr>
            <tr>
                <td>抽奖链接</td>
                <td>${merchant.lotteryLink}</td>
                <td></td>
            </tr>
            <tr>
                <td>贴纸二维码</td>
                <td><img src="${merchant.lotteryQrcode}" /></td>
                <td></td>
            </tr>
            <tr>
                <td>店铺名称</td>
                <td><input type="text" name="merchant.mechantName" value="${merchant.mechantName}" /></td>
                <td><span class="message"> *不多于20个汉字</span></td>
            </tr>
            <tr>
                <td>品牌名称</td>
                <td><input type="text" name="merchant.mechantName" value="${merchant.brand}" /></td>
                <td><span class="message">*不多于8个汉字</span></td>
            </tr>
            <tr>
                <td>奖券保存方式</td>
                <td>
                    <select name="saveType">
                        <c:forEach var="i" begin="0" end="${saveTypeCodes.size()}" varStatus="status">
                            <c:choose>
                                <c:when test="${saveTypeCodes.get(i).equals(merchant.saveType)}">
                                    <option value="${saveTypeCodes.get(i)}" selected="true">${saveTypeDescs.get(i)}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${saveTypeCodes.get(i)}">${saveTypeDescs.get(i)}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>存奖二维码</td>
                <td>
                    <img src="${merchant.mechantQrcode}" />
                    <input type="hidden" name="merchant.mechantQrcode" value="${merchant.mechantQrcode}" />
                    上传二维码<input type="file" />
                </td>
                <td></td>
            </tr>
            <tr>
                <td>主广告位</td>
                <td>
                    <img src="${merchant.mainAdvert}" />
                    <input type="hidden" name="merchant.mainAdvert" value="${merchant.mainAdvert}" />
                    上传主广告位<input type="file" />
                </td>
                <td></td>
            </tr>
            <tr>
                <td>banner广告位</td>
                <td>
                    <img src="${merchant.bannerAdvert}" />
                    <input type="hidden" name="merchant.bannerAdvert" value="${merchant.bannerAdvert}" />
                    上传banner广告位<input type="file" />
                </td>
                <td></td>
            </tr>
            <tr>
                <td>月均人流量</td>
                <td><input type="text" name="merchant.volumn" value="${merchant.volumn}" /></td>
                <td></td>
            </tr>
            <tr>
                <td>地址</td>
                <td><input type="text" name="merchant.address" value="${merchant.address}" /></td>
                <td><span class="message">*不多于20个汉字</span></td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input type="text" name="merchant.phone" value="${merchant.phone}" /></td>
                <td><span class="message">*不多于15个字符</span></td>
            </tr>
            <tr>
                <td>主营业务</td>
                <td><input type="text" name="merchant.mainBusiness" value="${merchant.mainBusiness}" /></td>
                <td><span class="message">*不多于10个汉字</span></td>
            </tr>
            <tr>
                <td>备注</td>
                <td><textarea name="merchant.remark">${merchant.remark}</textarea></td>
                <td></td>
            </tr>
        </table>
    </div>
</body>

</html>
