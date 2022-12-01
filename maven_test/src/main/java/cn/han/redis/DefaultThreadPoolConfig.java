package cn.han.redis;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author han_s
 * @Date 2022/10/27 13:36
 * @ProName maven_test
 * 重写默认线程池
 */
@Configuration
@EnableAsync
public class DefaultThreadPoolConfig implements AsyncConfigurer  {
    /*核心线程数*/
    private int corePoolSize =10;
    /*最大线程数*/
    private int maxPoolSize =20;
    /*空闲线程回收时间*/
    private int keepAliveTime = 300;
    /*任务队列容量*/
    private int queueSize = 50;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setQueueCapacity(queueSize);
        /*线程名字前缀*/
        executor.setThreadNamePrefix("default-test");
        /*
        * 当核心线程达到最大线程数的策略
        * 默认拒绝策略，AbortPolicy中止
        * CallerRunsPolicy策略:由调用者所在的线程来执行
        * */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        AsyncUncaughtExceptionHandler handler = new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                System.out.println("异常信息："+ex.getMessage());
                System.out.println("异常方法名称："+method.getName());
            }
        };
        return handler;
    }
}
