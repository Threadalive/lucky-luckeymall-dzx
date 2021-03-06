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
<jsp:include page="../includeView/head.jsp" />
<body style="position: relative;bottom: 50px" >
    <div style="position: relative;bottom: 10px">
    </div>
    <form id="updateForm" onsubmit="return false" >
        <div class="container-fluid">
            <h1 class="title center" style="color: silver;font-family: cursive;position: relative;left:200px">用户信息修改</h1>
            <br/>
            <div class="col-sm-offset-2 col-md-offest-2">
                <!-- 表单输入 -->
                <div  class="form-horizontal" style="width: 600px;position: relative;left: 180px">
                    <div class="form-group">
                        <label for="userName" class="col-sm-2 col-md-2 control-label">用户名</label>
                        <div class="col-sm-6 col-md-6">
                            <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入用户名"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 col-md-2 control-label">邮箱</label>
                        <div class="col-sm-6 col-md-6">
                            <input type="email" class="form-control" id="email" name="email" placeholder="请输入正确的邮箱格式"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nickName" class="col-sm-2 col-md-2 control-label">昵称</label>
                        <div class="col-sm-6 col-md-6">
                            <input type="text" class="form-control" id="nickName" name="nickName" placeholder="请输入昵称"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 col-md-2 control-label">密码</label>
                        <div class="col-sm-6 col-md-6">
                            <input type="password" class="form-control" id="password" name="password" placeholder="禁止输入非法字符"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="confirm_password" class="col-sm-2 col-md-2 control-label">确认密码</label>
                        <div class="col-sm-6 col-md-6">
                            <input type="password" class="form-control" id="confirm_password" name="confirm_password" placeholder="禁止输入非法字符"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="phoneNumber" class="col-sm-2 col-md-2 control-label">手机号码</label>
                        <div class="col-sm-6 col-md-6">
                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="请输入手机号"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="man" class="col-sm-2 col-md-2 control-label">性别</label>
                        <div class="col-sm-6 col-md-6">
                            <label class="radio-inline">
                                <input type="radio" id="man" name="sex" value="1"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="woman" name="sex" value="2"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="birthday" class="col-sm-2 col-md-2 control-label">出生日期</label>
                        <div class="col-sm-6 col-md-6">
                            <input type="date" class="form-control" id="birthday" name="birthday" placeholder="请输入出生日期"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="postNumber" class="col-sm-2 col-md-2 control-label">邮政编码</label>
                        <div class="col-sm-6 col-md-6">
                            <input type="text" class="form-control" id="postNumber" name="postNumber" placeholder="请输入邮政编码"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-sm-2 col-md-2 control-label">地址</label>
                        <div class="col-sm-6 col-md-6">
                            <input type="text" class="form-control" id="address" name="address" placeholder="请输入寄送地址" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-6">
                            <button class="btn btn-lg btn-primary btn-block" type="submit">确认修改</button>
                        </div>
                    </div>
                </div>
                <br/>
            </div>
        </div>
    </form>
<script src="${contextPath}/js/jquery.validate.min.js"></script>
<script src="${contextPath}/js/messages_zh.js"></script>
    <jsp:include page="../includeView/foot.jsp" />
</body>
<div style="position: relative;top: 40px">
</div>
<script>
    initData();
    function initData() {
        var userId = ${sessionScope.alterUser};
        var user = getUserById(userId);
        var userDetail = getUserDetailById(userId);

        $("#userName").val(user.userName);
        $("#email").val(user.email);
        $("#nickName").val(user.nickName);
        $("#password").val(userDetail.password);
        $("#confirm_password").val(userDetail.password);
        $("#phoneNumber").val(userDetail.phoneNumber);
        $("#birthday").val(userDetail.birthday);
        $("#postNumber").val(userDetail.postNumber);
        $("#address").val(userDetail.address);
        if(userDetail.sex == 0)
            $("#man").checked = true;
        else {
            $("#woman").checked = true;
        }
    }

    $.validator.setDefaults({
        submitHandler: function () {
            var loading = layer.load(0);
            var userId = ${sessionScope.alterUser};
            var user1 = getUserById(userId);
            var user = $('form').serializeArray();
            user.push({name: "oldUserName", value:user1.userName });
            user.push({name: "id", value: userId});
            var updateResult = null;
            $.ajax({
                async: false, //设置同步
                type: 'POST',
                url: '${contextPath}/user?updateUser',
                data: user,
                dataType: 'json',
                success: function (data) {
                    updateResult = data.result;
                },
                error: function (data) {
                    layer.alert("用户信息更新失败",{icon:3});
                }
            });
            if (updateResult == 'success') {
                layer.msg('更新成功！',{icon:1},function(){
                    window.location.href="${contextPath}/user?home";
                });
            }
            else if (updateResult == 'fail') {
                layer.close(loading);
                layer.msg('用户不存在', {icon: 2});
            }
        }
    });



    $(function () {
        // 在键盘按下并释放及提交后验证提交表单
        $("#updateForm").validate({

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

    // 根据id获取指定用户对象
    function getUserById(id) {
        var user = {};
        user.id = id;
        var tempUser ;
        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/user?getUserById',
            data : user,
            dataType : 'json',
            success : function(result) {
                tempUser = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        return tempUser;
    }

    function getUserDetailById(id) {
        var userDetailResult = "";
        var user = {};
        user.id = id;
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${contextPath}/user?getUserDetailById',
            data : user,
            dataType : 'json',
            success : function(result) {
                userDetailResult = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        return userDetailResult;
    }
</script>
<style>
    .error{
        color:red;
        font-weight: bold;
    }
</style>
</html>
