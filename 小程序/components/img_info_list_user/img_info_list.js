// components/img_info_list/img_info_list.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    users:{
      type:Array,
      value:[]
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

  ready: function() {
    // 在组件实例进入页面节点树时执行
    console.log(this.data);
      //建立list储存下标。
      //  const{index}=e.currentTarget.dataset;
      //  let {doctors}=this.data;
      //  //foreach判断每个下标和index是否相等，选择进入的页面。
      //  doctors.forEach((v,i)=>i===index?wx.navigateTo({
      //     url: this.data.link[i].url
      //   }):0)
  },
  methods: {

    
  }
})
