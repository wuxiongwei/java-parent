package com.wuxiongwei.java.touse.java.multi_thread.concurrency;

import java.util.Date;

/**
 * <p>
 * Copyright: Copyright (c) 2020/4/14  2:08 下午
 * <p>
 * Company: 苏州渠成易销网络科技有限公司
 * <p>
 *
 * @author xiongwei.wu@successchannel.com
 * @version 1.0.0
 */
public class RunnableT implements Runnable {

    private String command;

    public RunnableT(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}
