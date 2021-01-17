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
            <c:set value="class='active'" var="navNavCpuUsageStyle"></c:set>
            <%@include file="public/nav.jsp" %>
            <%@include file="public/commselected.jsp" %>
        </div>
        <br/>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h3><p class="text-center" id="NavPage1" style="color:#FF0000"></p></h3>
                </div>
            </div>
        </div>
        <div class="col-md-12" id="demochart1" style="height:350px"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h3><p class="text-center" id="NavPage2" style="color:#FF0000"></p></h3>
                </div>
            </div>
        </div>
        <div class="col-md-12" id="demochart2" style="height:350px"></div> 
        
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h4>
                        <p class="text-left" id="TestCaseInfo">
                            路线:    公司与吉利成都基地之间 <br/>
                            测试过程：RITU导航 去吉利后返回；<br/>			
	                    高德导航 去吉利后返回<br/>	
                            详细过程：<br/>				
                        RITU<br/>			
                            2019/1/2 15:09	算路，到geely ,调整到3D模式	<br/>
                            2019/1/2 15:24	算路，回程 缩放比例尺，查看滚动地图等操作<br/>	
                            2019/1/2 15:27	从新自动算路<br/>	
                            2019/1/2 15:29	自动重新算路<br/>	
                            2019/1/2 15:31	自动重新算路<br/>	
                            2019/1/2 15:33	自动重新算路<br/>	
                            2019/1/2 15:34	自动重新算路<br/>	
                            2019/1/2 15:36	自动重新算路<br/>	
                            2019/1/2	取消导航<br/>		
                        AutoNavi<br/>			
                            2019/1/2 16:02	算路，开始导航<br/>	
                            2019/1/2 16:03	自动重新算路<br/>	
                            2019/1/2 16:11	自动重新算路	因为高德发现在修路，所以在不断重新算路<br/>
                            2019/1/2 16:14	自动重新算路<br/>	
                            2019/1/2 16:14	自动重新算路<br/>	
                            2019/1/2 16:15	自动重新算路<br/>	
                                    自动重新算路<br/>	
                                    自动重新算路<br/>		
                       返程算路<br/>		
                            2019/1/2 16:19	自动重新算路 缩放比例尺 滚动地图<br/>	
                            2019/1/2 16:21	自动重新算路<br/>
                            2019/1/2 16:26	自动重新算路<br/>	
                            2019/1/2 16:29	自动重新算路<br/>	
                            2019/1/2	取消导航<br/>
                        </p>
                    </h4>
                </div>
            </div>
        </div>

        <%@include file="public/publicjs.jsp" %>
        
        <script type="text/javascript" src="js/navcpuusagechart.js"></script>
        <script type="text/javascript" src="js/commselected_manager.js"> </script>
        
        <script type="text/javascript">
            var optionSelected = {
                indexType: "navcpuusage",
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
                indexType: "navcpuusage",
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
            
            var firstVersionInfo = '';
            var secondVersionInfo = '';

            function loadData(){
                var inputData = $('#form1').serializeArray();

                firstVersionInfo = GetProjectVariantVersionInfo(optionSelected);

                for (var i = 0; i < inputData.length - 2; i ++)
                {
                    if (inputData[i].value.length === 0)
                    {
                        console.log("Please select version.");
                        alert("Please select check info");
                        return ;
                    }
                }

                //document.getElementById("demochart2").style.display="none";
                //document.getElementById("demochart1").style.display="block"; 

               $.ajax({
                   type: "post",
                   asyc: false,
                   url:"TestDataGet?dataType=NavCpuUsageOne",
                   data: inputData,
                   success: function(result){
                       //console.log(result);

                       var jsonParse = JSON.parse(result);
                       
                       //console.log(jsonParse.dbRes);                       
                       var cpuStatsRes = jsonParse.cpuStats;
                       
                       if (cpuStatsRes.length === 6)
                       {
                            var cpuStatsMsg = firstVersionInfo + '<br/>' +
                                              'Cpu0: [Min: ' + cpuStatsRes[0] +
                                                     ',Max: ' + cpuStatsRes[1] +
                                                     ',Avg: ' + cpuStatsRes[2] +
                                                     ']<br/>' +
                                              'Cpu1: [Min: ' + cpuStatsRes[3] +
                                                     ',Max: ' + cpuStatsRes[4] +
                                                     ',Avg: ' + cpuStatsRes[5] +
                                                     ']';
                                              
                                              
                            $("#NavPage1").html(cpuStatsMsg);
                            //console.log(cpuStatsMsg);
                       }
                                         

                       NavCpuUsageOptionOne.xAxis[0].data = jsonParse.times;
                       NavCpuUsageOptionOne.series[0].data = jsonParse.cpu0s;
                       NavCpuUsageOptionOne.series[1].data = jsonParse.cpu1s;
                       
                       loadNavCpuUsageChart('demochart1');
                   }
               });
            }

            function loadDataCompare(){
                var inputData = $('#form1').serializeArray();

                //console.log(inputData.length);
                
                secondVersionInfo = GetProjectVariantVersionInfo(optionSelectedCmp);

                for (var i = 0; i < inputData.length; i ++)
                {
                    if (inputData[i].value.length === 0)
                    {
                        console.log("Please select two version.");
                        alert("Please select check info");
                        return ;
                    }
                }        
                //document.getElementById("demochart1").style.display="none";
                //document.getElementById("demochart2").style.display="block";
               $.ajax({
                   type: "post",
                   asyc: false,
                   url:"TestDataGet?dataType=NavCpuUsageTwo",
                   data: inputData,
                   success: function(result){
                       //console.log(result);

                       var jsonParse = JSON.parse(result);
                       
                       //console.log(jsonParse.cpuStats);
                       var cpuStatsRes = jsonParse.cpuStats;
                       
                       if (cpuStatsRes.length === 6)
                       {
                            var cpuStatsMsg = secondVersionInfo + '<br/>' +
                                              'Cpu0: [Min: ' + cpuStatsRes[0] +
                                                     ',Max: ' + cpuStatsRes[1] +
                                                     ',Avg: ' + cpuStatsRes[2] +
                                                     ']<br/>' +
                                              'Cpu1: [Min: ' + cpuStatsRes[3] +
                                                     ',Max: ' + cpuStatsRes[4] +
                                                     ',Avg: ' + cpuStatsRes[5] +
                                                     ']';
                                              
                                              
                            $("#NavPage2").html(cpuStatsMsg);
                            //console.log(cpuStatsMsg);
                       }

                       NavCpuUsageOptionTwo.xAxis[0].data = jsonParse.times;
                       NavCpuUsageOptionTwo.series[0].data = jsonParse.cpu0sCmp;
                       NavCpuUsageOptionTwo.series[1].data = jsonParse.cpu1sCmp;
                       
                       loadNavCpuUsageChartTwo('demochart2');
                   }
               });
            }
        </script>
    </body>
</html>
