<%--&lt;%&ndash;--%>
  <%--Created by IntelliJ IDEA.--%>
  <%--User: mac--%>
  <%--Date: 2019/8/17--%>
  <%--Time: 5:58 PM--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<c:set var="contextPath" value="${pageContext.request.contextPath}"/>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>用户列表</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<jsp:include page="header.jsp" />--%>

<%--<div class="page-content d-flex align-items-stretch">--%>
    <%--<jsp:include page="index.jsp"/>--%>
<%--<div class="col-md-12">--%>
    <%--<div class="navbar-form navbar-left">--%>
    <%--<div class="form-group" style="width: 100px">--%>
        <%--<input type="text" class="form-control" placeholder="输入用户名或邮箱" id="searchKeyWord"/>--%>
    <%--</div>--%>
    <%--<button class="btn btn-default" style="position: relative;left: 80px" onclick="searchUser();">查询用户</button>--%>
        <%--</div>--%>
    <%--<div style="position: relative;top: 50px;">--%>
        <%--<h1><a style="color: dimgrey;font-family: cursive;position: relative;right: 180px" name="section1">用户信息</a></h1>--%>
    <%--<hr/>--%>
    <%--<table class="table table-hover center"  id="userTable" style="height: 100px;width: 900px;">--%>
    <%--</table>--%>
    <%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</body>--%>

