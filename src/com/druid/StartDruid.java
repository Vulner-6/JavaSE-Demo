package com.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class StartDruid
{
    public static void main(String[] args)
    {
        //1.导入jar包
        //2.获取配置文件
        Properties properties=new Properties();
        InputStream inputStream=StartDruid.class.getClassLoader().getResourceAsStream("druid.properties");
        try
        {
            properties.load(inputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //3.创建数据库连接池对象
        DataSource dataSource=null;
        try
        {
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //4.获取连接
        Connection connection=null;
        try
        {
            connection=dataSource.getConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
