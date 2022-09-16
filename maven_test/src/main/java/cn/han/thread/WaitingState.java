package cn.han.thread;

/**
 * @Author han_s
 * @Date 2022/8/31 14:37
 * @ProName maven_test
 *
 *来验证线程的WAITING状态
 */
public class WaitingState implements Runnable{
    @Override
    public void run() {
        while (true){
            synchronized (WaitingState.class){
                try {
                    System.out.println(Thread.currentThread().getName()+"执行");
                    WaitingState.class.wait();
                    System.out.println(Thread.currentThread().getName()+"结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
