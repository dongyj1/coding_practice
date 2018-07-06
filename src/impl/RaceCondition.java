package impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dyj on 4/15/18.
 * can use lock and sync
 */
public class RaceCondition {
    public static int num = 1000000;
    public static void main(String[] args) {
        Thread a = new Thread(new MyThread(1));
        Thread b = new Thread(new MyThread(2));
        a.start();
        b.start();
        try {
            a.join();
            b.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(num);
    }
}
class MyThread implements Runnable {

    private int id;

    public MyThread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
                if (id == 1) {
                    for (int i = 0; i < 10000; i++)
                        RaceCondition.num--;
                } else {
                    for (int i = 0; i < 10000; i++)
                        RaceCondition.num++;
                }
    }
}
