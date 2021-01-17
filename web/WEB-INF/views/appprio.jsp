<%-- 
    Document   : boottime
    Created on : 2018-11-28, 23:31:41
    Author     : yhx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="public/publicheader.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>App Prio</title>
        <%@include file="public/publiccss.jsp" %>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-table.min.css" />
    </head>
    <body>
        <div class="container">
            <c:set value="class='active'" var="navAppPrioStyle"></c:set>
            <%@include file="public/nav.jsp" %>
            <%@include file="public/commselected.jsp" %>
            <div class="checkbox">
                <label><input id="ShowAll" type="checkbox" value="">ShowAll</label>
            </div>
            <div class="row">
                <div id="tableDataOne" class="col-md-6">
                    <table id="dataTable"></table>
                </div>
                <div id="tableDataTwo" class="col-md-6">
                    <table id="dataTableCmp"></table>
                </div>
            </div>
        </div>
        <br/>
        
        <%@include file="public/publicjs.jsp" %>
        <script type="text/javascript" src="bootstrap/js/bootstrap-table.min.js"> </script>
        <script type="text/javascript" src="bootstrap/js/bootstrap-table-en-US.min.js"> </script>
        <script type="text/javascript" src="js/boottimechart.js"> </script>
        <script type="text/javascript" src="js/commselected_manager.js"> </script>
        
        <script type="text/javascript">
            var optionSelected = {
                indexType: "appprio",
                projectNameDivId: "dProjectName",
                variantNameDivId: "dPorjectVariant",
                variantDefaultStr: "Variant",
                versionDivId: "dversionSelect",
                versionDefaultStr: "Version",
                dataIndexDivId: "dindexSelect",
                dataIndexDefaultStr: "Test",
                enableAvg: false
            };
            
            var optionSelectedCmp = {
                indexType: "appprio",
                projectNameDivId: "dcProjectName",
                variantNameDivId: "dcPorjectVariant",
                variantDefaultStr: "cVariant",
                versionDivId: "dcVersionSelect",
                versionDefaultStr: "cVersion",
                dataIndexDivId: "dindexCmpSelect",
                dataIndexDefaultStr: "cTest",
                enableAvg: false
            };
            
            EnableSelectedAll(optionSelected, optionSelectedCmp);
            
            loadData();
            
            $("#dProjectName").change(function(){
                GetVariantNames(optionSelected);
            });
            
            $("#dPorjectVariant").change(function(){
                GetVersions(optionSelected);
            });

            $("#dversionSelect").change(function(){
                GetTestDataIndex(optionSelected);
            });
            
            //Compare data 
            $("#dcProjectName").change(function(){
                GetVariantNames(optionSelectedCmp);
            });
            
            $("#dcPorjectVariant").change(function(){
                GetVersions(optionSelectedCmp);
            });

            $("#dcVersionSelect").change(function(){
                GetTestDataIndex(optionSelectedCmp);
            });
            
            function loadAllPageData(name, type){
                var tableName = '#' + name;
                
                $(tableName).empty();
                $(tableName).bootstrapTable('destroy');
                $(tableName).bootstrapTable({
                    url : 'TestDataGet?dataType=AppPrioOne',
                    toolbar: '#toolbar',
                    cache: true,
                    striped: true,
                    detailView: false,
                    queryParams:function(){
                        var tmpParam = $('#form1').serializeArray();
                        
                        if (type === 0){
                            var param ={
                                projectname: tmpParam[0].value,
                                projectvariant: tmpParam[1].value,
                                dataversion: tmpParam[2].value,
                                dataindex: tmpParam[3].value,
                                isAllData: 'allData'
                            };
                            return param;
                        }else{
                            var param ={
                                projectname: tmpParam[4].value,
                                projectvariant: tmpParam[5].value,
                                dataversion: tmpParam[6].value,
                                dataindex: tmpParam[7].value,
                                isAllData: 'allData'
                            };
                            return param;
                        }
                    },
                    columns: [{
                         field: 'id',
                         title: 'ID',
                         align: 'left'
                    },{
                         field: 'pid',
                         title: 'PID',
                         align: 'left'
                    },{
                         field: 'pname',
                         title: 'PNAME',
                         align: 'left'
                    },{
                         field: 'tid',
                         title: 'TID',
                         align: 'left'
                    },{
                         field: 'tname',
                         title: 'TNAME',
                         align: 'left'
                    },{
                         field: 'prio',
                         title: 'TPRIO',
                         align: 'left'
                    },{
                         field: 'priotype',
                         title: 'PRIOTYPE',
                         align: 'left'
                    }]
                });
            }
            
            function loadMainPageData(name, type){
                var tableName = '#' + name;
                
                $(tableName).empty();
                $(tableName).bootstrapTable('destroy');
                $(tableName).bootstrapTable({
                    url : 'TestDataGet?dataType=AppPrioOne',
                    toolbar: '#toolbar',
                    cache: true,
                    striped: true,
                    detailView: true,
                    queryParams:function(){
                        var tmpParam = $('#form1').serializeArray();
                        
                        if (type === 0){
                            var param ={
                                projectname: tmpParam[0].value,
                                projectvariant: tmpParam[1].value,
                                dataversion: tmpParam[2].value,
                                dataindex: tmpParam[3].value
                            };
                            return param;
                        }else{
                            var param ={
                                projectname: tmpParam[4].value,
                                projectvariant: tmpParam[5].value,
                                dataversion: tmpParam[6].value,
                                dataindex: tmpParam[7].value
                            };
                            return param;
                        }
                    },
                    onExpandRow: function (index, row, $detail){
                        //console.log(row.pid);
                        
                        var subDataUrl = 'TestDataGet?dataType=AppPrioOneSub' + '&ProcessId=' + row.pid;
                        //console.log(subDataUrl);
                        
                        var cur_table = $detail.html('<table></table>').find('table');
                        
                        $(cur_table).empty();
                        
                        $(cur_table).bootstrapTable('destroy');
                        
                        $(cur_table).bootstrapTable({
                            url : subDataUrl,
                            toolbar: '#toolbar',
                            cache: true,
                            striped: true,
                            detailView: false,
                            queryParams:function(){
                                var tmpParam = $('#form1').serializeArray();
                        
                                if (type === 0){
                                    var param ={
                                        projectname: tmpParam[0].value,
                                        projectvariant: tmpParam[1].value,
                                        dataversion: tmpParam[2].value,
                                        dataindex: tmpParam[3].value
                                    };
                                    return param;
                                }else{
                                    var param ={
                                        projectname: tmpParam[4].value,
                                        projectvariant: tmpParam[5].value,
                                        dataversion: tmpParam[6].value,
                                        dataindex: tmpParam[7].value
                                    };
                                    return param;
                                }
                            },
                            columns:[{
                                field: 'id',
                                title: 'Indx',
                                align: 'left'   
                            },{
                                field: 'tid',
                                title: 'ThreadId',
                                align: 'left'
                            },{
                                field: 'tname',
                                title: 'ThreadName',
                                align: 'left'
                            },{
                                field: 'prio',
                                title: 'ThreadPrio',
                                align: 'left'
                            },{
                                field: 'priotype',
                                title: 'ThreadPrio',
                                align: 'left'
                            }]
                        });
                    },
                    columns: [{
                         field: 'id',
                         title: 'Indx',
                         align: 'left'
                    },{
                         field: 'pid',
                         title: 'ProcessId',
                         align: 'left'
                    },{
                         field: 'pname',
                         title: 'ProcName',
                         align: 'left'
                    }]
                });
            }

            function loadData(){
                var inputData = $('#form1').serializeArray();

                //console.log(inputData);

                for (var i = 0; i < inputData.length - 4; i ++)
                {
                    if (inputData[i].value.length === 0)
                    {
                        console.log("Please select version.");
                        alert("Please select check info");
                        return ;
                    }
                }
                
                if($("#ShowAll").is(":checked")){
                    //document.getElementById("tableDataTwo").style.display="block"; 
                    loadAllPageData("dataTable", 0);
                }else{
                    //document.getElementById("tableDataTwo").style.display="none";
                    loadMainPageData("dataTable", 0);
                }
            }

            function loadDataCompare(){
                var inputData = $('#form1').serializeArray();

                for (var i = 2; i < inputData.length; i ++)
                {
                    if (inputData[i].value.length === 0)
                    {
                        console.log("Please select compare version.");
                        alert("Please select check info");
                        return ;
                    }
                }
                
                if($("#ShowAll").is(":checked")){
                    //document.getElementById("tableDataTwo").style.display="block";
                    loadAllPageData("dataTableCmp", 1);
                }else{
                    //document.getElementById("tableDataTwo").style.display="none";
                    loadMainPageData("dataTableCmp", 1);
                }
            }            
        </script>
    </body>
</html>
