<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/5
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品查询</title>
</head>
<body>
<!--导航栏部分-->
<jsp:include page="../includeView/head.jsp"/>

<div class="container">
    <div class="row">
        <div id = "searchResultArea">
        </div>
    </div>
</div>

<jsp:include page="../includeView/foot.jsp" />
</body>

<script type="text/javascript">

    searchInit();
    //初始化查询
    function searchInit() {
        var searchKeyWord = "${searchKeyWord}";
        var kwIsNoEmpty;
        <c:if test="${not empty searchKeyWord}">
        kwIsNoEmpty=true;
        </c:if>
        if(kwIsNoEmpty){
            updateList(searchKeyWord);
        }
    }
    function search(searchKeyWord) {
        var search = {};
        search.searchKeyWord = searchKeyWord;
        var searchResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${contextPath}/product?searchProductByKeyWord',
            data : search,
            dataType : 'json',
            success : function(result) {
                searchResult = result.result;
            },
            error : function() {
                layer.alert('查询错误');
            }
        });
        return searchResult;
    }

    //更新查询列表
    function updateList(searchKeyWord) {
        var searchResultArea = $("#searchResultArea");
        var searchResult = search(searchKeyWord);
        var html = "";
        searchResultArea.html("");
        for(var i=0;i<searchResult.length;i++){
            var imgURL = "${contextPath}/img/"+searchResult[i].id+".jpg";
            html+= '<div class="col-sm-3 col-md-3 search-padding">'+
                '<div class="boxes pointer" onclick="productDetail('+searchResult[i].id+')">'+
                '<div class="big bigimg">'+
                '<img src="'+imgURL+'" width="350"/>'+
                '</div>'+
                '<p class="product-name">'+searchResult[i].productName+'</p>'+
                '<p class="product-price">¥'+searchResult[i].price+'</p>'+
                '</div>'+
                '</div>'+
                '<div class="col-sm-1 col-md-1"></div>';
        }
        searchResultArea.html(searchResultArea.html()+html);
    }

    //点击进入详情页
    function productDetail(id) {
        var product = {};
        var jumpResult = '';
        product.id = id;
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${contextPath}/product?getProductDetail',
            data : product,
            dataType : 'json',
            success : function(result) {
                jumpResult = result.result;
            },
            error : function() {
                layer.alert('查询错误');
            }
        });
        if(jumpResult == "success"){
            window.location.href = "${contextPath}/product?getProductDetail";
        }
    }
</script>
</html>

