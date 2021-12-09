<%--
  Created by IntelliJ IDEA.
  User: WHQ
  Date: 2021-12-9 0009
  Time: 下午 7:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../res/layui/css/layui.css"/>
    <script src="../res/layui/layui.js"></script>
</head>
<body>
<form class="layui-form" id="frm" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="name" required  lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">选择省份:</label>
        <div class="layui-input-inline">
            <select name="province">
                <option value="-1">请选择省份:</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">选择城市:</label>
        <div class="layui-input-inline">
            <select name="city">
                <option value="-1">请选择城市:</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">选择区域:</label>
        <div class="layui-input-inline">
            <select name="area">
                <option value="-1">请选择区域:</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">详细地址:</label>
        <div class="layui-input-inline">
           <textarea rows="5" cols="24" required></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电话</label>
        <div class="layui-input-inline">
            <input type="text" name="tel" required  lay-verify="required" placeholder="请输入联系方式" autocomplete="off" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮编</label>
        <div class="layui-input-inline">
            <input type="number" name="score" required  placeholder="请输入邮政编号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    //Demo
    layui.use(['form','jquery','layer'], function(){
        var form = layui.form;
        var $ = layui.$;
        var layer = layui.layer;

        //监听提交
        form.on('submit(formDemo)', function(data){

            $.ajax({
                url:"ajax?method=add",
                type:"post",
                data: $("#frm").serialize(),
                success:function () {
                    //1、提示消息框
                    layer.msg("添加成功！",{icon:1});

                    //2、关闭弹出层
                    //3、刷新主页面表格中的数据
                    //只要当前窗体的父窗体刷新，弹出层会自动关闭，并且父窗体的数据会自动刷新
                    setTimeout(function () {
                        window.parent.location.reload();
                    },2000);


                }
            });

            return false;
        });
    });
</script>
</body>
</html>
