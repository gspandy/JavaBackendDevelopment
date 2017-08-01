<%--
  Created by IntelliJ IDEA.
  User: lei.zeng
  Date: 2017/7/6
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/bind/basicType">
    <input name="count" value="10" type="text"/>
    <input type="submit" value="基本类型数据绑定">
</form>
<form action="/bind/packageType">
    <input name="number" value="10" type="text"/>
    <input type="submit" value="包装类型数据绑定">
</form>
<form action="/bind/simpleObject">
    <input name="name" value="lavor" type="text"/>
    <input name="id" value="1" type="text"/>
    <input type="submit" value="简单对象类型数据绑定">
</form>
<form action="/bind/complexObject">
    <input name="number" value="10" type="text"/>
    <input name="user.name" value="lavor" type="text"/>
    <input name="user.id" value="1" type="text"/>
    <input type="submit" value="复杂对象类型数据绑定">
</form>
<form action="/bind/arrayObject">
    <input name="strings" value="lavor" type="text"/>
    <input name="strings" value="hello" type="text"/>
    <input type="submit" value="数组对象类型数据绑定">
</form>
<form action="/bind/setObject">
    <input name="list[0]" value="lavor" type="text"/>
    <input name="list[1]" value="hello" type="text"/>
    <input type="submit" value="集合对象类型数据绑定">
</form>
</body>
</html>
