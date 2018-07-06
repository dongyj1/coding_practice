package impl.concurrencyBuffer;

import java.util.concurrent.Semaphore;

/**
 * Created by dyj on 4/24/18.
 */
public class Buffer {
    private final int capacity = 10;
    private final Semaphore full = new Semaphore(1);
    private final Semaphore empty = new Semaphore(1);
    private final Semaphore mutex = new Semaphore(1);
    private int insertIndex = 0;
    private int removeIndex = 0;
    private final Object[] items = new Object[capacity];
    int count = 0;

    public Object insert(Object item) throws InterruptedException {
        empty.acquire();
        mutex.acquire();
        items[insertIndex++] = item;
        if (insertIndex == capacity) {
            insertIndex = 0;
        }
        count++;
        mutex.release();
        empty.release();
        return item;
    }

    public Object remove() throws InterruptedException {
        full.acquire();
        mutex.acquire();
        Object item = items[removeIndex];
        items[removeIndex++] = null;
        if (removeIndex == capacity) {
            removeIndex = 0;
        }
        count--;
        mutex.release();
        full.release();
        return item;
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

class Producer implements Runnable {
    static int num = 1;
    Buffer buffer;
    public Producer(Buffer b) {
        buffer = b;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Produce : " + buffer.insert(num++));
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {

    Buffer buffer;
    public Consumer(Buffer b) {
        buffer = b;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consume : " + buffer.remove());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
