<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/12
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>LuckyMall</title>
</head>
<body>
<jsp:include page="../includeView/head.jsp" />
<!-- 中间内容 -->
<div class="container-fluid">
    <div class="row">
        <!-- 控制栏 -->
        <div class="col-sm-3 col-md-2 sidebar sidebar-1">
            <ul class="nav nav-sidebar">
                <li class="list-group-item-diy"><a href="#section1">查看所有用户<span class="sr-only">(current)</span></a></li>
                <li class="list-group-item-diy"><a href="#section2">查看所有商品</a></li>
                <li class="list-group-item-diy"><a href="#section3">添加商品</a></li>
                <li style="position: relative;left: 55px"><a href="${contextPath}/shoppingRecord?shoppingRecordHandle">处理订单</a></li>
            </ul>
        </div>
        <!-- 控制内容 -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="col-md-12">
                <hr />
                <a style="color: dimgrey;font-family: cursive;position: relative;right: 290px;font-size: 33px;" name="section1">用户信息</a>
                <div class="navbar-form navbar-left" style="position: relative;left: 170px">
                    <div class="form-group" style="width: 100px">
                        <input type="text" class="form-control" placeholder="输入用户名或邮箱" id="searchKeyWord2"/>
                    </div>
                    <button class="btn btn-default" style="position: relative;left: 80px" onclick="searchUser();">查询用户</button>
                    <a class="btn btn-default" style="position: relative;left: 80px" href="${contextPath}/user?addUser">添加用户</a>
                </div>
                <hr/>
                <table class="table table-hover center" id="userTable">
                </table>
            </div>

            <div class="col-md-12">
                <hr/>
                <a style="color: dimgrey;font-family: cursive;position: relative;right: 500px;font-size: 33px;" name="section2">商品信息</a>
                <div class="navbar-form navbar-left" style="position: relative;left: 170px">
                    <div class="form-group" style="width: 100px">
                        <input type="text" class="form-control" placeholder="输入商品名" id="searchKeyWord3"/>
                    </div>
                    <button class="btn btn-default" style="position: relative;left: 80px" onclick="adminSearchProduct();">查询商品</button>
                </div>
                <%--<div class="form-group">--%>
                    <div class="col-sm-6 col-md-6" style="width: 300px;position: relative;left: 300px;top: 9px">
                        <select name="productType" class="form-control" id="productType2">
                            <option value="1">潮流女装</option>
                            <option value="2">时尚男装</option>
                            <option value="3">羽绒服</option>
                            <option value="4">衬衫/T恤</option>
                            <option value="5">外套上衣</option>
                            <option value="6">针织毛衫</option>
                            <option value="7">秋外套</option>
                        </select>
                        <button class="btn btn-default" style="position: relative;left: 290px;bottom: 35px;" onclick="adminSearchProductByType();">查询商品</button>
                    </div>
                <%--</div>--%>
                <div class="col-lg-12 col-md-12 col-sm-12" id="productArea"></div>
                <br/>
            </div>

            <div class="col-md-12">
                <hr/>
                <h1 ><a style="color: dimgrey;font-family: cursive;" name="section3">添加商品</a></h1>
                <hr/>
                <div class="col-sm-offset-2 col-md-offest-2">
                    <!-- 表单输入 -->
                    <div  class="form-horizontal">
                        <div class="form-group">
                            <label for="productName" class="col-sm-2 col-md-2 control-label">商品名称</label>
                            <div class="col-sm-6 col-md-6">
                                <input type="text" class="form-control" id="productName" placeholder="外套" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="productDescribe" class="col-sm-2 col-md-2 control-label">商品描述</label>
                            <div class="col-sm-6 col-md-6">
                                <textarea type="text" class="form-control" id="productDescribe" placeholder="balabalabalabala"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="keyWord" class="col-sm-2 col-md-2 control-label">关键词</label>
                            <div class="col-sm-6 col-md-6">
                                <textarea type="text" class="form-control" id="keyWord" placeholder="xxxx;xxxx;xxxx"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="productPrice" class="col-sm-2 col-md-2 control-label">商品价格</label>
                            <div class="col-sm-6 col-md-6">
                                <input type="text" class="form-control" id="productPrice" placeholder="399" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="productCount" class="col-sm-2 col-md-2 control-label">商品数量</label>
                            <div class="col-sm-6 col-md-6">
                                <input type="text" class="form-control" id="productCount" placeholder="100" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="productType" class="col-sm-2 col-md-2 control-label">商品类别</label>
                            <div class="col-sm-6 col-md-6">
                                <select name="productType" class="form-control" id="productType">
                                    <option value="1">潮流女装</option>
                                    <option value="2">时尚男装</option>
                                    <option value="3">羽绒服</option>
                                    <option value="4">衬衫/T恤</option>
                                    <option value="5">外套上衣</option>
                                    <option value="6">针织毛衫</option>
                                    <option value="7">秋外套</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="productImgUpload" class="col-sm-2 col-md-2 control-label" accept="image/jpg">商品图片</label>
                            <div class="col-sm-6 col-md-6">
                                <input name="productImgUpload" type="file"  id="productImgUpload"/>
                                <p class="help-block">上传的图片大小应为280*160大小</p>
                            </div>
                            <%--<button class="btn btn-primary col-sm-2 col-md-2" onclick="fileUpload()">上传图片</button>--%>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-6" id="imgPreSee">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-6">
                                <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="addProduct()">添加商品</button>
                            </div>
                        </div>
                    </div>
                    <br/>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../includeView/foot.jsp" />
