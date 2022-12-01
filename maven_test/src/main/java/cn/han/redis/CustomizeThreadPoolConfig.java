package cn.han.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author han_s
 * @Date 2022/10/27 14:33
 * @ProName maven_test
 */
@Configuration
@EnableAsync
public class CustomizeThreadPoolConfig {
    /*核心线程数*/
    private int corePoolSize =10;
    /*最大线程数*/
    private int maxPoolSize =20;
    /*空闲线程回收时间*/
    private int keepAliveTime = 300;
    /*任务队列容量*/
    private int queueSize = 50;

    @Bean("customThreadPool")
    public Executor doCustomizeThreadPoolConfig(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setQueueCapacity(queueSize);
        /*线程名字前缀*/
        executor.setThreadNamePrefix("han-test");
        /*
         * 当核心线程达到最大线程数的策略
         * 默认拒绝策略，AbortPolicy中止
         * CallerRunsPolicy策略:由调用者所在的线程来执行
         * */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
