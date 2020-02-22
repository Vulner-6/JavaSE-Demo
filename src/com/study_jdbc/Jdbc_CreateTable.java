package com.study_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc_CreateTable
{
    public static void main(String[] args)
    {
        Connection localhost = null;
        Statement stmt = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            localhost = DriverManager.getConnection("jdbc:mysql://localhost:3306/study_jdbc?useUnicode=true" + "&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT", "root", "root");
            stmt = localhost.createStatement();
            String createTableSql = "CREATE TABLE testCreateTable (id int,name varchar(20) )";
            int count = stmt.executeUpdate(createTableSql);
            System.out.println(count);
            System.out.println("由于创建表没有增加数据，所以执行sql语句后，返回的是0");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
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
