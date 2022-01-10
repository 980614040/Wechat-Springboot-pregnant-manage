package com.pregnant_mannage.service;

import com.pregnant_mannage.entity.User;
import com.pregnant_mannage.mapper.UserMapper;
import com.pregnant_mannage.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper UserMapper;

    public List<User> getUserListbydoctorid(String doctorid) {

        System.out.println("UserService");

        System.out.println(doctorid);
        String where_condition = " where exam_paper.doctorid="+doctorid+" and user.userid=exam_paper.userid";

        System.out.println("queryUserList");
        return UserMapper.getuserlistbydoctorid(where_condition);
    }

    public List<User> queryAllUser() {
        String where_condition = " where 1=1";
        return UserMapper.queryUserListWhere(where_condition);
    }
    public void updateuser(User user){

        int userid=Integer.parseInt(user.getUserid());
        String username=user.getUsername();
        String age=user.getAge();
        String tel=user.getTel();
        String pregnant_time=user.getPregnant_time();
        String describes=user.getDescribes();



        String sqlTxt = "update  user set username='"+username+"', age='"+age+"',tel='"+tel+
                "',pregnant_time='"+pregnant_time+"',describes='"+describes+"'  " +
                "where userid="+userid+"";
        //2:调用springUtil获得数据库访问类。并执行相应代码
        //并且通过(JdbcTemplate)对获取的对象(默认为Object类型)
        // 进行强类型化转换为JdbcTemplate类型的对象。
        System.out.println(sqlTxt);
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");//获取连接数据库的jdbcTemplate对象
        jdbcTemplate.update(sqlTxt);//用jdbcTemplate对象通过sql语句对数据库进行查询。

    }


    public List<User> checkloginUser(String Userid,String pwd) {

        System.out.println("checkloginUser");

        System.out.println(Userid+"  "+pwd);
        String where_condition = " where Userid="+Userid+" and pwd="+pwd;

        System.out.println("queryUserList");
        System.out.println(UserMapper.queryUserListWhere(where_condition).size()==1);
        List<User> user=  UserMapper.queryUserListWhere(where_condition);

        if(user.size()==1){
            return user;}
        else return null;


    }

    public boolean insertintoUser(String Userid, String Userpwd){
        String sqlTxt = "insert into User (Userid,pwd) values ('"+Userid+"','"
                +Userpwd+" ')";
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");//获取连接数据库的jdbcTemplate对象
        jdbcTemplate.update(sqlTxt);//用jdbcTemplate对象通过sql语句对数据库进行查询。
        return true;
    }


    //后台
    public List<User> queryUserList(int current_page, int page_size, String field_name,
                                    String field_value) {
   String where_condition = " where 1=1 ";
        if (!field_name.equals("default")) {
            where_condition += " field_name like '%" + field_value + "%' ";
        }
        return UserMapper.queryUserListWhereaddpage(current_page, page_size, where_condition);
    }


    public User getAEditUser(String userid){
        //1:初始化
        String sqlTxt = "select * from user where userid = '" + userid + "'";
        User user = null;
        //2:调用springUtil获得数据库访问类。并执行相应代码
        //并且通过(JdbcTemplate)对获取的对象(默认为Object类型)
        // 进行强类型化转换为JdbcTemplate类型的对象。
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");//获取连接数据库的jdbcTemplate对象
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);//用jdbcTemplate对象通过sql语句对数据库进行查询。
        if (list.size() > 0){//以下代码是把list类型的数据转换成User类型的数据
            user = new User();
            for (Map<String, Object> map : list) {
                //map.get("user_code")的user_code数据库对应表的列名，
                // 不是User类里面的属性名，虽然名字相同，但是意义不一样
                user.setUserid(map.get("userid").toString());
                user.setUsername(map.get("username").toString());
                user.setTel(map.get("tel").toString());
                user.setPregnant_time(map.get("Pregnant_time").toString());
                user.setAge(map.get("age").toString());
                user.setDescribes(map.get("Describes").toString());
            }
        }
        //3:返回结果
        return user;
    }
    public boolean updateuser_pc(User user){

        boolean issuccess=false;
        int userid=Integer.parseInt(user.getUserid());
        String username=user.getUsername();
        String age=user.getAge();
        String tel=user.getTel();
        String pregnant_time=user.getPregnant_time();
        String describes=user.getDescribes();



        String sqlTxt = "update  user set username='"+username+"', age='"+age+"',tel='"+tel+
                "',pregnant_time='"+pregnant_time+"',describes='"+describes+"'  " +
                "where userid="+userid+"";
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


    public boolean  deleteuserbyid(String userid){

        String sqlTxt = "delete from user where userid="+userid+"";
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
