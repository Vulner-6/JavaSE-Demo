package com.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class StartDruid
{
    public static void main(String[] args)
    {
        //1.导入jar包
        //2.获取配置文件
        Properties properties = new Properties();
        InputStream inputStream = StartDruid.class.getClassLoader().getResourceAsStream("druid.properties");
        try
        {
            properties.load(inputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //3.创建数据库连接池对象
        DataSource dataSource = null;
        try
        {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //4.获取连接
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            //先用基本的jdbc进行查询
            connection = dataSource.getConnection();
            String sql = "SELECT * FROM users";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                String name = rs.getString("name");
                System.out.println(name);
            }
            System.out.println(rs.toString());
            System.out.println("=====================================================================");
            //使用spring的jdbcTemplate进行查询
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(dataSource);
            List list = jdbcTemplate.queryForList(sql);
            System.out.println(list);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                stmt.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
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
