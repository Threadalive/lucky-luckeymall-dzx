<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/3
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>LuckyMall首页</title>
</head>
<body>
<%--导航栏部分--%>
<jsp:include page="includeView/head.jsp"/>

<%--首页正文部分--%>
<div class="container-fluid">
    <div class="row">
        <!-- 控制栏 -->
        <div class="col-sm-3 col-md-2 sidebar sidebar-1">
            <ul class="nav nav-sidebar">
                <li class="list-group-item-diy"><a href="#productArea1">潮流女装<span class="sr-only">(current)</span></a>
                </li>
                <li class="list-group-item-diy"><a href="#productArea2">时尚男装</a></li>
                <li class="list-group-item-diy"><a href="#productArea3">羽绒服</a></li>
                <li class="list-group-item-diy"><a href="#productArea4">衬衫/T恤</a></li>
                <li class="list-group-item-diy"><a href="#productArea5">外套上衣</a></li>
                <li class="list-group-item-diy"><a href="#productArea6">针织毛衫</a></li>
                <li class="list-group-item-diy"><a href="#productArea7">秋外套</a></li>
            </ul>
        </div>
        <!-- 控制内容 -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="jumbotron" style="background: url('${contextPath}/bgimg/main.jpg');background-position-x: center;height: 300px;">
                <h1 style="font-family: fantasy;color: black;position: relative;top: 90px;">LuckyMall</h1>
                <p style="font-family: fantasy;color: lightblue;position: relative;top: 105px;left: 19px;">LuckyMall——致力于装扮不一样的你</p>
            </div >
            <div style="position: relative;bottom: 45px;">
            <div name="productArea1" class="row pd-10" id="productArea1">
            </div>

            <div name="productArea2" class="row" id="productArea2">
            </div>

            <div name="productArea3" class="row" id="productArea3">
            </div>

            <div name="productArea4" class="row" id="productArea4">
            </div>

            <div name="productArea5" class="row" id="productArea5">
            </div>

            <div name="productArea6" class="row" id="productArea6">
            </div>

            <div name="productArea7" class="row" id="productArea7">
            </div>
            </div>

        </div>
        <%--页底部分--%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2">
            <jsp:include page="includeView/foot.jsp"/>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var loading = layer.load(0);

    var productType = new Array;
    productType[1] = "潮流女装";
    productType[2] = "时尚男装";
    productType[3] = "羽绒服";
    productType[4] = "衬衫/T恤";
    productType[5] = "外套上衣";
    productType[6] = "针织毛衫";
    productType[7] = "秋外套";

    listProduct();

    function listProduct() {
        //获取所有商品
        var allProduct = getAllProducts();
        //对区域进行标记，未填充时标记为0，填充后置1
        var mark = new Array;
        mark[1] = 0;
        mark[2] = 0;
        mark[3] = 0;
        mark[4] = 0;
        mark[5] = 0;
        mark[6] = 0;
        mark[7] = 0;
        for(var i=0;i<allProduct.length;i++){
            //准备填充的html
            var html = "";
            //商品图片路径
            var imgURL = "${contextPath}/img/"+allProduct[i].id+".jpg";
            html += '<div class="col-sm-4 col-md-4" >'+
                '<div class="boxes pointer" onclick="productDetail('+allProduct[i].id+')">'+
                '<div class="big bigimg">'+
                '<img src="'+imgURL+'" width="350">'+
                '</div>'+
                '<p class="product-name">'+allProduct[i].productName+'</p>'+
                '<p class="product-price">¥'+allProduct[i].price+'</p>'+
                '</div>'+
                '</div>';
            var id = "productArea"+allProduct[i].type;
            var productArea = document.getElementById(id);
            if(mark[allProduct[i].type] == 0){
                //填充后的区域置1
                html ='<hr/><h1 style="color: darkslategray;">'+productType[allProduct[i].type]+'</h1><hr/>'+html;
                mark[allProduct[i].type] = 1;
            }
            productArea.innerHTML += html;
        }
        layer.close(loading);
    }

    function getAllProducts() {
        var allProducts = null;
        var nothing = {};
        $.ajax({
            async: false,
            type: 'POST',
            url: '${contextPath}/product?getAllProduct',
            data: nothing,
            dataType: 'json',
            success: function (result) {
                if(result!=null){
                    allProducts = result.list;
                }else {
                    layer.alert('获取商品失败咯~~');
                }
            },
            error: function (result) {
                layer.alert('查询失败');
            }
        });
        return allProducts;
    }

    //点击商品后进入详情页
    function productDetail(id) {
        var product = {};
        var jumpResult = '';
        product.id = id;

        //使用json将商品id传到后台
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${contextPath}/product?getProductDetail',
            data : product,
            dataType : 'json',
            success : function(result) {
                jumpResult = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        if(jumpResult == "success"){
            window.location.href = "${contextPath}/product?getProductDetail";
        }
    }
</script>
</html>
