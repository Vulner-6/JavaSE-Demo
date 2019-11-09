package com.morebaozi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 上菜不需要知道包子数量，只要做包子的人叫它上菜，那么它就花3秒上菜
 */
public class ShangCai implements Runnable {
    BaoziNew bz=new BaoziNew();
    Lock l;
    Condition condition;
    public ShangCai(BaoziNew bz, Lock l, Condition condition){
        this.bz=bz;
        this.l=l;
        this.condition=condition;
    }
    public void run(){
        int bzNum=0;
        //查看当前菜的数量，并报出来
        try{
            l.lock();
            bzNum=this.bz.num;
            if(bzNum>0){
                System.out.format("%s正在上菜，请稍等3秒,目前有%d个包子(包子数量大于0才对) %n",Thread.currentThread().getName(),bzNum);
                this.bz.num--;
            }
            else{
                condition.await();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            l.unlock();
        }
        //如果当前上菜线程发现包子数量大于0，才上菜
        if(bzNum>0){
            //上菜中，等3秒
            try{
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }

            //菜上好了
            try{
                l.lock();
                System.out.format("%s菜上好了,现在还有%d个包子 %n",Thread.currentThread().getName(),this.bz.num);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                l.unlock();
            }
        }
        else {
            System.out.format("没有包子上了%n");
        }
    }
}
