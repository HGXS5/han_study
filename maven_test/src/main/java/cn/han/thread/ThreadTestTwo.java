package cn.han.thread;

/**
 * @Author han_s
 * @Date 2022/8/31 14:05
 * @ProName maven_test
 */
public class ThreadTestTwo {
    public static void main(String[] args) {
        new Thread(new WaitingTime(),"time-one").start();//期望-超时等待状态
        new Thread(new WaitingTime(),"time-two").start();//期望-超时等待状态
        new Thread(new WaitingState(),"state-one").start();//期望-等待状态
        new Thread(new WaitingState(),"state-two").start();//期望-等待状态
        new Thread(new BlockedThread(),"blocked-one").start();//期望-超时等待状态
        new Thread(new BlockedThread(),"blocked-two").start();//期望-阻塞状态
    }
}
