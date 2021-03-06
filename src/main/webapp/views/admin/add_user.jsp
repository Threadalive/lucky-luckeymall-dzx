<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/17
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
</head>
<body style="position: relative;bottom: 50px">
<jsp:include page="../includeView/head.jsp" />

<div class="page-content d-flex align-items-stretch">
<form id="addForm" onsubmit="return false">
    <div class="container-fluid">
        <h1 class="title center" style="color: dimgray;font-family: cursive;position: relative;top: 50px;right: 5px">添加用户</h1>
        <br/>
        <div class="col-sm-offset-2 col-md-offest-2">
            <!-- 表单输入 -->
            <div  class="form-horizontal" style="height:500px;position:relative;width:529px;left:200px;top: 50px">
                <div class="form-group">
                    <label for="userName" class="col-sm-2 col-md-2 control-label"></label>
                    <div class="col-sm-6 col-md-6">
                        <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入用户名"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 col-md-2 control-label"></label>
                    <div class="col-sm-6 col-md-6">
                        <input type="email" class="form-control" id="email" name="email" placeholder="请输入正确的邮箱格式"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 col-md-2 control-label"></label>
                    <div class="col-sm-6 col-md-6">
                        <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirm_password" class="col-sm-2 col-md-2 control-label"></label>
                    <div class="col-sm-6 col-md-6">
                        <input type="password" class="form-control" id="confirm_password" name="confirm_password" placeholder="请确认您的密码"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="phoneNumber" class="col-sm-2 col-md-2 control-label"></label>
                    <div class="col-sm-6 col-md-6">
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="请输入手机号"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="man" class="col-sm-2 col-md-2 control-label"></label>
                    <div class="col-sm-6 col-md-6" style="position: relative;left: 4px;">
                        <label class="radio-inline">
                            <input type="radio" id="man" name="sex" value="1"> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" id="woman" name="sex" value="2"> 女
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-6">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">立即注册</button>
                    </div>
                </div>
            </div>
            <br/>
        </div>
    </div>
</form>
</div>
<script src="${contextPath}/js/jquery.validate.min.js"></script>
<script src="${contextPath}/js/messages_zh.js"></script>
</body>
<jsp:include page="../includeView/foot.jsp" />
<script>
    $.validator.setDefaults({
        submitHandler: function () {
            var loading = layer.load(0);

            var user = $('form').serializeArray();

            var registerResult = null;
            $.ajax({
                async: false, //设置同步
                type: 'POST',
                url: '${contextPath}/user?register',
                data: user,
                dataType: 'json',
                success: function (data) {
                    registerResult = data.result;
                },
                error: function (data) {
                    layer.alert("用户添加失败",{icon:3});
                }
            });

            if (registerResult == 'success') {
                layer.msg('添加成功！',{icon:1},function(){
                    window.location.href = "${contextPath}/user?control";
                });
            }
            else if (registerResult == 'nameExist') {
                layer.close(loading);
                layer.msg('这个用户名已经被占用啦！', {icon: 2});
            }
            else if (registerResult == 'emailExist') {
                layer.close(loading);
                layer.msg('这个邮箱已经注册啦！', {icon: 2});
            }
            else if (registerResult == 'fail') {
                layer.close(loading);
                layer.msg('服务器异常', {icon: 2});
            }
        }
    });

    $(function () {
        // 在键盘按下并释放及提交后验证提交表单
        $("#addForm").validate({

            errorPlacement: function(error, element) {
                if (element.is(":radio"))
                    error.appendTo(element.parent().parent().next().next());
                else
                    error.appendTo(element.parent().parent().next());
            },
            errorElement: "span",
            rules: {
                userName: {
                    required: true,
                    minlength: 2,
                    maxlength: 10
                },
                password: {
                    required: true,
                    minlength: 5,
                    maxlength: 20
                },
                confirm_password: {
                    required: true,
                    minlength: 5,
                    maxlength: 20,
                    equalTo: "#password"
                },
                email: {
                    required: true,
                    email: true
                },
                phoneNumber: {
                    required: true,
                    rangelength: [11, 11]
                }
            },
            messages: {
                userName: {
                    required: "(请输入用户名)",
                    minlength: "(用户名最少由两个字母组成)",
                    maxlenth: "(用户名不得超过10个字符)"
                },
                password: {
                    required: "(请输入密码)",
                    minlength: "(密码长度不能小于 5 个字母)",
                    maxlength: "(密码不能大于20个字符)"
                },
                confirm_password: {
                    required: "(请输入密码)",
                    minlength: "(密码长度不能小于5个字母)",
                    maxlength: "(密码不能大于20个字符)",
                    equalTo: "(两次密码输入不一致)"
                },
                phoneNumber: {
                    required: "(请输入手机号码)",
                    rangelength: "(手机号码格式错误)"
                },
                email: "(请输入一个正确的邮箱)"
            }
        });
    });
</script>
<style>
    .error{
        color:red;
        font-weight: bold;
    }
</style>
</html>
