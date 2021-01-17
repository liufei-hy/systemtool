<%-- 
    Document   : emmcusage
    Created on : Dec 4, 2018, 10:53:14 AM
    Author     : HaYang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="public/publicheader.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EMMC Usage</title>
        <%@include file="public/publiccss.jsp" %>
    </head>
    <body>
        <div class="container">
            <c:set value="class='active'" var="navEmmcUsageStyle"></c:set>
            <%@include file="public/nav.jsp" %>
            <%@include file="public/commselected.jsp" %>
        </div>
        <br/>
        
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div id="demochart1" style="height:600px"></div>
                </div>
            </div> 
        </div>
        <%@include file="public/publicjs.jsp" %>
        <script type="text/javascript" src="js/emmcusagechart.js"> </script>
        <script type="text/javascript" src="js/commselected_manager.js"> </script>
               
        <script type="text/javascript">
            var optionSelected = {
                indexType: "emmcusage",
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
                indexType: "emmcusage",
                projectNameDivId: "dcProjectName",
                variantNameDivId: "dcPorjectVariant",
                variantDefaultStr: "cVariant",
                versionDivId: "dcVersionSelect",
                versionDefaultStr: "cVersion",
                dataIndexDivId: "dindexCmpSelect",
                dataIndexDefaultStr: "cTest",
                enableAvg: false
            };
            
            var versionCmp = [];
            
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
                
                versionCmp[0] = inputData[2].value;
                versionCmp[1] = inputData[6].value;
                //console.log(versionCmp);

               $.ajax({
                   type: "post",
                   asyc: false,
                   url:"TestDataGet?dataType=EmmcUsageOne",
                   data: inputData,
                   success: function(result){
                       var jsonParse = JSON.parse(result);

                       if (jsonParse.length === 4)
                       {                         
                            diskOptionEMMC.series[0].data = [jsonParse[0].use, jsonParse[1].use, jsonParse[2].use, jsonParse[3].use];
                            diskOptionEMMC.series[1].data = [jsonParse[0].free, jsonParse[1].free, jsonParse[2].free, jsonParse[3].free];
  
                            loadEmmcUsageChart("demochart1");
                       }
                   }
               });
            }

            function loadDataCompare(){
                var inputData = $('#form1').serializeArray();

                //console.log(inputData);
                for (var i = 0; i < inputData.length; i ++)
                {
                    if (inputData[i].value.length === 0)
                    {
                        console.log("Please select two version.");
                        alert("Please select check info");
                        return ;
                    }
                }
                
                versionCmp[0] = inputData[2].value;
                versionCmp[1] = inputData[6].value;
                //console.log(versionCmp);
                
               $.ajax({
                   type: "post",
                   asyc: false,
                   url:"TestDataGet?dataType=EmmcUsageTwo",
                   data: inputData,
                   success: function(result){
                       //console.log(result);

                       var jsonParse = JSON.parse(result);
                       
                       if (jsonParse.length === 8)
                       {
                            //console.log(versionCmp);
                           
                            diskOptionEMMCCmp.series[0].data = [jsonParse[0].use, jsonParse[1].use, jsonParse[2].use, jsonParse[3].use];
                            diskOptionEMMCCmp.series[1].data = [jsonParse[4].use, jsonParse[5].use, jsonParse[6].use, jsonParse[7].use];
                            diskOptionEMMCCmp.series[0].name = versionCmp[0];
                            diskOptionEMMCCmp.series[1].name = versionCmp[1] + " ";
                            
                            loadEmmcUsageChartCmp("demochart1");
                        }
                   }
               });
            }         
        </script>
    </body>
</html>
