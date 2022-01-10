// pages/admin/admin_home/admin_home.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    
    link:[
     
    ]
  },
//自定义事件 接受子组件数据
  // linkitemChange(e){
  //   console.log(e)
    
  //   const{index}=e.detail;
  //   console.log(e.detail)
  //     let {link}=this.data;
  //   //  //foreach判断每个下标和index是否相等，选择进入的页面。
  //     link.forEach((v,i)=>i===index?wx.navigateTo({
  //        url: this.data.link[i].url
  //      }):0)
  // },



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
    var that=this;
    wx.getStorage({
      key: 'checkuserstyle',
      success (res) {
        console.log(res.data+"用户进入")
        switch (res.data){
        case "admin":
        {
          console.log("setdata")
          that.setData({
            link:[
              {
                id:0,
                name:"医生管理",
                src:"/icon/医生团队.png",
                url:"/pages/admin/manage/manage_doctor/manage_doctor"
              },
              {
                id:1,
                name:"用户管理",
                src:"/icon/用户.png",
                url:"/pages/admin/manage/manage_user/manage_user"
              },
              {
                id:2,
                name:"数据管理",
                src:"/icon/列表.png",
                url:"/pages/admin/manage/manage_data/manage_data"
              }
            ]
      
            
          })
          
          break;
        }
        case "doctor":
        {
          that.setData({
            link:[
              {
                id:3,
                name:"用户管理",
                src:"/icon/用户.png",
                url:"/pages/admin/manage/manage_user/manage_user"
              },
              {
                id:4,
                name:"数据管理",
                src:"/icon/列表.png",
                url:"/pages/admin/manage/manage_data/manage_data"
              }
              ,
              {
                id:5,
                name:"体检查询",
                src:"/icon/列表.png",
                url:"/pages/user/exam/exam_report/exam_report"
              }
            ]
          })
          break;
        }
        case "user":
        {
          
          that.setData({
            link:[
              {
                id:6,
                name:"医生列表",
                src:"/icon/医生团队.png",
                url:"/pages/admin/manage/manage_doctor/manage_doctor"
              },
              {
                id:7,
                name:"体检预约",
                src:"/icon/问诊.png",
                url:"/pages/user/exam/exam_appointment/exam_appointment"
              }
              ,
              {
                id:8,
                name:"体检查询",
                src:"/icon/列表.png",
                url:"/pages/user/exam/exam_report/exam_report"
              }
            ]
          })
          break;
        }
        }
      }
    })
    
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