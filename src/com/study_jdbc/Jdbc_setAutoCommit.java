package com.study_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC事务操作。
 * 我这里事务管理失败了。不知道为什么。
 * 可能是因为我用的是mysql
 */
public class Jdbc_setAutoCommit
{
    public static void main(String[] args)
    {
        Connection localhost=null;
        PreparedStatement pstmt1=null;
        PreparedStatement pstmt2=null;
        try
        {
            //1.获取连接
            localhost=Jdbc_Utils.getConnection();
            String updateSQL1="UPDATE users SET name = ? WHERE id = ?";
            //2.开启事务，事务范围内的代码必须全都正确，事务才能执行，否则事务无法执行。用于收付款操作。
            localhost.setAutoCommit(false);
            pstmt1=localhost.prepareStatement(updateSQL1);
            pstmt1.setString(1,"wahaha4");
            pstmt1.setInt(2,1);
            pstmt1.executeUpdate();
            String updateSQL2="UPDATE users SET name = ? WHERE id = ?";
            pstmt2=localhost.prepareStatement(updateSQL2);
            pstmt2.setString(1,"ruwawa1");
            pstmt2.setInt(2,2);
            //人工制造异常，让事务范围内的代码没有全部执行正确
            int i=4/0;
            pstmt2.executeUpdate();
            //提交事务,事务范围内的代码必须全都正确，代码才生效。出现异常就需要在catch中回滚一下
            localhost.commit();
        }
        catch (Exception e)
        {
            //回滚事务,注意连接不能为null,并且由于上面是int异常，所以上面抓应该抓Exception，而不是SQLException

            try
            {
                if(localhost!=null)
                {
                    localhost.rollback();
                }
            }
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
            }
            e.printStackTrace();
        }
        finally
        {
            //释放资源，工具类中如果某个资源不需要释放，就传入null
            Jdbc_Utils.close(pstmt1,localhost);
            Jdbc_Utils.close(pstmt2,null);

        }
    }

}
