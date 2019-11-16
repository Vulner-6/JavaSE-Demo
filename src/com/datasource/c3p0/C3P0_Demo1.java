package com.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.CommonDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 1.导入c3p0相关数据包
 * 2.复制粘贴c3p0配置文件。我是从c3p0源码example中复制进来的。
 * 3.编写示例代码
 */
public class C3P0_Demo1
{
    public static void main(String[] args)
    {
        try
        {
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" );
            //我这里必须要加后面的乱七八糟东西，否则会报异常错误。
            cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/study_jdbc?useUnicode=true" +
                    "&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT" );
            cpds.setUser("root");
            cpds.setPassword("root");
            //设置预编译池
            cpds.setMaxStatements( 180 );
            Connection conn=cpds.getConnection();
            System.out.println("未发现异常");
            System.out.println(conn);
            //用完数据库连接池，需要关闭
            cpds.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
