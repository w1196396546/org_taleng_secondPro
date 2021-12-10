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
            <input type="text" required id="name" name="name" required  lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <input type="hidden" value="" name="flag">
        <input type="hidden" name="addrId" value="">
        <label class="layui-form-label">选择省份:</label>
        <div class="layui-input-inline">
            <select name="province" id="mypro" lay-filter="province" required>
                <option value="-1">请选择省份:</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">选择城市:</label>
        <div class="layui-input-inline">
            <select name="city" id="city" lay-filter="city" required>
                <option value="-1">请选择城市:</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">选择区域:</label>
        <div class="layui-input-inline">
            <select name="area" id="area" required>
                <option value="-1">请选择区域:</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">详细地址:</label>
        <div class="layui-input-inline">
           <textarea rows="5" cols="24" required name="addr" id="addr" required></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电话</label>
        <div class="layui-input-inline">
            <input type="text" id="tel" required name="tel" required  lay-verify="required" placeholder="请输入联系方式" autocomplete="off" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮编</label>
        <div class="layui-input-inline">
            <input type="number" id="youbian" required name="youbian" required  placeholder="请输入邮政编号" autocomplete="off" class="layui-input">
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
        // alert(1)
            var msg="";
            $.ajax({
                url:"../operation",
                type: "post",
                data: {"method":"getProvince"},
                dataType:"json",
                success:function (data) {
                    $.each(data,function (index,k) {
                        console.log(form)
                       $("#mypro").append($("<option value='"+k.pid+"'>"+k.pname+"</option>"));
                    });
                    form.render("select");
                    // alert(msg)
                }
            });
            form.on('select(province)',function (data) {
                    var pid=data.value;
                    $("#city")[0].options.length=1;
                    $.ajax({
                        url:"../operation",
                        type:"post",
                        data:{"method":"getCity","pid":pid},
                        dataType: "json",
                        success:function (data) {
                            $.each(data,function (index,k) {
                               $("#city").append($("<option value='"+k.cid+"'>"+k.cname+"</option>")) ;
                            });
                            form.render("select");
                        }
                    });
            });
            form.on('select(city)',function (data){
               var cid=data.value;
               $("#area")[0].options.length=1;
               $.ajax({
                  url:"../operation",
                  type:"post",
                   data:{
                      "method":"getArea",
                       "cid":cid
                   },
                   dataType:"json",
                   success:function (data) {
                       $.each(data,function (index,k) {
                          $("#area").append($("<option value='"+k.aid+"'>"+k.aname+"</option>"))
                       });
                       form.render("select");
                   }
               });
            });
        //监听提交
        form.on('submit(formDemo)', function(data){
            // alert(112323)
            var provinceSelect=$("#mypro").children("option");
            var provinceMsg="";
            $.each(provinceSelect,function (index, k) {
               var check=$(k).prop("selected")
                if (check==true){
                    provinceMsg=$(k).html();
                }
            });
            var citySelect =$("#city").children("option");
            var cityMsg="";
            $.each(citySelect,function (index,k) {
               var check=$(k).prop("selected");
               if (check==true){
                   cityMsg=$(k).html();
               }
            });
            var areaSelect =$("#area").children("option");
            var areaMsg="";
            $.each(areaSelect,function (index,k) {
                var check=$(k).prop("selected");
                if (check==true){
                    areaMsg=$(k).html();
                }
            });
            // alert(provinceMsg+" "+cityMsg+" "+areaMsg)
            var name=$("#name").val();
            var pro=$("#mypro").val();
            var city=$("#city").val();
            var area=$("#area").val();
            var addr=$("#addr").val();
            //获得收货地址的收货id
            var addr_id=$("[name='addrId']").val();
            // alert(addr)
            var tel=$("#tel").val();
            var youbian=$("#youbian").val();
            // alert(youbian)
            // parent.show(name,pro,city,area,addr,tel,youbian,provinceMsg,cityMsg,areaMsg);
            var flag=$("[name='flag']").val();
            // alert(flag)
            if (flag!="true"){
                // alert("add")
                $.ajax({
                    url:"../user?method=addAddress&provinceMsg="+provinceMsg+"&cityMsg="+cityMsg+"&areaMsg="+areaMsg,
                    type:"post",
                    data: $("#frm").serialize(),
                    success:function () {
                        //1、提示消息框
                        layer.msg("添加成功！",{icon:1});

                        //2、关闭弹出层
                        //3、刷新主页面表格中的数据
                        //只要当前窗体的父窗体刷新，弹出层会自动关闭，并且父窗体的数据会自动刷新
                        setTimeout(function () {
                            parent.show(name,pro,city,area,addr,tel,youbian,provinceMsg,cityMsg,areaMsg);
                            window.parent.location.reload();
                        },2000);


                    }
                });
            }else {
                $.ajax({
                    url:"../user?method=updateAddress&provinceMsg="+provinceMsg+"&cityMsg="+cityMsg+"&areaMsg="+areaMsg,
                    type:"post",
                    data:$("#frm").serialize(),
                    success:function () {
                        //1、提示消息框
                        layer.msg("添加成功！",{icon:1});

                        //2、关闭弹出层
                        //3、刷新主页面表格中的数据
                        //只要当前窗体的父窗体刷新，弹出层会自动关闭，并且父窗体的数据会自动刷新
                        setTimeout(function () {
                            // parent.show(name,pro,city,area,addr,tel,youbian,provinceMsg,cityMsg,areaMsg);
                            window.parent.location.reload();
                        },2000);


                    }
                });
            }

            return false;
        });
    });
</script>
</body>
</html>
