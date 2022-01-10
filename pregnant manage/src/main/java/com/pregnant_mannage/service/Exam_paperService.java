package com.pregnant_mannage.service;

import com.pregnant_mannage.entity.Exam;
import com.pregnant_mannage.entity.Exam_paper;
import com.pregnant_mannage.mapper.ExamMapper;
import com.pregnant_mannage.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Exam_paperService {
    @Autowired
    private com.pregnant_mannage.mapper.Exam_paperMapper Exam_paperMapper;
    public List<Exam_paper> queryAllExam_paper() {

        System.out.println("Exam_paperService");
        String where_condition = " where 1=1";

        System.out.println("queryAllExam_paper");
        return Exam_paperMapper.queryExam_paperListWhere(where_condition);
    }

    public List<Exam_paper> doctorsearchexam_paperbyid(String userid, String doctorid, String examid) {


        String where_condition="where userid="+userid+" and doctor.doctorid="+doctorid+" and examid="+examid;
        return Exam_paperMapper.queryExam_paperListWhere(where_condition);
    }

    public List<Exam_paper> usersearchexam_paperbyid(String userid,  String examid) {


        String where_condition="where userid="+userid+" and examid="+examid+" and Exam_paper.doctorid=doctor.doctorid";
        return Exam_paperMapper.queryExam_paperListWhere(where_condition);
    }

    public List<Exam_paper> usersearchallexam_paperbyid(String userid) {


        String where_condition="where userid="+userid+" ";
        return Exam_paperMapper.queryallExam_paperListWhere(where_condition);
    }
    //录入报告
    public boolean insertintoexam_paper(Exam_paper exam_paper){
        //1:初始化

        String paperid=exam_paper.getPaperid();
        int  examid=Integer.parseInt(exam_paper.getExamid());
        int  userid=Integer.parseInt(exam_paper.getUserid());
        int  doctorid=Integer.parseInt(exam_paper.getDoctorid());
        String  describes=exam_paper.getDescribes();
        String  papaeratttion=exam_paper.getPapaeratttion();
        String  papaertime=exam_paper.getPapaertime();
        String src=null;

        int  weight=Integer.parseInt(exam_paper.getWeight());
        int  fetal_heart=Integer.parseInt(exam_paper.getFetal_heart());
        int  blood_pressure=Integer.parseInt(exam_paper.getBlood_pressure());
        int  uterine_height=Integer.parseInt(exam_paper.getUterine_height());
        int  abdominal_girth=Integer.parseInt(exam_paper.getAbdominal_girth());

        String sqlTxt = "insert into exam_paper values ('"+paperid+"',"
                +examid+",'"+describes+"'," +
                "'"+papaeratttion+"',"+doctorid+"," +
                ""+userid+",'"+papaertime+"','"+src+"',"+weight+"" +
                ","+fetal_heart+"" +
                ","+blood_pressure+"" +
                ","+uterine_height+"" +
                ","+abdominal_girth+" )";


        //2:调用springUtil获得数据库访问类。并执行相应代码
        //并且通过(JdbcTemplate)对获取的对象(默认为Object类型)
        // 进行强类型化转换为JdbcTemplate类型的对象。
        System.out.println(sqlTxt);
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");//获取连接数据库的jdbcTemplate对象
        jdbcTemplate.update(sqlTxt);//用jdbcTemplate对象通过sql语句对数据库进行查询。
        return true;

    }

    //后台
    public List<Exam_paper> queryAllExam_paper_pc(int current_page, int page_size, String field_name,
                                               String field_value) {


        String where_condition = " where 1=1 ";
        if (!field_name.equals("default")) {
            where_condition += " field_name like '%" + field_value + "%' ";
        }
        return Exam_paperMapper.queryallExam_paperListWhereaddpage(current_page, page_size, where_condition);



    }


    public boolean  deletepaperbyid(String examid){

        String sqlTxt = "delete from exam_paper where examid="+examid+"";
        //2:调用springUtil获得数据库访问类。并执行相应代码
        //并且通过(JdbcTemplate)对获取的对象(默认为Object类型)
        // 进行强类型化转换为JdbcTemplate类型的对象。
        System.out.println(sqlTxt);
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");//获取连接数据库的jdbcTemplate对象
        jdbcTemplate.update(sqlTxt);//用jdbcTemplate对象通过sql语句对数据库进行查询。

        return true;
    }
}
