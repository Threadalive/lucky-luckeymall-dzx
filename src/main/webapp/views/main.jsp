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
                <li class="list-group-item-diy"><a href="#productArea1">套装 <span class="sr-only">(current)</span></a>
                </li>
                <li class="list-group-item-diy"><a href="#productArea2">外套</a></li>
                <li class="list-group-item-diy"><a href="#productArea3">裤子</a></li>
                <li class="list-group-item-diy"><a href="#productArea4">裙装</a></li>
                <li class="list-group-item-diy"><a href="#productArea5">帽子</a></li>
                <li class="list-group-item-diy"><a href="#productArea6">鞋子</a></li>
                <li class="list-group-item-diy"><a href="#productArea7">腰带</a></li>
            </ul>
        </div>
        <!-- 控制内容 -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="jumbotron">
                <h1>欢迎来到LuckyMall</h1>
                <p>LuckyMall——致力于装扮不一样的你</p>
            </div>

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
        <%--页底部分--%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2">
            <jsp:include page="includeView/foot.jsp"/>
        </div>
    </div>
</div>
</body>

<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/css/style.css" rel="stylesheet">

<script src="${contextPath}/js/jquery.min.js" type="text/javascript"></script>
<script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${contextPath}/js/layer.js" type="text/javascript"></script>
<script src="${contextPath}/js/html5shiv.min.js"></script>
<script src="${contextPath}/js/js/respond.min.js"></script>

<script type="text/javascript">
    var loading = layer.load(0);

    var productType = new Array;
    productType[1] = "套装";
    productType[2] = "外套";
    productType[3] = "裤子";
    productType[4] = "裙装";
    productType[5] = "帽子";
    productType[6] = "鞋子";
    productType[7] = "腰带";

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
                '<img src="'+imgURL+'">'+
                '</div>'+
                '<p class="product-name">'+allProduct[i].productName+'</p>'+
                '<p class="product-price">¥'+allProduct[i].price+'</p>'+
                '</div>'+
                '</div>';
            var id = "productArea"+allProduct[i].type;
            var productArea = document.getElementById(id);
            if(mark[allProduct[i].type] == 0){
                //填充后的区域置1
                html ='<hr/><h1>'+productType[allProduct[i].type]+'</h1><hr/>'+html;
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
            url: '${contextPath}/product?getAllProducts',
            data: nothing,
            dataType: 'json',
            success: function (result) {
                if(result!=null){
                    allProducts = result.allProducts;
                }else {
                    layer.alert('获取商品失败咯~~');
                }
            },
            error: function (result) {
                layer.alert('查询失败');
            }
        });
        allProducts = eval("("+allProducts+")");
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
            window.location.href = "${contextPath}/productView/product?getProductDetail";
        }
    }
</script>
</html>
