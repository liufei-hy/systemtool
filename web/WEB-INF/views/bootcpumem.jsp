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
        <title>Boot Cpu Mem</title>
        <%@include file="public/publiccss.jsp" %>
    </head>
    <body>
        <div class="container">
            <c:set value="class='active'" var="navBootCpuMemStyle"></c:set>
            <%@include file="public/nav.jsp" %>
            <%@include file="public/commselected.jsp" %>
        </div>
        <br/>
        <div id="demochart1" style="height:450px"></div>
        <div id="demochart2" style="height:450px"></div>
        <%@include file="public/publicjs.jsp" %>
        
        <script type="text/javascript" src="js/bootcpumemchart.js"></script>
        <script type="text/javascript" src="js/commselected_manager.js"> </script>
        
        <script type="text/javascript">
            var optionSelected = {
                indexType: "bootcpumem",
                projectNameDivId: "dProjectName",
                variantNameDivId: "dPorjectVariant",
                variantDefaultStr: "Variant",
                versionDivId: "dversionSelect",
                versionDefaultStr: "Version",
                dataIndexDivId: "dindexSelect",
                dataIndexDefaultStr: "Test",
                enableAvg: true
            };
            
            var optionSelectedCmp = {
                indexType: "bootcpumem",
                projectNameDivId: "dcProjectName",
                variantNameDivId: "dcPorjectVariant",
                variantDefaultStr: "cVariant",
                versionDivId: "dcVersionSelect",
                versionDefaultStr: "cVersion",
                dataIndexDivId: "dindexCmpSelect",
                dataIndexDefaultStr: "cTest",
                enableAvg: true
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
                   url:"TestDataGet?dataType=BootCpuMemOne",
                   data: inputData,
                   success: function(result){
                       //console.log(result);

                       var jsonParse = JSON.parse(result);
                       
                       //console.log(jsonParse.dbRes);

                       BootCpuMemOptionOne.xAxis[0].data = jsonParse.times;
                       BootCpuMemOptionOne.series[0].data = jsonParse.cpu0s;
                       BootCpuMemOptionOne.series[1].data = jsonParse.cpu1s;
                       BootCpuMemOptionOne.series[2].data = jsonParse.mems;
                       
                       loadBootCpuMemChart('demochart1', 0);
                   }
               });
            }

            function loadDataCompare(){
                var inputData = $('#form1').serializeArray();

                //console.log(inputData.length);

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
                   url:"TestDataGet?dataType=BootCpuMemTwo",
                   data: inputData,
                   success: function(result){
                       //console.log(result);

                       var jsonParse = JSON.parse(result);

                       BootCpuMemOptionTwo.xAxis[0].data = jsonParse.times;
                       BootCpuMemOptionTwo.series[0].data = jsonParse.cpu0s;
                       BootCpuMemOptionTwo.series[1].data = jsonParse.cpu1s;
                       BootCpuMemOptionTwo.series[2].data = jsonParse.cpu0sCmp;
                       BootCpuMemOptionTwo.series[3].data = jsonParse.cpu1sCmp;
                       BootCpuMemOptionTwo.series[4].data = jsonParse.mems;
                       BootCpuMemOptionTwo.series[5].data = jsonParse.memsCmp;

                       loadBootCpuMemChart('demochart2', 1);
                   }
               });
            }
        </script>
    </body>
</html>
