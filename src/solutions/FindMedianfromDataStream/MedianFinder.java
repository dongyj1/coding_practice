package solutions.FindMedianfromDataStream;

import java.util.PriorityQueue;

/**
 * Created by dyj on 7/25/18.
 *
     Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

     For example,
     [2,3,4], the median is 3

     [2,3], the median is (2 + 3) / 2 = 2.5

     Design a data structure that supports the following two operations:

     void addNum(int num) - Add a integer number from the data stream to the data structure.
     double findMedian() - Return the median of all elements so far.
     Example:

     addNum(1)
     addNum(2)
     findMedian() -> 1.5
     addNum(3)
     findMedian() -> 2
 */
public class MedianFinder {

    public PriorityQueue<Integer> upper; // small heap
    public PriorityQueue<Integer> lower; // largest heap

    /** initialize your data structure here. */
    public MedianFinder() {
        upper = new PriorityQueue<>((a, b) -> (a - b));
        lower = new PriorityQueue<>((a, b) -> (b - a)); // largest heap
    }

    public void addNum(int num) {
        if (upper.size() == 0) upper.add(num);
        else if (num >= upper.peek()) {
            upper.add(num);
            if (upper.size() > lower.size() + 1) lower.add((upper.poll()));
        } else {
            lower.add(num);
            if (lower.size() > upper.size()) {
                upper.add(lower.poll());
            }
        }
    }

    public double findMedian() {
        System.out.println(""+ (upper.isEmpty()?"":upper.peek()) + " " + (lower.isEmpty()?"":lower.peek()));
        if (upper.size() == lower.size()) return (upper.peek() + lower.peek()) / 2.0;
        else return upper.peek();
    }
}
/**
 * upper : 3 2
 * lower : 1
 */
