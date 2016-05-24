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
    <script src="/js/jquery-2.2.4.min.js"></script>
    <style type="text/css">
        body{margin:0}
        #startGif{
            width: 30%;
            position: absolute;
            top: 25%;
            left: 35%;
        }
        #curtain{
            display: none;
            width: 100%;
            height: 100%;
            background-image: url(/images/curtain.jpg);
            background-size: 100% 100%;
        }
        #curtainText{
            width: 40%;
            top: 35%;
            left: 30%;
            position: relative;
        }
        #errorMsg{
            font-size: 2.5em;
            color: black;
            text-align: center;
            position: relative;
            top: 40%;
        }
    </style>
    <script type="text/javascript">
        function last5Second() {
            setTimeout(function(){
                $("#startGif").hide();
                $("#curtain").show();
            }, 5000);
        }
        function startLottery() {
            var code = '${code}';
            var merchantId = '${merchantId}';
            $.ajax({
                type: "POST",
                url: "/verifyLottery.do",
                data: {"merchantId":merchantId, "code":code},
                dataType: "json",
                success: function(data, textStatus) {
                    if (textStatus == "success" && data.errorMsg == null) {
                        window.location.href = "/lottery.do?openid=" + data.openid;
                    } else {
                        $("#errorMsg").html(data.errorMsg);
                        $("#curtainText").hide();
                        $("#curtain").css("background-image", "none");
                    }
                },
                error: function(){
                    $("#errorMsg").html("系统错误");
                    $("#curtainText").hide();
                    $("#curtain").css("background-image", "none");
                }
            });
        }
    </script>
</head>
<body>
    <div id="startGif"><img src="images/last5Second.gif" style="width: 100%;" onload="last5Second()" /></div>
    <div id="curtain">
        <div id="curtainText">
            <img src="/images/curtain_Text.png" style="width:100%" onclick="startLottery()" />
        </div>
        <div id="errorMsg">${errorMsg}</div>
    </div>
</body>
</html>
