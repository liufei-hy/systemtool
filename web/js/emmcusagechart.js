/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var emmcTitile = {
    x: 'center',
    y: 'bottom',
    text: 'eMMC Usage',
    textStyle: {
        fontSize: 20,
    }
};

var emmcCmpTitile = {
    text: 'eMMC Usage Compare',
    x: 'center',
    y: 'bottom'
};

var emmcLegend = {
    itemGap: 100,
    itemWidth:100,
    itemHight:50,
    //x: 'right',
    y: 'top',
    //borderWidth: 4,
    padding: 10,    // [5, 10, 15, 20]
    textStyle: {color: 'black'}
    //data: ['use', 'free'],
    //show: true
};

var emmcCmpLegend = {
    itemGap: 100,
    itemWidth:100,
    itemHight:50,
    //x: 'right',
    y: 'top',
    //borderWidth: 4,
    padding: 10,    // [5, 10, 15, 20]
    textStyle: {color: 'black'},
    //data: ['used_left', 'used_right']
};

var emmcTooltip = {
    trigger: "axis"
};

var emmcxAxis = {
    data: ["etfs","mmc0","mmc1","mmc2"],
    splitLine: {
        show:false
    },
    axisLabel: {
        margin: 14,
        fontSize: 14,
        color: 'black'
    }
};

var emmcyAxis = {
    type: 'value',
    name: 'MB'
};

var emmcTool = {
    show:true,
    feature:{
        dataView:{show:true,readOnly:false},
        saveAsImage:{show:true}
    },
    y: 'bottom'
};

var emmcLabel = {
    normal: {
        formatter: function(data){
            var result = "";
            if (data.value > 0)
                {
                    result = data.value + "MB";
                }                                        
                return result;
        },
        color: 'rgba(0,0,0,1)',
        show: true,
        position: 'insideTop',
        fontSize: 14
        //fontWeight: 100
    }
};

var diskOptionEMMC = {
        toolbox: emmcTool,
        title: emmcTitile,
        legend: emmcLegend,
        tooltip: emmcTooltip,
        xAxis: emmcxAxis,
        yAxis: emmcyAxis,
        series :[
            {
           name:'use',
           type:'bar',
            stack: 'Usage',
               label: emmcLabel,
           itemStyle :{
                    normal:{color: 'rgba(238,106,80,1)'}
                },
                data:[]
          },{
            name:'free',
            type:'bar',
            stack: 'Usage',
                label: emmcLabel,
            itemStyle :{
                    normal:{color:'rgba(0,205,102,1)'}
                },
                data:[]
         }
      ]
};

var diskOptionEMMCCmp = {
       toolbox: emmcTool,
       legend: emmcCmpLegend,
       tooltip : emmcTooltip,
       title: emmcCmpTitile,
       xAxis: emmcxAxis,
       yAxis: emmcyAxis,
       series :[
            {
            name:'use',
            type:'bar',
                barGap: 0,
                label: emmcLabel,
           itemStyle :{
                    normal:{color: 'rgba(238,106,80,0.9)'}
                },
                data:[]
          },{
            name:'free',
            type:'bar',
                barGap: 0,
                label: emmcLabel,
            itemStyle :{
                    normal:{color:'rgba(0,205,102,0.9)'}
                },
                data:[]
         }
      ]
};

var diskOptionEMMC;

var diskOptionEMMCCmp;

function loadEmmcUsageChart(divEMMC)
{
    //switchCmpAndNormal(0);
    
    diskChartEMMC = echarts.init(document.getElementById(divEMMC));
       
    diskChartEMMC.setOption(diskOptionEMMC, true);
       
     window.addEventListener("resize", function(){
        diskChartEMMC.resize();
     });
}

function loadEmmcUsageChartCmp(divEMMCCmp)
{
    //switchCmpAndNormal(1);
    
    diskChartEMMCCmp = echarts.init(document.getElementById(divEMMCCmp));
    
    diskChartEMMCCmp.setOption(diskOptionEMMCCmp, true);
        
    window.addEventListener("resize", function(){
        diskChartEMMCCmp.resize();
    });
}
