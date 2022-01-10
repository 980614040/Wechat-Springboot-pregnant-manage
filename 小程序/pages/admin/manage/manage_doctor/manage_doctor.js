// pages/admin/manage/manage_doctor/manage_doctor.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    doctors:[
      // {
      //   id:0,
      //   name:"医生管理",
      //   src:"/icon/医生团队.png",
      //   url:"/pages/admin/manage/manage_doctor/manage_doctor"
      // },
      // {
      //   id:1,
      //   name:"用户管理",
      //   src:"/icon/用户.png",
      //   url:"/pages/admin/manage/manage_user/manage_user"
      // },
      // {
      //   id:2,
      //   name:"数据管理",
      //   src:"/icon/列表.png",
      //   url:"/pages/admin/manage/manage_data/manage_data"
      // }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(wx.getStorageSync('searchdoctorlist')!="")
    {
      console.log("search")
      console.log(wx.getStorageSync('searchdoctorlist'))
      this.setData({
      "doctors":wx.getStorageSync('searchdoctorlist')
      })
      wx.setStorageSync('searchdoctorlist', "")
    }
    else{
    wx.request({
      url: 'http://127.0.0.1:8080/doctorlist',
       //远程javaee服务器上的url地址加请求
      // url: 'http://10.143.99.245:8080/show_adminindex',
      //要传递到远程去的json数据。
      timeout: 2000, //延时
      success: (result) => {//result.data就是从远程返回的数据
        
        
        if (result.data !=null){
           //把数据存到本地小程序缓存里面，名字叫user
           wx.setStorageSync('doctorlist', result.data)
           
           let {doctors}=this.data;
           doctors=result.data;
           console.log(doctors);
           this.setData({
             doctors
           })
           
         
          
        }
        
      },
    })
    }
    
    console.log(this.data)
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  changeData:function(){
    console.log("刷新")
    var that = this;
    that.onLoad();
   
  },
  search:function(key){
    var username=key;
    var that = this;
    console.log(username)
    if(that.data.checkuserstyle!="doctor"){
      var doctorlist= wx.getStorageSync('doctorlist');
      var newdoctorlist=[];
      console.log(doctorlist)
      for(var i=0;i<doctorlist.length;i++)
      {
        
        if(doctorlist[i].doctorname==username)
        {
          newdoctorlist.push(doctorlist[i])
          console.log("newdoctorlist:")
          console.log(newdoctorlist)
          wx.setStorageSync('searchdoctorlist',newdoctorlist )

          
        }
      }
      
     
    
  }
     wx.redirectTo({
      "url":"../manage_doctor/manage_doctor"
    })
  }
  ,
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