<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: shitian
  Date: 2017-07-15
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用Hibernate Validator实现的JSR303（及其升级版本）来进行输入验证</title>
</head>
<body>
<form:form method="POST" modelAttribute="jsr" action="/hibernateValidator">
    <table>
        <tr>
            <td>Username : </td>
            <td><form:input path="name" /></td>
            <td><form:errors path="name" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Username : </td>
            <td><form:input path="address" /></td>
            <td><form:errors path="address" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Username : </td>
            <td><form:input path="email" /></td>
            <td><form:errors path="email" cssClass="error" /></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="提交"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
