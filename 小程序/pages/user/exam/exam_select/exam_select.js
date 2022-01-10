// pages/user/exam/exam_select/exam_select.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

    startTime: '08:00',
    endTime: '21:00',
    unit: 60,
    reserveUnit: 60,
    activedConst: 101,
    disabledConst: 102,
    unreserveTime: [],
    
    
  },

onSelectTime(e) {
    const { startTimeText, endTimeText } = e.detail;
    console.log(e.detail)
    this.setData({
        startTimeText,
        endTimeText,
    })
},
//预约新的时间
checktime(){
  
  var userid=wx.getStorageSync('user')[0].userid
  var doctorid=wx.getStorageSync('chooseuser').doctorid
  var startTime=this.data.startTimeText;
  var endTime=this.data.endTimeText;
  var staus="102";
 //去掉字符串所有标点符号
  var time =startTime.replace(/[\ |\~|\`|\!|\@|\#|\$|\%|\^|\&|\*|\(|\)|\-|\_|\+|\=|\||\\|\[|\]|\{|\}|\;|\:|\"|\'|\,|\<|\.|\>|\/|\?]/g,""); 
  console.log(userid)
  console.log(time)
  console.log(startTime)
  console.log(endTime)
  var newtime=[{
    timeid:time,
    doctorid:doctorid,
    startTime:startTime,
    endTime:endTime,
    staus:"102"
  }]
 


  wx.request({
    url: 'http://127.0.0.1:8080/insertdoctortime?doctorid='+doctorid+'&&timeid='+time+'&&startTime='+startTime+'&&endTime='+endTime+'&&staus='+staus+'&&userid='+
    userid+'',
     //远程javaee服务器上的url地址加请求
    // url: 'http://10.143.99.245:8080/show_adminindex',
     //要传递到远程去的json数据。
    timeout: 2000, //延时
    success: (result) => {//result.data就是从远程返回的数据
      if (result.data==true){

        console.log(result.data)
        wx.showModal({
          content:"预约成功！",
          showCancel:false,
         success(res){
          if (res.confirm) {
            wx.navigateBack({
              delta: -1
          });
          }
         }
        })
        
    
      }
      
    }
    ,
  })
}
  /**
   * 生命周期函数--监听页面加载
   */,
  onLoad: function (options) {

    var myDate=new Date();
    
    var time=myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate()+" "+
    myDate.getHours()+":"+myDate.getMinutes()+":"+myDate.getMinutes();
    console.log(time)    
    var doctorid=wx.getStorageSync('chooseuser').doctorid
    
    console.log(doctorid)
    wx.request({
      url: 'http://127.0.0.1:8080/show_doctortime?doctorid='+doctorid+'',
       //远程javaee服务器上的url地址加请求
      // url: 'http://10.143.99.245:8080/show_adminindex',
       //要传递到远程去的json数据。
      timeout: 2000, //延时
      success: (result) => {//result.data就是从远程返回的数据
        if (result.data.error==null){

          
          //获取初始时间和已经预约的时间
          let {unreserveTime}=this.data.unreserveTime;
          unreserveTime=result.data;
          var times=[{
            timeid:"0",
            doctorid:"0",
            startTime:"2000-01-01 00:00",
            endTime:time,
            staus:"102"
          }]
          unreserveTime.push(times)
          this.setData({
            'unreserveTime':times
          })
          console.log(unreserveTime)
          setTimeout(() => {
            this.setData({
                unreserveTime
            });
        }, 2000)

        }
        
      }
      ,
    })


    // 动态设置非服务事件
    

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },


  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})