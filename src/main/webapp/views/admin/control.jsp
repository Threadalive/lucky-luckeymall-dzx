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
                <li class="list-group-item-diy"><a href="javascript:hideAndShow(1)">查看所有用户<span class="sr-only">(current)</span></a></li>
                <li class="list-group-item-diy"><a href="javascript:hideAndShow(2)">查看所有商品</a></li>
                <li class="list-group-item-diy"><a href="javascript:hideAndShow(3)">用户积分明细</a></li>
                <li class="list-group-item-diy"><a href="javascript:hideAndShow(4)">添加商品</a></li>
                <li style="position: relative;left: 55px"><a href="${contextPath}/adminControl?shoppingRecordHandle">处理订单</a></li>
            </ul>
        </div>
        <%--面板模块--%>
        <div style="position: relative;top:10px">
        <div class="panel panel-warning" style="width: 215px;position: relative;left: 256px;top: 40px;border-color: aliceblue;">
            <div class="panel-body" style="border-block-end: inherit;">
                <img src="${contextPath}/img/mark/user.png" width="110" />
                <nobr id="userCount" style="font-size: 40px;position: relative;left: 12px;top: 7px;font-family: monospace;"></nobr>
            </div>
        </div>
        <div class="panel panel-warning" style="width: 215px;position: relative;left: 500px;border-color: aliceblue;bottom: 87px;">
            <div class="panel-body" style="border-block-end: inherit;">
                <img src="${contextPath}/img/mark/product.png" width="110" />
                <nobr id="productCounts" style="font-size: 40px;position: relative;left: 12px;top: 7px;font-family: monospace;"></nobr>
            </div>
        </div>
        <div class="panel panel-warning" style="width: 215px;position: relative;left: 745px;border-color: aliceblue;bottom: 216px;">
            <div class="panel-body" style="border-block-end: inherit;">
                <img src="${contextPath}/img/mark/order.png" width="110" />
                <nobr id="orderCount" style="font-size: 40px;position: relative;left: 12px;top: 7px;font-family: monospace;"></nobr>
            </div>
        </div>
        <div class="panel panel-warning" style="width: 215px;position: relative;left: 987px;border-color: aliceblue;bottom: 343px;">
            <div class="panel-body" style="border-block-end: inherit;">
                <img src="${contextPath}/img/mark/other.png" width="110" />
                <nobr id="other" style="font-size: 30px;position: relative;left: 12px;top: 7px;">...</nobr>
            </div>
        </div>
        </div>
        <!-- 控制内容 -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="position: relative;bottom: 350px;">
            <div id="userMenu" hidden>
            <div class="col-md-12">
                <hr />
                <a style="color: dimgrey;font-family: cursive;position: relative;right: 290px;font-size: 33px;"name="section1">用户信息</a>
                <div class="navbar-form navbar-left" style="position: relative;left: 170px">
                    <div class="form-group" style="width: 100px">
                        <input type="text" class="form-control" placeholder="输入用户名或邮箱" id="searchKeyWord2"/>
                    </div>
                    <button class="btn btn-default" style="position:relative;left: 80px" onclick="searchUser()">查询用户</button>
                    <a class="btn btn-default" style="position: relative;left: 80px" href="${contextPath}/user?addUser">添加用户</a>
                </div>
                <hr/>
                <table class="table table-hover center" id="userTable">
                </table>
            </div>
            <tr>
                <div id="userButton" style="position: relative;left: 38%;top: 7px;height: 5%;">
                    <td colspan="6" align="center"><nobr style="position: relative;left: 3%;font-family: cursive;bottom: 6px;">共${page.totalRecords}条记录 共${page.totalPages}页 当前第<span id="currentPage"></span>页</nobr><br>

                    <a href="javascript:listAllUser(1)"><input type="button" class="btn btn-default btn-sm" name="fristPage" value="首页" /></a>

                            <a href="javascript:listAllUser(2)"><input type="button" class="btn btn-default btn-sm" name="previousPage" value="上一页" /></a>

                            <a href="javascript:listAllUser(3)"><input type="button" class="btn btn-default btn-sm"  name="nextPage" value="下一页" /></a>
                    <a href="javascript:listAllUser(4)"><input type="button" class="btn btn-default btn-sm"  name="lastPage" value="尾页" /></a>
                </td>
                </div>
            </tr>
            </div>
            <div id="productMenu">
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
                <tr>
                    <div id="productButton" style="position: relative;left: 38%;top: 26px;height: 5%;">
                        <td colspan="6" align="center"><nobr style="position: relative;left: 3%;font-family: cursive;bottom: 6px;">共${page1.totalRecords}条记录 共${page1.totalPages}页 当前第<span id="currentPage1"></span>页</nobr><br>

                            <a href="javascript:listAllProduct(1)"><input type="button" class="btn btn-default btn-sm" name="fristPage1" value="首页" /></a>

                            <a href="javascript:listAllProduct(2)"><input type="button" class="btn btn-default btn-sm" name="previousPage1" value="上一页" /></a>

                            <a href="javascript:listAllProduct(3)"><input type="button" class="btn btn-default btn-sm"  name="nextPage1" value="下一页" /></a>
                            <a href="javascript:listAllProduct(4)"><input type="button" class="btn btn-default btn-sm"  name="lastPage1" value="尾页" /></a>
                        </td>
                    </div>
                </tr>
            </div>

            <div id="scoreDetailMenu" hidden>
            <div class="col-md-12">
                <hr />
                <a style="color: dimgrey;font-family: cursive;position: relative;right: 290px;font-size: 33px;" name="section4">用户积分明细</a>
                <div class="navbar-form navbar-left" style="position: relative;left: 170px">
                    <div class="form-group" style="width: 100px;position: relative;left: 50px">
                        <input type="text" class="form-control" placeholder="输入用户名或邮箱" id="searchKeyWord4"/>
                    </div>
                    <button class="btn btn-default" style="position: relative;left: 130px" onclick="searchScoreDetailByUser();">查询用户积分变更记录</button>
                </div>
                <hr/>
                <table class="table table-hover center" id="scoreDetailTable">
                </table>
            </div>
                <tr>
                    <div id="scoreButton" style="position: relative;left: 38%;top: 26px;height: 5%;">
                        <td colspan="6" align="center"><nobr style="position: relative;left: 3%;font-family: cursive;bottom: 6px;">共${page2.totalRecords}条记录 共${page2.totalPages}页 当前第<span id="currentPage2"></span>页</nobr><br>

                            <a href="javascript:listAllScoreDetail(1)"><input type="button" class="btn btn-default btn-sm" name="fristPage2" value="首页" /></a>

                            <a href="javascript:listAllScoreDetail(2)"><input type="button" class="btn btn-default btn-sm" name="previousPage2" value="上一页" /></a>

                            <a href="javascript:listAllScoreDetail(3)"><input type="button" class="btn btn-default btn-sm"  name="nextPage2" value="下一页" /></a>
                            <a href="javascript:listAllScoreDetail(4)"><input type="button" class="btn btn-default btn-sm"  name="lastPage2" value="尾页" /></a>
                        </td>
                    </div>
                </tr>
            </div>

            <div id="addProductMenu" hidden>
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
                                <input name="productImgUpload" type="file"  id="productImgUpload" accept="image/*"/>
                                <p class="help-block">上传的图片大小应为280*160大小</p>
                            </div>
                            <%--<button class="btn btn-primary col-sm-2 col-md-2" onclick="fileUpload()">上传图片</button>--%>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-6">
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
</div>
<jsp:include page="../includeView/foot.jsp" />
</body>
<script type="text/javascript" charset="UTF-8">
    var loading = layer.load(2);
    var paramPageNo=1;
    var paramPageNo1=1;
    var paramPageNo2=1;
    listAllUser(paramPageNo);
    listAllProduct(paramPageNo1);
    listAllScoreDetail(paramPageNo2);
    getUserCount();
    getProductCount();
    getOrderCount();
    layer.close(loading);
    //列出所有用户
    function listAllUser(flag) {
        $("#userButton").removeAttr("hidden");
        var userTable = $("#userTable");
        var allUser = getAllUsers(flag);
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

        function listAllScoreDetail(flag) {
            $("#scoreButton").removeAttr('hidden');
            var scoreDetailTable = $("#scoreDetailTable");
            var allScoreDetail = getAllScoreDetail(flag);
            scoreDetailTable.html('<tr>'+
                '<th> 用户ID </th>'+
                '<th> 当前总积分</th>'+
                '<th> 获得积分</th>'+
                '<th> 支出积分</th>'+
                '<th> 交易时间</th>'+
                '<th> 对应商品项</th>'+
                '</tr>');
            var html = "";
            for(var i=0;i<allScoreDetail.length;i++){
                var time = new Date(allScoreDetail[i].createTime);
                html += '<tr>'+
                    '<td >'+allScoreDetail[i].userId+'</td>'+
                    '<td>'+allScoreDetail[i].score+'</td>'+
                    '<td>'+allScoreDetail[i].income+'</td>'+
                    '<td>'+allScoreDetail[i].expend+'</td>'+
                    '<td>'+time.getFullYear()+'-'+time.getMonth()+'-'+time.getDay()+'  '+time.getHours()+':'+time.getMinutes()+'</td>'+
                    '<td>'+allScoreDetail[i].productName+'</td>'+
                    '</td>'+
                    '</tr>';
            }
            scoreDetailTable.html(scoreDetailTable.html()+html);
        }

        function getAllScoreDetail(flag) {
            var scoreDetailResult = "";
            $('input[name="previousPage2"]').removeAttr('disabled');
            $('input[name="nextPage2"]').removeAttr('disabled');
            $('input[name="lastPage2"]').removeAttr('disabled');
            $('input[name="firstPage2"]').removeAttr('disabled');
            if(flag == 1){
                paramPageNo2=1;
            }else if(flag == 2){
                paramPageNo2--;
                if(paramPageNo2<1){
                    paramPageNo2=1;
                }
            }else if(flag == 3){
                paramPageNo2++;

            }else if(flag == 4){
                paramPageNo2 = ${page2.totalPages};
            }
            if(paramPageNo2 >= ${page2.totalPages}){
                $('input[name="nextPage2"]').attr('disabled','disabled');
                $('input[name="lastPage2"]').attr('disable','disable');
            }
            if(paramPageNo2 <= 1){
                $('input[name="previousPage2"]').attr('disabled','disabled');
                $('input[name="firstPage2"]').attr('disable','disable');
            }
            $("#currentPage2").text(paramPageNo2);

            var params={pageNo:paramPageNo2};
            $.ajax({
                async:false,
                type : 'POST',
                url : '${contextPath}/score?getAllScoreDetail',
                data : params,
                dataType : 'json',
                success : function(result) {
                    scoreDetailResult = result.list;
                },
                error : function(result) {
                    layer.alert('查询用户出错咯~');
                }
            });
            return scoreDetailResult;
        }
    //获取所有的用户
    function getAllUsers(flag) {
        var userResult = "";
        $('input[name="previousPage"]').removeAttr('disabled');
        $('input[name="nextPage"]').removeAttr('disabled');
        $('input[name="lastPage"]').removeAttr('disabled');
        $('input[name="firstPage"]').removeAttr('disabled');
        if(flag == 1){
            paramPageNo=1;
        }else if(flag == 2){
            paramPageNo--;
            if(paramPageNo<1){
                paramPageNo=1;
            }
        }else if(flag == 3){
            paramPageNo++;

        }else if(flag == 4){
            paramPageNo = ${page.totalPages};
        }
        if(paramPageNo >= ${page.totalPages}){
            $('input[name="nextPage"]').attr('disabled','disabled');
            $('input[name="lastPage"]').attr('disable','disable');
        }
        if(paramPageNo <= 1){
            $('input[name="previousPage"]').attr('disabled','disabled');
            $('input[name="firstPage"]').attr('disable','disable');
        }
        $("#currentPage").text(paramPageNo);

        var params={pageNo:paramPageNo};
        $.ajax({
            async:false,
            type : 'POST',
            url : '${contextPath}/user?getAllUser',
            data : params,
            dataType : 'json',
            success : function(result) {
                userResult = result.list;
            },
            error : function(resoult) {
                layer.alert('查询用户出错咯~');
            }
        });
        return userResult;
    }

    function listAllProduct(flag) {
        $("#productButton").removeAttr('hidden');
        var productArea = $("#productArea");
        var allProduct = getAllProducts(flag);
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

    //查询所有商品
    function getAllProducts(flag) {
        var productResult = "";
        $('input[name="previousPage1"]').removeAttr('disabled');
        $('input[name="nextPage1"]').removeAttr('disabled');
        $('input[name="lastPage1"]').removeAttr('disabled');
        $('input[name="firstPage1"]').removeAttr('disabled');

        if(flag == 1){
            paramPageNo1=1;
        }else if(flag == 2){
            paramPageNo1--;
            if(paramPageNo1<1){
                paramPageNo1=1;
            }
        }else if(flag == 3){
            paramPageNo1++;
        }else if(flag == 4){
            paramPageNo1 = ${page1.totalPages};
        }
        if(paramPageNo1 >= ${page1.totalPages}){
            $('input[name="nextPage1"]').attr('disabled','disabled');
            $('input[name="lastPage1"]').attr('disable','disable');
        }
        if(paramPageNo1 <= 1){
            $('input[name="previousPage1"]').attr('disabled','disabled');
            $('input[name="firstPage1"]').attr('disabled','disabled');
        }
        $("#currentPage1").text(paramPageNo1);

        var params={pageNo:paramPageNo1};
        $.ajax({
            async:false,
            type : 'POST',
            url : '${contextPath}/product?getAllProducts',
            data : params,
            dataType : 'json',
            success : function(result) {
                productResult = result.list;
            },
            error : function(resoult) {
                layer.alert('查询用户出错咯~');
            }
        });
        return productResult;
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
            listAllUser(1);
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
            listAllProduct(paramPageNo1);
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
                layer.msg('添加商品成功', {icon: 1, time: 2000});
                listAllProduct(paramPageNo1)
            },
            error : function(result) {
                layer.alert('添加商品失败');
            }
        });

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
        if(user!="" && user!= "userNoExist")
        {
            userTable.html('<tr>' +
                '<th> 用户ID </th>' +
                '<th> 用户名</th>' +
                '<th> 昵称</th>' +
                '<th> 邮箱</th>' +
                '<th> 修改信息</th>' +
                '<th> 是否删除</th>' +
                '</tr>');
            var html = "";
                html += '<tr>' +
                    '<td>' + user.id + '</td>' +
                    '<td>' + user.userName + '</td>' +
                    '<td>' + user.nickName + '</td>' +
                    '<td>' + user.email + '</td>' +
                    '<td>' +
                    '<button class="btn btn-primary btn-sm" type="submit" style="background-color: darkred;border-color: white;" onclick="updateUser(' + user.id + ')">编辑</button>' +
                    '</td>' +
                    '<td>' +
                    '<button class="btn btn-primary btn-sm" type="submit" style="background-color: darkred;border-color: white;" onclick="deleteUser(' + user.id + ')">删除</button>' +
                    '</td>' +
                    '</tr>';
            userTable.html(userTable.html() + html);
            $("#userButton").attr("hidden","hidden");
        }else{
        }
    }

    // 根据id获取指定用户对象
    function getUserByName() {
        var user = {};
        user.userName = $("#searchKeyWord2").val();

        var tempUser = "";
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
        if(tempUser == "listAll"){
            listAllUser(1);
            return "";
        }else if(tempUser == "userNoExist"){
            layer.msg("用户不存在哦",{icon:1,time:2000});
            return tempUser;
        }else {
            return tempUser;
        }
    }

    function adminSearchProduct() {
        var productArea = $("#productArea");

        var allProduct = getProductsByKeyWord();
        if(allProduct == ""){
            listAllProduct(1);
        }else {
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
        $("#productButton").attr('hidden','hidden');
        }
    }

    function searchScoreDetailByUser() {
        var scoreDetailTable = $("#scoreDetailTable");
        var allScoreDetail =getScoreDetailByUser();
        if(allScoreDetail!="") {
            scoreDetailTable.html('<tr>' +
                '<th> 用户ID </th>' +
                '<th> 当前总积分</th>' +
                '<th> 获得积分</th>' +
                '<th> 支出积分</th>' +
                '<th> 交易时间</th>' +
                '<th> 对应商品项</th>' +
                '</tr>');
            var html = "";
            for (var i = 0; i < allScoreDetail.length; i++) {
                var time = new Date(allScoreDetail[i].createTime);
                html += '<tr>' +
                    '<td >' + allScoreDetail[i].userId + '</td>' +
                    '<td>' + allScoreDetail[i].score + '</td>' +
                    '<td>' + allScoreDetail[i].income + '</td>' +
                    '<td>' + allScoreDetail[i].expend + '</td>' +
                    '<td>' + time.getFullYear() + '-' + time.getMonth() + '-' + time.getDay() + '  ' + time.getHours() + ':' + time.getMinutes() + '</td>' +
                    '<td>' + allScoreDetail[i].productName + '</td>' +
                    '</td>' +
                    '</tr>';
            }
            scoreDetailTable.html(scoreDetailTable.html() + html);
            $("#scoreButton").attr('hidden','hidden');
        }else {
        }
    }

    function getScoreDetailByUser() {
        var scoreDetail = {};
        var keyWord = $("#searchKeyWord4").val();
            scoreDetail.userName = keyWord;
        var tempScoreDetail ="";

        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/score?getScoreDetailByUser',
            data : scoreDetail,
            dataType : 'json',
            success : function(result) {
                tempScoreDetail = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        if(tempScoreDetail == "listAll"){
            listAllScoreDetail(1);
            return "";
        }else if(tempScoreDetail == "userNoExist"){
            layer.msg("用户不存在哦",{icon:1,time:2000});
            return {};
        }else {
            return tempScoreDetail;
        }
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
        $("#productButton").attr('hidden','hidden');
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
        var keyWord = $("#searchKeyWord3").val()
        if(keyWord == ""){
            return "";
        }else {
        nothing.searchKeyWord = keyWord;
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
    }

    //获取用户总数
    function getUserCount() {
        var user = {};
        var userCount = $("#userCount");
        var count = "";
        $.ajax({
            async:false,
            type : 'POST',
            url : '${contextPath}/user?getUserCount',
            data : user,
            dataType : 'json',
            success : function(result) {
                count = result.result;
            },
            error : function(resoult) {
                layer.alert('查询出错咯~');
            }
    });
        userCount.html(count);
    }

    //获取商品总数
    function getProductCount() {
        var product = {};
        var productCount = $("#productCounts");
        var count = "";
        $.ajax({
            async:false,
            type : 'POST',
            url : '${contextPath}/product?getProductCount',
            data : product,
            dataType : 'json',
            success : function(result) {
                count = result.result;
            },
            error : function(resoult) {
                layer.alert('查询出错咯~');
            }
        });
        productCount.html(count);
    }

    //获取订单总数
    function getOrderCount() {
        var order = {};
        var orderCount = $("#orderCount");
        var count = "";
        $.ajax({
            async:false,
            type : 'POST',
            url : '${contextPath}/shoppingRecord?getOrderCount',
            data : order,
            dataType : 'json',
            success : function(result) {
                count = result.result;
            },
            error : function(resoult) {
                layer.alert('查询出错咯~');
            }
        });
        orderCount.html(count);
    }
    function hideAndShow(flag) {
        if(flag == 1) {
            $("#userMenu").removeAttr("hidden");
            $("#productMenu").attr("hidden","hidden");
            $("#scoreDetailMenu").attr("hidden","hidden");
            $("#addProductMenu").attr("hidden","hidden");
        }else if(flag == 2){
            $("#productMenu").removeAttr("hidden");
            $("#userMenu").attr("hidden","hidden");
            $("#scoreDetailMenu").attr("hidden","hidden");
            $("#addProductMenu").attr("hidden","hidden");
        }else if(flag == 3){
            $("#scoreDetailMenu").removeAttr("hidden");
            $("#userMenu").attr("hidden","hidden");
            $("#productMenu").attr("hidden","hidden");
            $("#addProductMenu").attr("hidden","hidden");
        }else if(flag == 4){
            $("#addProductMenu").removeAttr("hidden");
            $("#userMenu").attr("hidden","hidden");
            $("#productMenu").attr("hidden","hidden");
            $("#scoreDetailMenu").attr("hidden","hidden");
        }
    }

</script>
</html>
