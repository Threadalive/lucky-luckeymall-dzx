<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/19
  Time: 10:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品页</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
</head>
<body>
<jsp:include page="header.jsp" />

<div class="page-content d-flex align-items-stretch">
    <jsp:include page="index.jsp"/>
<div class="col-md-12">
    <hr/>
    <h1><a style="color: dimgrey;font-family: cursive;" name="section2">商品信息</a></h1>
    <hr/>
    <div class="col-lg-12 col-md-12 col-sm-12" id="productArea"></div>
    <br/>
</div>
</div>
        <jsp:include page="foot.jsp"/>
</body>
<script type="text/javascript" charset="UTF-8">
    listAllProduct();
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
                '<img src="'+imgURL+'" width="300">'+
                '</div>'+
                '<p class="font-styles center">'+allProduct[i].productName+'</p>'+
                '<p class="pull-right">价格：'+allProduct[i].price+'</p>'+
                '<p class="pull-left">库存：'+allProduct[i].counts+'</p>'+
                '<div class = "row">'+
                '<button class="btn btn-primary delete-button" style="background-color: darkred;border-color: white;width: 69px;height: 30px;padding-top: 5px;font-size: 2px;" type="submit" onclick="deleteProduct('+allProduct[i].id+')">删除商品</button>'+
                '</div>'+
                '</div>'+
                '</div>';
        }
        productArea.html(productArea.html()+html);
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

</script>
</html>
