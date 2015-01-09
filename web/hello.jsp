<%--
  Created by IntelliJ IDEA.
  User: Б
  Date: 07.01.2015
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Hello World Sample
    </title>
</head>

<body>
<h1>
    ${hello}
    I'm
    <c:if test="${!empty name}">
        ${name}
    </c:if>
</h1>
</body>
</html>