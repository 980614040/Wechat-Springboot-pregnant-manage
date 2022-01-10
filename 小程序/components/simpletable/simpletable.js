// components/table/table.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    exam: {
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


    
  },

  /**
   * 组件的方法列表
   */
  methods: {
    insertexam(e){
     
      //建立list储存下标。
      console.log(e)

       const{index}=e.currentTarget.dataset;
       let {exam}=this.data;

       var chooseuser=wx.getStorageSync('chooseuser');
      
      console.log({index})
        this.triggerEvent("insert",{index})
      

     
    },
    searchexam(e){
      console.log(e)
      this.triggerEvent("select",1)
      const{index}=e.currentTarget.dataset;
      let {exam}=this.data;

      var chooseuser=wx.getStorageSync('chooseuser');
     
     console.log({index})
     this.triggerEvent("search",{index})
    }
  },
  
})
