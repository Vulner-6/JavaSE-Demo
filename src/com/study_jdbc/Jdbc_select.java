package com.study_jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询表中数据，并封装成对象
 */
public class Jdbc_select {
    Connection localhost=null;
    Statement stmt=null;
    ResultSet resultSet;
    TestEnity testEnity=new TestEnity();
    List<TestEnity> list=new ArrayList<TestEnity>();
    public List<TestEnity> findAll(){
        //利用封装的工具类进行尝试获取资源，返回list泛型
        this.localhost =Jdbc_Utils.getConnection();
        try{
            this.stmt=localhost.createStatement();
            String querySql="SELECT * FROM users";
            resultSet=this.stmt.executeQuery(querySql);
            while (resultSet.next()){
                testEnity.setId(resultSet.getInt("id"));
                testEnity.setName(resultSet.getString("name"));
                testEnity.setAge(resultSet.getInt("age"));
                list.add(testEnity);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            //释放资源
            Jdbc_Utils.close(resultSet,stmt,localhost);
        }
        return this.list;
    }

    public static void main(String[] args){
        Jdbc_select js=new Jdbc_select();
        List<TestEnity> testEnities=js.findAll();
        System.out.println(testEnities);
    }
}
