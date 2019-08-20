<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/7
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>订单记录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%--导入导航栏部分--%>
<jsp:include page="../includeView/head.jsp" />

<div class="container-fluid bigHead">
    <div class="row">
        <div class="col-sm-10  col-md-10 col-sm-offset-1 col-md-offset-1">
            <div class="jumbotron" style="background: url('${contextPath}/bgimg/login_img03.png');background-position-x: center;height: 300px;">
                <div style="position: relative;top: 80px;">
                <h1 style="font-family: fantasy;color: darkcyan;">LuckyMall</h1>
                <p style="font-family: cursive;color: white">您的购买清单</p>
                </div>
            </div>
        </div>
        <div class="col-sm-10  col-md-10 col-sm-offset-1 col-md-offset-1">
            <div class="row">
                <ul class="nav nav-tabs list-group-diy" role="tablist">
                    <li role="presentation" class="active list-group-item-diy"><a href="#unHandle" aria-controls="unHandle" role="tab" data-toggle="tab">待发货订单&nbsp;<span class="badge" id="unHandleCount">0</span></a></li>
                    <li role="presentation" class="list-group-item-diy"><a href="#transport" aria-controls="transport" role="tab" data-toggle="tab">运输中订单&nbsp;<span class="badge" id="transportCount">0</span></a></li>
                    <li role="presentation" class="list-group-item-diy"><a href="#receive" aria-controls="receive" role="tab" data-toggle="tab">已收货订单&nbsp;<span class="badge" id="receiveCount">0</span></a></li>
                    <li role="presentation" class="list-group-item-diy"><a href="#all" aria-controls="all" role="tab" data-toggle="tab">全部订单&nbsp;<span class="badge" id="allCount">0</span></a></li>
                </ul>

                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="unHandle">
                        <table class="table table-hover center" id="unHandleTable" style="margin-top: 10px;position: relative;right: 130px">
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="transport">
                        <table class="table table-hover center" id="transportTable" style="margin-top: 10px;">
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="receive">
                        <table class="table table-hover center" id="receiveTable" style="margin-top: 10px;">
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="all">
                        <table class="table table-hover center"  id="allTable" style="margin-top: 10px;position: relative;right: 100px">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<%--页尾--%>
<jsp:include page="../includeView/foot.jsp" />

