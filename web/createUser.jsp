<%-- 
    Document   : createUser
    Created on : 05-oct-2013, 19:10:15
    Author     : gerardca
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            if(session.getAttribute("sessionID")!= null)
                response.sendRedirect("loginPage.jsp");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page</title>
    </head>
    <body>
        <h1>Welcome to create user page of Practica1 Aplication</h1>
        <form method="post" action="createUser.do">
            <table border="1">
                <tbody>
                    <tr>
                        <th>User id: </th>
                        <th> <input type =text name=user_id size=20><br> </th>
                    </tr>
                    <tr>
                        <th>User name: </th>
                        <th><input type =text name=user_name size=20><br> </th>
                    </tr>
                    <tr>
                        <td>New Password: </td>
                        <td> <input type =password name=passwd size=20><br> </td>
                    </tr>
                </tbody>
            </table>
            <input type=submit value=enter>
        </form>
    </body>
</html>

