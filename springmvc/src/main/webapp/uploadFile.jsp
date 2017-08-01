<%--
  Created by IntelliJ IDEA.
  User: lei.zeng
  Date: 2017/7/5
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/uploadFile" enctype="multipart/form-data" method="post">
    <input type="file" name="file">
    <input type="submit" value="提交">
</form>
<form action="/uploadMutiFiles" enctype="multipart/form-data" method="post">
    <input type="file" name="file">
    <input type="file" name="file2">
    <input type="file" name="file3">
    <input type="submit" value="提交">
</form>
</body>
</html>
