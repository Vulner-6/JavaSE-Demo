package com.study_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SellTickets implements Runnable {
    private int ticketNum=100;
    //使用同步锁，来防止条件竞争这种漏洞
    Lock lock=new ReentrantLock();
    public void run(){
        while (true){
            //try-catch-finally，多使用这种格式，可以避免死锁
            try{
                this.lock.lock();
                if(this.ticketNum>0){
                    System.out.format("正在卖第%d张票 %n",this.ticketNum);
                    this.ticketNum--;
                }else {
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                this.lock.unlock();
            }

        }

    }
}
