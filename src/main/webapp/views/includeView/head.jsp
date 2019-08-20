<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/5
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>导航栏</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<!--导航栏部分-->
<nav class="navbar navbar-default navbar-fixed-top" style="height: 62px;">
    <div class="container-fluid" style="height: 60px">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="true">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <c:if test="${currentUser.role == 0}">
            <a class="navbar-brand" href="${contextPath}/user?main" style="color: crimson;font-size: x-large;font-family: fantasy">LuckyMall</a>
            </c:if>
            <c:if test="${empty currentUser}">
                <a class="navbar-brand" href="${contextPath}/user?main" style="color: crimson;font-size: x-large;font-family: fantasy">LuckyMall</a>
            </c:if>
            <c:if test="${currentUser.role == 1}">
                <h2 class="navbar-brand" href="${contextPath}/user?main" style="color: crimson;font-size: x-large;font-family: fantasy">LuckyMall</h2>
            </c:if>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty currentUser}">
                    <li><a href="${contextPath}/user?register">注册</a></li>
                    <li><a href="${contextPath}/login">登录</a></li>
                </c:if>
                <c:if test="${not empty currentUser}">
                    <c:if test="${currentUser.role == 1}">
                        <li><a href="${contextPath}/user?control">控制台</a></li>
                    </c:if>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
                                ${currentUser.nickName}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${currentUser.role == 0}">
                            <li><a href="${contextPath}/shoppingRecord?showShoppingRecord">订单状态</a></li>
                            </c:if>
                            <%--<li role="separator" class="divider"></li>--%>
                            <li><a href="${contextPath}/user?updateSelfInfo">个人资料修改</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="" onclick="doLogout()">注销登录</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
            <div class="navbar-form navbar-right" style="position: relative;left: 15px">
                <img src="${contextPath}/bgimg/tel.gif" style="position: relative;right: 10px" onclick="call()" />
                <c:if test="${currentUser.role !=1}">
                <a style="font-family: cursive;font-size: 20px;color: dimgrey;position: relative;top: 2px;" href="${contextPath}/shoppingCar">购物车</a>
                <img src="${contextPath}/bgimg/spcarlogo.jpg" width="20px" height="20px" style="position: relative;right: 5px">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="" id="searchKeyWord"/>
                </div>
                <button class="btn btn-default" onclick="searchProduct();">查找商品</button>
                </c:if>
            </div>
        </div>
    </div>
</nav>

</body>

<%--导入依赖包--%>
<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/css/style.css" rel="stylesheet">
<script src="${contextPath}/js/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${contextPath}/js/layer.js" type="text/javascript"></script>
<script src="${contextPath}/js/html5shiv.min.js"></script>
<script src="${contextPath}/js/js/respond.min.js"></script>

<%--关键字查询商品--%>
<script type="text/javascript">
    function searchProduct() {
        var search = {};
        search.searchKeyWord = $("#searchKeyWord").val();
        var searchResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${contextPath}/product?searchPre',
            data : search,
            dataType : 'json',
            success : function(result) {
                searchResult = result.result;
            },
            error : function(result) {
                layer.alert('查询失败咯');
            }
        });
        if(searchResult == "success")
            window.location.href = "${contextPath}/product?search";
    }

    function call(){
        layer.msg('客服电话 18160744093~',{icon:1,time:4000}
        );
    }
    function doLogout() {
        var message = "";
        var logoutResult="";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${contextPath}/user?doLogout',
            data : message,
            dataType : 'json',
            success : function(result) {
                logoutResult = result.result;
            },
            error : function(result) {
                layer.alert('查询失败咯');
            }
        });
        if(logoutResult == "success")
            layer.msg('用户已退出',{icon:1,time:2000},function () {
                window.location.href = "${contextPath}/user?main";
                }
            );
    }
</script>
</html>
