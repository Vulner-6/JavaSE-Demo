package com.file;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Output
{
    public static void main(String[] args)
    {
        FileOutputStream fos=null;
        try
        {
            fos=new FileOutputStream("testOutput.txt");
            fos.write(97);    //写入97，文本中显示a。97-->二进制-->文本打开，二进制范围在1-127默认查询ascii码，因此显示a。
            //写入字节数组
            byte[] bytes={65,66,67,68};
            fos.write(bytes);
            //写入字符串
            byte[] bytes2="你好".getBytes();
            System.out.println("\"你好\"的字节显示是："+ Arrays.toString(bytes2));
            fos.write(bytes2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fos.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }


}
