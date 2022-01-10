// pages/userinfo/userinfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
      user:Object[{}],
      checkuserstyle:""
      ,
     link:[
      {
        id:0,
        name:"我的报告",
        src:"/icon/附件.png",
        url:"/pages/user/exam/exam_report/exam_report"
      },
      {
        id:1,
        name:"我的问诊",
        src:"/icon/问诊.png",
        url:"/pages/admin/manage/manage_user/manage_user"
      },
      {
        id:2,
        name:"信息修改",
        src:"/icon/编辑.png",
        url:"/pages/updateuser/updateuser"
      }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
 
  onLoad: function (options) {
    console.log(wx.getStorageSync('checkuserstyle'));
    
   var checkuserstyle=wx.getStorageSync('checkuserstyle');
    var user=null;
    switch(checkuserstyle){
    case "admin":
      { 
        console.log(wx.getStorageSync('admin'));
        user=wx.getStorageSync('admin');
        
          this.setData({
            user
          });
          
          break;
      }
      case "doctor":
        {
          console.log(wx.getStorageSync('doctor'));
           user=wx.getStorageSync('doctor');
          this.setData({user});
          break;
        }
        case "user":
          {
            console.log(wx.getStorageSync('user'));
             user=wx.getStorageSync('user');
            this.setData({user});
            break;
          }
          
    }
   this.setData({
    checkuserstyle
   })
    console.log(this.data.user)
    
  },

  exit(){
    wx.clearStorage()
    wx.reLaunch({
      url: '../login/login',
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */


  changeData:function(){
    console.log("刷新")
    var that = this;
    that.onLoad();
  },
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