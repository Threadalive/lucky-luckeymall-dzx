<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/19
  Time: 9:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单处理</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
</head>
<body>
<jsp:include page="header.jsp" />

<div class="page-content d-flex align-items-stretch">
    <jsp:include page="index.jsp"/>
    <jsp:include page="shopping_handle.jsp"/>
</div>
<div>
<jsp:include page="foot.jsp"/>
</div>
</body>

<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/css/style.css" rel="stylesheet">
<script src="${contextPath}/js/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${contextPath}/js/layer.js" type="text/javascript"></script>
<script src="${contextPath}/js/html5shiv.min.js"></script>
<script src="${contextPath}/js/js/respond.min.js"></script>
</html>
