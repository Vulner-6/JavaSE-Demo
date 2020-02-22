package com.morebaozi;

import java.util.concurrent.locks.Lock;

/**
 * 消费者每隔2秒就吃掉一个包子，没有包子就喊话“没有包子了”
 */
public class XiaoFeiZhe implements Runnable
{
    Lock l;
    BaoziNew baoziNew;

    public XiaoFeiZhe(BaoziNew baoziNew, Lock l)
    {
        this.l = l;
        this.baoziNew = baoziNew;
    }

    public void run()
    {
        //拿走一个包子
        int temp = 0;
        try
        {
            this.l.lock();
            if (this.baoziNew.num > 0)
            {
                temp = this.baoziNew.num;
                this.baoziNew.num--;
            }
            else
            {
                System.out.format("消费者说现在没有包子了…………%n");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            l.unlock();
        }

        try
        {
            if (temp > 0)
            {
                Thread.sleep(2000);
                System.out.format("消费者说：我之前拿包子的时候，还有%d个包子，现在我花两秒吃完这个包子了%n", temp);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
