package com.study_jdbc;

import java.sql.*;

public class Jdbc_ResultSet {
    public static void main(String[] args){
        Connection localhost=null;
        Statement stmt=null;
        ResultSet resultSet=null;   //这个ResultSet也会占用资源
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            localhost = DriverManager.getConnection("jdbc:mysql://localhost:3306/study_jdbc?useUnicode=true" +
                    "&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT","root","root");
            stmt=localhost.createStatement();
            String sql="SELECT * FROM users";
            //执行查询语句，获取结果集
            resultSet =stmt.executeQuery(sql);
            //遍历结果集，打印输出
            while(resultSet.next()){
                System.out.println("姓名："+resultSet.getString("name")+"  年龄："+resultSet.getInt("age"));
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            //释放资源，不能忘了ResultSet，也要释放资源
            if(resultSet!=null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(stmt!=null){
                try {
                    stmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(localhost!=null){
                try {
                    localhost.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
