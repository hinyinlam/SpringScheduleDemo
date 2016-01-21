package com.pivotal;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Created by hinlam on 19/1/16.
 */
@Configuration
@EnableAutoConfiguration
@EnableAsync
@EnableScheduling
public class SchedulerConfiguration {

    @Bean
    public ThreadPoolTaskExecutor getExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(25);
        System.out.println("creating executor");
        return threadPoolTaskExecutor;
    }

    @Bean
    public ThreadPoolTaskScheduler getScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        return threadPoolTaskScheduler;
    }

}
