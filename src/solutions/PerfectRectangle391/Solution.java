package solutions.PerfectRectangle391;

import java.util.HashSet;

/**
 * Created by dyj on 4/21/18.
 * Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

 Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).
 rectangles = [
 [1,1,3,3],
 [3,1,4,2],
 [3,2,4,4],
 [1,3,2,4],
 [2,3,3,4]
 ]
 */

class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length <= 0 || rectangles[0].length <= 0) {
            return false;
        }
        int Xmin = Integer.MAX_VALUE, Xmax = Integer.MIN_VALUE, Ymin = Integer.MAX_VALUE, Ymax = Integer.MIN_VALUE;
        HashSet<String> set = new HashSet<>();
        int totalArea = 0;

        for (int[] rectangle : rectangles) {
            Xmin = Math.min(rectangle[0], Xmin);
            Xmax = Math.max(Xmax, rectangle[2]);
            Ymin = Math.min(Ymin, rectangle[1]);
            Ymax = Math.max(Ymax, rectangle[3]);

            totalArea += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);

            String p1 = rectangle[0] + " " + rectangle[1];
            String p2 = rectangle[0] + " " + rectangle[3];
            String p3 = rectangle[2] + " " + rectangle[1];
            String p4 = rectangle[2] + " " + rectangle[3];

            if (!set.add(p1)) set.remove(p1);
            if (!set.add(p2)) set.remove(p2);
            if (!set.add(p3)) set.remove(p3);
            if (!set.add(p4)) set.remove(p4);

        }
        boolean areaEqual = totalArea == (Xmax - Xmin) * (Ymax - Ymin);
        boolean cornerEqual = (set.size() == 4)
                && set.contains(Xmin + " " + Ymin)
                && set.contains(Xmin + " " + Ymax)
                && set.contains(Xmax + " " + Ymin)
                && set.contains(Xmax + " " + Ymax);
        return areaEqual
                && cornerEqual;
    }

    public static void main(String[] args) {
        int[][] rectangles = { {1,1,3,3}, {3,1,4,2}, {1,3,2,4}, {2,3,3,4}};
        Solution solution = new Solution();
        System.out.println(solution.isRectangleCover(rectangles));
    }
}
