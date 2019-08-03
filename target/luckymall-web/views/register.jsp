<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/1
  Time: 4:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>注册</title>
</head>
<body>
<form id="signupForm">
    <div>
        <label>用户名</label>
        <div>
            <input type="text"  name="userName" placeholder="请输入用户名"/>
        </div>
    </div>
    <div>
        <label>昵称</label>
        <div>
            <input type="text" name="nickName" placeholder="请输入昵称" />
        </div>
    </div>
    <div>
        <label>邮箱</label>
        <div>
            <input type="email" name="email" placeholder="请输入正确的邮箱格式"/>
        </div>
    </div>
    <div>
        <label>密码</label>
        <div>
            <input type="password" name="password" placeholder="禁止输入非法字符" />
        </div>
    </div>
    <div>
        <label>再次输入密码</label>
        <div>
            <input type="password" name="confirm_password" placeholder="禁止输入非法字符" />
        </div>
    </div>
    <div>
        <label>手机号码</label>
        <div>
            <input type="text" name="phoneNumber" placeholder="请输入手机号" />
        </div>
    </div>
    <div>
        <label>性别</label>
        <div>
            <label>
                <input type="radio" name="sex" value="1"> 男
            </label>
            <label>
                <input type="radio" name="sex" value="2"> 女
            </label>
        </div>
        <div>
            <label>出生日期</label>
            <div>
                <input type="date"  name="birthday" placeholder="请输入出生日期" />
            </div>
        </div>
        <div>
            <label>邮政编码</label>
            <div>
                <input type="text" name="postNumber" placeholder="请输入邮政编码" />
            </div>
        </div>
        <div>
            <label>地址</label>
            <div>
                <input type="text" name="address" placeholder="请输入寄送地址" />
            </div>
        </div>
    </div>
    <div>
        <button type="submit">注册</button>
    </div>
</form>
</body>

<link href="${cp}/css/bootstrap.min.css" rel="stylesheet">
<link href="${cp}/css/style.css" rel="stylesheet">

<script src="${contextPath}/js/jquery-3.4.1.js"></script>
<script src="${contextPath}/js/layer.js"></script>
<script src="${contextPath}/js/jquery.validate.min.js"></script>
<script src="${contextPath}/js/messages_zh.js"></script>

<script>
    $.validator.setDefaults({
        submitHandler: function() {
            var loading = layer.load(0);

            var user = $('form').serializeArray();

            var registerResult = null;
            $.ajax({
                async : false, //设置同步
                type : 'POST',
                url : '${contextPath}/user?register',
                data : user,
                dataType : 'json',
                success : function(data) {
                    registerResult = data.result;
                    alert("用户："+data.id+"注册成功");
                },
                error : function(data) {
                    layer.alert("用户注册失败");
                }
            });
            if(registerResult == 'success'){
                layer.close(loading);
                layer.msg('注册成功',{icon:1});
                <%--window.location.href="${cp}/login";--%>
            }
            else if(registerResult == 'nameExist'){
                layer.close(loading);
                layer.msg('这个用户名已经被占用啦！',{icon:2});
            }
            else if(registerResult == 'emailExist'){
                layer.close(loading);
                layer.msg('这个邮箱已经注册啦！',{icon:2});
            }
            else if(registerResult == 'fail'){
                layer.close(loading);
                layer.msg('服务器异常',{icon:2});
            }
        }
    });

    $().ready(function() {
        // 在键盘按下并释放及提交后验证提交表单
        $("#signupForm").validate({
            rules: {
                userName: {
                    required:true,
                    minlength:2,
                    maxlength:10
                },
                nickName: {
                    required: true,
                    minlength: 2
                },
                password: {
                    required: true,
                    minlength: 5,
                    maxlength:20
                },
                confirm_password: {
                    required: true,
                    minlength: 5,
                    maxlength:20,
                    // equalTo: "#password"
                },
                email: {
                    required: true,
                    email: true
                },
                phoneNumber:{
                    required:true,
                    rangelength:[11,11]
                },
                birthday:"required",

                postNumber:"required",

                address:"required"

            },
            messages: {
                username: {
                    required: "请输入用户名",
                    minlength: "用户名最少由两个字母组成",
                    maxlenth: "用户名不得超过10个字符"
                },
                nickName: {
                    required: "请输入昵称",
                    minlength: "昵称不得少于两个字符",
                    maxlenth: "昵称不得超过10个字符"
                },
                password: {
                    required: "请输入密码",
                    minlength: "密码长度不能小于 5 个字母",
                    maxlength: "密码不能大于20个字符"
                },
                confirm_password: {
                    required: "请输入密码",
                    minlength: "密码长度不能小于5个字母",
                    maxlength: "密码不能大于20个字符",
                    // equalTo: "两次密码输入不一致"
                },
                phoneNumber:{
                    required:"请输入手机号码",
                    rangelength:"手机号码格式错误"
                },
                email: "请输入一个正确的邮箱",

                birthday:"请选择出生日期",

                postNumber:"请输入邮政编码",

                address:"请输入售货地址"
            }
        });
    });
</script>
</html>
