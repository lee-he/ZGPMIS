<%@page import="java.util.Calendar"%>
<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= request.getParameter("pageTitle") %></title>
        <%@include file="./include/javascript.jspf" %>
        <%@include file="./include/css.jspf" %>
    </head>
    <body>
        <div class="container body-content">
            <%@include file="./navigate.jspf" %>
        </div>        
    </body>
</html>