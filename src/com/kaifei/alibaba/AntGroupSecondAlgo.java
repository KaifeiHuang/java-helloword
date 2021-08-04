package com.kaifei.alibaba;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 使用代码实现一个限流逻辑，其他请求拒绝，请使用令牌桶算法实现
 */
public class AntGroupSecondAlgo {

    /**
     * init token capacity
     */
    public static int INIT_TOKEN_CAPACITY = 10;

    /**
     * the rate of the generating token
     */
    public static final int GENERATE_TOKEN_RATE = 10;

    /**
     * the number of current tokens
     */
    public int tokens;

    /**
     * current time
     */
    public long timeStamp = System.currentTimeMillis();

    private ScheduledExecutorService scheduledExecutorService =
            Executors.newScheduledThreadPool(10);
    /**
     * handle request with token
     */
    public void handRequestWithTokenLimit()
    {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {

            // 当前令牌数
            tokens = generateTokens();
            System.out.println("=====: " + tokens);
            // 模拟定期发送请求
            int requests = (int) (Math.random() * 9) + 1;
            System.out.println(("收到请求数：" + requests + "，当前令牌数：" + tokens));
            timeStamp = System.currentTimeMillis();
            applyTokenForRequest(requests);

        }, 2000, 100, TimeUnit.MILLISECONDS);
    }

    public int generateTokens() {
        long now = System.currentTimeMillis();
        return Math.min(INIT_TOKEN_CAPACITY, (int) (tokens + (now - timeStamp) * GENERATE_TOKEN_RATE / 1000));
    }

    public void applyTokenForRequest(int requests) {
        if (tokens < requests)
        {
            // 若申请不到令牌,则拒绝
            System.out.println("当前无令牌可以申请，暂时无法提供服务");
        }
        else
        {
            // 申请令牌
            tokens -= requests;
            System.out.println(("剩余令牌=" + tokens));
        }
    }


    public static void main(String[] args) {
        AntGroupSecondAlgo requestHandler = new AntGroupSecondAlgo();
        requestHandler.handRequestWithTokenLimit();
    }
}
