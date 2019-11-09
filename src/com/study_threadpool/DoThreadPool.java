package com.study_threadpool;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * 1.创建指定数量的线程池
 * 2.提交任务给线程池
 * 3.所有任务结束后，可以关闭线程池，释放资源。但是一旦关闭了，就不能再提交任务给线程池了。
 */
public class DoThreadPool {
    public static void main(String[] args){
        //创建容纳两个线程的线程池
        ExecutorService es=Executors.newFixedThreadPool(2);
        //调用ExecutorService的submit方法，传递线程任务（实现类），开启线程，执行run方法
        for(int i=0;i<10;i++){
            es.submit(new RunnableImpl());
            System.out.println("for第"+i+"次循环");
        }
        //只有当上面for循环中生成的10个线程都执行完了，才会执行到这里。
        es.submit(new AnotherRun());

    }
}
