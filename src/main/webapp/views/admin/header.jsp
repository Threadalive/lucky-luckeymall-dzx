<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/17
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>主页</title>
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="${contextPath}/css/vendor/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="${contextPath}/css/vendor/font-awesome/css/font-awesome.min.css">
    <!-- Fontastic Custom icon font-->
    <link rel="stylesheet" href="${contextPath}/css/css/fontastic.css">
    <!-- Google fonts - Poppins -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="${contextPath}/css/css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="${contextPath}/css/css/custom.css">
</head>
<body>

<div class="page" style="position:relative;bottom: 20px;">
    <!-- Main Navbar-->
    <header class="header" style="margin-bottom: 0px;position: relative;top: 10px;">
        <nav class="navbar">
            <!-- Search Box-->
            <div class="search-box">
                <button class="dismiss"><i class="icon-close"></i></button>
                <form id="searchForm" action="#" role="search">
                    <input type="search" placeholder="您想要查询什么?" class="form-control">
                </form>
            </div>
            <div class="container-fluid">
                <div class="navbar-holder d-flex align-items-center justify-content-between">
                    <!-- Navbar Header-->
                    <div class="navbar-header">
                        <!-- Navbar Brand --><a href="user?home" class="navbar-brand d-none d-sm-inline-block">
                        <div class="brand-text d-none d-lg-inline-block"><span>LuckyMall</span><strong>后台管理系统</strong></div>
                        <!-- Toggle Button--><a id="toggle-btn" href="#toggle-btn" class="menu-btn active" style="position: relative;top: 10px;"><span></span><span></span><span></span></a>
                    </div>
                    <!-- Navbar Menu -->
                    <ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
                        <img src="${contextPath}/bgimg/tel.gif" style="width: 25px;height: 25px;position: relative;right: 10px;top: 3px;" onclick="call()" />
                        <!-- Search-->
                        <li class="nav-item d-flex align-items-center"><a id="search" href="#"><i class="icon-search"></i></a></li>
                        <!-- Messages                        -->
                        <li class="nav-item dropdown"> <a id="notifications" rel="nofollow" data-target="#" href="#notifications" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link"><i class="fa fa-envelope-o"></i><span class="badge bg-orange badge-corner">10</span></a>
                            <ul aria-labelledby="notifications" class="dropdown-menu">
                                <li><a rel="nofollow" href="#" class="dropdown-item d-flex">
                                    <div class="msg-profile"> <img src="img/avatar-1.jpg" alt="..." class="img-fluid rounded-circle"></div>
                                    <div class="msg-body">
                                        <h3 class="h5">Jason Doe</h3><span>Sent You Message</span>
                                    </div></a></li>
                                <li><a rel="nofollow" href="#" class="dropdown-item d-flex">
                                    <div class="msg-profile"> <img src="img/avatar-2.jpg" alt="..." class="img-fluid rounded-circle"></div>
                                    <div class="msg-body">
                                        <h3 class="h5">Frank Williams</h3><span>Sent You Message</span>
                                    </div></a></li>
                                <li><a rel="nofollow" href="#" class="dropdown-item d-flex">
                                    <div class="msg-profile"> <img src="img/avatar-3.jpg" alt="..." class="img-fluid rounded-circle"></div>
                                    <div class="msg-body">
                                        <h3 class="h5">Ashley Wood</h3><span>Sent You Message</span>
                                    </div></a></li>
                                <li><a rel="nofollow" href="#" class="dropdown-item all-notifications text-center"> <strong>Read all messages   </strong></a></li>
                            </ul>
                        </li>
                        <!-- Languages dropdown    -->
                        <li class="nav-item dropdown"><a id="languages" rel="nofollow" data-target="#" href="#languages" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link language dropdown-toggle"><img src="img/flags/16/GB.png" alt="English"><span class="d-none d-sm-inline-block">English</span></a>
                            <ul aria-labelledby="languages" class="dropdown-menu">
                                <li><a rel="nofollow" href="#" class="dropdown-item"> <img src="img/flags/16/CN.png" alt="Chinese" class="mr-2">中文</a></li>
                                <li><a rel="nofollow" href="#" class="dropdown-item"> <img src="img/flags/16/CK.png" alt="English" class="mr-2">英文</a></li>
                            </ul>
                        </li>
                        <!-- Logout    -->
                        <li class="nav-item"><a href="javascript:doLogout()" class="nav-link logout"> <span class="d-none d-sm-inline">退出登录</span><i class="fa fa-sign-out"></i></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

<!-- JavaScript files-->
<script src="${contextPath}/css/vendor/jquery/jquery.min.js"></script>
<script src="${contextPath}/css/vendor/popper.js/umd/popper.min.js"> </script>
<script src="${contextPath}/css/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${contextPath}/css/vendor/jquery.cookie/jquery.cookie.js"> </script>
<script src="${contextPath}/css/vendor/jquery-validation/jquery.validate.min.js"></script>
<!-- Main File-->
<script src="${contextPath}/js/front.js"></script>

<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<script src="${contextPath}/js/layer.js" type="text/javascript"></script>

</body>
<script type="text/javascript">
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
                    window.location.href = "${contextPath}/login";
                }
            );
    }
</script>
</html>
