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
        <title>Boot App Time</title>
        <%@include file="public/publiccss.jsp" %>
    </head>
    <body>
        <div class="container">
            <c:set value="class='active'" var="navBootTimeStyle"></c:set>
            <%@include file="public/nav.jsp" %>
            <%@include file="public/commselected.jsp" %>
        </div>
        <br/>
        <div id="demochart1" style="height:1200px"></div>
        <div id="demochart2" style="height:2400px"></div>
        <%@include file="public/publicjs.jsp" %>
        <script type="text/javascript" src="js/boottimechart.js"> </script>
        <script type="text/javascript" src="js/commselected_manager.js"> </script>
        
        <script type="text/javascript">
            var optionSelected = {
                indexType: "boottime",
                projectNameDivId: "dProjectName",
                variantNameDivId: "dPorjectVariant",
                variantDefaultStr: "Variant",
                versionDivId: "dversionSelect",
                versionDefaultStr: "Version",
                dataIndexDivId: "dindexSelect",
                dataIndexDefaultStr: "Test",
                callback: function(project, variant, ver){
                    //sysBootTimeVersionSelected = project + "_" + variant + "_" + ver;
                },
                enableAvg: false
            };
            
            var optionSelectedCmp = {
                indexType: "boottime",
                projectNameDivId: "dcProjectName",
                variantNameDivId: "dcPorjectVariant",
                variantDefaultStr: "cVariant",
                versionDivId: "dcVersionSelect",
                versionDefaultStr: "cVersion",
                dataIndexDivId: "dindexCmpSelect",
                dataIndexDefaultStr: "cTest",
                callback: function(project, variant, ver){
                    //sysBootTimeVersionSelectedCmp = project + "_" + variant + "_" + ver;
                },
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

            function loadData(){
                var inputData = $('#form1').serializeArray();
                
                //Save Version Info
                sysBootTimeVersionSelected = GetProjectVariantVersionInfo(optionSelected);

                for (var i = 0; i < inputData.length - 4; i ++)
                {
                    if (inputData[i].value.length === 0)
                    {
                        console.log("Please select version.");
                        alert("Please select check info");
                        return ;
                    }
                }

                document.getElementById("demochart2").style.display="none";
                document.getElementById("demochart1").style.display="block"; 

               $.ajax({
                   type: "post",
                   asyc: false,
                   url:"TestDataGet?dataType=BootTimeOne",
                   data: inputData,
                   success: function(result){
                       //console.log(result);

                       var jsonParse = JSON.parse(result);

                       sysBootTimeUserData.userData = jsonParse.appInfos;
                       sysBootTimeOption.yAxis.data = jsonParse.appNames;
                       sysBootTimeOption.series[0].data = jsonParse.appStarts;
                       sysBootTimeOption.series[1].data = jsonParse.appPosts;
                       sysBootTimeOption.series[2].data = jsonParse.appRuns;
                       sysBootTimeOption.series[3].data = jsonParse.appStops;

                       loadBootTimeChart('demochart1', 0);
                   }
               });
            }

            function loadDataCompare(){
                var inputData = $('#form1').serializeArray();
                //console.log(inputData.length);
                //Save Version Info
                sysBootTimeVersionSelectedCmp = GetProjectVariantVersionInfo(optionSelectedCmp);;

                for (var i = 0; i < inputData.length; i ++)
                {
                    if (inputData[i].value.length === 0)
                    {
                        console.log("Please select two version.");
                        alert("Please select check info");
                        return ;
                    }
                }        
                document.getElementById("demochart1").style.display="none";
                document.getElementById("demochart2").style.display="block";
               $.ajax({
                   type: "post",
                   asyc: false,
                   url:"TestDataGet?dataType=BootTimeTwo",
                   data: inputData,
                   success: function(result){
                       //console.log(result);

                       var jsonParse = JSON.parse(result);

                       sysBootTimeOptionCompare.yAxis.data = jsonParse.appNames;
                       sysBootTimeOptionCompare.series[0].data = jsonParse.appStarts;
                       sysBootTimeOptionCompare.series[1].data = jsonParse.appPosts;
                       sysBootTimeOptionCompare.series[2].data = jsonParse.appRuns;
                       sysBootTimeOptionCompare.series[3].data = jsonParse.appStops;
                       sysBootTimeUserDataCmp1.userData = jsonParse.appInfos;

                       sysBootTimeOptionCompare.series[4].data = jsonParse.appStartsCmp;
                       sysBootTimeOptionCompare.series[5].data = jsonParse.appPostsCmp;
                       sysBootTimeOptionCompare.series[6].data = jsonParse.appRunsCmp;
                       sysBootTimeOptionCompare.series[7].data = jsonParse.appStopsCmp;
                       sysBootTimeUserDataCmp2.userData = jsonParse.appInfosCmp;
                       
                       loadBootTimeChart('demochart2', 1);
                   }
               });
            }            
        </script>
    </body>
</html>
