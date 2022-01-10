// pages/admin/manage/manage_user/manage_user.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    if(wx.getStorageSync('searchuserlist')!="")
    {
      console.log("search")
      console.log(wx.getStorageSync('searchuserlist'))
      this.setData({
      "users":wx.getStorageSync('searchuserlist')
      })
      wx.setStorageSync('searchuserlist', "")
    }
    else{
    var checkuserstyle=wx.getStorageSync('checkuserstyle');
    if(checkuserstyle=="doctor"){
      var data = {
        "doctorid": wx.getStorageSync('doctor')[0].doctorid,
}

      wx.request({
      url: 'http://127.0.0.1:8080/doctorsearchuserlist',
       //远程javaee服务器上的url地址加请求
      // url: 'http://10.143.99.245:8080/show_adminindex',
      //要传递到远程去的json数据。
      data:data,
      timeout: 2000, //延时
      success: (result) => {//result.data就是从远程返回的数据
        
        
        if (result.data !=null){
           //把数据存到本地小程序缓存里面，名字叫user
           wx.setStorageSync('userlist', result.data)
           
           let {users}=this.data;
           users=result.data;
           console.log(users);
           this.setData({
             users
           })
           
         
          
        }
        
      },
    })
    }
    else{
      wx.request({
        url: 'http://127.0.0.1:8080/userlist',
         //远程javaee服务器上的url地址加请求
        // url: 'http://10.143.99.245:8080/show_adminindex',
        //要传递到远程去的json数据。
        data:data,
        timeout: 2000, //延时
        success: (result) => {//result.data就是从远程返回的数据
          
          
          if (result.data !=null){
             //把数据存到本地小程序缓存里面，名字叫user
             wx.setStorageSync('userlist', result.data)
             
             let {users}=this.data;
             users=result.data;
             console.log(users);
             this.setData({
               users
             })
             
           
            
          }
          
        },
      })
    }
  }

  
    console.log(this.data)
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  search:function(key){
    
    var username=key;
    var that = this;
    console.log(username)
    if(that.data.checkuserstyle!="user"){
      var userlist= wx.getStorageSync('userlist');
      var newuserlist=[];
      console.log(userlist)
      for(var i=0;i<userlist.length;i++)
      {
        
        if(userlist[i].username==username)
        {
          newuserlist.push(userlist[i])
          console.log("newuserlist:")
          console.log(newuserlist)
          wx.setStorageSync('searchuserlist',newuserlist )

          
        }
      }
      
     
    
  }
     wx.redirectTo({
      "url":"../manage_user/manage_user"
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