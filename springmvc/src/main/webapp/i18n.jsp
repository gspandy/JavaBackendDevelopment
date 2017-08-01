<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%--
  Created by IntelliJ IDEA.
  User: shitian
  Date: 2017-07-19
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html >
<head>

    <title>国际化i18n</title>
</head>
<body>
<%--常规化获取国际化信息--%>
<h2>${language}</h2>
<%--利用spring的message标签，快速获得messageSource对应配置文件中的国际化信息--%>
<spring:message code="language"></spring:message>
</body>
</html>
