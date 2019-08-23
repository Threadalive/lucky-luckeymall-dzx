<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/22
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>服务器出错咯</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/errorpage/css/style.css" />
</head>
<body style="background-image: url('${contextPath}/errorpage/images/500.png');background-size: cover;background-repeat: no-repeat;background-size: 1500px 699px;">
<div class="tips"style=" position: relative; top: 10%; right: 35%;">
    <h2 class="b-text">矮油~~服务器出错咯...</h2>
    <p class="m-box">
        <span class="m-text">休息一下,去首页逛逛看吧。</span>
        <a class="back-index" href="${contextPath}/user?main")>返回首页</a>
    </p>
</div>
</body>
</html>
