<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="template/head.jsp">
            <jsp:param name="pageTitle" value='<%= java.net.URLEncoder.encode("登陆", "UTF-8")%>'></jsp:param>
        </jsp:include>
    </head>
    <body>
        <div class="container body-content">            
            <div class="jumbotron">
                <h2>技术支持</h2>
                <h1>JavaEE</h1>
                <p class="lead">JavaEE is a free web framework for building great Web sites and Web applications using HTML, CSS, and JavaScript.</p>
                <p>
                    <a href="http://www.oracle.com/technetwork/java/javaee/overview/index.html" class="btn btn-primary btn-lg">Learn more &raquo;</a>
                </p>
            </div>
            <%@include file="template/footer.jspf" %>
        </div> 
    </body>
</html>
