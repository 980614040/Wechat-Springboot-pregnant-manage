package com.pregnant_mannage.service;

import com.pregnant_mannage.entity.Doctor;
import com.pregnant_mannage.entity.User;
import com.pregnant_mannage.mapper.DoctorMapper;
import com.pregnant_mannage.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DoctorService {
    @Autowired
    private DoctorMapper DoctorMapper;

    public List<Doctor> queryDoctorList(String Doctorid) {

        System.out.println("DoctorService");

        System.out.println(Doctorid);
        String where_condition = " where Doctorid="+Doctorid;

        System.out.println("queryDoctorList");
        return DoctorMapper.queryDoctorListWhere(where_condition);
    }

    public List<Doctor> queryAllDoctor() {

        System.out.println("DoctorService");
        String where_condition = " where 1=1";

        System.out.println("queryAllDoctor");
        return DoctorMapper.queryDoctorListWhere(where_condition);
    }


    public List<Doctor>checkloginDoctor(String Doctorid, String pwd) {

        System.out.println("checkloginDoctor");

        System.out.println(Doctorid+"  "+pwd);
        String where_condition = " where Doctorid="+Doctorid+" and pwd="+pwd;

        System.out.println("queryDoctorList");
        System.out.println(DoctorMapper.queryDoctorListWhere(where_condition).size()==1);
        List<Doctor> doctor=DoctorMapper.queryDoctorListWhere(where_condition);

        if (doctor.size()==1)
        {return doctor;}
        else return null;


    }

    public boolean insertintoDoctor(String Doctorid, String Doctorpwd){
        //1:初始化


        String sqlTxt = "insert into Doctor (Doctorid,pwd) values ('"+Doctorid+"','"
                +Doctorpwd+" ')";


        //2:调用springUtil获得数据库访问类。并执行相应代码
        //并且通过(JdbcTemplate)对获取的对象(默认为Object类型)
        // 进行强类型化转换为JdbcTemplate类型的对象。
        System.out.println(sqlTxt);
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");//获取连接数据库的jdbcTemplate对象
        jdbcTemplate.update(sqlTxt);//用jdbcTemplate对象通过sql语句对数据库进行查询。
        return true;

    }
    public void updatedoctor(Doctor doctor){

        int doctorid=Integer.parseInt(doctor.getDoctorid());
        String doctorname=doctor.getDoctorname();
        int age= Integer.parseInt(doctor.getAge());
        String sex=doctor.getSex();
        String post=doctor.getPost();
        String describes=doctor.getDescribes();
        String Workyear=doctor.getWorkyear();



        String sqlTxt = "update  doctor set doctorname='"+doctorname+"', age="+age+",sex='"+sex+
                "',post='"+post+"',describes='"+describes+"',Workyear='"+Workyear+"'  " +
                "where doctorid="+doctorid+"";
        //2:调用springUtil获得数据库访问类。并执行相应代码
        //并且通过(JdbcTemplate)对获取的对象(默认为Object类型)
        // 进行强类型化转换为JdbcTemplate类型的对象。
        System.out.println(sqlTxt);
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");//获取连接数据库的jdbcTemplate对象
        jdbcTemplate.update(sqlTxt);//用jdbcTemplate对象通过sql语句对数据库进行查询。

    }
    public boolean  deletedoctorbyid(String doctorid){

        String sqlTxt = "delete from doctor where doctorid="+doctorid+"";
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
    public List<Doctor> queryDoctorListWherraddpage(int current_page, int page_size, String field_name,
                                    String field_value) {
        String where_condition = " where 1=1 ";
        if (!field_name.equals("default")) {
            where_condition += " field_name like '%" + field_value + "%' ";
        }
        return DoctorMapper.queryDoctorListWhereaddpage(current_page, page_size, where_condition);
    }

    public Doctor getAEditdoctor(String doctorid){
        //1:初始化
        String sqlTxt = "select * from doctor where doctorid = '" + doctorid + "'";
        Doctor doctor = null;
        //2:调用springUtil获得数据库访问类。并执行相应代码
        //并且通过(JdbcTemplate)对获取的对象(默认为Object类型)
        // 进行强类型化转换为JdbcTemplate类型的对象。
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");//获取连接数据库的jdbcTemplate对象
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);//用jdbcTemplate对象通过sql语句对数据库进行查询。
        if (list.size() > 0){//以下代码是把list类型的数据转换成doctor类型的数据
            doctor = new Doctor();
            for (Map<String, Object> map : list) {
                //map.get("doctor_code")的doctor_code数据库对应表的列名，
                // 不是doctor类里面的属性名，虽然名字相同，但是意义不一样
                doctor.setDoctorid(map.get("doctorid").toString());
                doctor.setDoctorname(map.get("doctorname").toString());
                doctor.setWorkyear(map.get("workyear").toString());
                doctor.setSex(map.get("sex").toString());
                doctor.setAge(map.get("age").toString());
                doctor.setDescribes(map.get("Describes").toString());
                doctor.setPost(map.get("post").toString());
            }
        }
        //3:返回结果
        return doctor;
    }


    public boolean updateDoctor_pc(Doctor Doctor){

        boolean issuccess=false;
        int Doctorid=Integer.parseInt(Doctor.getDoctorid());
        String Doctorname=Doctor.getDoctorname();
        String age=Doctor.getAge();
        String sex=Doctor.getSex();
        String post=Doctor.getPost();
        String describes=Doctor.getDescribes();
        String workyear=Doctor.getWorkyear();



        String sqlTxt = "update  doctor set doctorname='"+Doctorname+"', age="+age+",sex='"+sex+
                "',post='"+post+"',describes='"+describes+"',Workyear='"+workyear+"'  " +
                "where doctorid="+Doctorid+"";
        //2:调用springUtil获得数据库访问类。并执行相应代码
        //并且通过(JdbcTemplate)对获取的对象(默认为Object类型)
        // 进行强类型化转换为JdbcTemplate类型的对象。
        System.out.println(sqlTxt);
        try {
            JdbcTemplate jdbcTemplate =
                    (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");//获取连接数据库的jdbcTemplate对象
            jdbcTemplate.update(sqlTxt);//用jdbcTemplate对象通过sql语句对数据库进行查询。
            issuccess=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



        return issuccess;
    }


}
