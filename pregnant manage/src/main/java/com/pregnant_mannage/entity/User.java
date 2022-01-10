package com.pregnant_mannage.entity;
//entity包一般用来存储对应数据库表结构的实体类
//实体类一般都是用来给service和controller mapper等提高辅助服务的。
public class User {

    private String userid ;
    private String username ;
    private String age ;
    private String pwd ;
    private String describes ;
    private String  src ;
    private String Pregnant_time ;
    private String tel;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getPregnant_time() {
        return Pregnant_time;
    }

    public void setPregnant_time(String pregnant_time) {
        Pregnant_time = pregnant_time;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
