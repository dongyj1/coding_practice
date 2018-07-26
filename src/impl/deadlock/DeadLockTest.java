package impl.deadlock;

/**
 * Created by dyj on 4/20/18.
 */
public class DeadLockTest implements A, B{
    private String name = "";

    @Override
    public synchronized void setNum(String name1) {
        this.name = name1;
    }

    @Override
    public synchronized void printNum() {
        System.out.println(this.name);
    }

    public static void main(String[] args) {
        DeadLockTest deadLockTest = new DeadLockTest();
        A a = deadLockTest;
        B b = deadLockTest;
        Thread ta = new Thread(a, "aa");
        Thread tb = new Thread(b, "tb");
        System.out.println("start");
        ta.start();
        try {
            ta.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tb.start();
        try {
            tb.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
            this.setNum(this.getClass().getSimpleName());
            this.printNum();

    }
}
interface A extends Runnable{
    void setNum(String num);
    void printNum();
}

interface B extends Runnable{
    void setNum(String num);
    void printNum();
}
