package com.pregnant_mannage.service;

import com.pregnant_mannage.entity.Doctor;
import com.pregnant_mannage.entity.Doctortime;
import com.pregnant_mannage.mapper.DoctorMapper;
import com.pregnant_mannage.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoctortimeService {
    @Autowired
    private   com.pregnant_mannage.mapper.DoctortimeMapper DoctortimeMapper;

    public  List<Doctortime> queryDoctortimeList(String Doctorid) {

        System.out.println("DoctortimeService");
        System.out.println(Doctorid);
        String where_condition = " where Doctorid="+Doctorid;
        System.out.println(DoctortimeMapper.queryDoctortimeListWhere(where_condition).size());
        return DoctortimeMapper.queryDoctortimeListWhere(where_condition);
    }

    public  boolean insertDoctortimeinfo(Doctortime doctortimeinfo) {

        int userid= Integer.parseInt(doctortimeinfo.getUserid());
        String timeid= doctortimeinfo.getTimeid();
        String startTime=doctortimeinfo.getStartTime();
        String endTime=  doctortimeinfo.getEndTime();
        int staus=Integer.parseInt(doctortimeinfo.getStatus());
        int doctorid=Integer.parseInt(doctortimeinfo.getDoctorid());

        System.out.println("DoctortimeService");
        String where_condition = " where Doctorid="+doctorid;

        String sqlTxt = "insert into Doctortime values ('"+timeid+"',"+doctorid+",'"+startTime+"','"+endTime+"',"+staus+","+userid+")";
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