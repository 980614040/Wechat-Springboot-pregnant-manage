package com.pregnant_mannage.controller;


import com.pregnant_mannage.entity.Admin;
import com.pregnant_mannage.entity.Doctor;
import com.pregnant_mannage.service.AdminService;

import com.pregnant_mannage.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller //文件交给Controller管理
public class CAdmin {
    ///小程序部分
    @Autowired
    private AdminService adminService;
    @RequestMapping("show_adminindex")
    @ResponseBody
    public List<Admin> checkLoginadmin(HttpServletRequest request){
        String userCode = request.getParameter("user_code");
        String userPwd = request.getParameter("user_pwd");
        System.out.println(userCode + "|" + userPwd);
        List<Admin> admin =adminService.checkloginadmin(userCode,userPwd);


        //1种方式,如果user== null返回0，否则返回1. 函数54行的返回类型必须为String

        if (admin.size()==1){
            return admin;
        }
        else return null;

        //2种方式，直接返回json对象。如果是null，自然返回就是null，
        //此时需要把函数54行的返回值String类型修改成User。
    }


    @RequestMapping("admindesign")
    @ResponseBody
    public boolean designadmin(HttpServletRequest request){
        String userCode = request.getParameter("user_code");
        String userPwd = request.getParameter("user_pwd");
        System.out.println(userCode + "|" + userPwd);
        boolean issuccess=adminService.insertintoadmin(userCode,userPwd);


        //1种方式,如果user== null返回0，否则返回1. 函数54行的返回类型必须为String
        if (issuccess)
            return true;
        else
            return  false;
        //2种方式，直接返回json对象。如果是null，自然返回就是null，
        //此时需要把函数54行的返回值String类型修改成User。
    }





    ///后台部分。




}
