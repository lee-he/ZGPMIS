<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="template/head.jsp">
            <jsp:param name="pageTitle" value="<%= java.net.URLEncoder.encode("登陆", "UTF-8")%>"></jsp:param>
        </jsp:include>
    </head>
    <body>
        <div class="container body-content">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="username_input" class="col-lg-2 control-label">用户名</label>
                    <div class="col-lg-10">
                        <input type="text" width="50" class="form-control" id="username_input" placeholder="请输入用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password_input" class="col-lg-2 control-label">密码</label>
                    <div class="col-lg-10">
                        <input type="password" width="50" class="form-control" id="password_input" placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button type="submit" class="btn btn-primary btn-lg">登陆</button>
                    </div>
                </div>
            </form>
            <%@include file="template/footer.jspf" %>
        </div> 
    </body>
</html>
