package solutions.LRUCache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dyj on 7/23/18.
 *
 Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LRUCache cache = new LRUCache( 2 /* capacity * );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

 */
public class LRUCache {
    Node head;
    Node tail;
    int capacity;
    int size;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        connect(head, tail);
        size = 0;
        map = new HashMap<>();
    }

    private void connect(Node prevNode, Node nextNode){
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void insert(Node preNode, Node curNode, Node nextNode){
        connect(preNode, curNode);
        connect(curNode, nextNode);
    }



    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        } else {
            Node cur = map.get(key);

            // remove node from cur position in list
            connect(cur.prev, cur.next);

            // add to head position
            insert(head, cur, head.next);

            return cur.val;
        }
    }

    public void put(int key, int value) {
        // contains key already
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            cur.val = value;
            // remove node from cur position in list
            connect(cur.prev, cur.next);

            // add to head position
            insert(head, cur, head.next);

        } else {
            // add new value
            if (size < capacity) {
                // no need to remove, just add
                Node cur = new Node(key, value);
                map.put(key, cur);
                insert(head, cur, head.next);
                size++;
            } else {
                // remove least recent node
                Node toRemove = tail.prev;
                map.remove(toRemove.key);
                connect(toRemove.prev, toRemove.next);

                // add new node
                Node cur = new Node(key, value);
                map.put(key, cur);
                insert(head, cur, head.next);
            }
        }
    }

    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        Node(int k, int v) {
            key = k;
            val = v;
            prev = null;
            next = null;
        }
    }
}
