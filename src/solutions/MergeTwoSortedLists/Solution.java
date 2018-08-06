package solutions.MergeTwoSortedLists;

import solutions.ListNode;

/**
 * Created by dyj on 8/6/18.
 *
 Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

 Example:

 Input: 1->2->4, 1->3->4
 Output: 1->1->2->3->4->4

 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), c1 = l1, c2 = l2, cur = dummy;

        while (c1 != null || c2 != null) {
            int n1 = (c1 == null) ? Integer.MAX_VALUE : c1.val;
            int n2 = (c2 == null) ? Integer.MAX_VALUE : c2.val;

            if (n1 < n2) {
                cur.next = c1;
                c1 = c1.next;
            } else {
                cur.next = c2;
                c2 = c2.next;
            }
            cur = cur.next;
        }

        return dummy.next;
    }
}
