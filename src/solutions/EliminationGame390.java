package solutions;

/**
 * Created by dyj on 4/13/18.
 */
public class EliminationGame390 {

    public static int lastRemaining(int n) {
        int remaining = n;
        int head = 1, step = 1;
        boolean left = true;

        while (remaining > 1) {
            if (left || remaining % 2 == 1) {
                head += step;
            }
            step *= 2;
            remaining /= 2;
            left = !left;
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining(9));
    }
}
