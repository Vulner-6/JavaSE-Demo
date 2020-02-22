package com.study_threadpool;

public class RunnableImpl implements Runnable
{
    public void run()
    {
        System.out.format("%s创建了一个新的线程并执行6秒 %n", Thread.currentThread().getName());
        try
        {
            Thread.sleep(6000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
