package linkedlist;

public class SinglyLinkedList {
    
    private Node head;
    private Node tail;
    private int size;

    public SinglyLinkedList() {
        this.size = 0;
    }

    public void insertFirst(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;

        if (tail == null) {
            tail = head;
        }
        size += 1;
    }

    public void insertLast(int val) {
        if (tail == null) {
            insertFirst(val);
            return;
        }
        Node node = new Node(val);
        tail.next = node;
        tail = node;
        size++;
    }

    public void insert(int val, int index) {
        if (index == 0) {
            insertFirst(val);
            return;
        }
        if (index == size) {
            insertLast(val);
            return;
        }

        Node temp = head;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }

        Node node = new Node(val, temp.next);
        temp.next = node;

        size++;
    }

    // insert using recursion
    public void insertRec(int val, int index) {
        head = insertRecHelper(val, index, head);
    }

    private Node insertRecHelper(int val, int index, Node node) {
        if(index == 0) {
            size++;
            return new Node(val,node);
        }

        node.next = insertRecHelper(val,--index,node.next);
        return node;
    }


    public int deleteLast() {
        if (size <= 1) {
            return deleteFirst();
        }

        Node secondLast = get(size - 2);
        int val = tail.value;
        tail = secondLast;
        tail.next = null;
        size--;
        return val;
    }

    public int delete(int index) {
        if (index == 0) {
            return deleteFirst();
        }
        if (index == size - 1) {
            return deleteLast();
        }

        Node prev = get(index - 1);
        int val = prev.next.value;

        prev.next = prev.next.next;
        size--;
        return val;
    }

    public Node find(int value) {
        Node node = head;
        while (node != null) {
            if (node.value == value) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public Node get(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public int deleteFirst() {
        int val = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return val;
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " -> ");
            temp = temp.next;
        }
        System.out.println("END");
    }

    private class Node {
        private int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-list
    public void duplicates() {
            Node node = head;

            while(node.next != null){
                if(node.value == node.next.value){
                    node.next = node.next.next;
                    size--;
                }else{
                    node = node.next;
                }
            }

            tail = node;
    }

    // https://leetcode.com/problems/merge-two-sorted-lists/submissions/
    public static SinglyLinkedList merge(SinglyLinkedList first, SinglyLinkedList second) {
        Node f = first.head;
        Node s = second.head;
        SinglyLinkedList ans = new SinglyLinkedList();

        while(f != null && s != null){
            if(f.value < s.value){
                ans.insertLast(f.value);
                f = f.next;
            }else{
                    ans.insertLast(s.value);
                    s = s.next;
            }
        }


            while (f!=null){
                ans.insertLast(f.value);
                f = f.next;
            }

            while (s!=null){
                ans.insertLast(s.value);
                s = s.next;
            }

            return ans;
    }

    public void bubbleSort() {
        bubbleSort(size - 1, 0);
    }

    private void bubbleSort(int row, int col) {
        if(row == 0) return;

        if(col < row){
            var f = get(col);
            var s = get(col+1);
            if(f.value > s.value){
                if(f == head){
                        head = s;
                        f.next =s.next;
                        s.next = f;
                }else if( s == tail){
                    var prev = get(col-1);
                    tail = f;
                    prev.next = s;
                    s.next = f;
                    f.next = null;
                }else{
                    var prev = get(col-1);
                    prev.next = s;
                    f.next = s.next;
                    s.next = f;
                }

            }
            bubbleSort(row,col+1);
        }else{
            bubbleSort(row-1,0);
        }
    }


    // recursion reverse
    private void reverse(Node node) {
        if (node == tail) {
            head = tail;
            return;
        }
        reverse(node.next);
        tail.next = node;
        tail = node;
        tail.next = null;
    }

    // in place reversal of linked list
    // google, microsoft, apple, amazon: https://leetcode.com/problems/reverse-linked-list/
    public void reverse() {
        if (size < 2) {
            return;
        }

        Node prev = null;
        Node present = head;
        Node next = present.next;

        while (present != null) {
            present.next = prev;
            prev = present;
            present = next;
            if (next != null) {
                next = next.next;
            }
        }
        head = prev;
    }


    public static void main(String[] args) {
        SinglyLinkedList first = new SinglyLinkedList();

        first.insertLast(1);
        first.insertLast(13);
        first.insertLast(30);
        first.insertLast(5);
        first.insertLast(52);
        first.insertLast(213);


        SinglyLinkedList sec = new SinglyLinkedList();
        sec.insertLast(2);
        sec.insertLast(4);
        sec.insertLast(6);
        sec.insertLast(24);
        sec.insertLast(25);


        first.display();
        first.bubbleSort();
        first.display();
//        sec.display();
////        var ans  = merge(first,sec);
//        ans.display();
    }


}
