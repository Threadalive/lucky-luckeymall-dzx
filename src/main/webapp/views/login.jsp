<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/3
  Time: 2:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>LuckyMall</title>
</head>
<body>
<form id="loginForm">
    <div class="container-fluid" style="padding-top: 80px;padding-bottom: 80px" >
        <h1 class="title center">登录</h1>
        <br/>
        <div class="col-sm-offset-2 col-md-offest-2">
            <!-- 表单输入 -->
            <div  class="form-horizontal">
                <div class="form-group">
                    <label for="nameOrEmail" class="col-sm-2 col-md-2 control-label">邮箱/用户名</label>
                    <div class="col-sm-6 col-md-6">
                        <input type="text" class="form-control" id="nameOrEmail" name="nameOrEmail" placeholder="xxxxxx@xx.com"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 col-md-2 control-label">密码</label>
                    <div class="col-sm-6 col-md-6">
                        <input type="password" class="form-control" id="password" name="password" placeholder="禁止输入非法字符" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-6">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
                    </div>
                </div>
            </div>
            <br/>
        </div>
    </div>
</form>
</body>

<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/css/style.css" rel="stylesheet">

<script src="${contextPath}/js/jquery-3.4.1.js"></script>
<script src="${contextPath}/js/layer.js"></script>
<script src="${contextPath}/js/jquery.validate.min.js"></script>
<script src="${contextPath}/js/messages_zh.js"></script>
</html>


<script>
    $.validator.setDefaults({
        submitHandler: function () {
            var loading = layer.load(0);

            var user = $('form').serializeArray();

            var loginResult = null;
            $.ajax({
                async: false, //设置同步
                type: 'POST',
                url: '${contextPath}/login',
                data: user,
                dataType: 'json',
                success: function (data) {
                    loginResult = data.result;
                    layer.close(loading);
                },
                error: function (data) {
                    layer.alert("用户登陆失败");
                }
            });
            if(loginResult == 'success'){
                layer.msg('登录成功',{icon:1},function(){
                    window.location.href = "${contextPath}/user?main";
                });
            }
            else if(loginResult == 'nameUnexist'){
                layer.msg('是不是用户名记错了？',{icon:2});
            }
            else if(loginResult == 'wrong'){
                layer.msg('密码不对哦，再想想~',{icon:2});
            }
            else if(loginResult == 'fail'){
                layer.msg('服务器异常',{icon:2});
            }
        }
    });

    $().ready(function () {
        // 在键盘按下并释放及提交后验证提交表单
        $("#loginForm").validate({

            errorPlacement: function(error, element) {
                    error.appendTo(element.parent().parent().next());
            },
            errorElement: "div",
            rules: {
                nameOrEmail: {
                    required: true,
                },
                password: {
                    required: true,
                },
            },
            messages: {
                nameOrEmail: {
                    required: "(请输入用户名或邮箱)"
                },
                password: {
                    required: "(请输入密码)"
                }
            }
        });
    });
</script>
