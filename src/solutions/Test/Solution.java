package solutions.Test;

import java.util.HashSet;
import java.util.*;

class Solution {

    List<Integer> list;
    public Solution() {
        list = new ArrayList<>();
    }
    
    public int next(int price) {
        list.add(price);
        int c = 0, i = list.size() - 1;
        while (i >= 0 && list.get(i) <= price) {
            i--;
            c++;
        }
        return c;
    }
    public static void main(String[] s) {
    
        Solution ss = new Solution();
        int[] in = new int[]{100, 80, 60, 70, 60, 75, 85};
        for (int i : in) {
            System.out.println(ss.next(i));
        }
    }
}

