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
        div#mainAdvert {
            width: 100%;
            height: 63.53%;
        }
        div#mainAdvert img {
            width: 100%;
            height: 100%;
        }
        div#bannerAdvert {
            width: 100%;
            height: 21.18%;
        }
        div#bannerAdvert img {
            width: 100%;
            height: 100%;
        }
        div#roll {
            width: 100%;
            height: 15.29%;
            background-image: url(/images/border_new.png);
            background-size: 100% 100%;
        }
        div#r1,#r2,#r3 {
            float:left;
            width: 22%;
            margin: 2.5% 0 2.5% 8.5%;
            height: 78%;
        }
        #r1 img,#r2 img,#r3 img {
            width: 100%;
            height: 100%;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function(){
            var showImages = '${showIcons}';
            var showIcons = showImages.split(";");
            setTimeout(function(){
                $("#r1 img").attr("src", showIcons[0]);
            }, 7800);
            setTimeout(function(){
                $("#rollImage2").attr("src", showIcons[1]);
            }, 9000);
            setTimeout(function(){
                $("#rollImage3").attr("src", showIcons[2]);
            }, 10200);

            setTimeout(function(){
                window.location.href = "/lotteryFinish.do?luckyCouponTypeId=" + '${luckyCouponTypeId}';
            }, 13000);
        });
    </script>
</head>
<body>
    <div id="mainAdvert"><img src="${merchant.mainAdvert}" /></div>
    <div id="roll">
        <div id="r1"><img src="/images/lotteryRoll_a.gif"></div>
        <div id="r2"><img src="/images/lotteryRoll_b.gif"></div>
        <div id="r3"><img src="/images/lotteryRoll_c.gif"></div>
    </div>
    <div id="bannerAdvert"><img src="${merchant.bannerAdvert}" /></div>
</body>
</html>
