<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品详情</title>
</head>
<body>
<!--导航栏部分-->
<jsp:include page="../includeView/head.jsp"/>

<!-- 中间内容 -->
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-1 col-md-1"></div>
        <div class="col-sm-10 col-md-10">
            <h1>${productDetail.productName}</h1>
            <hr/>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-1 col-md-1"></div>
        <div class="col-sm-5 col-md-5">
            <img class="detail-img" src="${contextPath}/img/${productDetail.id}.jpg">
        </div>
        <div class="col-sm-5 col-md-5 detail-x">
            <table class="table table-striped">
                <tr>
                    <th>商品名称</th>
                    <td>${productDetail.productName}</td>
                </tr>
                <tr>
                    <th>商品价格</th>
                    <td>￥${productDetail.price}</td>
                </tr>
                <tr>
                    <th>商品描述</th>
                    <td>${productDetail.description}</td>
                </tr>
                <tr>
                    <th>商品类别</th>
                    <td>${productDetail.type}</td>
                </tr>
                <tr>
                    <th>商品库存</th>
                    <td>${productDetail.counts}</td>
                </tr>
                <tr>
                    <th>购买数量</th>
                    <td>
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-default" onclick="subCounts()">-</button>
                            <button id="productCounts" type="button" class="btn btn-default">1</button>
                            <button type="button" class="btn btn-default" onclick="addCounts()">+</button>
                        </div>
                    </td>
                </tr>
            </table>
            <div class="row">
                <div class="col-sm-1 col-md-1 col-lg-1"></div>
                <button class="btn btn-danger btn-lg col-sm-4 col-md-4 col-lg-4" onclick="addToShoppingCar('${productDetail.id}')">添加购物车</button>
                <div class="col-sm-2 col-md-2 col-lg-2"></div>
                <button  class="btn btn-danger btn-lg col-sm-4 col-md-4 col-lg-4" onclick="buyConfirm('${productDetail.id}')">立即购买</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-1 col-md-1 col-lg-1"></div>
        <div class="col-sm-10 col-md-10 col-lg-10">
            <hr class="division"/>
            <table class="table evaluationTable" border="0" id="evaluation">
                    <caption><h3>商品评价</h3></caption>
                <tr>
                    <th>用户昵称</th>
                    <th>评价详情</th>
                </tr>
            </table>
            <hr/>
            <div id="inputArea"></div>
        </div>
    </div>
</div>

<!-- 尾部 -->
<jsp:include page="../includeView/foot.jsp"/>


</body>
<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/css/style.css" rel="stylesheet">

<script src="${contextPath}/js/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="${contextPath}/js/layer.js" type="text/javascript"></script>
<script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

<script src="${contextPath}/js/html5shiv.min.js"></script>
<script src="${contextPath}/js/respond.min.js"></script>