<%--<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">--%>
<%--<link href="${contextPath}/css/style.css" rel="stylesheet">--%>
<%--<script src="${contextPath}/js/jquery-3.4.1.min.js" type="text/javascript"></script>--%>
<%--<script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>--%>
<%--<script src="${contextPath}/js/layer.js" type="text/javascript"></script>--%>
<%--<script src="${contextPath}/js/html5shiv.min.js"></script>--%>
<%--<script src="${contextPath}/js/js/respond.min.js"></script>--%>
<%--<script src="${contextPath}/js/jquery.validate.min.js"></script>--%>
<%--<script src="${contextPath}/js/messages_zh.js"></script>--%>
<%--</body>--%>
<%--<jsp:include page="foot.jsp"/>--%>
<%--<script type="text/javascript" charset="UTF-8">--%>
    <%--listAllUser();--%>
    <%--//列出所有用户--%>
    <%--function listAllUser() {--%>
        <%--var userTable = $("#userTable");--%>
        <%--var allUser = getAllUsers();--%>
        <%--userTable.html('<tr>'+--%>
            <%--'<th> 用户ID </th>'+--%>
            <%--'<th> 用户名</th>'+--%>
            <%--'<th> 昵称</th>'+--%>
            <%--'<th> 邮箱</th>'+--%>
            <%--'<th> 修改信息</th>'+--%>
            <%--'<th> 是否删除</th>'+--%>
            <%--'</tr>');--%>
        <%--var html = "";--%>
        <%--for(var i=0;i<allUser.length;i++){--%>
            <%--html += '<tr>'+--%>
                <%--'<td>'+allUser[i].id+'</td>'+--%>
                <%--'<td>'+allUser[i].userName+'</td>'+--%>
                <%--'<td>'+allUser[i].nickName+'</td>'+--%>
                <%--'<td>'+allUser[i].email+'</td>'+--%>
                <%--'<td>'+--%>
                <%--'<button class="btn btn-primary btn-sm" type="submit" style="background-color: darkred;border-color: white;" onclick="updateUser('+allUser[i].id+')">编辑</button>'+--%>
                <%--'</td>'+--%>
                <%--'<td>'+--%>
                <%--'<button class="btn btn-primary btn-sm" type="submit" style="background-color: darkred;border-color: white;" onclick="deleteUser('+allUser[i].id+')">删除</button>'+--%>
                <%--'</td>'+--%>
                <%--'</tr>';--%>
        <%--}--%>
        <%--userTable.html(userTable.html()+html);--%>
    <%--}--%>

    <%--//获取所有的用户--%>
    <%--function getAllUsers() {--%>
        <%--var nothing = {};--%>
        <%--var userResult = "";--%>
        <%--$.ajax({--%>
            <%--async:false,--%>
            <%--type : 'POST',--%>
            <%--url : '${contextPath}/user?getAllUser',--%>
            <%--data : nothing,--%>
            <%--dataType : 'json',--%>
            <%--success : function(result) {--%>
                <%--userResult = result.allUsers;--%>
            <%--},--%>
            <%--error : function(resoult) {--%>
                <%--layer.alert('查询用户出错咯~');--%>
            <%--}--%>
        <%--});--%>
        <%--return userResult;--%>
    <%--}--%>
    <%--//删除用户--%>
    <%--function deleteUser(id) {--%>
        <%--var nothing = "";--%>
        <%--var deleteResult = "";--%>
        <%--$.ajax({--%>
            <%--async : false,--%>
            <%--type : 'DELETE',--%>
            <%--url : '${contextPath}/user/'+id,--%>
            <%--data : nothing,--%>
            <%--dataType : 'json',--%>
            <%--success : function(result) {--%>
                <%--deleteResult = result;--%>
            <%--},--%>
            <%--error : function(result) {--%>
                <%--layer.alert('删除用户错误');--%>
            <%--}--%>
        <%--});--%>
        <%--layer.msg(deleteResult.message,{icon:1,time:2000},function () {--%>
            <%--listAllUser();--%>
        <%--});--%>
    <%--}--%>
    <%--function searchUser() {--%>
        <%--var userTable = $("#userTable");--%>
        <%--var user =getUserByName();--%>
        <%--userTable.html('<tr>'+--%>
            <%--'<th> 用户ID </th>'+--%>
            <%--'<th> 用户名</th>'+--%>
            <%--'<th> 昵称</th>'+--%>
            <%--'<th> 邮箱</th>'+--%>
            <%--'<th> 是否删除</th>'+--%>
            <%--'</tr>');--%>
        <%--var html="";--%>
        <%--html += '<tr>'+--%>
            <%--'<td>'+user.id+'</td>'+--%>
            <%--'<td>'+user.userName+'</td>'+--%>
            <%--'<td>'+user.nickName+'</td>'+--%>
            <%--'<td>'+user.email+'</td>'+--%>
            <%--'<td>'+--%>
            <%--'<button class="btn btn-primary btn-sm" type="submit" style="background-color: darkred;border-color: white;" onclick="updateUser('+user.id+')">编辑</button>'+--%>
            <%--'</td>'+--%>
            <%--'<td>'+--%>
            <%--'<button class="btn btn-primary btn-sm" type="submit" style="background-color: darkred;border-color: white;" onclick="deleteUser('+user.id+')">删除</button>'+--%>
            <%--'</td>'+--%>
            <%--'</tr>';--%>

        <%--userTable.html(userTable.html()+html);--%>
    <%--}--%>

    <%--// 根据id获取指定用户对象--%>
    <%--function getUserByName() {--%>
        <%--var user = {};--%>
        <%--user.userName = $("#searchKeyWord").val();;--%>
        <%--var tempUser ;--%>
        <%--$.ajax({--%>
            <%--async: false,--%>
            <%--type : 'POST',--%>
            <%--url : '${contextPath}/user?getUserByName',--%>
            <%--data : user,--%>
            <%--dataType : 'json',--%>
            <%--success : function(result) {--%>
                <%--tempUser = result.result;--%>
            <%--},--%>
            <%--error : function(result) {--%>
                <%--layer.alert('查询错误');--%>
            <%--}--%>
        <%--});--%>
        <%--return tempUser;--%>
    <%--}--%>
    <%--function updateUser(id) {--%>
        <%--var alterId={};--%>
        <%--alterId.userId = id;--%>
        <%--var tempUser = "";--%>
        <%--$.ajax({--%>
            <%--async: false,--%>
            <%--type : 'POST',--%>
            <%--url : '${contextPath}/user?setId',--%>
            <%--data : alterId,--%>
            <%--dataType : 'json',--%>
            <%--success : function(result) {--%>
                <%--tempUser = result.result;--%>
            <%--},--%>
            <%--error : function(result) {--%>
                <%--layer.alert('查询错误');--%>
            <%--}--%>
        <%--});--%>
        <%--if(tempUser == "success"){--%>
            <%--window.location.href="${contextPath}/user?alterUserMsg";--%>
        <%--}--%>
    <%--}--%>
<%--</script>--%>
<%--</html>--%>
