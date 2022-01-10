// pages/updateuser/updateuser.js
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
 
    var userstyle=  wx.getStorageSync('checkuserstyle');
    console.log(userstyle)
    this.setData({
      "userstyle":userstyle
    })
    switch(userstyle)
    {
      case "user":
        {
          var  userinfo=wx.getStorageSync('user');
          console.log(userinfo)
          this.setData({
            "user":userinfo[0]
            
          })
          break;
        }
        case "doctor":
        {
          var  doctorinfo=wx.getStorageSync('doctor');
          console.log(doctorinfo)
          this.setData({
            "doctor":doctorinfo[0]
            
          })
          break;
        }
        case "admin":
        {
          var  doctorinfo=wx.getStorageSync('doctor');
          console.log(doctorinfo)
          this.setData({
            "doctor":doctorinfo[0]
            
          })
          break;
        }
    }

  },
//user录入
  userSubmit:function(ex){

    console.log(ex)
    var userid=wx.getStorageSync('user')[0].userid;
    console.log(userid)
    var username = ex.detail.value.username;
    var age = ex.detail.value.age;
    var tel = ex.detail.value.tel;
    var pregnant_time=ex.detail.value.pregnant_time;
    var describes=ex.detail.value.describes;
    var src=wx.getStorageSync('user')[0].src;
    //也可以合并成下面一句话：
    var data = {
                "userid":userid,
                "username": username,
                "age": age,
                "tel":tel,
                "pregnant_time":pregnant_time,
                "describes":describes,
                "src":src
    }
    
    wx.request({
      url: 'http://127.0.0.1:8080/updateuser',
       //远程javaee服务器上的url地址加请求
      // url: 'http://10.143.99.245:8080/show_adminindex',
      data: data, //要传递到远程去的json数据。
      timeout: 2000, //延时
      success: (result) => {//result.data就是从远程返回的数据
        console.log(result.data)
        if (result.data.error==null){


          wx.showModal({
            content:"修改成功！",
            showCancel:false,
           success(res){
            if (res.confirm) {
              wx.setStorageSync('user', [data])
              console.log(wx.getStorageSync('user'));
              var pages=getCurrentPages(); // 获取当前页面
              var prevPage=pages[pages.length -2];// 获取上一个页面js  
              prevPage.changeData();
              wx.navigateBack({
                delta: -1
            });
            }
           }
          })
         
          
        }
        else{
          wx.showModal({
            content:"修改信息失败！",
            showCancel:false
          })
        }
  }})},
//doctor录入   
  doctorSubmit:function(ex){

    console.log(ex)
    var doctorid=wx.getStorageSync('doctor')[0].doctorid;
    console.log(doctorid)
    var doctorname = ex.detail.value.doctorname;
    var age = ex.detail.value.age;
    var sex = ex.detail.value.sex;
    var Workyear=ex.detail.value.Workyear;
    var post=ex.detail.value.post;
    var describes=ex.detail.value.describes;
    var src=wx.getStorageSync('doctor')[0].src;
   console.log(sex)
    //也可以合并成下面一句话：
    var data = {
                "doctorid":doctorid,
                "doctorname": doctorname,
                "age": age,
                "sex":sex,
                "Workyear":Workyear,
                "post":post,
                "describes":describes,
                "src":src
    }
    
    wx.request({
      url: 'http://127.0.0.1:8080/updatedoctor',
       //远程javaee服务器上的url地址加请求
      // url: 'http://10.143.99.245:8080/show_adminindex',
      data: data, //要传递到远程去的json数据。
      timeout: 2000, //延时
      success: (result) => {//result.data就是从远程返回的数据
        console.log(result.data)
        if (result.data.error==null){

         
          wx.showModal({
            content:"修改成功！",
            showCancel:false,
           success(res){
            if (res.confirm) {
              wx.setStorageSync('doctor', [data])
          console.log(wx.getStorageSync('doctor'));
              var pages=getCurrentPages(); // 获取当前页面
              var prevPage=pages[pages.length -2];// 获取上一个页面js  
              prevPage.changeData();
              wx.navigateBack({
                delta: -1
            });
            }
           }
          })
         
          
        }
        else{
          wx.showModal({
            content:"修改信息失败！",
            showCancel:false
          })
        }
  }})},
})