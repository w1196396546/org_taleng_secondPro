<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Document</title>
  <link rel="stylesheet" type="text/css" href="../res/static/css/main.css">
  <link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">
  <link rel="stylesheet" type="text/css" href="css/lobibox.min.css"/>
  <script type="text/javascript" src="../res/layui/layui.js"></script>
  <script src="js/vue-2.4.0.js"></script>
  <script src="js/jquery-3.6.0.min.js"></script>
  <script src="js/lobibox.min.js"></script>
  <script src="js/jquery.cookie.js"></script>
  <script src="js/axios.min.js"></script>
<%--  <script src="js/jquery.my-modal.1.1.min.js"></script>--%>
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
        <div class="sp-cart"><a href="shopcart.jsp">购物车</a><span>2</span></div>
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


  <div class="header">
    <div class="headerLayout w1200">
      <div class="headerCon">
        <h1 class="mallLogo">
          <a href="#" title="母婴商城">
            <img src="../res/static/img/logo.png">
          </a>
        </h1>
        <div class="mallSearch">
          <form action="" class="layui-form" novalidate>
            <input type="text" name="title" required  lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入需要的商品">
            <button class="layui-btn" lay-submit lay-filter="formDemo">
                <i class="layui-icon layui-icon-search"></i>
            </button>
            <input type="hidden" name="" value="">
          </form>
        </div>
      </div>
    </div>
  </div>


  <div class="content content-nav-base shopcart-content">
    <div class="main-nav">
      <div class="inner-cont0">
        <div class="inner-cont1 w1200">
          <div class="inner-cont2">
            <a href="operation?method=commodity" class="active">所有商品</a>
            <a href="buytoday.jsp">今日团购</a>
            <a href="information.jsp">母婴资讯</a>
            <a href="about.jsp">关于我们</a>
          </div>
        </div>
      </div>
    </div>
    <div class="banner-bg w1200">
      <h3>夏季清仓</h3>
      <p>宝宝被子、宝宝衣服3折起</p>
    </div>
    <div class="cart w1200">
      <div class="cart-table-th">
        <div class="th th-chk">
          <div class="select-all">
            <div class="cart-checkbox">
<%--              class="check-all check" id="allCheckked"--%>
              <input id="checkAll" type="checkbox" value="true">

            </div>
          <label>&nbsp;&nbsp;全选</label>
          </div>
        </div>
        <div class="th th-item">
          <div class="th-inner">
            商品
          </div>
        </div>
        <div class="th th-price">
          <div class="th-inner">
            单价
          </div>
        </div>
        <div class="th th-amount">
          <div class="th-inner">
            数量
          </div>
        </div>
        <div class="th th-sum">
          <div class="th-inner">
            小计
          </div>
        </div>
        <div class="th th-op">
          <div class="th-inner">
            操作
          </div>
        </div>  
      </div>
      <div class="OrderList" id="app">
        <div class="order-content" id="list-cont">

          <c:set var="money" value="0"/>

          <c:forEach items="${goodsInfoList}" var="goodsInfo">
            <ul class="item-content layui-clear">
              <li class="th th-chk">
                <div class="select-all">
                  <div class="cart-checkbox">
