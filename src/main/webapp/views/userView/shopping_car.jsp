<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/12
  Time: 10:25 AM
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
<!--导航栏部分-->
<jsp:include page="../includeView/head.jsp"/>

<!-- 中间内容 -->
<div class="container-fluid bigHead">
    <div class="row">
        <div class="col-sm-10  col-md-10 col-sm-offset-1 col-md-offset-1">
            <div class="jumbotron" style="background: url('${contextPath}/bgimg/spcar.jpg');background-position-x: center;height: 300px;">
                <div style="position: relative;top: 70px;">
                <h1 style="font-family: fantasy;color: darkcyan;">LuckyMall</h1>
                <p style="font-family: inherit;color: darkred;">您的购物车清单</p>
                </div>
            </div>
        </div>
        <div class="col-sm-10  col-md-10 col-sm-offset-1 col-md-offset-1">
            <table class="table table-hover center" id="shoppingCarTable">
            </table>

            <hr/>
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-4"></div>
                <button type="button" class="btn btn-danger btn-lg col-lg-4 col-md-4 col-sm-4" style="position: relative;left: 560px;width: 100px;height: 30px;font-size: larger;padding-bottom: 0px;padding-top: 0px;font-family: cursive;" onclick="confirmPre()">确认购买</button>
            </div>
        </div>
    </div>
</div>

