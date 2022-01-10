// components/userinfo/userinfo.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

    user:{
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

  ready: function() {
    console.log(this.data);
  },
  methods: {
    
    
  }
})
