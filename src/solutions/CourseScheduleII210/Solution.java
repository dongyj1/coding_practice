package solutions.CourseScheduleII210;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by dyj on 4/30/18.
 */
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] res = new int[numCourses];
        int index = 0;

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int pre = queue.poll();
            res[index++] = pre;
            for (int[] pair : prerequisites) {
                if (pre == pair[1]) {
                    indegree[pair[0]]--;
                    if (indegree[pair[0]] == 0) {
                        queue.add(pair[0]);
                    }
                }
            }
        }

        return (index == numCourses) ? res : new int[0];
    }
}
