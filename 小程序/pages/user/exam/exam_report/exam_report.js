// pages/user/exam/exam_report/exam_report.js
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


    var checkuserstyle=wx.getStorageSync('checkuserstyle');
    console.log(checkuserstyle)
    wx.request({
      url: 'http://127.0.0.1:8080/show_exam',
       //远程javaee服务器上的url地址加请求
      // url: 'http://10.143.99.245:8080/show_adminindex',
      //要传递到远程去的json数据。
      timeout: 2000, //延时
      success: (result) => {//result.data就是从远程返回的数据
        
        
        if (result.data !=null){
           //把数据存到本地小程序缓存里面，名字叫user
           wx.setStorageSync('exam', result.data)
           
           let {exam}=this.data;
           exam=result.data;
           
           
           this.setData({
             "exam":exam,
             "checkuserstyle":checkuserstyle
           })
           console.log(this.data.exam)
        }

        
      },
    })
    
  },

  insertintosql(e){
    //插入执行
    wx.setStorageSync('chooseproject', this.data.exam[e.detail.index])
    console.log(wx.getStorageSync('chooseproject'));
    wx.navigateTo({
      url: '../exam_paper/exam_paper',
    })
  },

  selectsql(e){
    //插入执行 
    wx.setStorageSync('chooseproject', this.data.exam[e.detail.index])
    console.log(wx.getStorageSync('chooseproject'));
    wx.navigateTo({
      url: '../exam_paper/exam_paper',
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