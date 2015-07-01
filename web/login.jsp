<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="template/head.jsp">
            <jsp:param name="pageTitle" value='<%= java.net.URLEncoder.encode("登陆", "UTF-8")%>'></jsp:param>
        </jsp:include>
        <script>
            $(function () {
                $("#UserLogin").click(function () {
                    $("#UserLogin").attr("value", "正在验证身份……");
                    $("#UserLogin").attr("disabled", "disabled");

                    var sendData =
                            {
                                username: $("#UserName").val(),
                                password: $("#Password").val()
                            };
                    $.ajax({
                        type: "POST",
                        url: "validate",
                        data: sendData,
                        success: function (response) {
                            var responseData = eval("(" + response + ")");
                            if (responseData.success) {
                                window.location.href = "./";
                            } else {
                                $("#UserLogin").attr("value", "登陆");
                            }
                        }
                    });
                });
            });
        </script>
    </head>
    <body>
        <div class="container body-content">
            <div class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="UserName" class="col-lg-2 control-label">用户名</label>
                    <div class="col-lg-10">
                        <input type="text" width="50" class="form-control" id="UserName" placeholder="请输入用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="Password" class="col-lg-2 control-label">密码</label>
                    <div class="col-lg-10">
                        <input type="Password" width="50" class="form-control" id="Password" placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button id="UserLogin" type="buttom" class="btn btn-primary btn-lg">登陆</button>
                    </div>
                </div>
            </div>
            <%@include file="template/footer.jspf" %>
        </div> 
    </body>
</html>
