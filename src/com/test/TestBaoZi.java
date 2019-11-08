package com.test;

import com.baozi.BaoZi;
import com.baozi.BaoZiPu;
import com.baozi.GuKe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试多线程协作，包子铺做包子，顾客吃包子，相互协作，无限循环
 */
public class TestBaoZi {
    public static void main(String[] args){
        BaoZi baoZi=new BaoZi();
        //声明锁、以及唤醒和等待机制。用于什么时候锁，什么时候唤醒，什么时候沉睡。
        Lock l=new ReentrantLock();
        Condition condition=l.newCondition();

        BaoZiPu baoZiPu=new BaoZiPu(baoZi,l,condition);
        GuKe guKe=new GuKe(baoZi,l,condition);
        //开启包子铺线程做包子
        Thread zuoBaoZi=new Thread(baoZiPu);
        zuoBaoZi.start();

        //开启顾客吃包子
        Thread chiBaoZi=new Thread(guKe);
        chiBaoZi.start();



    }
}
