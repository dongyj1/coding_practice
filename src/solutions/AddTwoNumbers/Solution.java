package solutions.AddTwoNumbers;

import solutions.ListNode;

/**
 * Created by dyj on 7/18/18.
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
                c2 = c2.next;
            }
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
        }
        if (sum / 10 == 1) {
            cur.next = new ListNode(1);
            cur.next.next = null;
        }
        return dummy.next;
    }
}
