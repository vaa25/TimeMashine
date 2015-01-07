<%--
  Created by IntelliJ IDEA.
  User: Б
  Date: 07.01.2015
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Hello World Sample
    </title>
</head>

<body>
<h1>
    Hello, world !
    <%
        String name = (String) request.getAttribute("name");
        if (name != null && name.length() != 0) {
    %>
    I'm <%= name%>
    <%
        }
    %>
</h1>
</body>
</html>