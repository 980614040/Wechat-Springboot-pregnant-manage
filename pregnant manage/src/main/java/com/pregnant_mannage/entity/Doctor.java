package com.pregnant_mannage.entity;

public class Doctor {
    String Doctorid;
    String Doctorname;
    String sex;
    String age;
    String pwd;
    String describes;
    String src;
    String  Workyear;
    String post;

    public String getWorkyear() {
        return Workyear;
    }

    public void setWorkyear(String workyear) {
        Workyear = workyear;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDoctorid() {
        return Doctorid;
    }

    public void setDoctorid(String Doctorid) {
        this.Doctorid = Doctorid;
    }

    public String getDoctorname() {
        return Doctorname;
    }

    public void setDoctorname(String Doctorname) {
        this.Doctorname = Doctorname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
}
