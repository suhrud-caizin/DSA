package linkedlist.Questions;


import java.util.HashMap;
import java.util.Map;

public class AllOne {
    Node head;
    Node tail;
    Map<String,Node> map;
    LL list;
    public AllOne() {
        map = new HashMap<>();
        list = new LL();
    }

    public void inc(String key) {
            var node = map.get(key);
            if(node == null){
                var temp = new Node(1,key);
                map.put(key, temp);
                list.addFirst(temp);
            }else{
                node.freq = node.freq + 1;
                list.replaceForward(node);
            }
    }

    public void dec(String key) {
        var node = map.get(key);
        if(node.freq ==  1){
            map.remove(key);
            list.replaceForward(node);
        }else{
            node.freq = node.freq -1;
            list.replaceBackward(node);
        }
    }

    public String getMaxKey() {
        if(list.tail != null) return list.tail.val;
        return "";
    }

    public String getMinKey() {
        if(list.head != null) return list.head.val;
        return "";
    }
}
class LL{
    Node head;
    Node tail;
    void addFirst(Node node){
        if(head == null){
            head = node;
            tail = node;
        }else{
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    void replaceBackward(Node node){
        int freq = node.freq;
        Node itr = node;

        while(itr != null && itr.freq == freq){
            itr = itr.prev;
        }

        if(itr == null){
            node.next = head;
            head.prev = node;
            head = node;
        } else if(itr != null && itr.next != node){
            remove(node);
            var n = itr.next;
            node.next = n;
            if(n != null)
                n.prev = node;
            itr.next = node;
            node.prev = itr;
        }
    }

    void replaceForward(Node node){
        int freq = node.freq;
        Node itr = node;

        while(itr.next != null && itr.next.freq == freq){
            itr = itr.next;
        }
        if(itr == null){
            tail.next = node;
            node.prev = tail;
            tail = node;
        }else if(node.next != itr){
            remove(node);
            var p = itr.prev;
            node.next = itr;
            itr.prev = node;
            node.prev = p;
            if(p != null)
                p.next = node;
        }
    }

    void remove(Node node){
        var n = node.next;
        var p = node.prev;

        if(p != null)
            p.next = n;
        n.prev = p;
    }
}
class Node{
    public Node next;
    public Node prev;
    int freq;
    String val;

    public Node(int freq, String str){
        this.freq = freq;
        val = str;
    }

}