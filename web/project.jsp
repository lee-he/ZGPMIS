<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html lang="zh_CN">
    <head>
        <jsp:include page="template/head.jsp">
            <jsp:param name="pageTitle" value='<%= java.net.URLEncoder.encode("查询项目", "UTF-8")%>'></jsp:param>
        </jsp:include>
        <%@include file="./template/include/jqgrid.jspf" %>
        <script type = "text/javascript">
            $(function () {
                $("#jqGrid").jqGrid({
                    datatype: "json", //将这里改为使用JSON数据  
                    url: 'project?oper=list', //这是Action的请求地址  
                    mtype: 'POST',
                    height: 250,
                    colNames: ['项目名称', '业主名', '预算(万)', '项目截止日期', '联系电话', '项目地址'],
                    colModel: [
                        {name: 'name', index: 'name', sorttype: "string", width: "270", key: true, editable: true, editrules:{required: true}},
                        {name: 'owner', index: 'owner', width: "160", editable: true},
                        {name: 'budget', index: 'budget', width: "80", align: 'right', editable: true},
                        {name: 'duedate', index: 'duedate', width: "120", editable: true},
                        {name: 'tel', index: 'tel', width: "100", editable: true},
                        {name: 'address', index: 'address', width: "210", editable: true}
                    ],
                    pager: '#jqGridPager', //分页工具栏  
                    rowNum: 10, //每页显示记录数  
                    viewrecords: true, //是否显示行数  
                    rowList: [10, 20, 30], //可调整每页显示的记录数  
                    multiselect: false, //是否支持多选  
                    caption: "项目信息"
                });
                $('#jqGrid').navGrid('#jqGridPager',
                    {edit: true, add: true, del: true, search: false, refresh: false, view: false, position: "left", cloneToTop: false},
                    // options for the Edit Dialog
                    {
                        editCaption: "编辑",
                        errorTextFormat: function (data) {
                            return 'Error: ' + data.responseText
                        }
                    },
                    // options for the Add Dialog
                    {
                        url: 'project',
                        mtype: 'POST',
                        reloadAfterSubmit: true
                    },
                    // options for the Delete Dailog
                    {
                        url: 'project',
                        mtype: 'POST',
                        reloadAfterSubmit: true
                    }
                );
            });

            

        </script>
    </head>

    <body>
        <div class="container body-content">
            <!-- Main component for a primary marketing message or call to action -->
            <div class="hero-unit">
                <div id="content">			
                    <table id="jqGrid"></table>
                    <div id="jqGridPager"></div>						
                </div>
            </div>
            <%@include file="template/footer.jspf" %>
        </div>        
    </body>
</html>
