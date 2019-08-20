<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/19
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
</head>
<body>
<jsp:include page="header.jsp" />

<div class="page-content d-flex align-items-stretch">
    <jsp:include page="index.jsp"/>
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
<jsp:include page="foot.jsp"/>
<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/css/style.css" rel="stylesheet">
<script src="${contextPath}/js/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${contextPath}/js/layer.js" type="text/javascript"></script>
<script src="${contextPath}/js/html5shiv.min.js"></script>
<script src="${contextPath}/js/js/respond.min.js"></script>
</body>
<script type="text/javascript">
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
                // listAllProduct();
            },
            error : function(result) {
                layer.alert('添加商品失败');
            }
        });
    }
</script>
</html>
