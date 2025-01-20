package linkedlist.Questions;

import sorting.ListNode;

import java.util.Arrays;
import java.util.HashMap;
//import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LRUCache {

    Map<Integer, Integer> mapping;
    LinkedList list;
    ExecutorService executor;

//    LinkedList list;


    public LRUCache(int capacity) {
        mapping = new HashMap<>();
        list = new LinkedList(capacity);
        executor = Executors.newCachedThreadPool();
    }

    public int get(int key) {
        if(!mapping.containsKey(key)) return -1;

        // move key to end of LL
        executor.submit(()->{
        list.moveToLast(key);

        });

        return mapping.get(key);
    }

    public void put(int key, int value) {
        if(mapping.containsKey(key)){
            // move the node to end of list

            executor.submit(()->{
            list.moveToLast(key);
            });


        }else{
            // add at the end
            executor.submit(()->{
            list.addLast(key);
            });
        }
            mapping.put(key,value);
    }

    class LinkedList{
        ListNode head;
        ListNode tail;
        int capacity;
        int size;

        LinkedList(int capacity){
            this.capacity = capacity;
        }

        synchronized void addLast(int val){
            if(size == 0) addFirst(val);
            else{
                tail.next = new ListNode(val);
                tail = tail.next;
                if(size == capacity){
                    mapping.remove(head.val);
                    head = head.next;
                }
                else size++;
            }
        }

        void addFirst(int val){
                tail = head = new ListNode(val);
                size++;
        }

        synchronized void moveToLast(int val){
            ListNode node = head;
            ListNode prev = null;

            while(node.next != null){
                if(node.val == val){
                       if(node == head) head = node.next;
                    if(prev != null)
                        prev.next = node.next;

                    tail.next = node;
                    node.next = null;
                    tail = node;
                    break;
                }
                prev = node;
                node = node.next;
            }
        }

    }

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;

        ListNode node = head;

        while(node.next != null){
            if(node.next.val == head.val){
                var tmp = node.next;
                while(tmp != null && tmp.val == node.val){
                    tmp = tmp.next;
                }
                node.next = tmp;
                node = tmp;
            } else node = node.next;
        }

        return head;
    }


    public ListNode partition(ListNode head, int x) {
        ListNode node = head;
        ListNode leftHead = new ListNode(0);
        ListNode rightHead = new ListNode(0);

        var l = leftHead;
        var r = rightHead;

            while(node != null){
                if(node.val < x){
                    l.next = node;
                    l = node;
                }else{
                    r.next = node;
                    r = node;
                }
                node = node.next;
            }
            l.next = rightHead.next;
            r.next = null;

            return leftHead.next;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode node = head;
        ListNode prev = null;

        while(node != null && node.next != null){
            // swap
            var nNext = node.next.next;
            node.next.next = node;

            if(prev != null) prev.next = node.next;
            else head = node.next;

            node.next = nNext;



            node = nNext;
            prev = node;
        }


        return head;
    }




    public static void main(String[] args) {
//            var cache = new LRUCache(2);
//            cache.put(1,1);
//        cache.put(2,2);
//            cache.get(1);
//        cache.put(3,3);
//        cache.get(2);
//        cache.put(4,4);
//        cache.get(1);
//        cache.get(3);
//        cache.get(4);
//        System.out.println("done");
    }
}

