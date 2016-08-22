

<%@page import="com.lue.model.DataBank"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <title>Instagram</title>
        <style>
            table, td, th {
                border: 1px solid green;
            }

            th {
                background-color: green;
                color: white;
            }
        </style>
    </head>
    <body style="alignment-adjust: central;">
        <% String name = request.getSession().getAttribute("instagramName").toString();%>
        <h1 style="color: brown ; text-align: center;">Instagram <%=name%> </h1>

        <table>
            <thead>
            <th>sr. No</th>
            <th>Post Date</th>
            <th>Image_Url</th>
            <th>Number of likes</th>
            <th>Number Of Comments</th>
            <th>links to full post</th>
        </thead>
        <tbody>
            <%String msg = request.getParameter("msg");
                if (msg!=null && msg.equalsIgnoreCase("error")) {%>
        <h1 style="color: red; text-align: center;">
            <strong></strong>sorry!  ERROR!! please enter valid Details!!.
        </h1>
        <% } else {
            Object o = request.getSession().getAttribute("databank");
            DataBank data = (DataBank) o;
            int numberOfPost = Integer.parseInt(request.getSession().getAttribute("no").toString());
            int r = 1;
            for (int i = 0; i < numberOfPost; i++) {
        %>


        <tr>
            <td><%= r++%></td>
            <td><%=data.getDate().get(i)%></td>
            <td width="8%"><a href="<%=data.getImageUrl().get(i)%>" target="_blank"><img src="<%=data.getImageUrl().get(i)%>" width="100" height="70"/></a></td>
            <td><%=data.getNumberOfLikes().get(i)%></td>
            <td><%=data.getNumberOfComments().get(i)%></td>
            <td>
                <form method="post" action="fullpost">
                    <input type="hidden" name="fullposturl" value="<%= data.getLinkToFullPost().get(i)%>">
                    <input type="hidden" name="imgeurl" value="<%= data.getImageUrl().get(i)%>">
                    <input type="hidden" name="postdesc" value="<%= data.getPostDescription().get(i)%>">
                    <input type="submit" class="btn btn-primary" value="Get Full Post Details">
                </form>
                <%--                   <a href="<%= data.getLinkToFullPost().get(i) %>" target="_blank">links to full post</a>--%>
            </td>
        </tr>

        <% }
              }%>
    </tbody>
</table>

</body>
</html>