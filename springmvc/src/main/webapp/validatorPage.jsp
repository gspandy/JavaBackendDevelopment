<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: shitian
  Date: 2017-07-15
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>输入验证</title>
</head>
<body>
<form:form method="POST" modelAttribute="user" action="/validator">
    <table>
        <tr>
            <td>Username : </td>
            <td><form:input path="name" /></td>
            <td><form:errors path="name" cssClass="error" /></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="提交"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
