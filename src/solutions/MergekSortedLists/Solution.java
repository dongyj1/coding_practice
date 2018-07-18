package solutions.MergekSortedLists;

import solutions.ListNode;

import java.util.PriorityQueue;

/**
 * Created by dyj on 7/18/18.
 *
 Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

 Example:

 Input:
 [
 1->4->5,
 1->3->4,
 2->6
 ]
 Output: 1->1->2->3->4->4->5->6

 */
public class Solution {

    // PriorityQueue O(nlogn) space O(n)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        for (ListNode l : lists) {
            if (l != null)
                pq.offer(l);
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (!pq.isEmpty()) {
            ListNode l = pq.poll();
            if (l.next != null) {
                pq.offer(l.next);
            }
            cur.next = l;
            cur = cur.next;
        }
        cur.next = null;

        return dummy.next;
    }

    // Merge Sort O(nklgk)
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return sort(lists, 0, lists.length - 1);
    }
    // Sort lists in range into sorted ListNode using divide and conquer.
    private ListNode sort(ListNode[] lists, int low, int high) {
        if (low >= high) {
            return lists[low];
        }
        int mid = low + (high - low) / 2;
        ListNode l1 = sort(lists, low, mid);
        ListNode l2 = sort(lists, mid, high);
        return merge(l1, l2);
    }

    /**
     *
     * @param l1 ListNode 1
     * @param l2 ListNode 2
     * @return sorted ListNode
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

}