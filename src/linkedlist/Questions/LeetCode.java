package linkedlist.Questions;

import sorting.ListNode;

public class LeetCode {
    public boolean hasCycle(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow) return true;
        }

        return false;
    }


    public int lengthOfCycle(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow){
                int len = 1;
                while(slow.next != fast){
                    len++;
                    slow = slow.next;
                }

                return len;

            }
        }
        return 0;
    }
}
