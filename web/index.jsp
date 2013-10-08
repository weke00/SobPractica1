<%-- 
    Document   : index
    Created on : 01-oct-2013, 20:10:41
    Author     : gerardca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            if (session.getAttribute("sessionID") != null) {
                response.sendRedirect("loginPage.jsp");
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Welcome to login page of Practica1 Aplication</h1>
        <form method="post" action="request.do">
            <table border="1">
                <tbody>
                    <tr>
                        <th>User: </th>
                        <th> <input type =text name=user size=20><br> </th>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td> <input type =password name=passwd size=20><br> </td>
                    </tr>
                </tbody>
            </table>
            <input type=submit value=enter>
        </form>
        <form name="newuser" action="" method="get" >
            <input type=button value="NEW USER" name="newuser"
                   onclick="document.forms.newuser.action = 'createUser.jsp';
                           document.forms.newuser.submit()">
        </form>

    </body>
</html>
