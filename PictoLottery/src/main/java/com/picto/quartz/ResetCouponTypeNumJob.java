package com.picto.quartz;

/**
 * Created by BF100271 on 2016/6/22.
 */
public class ResetCouponTypeNumJob {
    public void execute() {
        System.out.println("========开始执行定时任务=========");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("========执行定时任务结束=========");
    }
}
