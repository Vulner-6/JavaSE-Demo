package com.study_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 写的更规范一点
 * 练习增加数据
 */
public class Jdbc_insert
{
    public static void main(String[] args)
    {
        Statement stmt = null;
        Connection localhost = null;
        try
        {
            //注册数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立数据库连接
            localhost = DriverManager.getConnection("jdbc:mysql://localhost:3306/study_jdbc?useUnicode=true" + "&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT", "root", "root");
            //创建statement
            stmt = localhost.createStatement();
            //定义sql执行语句
            String sql = "INSERT INTO users VALUES(null,'wan',23) ";
            //执行sql语句
            int num = stmt.executeUpdate(sql);
            //查看更改的数量
            System.out.println(num);
            if (num > 0)
            {
                System.out.println("执行成功");
            }
            else
            {
                System.out.println("执行失败");
            }
        }
        catch (ClassNotFoundException e)
        {
            //捕获注册失败异常
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            //捕获sql执行失败异常
            e.printStackTrace();
        }
        finally
        {
            //释放资源
            //先判断不为空
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            //先判断不为空
            if (localhost != null)
            {
                try
                {
                    localhost.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }
}
