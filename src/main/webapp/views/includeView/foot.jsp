<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/5
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>页脚</title>
</head>
<body>
<hr/>
<div class="foot-style" style="position:relative;left: 90px;">
    欢迎来到 <a href="${contextPath}/user?main">LuckyMall购物商城</a>
</div>
</body>
</html>
