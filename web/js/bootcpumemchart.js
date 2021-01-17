/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var BootCpuMemOptionOne = {
    calculable: true,
    tooltip:{
        trigger:'axis'  
    },
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
    legend:{
        data:['cpu01','mem']
    },                
    xAxis: [
        {
            type: 'category',
            data: []
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: 'cpu01',
            min: 0,
            max: 100           
        },
        {
            type: 'value',
            name: 'mem',
            min: 0,
            max: 2048
        }
    ],
    dataZoom: [
        {
            type: 'inside',
            start: 0,
            end: 40
        },
        {
            start: 0,
            end: 40,
            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
            handleSize: '100%',
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }
    ],
    series: [
        {
            color: 'rgba(0,255,0,1)',
            name: 'cpu01',
            data: [],
            type: 'line',
            smooth: true
        },
        {
            color: 'rgba(0,255,0,1)',
            name: 'cpu01',
            data: [],
            type: 'line',
            smooth: true
        },
        {
            color: 'rgba(0,0,255,1)',
            name: 'mem',
            data: [],
            type: 'line',
            smooth: true,
            yAxisIndex: 1
        }
    ]
};

var BootCpuMemOptionTwo = {
    calculable: true,
    tooltip:{
        trigger:'axis'  
    },
    toolbox:{
        show:true,
        feature:{
            mark:{show:true},
            dataView:{show:true,readOnly:false},
            magicType:{show:true,type:['line']},
            restore:{show:true},
            saveAsImage:{show:true}
        }
    },
    legend:{
        data:['cpu01','cpu01Cmp','mem','memCmp']
    },                
    xAxis: [
        {
            type: 'category',
            data: []
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: 'cpu01',
            min: 0,
            max: 100
        },
        {
            type: 'value',
            name: 'mem',
            min: 0,
            max: 2048
        },
        {
            type: 'value',
            name: 'memCmp'
        },
        {
            type: 'value',
            name: 'cpu01Cmp'
        }
    ],
    dataZoom: [
        {
            type: 'inside',
            start: 0,
            end: 40
        },
        {
            start: 0,
            end: 40,
            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
            handleSize: '100%',
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }
    ],
    series: [
        {
            color: 'rgba(0,255,0,1)',
            name: 'cpu01',
            data: [],
            type: 'line',
            smooth: true
        },
        {
            color: 'rgba(0,255,0,1)',
            name: 'cpu01',
            data: [],
            type: 'line',
            smooth: true
        },
        {
            color: 'rgba(255,0,0,1)',
            name: 'cpu01Cmp',
            data: [],
            type: 'line',
            smooth: true
        },
        {
            color: 'rgba(255,0,0,1)',
            name: 'cpu01Cmp',
            data: [],
            type: 'line',
            smooth: true
        },
        {
            color: 'rgba(0,0,255,1)',
            name: 'mem',
            data: [],
            type: 'line',
            smooth: true,
            yAxisIndex: 1
        },
        {
            color: 'rgba(0,100,255,1)',
            name: 'memCmp',
            data: [],
            type: 'line',
            smooth: true,
            yAxisIndex: 1
        }
    ]
};


var BootCpuMemChart;

function loadBootCpuMemChart(divName, index)
{       
    BootCpuMemChart = echarts.init(document.getElementById(divName));
    
    if (0 === index)
    {   
        BootCpuMemChart.setOption(BootCpuMemOptionOne, true);
    }
    else
    {
        BootCpuMemChart.setOption(BootCpuMemOptionTwo, true);
    } 
    
    window.addEventListener("resize", function(){
        BootCpuMemChart.resize();
});
}
