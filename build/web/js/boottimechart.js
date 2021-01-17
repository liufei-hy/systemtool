/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */  
/* global echarts */

var sysBootTimeUserData = {
    userData: []
};

var sysBootTimeVersionSelected = "";

var sysBootTimeOption = {
    toolbox:{
        show:true,
        feature:{
            //mark:{show:true},
            dataView:{show:true,readOnly:false},
            //magicType:{show:true,type:['line']},
            //restore:{show:true},
            saveAsImage:{show:true}
        }
    },
    tooltip : {
        trigger: 'axis',
        
        formatter: function(datas){
            var res = sysBootTimeVersionSelected + '<br/>' + datas[0].name + '<br/>';
            
            var index = datas[0].dataIndex;
            //console.log("Index: " + datas[0].dataIndex);
            
            for (var i = 0; i < datas.length; i ++){
                var color = datas[i].color;
                res += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:' + color + '"></span>';         
                res += datas[i].seriesName + ':'
                    +  datas[i].data + 'ms' + '<br/>';
            }
            
            res += sysBootTimeUserData.userData[index];
            
            return res;
        },
        axisPointer : {
            type : 'shadow'
        }
    },
    legend: {
        data: ['Start', 'PostStart','Run','Stopped']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis:  {
        type: 'value',
        position: 'top'
    },
    yAxis: {
        type: 'category',
        data: [],
        axisLabel:{
            show: true,
            textStyle:{
                color:'#0000FF',
                fontSize:13
            }
        }
    },
    series: [
        {
            name: 'Start',
            type: 'bar',
            stack: 'total',
            color: '#04DBF0',
            label: {
                normal: {
                    formatter: function(data){
                        var result = "";

                        if (data.value > 0)
                        {
                            result = data.value + "ms";
                        }                                        
                        return result;
                    },
                    color: '#000000',
                    show: true,
                    position: 'insideTop'
                }
            },
            itemStyle: {
                normal: {
                    barBorderWidth: 1,
                    barBorderColor:'rgba(0,0,0,1)'
                }
            },
            data: []
        },
        {
            name: 'PostStart',
            type: 'bar',
            stack: 'total',
            //color: '#C8F29D',
            color: 'rgba(200,242,157,1)',
            //color: 'hsla(300,100%,50%,1)',
            label: {
                normal: {
                    formatter: function(data){
                        var result = "";

                        if (data.value > 0)
                        {
                            result = data.value + "ms";
                        }                                        
                        return result;
                    },
                    color: '#000000',
                    show: true,
                    position: 'insideTop'
                }
            },
            itemStyle: {
                normal: {
                    barBorderWidth: 1,
                    barBorderColor:'rgba(0,0,0,1)'
                }
            },
            data: []
        },
        {
            name: 'Run',
            type: 'bar',
            stack: 'total',
            color: '#F2C57C',
            label: {
                normal: {
                    formatter: function(data){
                        var result = "";

                        if (data.value > 0)
                        {
                            result = data.value + "ms";
                        }                                        
                        return result;
                    },
                    color: '#000000',
                    show: true,
                    position: 'insideTop'
                }
            },
            itemStyle: {
                normal: {
                    barBorderWidth: 1,
                    barBorderColor:'rgba(0,0,0,1)'
                }
            },
            data: []
        },
        {
            name: 'Stopped',
            type: 'bar',
            stack: 'total',
            color: '#B0C4DE',
            label: {
                normal: {
                    formatter: function(data){
                        var result = "";

                        if (data.value > 0)
                        {
                            result = data.value + "ms";
                        }                                        
                        return result;
                    },
                    color: '#000000',
                    show: true,
                    position: 'insideTop'
                }
            },
            itemStyle: {
                normal: {
                    barBorderWidth: 1,
                    barBorderColor:'rgba(0,0,0,1)'
                }
            },
            data: []
        }
    ]
};

var itemBorderColor='rgba(0,0,255,1)';
var itemBorderColorCmp='rgba(255,0,0,1)';

var sysBootTimeUserDataCmp1 = {
    userData: []
};

var sysBootTimeUserDataCmp2 = {
    userData: []
};

var sysBootTimeVersionSelectedCmp = "";

var sysBootTimeOptionCompare = {
    toolbox:{
        show:true,
        feature:{
            //mark:{show:true},
            dataView:{show:true,readOnly:false},
            //magicType:{show:true,type:['line']},
            //restore:{show:true},
            saveAsImage:{show:true}
        }
    },
    tooltip : {
        trigger: 'axis',
        formatter: function(datas){
            var res = datas[0].name + '<br/>';
            
            var index = datas[0].dataIndex;
            //console.log("Index: " + index);
            
            res += '<span style="color:' + itemBorderColor + '">' + sysBootTimeVersionSelected + '</span> <br/>';
            for (var i = 0; i < 4; i ++){
                var color = datas[i].color;
                res += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:' + color + '"></span>';         
                res += datas[i].seriesName + ':'
                    +  datas[i].data + 'ms' + '<br/>';
            }
            res += sysBootTimeUserDataCmp1.userData[index];
            
            res += '<span style="color:' + itemBorderColorCmp + '">' + sysBootTimeVersionSelectedCmp + '</span> <br/>';
            for (var i = 4; i < datas.length; i ++){
                var color = datas[i].color;
                res += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:' + color + '"></span>';         
                res += datas[i].seriesName + ':'
                    +  datas[i].data + 'ms' + '<br/>';
            }
            res += sysBootTimeUserDataCmp2.userData[index];
            return res;
        },
        axisPointer : {
            type : 'shadow'
        }
    },
    legend: {
        data: ['Start', 'PostStart','Run','Stopped']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis:  {
        type: 'value',
        position: 'top'
    },
    yAxis: {
        type: 'category',
        data: [],
        axisLabel:{
            show: true,
            textStyle:{
                color:'#0000FF',
                fontSize:13
            }
        }
    },
    series: [
        {
            name: 'Start',
            type: 'bar',
            stack: 'total',
            color: '#04DBF0',
            label: {
                normal: {
                    formatter: function(data){
                        var result = "";

                        if (data.value > 0)
                        {
                            result = data.value + "ms";
                        }                                        
                        return result;
                    },
                    color: '#000000',
                    show: true,
                    position: 'insideTop'
                }
            },
            itemStyle: {
                normal: {
                    barBorderWidth: 1,
                    barBorderColor:itemBorderColor
                }
            },
            data: []
        },
        {
            name: 'PostStart',
            type: 'bar',
            stack: 'total',
            //color: '#C8F29D',
            color: 'rgba(200,242,157,1)',
            //color: 'hsla(300,100%,50%,1)',
            label: {
                normal: {
                    formatter: function(data){
                        var result = "";

                        if (data.value > 0)
                        {
                            result = data.value + "ms";
                        }                                        
                        return result;
                    },
                    color: '#000000',
                    show: true,
                    position: 'insideTop'
                }
            },
            itemStyle: {
                normal: {
                    barBorderWidth: 1,
                    barBorderColor:itemBorderColor
                }
            },
            data: []
        },
        {
            name: 'Run',
            type: 'bar',
            stack: 'total',
            color: '#F2C57C',
            label: {
                normal: {
                    formatter: function(data){
                        var result = "";

                        if (data.value > 0)
                        {
                            result = data.value + "ms";
                        }                                        
                        return result;
                    },
                    color: '#000000',
                    show: true,
                    position: 'insideTop'
                }
            },
            itemStyle: {
                normal: {
                    barBorderWidth: 1,
                    barBorderColor:itemBorderColor
                }
            },
            data: []
        },
        {
            name: 'Stopped',
            type: 'bar',
            stack: 'total',
            color: '#B0C4DE',
            label: {
                normal: {
                    formatter: function(data){
                        var result = "";

                        if (data.value > 0)
                        {
                            result = data.value + "ms";
                        }                                        
                        return result;
                    },
                    color: '#000000',
                    show: true,
                    position: 'insideTop'
                }
            },
            itemStyle: {
                normal: {
                    barBorderWidth: 1,
                    barBorderColor:itemBorderColor
                }
            },
            data: []
        },
        {
            name: 'Start',
            type: 'bar',
            stack: 'totalCompare',
            color: '#04DBF0',
            label: {
                normal: {
                    formatter: function(data){
                        var result = "";

                        if (data.value > 0)
                        {
                            result = data.value + "ms";
                        }                                        
                        return result;
                    },
                    color: '#000000',
                    show: true,
                    position: 'insideTop'
                }
            },
            itemStyle: {
                normal: {
                    barBorderWidth: 1,
                    barBorderColor:itemBorderColorCmp
                }
            },
            data: []
        },
        {
            name: 'PostStart',
            type: 'bar',
            stack: 'totalCompare',
            //color: '#C8F29D',
            color: 'rgba(200,242,157,1)',
            //color: 'hsla(300,100%,50%,1)',
            label: {
                normal: {
                    formatter: function(data){
                        var result = "";

                        if (data.value > 0)
                        {
                            result = data.value + "ms";
                        }                                        
                        return result;
                    },
                    color: '#000000',
                    show: true,
                    position: 'insideTop'
                }
            },
            itemStyle: {
                normal: {
                    barBorderWidth: 1,
                    barBorderColor:itemBorderColorCmp
                }
            },
            data: []
        },
        {
            name: 'Run',
            type: 'bar',
            stack: 'totalCompare',
            color: '#F2C57C',
            label: {
                normal: {
                    formatter: function(data){
                        var result = "";

                        if (data.value > 0)
                        {
                            result = data.value + "ms";
                        }                                        
                        return result;
                    },
                    color: '#000000',
                    show: true,
                    position: 'insideTop'
                }
            },
            itemStyle: {
                normal: {
                    barBorderWidth: 1,
                    barBorderColor:itemBorderColorCmp
                }
            },
            data: []
        },
        {
            name: 'Stopped',
            type: 'bar',
            stack: 'totalCompare',
            color: '#B0C4DE',
            label: {
                normal: {
                    formatter: function(data){
                        var result = "";

                        if (data.value > 0)
                        {
                            result = data.value + "ms";
                        }                                        
                        return result;
                    },
                    color: '#000000',
                    show: true,
                    position: 'insideTop'
                }
            },
            itemStyle: {
                normal: {
                    barBorderWidth: 1,
                    barBorderColor:itemBorderColorCmp
                }
            },
            data: []
        }
    ]
};

var BootTimeChart;

function loadBootTimeChart(divName, index)
{       
    BootTimeChart = echarts.init(document.getElementById(divName));
    
    if (0 === index)
    {   
        BootTimeChart.setOption(sysBootTimeOption, true);
    }
    else
    {
        BootTimeChart.setOption(sysBootTimeOptionCompare, true);
    } 
    window.addEventListener("resize", function(){
    BootTimeChart.resize();
});
}




