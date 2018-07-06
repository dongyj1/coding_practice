package solutions;

/**
 * Created by dyj on 4/11/18.
 */

public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head, prev = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

}

class ListNode {
    int val;
   ListNode next;
    ListNode(int x) { val = x; }
 }
