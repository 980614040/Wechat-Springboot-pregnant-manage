// pages/user/exam/exam_paper/exam_paper.js
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

    
    console.log(wx.getStorageSync('chooseproject'));
    var projectid=wx.getStorageSync('chooseproject').examid;
    var projectname=wx.getStorageSync('chooseproject').examproject;
    var checkuserstyle=wx.getStorageSync('checkuserstyle');
    if(checkuserstyle=="user")
      {
        var userid=wx.getStorageSync('user')[0].userid;
        
        var username=wx.getStorageSync('user')[0].username;
        var choosedoctorname=wx.getStorageSync('choosedoctor').doctorname;
        wx.request({
          url: 'http://127.0.0.1:8080/show_usersearch_paper?examid='+projectid+'&&userid='+userid+'',
           //远程javaee服务器上的url地址加请求
          // url: 'http://10.143.99.245:8080/show_adminindex',
          //要传递到远程去的json数据。
          timeout: 2000, //延时
          success: (result) => {//result.data就是从远程返回的数据
            
            
            if (result.data !=""){
               //把数据存到本地小程序缓存里面，名字叫user
               console.log(result.data)
               wx.setStorageSync('exam_paper', result.data)
               
               let {exam_paper}=this.data;
               exam_paper=result.data;
               
               
               
               this.setData({
                 "exam_paper":exam_paper,
                 "checkuserstyle":checkuserstyle,
                 "username":username,
                 "doctorname":result.data[0].doctorname,
                 "projectname":projectname,
                 "describes":result.data[0].describes,
                 "papaeratttion":result.data[0].papaeratttion,
                  "weight":result.data[0].weight,
                  "uterine_height":result.data[0].uterine_height,
                  "blood_pressure":result.data[0].blood_pressure,
                  "abdominal_girth":result.data[0].abdominal_girth,
                  "fetal_heart":result.data[0].fetal_heart
               })
               console.log(this.data.exam_paper)
               
            }
            else{
              wx.showModal({
            content:"报告还未生成！",
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
            
          },
        })
    
      }
      else{
        var doctorid=wx.getStorageSync('doctor')[0].doctorid;
        var choosuserid=wx.getStorageSync('chooseuser').userid;
        var choosusername=wx.getStorageSync('chooseuser').username;
        var doctorname=wx.getStorageSync('doctor')[0].doctorname;
        wx.request({
          url: 'http://127.0.0.1:8080/show_doctorsearch_paper?examid='+projectid+'&&userid='+choosuserid+'&&doctorid='+doctorid+'',
           //远程javaee服务器上的url地址加请求
          // url: 'http://10.143.99.245:8080/show_adminindex',
          //要传递到远程去的json数据。
          timeout: 2000, //延时
          success: (result) => {//result.data就是从远程返回的数据
            
            
            if (result.data !=""){
               //把数据存到本地小程序缓存里面，名字叫user
               console.log(result.data)
               wx.setStorageSync('exam_paper', result.data)
               let {exam_paper}=this.data;
               exam_paper=result.data;
               
               
               this.setData({
                 "exam_paper":exam_paper,
                 "checkuserstyle":checkuserstyle,
                 "username":choosusername,
                 "doctorname":doctorname,
                 "projectname":projectname,
                 "describes":result.data[0].describes,
                 "papaeratttion":result.data[0].papaeratttion,
                 "weight":result.data[0].weight,
                  "uterine_height":result.data[0].uterine_height,
                  "blood_pressure":result.data[0].blood_pressure,
                  "abdominal_girth":result.data[0].abdominal_girth,
                  "fetal_heart":result.data[0].fetal_heart
               })
               console.log(this.data.exam_paper)

            }
            else{

              console.log(result.data)
              wx.setStorageSync('exam_paper', result.data)
              let {exam_paper}=this.data;
              exam_paper=result.data;
              
              
              this.setData({
                "exam_paper":exam_paper,
                "checkuserstyle":checkuserstyle,
                "username":choosusername,
                "doctorname":doctorname,
                "projectname":projectname
              })
              console.log(this.data.exam_paper)
            }
            
            
          },
        })
    
      }
   
     
     
  },

  formSubmit(ex){

    console.log(ex)
    var data = {
    }
    var projectid=wx.getStorageSync('chooseproject').examid;
    var userid=wx.getStorageSync('chooseuser').userid;
    var doctorid=wx.getStorageSync('doctor')[0].doctorid;
    var username=wx.getStorageSync('chooseuser').username;
    var doctorname=wx.getStorageSync('doctor')[0].doctorname;
    var projectname=wx.getStorageSync('chooseproject').examproject;
    var paperid=doctorid+"0"+userid+"0"+projectid;
    console.log(paperid);
    var checkuserstyle=wx.getStorageSync('checkuserstyle');
    this.setData({
      "checkuserstyle":checkuserstyle,
      "username":username,
      "doctorname":doctorname,
      "projectname":projectname
    })
    var myDate=new Date();
    var time=myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate()+" "+
    myDate.getHours()+":"+myDate.getMinutes()+":"+myDate.getMinutes();
    console.log(time)
    var data={
      "paperid":paperid,
      "examid":projectid,
      "doctorid":doctorid,
      "userid":userid,
      "papertime":time,
      "describes": ex.detail.value.describes,
      "weight":ex.detail.value.weight,
      "papaeratttion": ex.detail.value.papaeratttion,
      "uterine_height": ex.detail.value.uterine_height,
      "blood_pressure": ex.detail.value.blood_pressure,
      "abdominal_girth": ex.detail.value.abdominal_girth,
      "fetal_heart": ex.detail.value.fetal_heart
    }
    wx.request({
      url: 'http://127.0.0.1:8080/insertpaper',
      // ?paperid='+paperid+'&&examid='+projectid+'&&doctorid='+doctorid+'&&userid='+userid+'&&papertime='
      // +time,
       //远程javaee服务器上的url地址加请求
      // url: 'http://10.143.99.245:8080/show_adminindex',
      //要传递到远程去的json数据。
      data:data,
      timeout: 2000, //延时
      success: (result) => {//result.data就是从远程返回的数据
        if (result.data ==true){

          
          wx.showModal({
            content:"录入成功！",
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
        else{
          wx.showModal({
            content:"录入失败！",
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
   * 生命周期函数--监听页面初次渲染完成
   */,
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