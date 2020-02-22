package com.study_threadpool;

/**
 * 测试执行不同任务的线程能否放到同一个线程池中
 */
public class AnotherRun implements Runnable
{
    public void run()
    {
        System.out.format("-----执行其他任务的线程，等待1秒-------%n");
        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