<%--                    class="CheckBoxShop check" id=""--%>
                    <input id="checkOnee" type="checkbox" num="all" name="select-all" value="true">
                  </div>
                </div>
              </li>
              <li class="th th-item">
                <div class="item-cont">
                  <a href="javascript:;"><img src="${goodsInfo.goods_imgaddr}" alt=""></a>
                  <div class="text">
                    <div class="title">${goodsInfo.goods_name}</div>
                    <p><span>粉色</span>  <span>130</span>cm</p>
                  </div>
                </div>
              </li>
              <li class="th th-price">
                <span class="th-su">${goodsInfo.goods_price}</span>
              </li>
              <li class="th th-amount">
                <div class="box-btn layui-clear">
                  <c:if test="${sessionScope.user!=null}">
                    <div  class="layui-btn" id="lessDiv"><a id="lessa1" href="user?method=operationGoodsNum&type=l&goodsId=${goodsInfo.goods_id}" >-</a></div>
                  </c:if>
                  <c:if test="${sessionScope.user==null}">
                    <div  class="layui-btn" id="lessDiv"><a id="lessa1" href="operation?method=operationIpGoodsNum&type=l&goodsId=${goodsInfo.goods_id}" >-</a></div>

                  </c:if>
                  <input class="Quantity-input" type="" name="" value="${goodsInfo.goods_num}" disabled="disabled">
                  <c:if test="${sessionScope.user!=null}">
                  <div class="layui-btn addadv"><a href="user?method=operationGoodsNum&goodsId=${goodsInfo.goods_id}&type=a" id="adda1">+</a></div>

                  </c:if>
                  <c:if test="${sessionScope.user==null}">
                    <div class="layui-btn addadv"><a href="operation?method=operationIpGoodsNum&goodsId=${goodsInfo.goods_id}&type=a" id="adda1">+</a></div>

                  </c:if>
                </div>
              </li>
              <li class="th th-sum">
                <span class="sum">${goodsInfo.goods_price*goodsInfo.goods_num}</span>
              </li>
              <li class="th th-op">
                <span class="dele-btn">删除</span>
              </li>
            </ul>
            <c:set var="money" value="${money+goodsInfo.goods_price*goodsInfo.goods_num}"/>
          </c:forEach>
          <script>
            $("#lessa1").click(function () {
              var num=$(".Quantity-input").val();
              if (num<=1){
                layer.msg("商品数量不能少于1",{icon:2});
                return false;
              }
            })
            $("#adda1").click(function () {
              var num=$(".Quantity-input").val();
              if (num>=10){
                layer.msg("商品数量不能操作10",{icon:2});
                return false;
              }
            })
          </script>
