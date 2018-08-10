package solutions.InsertintoaCyclicSortedList;

/**
 * Created by dyj on 8/7/18.
 *
 Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.

 If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.

 If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. Otherwise, you should return the original given node.
 */
public class Solution {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            head = new Node(insertVal, null);
            head.next = head;
            return head;
        }
        Node pre = head;
        Node next = head.next;
        while (next != null) {
            if ((pre.val <= insertVal && next.val >= insertVal) // in middle 
            || (next.val >= insertVal && pre.val >= next.val)  // insertVal is smaller
            || (pre.val <= insertVal && pre.val >= next.val)    // insertVal is larger
            ){  
                Node node = new Node(insertVal, null);
                node.next = next;
                pre.next = node;
                break;
            }
            pre = next;
            next = next.next;
        }
        return head;
    }
}
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};
