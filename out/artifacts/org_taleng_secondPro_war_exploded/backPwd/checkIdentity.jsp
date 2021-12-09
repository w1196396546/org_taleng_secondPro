<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="../res/static/css/main.css">
    <link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">
    <script type="text/javascript" src="../res/layui/layui.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
</head>
<body>
<div class="content content-nav-base  login-content">
    <div class="login-bg">
        <div class="login-cont w1200">
            <div class="form-box">
                <form class="layui-form" action="../user?method=setPwd&email=${requestScope.email}" method="post" >
                    <legend>找回密码</legend>
                    <div class="layui-form-item">
                        <div class="layui-inline iphone">
                            <div class="layui-input-inline">
                                <%--<i class="layui-icon layui-icon-cellphone iphone-icon"></i>--%>
                                <input type="password" name="pwd" required  placeholder="请输入密码" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline iphone">
                            <div class="layui-input-inline">
                                <%--<i class="layui-icon layui-icon-cellphone iphone-icon"></i>--%>
                                <input type="password" name="checkpwd" required placeholder=" 确认密码" class="layui-input">
                            </div>
                        </div>
                        <p style="color: red; size: 20px" id="emailP"></p>
                    </div>
                    <div class="layui-form-item login-btn">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit="" lay-filter="demo1" onclick="return checkPwd()">提交</button>
                        </div>
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>

<div class="footer">
    <div class="ng-promise-box">
        <div class="ng-promise w1200">
            <p class="text">
                <a class="icon1" href="javascript:;">7天无理由退换货</a>
                <a class="icon2" href="javascript:;">满99元全场免邮</a>
                <a class="icon3" style="margin-right: 0" href="javascript:;">100%品质保证</a>
            </p>
        </div>
    </div>
    <div class="mod_help w1200">
        <p>
            <a href="javascript:;">关于我们</a>
            <span>|</span>
            <a href="javascript:;">帮助中心</a>
            <span>|</span>
            <a href="javascript:;">售后服务</a>
            <span>|</span>
            <a href="javascript:;">母婴资讯</a>
            <span>|</span>
            <a href="javascript:;">关于货源</a>
        </p>
        <p class="coty">母婴商城版权所有 &copy; 2012-2020</p>
    </div>
</div>
<script type="text/javascript">
    var flag=false;
    layui.config({
        base: '../res/static/js/util' //你存放新模块的目录，注意，不是layui的模块目录
    }).use(['jquery','form'],function(){
        var $ = layui.$,form = layui.form;
        $("[name='checkpwd']").blur(function () {
            var pwd=$("[name='pwd']").val();
            var checkPwd=$(this).val();
            if (pwd==checkPwd){
                flag=true;
            } else {
                $("#emailP").html("两次输入的密码不一致!")
                flag=false;
            }
        });

    })
    function checkPwd() {
        if (flag==true){
            return true;
        } else {
            return false;
        }
    }
</script>

</body>
</html>