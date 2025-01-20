package linkedlist.Questions;


import sorting.ListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    Map<Integer,ListNode> map;
    ListNode head;
    ListNode node;
    int size;
    Random random = new Random();
    public Solution(ListNode head) {
        this.head = head;
        map = new HashMap<>();
        node = head;
        if(head != null){
            int i = 1;
            while(node != null){
                map.put(i,node);
                node = node.next;
                i++;
            }
            size = i-1;
        }
    }

    public int getRandom() {
        return map.get(generateRandom(size)).val;
    }

    private int generateRandom(int x) {

        return random.nextInt(x) + 1;
    }
}

