package com.pregnant_mannage.mapper;

import com.pregnant_mannage.entity.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ExamMapper {

    //film_play_status是正在热映还是即将上映，number要显示的数量
//    Exam getExamByExamid(@Param("Examid") String Examid);
//
//    Exam checkloginExam(@Param("Examid") String Examid,@Param("pwd") String pwd);

    @Select("select * from Exam ${where_condition}")
    List<Exam> queryExamListWhere(
            @Param("where_condition") String where_condition);
}
