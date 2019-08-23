<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/8/19
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品信息修改</title>
</head>
<body>
<jsp:include page="../includeView/head.jsp" />

<div class="col-md-12">
    <hr/>
    <h1 ><a style="color: dimgrey;font-family: cursive;" name="section3">编辑商品信息</a></h1>
    <hr/>
    <div class="col-sm-offset-2 col-md-offest-2">
        <div style="width: 300px;height: 400px">
            <img src="${contextPath}/img/${sessionScope.alterProduct}.jpg" style="width: 400px;height:500px;position: relative;top: 50px;right: 120px">
        </div>
        <!-- 表单输入 -->
        <div  class="form-horizontal" style="position: relative;left: 240px;bottom: 350px">
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
                    <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="updateProduct()">更新商品</button>
                </div>
            </div>
        </div>
        <br/>
    </div>
</div>
<jsp:include page="../includeView/foot.jsp" />
</body>
<script type="text/javascript">
    initData();
    function initData() {
        var productId = ${sessionScope.alterProduct};
        var product = getProductById(productId);

        $("#productName").val(product.productName);
        $("#productDescribe").val(product.description);
        $("#keyWord").val(product.keyWord);
        $("#productPrice").val(product.price);
        $("#productCount").val(product.count);
        $("#productType").val(product.type);
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

    function updateProduct() {
        var loadings = layer.load(0);
        var product = new FormData();
        product.append('id',${sessionScope.alterProduct});
        product.append('productName',$("#productName").val());
        product.append('description',$("#productDescribe").val());
        product.append('keyWord',$("#keyWord").val());
        product.append('price',$("#productPrice").val());
        product.append('counts',$("#productCount").val());
        product.append('type',$("#productType").val());
        product.append('file',$("#productImgUpload")[0].files[0]);
        $.ajax({
            type : 'POST',
            url : '${contextPath}/product?updateProduct',
            data : product,
            dataType : 'json',
            contentType: false, //不设置内容类型
            processData: false, //不处理数据
            success : function(result) {
                layer.msg('商品信息更新成功', {icon: 1, time: 1000});
                layer.close(loadings);
            },
            error : function(result) {
                layer.alert('添加商品失败');
            }
        });
    }

</script>
</html>
