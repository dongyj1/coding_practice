package solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by dyj on 4/18/18.
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

 For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 [1, 1]
 [1, 1], [3, 3]
 [1, 1], [3, 3], [7, 7]
 [1, 3], [7, 7]
 [1, 3], [6, 7]
 */
public class DataStreamasDisjointIntervals352 {

    TreeSet<Interval> intervalsSet;
    /** Initialize your data structure here. */
    public DataStreamasDisjointIntervals352() {
        intervalsSet = new TreeSet<>((a, b) -> (b.start - a.start));
    }

    public void addNum(int val) {
        Interval temp = new Interval(val, val);
        if (intervalsSet.contains(temp)) {
            return;
        }
        Interval pre = intervalsSet.lower(temp);
        Interval post = intervalsSet.higher(temp);

        if (pre != null && post != null && pre.end == val - 1 && post.start == val + 1) {
            pre.end = post.end;
            intervalsSet.remove(post);
            return;
        }
        if (pre != null) {
            if (pre.end == val - 1) {
                pre.end = val;
                return;
            } else if (pre.end >= val) {
                return;
            }
        }
        if (post != null) {
            if (post.start == val + 1) {
                post.start = val;
                return;
            } else if (post.start <= val) {
                return;
            }
        }
        intervalsSet.add(temp);
    }

    public List<Interval> getIntervals() {
        List<Interval> list = new ArrayList<>();
        for (Interval interval : intervalsSet) {
            list.add(interval);
        }
        return list;
    }

    public static void main(String[] args) {

    }
}

class Interval {
        int start;
         int end;
            Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
}
