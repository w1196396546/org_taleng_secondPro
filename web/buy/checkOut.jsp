<%--
  Created by IntelliJ IDEA.
  User: WHQ
  Date: 2021-12-9 0009
  Time: 下午 6:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
<c:if test="${sessionScope.user==null}">
    <div class="site-nav-bg">
        <div class="site-nav w1200">
            <p class="sn-back-home">
                <i class="layui-icon layui-icon-home"></i>
                <a href="#">首页</a>
            </p>
            <div class="sn-quick-menu">
                <div class="login"><a href="user/login.jsp">登录</a></div>
                <div class="sp-cart"><a href="operation?method=showIpShoppingCart">购物车</a><span>2</span></div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${sessionScope.user!=null}">
    <div class="site-nav-bg">
        <div class="site-nav w1200">
            <p class="sn-back-home">
                <i class="layui-icon layui-icon-home"></i>
                <a href="#">首页</a>
            </p>
            <div class="sn-quick-menu">
                <div class="login"><a href="user/userCenter.jsp">用户中心</a></div>
                <div class="sp-cart"><a href="user?method=userShoppingCart&userEmail=${sessionScope.user.userEmail}">购物车</a><span>${cookie.cou.value}</span></div>
                <div class="login" style="margin-left: 15px;"><a href="user?method=logOut">退出登录</a></div>
            </div>

        </div>
    </div>
</c:if>
<div class="content content-nav-base  login-content">
    <div class="login-bg">
        <div class="login-cont w1200">
                <div style="font-size: 50px;font-family: 华文楷体">
                    确认订单
                </div>
                <div style="height: 700px;width: 1200px;background-color: white">
                    <span style="font-size: 25px;font-family: 楷体;position: relative;top: 2vw;left: 2vw">收货地址</span>
                    <div style="position: relative;top: 5vw;left: 3vw;">
                        <button class="layui-btn mybtn">点击添加收货地址</button>
                    </div>
                    <script>
                        layui.use(['jquery'],function () {
                            var $ =layui.$;
                            $(".mybtn").click(function () {
                                layer.open({
                                    type: 2,//弹出层的类型，2:表示是一个frame
                                    shade: 0.2,
                                    area: ['500px','450px'],
                                    anim:1,//动画效果
                                    title:"添加地址",
                                    content: 'addAddress.jsp',
                                    zIndex: layer.zIndex, //重点1
                                    success: function(layero){
                                        layer.setTop(layero); //重点2
                                        //要将父窗口的数据，传递到子窗口弹出层中
                                        //1、获得弹出层（子窗体）的body
                                        var  body = layui.layer.getChildFrame("body");

                                        // //给body中的控件赋值
                                        // body.find("[name='id']").val(data.id);
                                        // body.find("[name='name']").val(data.name);
                                        // body.find("[name='clazz']").val(data.clazz);
                                        // body.find("[name='score']").val(data.score);
                                        // //如果生日是Date类型，默认取出崃是时间戳，将不能在日期控件中直接回显（要在日期控件中回显，格式必须是:yyyy-MM-dd）
                                        // body.find("[name='bir']").val(layui.util.toDateString(data.bir,'yyyy-MM-dd'));
                                        // //单选按钮
                                        // body.find("[value='"+data.gender+"']").attr("checked",true);

                                        //获得弹出层的window
                                        var updateWin = layero.find('iframe')[0].contentWindow;
                                        //渲染弹出层中的表单组件
                                        updateWin.layui.form.render("radio");
                                    }
                                });
                            });
                        });
                    </script>
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
