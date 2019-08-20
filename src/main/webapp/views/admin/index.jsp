<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/17
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>后台</title>
</head>
<body>

<nav class="side-navbar">
    <!-- Sidebar Header-->
    <div class="sidebar-header d-flex align-items-center">
        <div class="avatar"><img src="img/avatar-1.jpg" alt="..." class="img-fluid rounded-circle"></div>
        <div class="title">
            <h1 class="h4">Mark Stephen</h1>
            <p>Web Designer</p>
        </div>
    </div>
    <!-- Sidebar Navidation Menus--><span class="heading">Main</span>
    <ul class="list-unstyled">
        <li><a href="user?home"> <i class="icon-home"></i><strong>主页</strong></a></li>
        <li><a href="#exampledropdownDropdown" aria-expanded="false" data-toggle="collapse"> <i class="icon-user"></i><strong>用户管理</strong></a>
            <ul id="exampledropdownDropdown" class="collapse list-unstyled ">
                <li><a href="${contextPath}/user?addUser"><strong>添加用户</strong></a></li>
                <li><a href="${contextPath}/user?showUser"><strong>查询用户</strong></a></li>
                <li><a href="#"><strong>用户积分管理</strong></a></li>
            </ul>
        <li><a href="${contextPath}/shoppingRecord?orderHandle"> <i class="icon-bill"></i><strong>订单管理</strong></a></li>
        <li><a href="#exampledropdownDropdown2" aria-expanded="false" data-toggle="collapse"> <i class="icon-padnote"></i><strong>商品管理</strong></a>
            <ul id="exampledropdownDropdown2" class="collapse list-unstyled ">
                <li><a href="${contextPath}/product?getProductPage"><strong>查询商品</strong></a></li>
                <li><a href="#"><strong>添加商品</strong></a></li>
                <li><a href="#"><strong>下架商品</strong></a></li>
            </ul>
        </li>
        <li><a href="login"> <i class="icon-interface-windows"></i><strong>登录页</strong></a></li>
    </ul><span class="heading">Extras</span>
    <ul class="list-unstyled">
        <li> <a href="#"> <i class="icon-flask"></i>Demo </a></li>
        <li> <a href="#"> <i class="icon-screen"></i>Demo </a></li>
        <li> <a href="#"> <i class="icon-mail"></i>Demo </a></li>
        <li> <a href="#"> <i class="icon-picture"></i>Demo </a></li>
    </ul>
</nav>
</body>
</html>
