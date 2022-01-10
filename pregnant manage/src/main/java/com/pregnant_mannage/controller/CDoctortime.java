package com.pregnant_mannage.controller;

import com.pregnant_mannage.entity.Doctor;
import com.pregnant_mannage.entity.Doctortime;
import com.pregnant_mannage.service.DoctorService;
import com.pregnant_mannage.service.DoctortimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class CDoctortime {
    @Autowired
    private com.pregnant_mannage.service.DoctortimeService DoctortimeService;
    @RequestMapping("show_doctortime")
    @ResponseBody
    public  List<Doctortime> getDoctortimelist(HttpServletRequest request){
        System.out.println("进入CDoctorid");
         String  doctorid=request.getParameter("doctorid");

        System.out.println(doctorid);
        List<Doctortime> doctortimeList = DoctortimeService.queryDoctortimeList(doctorid);


        //1种方式,如果user== null返回0，否则返回1. 函数54行的返回类型必须为String
        if (doctortimeList.size()>0){
            return doctortimeList;
        }
        else return null;
    }


    @RequestMapping("insertdoctortime")
    @ResponseBody
    public  boolean insertdoctortime(HttpServletRequest request){
        System.out.println("进入insertdoctortime");
        String userid=request.getParameter("userid");
        String timeid=request.getParameter("timeid");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        String staus=request.getParameter("staus");
        String  doctorid=request.getParameter("doctorid");
        Doctortime doctortimeinfo=new Doctortime();
        doctortimeinfo.setDoctorid(doctorid);
        doctortimeinfo.setEndTime(endTime);
        doctortimeinfo.setStartTime(startTime);
        doctortimeinfo.setTimeid(timeid);
        doctortimeinfo.setStatus(staus);
        doctortimeinfo.setUserid(userid);

        System.out.println(doctortimeinfo.getDoctorid());

        boolean issuccess = DoctortimeService.insertDoctortimeinfo(doctortimeinfo);
        return issuccess;

    }


}