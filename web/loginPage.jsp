<%@page import="Hibernate.Usuario"%>
<%@page import="org.omg.PortableInterceptor.SYSTEM_EXCEPTION"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
        <title>Login Success Page</title>
    </head>
    <body>
        <%
            //allow access only if session exists
            Usuario user = null;
            String userName = " ";
            String sessionID = "= ";
            String cookieID = null;
            if (session.getAttribute("sessionID") == null) {
                response.sendRedirect("index.jsp");
            } else {

                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("JSESSIONID")) {
                            cookieID = cookie.getValue();
                            System.out.println("He trobat la cookie");

                        }
                    }
                }

                user = (Usuario) session.getAttribute("sessionID");
                System.out.println("id guardada ="+user.getIdsession());
                System.out.println("id cookie ="+cookieID);
                if (user.getIdsession().equals(cookieID)) {
                    userName = user.getNom();
                    sessionID = user.getIdsession();
                } else {
                    System.out.println("aqui no entra");
                    response.sendRedirect("index.jsp");
                    
                }
            }
        %>
        <h3>Hi <%=userName%> , <% %>Login successful. Your Session ID=<%=sessionID%></h3>
        <form action="logout" method="post"> 
            <input type="submit" value="Logout" >
        </form>
        
    </body>
</html>