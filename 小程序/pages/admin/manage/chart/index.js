import * as echarts from '../../../../ec-canvas/echarts';

const app = getApp();
const that=this;
Page({
  data:{

  }
  ,
  onShareAppMessage: function (res) {
    return {
      title: 'ECharts 可以在微信小程序中使用啦！',
      path: '/pages/index/index',
      success: function () { },
      fail: function () { }
    }
  },
  data: {
    ec: {
      onInit: initChart,
    }
  },

  
  onShow(options) {

    

    this.setData({
      
        "echeight":wx.getStorageSync('chartheight')
      
       
    })
    console.log(this.data.echeight)
    
      if(wx.getStorageSync('checkuserstyle')=="user"){
        var userid=wx.getStorageSync('user')[0].userid
        var useriddata={
          "userid":userid}
      } 
      else{
        
      
        var userid=wx.getStorageSync('chooseuser').userid
        console.log(userid)
         var useriddata={
        "userid":userid
      }
      }
      console.log(userid)
     
      var weights=[];
      var abdominal_girths=[];
      var blood_pressures=[];
      var fetal_hearts=[];
      var uterine_heights=[];
      var nums=[];
      wx.request({
        url: 'http://127.0.0.1:8080/show_usersearch_allpaper',
        data:useriddata,
         //远程javaee服务器上的url地址加请求
        // url: 'http://10.143.99.245:8080/show_adminindex',
        //要传递到远程去的json数据。
        timeout: 2000, //延时
        success: (result) => {//result.data就是从远程返回的数据
          
          
          if (result.data !=null){
             //把数据存到本地小程序缓存里面，名字叫user
             console.log(result.data)
            //  wx.setStorageSync('exam_paper', result.data)
             var i=0;
              while(i<result.data.length)
              {
              
               var weight=parseInt(result.data[i].weight);
               var  abdominal_girth=parseInt(result.data[i].abdominal_girth);
               var  blood_pressure=parseInt(result.data[i].blood_pressure);
               var  fetal_heart=parseInt(result.data[i].fetal_heart);
               var  uterine_height=parseInt(result.data[i].uterine_height);
               i++;
              weights.push(weight)
              abdominal_girths.push(abdominal_girth)
              blood_pressures.push(blood_pressure)
              fetal_hearts.push(fetal_heart)
              uterine_heights.push(uterine_height)
              nums.push("第"+i+"次")
            }
              // break;
              app.globalData.weights=weights
              app.globalData.abdominal_girths=abdominal_girths
              app.globalData.blood_pressures=blood_pressures
              app.globalData.fetal_hearts=fetal_hearts
              app.globalData.uterine_heights=uterine_heights
              app.globalData.nums=nums
              wx.setStorageSync('chartheight', i*5*100)
              
            }
            
      
            //  option.series[0].data=[weights[0],weights[1],weights[2],55,77,232,443]
            //  console.log(option.series[0].data)
          }  
        }
      )
     
   
    
    
    setTimeout(function () {

    }, 2000);
    
  }
});
let chart = null;

function initChart(canvas, width, height, dpr) {
  chart = echarts.init(canvas, null, {
    width: width,
    height: height,
    devicePixelRatio: dpr // new
  });
  canvas.setChart(chart);
  var option = {
    tooltip: {
      trigger: 'axis',
      axisPointer: {            // 坐标轴指示器，坐标轴触发有效
        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
      },
      confine: true
    },
    legend: {
      data: ['体重', '腹围', '血压',"胎心","宫高"]
    },
    grid: {
      left: 20,
      right: 20,
      bottom: 15,
      top: 40,
      containLabel: true
    },
    xAxis: [
      {
        
        type: 'value',
        axisLine: {
          lineStyle: {
            color: '#999'
          }
        },
        axisLabel: {
          color: '#666'
        }
      }
    ],
    yAxis: [
      {
        
        type: 'category',
        axisTick: { show: false },
        data: [],
        axisLine: {
          lineStyle: {
            color: '#999'
          }
        },
        axisLabel: {
          color: '#666'
        }
      }
    ],
    series: [
      {
        symbolMargin:50,
        name: '体重',
        type: 'bar',
        barWidth:30,
        label: {
          normal: {
            show: true,
            position: 'inside',
            
          }
        },
        data: [],
        itemStyle: {
          // emphasis: {
          //   color: '#37a2da'
          // }
        }
      },
      {
        name: '腹围',
        type: 'bar',
        barWidth:30,
        label: {
          normal: {
            show: true,
            position: 'inside',
            
          }
        },
        data: [],
        itemStyle: {
          // emphasis: {
          //   color: '#32c5e9'
          // }
        }
      },
      {
        name: '血压',
        type: 'bar',
        barWidth:30,
        label: {
          normal: {
            show: true,
            position: 'inside'
          }
        },
        data: [],
        itemStyle: {
          // emphasis: {
          //   color: '#67e0e3'
          // }
        }
      }
      ,
      {
        name: '胎心',
        type: 'bar',
        barWidth:30,
        label: {
          normal: {
            show: true,
            position: 'inside'
          }
        },
        data: [],
        itemStyle: {
          // emphasis: {
          //   color: '#67e0e3'
          // }
        }
      }
      ,
      {
        name: '宫高',
        type: 'bar',
        barWidth:30,
        label: {
          normal: {
            show: true,
            position: 'inside'
          }
        },
        data: [],
        itemStyle: {
          // emphasis: {
          //   color: '#67e0e3'
          // }
        }
      }
    ],
    
  };


  setInterval(function() {
  
  console.log(option.yAxis)
  option.yAxis[0].data=app.globalData.nums;
  option.series[0].data= app.globalData.weights;
  option.series[1].data= app.globalData.abdominal_girths,
  option.series[2].data= app.globalData.blood_pressures,
  option.series[3].data= app.globalData.fetal_hearts,
  option.series[4].data= app.globalData.uterine_heights;
  console.log(option.series[0].data[0] )
  chart.setOption(option, true);
  
}, 2000)

// var weights=that.getdata();
// console.log(that.getdata())
// option.series[0].data=weights


  
  return chart;
}