<script type="text/javascript">
    var loading = layer.load(0);

    updateShoppingRecords();

    function updateShoppingRecords() {
        var orderArray = new Array;
        orderArray[0] = "未发货";
        orderArray[1] = "配送中";
        orderArray[2] = "已收货";

        var unHandleTable = $("#unHandleTable");
        var transportTable = $("#transportTable");
        var receiveTable = $("#receiveTable");
        var allTable = $("#allTable");

        var unHandleCount = $("#unHandleCount");
        var transportCount = $("#transportCount");
        var receiveCount = $("#receiveCount");
        var allCount = $("#allCount");

        var unHandleCounts = parseInt(unHandleCount.html());
        var transportCounts = parseInt(transportCount.html());
        var receiveCounts = parseInt(receiveCount.html());
        var allCounts = parseInt(allCount.html());

        // 获取所有订单
        var allShoppingRecords = getShoppingRecords();


        unHandleTable.html("");
        transportTable.html("");
        receiveTable.html("");
        allTable.html("");

        // 未处理的订单块
        var unHandleHTML = '<tr>'+
            '<th style="font-family: cursive;font-size: larger;">商品名称</th>'+
            '<th style="font-family: cursive;font-size: larger;">购买数量</th>'+
            '<th style="font-family: cursive;font-size: larger;">付款金额</th>'+
            '<th style="font-family: cursive;font-size: larger;">订单状态</th>'+
            '</tr>';

        // 运输中的订单块
        var transportHTML = '<tr>'+
            '<th style="font-family: cursive;font-size: larger;">商品名称</th>'+
            '<th style="font-family: cursive;font-size: larger;">购买数量</th>'+
            '<th style="font-family: cursive;font-size: larger;">付款金额</th>'+
            '<th style="font-family: cursive;font-size: larger;">送货地址</th>'+
            '<th style="font-family: cursive;font-size: larger;">联系电话</th>'+
            '<th style="font-family: cursive;font-size: larger;">订单状态</th>'+
            '<th style="font-family: cursive;font-size: larger;">确认收货</th>'+
            '</tr>';

        // 已收货的订单块
        var receiveHTML = '<tr>'+
            '<th style="font-family: cursive;font-size: larger;">商品名称</th>'+
            '<th style="font-family: cursive;font-size: larger;">购买数量</th>'+
            '<th style="font-family: cursive;font-size: larger;">付款金额</th>'+
            '<th style="font-family: cursive;font-size: larger;">订单状态</th>'+
            '<th style="font-family: cursive;font-size: larger;">评价</th>'+
            '</tr>';

        // 所有订单
        var allHTML = '<tr>'+
            '<th style="font-family: cursive;font-size: larger;">商品名称</th>'+
            '<th style="font-family: cursive;font-size: larger;">购买数量</th>'+
            '<th style="font-family: cursive;font-size: larger;">付款金额</th>'+
            '<th style="font-family: cursive;font-size: larger;">订单状态</th>'+
            '</tr>';

        // 内容暂存变量
        var unHandleHTMLTemp = "";
        var transportHTMLTemp = "";
        var receiveHTMLTemp = "";
        var allHTMLTemp = "";

        // 遍历订单设置内容
        for(var i=0;i<allShoppingRecords.length;i++){
            // 通过订单记录中的商品id获取商品对象
            var product = getProductById(allShoppingRecords[i].productId);
            allHTMLTemp += '<tr style="height: 38px;">'+
                '<td style="font-family: cursive;font-size: larger;">'+product.productName+'</td>'+
                '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].counts+'</td>'+
                '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].productPrice+'</td>'+
                '<td style="font-family: cursive;font-size: larger;">'+orderArray[allShoppingRecords[i].orderStatus]+'</td>'+
                '</tr>';
            allCounts++;
            // 当订单状态为0，即未发货，设置未发货块的html内容
            if(allShoppingRecords[i].orderStatus == 0){
                unHandleHTMLTemp+= '<tr style="height: 38px;">'+
                    '<td style="font-family: cursive;font-size: larger;">'+product.productName+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].counts+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].productPrice+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+orderArray[allShoppingRecords[i].orderStatus]+'</td>'+
                    '</tr>';
                unHandleCounts++;
            }
            // 当订单状态为1，即配送中，设置配送中块的html内容
            else if(allShoppingRecords[i].orderStatus == 1){
                // 根据用户id获取用户收货地址
                var address = getUserAddress(allShoppingRecords[i].userId);
                // 根据用户id获取用户电话号码
                var phoneNumber = getUserPhoneNumber(allShoppingRecords[i].userId);
                // 设置配送块的html代码，点击确认收货后调用receiveProducts函数设置订单状态
                transportHTMLTemp+= '<tr style="height: 38px;">'+
                    '<td style="font-family: cursive;font-size: larger;">'+product.productName+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].counts+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].productPrice+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+address+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+phoneNumber+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+orderArray[allShoppingRecords[i].orderStatus]+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+
                    '<button class="btn btn-primary btn-sm" onclick="receiveProducts('+allShoppingRecords[i].userId+','+allShoppingRecords[i].productId+',\''+allShoppingRecords[i].createTime+'\')" style="position: relative;\n' +
                    '    top: -2px;background-color: rosybrown;border-color: white;">确认收货</button>'+
                    '</td>'+
                    '</tr>';
                transportCounts++;
            }
            // 设置已收货块内容,点击评价调用函数进入评价页面
            else if(allShoppingRecords[i].orderStatus == 2){
                receiveHTMLTemp += '<tr style="height: 38px;">'+
                    '<td style="font-family: cursive;font-size: larger;">'+product.productName+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].counts+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].productPrice+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+orderArray[allShoppingRecords[i].orderStatus]+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+
                    '<button class="btn btn-primary btn-sm" style="background-color: rosybrown;border-color: white;" onclick="productDetail('+allShoppingRecords[i].productId+')">评价</button>'+
                    '</td>'+
                    '</tr>';
                receiveCounts++;
            }
        }

        //若未发货订单获取结果为空
        if(unHandleHTMLTemp == ""){
            unHandleHTML='<div class="row">'+
                '<div class="col-sm-3 col-md-3 col-lg-3"></div> '+
                '<div class="col-sm-6 col-md-6 col-lg-6">'+
                '<h2 style="font-family: cursive;">没有相关订单</h2>'+
                '</div>'+
                '</div>';
        }
        // 设置内容块
        else
            unHandleHTML+=unHandleHTMLTemp;

        if(transportHTMLTemp == ""){
            transportHTML = '<div class="row">'+
                '<div class="col-sm-3 col-md-3 col-lg-3"></div> '+
                '<div class="col-sm-6 col-md-6 col-lg-6">'+
                '<h2 style="font-family: cursive;">没有相关订单</h2>'+
                '</div>'+
                '</div>';
        }
        else
            transportHTML+=transportHTMLTemp;
        if(receiveHTMLTemp == ""){
            receiveHTML = '<div class="row">'+
                '<div class="col-sm-3 col-md-3 col-lg-3"></div> '+
                '<div class="col-sm-6 col-md-6 col-lg-6">'+
                '<h2 style="font-family: cursive;">没有相关订单</h2>'+
                '</div>'+
                '</div>';
        }
        else
            receiveHTML+=receiveHTMLTemp;
        if(allHTMLTemp == ""){
            allHTML = '<div class="row">'+
                '<div class="col-sm-3 col-md-3 col-lg-3"></div> '+
                '<div class="col-sm-6 col-md-6 col-lg-6">'+
                '<h2 style="font-family: cursive;">没有相关订单</h2>'+
                '</div>'+
                '</div>';
        }
        else
            allHTML+=allHTMLTemp;

        // 设置每个状态订单的数量
        unHandleCount.html(unHandleCounts);
        transportCount.html(transportCounts);
        receiveCount.html(receiveCounts);
        allCount.html(allCounts);

        // 将设置的内容块填入指定区域
        unHandleTable.html(unHandleTable.html()+unHandleHTML);
        transportTable.html(transportTable.html()+transportHTML);
        receiveTable.html(receiveTable.html()+receiveHTML);
        allTable.html(allTable.html()+allHTML);
        layer.close(loading);
    }

    // 根基商品id获取具体商品对象
    function getProductById(id) {
        var product = {};
        product.id = id;
        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/product?getProductById',
            data : product,
            dataType : 'json',
            success : function(result) {
                product = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        return product;
    }

    // 根据用户id获取地址信息
    function getUserAddress(id) {
        var user = {};
        user.userId = id;
        var address = "";
        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/user?getUserAddressAndPhoneNumber',
            data : user,
            dataType : 'json',
            success : function(result) {
                address = result.address;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        return address;
    }

    // 根据用户id获取电话号码
    function getUserPhoneNumber(id) {
        var user = {};
        user.userId = id;
        var phoneNumber = "";
        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/user?getUserAddressAndPhoneNumber',
            data : user,
            dataType : 'json',
            success : function(result) {
                phoneNumber = result.phoneNumber;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        return phoneNumber;
    }

    // 使用jstl根据session中的currentUser属性判断用户是否已登录，否则跳转至登陆界面
    function judgeIsLogin() {
        var isLogined=false;
        <c:if test="${not empty currentUser and not empty currentUser.id}">
        isLogined=true;
        </c:if>
        if(!isLogined){
            layer.confirm('您还没有登陆哦，请先登陆吧~',{icon:1},function(){
                window.location.href="${contextPath}/login";
            });
        }
        return isLogined;
    }
    // 用于用户获取所有的订单记录
    function getShoppingRecords() {
        // 判断用户是否登录
        if(judgeIsLogin()){
        var user = {};
        user.userId = ${currentUser.id};
        var allShoppingRecord = "";
        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/shoppingRecord?getShoppingRecords',
            data : user,
            dataType : 'json',
            success : function(result) {
                allShoppingRecord = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        return allShoppingRecord;
    }
    }
    // 设置点击收获后更改订单状态
    function receiveProducts(userId,productId,createTime) {
        layer.confirm('您确定收到货了嘛？',{icon: 3, title:'提示'},function (loading) {
            var shoppingRecord = {};
            shoppingRecord.userId = userId;
            shoppingRecord.productId = productId;
            shoppingRecord.createTime = createTime;
            shoppingRecord.orderStatus = 2;
            $.ajax({
                type : 'POST',
                url : '${contextPath}/shoppingRecord?updateShoppingRecords',
                data : shoppingRecord,
                dataType : 'json',
                success : function(result) {
                    if(result.result == "success"){
                        layer.msg('收货成功',{icon:1},function(){
                            window.location.href = "${contextPath}/shoppingRecord?showShoppingRecord";
                        });
                        layer.close(loading);
                    }
                },
                error : function(result) {
                    layer.alert('收货失败啦，再试一次吧~');
                }
            });
        });
    }

    // 根据商品id跳转相应的商品详情页
    function productDetail(id) {
        var product = {};
        var jumpResult = '';
        product.id = id;
        $.ajax({
            type : 'POST',
            url : '${contextPath}/product?getProductDetail',
            data : product,
            dataType : 'json',
            success : function(result) {
                if(result.result == "success"){
                    layer.msg('即将进入详情页面~',{icon:1,time:2000},function(){
                        window.location.href = "${contextPath}/product?getProductDetail";
                    });
                }
            },
            error : function(resoult) {
                layer.alert('查询错误');
            }
        });
    }
</script>
</html>
