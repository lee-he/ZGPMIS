<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="template/head.jsp">
            <jsp:param name="pageTitle" value='<%= java.net.URLEncoder.encode("首页", "UTF-8")%>'></jsp:param>
        </jsp:include>
    </head>
    <body>
        <div class="container body-content">            
            <div class="jumbotron">
                <h2>欢迎来到自贡市项目信息管理系统</h2>
            </div>
            <%@include file="template/footer.jspf" %>
        </div> 
    </body>
</html>
