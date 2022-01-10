package com.pregnant_mannage.controller;

import com.pregnant_mannage.entity.Exam;
import com.pregnant_mannage.entity.Exam_paper;
import com.pregnant_mannage.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CExam {
    @Autowired
    private com.pregnant_mannage.service.ExamService ExamService;

    @RequestMapping("show_exam")
    @ResponseBody
    public List<Exam> getAllExam(){

        System.out.println("Examlist");
        List<Exam> ExamList = null;
        ExamList = ExamService.queryAllExam();
        System.out.println(ExamList.size());
        return ExamList;
    }


}
