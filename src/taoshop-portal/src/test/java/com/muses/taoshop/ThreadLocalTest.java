package com.muses.taoshop;

import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 *  ThreadLocal测试类
 * </pre>
 *
 * @author nicky
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期: 2018.12.22 22:51    修改内容:
 * </pre>
 */
public class ThreadLocalTest implements Runnable {

    private int i ;

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    public ThreadLocalTest(int i){
        this.i = i;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            Date t = threadLocal.get().parse("2018-12-22 19:30:"+i%60);
            System.out.println(i+":"+t.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i =0;i<=1000;i++){
            es.execute(new ThreadLocalTest(i));
        }
        threadLocal.remove();
    }
}
