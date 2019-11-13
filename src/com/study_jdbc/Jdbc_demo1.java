package com.study_jdbc;
import java.sql.*;
import com.mysql.jdbc.Driver;
import java.lang.*;
import java.util.concurrent.locks.Condition;

/**
 * 练习连接本地mysql数据库，进行更新数据表操作
 * 1.导入驱动jar包（已经下载、导入了）
 * 2.注册mysql驱动
 * 3.获取数据库连接对象
 * 4.定义sql查询语句
 * 5.获取执行sql对象
 */
public class Jdbc_demo1 {
    public static void main(String[] args) throws Exception{
        //1.导入驱动jar包（已经下载、导入了）
        //2.注册mysql驱动，mysql5以后，不注册也可以，因为mysql的jar包中，再META-INF文件夹中已经注册了。但是最好还是自己再注册一下。
        Class.forName("com.mysql.cj.jdbc.Driver");
        //3.获取数据库连接对象
        Connection localhost = DriverManager.getConnection("jdbc:mysql://localhost:3306/study_jdbc?useUnicode=true" +
                "&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT","root","root");
        //4.定义sql语句
        String sql="UPDATE users SET age=44 WHERE id = 1";
        //5.获取sql对象Statement
        Statement stmt=localhost.createStatement();
        //6.执行sql
        int count=stmt.executeUpdate(sql);
        //7.查看处理结果
        System.out.println(count);
        //8.释放资源
        stmt.close();
        localhost.close();
    }

}
