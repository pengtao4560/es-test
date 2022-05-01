package com.atguigu.springcloud.hutooltest;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 代替 long start = System.currentTimeMillis();...的耗时统计工具类：
 * 计秒表 cn.hutool.core.date.StopWatch;
 */
@Slf4j
public class StopWatchTest {

    @Test
    public void test1() {
        StopWatch stopWatch = new StopWatch("测试耗时统计");
        stopWatch.start("子任务1");
        ThreadUtil.sleep(3, TimeUnit.SECONDS);
        stopWatch.stop();
        stopWatch.start("子任务2");
        ThreadUtil.sleep(2, TimeUnit.SECONDS);
        stopWatch.stop();
        stopWatch.start("子任务3");
        ThreadUtil.sleep(5, TimeUnit.SECONDS);
        stopWatch.stop();
        log.info("耗时纳秒（1 毫秒=1000000 纳秒）\n  {}", stopWatch.prettyPrint());
        log.info("-----------------");
        log.info("耗时毫秒： {}", stopWatch.prettyPrint(TimeUnit.MILLISECONDS));

    }
}
