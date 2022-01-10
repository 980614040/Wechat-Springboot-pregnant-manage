package com.pregnant_mannage.service;

import com.pregnant_mannage.entity.Exam;
import com.pregnant_mannage.entity.Exam_paper;
import com.pregnant_mannage.mapper.ExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class ExamService {
        @Autowired
        private com.pregnant_mannage.mapper.ExamMapper ExamMapper;

    public List<Exam> queryAllExam() {

        System.out.println("ExamService");
        String where_condition = " where 1=1";

        System.out.println("queryAllExam");
        return ExamMapper.queryExamListWhere(where_condition);
    }


}
