
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>post details</title>
    </head>
    <body>
        <% Object o=request.getSession().getAttribute("imgeurl");
        System.out.println("oo==="+o);
        %>
        <img src="<%=o%>">
        <h2 style="color: blueviolet;"><%= request.getSession().getAttribute("postDesc")%></h2>
    </body>
</html>
