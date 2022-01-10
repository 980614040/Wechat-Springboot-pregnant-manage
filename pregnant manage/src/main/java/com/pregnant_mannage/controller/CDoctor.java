package com.pregnant_mannage.controller;



import com.pregnant_mannage.entity.Doctor;
import com.pregnant_mannage.entity.User;
import com.pregnant_mannage.service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class CDoctor {
    @Autowired
    private DoctorService DoctorService;
    @RequestMapping("show_doctorindex")
    @ResponseBody
    public List<Doctor> checkLoginDoctor(HttpServletRequest request){
        String userCode = request.getParameter("user_code");
        String userPwd = request.getParameter("user_pwd");
        System.out.println(userCode + "|" + userPwd);
        List<Doctor> doctor =DoctorService.checkloginDoctor(userCode,userPwd);


        //1种方式,如果user== null返回0，否则返回1. 函数54行的返回类型必须为String
        if (doctor.size()==1){
            return doctor;
        }
        else return null;


        //2种方式，直接返回json对象。如果是null，自然返回就是null，
        //此时需要把函数54行的返回值String类型修改成User。
    }


    @RequestMapping("doctordesign")
    @ResponseBody
    public boolean designDoctor(HttpServletRequest request){
        String userCode = request.getParameter("user_code");
        String userPwd = request.getParameter("user_pwd");
        System.out.println(userCode + "|" + userPwd);
        boolean issuccess=DoctorService.insertintoDoctor(userCode,userPwd);


        //1种方式,如果user== null返回0，否则返回1. 函数54行的返回类型必须为String
        if (issuccess)
            return true;
        else
            return  false;
        //2种方式，直接返回json对象。如果是null，自然返回就是null，
        //此时需要把函数54行的返回值String类型修改成User。
    }

    @RequestMapping("deletedoctor")
    @ResponseBody
    public boolean deletedoctorbyid(HttpServletRequest request){
        String doctorid = request.getParameter("doctorid");

        boolean issuccess=DoctorService.deletedoctorbyid(doctorid);


        //1种方式,如果user== null返回0，否则返回1. 函数54行的返回类型必须为String
        if (issuccess)
            return true;
        else
            return  false;
        //2种方式，直接返回json对象。如果是null，自然返回就是null，
        //此时需要把函数54行的返回值String类型修改成User。
    }


    @RequestMapping("doctorlist")
    @ResponseBody
    public List<Doctor> getAllDoctor(){

        System.out.println("doctorlist");
        List<Doctor> DoctorList = null;
        DoctorList = DoctorService.queryAllDoctor();
        System.out.println(DoctorList.size());
        return DoctorList;
    }
    @RequestMapping("updatedoctor")
    @ResponseBody
    public void updatedoctor(HttpServletRequest request){
        String doctorid=request.getParameter("doctorid");
        String doctorname=request.getParameter("doctorname");
        String age=request.getParameter("age");
        String sex=request.getParameter("sex");
        String Workyear=request.getParameter("Workyear");
        String post=request.getParameter("post");
        String describes=request.getParameter("describes");

        System.out.println(doctorid);
        Doctor doctor=new Doctor();
        doctor.setAge(age);
        doctor.setDescribes(describes);
        doctor.setDoctorid(doctorid);
        doctor.setPost(post);
        doctor.setDoctorname(doctorname);
        doctor.setWorkyear(Workyear);
        doctor.setSex(sex);

        DoctorService.updatedoctor(doctor);
    }


    ///后台
    @RequestMapping("query_doctor_list_pc")
    public String queryUserList(HttpServletRequest request, Model model){
        System.out.println("query_doctor_list_pc");
        int current_page = Integer.parseInt(request.getParameter("current_page"));
        int page_size = Integer.parseInt(request.getParameter("page_size"));
        String field_name = request.getParameter("field_name");
        String field_value = request.getParameter("field_value");
        System.out.println(current_page + "|" + page_size + "|" + field_name + "|" + field_value);
        //从userService里面获取数据并发送到网页去。
        List<Doctor> doctorList = DoctorService.queryDoctorListWherraddpage(current_page, page_size, field_name,
                field_value);
        //把userList通过物流发送到user/user_list网页去。这样，网页端就可以获取数据了。
        model.addAttribute("doctors", doctorList);
        //把调用该请求的参数传递到网页上去。这样网页上的上一页下一页才能使用这个参数
        model.addAttribute("current_page", current_page);
        model.addAttribute("page_size", page_size);
        model.addAttribute("field_name", field_name);
        model.addAttribute("field_value", field_value);

        //userList是函数queryUserList里面的名字，
        // users是发送到user/user_list的名字，即在网页中使用的名字应为users
        return "admin/doctorlist";
    }

    @RequestMapping("show_edit_doctor_pc")
    public String showEditdoctor_pc(HttpServletRequest request, Model model){
        //request用来传递参数，好知道客户端想获取谁的数据
        //1：初始化，从request获取URL请求里面的参数.
        String doctorid = request.getParameter("doctorid");

        System.out.println("showEditdoctor_pc:"+doctorid);
        //2:把参数传递给service层相应的类和相应的函数.从那边获取返回值.

        Doctor doctor = DoctorService.getAEditdoctor(doctorid);//得到要编辑的一条用户数据
        //3:将返回值传递给对应的网页。并跳转到同名网页。
        //要奖doctor对象传递到网页，需要一个媒介，或者说载体。引入一个参数叫model或者modelandview都可以
        //这样就把doctor传递到对应网页上。在对应网页的名字叫doctor_info，
        // model相当物流，把doctor打包从控制器搬运到对应网页，在终点，包裹名字叫doctor_info

        System.out.println(doctor.getDoctorname());
        //返回
        String d_isedit=request.getParameter("d_issuccuse");

        if (d_isedit!=null&&d_isedit.equals("true"))
        {
            System.out.println("d_isedit:"+d_isedit);
            model.addAttribute("d_issuccuse","true");
            model.addAttribute("choose_doctor", doctor);
            HttpSession session = request.getSession();
            session.setAttribute("choose_doctor", doctor);
        }
        else if(d_isedit!=null&&d_isedit.equals("false")){

            System.out.println("d_isedit:"+d_isedit);
            model.addAttribute("d_issuccuse","false");
            model.addAttribute("choose_doctor", doctor);
            HttpSession session = request.getSession();
            session.setAttribute("choose_doctor", doctor);
        }
        else {
            System.out.println("d_isedit:"+d_isedit);
            model.addAttribute("d_issuccuse","first");
            model.addAttribute("choose_doctor", doctor);
            HttpSession session = request.getSession();
            session.setAttribute("choose_doctor", doctor);
        }



        return "/admin/edit_doctor";
    }

    @RequestMapping("edit_doctor_pc")
    public String Editdoctor_pc(HttpServletRequest request, Model model){
        //request用来传递参数，好知道客户端想获取谁的数据
        //1：初始化，从request获取URL请求里面的参数.


        System.out.println(((Doctor) request.getSession().getAttribute("choose_doctor")).getDoctorid());
        Doctor doctor=new Doctor();
        doctor= (Doctor) request.getSession().getAttribute("choose_doctor");
        doctor.setDescribes(request.getParameter("Describes"));
        doctor.setDoctorname(request.getParameter("doctorname"));
        doctor.setSex(request.getParameter("sex"));
        doctor.setAge(request.getParameter("age"));
        doctor.setWorkyear(request.getParameter("workyear"));
        doctor.setPost(request.getParameter("post"));

        if(DoctorService.updateDoctor_pc(doctor)){
            model.addAttribute("choose_doctor", doctor);
            //返回
            model.addAttribute("doctorid",doctor.getDoctorid());
            model.addAttribute("d_issuccuse","true");
            System.out.println("修改成功");
            return "redirect:show_edit_doctor_pc";
        }
        else
        {
            model.addAttribute("doctorid",doctor.getDoctorid());
            model.addAttribute("d_issuccuse","false");
            System.out.println("修改失败,请检查");
            return "redirect:show_edit_doctor_pc";
        }

    }

}
