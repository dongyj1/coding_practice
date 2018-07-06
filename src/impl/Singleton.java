package impl;

/**
 * Created by dyj on 4/16/18.
 */
public class Singleton {
    private static Singleton instance = new Singleton();
    private Singleton() {
    }
    // double-checked locking
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
    public void showMessage() {
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        singleton.showMessage();
    }
}
