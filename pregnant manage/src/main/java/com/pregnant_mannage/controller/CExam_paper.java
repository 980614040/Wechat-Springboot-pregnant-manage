package com.pregnant_mannage.controller;

import com.pregnant_mannage.entity.Exam_paper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CExam_paper {
    @Autowired
    private com.pregnant_mannage.service.Exam_paperService Exam_paperService;

    @RequestMapping("show_exam_paper")
    @ResponseBody
    public List<Exam_paper> getAllExam_paper(){

        System.out.println("Examlist");
        List<Exam_paper> Exam_paperList = null;
        Exam_paperList = Exam_paperService.queryAllExam_paper();
        System.out.println(Exam_paperList.size());
        return Exam_paperList;
    }

    @RequestMapping("show_usersearch_paper")
    @ResponseBody
    public List<Exam_paper> usersearchpaper(HttpServletRequest request){
        System.out.println("进入searchpaper");
        String userid=request.getParameter("userid");

        String examid=request.getParameter("examid");

        Exam_paper exam_paper=new Exam_paper();


        exam_paper.setUserid(userid);

        System.out.println(exam_paper.getDoctorid());
        List<Exam_paper>  exam_paperList= Exam_paperService.usersearchexam_paperbyid(userid,examid);
        System.out.println();

        {return exam_paperList;}

    }

    @RequestMapping("show_usersearch_allpaper")
    @ResponseBody
    public List<Exam_paper> usersearchallpaper(HttpServletRequest request){

        String userid=request.getParameter("userid");
        Exam_paper exam_paper=new Exam_paper();


        exam_paper.setUserid(userid);

        List<Exam_paper>  exam_paperList= Exam_paperService.usersearchallexam_paperbyid(userid);
        System.out.println();

        {return exam_paperList;}

    }


    @RequestMapping("show_doctorsearch_paper")
    @ResponseBody
    public List<Exam_paper> doctorsearchpaper(HttpServletRequest request){
        String userid=request.getParameter("userid");
        String doctorid=request.getParameter("doctorid");
        String examid=request.getParameter("examid");

        Exam_paper exam_paper=new Exam_paper();
        exam_paper.setDoctorid(doctorid);

        exam_paper.setUserid(userid);

        System.out.println(exam_paper.getDoctorid());
        List<Exam_paper>  exam_paperList= Exam_paperService.doctorsearchexam_paperbyid(userid,doctorid,examid);

        System.out.println(exam_paperList.size());
        {return exam_paperList;}

    }

    @RequestMapping("insertpaper")
    @ResponseBody
    public boolean insertpaper(HttpServletRequest request){
        String paperid=request.getParameter("paperid");
        String  examid=request.getParameter("examid");
        String  userid=request.getParameter("userid");
        String  doctorid=request.getParameter("doctorid");
        String  describes=request.getParameter("describes");
        String  papaeratttion=request.getParameter("papaeratttion");
        String  papaertime=request.getParameter("papertime");

        String weight=request.getParameter("weight");
        String fetal_heart=request.getParameter("fetal_heart");
        String blood_pressure=request.getParameter("blood_pressure");
        String uterine_height=request.getParameter("uterine_height");
        String abdominal_girth=request.getParameter("abdominal_girth");

        System.out.println(weight);
        System.out.println(fetal_heart);
        System.out.println(blood_pressure);

        Exam_paper exam_paper=new Exam_paper();
        exam_paper.setDoctorid(doctorid);
        exam_paper.setUserid(userid);
        exam_paper.setDescribes(describes);
        exam_paper.setExamid(examid);
        exam_paper.setPapaeratttion(papaeratttion);
        exam_paper.setPaperid(paperid);
        exam_paper.setPapaertime(papaertime);
        exam_paper.setWeight(weight);
        exam_paper.setFetal_heart(fetal_heart);
        exam_paper.setBlood_pressure(blood_pressure);
        exam_paper.setUterine_height(uterine_height);
        exam_paper.setAbdominal_girth(abdominal_girth);



        boolean issuccess=Exam_paperService.insertintoexam_paper(exam_paper);


        //1种方式,如果user== null返回0，否则返回1. 函数54行的返回类型必须为String
        if (issuccess)
            return true;
        else
            return  false;
        //2种方式，直接返回json对象。如果是null，自然返回就是null，
        //此时需要把函数54行的返回值String类型修改成User。
    }


    //后台

    @RequestMapping("show_papers_pc")
    public String show_papers_pc(HttpServletRequest request, Model model)
    {
        System.out.println("show_papers_pc");
        int current_page = Integer.parseInt(request.getParameter("current_page"));
        int page_size = Integer.parseInt(request.getParameter("page_size"));
        String field_name = request.getParameter("field_name");
        String field_value = request.getParameter("field_value");
        System.out.println(current_page + "|" + page_size + "|" + field_name + "|" + field_value);
        //从userService里面获取数据并发送到网页去。
        List<Exam_paper> exam_paperList = Exam_paperService.queryAllExam_paper_pc(current_page, page_size, field_name,
                field_value);
        //把userList通过物流发送到user/user_list网页去。这样，网页端就可以获取数据了。
        HttpSession session = request.getSession();

        session.setAttribute("exam_papers", exam_paperList);
        //把调用该请求的参数传递到网页上去。这样网页上的上一页下一页才能使用这个参数
        session.setAttribute("current_page", current_page);
        session.setAttribute("page_size", page_size);
        session.setAttribute("field_name", field_name);
        session.setAttribute("field_value", field_value);

        model.addAttribute("exam_papers", exam_paperList);
        //把调用该请求的参数传递到网页上去。这样网页上的上一页下一页才能使用这个参数
        model.addAttribute("current_page", current_page);
        model.addAttribute("page_size", page_size);
        model.addAttribute("field_name", field_name);
        model.addAttribute("field_value", field_value);
        String paperslist_option= request.getParameter("exam_paperslist_option");
        System.out.println(paperslist_option);
        if(paperslist_option==null){
            model.addAttribute("paperslist_option", "first");
        }


        //userList是函数queryUserList里面的名字，
        // users是发送到user/user_list的名字，即在网页中使用的名字应为users
        return "admin/paperlist";
    }

    @RequestMapping("delete_papars_pc")
    public String DeletepaparByExamid(HttpServletRequest request, Model model){

        System.out.println("delete_papars_pc");
        int current_page = Integer.parseInt(request.getParameter("current_page"));
        int page_size = Integer.parseInt(request.getParameter("page_size"));
        String field_name = request.getParameter("field_name");
        String field_value = request.getParameter("field_value");
        System.out.println(current_page + "|" + page_size + "|" + field_name + "|" + field_value);

        String examid = request.getParameter("examid");

        boolean issuccess=Exam_paperService.deletepaperbyid(examid);


        //1种方式,如果user== null返回0，否则返回1. 函数54行的返回类型必须为String
        if (issuccess)
        {
            model.addAttribute("paperslist_option", "p_delete");
        }



        model.addAttribute("current_page", current_page);
        model.addAttribute("page_size", page_size);
        model.addAttribute("field_name", field_name);
        model.addAttribute("field_value", field_value);


        //userList是函数queryUserList里面的名字，
        // users是发送到user/user_list的名字，即在网页中使用的名字应为users
        return "redirect:show_papers_pc";
    }


    @RequestMapping("show_echart_pc")
    public String show_echart_pc(HttpServletRequest request, Model model){
        String userid=request.getParameter("userid");
        System.out.println(userid);
        Exam_paper exam_paper=new Exam_paper();
        exam_paper.setUserid(userid);

        List<Exam_paper>  exam_paperList= Exam_paperService.usersearchallexam_paperbyid(userid);

        String weights=null;
        String abdominal_girths=null;
         String blood_pressures=null;
         String fetal_hearts=null;
         String uterine_heights=null;
        for (int i = 0; i <exam_paperList.size() ; i++) {
            String weight=exam_paperList.get(i).getWeight();
            if (weights==null)
            {
                weights=weight;
            }
            else
                {
            weights=weights+","+weight;
            }

            String abdominal_girth=exam_paperList.get(i).getAbdominal_girth();
            if (abdominal_girths==null)
            {
                abdominal_girths=abdominal_girth;
            }
            else
                {
            abdominal_girths=abdominal_girths+","+abdominal_girth;
            }


            String blood_pressure=exam_paperList.get(i).getBlood_pressure();

            if (blood_pressures==null)
            {
                blood_pressures=blood_pressure;
            }
            else
                {
            blood_pressures=blood_pressures+","+blood_pressure;
            }

            String fetal_heart=exam_paperList.get(i).getFetal_heart();
            if (fetal_hearts==null)
            {
                fetal_hearts=fetal_heart;
            }
         else {
            fetal_hearts=fetal_hearts+","+fetal_heart;
         }

            String uterine_height=exam_paperList.get(i).getUterine_height();

         if (uterine_heights==null)
         {
             uterine_heights=uterine_height;
         }else {
            uterine_heights=uterine_heights+","+uterine_height;
        }
        }

        model.addAttribute("weights",weights);
        model.addAttribute("abdominal_girths",abdominal_girths);
        model.addAttribute("blood_pressures",blood_pressures);
        model.addAttribute("fetal_hearts",fetal_hearts);
        model.addAttribute("uterine_heights",uterine_heights);

        System.out.println("show_echart_pc:"+userid);



        return "/echart/echart_for_user";
    }

    @RequestMapping("show_addpaper_pc")
    public String show_addpaper_pc(HttpServletRequest request, Model model){

        String isedit=request.getParameter("excel_issuccuse");

        if (isedit!=null&&isedit.equals("true"))
        {
            System.out.println("isedit:"+isedit);
            model.addAttribute("excel_issuccuse","true");



        }
        else if(isedit!=null&&isedit.equals("false")){
            System.out.println("isedit:"+isedit);
            model.addAttribute("excel_issuccuse","false");



        }
        else {
            System.out.println("isedit:"+isedit);
            model.addAttribute("excel_issuccuse","first");
        }



        return "/admin/add_paper";
    }




}
