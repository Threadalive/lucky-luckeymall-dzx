<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/1
  Time: 4:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>注册</title>
</head>
<body style="background: url('${contextPath}/bgimg/login_img02.png');background-size: cover">
<jsp:include page="../includeView/head.jsp" />
<form id="signupForm" name="signupForm" onsubmit="return false">
<div class="container-fluid">
    <h1 class="title center" style="color: lightgrey;font-family: fantasy;position: relative;top: 50px;">注册</h1>
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
            <nobr><button type="button" id="codeButton" name="codeButton" style="position: relative;left: 69%;bottom: 49px;" onclick="sendPhoneCode()" class="btn btn-default" >发送验证码</button></nobr>
            <div class="form-group" id="pcArea" style="position: relative;bottom: 30px">
                <label for="phoneCode" class="col-sm-2 col-md-2 control-label"></label>
                <div class="col-sm-6 col-md-6">
                    <input type="text" class="form-control" id="phoneCode" name="phoneCode" placeholder="请输入手机验证码"/>
                </div>
            </div>
            <div class="form-group" style="position: relative;bottom: 30px">
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
            <div class="form-group" style="position: relative;bottom: 30px">
                <div class="col-sm-offset-2 col-sm-6">
                    <button class="btn btn-lg btn-primary btn-block" type="submit">立即注册</button>
                </div>
            </div>
        </div>
        <br/>
    </div>
</div>
</form>
<jsp:include page="../includeView/foot.jsp" />
</body>

<script src="${contextPath}/js/jquery.validate.min.js"></script>
<script src="${contextPath}/js/messages_zh.js"></script>

<script>
    var phoneCode = '';

    $.validator.setDefaults({
        submitHandler: function () {
            var loading = layer.load(0);
            if(phoneCode == $("#phoneCode").val()){

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
                    layer.alert("用户注册失败",{icon:3});
                }
            });

            if (registerResult == 'success') {
                layer.msg('注册成功！即将进入登入页',{icon:1},function(){
                    window.location.href="${contextPath}/login";
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
        }else {
                layer.msg('验证码错误哦',{icon:5});
                layer.close(loading);
            }
        }
    });


    $(function () {
        // 在键盘按下并释放及提交后验证提交表单
        $("#signupForm").validate({

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

    function sendPhoneCode() {
        var phoneNum = $("#phoneNumber").val();
        if(phoneNum == ""){
            layer.msg('请输入手机号码',{icon:3})
        }else {
            var num = {};
            num.phoneNum = phoneNum;
        $.ajax({
            async: false, //设置同步
            type: 'POST',
            url: '${contextPath}/user?sendMsg',
            data: num,
            dataType: 'json',
            success: function (data) {
                phoneCode = data.result;
                $("#codeButton").html("<i>发送成功,30s后可重新发送</i>");
                $("#codeButton").attr("disable","true");
                setTimeout(delay,30000);
            },
            error: function (data) {
                layer.alert("用户添加失败",{icon:3});
            }
        });
        }
    }

    function delay() {
        $("#codeButton").html("<i>重新发送</i>");
        $("#codeButton").attr("disable","false");
    }

</script>
<style>
    .error{
        color:red;
        font-weight: bold;
    }
</style>
</html>
