<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html lang="zh_CN">
    <head>
        <meta charset="utf-8">
        <title>jqGrid.Bootstrap</title>
        <%@include file="template/include/javascript.jspf" %>
        <%@include file="template/include/css.jspf" %>
        <%@include file="template/include/jqgrid.jspf" %>

        <script type = "text/javascript">
            $(document).ready(function () {
                var template = "<div style='margin-left:15px;'><div> Customer ID <sup>*</sup>:</div><div> {CustomerID} </div>";
                template += "<div> Company Name: </div><div>{CompanyName} </div>";
                template += "<div> Phone: </div><div>{Phone} </div>";
                template += "<div> Postal Code: </div><div>{PostalCode} </div>";
                template += "<div> City:</div><div> {City} </div>";
                template += "<hr style='width:100%;'/>";
                template += "<div> {sData} {cData}  </div></div>";

                $("#jqGrid").jqGrid({
                    url: 'Handler/UserInformation',
                    editurl: 'clientArray',
                    datatype: "json",
                    colModel: [
                        {
                            label: 'Customer ID',
                            name: 'CustomerID',
                            width: 75,
                            key: true,
                            editable: true,
                            editrules: {required: true}
                        },
                        {
                            label: 'Company Name',
                            name: 'CompanyName',
                            width: 140,
                            editable: true
                        },
                        {
                            label: 'Phone',
                            name: 'Phone',
                            width: 100,
                            editable: true
                        },
                        {
                            label: 'Postal Code',
                            name: 'PostalCode',
                            width: 80,
                            editable: true
                        },
                        {
                            label: 'City',
                            name: 'City',
                            width: 140,
                            editable: true
                        }
                    ],
                    sortname: 'CustomerID',
                    sortorder: 'asc',
                    loadonce: true,
                    viewrecords: true,
                    width: 780,
                    height: 200,
                    rowNum: 10,
                    pager: "#jqGridPager"
                });

                $('#jqGrid').navGrid('#jqGridPager',
                        // the buttons to appear on the toolbar of the grid
                                {edit: true, add: true, del: true, search: false, refresh: false, view: false, position: "left", cloneToTop: false},
                        // options for the Edit Dialog
                        {
                            editCaption: "The Edit Dialog",
                            template: template,
                            errorTextFormat: function (data) {
                                return 'Error: ' + data.responseText
                            }
                        },
                        // options for the Add Dialog
                        {
                            template: template,
                            errorTextFormat: function (data) {
                                return 'Error: ' + data.responseText
                            }
                        },
                        // options for the Delete Dailog
                        {
                            errorTextFormat: function (data) {
                                return 'Error: ' + data.responseText
                            }
                        });
                    });

        </script>
    </head>

    <body>
        <div class="container body-content">
            <%@include file="template/navigate.jspf" %>

            <!-- Main component for a primary marketing message or call to action -->
            <div class="jumbotron"> 
                <div class="hero-unit">
                    <div id="content">			
                        <table id="jqGrid"></table>
                        <div id="jqGridPager"></div>						
                    </div>				

                    <br/><br/>

                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="inputEmail1" class="col-lg-2 control-label">Email</label>
                            <div class="col-lg-10">
                                <input type="email" width="50" class="form-control" id="inputEmail1" placeholder="Email">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword1" class="col-lg-2 control-label">Password</label>
                            <div class="col-lg-10">
                                <input type="password" width="50" class="form-control" id="inputPassword1" placeholder="Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"> Remember me
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <button type="submit" class="btn btn-default">Sign in</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <%@include file="template/footer.jspf" %>
        </div>        
    </body>
</html>