<%--            <ul class="item-content layui-clear" v-for="(item,index) in list" :key="index">--%>
<%--              <li class="th th-chk">--%>
<%--                <div class="select-all">--%>
<%--                  <div class="cart-checkbox">--%>
<%--                    <input class="CheckBoxShop check" id="" type="checkbox" num="all" name="select-all" value="true">--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--              </li>--%>
<%--              <li class="th th-item">--%>
<%--                <div class="item-cont">--%>
<%--                  <a href="javascript:;"><img :src="item.goods_imgaddr" alt=""></a>--%>
<%--                  <div class="text">--%>
<%--                    <div class="title">{{item.goods_name}}</div>--%>
<%--                    <p><span>粉色</span>  <span>130</span>cm</p>--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--              </li>--%>
<%--              <li class="th th-price">--%>
<%--                <span class="th-su">{{item.goods_price|conversionPrice}}</span>--%>
<%--              </li>--%>
<%--              <li class="th th-amount">--%>
<%--                <div class="box-btn layui-clear">--%>
<%--                  <div class="less layui-btn">-</div>--%>
<%--                  <input class="Quantity-input" type="" name="" value="1" disabled="disabled">--%>
<%--                  <div class="add layui-btn">+</div>--%>
<%--                </div>--%>
<%--              </li>--%>
<%--              <li class="th th-sum">--%>
<%--                <span class="sum">{{item.goods_price|conversionPrice}}</span>--%>
<%--              </li>--%>
<%--              <li class="th th-op">--%>
<%--                <span class="dele-btn">删除</span>--%>
<%--              </li>--%>
<%--            </ul>--%>
        </div>
        <script>
          var sum=0;
          $(function () {
            $("#checkAll")[0].checked=true;
            var uls=$(".order-content").children("ul");
            var ins=$(uls).children("li").children("div").children("div").children("input");
            $.each(ins,function (index, k) {
              k.checked=true;
            });
            $.each(uls,function (index,k) {

              // var price=k.children("li");
              var lis=$(k).children("li");
              var price=$(lis[2]).children("span").html();
              var count=$(lis[3]).children("div").children("input").val();
              sum=sum+price*count;
            })
            $("#myspan").html(sum);
          });
          // box-btn
          $(".box-btn").on("change",".Quantity-input",function () {
            sum=0;
            var uls=$(".order-content").children("ul");
            $.each(uls,function (index,k) {
              // var price=k.children("li");
              var lis = $(k).children("li");
              var price = $(lis[2]).children("span").html();
              var count = $(lis[3]).children("div").children("input").val();
              sum = sum + price * count;
            });
          });
          $("#checkAll").change(function () {
            // alert(123)
            var che=$(this).prop("checked");
            var uls=$(".order-content").children("ul");
            var ins=$(uls).children("li").children("div").children("div").children("input");

            if (che==false){
              $.each(ins,function (index, k) {
                k.checked=false;
                sum=0;
                $("#myspan").html(sum);
              });
            }else {
              $.each(ins,function (index, k) {
                k.checked=true;
                sum=0;
                var uls=$(".order-content").children("ul");
                $.each(uls,function (index,k) {
                  // var price=k.children("li");
                  var lis = $(k).children("li");
                  var price = $(lis[2]).children("span").html();
                  var count = $(lis[3]).children("div").children("input").val();
                  sum = sum + price * count;

                });
              });
            }
            $("#myspan").html(sum);
          });
          $(".cart-checkbox").on("change","#checkOnee",function () {
            var flag=$(this).prop("checked");
            // alert(flag)
            if (flag==false){
              var lis=$(this).parent("div").parent("div").parent("li").parent("ul").children("li");
              var money=0;
                var price = $(lis[2]).children("span").html();
                var count = $(lis[3]).children("div").children("input").val();
                // alert("count="+count)
                money=price*count;
                // alert("money="+money)
                sum=sum-money;
            }else {
              var lis=$(this).parent("div").parent("div").parent("li").parent("ul").children("li");
              var money=0;
              var price = $(lis[2]).children("span").html();
              var count = $(lis[3]).children("div").children("input").val();
              // alert("count="+count)
              money=price*count;
              // alert("money="+money)
              sum=sum+money;
            }
            $("#myspan").html(sum);
          });
        </script>
      </div>

      <!-- 模版导入数据 -->
      <!-- <script type="text/html" id="demo">
        {{# layui.each(d.infoList,function(index,item){}}
          <ul class="item-content layui-clear">
            <li class="th th-chk">
              <div class="select-all">
                <div class="cart-checkbox">
                  <input class="CheckBoxShop check" id="" type="checkbox" num="all" name="select-all" value="true">
                </div>
              </div>
            </li>
            <li class="th th-item">
              <div class="item-cont">
                <a href="javascript:;"><img src="../res/static/img/paging_img1.jpg" alt=""></a>
                <div class="text">
                  <div class="title">宝宝T恤棉质小衫</div>
                  <p><span>粉色</span>  <span>130</span>cm</p>
                </div>
              </div>
            </li>
            <li class="th th-price">
              <span class="th-su">189.00</span>
            </li>
            <li class="th th-amount">
              <div class="box-btn layui-clear">
                <div class="less layui-btn">-</div>
                <input class="Quantity-input" type="" name="" value="1" disabled="disabled">
                <div class="add layui-btn">+</div>
              </div>
            </li>
            <li class="th th-sum">
              <span class="sum">189.00</span>
            </li>
            <li class="th th-op">
              <span class="dele-btn">删除</span>
            </li>
          </ul>
        {{# });}}
      </script> -->


      <div class="FloatBarHolder layui-clear">
        <div class="th th-chk">
          <div class="select-all">
            <div class="cart-checkbox">
<%--              class="check-all check"--%>
              <input   name="select-all" type="checkbox"  value="true">
            </div>
            <label>&nbsp;&nbsp;已选<span class="Selected-pieces">0</span>件</label>
          </div>
        </div>
        <div class="th batch-deletion">
          <span class="batch-dele-btn">批量删除</span>
        </div>
        <div class="th Settlement">
          <c:if test="${sessionScope.user==null}">
            <button class="layui-btn jiesuan1">结算</button>
          </c:if>
          <c:if test="${sessionScope.user!=null}">
            <a href="buy/checkOut.jsp">
              <button class="layui-btn jiesuan2">结算</button>
            </a>

          </c:if>
        </div>

        <div class="th total">
<%--          class="pieces-total"--%>
          <p>应付：<span id="myspan" class="pieces-total">0</span></p>
        </div>
      </div>
    </div>
  </div>
<script>
  $(".jiesuan1").click(function (){
    layer.msg("请登录之后在操作!",{icon:2});
  });
  // $(".jiesuan2").click(function () {
  //   //用户已登录，先得到总价格
  //   var price=$("#myspan").html();
  //   location.href="pay?method=aliPay";
  // });
</script>
<script>
    // // alert(1)
    // var name=$.cookie("user");
    // if (name!=""){
    //   var vue=new Vue({
    //     el:'#app',
    //     data:{
    //       msg:"asd",
    //       list:[]
    //     },
    //     methods:{
    //       init(){
    //         let _this=this;
    //         // alert("进入初始化方法")
    //         $.ajax({
    //           url:"user",
    //           type:"post",
    //           data: {"method":"userShoppingCart","userEmail":name},
    //           dataType:"json",
    //           success:function (data){
    //             _this.list=data;
    //             $.each(_this.list,function (index,k) {
    //               alert(k.goods_imgaddr)
    //             })
    //           }
    //         })
    //       }
    //     },
    //     created(){
    //       // alert("钩子函数")
    //       this.init();
    //     },
    //     filter:{
    //       conversionPrice(price){
    //         var p=parseInt(price);
    //         return p;
    //       }
    //     }
    //   });
    // }else {
    //   var vue=new Vue({
    //     el:'#app',
    //     data:{
    //       msg:"asd",
    //       list:[]
    //     },
    //     methods:{
    //       init(){
    //         alert("cookie没有值的购物车")
    //         let _this=this;
    //         alert("进入初始化方法")
    //         $.ajax({
    //           url:"operation",
    //           type:"post",
    //           data: {"method":"showIpShoppingCart"},
    //           dataType:"json",
    //           success:function (data){
    //             _this.list=data;
    //             $.each(_this.list,function (index,k) {
    //               alert(k.goods_imgaddr)
    //             })
    //           }
    //         })
    //       }
    //     },
    //     created(){
    //       this.init();
    //     },
    //     filter:{
    //       conversionPrice(price){
    //         var p=parseInt(price);
    //         return p;
    //       }
    //     }
    //   });
    // }

</script>
<script type="text/javascript">
  layui.config({
    base: '../res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
  }).use(['mm','jquery','element','car'],function(){
    var mm = layui.mm,$ = layui.$,element = layui.element,car = layui.car;

    // 模版导入数据
    // var html = demo.innerHTML,
    // listCont = document.getElementById('list-cont');
    // mm.request({
    //   url: '../json/shopcart.json',
    //   success : function(res){
    //     listCont.innerHTML = mm.renderHtml(html,res)
    //     element.render();
    //     car.init()
    //   },
    //   error: function(res){
    //     console.log(res);
    //   }
    // })
    // 
    car.init()


});
</script>
<%--<script>--%>
<%--  $(function (){--%>
<%--    $(".addadv").click(function () {--%>
<%--      var num=$(".Quantity-input").val();--%>
<%--      if (num>=5){--%>
<%--        layer.msg("商品数量不能大于5",{icon:2});--%>
<%--        return;--%>
<%--      }else {--%>
<%--        $("#adda1").attr("href","user?method=operationGoodsNum&goodsId=${goodsInfo.goods_id}&type=a");--%>
<%--      }--%>
<%--    });--%>
<%--  });--%>
<%--</script>--%>

<%--<script>--%>
<%--  $(function (){--%>
<%--    $(".addadv").click(function () {--%>
<%--      var num=$(".Quantity-input").val();--%>
<%--      if (num>=5){--%>
<%--        layer.msg("商品数量不能大于5",{icon:2});--%>
<%--        return;--%>
<%--      }else {--%>
<%--        $("#adda2").attr("href","operation?method=operationIpGoodsNum&goodsId=${goodsInfo.goods_id}&type=a");--%>
<%--      }--%>
<%--    });--%>
<%--  });--%>
<%--</script>--%>


<%--<button class="layui-btn jiesuan1">结算</button>--%>
<%--<script>--%>
<%--  $(".jiesuan").click(function (){--%>
<%--    layer.msg("请登录后在操作!",{icon:2});--%>
<%--  });--%>
<%--</script>--%>

<%--<script>--%>
<%--  $(".jiesuan2").click(function () {--%>
<%--    $.ajax({--%>
<%--      url:"pay",--%>
<%--      type:"post",--%>
<%--      data:{--%>
<%--        sum:$()--%>
<%--      }--%>
<%--    });--%>
<%--  });--%>
<%--</script>--%>
</body>
</html>