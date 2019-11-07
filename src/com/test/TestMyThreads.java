package com.test;

import com.study_thread.Hero;
import com.study_thread.MyRunable;
import com.study_thread.MyThreads;
import org.junit.jupiter.api.Test;

public class TestMyThreads {
    //多线程无法进行单元测试，需要使用其他的方法。
    Hero gareen=new Hero("盖伦",100,10);
    Hero teemo=new Hero("提莫",50,4);
    Hero bh=new Hero("赏金猎人",60,4);
    Hero leesin=new Hero("盲僧",80,100);

    @Test
    /**
     * 单元测试，可以对实现Runable接口的线程进行测试，却不能测试 Thread 类
     */
    public void testMyThreads(){
        //盖伦打死提莫,盲僧打死赏金猎人
        MyThreads gareenKillTeemo=new MyThreads(this.gareen,this.teemo);
        MyThreads leesinKillBh=new MyThreads(this.leesin,this.bh);
        gareenKillTeemo.start();
        leesinKillBh.start();

        //同时开启加血模式的线程
        MyRunable gareenBattle = new MyRunable(this.gareen,this.teemo);
        Thread myRunable=new Thread(gareenBattle);
        myRunable.start();

        //测试join()方法，加入到主线程，直到这个子线程执行结束，才会继续执行主线程
        try{
            myRunable.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        /*
        //测试代码是再main方法中执行的，为了能够进行测试，这里进行sleep()
        try{
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        */
    }

    @Test
    public void testBoDongQuan(){
        Hero leesin=new Hero("盲僧",100,5);
        Thread t1=new Thread(){
            public void run(){
                while(true){
                    leesin.adugen();
                    try{
                        System.out.format("波动拳放完了，充能5秒钟");
                        Thread.sleep(5000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        try {
            t1.join();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
