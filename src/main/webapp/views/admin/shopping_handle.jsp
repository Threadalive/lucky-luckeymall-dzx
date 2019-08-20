<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/7
  Time: 9:55 AM
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
    <title>LuckyMall</title></head>
<body>
<%--导航栏--%>
<jsp:include page="../includeView/head.jsp" />

<div class="container-fluid bigHead">
    <div class="row">
        <div class="col-sm-10  col-md-10 col-sm-offset-1 col-md-offset-1">
            <div class="jumbotron" style="background: url('${contextPath}/bgimg/handle.jpg');background-position-x: center;height: 300px;">
                <div style="position: relative;top: 80px;">
                <h1 style="font-family: fantasy;color: darkcyan;">LuckyMall</h1>
                <p style="font-family: cursive;color: darkcyan">所有购买清单</p>
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
                        <table class="table table-hover center" id="unHandleTable">
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="transport">
                        <table class="table table-hover center" id="transportTable">
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="receive">
                        <table class="table table-hover center" id="receiveTable">
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="all">
                        <table class="table table-hover center" id="allTable">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--底部--%>
<jsp:include page="../includeView/foot.jsp" />
</body>

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

        var allShoppingRecords = getAllShoppingRecords();
        unHandleTable.html("");
        transportTable.html("");
        receiveTable.html("");
        allTable.html("");

        var unHandleHTML = '<tr>'+
            '<th style="font-family: cursive;font-size: larger;">购买用户</th>'+
            '<th style="font-family: cursive;font-size: larger;">商品名称</th>'+
            '<th style="font-family: cursive;font-size: larger;">购买数量</th>'+
            '<th style="font-family: cursive;font-size: larger;">付款金额</th>'+
            '<th style="font-family: cursive;font-size: larger;">订单状态</th>'+
            '<th style="font-family: cursive;font-size: larger;">发货</th>'+
            '</tr>';
        var transportHTML = '<tr>'+
            '<th style="font-family: cursive;font-size: larger;">购买用户</th>'+
            '<th style="font-family: cursive;font-size: larger;">商品名称</th>'+
            '<th style="font-family: cursive;font-size: larger;">购买数量</th>'+
            '<th style="font-family: cursive;font-size: larger;">付款金额</th>'+
            '<th style="font-family: cursive;font-size: larger;">送货地址</th>'+
            '<th style="font-family: cursive;font-size: larger;">联系电话</th>'+
            '<th style="font-family: cursive;font-size: larger;">订单状态</th>'+
            '</tr>';
        var receiveHTML = '<tr>'+
            '<th style="font-family: cursive;font-size: larger;">购买用户</th>'+
            '<th style="font-family: cursive;font-size: larger;">商品名称</th>'+
            '<th style="font-family: cursive;font-size: larger;">购买数量</th>'+
            '<th style="font-family: cursive;font-size: larger;">付款金额</th>'+
            '<th style="font-family: cursive;font-size: larger;">订单状态</th>'+
            '</tr>';
        var allHTML = '<tr>'+
            '<th style="font-family: cursive;font-size: larger;">购买用户</th>'+
            '<th style="font-family: cursive;font-size: larger;">商品名称</th>'+
            '<th style="font-family: cursive;font-size: larger;">购买数量</th>'+
            '<th style="font-family: cursive;font-size: larger;">付款金额</th>'+
            '<th style="font-family: cursive;font-size: larger;">订单状态</th>'+
            '</tr>';

        var unHandleHTMLTemp = "";
        var transportHTMLTemp = "";
        var receiveHTMLTemp = "";
        var allHTMLTemp = "";

        for(var i=0;i<allShoppingRecords.length;i++){
            var user = getUserById(allShoppingRecords[i].userId);
            var product = getProductById(allShoppingRecords[i].productId);
            allHTMLTemp  += '<tr>'+
                '<td style="font-family: cursive;font-size: larger;">'+user.userName+'</td>'+
                '<td style="font-family: cursive;font-size: larger;">'+product.productName+'</td>'+
                '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].counts+'</td>'+
                '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].productPrice+'</td>'+
                '<td style="font-family: cursive;font-size: larger;">'+orderArray[allShoppingRecords[i].orderStatus]+'</td>'+
                '</tr>';
            allCounts++;
            if(allShoppingRecords[i].orderStatus == 0){
                unHandleHTMLTemp+= '<tr>'+
                    '<td style="font-family: cursive;font-size: larger;">'+user.userName+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+product.productName+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].counts+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].productPrice+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+orderArray[allShoppingRecords[i].orderStatus]+'</td>'+
                    '<td>'+
                    '<button class="btn btn-primary btn-sm" style="background-color: darkred;border-color: white;" onclick="confirmSend('+allShoppingRecords[i].userId+','+allShoppingRecords[i].productId+',\''+allShoppingRecords[i].createTime+'\')">发货</button>'+
                    '</td>'+
                    '</tr>';
                unHandleCounts++;
            }
            else if(allShoppingRecords[i].orderStatus ==1){
                var address = getUserAddress(allShoppingRecords[i].userId);
                var phoneNumber = getUserPhoneNumber(allShoppingRecords[i].userId)
                transportHTMLTemp+= '<tr>'+
                    '<td style="font-family: cursive;font-size: larger;">'+user.userName+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+product.productName+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].counts+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].productPrice+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+address+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+phoneNumber+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+orderArray[allShoppingRecords[i].orderStatus]+'</td>'+
                    '</tr>';
                transportCounts++;
            }
            else if(allShoppingRecords[i].orderStatus ==2){
                receiveHTMLTemp += '<tr>'+
                    '<td style="font-family: cursive;font-size: larger;">'+user.userName+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+product.productName+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].counts+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+allShoppingRecords[i].productPrice+'</td>'+
                    '<td style="font-family: cursive;font-size: larger;">'+orderArray[allShoppingRecords[i].orderStatus]+'</td>'+
                    '</tr>';
                receiveCounts++;
            }
        }
        if(unHandleHTMLTemp == ""){
            unHandleHTML='<div class="row">'+
                '<div class="col-sm-3 col-md-3 col-lg-3"></div> '+
                '<div class="col-sm-6 col-md-6 col-lg-6">'+
                '<h2>没有相关订单</h2>'+
                '</div>'+
                '</div>';
        }
        else
            unHandleHTML+=unHandleHTMLTemp;
        if(transportHTMLTemp == ""){
            transportHTML = '<div class="row">'+
                '<div class="col-sm-3 col-md-3 col-lg-3"></div> '+
                '<div class="col-sm-6 col-md-6 col-lg-6">'+
                '<h2>没有相关订单</h2>'+
                '</div>'+
                '</div>';
        }
        else
            transportHTML+=transportHTMLTemp;
        if(receiveHTMLTemp == ""){
            receiveHTML = '<div class="row">'+
                '<div class="col-sm-3 col-md-3 col-lg-3"></div> '+
                '<div class="col-sm-6 col-md-6 col-lg-6">'+
                '<h2>没有相关订单</h2>'+
                '</div>'+
                '</div>';
        }
        else
            receiveHTML+=receiveHTMLTemp;
        if(allHTMLTemp == ""){
            allHTML = '<div class="row">'+
                '<div class="col-sm-3 col-md-3 col-lg-3"></div> '+
                '<div class="col-sm-6 col-md-6 col-lg-6">'+
                '<h2>没有相关订单</h2>'+
                '</div>'+
                '</div>';
        }
        else
            allHTML+=allHTMLTemp;

        unHandleCount.html(unHandleCounts);
        transportCount.html(transportCounts);
        receiveCount.html(receiveCounts);
        allCount.html(allCounts);

        unHandleTable.html(unHandleTable.html()+unHandleHTML);
        transportTable.html(transportTable.html()+transportHTML);
        receiveTable.html(receiveTable.html()+receiveHTML);
        allTable.html(allTable.html()+allHTML);
        layer.close(loading);
    }

    function getAllShoppingRecords() {
        if(judgeIsLogin()){
        var shoppingRecordProducts = "";
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${contextPath}/shoppingRecord?getAllShoppingRecords',
            data : {},
            dataType : 'json',
            success : function(result) {
                shoppingRecordProducts = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        return shoppingRecordProducts;
        }
    }

    function getProductById(id) {
        var productResult = "";
        var product = {};
        product.id = id;
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${contextPath}/product?getProductById',
            data : product,
            dataType : 'json',
            success : function(result) {
                productResult = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        return productResult;
    }

    function getUserById(id) {
        var userResult = "";
        var user = {};
        user.id = id;
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${contextPath}/user?getUserById',
            data : user,
            dataType : 'json',
            success : function(result) {
                userResult = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        return userResult;
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

    function sendProducts(userId,productId,createTime) {
        var sendResult = "";
        var shoppingRecord = {};
        shoppingRecord.userId = userId;
        shoppingRecord.productId = productId;
        shoppingRecord.createTime = createTime;
        shoppingRecord.orderStatus = 1;
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${contextPath}/shoppingRecord?updateShoppingRecords',
            data : shoppingRecord,
            dataType : 'json',
            success : function(result) {
                sendResult = result.result;
            },
            error : function(result) {
                layer.alert('发货错误');
            }
        });
        if(sendResult == "success"){
            layer.msg('发货成功',{icon:1},function () {
                window.location.href = "${contextPath}/shoppingRecord?shoppingRecordHandle";
            });
        }

    }
    function confirmSend(userId,productId,createTime) {
        var address = getUserAddress(userId);
        var phoneNumber = getUserPhoneNumber(userId);
        var product = getProductById(productId);

        var html = '<div class="col-sm-1 col-md-1 col-lg-1"></div>'+
            '<div class="col-sm-10 col-md-10 col-lg-10">'+
            '<table class="table confirm-margin">'+
            '<tr>'+
            '<th>商品名称：</th>'+
            '<td>'+product.productName+'</td>'+
            '</tr>'+
            '<tr>'+
            '<th>商品单价：</th>'+
            '<td>'+product.price+'</td>'+
            '</tr>'+
            '<tr>'+
            '<th>发货地址：</th>'+
            '<td>'+address+'</td>'+
            '</tr>'+
            '<tr>'+
            '<th>客户联系电话：</th>'+
            '<td>'+phoneNumber+'</td>'+
            '</tr>'+
            '</table>'+
            '<div class="row">'+
            '<div class="col-sm-4 col-md-4 col-lg-4"></div>'+
            '<button class="btn btn-danger col-sm-4 col-md-4 col-lg-4" onclick="sendProducts('+userId+','+productId+',\''+createTime+'\')">确认发货</button>'+
            '</div>'+
            '</div>';
        layer.open({
            type:1,
            title:'请确认发货信息：',
            content:html,
            area:['600px','300px'],
            offset:"center"
        });
    }
</script>
</html>
