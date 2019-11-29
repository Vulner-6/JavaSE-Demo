package com.jdbc_template;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import com.study_jdbc.Jdbc_Utils;
import org.springframework.jdbc.core.JdbcTemplate;

public class StartJdbcTemplate
{
    public static void main(String[] args)
    {
        //1.准备数据源，这里使用spring内置数据源（也可以自己写个返回DataSource的类，当作形参传入JdbcTemplate）
        DriverManagerDataSource ds=new DriverManagerDataSource();
        ds.setDriverClass("com.mysql.cj.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/study_jdbc?useUnicode=true&characterEncoding=utf-8&useSSL=false" +
                "&serverTimezone = GMT");
        ds.setUser("root");
        ds.setPassword("root");
        //2.给JdbcTemple设置数据源
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(ds);
        //3.执行相关语句
        jdbcTemplate.execute("INSERT INTO users(name,age) VALUES('test',11)");

    }
}
