package com.morebaozi;

import com.baozi.BaoZi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 做包子线程
 * 一个人做一个包子需要5秒，做100个包子，单线程应该500秒。
 * 做好一个包子要上一次菜，上菜需要3秒，单线程上100次菜需要300秒。因此单线程完成这任务需要800秒
 * 设计思路:
 * 传入包子类，显示当前包子数量，等待10秒做出包子,包子数量增加1
 */
public class DoBaozi implements Runnable
{
    BaoziNew bz;
    Lock l;
    Condition condition;

    public DoBaozi(BaoziNew bz, Lock l, Condition condition)
    {
        this.bz = bz;
        this.l = l;
        this.condition = condition;
    }

    public void run()
    {
        //用户获取包子的状态，决定是否做包子，但是又不想影响效率，因此才声明两个暂时保存包子那一刹那状态的变量。
        int bzNum = 0;
        //获取当前一瞬间的包子数量，状态
        try
        {
            l.lock();
            //获取当前这一瞬间包子的数量和状态
            bzNum = this.bz.num;
            System.out.format("%s正在做包子，需要5秒，当前有%d个包子 %n", Thread.currentThread().getName(), bzNum);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            l.unlock();
        }

        //正在做包子
        try
        {
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //做好了一个包子，需要修改包子数量,并发出声明
        try
        {
            l.lock();
            this.bz.num++;
            System.out.format("%s做好了1个包子，现在已经有%d个包子了%n", Thread.currentThread().getName(), this.bz.num);
            this.condition.signal();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            l.unlock();
        }

    }
}
