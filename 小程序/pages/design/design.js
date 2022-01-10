// pages/design/design.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title:"欢迎使用孕妇体检系统",
    userid:"",
    pwd:"",
    checkuserstyle:""
  },
  checkuserstyle(e){
    //判定选择哪种身份
    
    this.setData({
      
      checkuserstyle:e.detail.value
    })
  },
  formSubmit:function(ex){
    
    
    var user_code = ex.detail.value.user_code
    var user_pwd = ex.detail.value.user_pwd
    
    //也可以合并成下面一句话：
    var data = {"user_code": ex.detail.value.user_code,
                "user_pwd": ex.detail.value.user_pwd
    }
    console.log(this.data.checkuserstyle)

    //登录判定
    switch (this.data.checkuserstyle)
    {
      
      case "":{
        wx.showModal({
                content:"请选择身份！",
                showCancel:false
              })
              break;}
      case "admin":{
      //到远程进行验证。
      console.log("case:admin");
      wx.request({
        url: 'http://127.0.0.1:8080/admindesign',
         //远程javaee服务器上的url地址加请求
        // url: 'http://10.143.99.245:8080/show_adminindex',
        data: data, //要传递到远程去的json数据。
        timeout: 2000, //延时
        success: (result) => {//result.data就是从远程返回的数据
          console.log("result.data:"+result.data);
          if (result.data ==true){
             //把数据存到本地小程序缓存里面，名字叫user
             wx.setStorageSync('admin', result.data)
            wx.navigateTo({
              url: '../login/login'
            
            })     
           
            
          }
          else{
            wx.showModal({
              content:"注册失败",
              showCancel:false
            })
          }
        },
      })
        break;
      
        break;
      }
      case "doctor":{
        console.log("case:doctor");
      //到远程进行验证。
      
      wx.request({
        url: 'http://127.0.0.1:8080/doctordesign',
         //远程javaee服务器上的url地址加请求
        // url: 'http://10.143.99.245:8080/show_adminindex',
        data: data, //要传递到远程去的json数据。
        timeout: 2000, //延时
        success: (result) => {//result.data就是从远程返回的数据
          console.log(result.data)
          if (result.data ==true){
  
            console.log(result.data);
             //把数据存到本地小程序缓存里面，名字叫user
             wx.setStorageSync('doctor', result.data)
            wx.navigateTo({
              url: '../login/login'
            
            })     
           
            
          }
          else{
            wx.showModal({
              content:"注册失败",
              showCancel:false
            })
          }
        },
      })
        break;
      }
      case "user":{
        
        //到远程进行验证。
        
        wx.request({
          url: 'http://127.0.0.1:8080/userdesign',
           //远程javaee服务器上的url地址加请求
          // url: 'http://10.143.99.245:8080/show_adminindex',
          data: data, //要传递到远程去的json数据。
          timeout: 2000, //延时
          success: (result) => {//result.data就是从远程返回的数据
            if (result.data ==true){
    
              console.log(result.data);
              wx.setStorageSync('user', result.data) //把数据存到本地小程序缓存里面，名字叫user
              wx.navigateTo({
                url: '../login/login'
              
              })     
            
            }
            else{
              wx.showModal({
                content:"注册失败",
                showCancel:false
              })
            }
          }
          ,
        })
        break;
      }
      
      
    }
    // if(this.data.checkuserstyle=="")
    // {
      
    // }else{
    // if(this.data.checkuserstyle=="admin"){}
    //  if(this.data.checkuserstyle=="doctor"){}

    //   if(this.data.checkuserstyle=="user"){}
    //     else {
    //       wx.showModal({
    //         content:"密码或账户错误！",
    //         showCancel:false
          
          
    //       })
    //     }}
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
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