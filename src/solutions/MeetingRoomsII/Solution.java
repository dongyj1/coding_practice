package solutions.MeetingRoomsII;


import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by dyj on 8/5/18.
 *
 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

 Example 1:

 Input: [[0, 30],[5, 10],[15, 20]]
 Output: 2
 Example 2:

 Input: [[7,10],[2,4]]
 Output: 1

 */
public class Solution {
    // PriorityQueue, use the top record the one used for all
    // time O(nlogn) space O(n)
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        if (intervals.length == 1) return 1;
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));
        PriorityQueue<Interval> pq = new PriorityQueue<>(2, (a,b)->(a.end - b.end)); // end from small to large

        pq.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval cur = pq.poll();
            if (cur.end <= intervals[i].start) { // no intersection
                cur.end = intervals[i].end;
            } else { // intersects
                pq.offer(intervals[i]);
            }
            pq.offer(cur);
        }
        return pq.size();
    }
}

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}