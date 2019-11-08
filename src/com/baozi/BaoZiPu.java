package com.baozi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 包子铺线程，生产包子
 *     传入包子对象，加锁，判断包子是否存在
 *        存在：线程等待
 *        不存在：轮流制作两种包子馅的包子，修改包子状态，唤醒消费者线程
 */
public class BaoZiPu implements Runnable{
    //声明一个包子类，用来接收传入的包子
    BaoZi baoZi;
    //声明锁，用来加锁和解锁
    Lock l;
    Condition condition;  //用于唤醒和等待
    //接收包子对象
    public BaoZiPu(BaoZi baoZi,Lock l,Condition condition){
        this.baoZi=baoZi;
        this.l=l;
        this.condition=condition;
    }


    public void run(){
        int index=1;
        while (true){
            //访问包子对象的数据，判断包子是否要生产，因此要加锁。用try-catch-finally结构更好。
            try{
                l.lock();
                //如果包子存在，就线程等待被唤醒
                if(this.baoZi.exists){
                    condition.await();    //这里到底是谁等待？？？
                }
                //否则，就制作包子
                else {
                    if(1%2==0){
                        //制作薄皮包子
                        this.baoZi.pi="薄皮";
                        this.baoZi.xian="猪肉馅";
                    }
                    else {
                        //制作厚皮包子
                        this.baoZi.pi="厚皮";
                        this.baoZi.xian="豆沙馅";
                    }
                    System.out.format("正在制作%s%s包子，需要等待3秒%n",this.baoZi.pi,this.baoZi.xian);
                    Thread.sleep(3000);
                    System.out.format("%s%s包子做好了 %n",this.baoZi.pi,this.baoZi.xian);
                    this.baoZi.exists=true;
                    condition.signal();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                l.unlock();
            }
        }



    }
}