<!-- 尾部 -->
<jsp:include page="../includeView/foot.jsp"/>
</body>
<script type="text/javascript">
    var totalPrice = 0;
    updateShoppingCars();

    //更新购物车列表
    function updateShoppingCars() {
        var shoppingCarTable = $("#shoppingCarTable");
        var allShoppingCars = getShoppingCars();
        shoppingCarTable.html("");
        var html = '<tr>'+
            '<th>是否购买</th>'+
            '<th>商品名称</th>'+
            '<th>商品单价</th>'+
            '<th>商品数量</th>'+
            '</tr>';
        for(var i=0;i<allShoppingCars.length;i++){
            var product = getProductById(allShoppingCars[i].productId);
            html  += '<tr>'+
                '<td>'+
                '<div class="checkbox">'+
                '<label>'+
                '<input type="checkbox" id="checkbox'+allShoppingCars[i].productId+'" value="option1">'+
                '</label>'+
                '</div>'+
                '</td>'+
                '<td>'+product.productName+'</td>'+
                '<td>'+product.price+'</td>'+
                '<td>'+allShoppingCars[i].counts+'</td>'+
                '</tr>';
        }
        shoppingCarTable.html(shoppingCarTable.html()+html);
    }

    //获取购物车列表
    function getShoppingCars() {
        if(judgeIsLogin()){
        var shoppingCarProducts = "";
        var user = {};
        user.userId = ${currentUser.id};
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${contextPath}/shoppingCar?getShoppingCarById',
            data : user,
            dataType : 'json',
            success : function(result) {
                shoppingCarProducts = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        return shoppingCarProducts;
        }
    }

    //执行购买
    function confirmPre() {
        var allShoppingCars = getShoppingCars();
        var buyProducts = new Array;
        //购买商品数量
        var buyProductsCounts = new Array;
        //选中商品
        var buyCounts = 0;
        for(var i=0;i<allShoppingCars.length;i++){
            var checkBox = document.getElementById("checkbox"+allShoppingCars[i].productId);
            if(checkBox.checked){
                buyProducts[buyCounts] = allShoppingCars[i].productId;
                buyProductsCounts[buyCounts] = allShoppingCars[i].counts;
                buyCounts++;
            }
        }
        if(buyCounts == 0){
            layer.msg("未选中商品",{icon:2});
        }
        else{
            buyConfirm(buyProducts,buyProductsCounts);
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

    function buyConfirm(productsId,productsCounts) {
        var address = getUserAddress(${currentUser.id});
        var phoneNumber = getUserPhoneNumber(${currentUser.id});
        // var totalPrice = 0;
        if(address!=""&&address!=null) {
            var html = '<div class="col-sm-1 col-md-1 col-lg-1"></div>' +
                '<div class="col-sm-10 col-md-10 col-lg-10">' +
                '<table class="table confirm-margin">';
            for (var i = 0; i < productsId.length; i++) {
                var product = getProductById(productsId[i]);
                html += '<tr>' +
                    '<th>商品' + (i + 1) + '名称：</th>' +
                    '<td>' + product.productName + '</td>' +
                    '</tr>' +
                    '<tr>' +
                    '<th>商品单价：</th>' +
                    '<td>' + product.price + '</td>' +
                    '</tr>' +
                    '<tr>' +
                    '<th>购买数量：</th>' +
                    '<td>' + productsCounts[i] + '</td>' +
                    '</tr>' +
                    '<tr>';
                totalPrice += product.price * productsCounts[i];
            }
            html += '<th>总金额：</th>' +
                '<td>' + totalPrice + '</td>' +
                '</tr>' +
                '<tr>' +
                '<th>收货地址：</th>' +
                '<td>' + address + '</td>' +
                '</tr>' +
                '<tr>' +
                '<th>联系电话：</th>' +
                '<td>' + phoneNumber + '</td>' +
                '</tr>' +
                '</table>' +
                '<div class="row">' +
                '<div class="col-sm-4 col-md-4 col-lg-4"></div>' +
                '<button class="btn btn-danger col-sm-4 col-md-4 col-lg-4" style="position: relative;bottom: 10px" onclick="addToShoppingRecordsPre([' + productsId + '],[' + productsCounts + '])">确认支付</button>' +
                '</div>' +
                '</div>';
            layer.open({
                type: 1,
                title: '请确认订单信息：',
                content: html,
                area: ['650px', '380px'],
            });
        }
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
        if(address==""|address==null){
            layer.msg('请先补充地址信息哦！',{icon:1},function(){
                window.location.href="${contextPath}/user?updateSelfInfo";
            });
        }else {
            return address;
        }
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

    function addToShoppingRecordsPre(productsId,productsCounts) {
        for(var i=0;i<productsId.length;i++){
            addToShoppingRecords(productsId[i],productsCounts[i]);
        }
        <%--layer.msg('支付成功', {icon: 1, title:'支付详情'},--%>
            <%--function(){--%>
                <%--window.location.href = "${contextPath}/shoppingRecord?showShoppingRecord";--%>
            <%--},--%>
            <%--function(index){--%>
                <%--window.location.href = "${contextPath}/shoppingCar";--%>
            <%--}--%>
        <%--);--%>
        addScore();
    }

    function addScore(productId) {
        if(judgeIsLogin()){
            var product = getProductById(productId);
            var income = parseInt(totalPrice*0.05);

            var userScore = {};
            userScore.income = income;
            userScore.userId = '${currentUser.id}';
            userScore.productName = product.productName;
            $.ajax({
                async: false,
                type : 'POST',
                url : '${contextPath}/score?addScore',
                data : userScore,
                dataType : 'json',
                success : function(result) {
                    if(result.result == "success") {
                        layer.msg('支付成功！用户积分+'+income+'!', {icon: 1, title:'支付详情'},
                            function () {
                                window.location.href = "${contextPath}/shoppingRecord?showShoppingRecord";
                            },
                            function(index){
                                layer.close(index);
                            }
                        );
                    }
                    else if(result.result == "fail"){
                        layer.msg("积分增加出错咯");
                    }
                },
                error : function(result) {
                    layer.alert('出错咯~再试试吧');
                }
            });
        }
    }

    function addToShoppingRecords(productId,productCounts) {
        if(judgeIsLogin()){
        var shoppingRecord = {};
        shoppingRecord.userId = ${currentUser.id};
        shoppingRecord.productId = productId;
        shoppingRecord.counts = productCounts;
        var buyResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${contextPath}/shoppingRecord?addShoppingRecord',
            data : shoppingRecord,
            dataType : 'json',
            success : function(result) {
                buyResult = result.result;
            },
            error : function(result) {
                layer.alert('购买错误');
            }
        });
        var product = getProductById(productId);

        if(buyResult == "success") {
            //删除购物车对应记录
            deleteShoppingCar(productId);
            // layer.msg("商品 "+product.productName+" 购买成功",{icon:1});
        }
        else if(buyResult == "unEnough"){
            layer.alert("商品 "+product.name+" 库存不足，购买失败");
        }else {
            layer.alert("商品 "+product.name+" 购买失败")
        }
        }
    }

    //删除对应购物车记录
    function deleteShoppingCar(productId) {
        var shoppingCar = {};
        shoppingCar.userId = ${currentUser.id};
        shoppingCar.productId = productId;
        var deleteResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${contextPath}/shoppingCar?deleteShoppingCar',
            data : shoppingCar,
            dataType : 'json',
            success : function(result) {
                deleteResult = result.result;
            },
            error : function(result) {
                layer.alert('查询用户错误');
            }
        });
    }
</script>
</html>
