package com.study_jdbc;

import java.sql.*;
import java.util.Scanner;

public class Jdbc_LoginDemo
{
    /**
     * 登录功能。但是由于我的表中没有密码，所以就用age代替。
     * @param username
     * @param password
     * @return boolean
     */
    public static boolean login(String username,String password)
    {
        Connection localhost=null;
        Statement stmt=null;
        String querySql="SELECT * FROM users WHERE NAME ='"+username+"' AND age='"+password+"'";
        ResultSet resultSet=null;
        try
        {
            localhost = Jdbc_Utils.getConnection();
            stmt=localhost.createStatement();
            resultSet=stmt.executeQuery(querySql);
            if(resultSet.next())
            {
                System.out.println("登录成功！");
                return true;
            }
            else
            {
                System.out.println("登录失败！");
                return false;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //释放资源
            Jdbc_Utils.close(resultSet,stmt,localhost);
        }
        return false;
    }

    public static boolean login(String username ,String password,boolean secure)
    {
        if(secure)
        {
            Connection localhost=null;
            PreparedStatement pstmt=null;
            String querySql="SELECT * FROM users WHERE NAME = ? AND age= ?";
            ResultSet resultSet=null;
            try
            {
                localhost=Jdbc_Utils.getConnection();
                //利用preparedStatement进行sql查询
                pstmt=localhost.prepareStatement(querySql);
                pstmt.setString(1,username);
                pstmt.setString(2,password);
                resultSet=pstmt.executeQuery();
                if(resultSet.next())
                {
                    System.out.println("登录成功");
                    return true;
                }
                else
                {
                    System.out.println("登录失败");
                    return false;
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                Jdbc_Utils.close(resultSet,pstmt,localhost);
            }
            return false;
        }
        else
        {
            System.out.println("Jdbc_LoginDemo.login()第三个参数必须为true");
            return false;
        }


    }
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入名字：");
        String username=scanner.next();
        System.out.println("请输入密码/age：");
        String password=scanner.next();
        login(username,password,true);
    }
}
