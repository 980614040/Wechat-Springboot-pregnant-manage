package com.pregnant_mannage.db;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DbManage {
    public DriverManagerDataSource getDataSource() {//用来和数据库建立连接，这样才能访问数据.
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/pregnant manage?serverTimezone=GMT%2B8");
        ds.setPassword("123456");
        ds.setUsername("root");
        return ds;
    }
}
