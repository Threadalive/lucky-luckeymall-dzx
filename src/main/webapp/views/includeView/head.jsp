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
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${contextPath}/user?main">LuckyMall</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty currentUser}">
                    <li><a href="${contextPath}/user?register">注册</a></li>
                    <li><a href="${contextPath}/login">登录</a></li>
                </c:if>
                <c:if test="${not empty currentUser}">
                    <c:if test="${currentUser.role == 1}">
                        <li><a href="${contextPath}/admin/control" methods="post">控制台</a></li>
                    </c:if>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                ${currentUser.nickName}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${contextPath}/shoppingCar">购物车</a></li>
                            <li><a href="${contextPath}/shoppingRecord?showShoppingRecord">订单状态</a></li>
                            <c:if test="${currentUser.role == 1}">
                                <li><a href="${contextPath}/shoppingRecord?shoppingRecordHandle">处理订单</a></li>
                            </c:if>
                            <li role="separator" class="divider"></li>
                            <li><a href="${contextPath}/user?updateSelfInfo">个人资料修改</a></li>
                            <li><a href="${contextPath}/user?doLogout">注销登录</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>

            <div class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="" id="searchKeyWord"/>
                </div>
                <button class="btn btn-default" onclick="searchProduct();">查找商品</button>
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
</script>
</html>
