package com.crackpwd;

public class MyDaemon implements Runnable
{
    int index = 0;

    public void run()
    {
        while (true)
        {
            index++;
            System.out.format("守护线程index的值是：%d %n", index);
        }
    }
}
