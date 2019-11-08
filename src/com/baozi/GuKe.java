package com.baozi;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 顾客需要吃包子
 *     加锁，访问包子数据，判断包子是否存在
 *     存在，吃包子，吃完包子唤醒包子铺做包子
 *     不存在，等待，等待被包子铺唤醒
 */
public class GuKe implements Runnable{
    //用于接收共享数据包子对象
    BaoZi baoZi;
    BaoZiPu baoZiPu;

    //用于上锁
    Lock l;
    Condition condition;   //用于唤醒和等待
    //接收包子
    public GuKe(BaoZi baoZi,Lock l,Condition condition){
        this.baoZi=baoZi;
        this.l=l;
        this.condition=condition;
    }


    public void run(){
        while(true){
            try{
                l.lock();
                if(this.baoZi.exists){
                    System.out.format("有包子，顾客正在吃包子，需要2秒等待 %n");
                    Thread.sleep(2000);
                    System.out.format("包子吃完了 %n");
                    //唤醒包子铺做包子
                    this.baoZi.exists=false;
                    condition.signal();
                }
                else {
                    //就等待包子铺唤醒我吃包子
                    condition.await();
                }
            } catch (Exception e){
                e.printStackTrace();
            }finally {
                l.unlock();
            }
        }
    }
}
