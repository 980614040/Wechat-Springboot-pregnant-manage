// components/searchlist/link.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    link:{
      type:Array,
      value:[]
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    // link:[
    //   {
    //     id:0,
    //     name:"医生管理",
    //     src:"/icon/医生团队.png",
    //     url:"/pages/admin/manage/manage_doctor/manage_doctor"
    //   },
    //   {
    //     id:1,
    //     name:"用户管理",
    //     src:"/icon/用户.png",
    //     url:"/pages/admin/manage/manage_user/manage_user"
    //   },
    //   {
    //     id:2,
    //     name:"数据管理",
    //     src:"/icon/列表.png",
    //     url:"/pages/admin/manage/manage_data/manage_data"
    //   }
    // ]
   
  },

  /**
   * 组件的方法列表
   */
  methods: {

    checklink(e){
      console.log(this.data);
      //建立list储存下标。
      console.log(e)
      console.log(e.currentTarget.dataset)
       const{index}=e.currentTarget.dataset;
       let {link}=this.data;

         //父传子，父xml=>子js=>子xml,传递的是属性。
      
       //foreach判断每个下标和index是否相等，选择进入的页面。

       link.forEach((v,i)=>i===index?wx.navigateTo({
        url: this.data.link[i].url
      }):0)

      //子传父，子js=>父xml=>父js传递的是事件名和参数。
      //定义一个可传参数。
      // this.triggerEvent("admin_home.js(父组件)定义事件名称"，要传递参数)
        // this.triggerEvent("checklink",{index})
     
    }
  }
})
