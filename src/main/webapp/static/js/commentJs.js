// 根基商品id获取具体商品对象
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
    productResult = JSON.parse(productResult);
    return productResult;
}

// 根据用户id获取地址信息
function getUserAddress(id) {
    var address = "";
    var user = {};
    user.userId = id;
    $.ajax({
        async : false, //设置同步
        type : 'POST',
        url : '${contextPath}/user?getUserNameAndPhoneNumber',
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
    var phoneNumber = "";
    var user = {};
    user.userId = id;
    $.ajax({
        async : false, //设置同步
        type : 'POST',
        url : '${contextPath}/user?getUserNameAndPhoneNumber',
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
    if("${currentUser.id}" == null || "${currentUser.id}" == undefined || "${currentUser.id}" ==""){
        layer.msg('您还没有登陆哦，请先登陆吧~',{icon:1},function(){
            window.location.href="${contextPath}/login";
        });
    }
}