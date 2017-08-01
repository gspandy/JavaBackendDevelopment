<%--
  Created by IntelliJ IDEA.
  User: lei.zeng
  Date: 2017/7/18
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>json与ajax的使用</title>
    <script src="js/jquery-1.9.1.js"></script>
</head>
<input type="button" id="getJson" value="前台向后台传递json数据"><br/>
<input type="button" id="setJson" value="后台向前台传递json数据"><br/>
<script >
    $(document).ready(function () {
        $("#getJson").click(function () {
            var param = [{id: 1001, name: 'qq'}, {id: 1002, name: 'ww'}];
            //传递json数据给后台
            $.ajax({
                url: '/getJson',
                type: 'POST',
                dataType: "text",//ajax返回的数据类型
                contentType:"application/json",//ajax传递给后台的数据类型
                data: JSON.stringify(param),//将param转换成json字符串
                success: function (data) {
                    alert("向后台传递json数据成功！");
                },
                error:function (data) {
                    alert("向后台传递json数据失败！");
                }
            });　
        });
        $("#setJson").click(function () {
            //从后台获取json数据
            $.ajax({
                url: '/setJson',
                type: 'POST',
                dataType: "JSON",//ajax返回的数据类型
                success: function (data) {
                    //这里的data是返回的json数据对象,也可以返回json字符串然后用JSON.parse()转换成json数据对象
                    alert("向前台传递json数据成功！");
                },
                error:function (data) {
                    alert("向前台传递json数据失败！");
                }
            });
        });
    });
</script>
</body>
</html>
