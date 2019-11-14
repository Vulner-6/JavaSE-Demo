package com.study_jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * 封装JDBC工具类，为了方便调用，建议用静态方法进行封装
 *   1.抽取一个方法，注册驱动（结合配置文件）
 *   2.抽取一个方法，获得连接对象
 *   3.抽取一个方法，释放资源
 */
public class Jdbc_Utils
{
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    /**
     * 静态代码块，随着类的加载而加载，并且只加载一次
     * 用于读取配置文件中的信息
     */
    static {
        try{
            //1.创建Properties集合类
            Properties properties=new Properties();
            //2.加载文件,利用ClassLoader获取src路径
            ClassLoader classLoader=Jdbc_Utils.class.getClassLoader();
            URL res=classLoader.getResource("com/study_jdbc/jdbc.properties");  //src下的路径
            String path=res.getPath();
            properties.load(new FileReader(path));
            /*
            properties.load(new FileReader("E:\\Programming\\Projects\\JavaSE-Demo\\src\\com\\study_jdbc\\jdbc" +
                    ".properties"));
            */

            //3.获取数据，赋值
            url=properties.getProperty("url");
            user=properties.getProperty("user");
            password=properties.getProperty("password");
            driver=properties.getProperty("driver");
            //4.注册驱动
            Class.forName(driver);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接
     * @return 连接对象
     */
    public static Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放资源，针对增删改，没有查
     * @param stmt
     * @param connection
     */
    public static void close(Statement stmt,Connection connection)
    {
        //之所以不放在一起判断，是因为怕捕获异常导致后面部分资源的释放代码无法执行
        if(stmt!=null)
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
        if(connection!=null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放资源，针对增删改，没有查
     * @param pstmt
     * @param connection
     */
    public static void close(PreparedStatement pstmt,Connection connection)
    {
        //之所以不放在一起判断，是因为怕捕获异常导致后面部分资源的释放代码无法执行
        if(pstmt!=null)
        {
            try
            {
                pstmt.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        if(connection!=null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 利用方法重写的方式，自动根据传入的参数释放资源
     * @param resultSet
     * @param stmt
     * @param connection
     */
    public static void close(ResultSet resultSet, Statement stmt, Connection connection)
    {
        //之所以不放在一起判断，是因为怕捕获异常导致后面部分资源的释放代码无法执行
        if(resultSet!=null)
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
        if(stmt!=null)
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
        if(connection!=null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 利用方法重写的方式，自动根据传入的参数释放资源，这里重写PreparedStatement
     * @param resultSet
     * @param pstmt
     * @param connection
     */
    public static void close(ResultSet resultSet, PreparedStatement pstmt, Connection connection)
    {
        //之所以不放在一起判断，是因为怕捕获异常导致后面部分资源的释放代码无法执行
        if(resultSet!=null)
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
        if(pstmt!=null)
        {
            try
            {
                pstmt.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        if(connection!=null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
