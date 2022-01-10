package com.pregnant_mannage.controller;

import com.pregnant_mannage.entity.Admin;
import com.pregnant_mannage.entity.Doctor;
import com.pregnant_mannage.service.AdminService;
import com.pregnant_mannage.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CheckLogin_Pc
{
    @Autowired
    private AdminService adminService;
    private DoctorService doctorService;
    @RequestMapping("show_login_pc")
    public String showLogin(){
        return "login";//通过login映射到template下面的同名网页。html后缀不需要考虑
    }

    @RequestMapping("check_login_pc")

    public String checkLogin(HttpServletRequest request){//用来接收表单或者URl传递过来的参数
        String userid = request.getParameter("userid");
        String pwd = request.getParameter("pwd");
        String usertype = request.getParameter("usertype");
        System.out.println(userid + "|" + pwd + "|" + usertype);
        String url="";

        switch (usertype)
        {
            case "admin":
            {
                List<Admin> admin =adminService.checkloginadmin(userid,pwd);


                //1种方式,如果user== null返回0，否则返回1. 函数54行的返回类型必须为String

                if (admin.size()==1){
                    HttpSession session = request.getSession();
                    session.setAttribute("admin_info", admin);
                    url="redirect:show_admin_home_pc";
                    break;
                }

            }
            case "doctor":
                {
                List<Doctor> doctor =doctorService.checkloginDoctor(userid,pwd);


                //1种方式,如果user== null返回0，否则返回1. 函数54行的返回类型必须为String
                if (doctor.size()==1){
                    HttpSession session = request.getSession();
                    session.setAttribute("doctor_info", doctor);
                    url= "redirect:show_doctor_home_pc";
                    break;
                }
            }
        }

        return url;
    }


    @RequestMapping("show_admin_home_pc")
    public String show_admin_home(){
        return "/admin/admin_home";
    }
    @RequestMapping("show_doctor_home_pc")
    public String show_doctor_home(){
        return "/docotor/doctor_home";
    }
}
