package com.morebaozi;

import com.baozi.BaoZi;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个做包子线程，一个上菜线程，一个吃包子线程
 */
public class Main
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        //线程锁、唤醒等待
        Lock l = new ReentrantLock();
        Condition condition1 = l.newCondition();
        //声明包子类
        BaoziNew baoziNew = new BaoziNew();

        //声明做包子线程和上菜线程,以及消费者线程
        Thread zuoBaoZi = new Thread(new DoBaozi(baoziNew, l, condition1));
        Thread shangCai = new Thread(new ShangCai(baoziNew, l, condition1));
        Thread chiBaoZi = new Thread(new XiaoFeiZhe(baoziNew, l));
        ExecutorService es = Executors.newFixedThreadPool(5);

        int test = 0;
        while (test < 100)
        {
            //循环5个线程做5个包子，看耗时
            for (int i = 0; i < 5; i++)
            {
                es.submit(zuoBaoZi);
            }
            try
            {
                Thread.sleep(5050);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            //做好包子再上菜
            for (int i = 0; i < 4; i++)
            {
                es.submit(shangCai);
            }

            //消费者吃包子

            for (int i = 0; i < 3; i++)
            {
                es.submit(chiBaoZi);
            }
            test++;
            try
            {
                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        long stopTime = System.currentTimeMillis();
        long spendTime = stopTime - startTime;
        System.out.println("共耗时：" + spendTime);
    }
}