</body>
<script type="text/javascript" charset="UTF-8">

    if(judgeIsLogin()){
    var loading = layer.load(2);
    listAllUser();
    listAllProduct();
    layer.close(loading);
    //列出所有用户
    function listAllUser() {
        var userTable = $("#userTable");
        var allUser = getAllUsers();
        userTable.html('<tr>'+
            '<th> 用户ID </th>'+
            '<th> 用户名</th>'+
            '<th> 昵称</th>'+
            '<th> 邮箱</th>'+
            '<th> 修改信息</th>'+
            '<th> 是否删除</th>'+
            '</tr>');
        var html = "";
        for(var i=0;i<allUser.length;i++){
            html += '<tr>'+
                '<td>'+allUser[i].id+'</td>'+
                '<td>'+allUser[i].userName+'</td>'+
                '<td>'+allUser[i].nickName+'</td>'+
                '<td>'+allUser[i].email+'</td>'+
                '<td>'+
                '<button class="btn btn-primary btn-sm" type="submit" style="background-color: darkred;border-color: white;" onclick="updateUser('+allUser[i].id+')">编辑</button>'+
                '</td>'+
                '<td>'+
                '<button class="btn btn-primary btn-sm" type="submit" style="background-color: darkred;border-color: white;" onclick="deleteUser('+allUser[i].id+')">删除</button>'+
                '</td>'+
                '</tr>';
        }
        userTable.html(userTable.html()+html);
    }

    //获取所有的用户
    function getAllUsers() {
        var nothing = {};
        var userResult = "";
        $.ajax({
            async:false,
            type : 'POST',
            url : '${contextPath}/user?getAllUser',
            data : nothing,
            dataType : 'json',
            success : function(result) {
                userResult = result.allUsers;
            },
            error : function(resoult) {
                layer.alert('查询用户出错咯~');
            }
        });
        return userResult;
    }


    function listAllProduct() {
        var productArea = $("#productArea");
        var allProduct = getAllProducts();
        var html="";
        productArea.html('');
        for(var i=0;i<allProduct.length;i++){
            var imgURL = "${contextPath}/img/"+allProduct[i].id+".jpg";
            html+='<div class="col-sm-4 col-md-4 pd-5">'+
                '<div class="boxes">'+
                '<div class="big bigimg">'+
                '<img src="'+imgURL+'" width="300" onclick="alterProduct('+allProduct[i].id+')">'+
                '</div>'+
                '<p class="font-styles center">'+allProduct[i].productName+'</p>'+
                '<p style="position: relative;right: 14px;" class="pull-right">价格：'+allProduct[i].price+'</p>'+
                '<p style="position: relative;left: 14px;" class="pull-left">库存：'+allProduct[i].counts+'</p>'+
                '<div class = "row">'+
                '<button class="btn btn-primary delete-button" style="background-color: darkred;border-color: white;position:relative;left:5px;width: 69px;height: 30px;padding-top: 5px;font-size: 2px;" type="submit" onclick="deleteProduct('+allProduct[i].id+')">删除商品</button>'+
                '</div>'+
                '</div>'+
                '</div>';
        }
        productArea.html(productArea.html()+html);
    }

    function alterProduct(id) {
        var alterId={};
        alterId.id = id;
        var tempProduct = "";
        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/product?setId',
            data : alterId,
            dataType : 'json',
            success : function(result) {
                tempProduct = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        if(tempProduct == "success"){
            window.location.href="${contextPath}/product?alterProductMsg";
        }
    }
    //查询所有商品
    function getAllProducts() {
        var allProducts = null;
        var nothing = {};
        $.ajax({
            async:false,
            type : 'POST',
            url : '${contextPath}/product?getAllProducts',
            data : nothing,
            dataType : 'json',
            success : function(result) {
                allProducts = result.allProducts;
            },
            error : function(resoult) {
                layer.alert('查询商品出错咯~');
            }
        });
        return allProducts;
    }

    //删除用户
    function deleteUser(id) {
        var nothing = "";
        var deleteResult = "";
        $.ajax({
            async : false,
            type : 'DELETE',
            url : '${contextPath}/user/'+id,
            data : nothing,
            dataType : 'json',
            success : function(result) {
                deleteResult = result;
            },
            error : function(result) {
                layer.alert('删除用户错误');
            }
        });
        layer.msg(deleteResult.message,{icon:1,time:2000},function () {
            listAllUser();
        });
    }

    function deleteProduct(id) {
        var nothing = "";
        var deleteResult = "";
        $.ajax({
            async : false,
            type : 'DELETE',
            url : '${contextPath}/product/'+id,
            data : nothing,
            dataType : 'json',
            success : function(result) {
                deleteResult = result;
            },
            error : function(result) {
                layer.alert('删除商品错误');
            }
        });
        layer.msg(deleteResult.message,{icon:1,time:1000},function () {
            listAllProduct();
        });
    }

    function addProduct() {
        var loadings = layer.load(0);
        var product = new FormData();
        product.append('productName',$("#productName").val());
        product.append('description',$("#productDescribe").val());
        product.append('keyWord',$("#keyWord").val());
        product.append('price',$("#productPrice").val());
        product.append('counts',$("#productCount").val());
        product.append('type',$("#productType").val());
        product.append('file',$("#productImgUpload")[0].files[0]);
        $.ajax({
            type : 'POST',
            url : '${contextPath}/product?addProduct',
            data : product,
            dataType : 'json',
            contentType: false, //不设置内容类型
            processData: false, //不处理数据
            success : function(result) {
                layer.msg('添加商品成功', {icon: 1, time: 1000});
                layer.close(loadings);
                listAllProduct();
            },
            error : function(result) {
                layer.alert('添加商品失败');
            }
        });
    }
    }
    // 使用jstl根据session中的currentUser属性判断用户是否已登录，否则跳转至登陆界面
    function judgeIsLogin() {
        var isLogined=false;
        <c:if test="${not empty currentUser and not empty currentUser.id}">
        isLogined=true;
        </c:if>
        if(!isLogined){
            window.location.href="${contextPath}/login";
        }
        return isLogined;
    }
    function updateUser(id) {
        var alterId={};
        alterId.userId = id;
        var tempUser = "";
        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/user?setId',
            data : alterId,
            dataType : 'json',
            success : function(result) {
                tempUser = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        if(tempUser == "success"){
            window.location.href="${contextPath}/user?alterUserMsg";
        }
    }

    function searchUser() {
        var userTable = $("#userTable");
        var user =getUserByName();
        userTable.html('<tr>'+
            '<th> 用户ID </th>'+
            '<th> 用户名</th>'+
            '<th> 昵称</th>'+
            '<th> 邮箱</th>'+
            '<th> 修改信息</th>'+
            '<th> 是否删除</th>'+
            '</tr>');
        var html="";
        html += '<tr>'+
            '<td>'+user.id+'</td>'+
            '<td>'+user.userName+'</td>'+
            '<td>'+user.nickName+'</td>'+
            '<td>'+user.email+'</td>'+
            '<td>'+
            '<button class="btn btn-primary btn-sm" type="submit" style="background-color: darkred;border-color: white;" onclick="updateUser('+user.id+')">编辑</button>'+
            '</td>'+
            '<td>'+
            '<button class="btn btn-primary btn-sm" type="submit" style="background-color: darkred;border-color: white;" onclick="deleteUser('+user.id+')">删除</button>'+
            '</td>'+
            '</tr>';

        userTable.html(userTable.html()+html);
    }

    // 根据id获取指定用户对象
    function getUserByName() {
        var user = {};
        user.userName = $("#searchKeyWord2").val();
        var tempUser ;
        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/user?getUserByName',
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

    function adminSearchProduct() {
        var productArea = $("#productArea");

        var allProduct = getProductsByKeyWord();

        var html="";

        productArea.html('');

        for(var i=0;i<allProduct.length;i++){
            var imgURL = "${contextPath}/img/"+allProduct[i].id+".jpg";
            html+='<div class="col-sm-4 col-md-4 pd-5">'+
                '<div class="boxes">'+
                '<div class="big bigimg">'+
                '<img src="'+imgURL+'" width="300" onclick="alterProduct('+allProduct[i].id+')">'+
                '</div>'+
                '<p class="font-styles center">'+allProduct[i].productName+'</p>'+
                '<p style="position: relative;right: 14px;" class="pull-right">价格：'+allProduct[i].price+'</p>'+
                '<p style="position: relative;left: 14px;" class="pull-left">库存：'+allProduct[i].counts+'</p>'+
                '<div class = "row">'+
                '<button class="btn btn-primary delete-button" style="background-color: darkred;border-color: white;position:relative;left:5px;width: 69px;height: 30px;padding-top: 5px;font-size: 2px;" type="submit" onclick="deleteProduct('+allProduct[i].id+')">删除商品</button>'+
                '</div>'+
                '</div>'+
                '</div>';
        }
        productArea.html(productArea.html()+html);
    }

    function adminSearchProductByType() {
        var productArea = $("#productArea");

        var allProduct = getProductsByType();

        var html="";

        productArea.html('');

        for(var i=0;i<allProduct.length;i++){
            var imgURL = "${contextPath}/img/"+allProduct[i].id+".jpg";
            html+='<div class="col-sm-4 col-md-4 pd-5">'+
                '<div class="boxes">'+
                '<div class="big bigimg">'+
                '<img src="'+imgURL+'" width="300" onclick="alterProduct('+allProduct[i].id+')">'+
                '</div>'+
                '<p class="font-styles center">'+allProduct[i].productName+'</p>'+
                '<p style="position: relative;right: 14px;" class="pull-right">价格：'+allProduct[i].price+'</p>'+
                '<p style="position: relative;left: 14px;" class="pull-left">库存：'+allProduct[i].counts+'</p>'+
                '<div class = "row">'+
                '<button class="btn btn-primary delete-button" style="background-color: darkred;border-color: white;position:relative;left:5px;width: 69px;height: 30px;padding-top: 5px;font-size: 2px;" type="submit" onclick="deleteProduct('+allProduct[i].id+')">删除商品</button>'+
                '</div>'+
                '</div>'+
                '</div>';
        }
        productArea.html(productArea.html()+html);
    }

    //根据类型查询商品
    function getProductsByType() {
        var products = null;
        var nothing = {};
        nothing.type = $("#productType2").val();
        $.ajax({
            async:false,
            type : 'POST',
            url : '${contextPath}/product?searchProductByType',
            data : nothing,
            dataType : 'json',
            success : function(result) {
                products = result.result;
            },
            error : function(resoult) {
                layer.alert('查询商品出错咯~');
            }
        });
        return products;
    }

    //查询所有商品
    function getProductsByKeyWord() {
        var products = null;
        var nothing = {};
        nothing.searchKeyWord = $("#searchKeyWord3").val();
        $.ajax({
            async:false,
            type : 'POST',
            url : '${contextPath}/product?searchProductByKeyWord',
            data : nothing,
            dataType : 'json',
            success : function(result) {
                products = result.result;
            },
            error : function(resoult) {
                layer.alert('查询商品出错咯~');
            }
        });
        return products;
    }
</script>
</html>
