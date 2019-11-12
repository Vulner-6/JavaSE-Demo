package com.study_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc_update {
    public static void main(String[] args){
        Connection localhost=null;
        Statement stmt=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            localhost= DriverManager.getConnection("jdbc:mysql://localhost:3306/study_jdbc?useUnicode=true" +
                    "&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT","root","root");
            stmt=localhost.createStatement();
            String updateSql="UPDATE users SET NAME = 'updateTest' WHERE id=2";
            int count=stmt.executeUpdate(updateSql);
            System.out.println(count);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(stmt!=null){
                try{
                    stmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(localhost!=null){
                try{
                    localhost.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }
}
