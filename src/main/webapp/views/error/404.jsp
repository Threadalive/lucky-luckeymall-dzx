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
    <title>您访问的页面不在地球上</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/errorpage/css/style.css" />
</head>
<body>

<div class="newfinno-con"></div>

<div class="tips">
    <h2 class="b-text">矮油~~您访问的页面不在地球上...</h2>
    <p class="m-box">
        <span class="m-text">休息一下,去首页逛逛看吧。</span>
        <a class="back-index" href="${contextPath}/user?main">返回首页</a>
    </p>
</div>
</body>
</html>
