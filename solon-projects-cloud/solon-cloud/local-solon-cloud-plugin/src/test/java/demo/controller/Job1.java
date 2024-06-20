package demo.controller;

import org.noear.solon.cloud.CloudClient;
import org.noear.solon.cloud.CloudJobHandler;
import org.noear.solon.cloud.annotation.CloudJob;
import org.noear.solon.cloud.model.Event;
import org.noear.solon.core.handle.Context;

import java.util.Date;

/**
 * 云端调度的定时任务（本地实现时，就在本地调试了）
 */
@CloudJob(name = "job1",cron7x = "* * * * * ?")
public class Job1 implements CloudJobHandler {
    @Override
    public void handle(Context ctx) throws Throwable {
        System.out.println("云端定时任务：job1:" + new Date());

        //发送云端事件
        CloudClient.event().publish(new Event("demo.event1", "test"));
    }
}
