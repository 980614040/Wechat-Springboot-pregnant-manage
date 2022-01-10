// components/doctorinfo/doctorinfo.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    doctor:{
      type:Object,
      value:[]
    },
    checkuserstyle:{
      type:String,
      value:""
    }
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    deletedoctor(){
      var data={"doctorid":wx.getStorageSync('chooseuser').doctorid}
      console.log("删除")
      
      wx.request({
        url: 'http://127.0.0.1:8080/deletedoctor',
         //远程javaee服务器上的url地址加请求
        // url: 'http://10.143.99.245:8080/show_adminindex',
        data: data, //要传递到远程去的json数据。
        timeout: 2000, //延时
        success: (result) => {//result.data就是从远程返回的数据
          console.log(result.data)
          if (result.data==true){
  
  
            wx.showModal({
              content:"删除成功！",
              showCancel:false,
             success(res){
              if (res.confirm) {
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
              content:"删除失败！",
              showCancel:false
            })
          }
    }})},
    }
    
  })
