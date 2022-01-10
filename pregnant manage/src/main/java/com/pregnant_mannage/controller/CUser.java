package com.pregnant_mannage.controller;


import com.pregnant_mannage.entity.Doctor;
import com.pregnant_mannage.entity.User;
import com.pregnant_mannage.entity.User;
import com.pregnant_mannage.entity.User;
import com.pregnant_mannage.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

@Controller
public class CUser {
    @Autowired
    private UserService UserService;
    @RequestMapping("show_userindex")
    @ResponseBody
    public List<User> checkLoginUser(HttpServletRequest request){
        String userCode = request.getParameter("user_code");
        String userPwd = request.getParameter("user_pwd");
        System.out.println(userCode + "|" + userPwd);
        List<User> user =UserService.checkloginUser(userCode,userPwd);

        if (user.size()==1){
            return user;
        }
        else return null;


        //2种方式，直接返回json对象。如果是null，自然返回就是null，
        //此时需要把函数54行的返回值String类型修改成User。
    }


    @RequestMapping("userdesign")
    @ResponseBody
    public boolean designUser(HttpServletRequest request){
        String userCode = request.getParameter("user_code");
        String userPwd = request.getParameter("user_pwd");
        System.out.println(userCode + "|" + userPwd);
        boolean issuccess=UserService.insertintoUser(userCode,userPwd);


        //1种方式,如果user== null返回0，否则返回1. 函数54行的返回类型必须为String
        if (issuccess)
            return true;
        else
            return  false;
        //2种方式，直接返回json对象。如果是null，自然返回就是null，
        //此时需要把函数54行的返回值String类型修改成User。
    }

    @RequestMapping("userlist")
    @ResponseBody
    public List<User> getAllUser(){

        System.out.println("userlist");
        List<User> UserList = null;
        UserList = UserService.queryAllUser();
        System.out.println(UserList.size());
        System.out.println(UserList.get(1).getUserid());
        return UserList;
}

    @RequestMapping("doctorsearchuserlist")
    @ResponseBody
    public List<User> getUserlistbydoctorid(HttpServletRequest request){

        String doctorid=request.getParameter("doctorid");
        System.out.println("getUserlistbydoctorid");
        List<User> UserList = null;
        UserList = UserService.getUserListbydoctorid(doctorid);

        return UserList;
    }
    @RequestMapping("updateuser")
    @ResponseBody
    public void updateuser(HttpServletRequest request){
        String userid=request.getParameter("userid");
        String username=request.getParameter("username");
        String age=request.getParameter("age");
        String tel=request.getParameter("tel");
        String pregnant_time=request.getParameter("pregnant_time");
        String describes=request.getParameter("describes");
        System.out.println(userid);
        User user=new User();
        user.setAge(age);
        user.setDescribes(describes);
        user.setUserid(userid);
        user.setPregnant_time(pregnant_time);
        user.setUsername(username);
        user.setTel(tel);

        UserService.updateuser(user);
    }


    //后台

    @RequestMapping("query_user_list_pc")
    public String queryUserList(HttpServletRequest request, Model model){
        System.out.println("query_user_list_pc");
        int current_page = Integer.parseInt(request.getParameter("current_page"));
        int page_size = Integer.parseInt(request.getParameter("page_size"));
        String field_name = request.getParameter("field_name");
        String field_value = request.getParameter("field_value");
        System.out.println(current_page + "|" + page_size + "|" + field_name + "|" + field_value);
        //从userService里面获取数据并发送到网页去。
        List<User> userList = UserService.queryUserList(current_page, page_size, field_name,
                field_value);
        //把userList通过物流发送到user/user_list网页去。这样，网页端就可以获取数据了。
        HttpSession session = request.getSession();

        session.setAttribute("users", userList);
        //把调用该请求的参数传递到网页上去。这样网页上的上一页下一页才能使用这个参数
        session.setAttribute("current_page", current_page);
        session.setAttribute("page_size", page_size);
        session.setAttribute("field_name", field_name);
        session.setAttribute("field_value", field_value);

        model.addAttribute("users", userList);
        //把调用该请求的参数传递到网页上去。这样网页上的上一页下一页才能使用这个参数
        model.addAttribute("current_page", current_page);
        model.addAttribute("page_size", page_size);
        model.addAttribute("field_name", field_name);
        model.addAttribute("field_value", field_value);
        String userlist_option= request.getParameter("userlist_option");
        System.out.println(userlist_option);
        if(userlist_option==null){
        model.addAttribute("userlist_option", "first");
        }
        

        //userList是函数queryUserList里面的名字，
        // users是发送到user/user_list的名字，即在网页中使用的名字应为users
        return "admin/userlist";
    }

