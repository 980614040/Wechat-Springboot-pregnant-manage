// components/searchbox/searchbox.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

    list1:{
      type:Array,
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

    viewShowed: false, //显示结果view的状态
    inputVal: "", // 搜索框值
    catList: [], //搜索渲染推荐数据
    list1: []
  },

  /**
   * 组件的方法列表
   */
  methods: {

    hideInput: function() {
      this.setData({
        inputVal: "",
        viewShowed: false,
      });
    },

    inputTyping: function(e) {
      console.log("input-----",e)
      var value = e.detail.value
      var that = this;
      var searchitems=that.data.list1
      var list1 =[];
      if(that.data.checkuserstyle=="user"){
      for (var i=0;i<searchitems.length;i++)
      {
        console.log(searchitems[i].doctorname)
        list1.push(searchitems[i].doctorname)
      }
    }
    else
    {
      for (var i=0;i<searchitems.length;i++)
      {
        console.log(searchitems[i].username)
        list1.push(searchitems[i].username)
      }
    }
      if (value == '') {
        that.setData({
          viewShowed: false,
        });
      } else {
      //“这里需要特别注意，不然在选中下拉框值的时候，下拉框又出现”
        if (e.detail.cursor) { //e.detail.cursor表示input值当前焦点所在的位置
          var arr = [];
          for (var i = 0; i < list1.length; i++) {
            if (list1[i].indexOf(value) >= 0) {
              arr.push(list1[i]);
            }
          }
          console.log(arr)
          that.setData({
            viewShowed: true,
            carList: arr
          });
        }
      }
    },
    name: function(res) {
      console.log(res.currentTarget.dataset.index);
      var index = res.currentTarget.dataset.index
      var that = this;
      that.setData({
        inputVal: that.data.carList[index],
        viewShowed: false,
      })

     
    },
   
    search: function(e){
      var username=e.detail.value
      var pages=getCurrentPages();
        var prevPage=pages[pages.length -1];
        prevPage.search(username);

      
    }
    

  //   //  点击搜索给查找传数据
  //   get_keyword: function (e) {
  //     console.log(e);
  //     this.setData({
  //         keywords: e.detail.value
  //     })
  // },


  // _goods: function (e) {
  //     var id = e.currentTarget.dataset.id;
  //     console.log(e.currentTarget.dataset);
  //     var store_id = e.currentTarget.dataset.store_id;
  //     app.XcxData.store_id = store_id;
  //     wx.navigateTo({
  //         url: "/pages/details/details?id=" + id + "&store_id=" + store_id
  //     });
  // },

  // //  搜索获取门店信息
  // get_search: function () {
  //     var t = this,
  //         keywords = t.data.keywords;
  //     if (keywords == '') {
  //         wx.showToast({
  //             title: '请输入店铺名称！',
  //             icon: 'none',
  //             duration: 2000
  //         })
  //         return false
  //     }
  //     app.sendRequest({
  //         url: '/store/search_store',
  //         type: 'post',
  //         data: {
  //             name: keywords
  //         },
  //         loadTitle: "搜索中...",
  //         success: function (e) {
  //             1 == e.status && t.setData({
  //                 list: e.store
  //             })
  //         }
  //     })
  // }
  }
})
