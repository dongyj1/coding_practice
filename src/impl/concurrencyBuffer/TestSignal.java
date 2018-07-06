package impl.concurrencyBuffer;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by dyj on 4/24/18.
 */
public class TestSignal {

    public static void main(String[] args) {
        //线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        //定义信号量，只能5个线程同时访问
        final Semaphore semaphore = new Semaphore(5);
        //模拟20个线程同时访问
        for (int i = 0; i < 20; i++) {
            final int NO = i;
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        //获取许可
                        semaphore.acquire();
                        //availablePermits()指的是当前信号灯库中有多少个可以被使用
                        System.out.println("线程" + Thread.currentThread().getName() +"进入，当前已有" + (5-semaphore.availablePermits()) + "个并发");
                        System.out.println("index:"+NO);
                        Thread.sleep(new Random().nextInt(1000)*10);

                        System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
                        //访问完后，释放
                        semaphore.release();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            executor.execute(runnable);
        }
        // 退出线程池
        executor.shutdown();
    }
}