    @RequestMapping("delete_user_pc")
    public String DeleteuserByUserid(HttpServletRequest request, Model model){

        System.out.println("delete_user_pc");
        int current_page = Integer.parseInt(request.getParameter("current_page"));
        int page_size = Integer.parseInt(request.getParameter("page_size"));
        String field_name = request.getParameter("field_name");
        String field_value = request.getParameter("field_value");
        System.out.println(current_page + "|" + page_size + "|" + field_name + "|" + field_value);

        String userid = request.getParameter("userid");

        boolean issuccess=UserService.deleteuserbyid(userid);


        //1种方式,如果user== null返回0，否则返回1. 函数54行的返回类型必须为String
        if (issuccess)
        {
            model.addAttribute("userlist_option", "u_delete");
        }



        model.addAttribute("current_page", current_page);
        model.addAttribute("page_size", page_size);
        model.addAttribute("field_name", field_name);
        model.addAttribute("field_value", field_value);


        //userList是函数queryUserList里面的名字，
        // users是发送到user/user_list的名字，即在网页中使用的名字应为users
        return "redirect:query_user_list_pc";
    }


    @RequestMapping("show_edit_user_pc")
    public String showEditUser_pc(HttpServletRequest request, Model model){
        //request用来传递参数，好知道客户端想获取谁的数据
        //1：初始化，从request获取URL请求里面的参数.
        String userid = request.getParameter("userid");

        System.out.println("showEditUser_pc:"+userid);
        //2:把参数传递给service层相应的类和相应的函数.从那边获取返回值.
        User user = null;
        user = UserService.getAEditUser(userid);//得到要编辑的一条用户数据
        //3:将返回值传递给对应的网页。并跳转到同名网页。
        //要奖user对象传递到网页，需要一个媒介，或者说载体。引入一个参数叫model或者modelandview都可以
        //这样就把user传递到对应网页上。在对应网页的名字叫user_info，
        // model相当物流，把user打包从控制器搬运到对应网页，在终点，包裹名字叫user_info

        //返回
         String isedit=request.getParameter("issuccuse");

         if (isedit!=null&&isedit.equals("true"))
         {
             System.out.println("isedit:"+isedit);
             model.addAttribute("issuccuse","true");
             model.addAttribute("choose_user", user);
             HttpSession session = request.getSession();
             session.setAttribute("choose_user", user);
         }
         else if(isedit!=null&&isedit.equals("false")){
             System.out.println("isedit:"+isedit);
             model.addAttribute("issuccuse","false");
             model.addAttribute("choose_user", user);
             HttpSession session = request.getSession();
             session.setAttribute("choose_user", user);
         }
         else {
             System.out.println("isedit:"+isedit);
             model.addAttribute("issuccuse","first");
             model.addAttribute("choose_user", user);
             HttpSession session = request.getSession();
             session.setAttribute("choose_user", user);
         }



        return "/admin/edit_user";
    }

    @RequestMapping("edit_user_pc")
    public String EditUser_pc(HttpServletRequest request, Model model){
        //request用来传递参数，好知道客户端想获取谁的数据
        //1：初始化，从request获取URL请求里面的参数.


        System.out.println(((User) request.getSession().getAttribute("choose_user")).getUserid());
        User user=new User();
        user= (User) request.getSession().getAttribute("choose_user");
        user.setDescribes(request.getParameter("Describes"));
        user.setUsername(request.getParameter("username"));
        user.setTel(request.getParameter("tel"));
        user.setAge(request.getParameter("age"));
        user.setPregnant_time(request.getParameter("Pregnant_time"));

        if(UserService.updateuser_pc(user)){
        model.addAttribute("choose_user", user);
            //返回
            model.addAttribute("userid",user.getUserid());
            model.addAttribute("issuccuse","true");
            System.out.println("修改成功");
            return "redirect:show_edit_user_pc";
        }
        else
        {
            model.addAttribute("userid",user.getUserid());
            model.addAttribute("issuccuse","false");
            System.out.println("修改失败,请检查");
            return "redirect:show_edit_user_pc";
        }

    }





}