<script type="text/javascript">

    listEvaluations();

    // 添加购物车功能
    function addToShoppingCar(productId) {
        if(judgeIsLogin()){
        var productCounts = $("#productCounts");
        // 取购买数量
        var counts = parseInt(productCounts.html());
        var shoppingCar = {};
        shoppingCar.userId = "${currentUser.id}";
        shoppingCar.productId = productId;
        shoppingCar.counts = counts;
        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/shoppingCar?addShoppingCar',
            data : shoppingCar,
            dataType : 'json',
            success : function(result) {
                if(result.result == "success") {
                    layer.confirm('前往购物车？', {icon: 1, title:'添加成功',btn:['前往购物车','继续浏览']},
                        function(){
                            window.location.href = "${contextPath}/shoppingCar";
                        },
                        function(index){
                            layer.close(index);}
                    );
                }
            },
            error : function(result) {
                layer.alert('查询用户错误');
            }
        });
        }
    }
    // 减少商品数量
    function subCounts() {
        var productCounts = $("#productCounts");
        var counts = parseInt(productCounts.html());
        if(counts>=2){
            counts--;
        }
        productCounts.html(counts);
    }

    //增加商品数量,库存还有的情况下可增加
    function addCounts() {
        var productCounts = $("#productCounts");
        var counts = parseInt(productCounts.html());
        if(counts<${productDetail.counts}){
            counts++;
        }
        productCounts.html(counts);
    }

    // 立即购买
    function buyConfirm(productId) {

        if(judgeIsLogin()){
        var address = getUserAddress('${currentUser.id}');
        var phoneNumber = getUserPhoneNumber('${currentUser.id}');
        // 购买数量
        var productCounts = $("#productCounts");
        var counts = parseInt(productCounts.html());
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
            '<th>购买数量：</th>'+
            '<td>'+counts+'</td>'+
            '</tr>'+
            '<tr>'+
            '<th>总金额：</th>'+
            '<td>'+counts*product.price+'</td>'+
            '</tr>'+
            '<tr>'+
            '<th>收货地址：</th>'+
            '<td>'+address+'</td>'+
            '</tr>'+
            '<tr>'+
            '<th>联系电话：</th>'+
            '<td>'+phoneNumber+'</td>'+
            '</tr>'+
            '</table>'+
            '<div class="row">'+
            '<div class="col-sm-4 col-md-4 col-lg-4"></div>'+
            '<button class="btn btn-danger col-sm-4 col-md-4 col-lg-4" onclick="addToShoppingRecords('+productId+')">确认购买</button>'+
            '</div>'+
            '</div>';
        layer.open({
            type:1,
            title:'请确认订单信息：',
            content:html,
            area:['650px','350px']
        });
        }
    }

    // 添加到订单记录
    function addToShoppingRecords(productId) {
        if(judgeIsLogin()){
        var productCounts = $("#productCounts");
        var counts = parseInt(productCounts.html());
        var shoppingRecord = {};
        shoppingRecord.userId = '${currentUser.id}';
        shoppingRecord.productId = productId;
        shoppingRecord.counts = counts;
        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/shoppingRecord?addShoppingRecord',
            data : shoppingRecord,
            dataType : 'json',
            success : function(result) {
                if(result.result == "success") {
                    layer.confirm('购买成功!前往订单详情？', {icon: 1, title:'购买成功',btn:['前往订单','继续购买']},
                        function(){
                            window.location.href = "${contextPath}/shoppingRecord?showShoppingRecord";
                        },
                        function(index){
                            layer.close(index);}
                    );
                }
                else if(buyResult == "unEnough"){
                    layer.alert("库存不足，亲下回再买哦~")
                }
            },
            error : function(result) {
                layer.alert('出错咯~再试试吧');
            }
        });
        }
    }

    // 展示评价
    function listEvaluations() {
        var evaluations = getEvaluations();
        var evaluationTable =$("#evaluation");
        var html = "";
        for(var i=0;i<evaluations.length;i++){
            var user = getUserById(evaluations[i].userId);
            html+='<tr>'+
                '<td>'+user.nickName+'</td>'+
                '<td>'+evaluations[i].content+'</td>'+
                '</tr>';
        }
        evaluationTable.html(evaluationTable.html()+html);

        //若用户订单存在,添加输入框
        if(getUserProductRecord() == "true"){
            var inputArea = $("#inputArea");
            html= '<div class="col-sm-12 col-md-12 col-lg-12">'+
                '<textarea class="form-control" rows="4" id="evaluationText"></textarea>'+
                '</div>'+
                '<div class="col-sm-12 col-md-12 col-lg-12">'+
                '<div class="col-sm-4 col-md-4 col-lg-4"></div>'+
                '<button class="btn btn-primary btn-lg evaluationButton col-sm-4 col-md-4 col-lg-4" onclick="addToEvaluation()" style="' +
                'bottom: 0px;' +
                'top: 10px;' +
                '">添加评价</button>'+
                '</div>';
            inputArea.html(inputArea.html()+html);
        }
    }

    // 根据用户id和商品id查询订单是否存在
    function getUserProductRecord() {
        var product = {};
        product.userId = '${currentUser.id}';
        product.productId = '${productDetail.id}';
        var flag = "";
        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/shoppingRecord?getUserProductRecord',
            data : product,
            dataType : 'json',
            success : function(result) {
               flag = result.result;
            },
            error : function(result) {
                layer.alert('您还没有登录哦');
            }
        });
        return flag;
    }

    // 获取评价
    function getEvaluations() {
        var evaluations = "";
        var product = {};
        product.productId = '${productDetail.id}';
        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/comment?getCommentByProductId',
            data : product,
            dataType : 'json',
            success : function(result) {
                evaluations = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        return evaluations;
    }

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

    // 根基商品id获取具体商品对象
    function getProductById(id) {
        var productResult = "";
        var product = {};
        product.id = id;
        $.ajax({
            async: false,
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
            layer.msg('您还没有登陆哦，请先登陆吧~',{icon:1,time:1000},function(){
                window.location.href="${contextPath}/login";
            });
        }
        return isLogined;
    }

    //添加到评价数据库中
    function addToEvaluation() {
        var inputText = $("#evaluationText").val();
        var evaluation = {};
        evaluation.userId = '${currentUser.id}';
        evaluation.productId = '${productDetail.id}';
        evaluation.content = inputText;
        var flag;
        $.ajax({
            async: false,
            type : 'POST',
            url : '${contextPath}/comment?addComment',
            data : evaluation,
            dataType : 'json',
            success : function(result) {
                flag = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        if(flag = "success"){
            layer.msg("评价成功",{icon:1});
            window.location.href = "${contextPath}/product?getProductDetail";
        }
    }
</script>
</html>