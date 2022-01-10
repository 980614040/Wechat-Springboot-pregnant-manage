package com.pregnant_mannage.service;

import com.pregnant_mannage.entity.Admin;
import com.pregnant_mannage.mapper.AdminMapper;
import com.pregnant_mannage.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public List<Admin> queryAdminList(String adminid) {

        System.out.println("adminService");

        System.out.println(adminid);
        String where_condition = " where adminid="+adminid;

        System.out.println("queryadminList");
        return adminMapper.queryadminListWhere(where_condition);
    }

    public List<Admin> checkloginadmin(String adminid, String pwd) {

        System.out.println("checkloginadmin");

        System.out.println(adminid+"  "+pwd);
        String where_condition = " where adminid="+adminid+" and pwd="+pwd;

        System.out.println("queryadminList");
        System.out.println(adminMapper.queryadminListWhere(where_condition).size()==1);
        List<Admin> admin=adminMapper.queryadminListWhere(where_condition);

        if (admin.size()==1){
            return admin;
        }
        else return null;
    }




    public boolean insertintoadmin(String adminid, String adminpwd){
        //1:初始化


        String sqlTxt = "insert into admin (adminid,pwd) values ('"+adminid+"','"
                +adminpwd+" ')";


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
