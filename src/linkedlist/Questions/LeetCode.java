package linkedlist.Questions;

import sorting.ListNode;

import java.util.*;

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

    public ListNode detectCycle(ListNode head) {
            int len = lengthOfCycle(head);

            if(len > 0){
                ListNode first = head;
                ListNode second = head;

                for (int i = 0; i < len; i++) {
                        second = second.next;
                }

                while (first != second){
                    first = first.next;
                    second = second.next;
                }
                return first;
            }else return null;
    }


    public ListNode detectCycle2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next.next;

            if(fast == slow){
                while(slow != head){
                    slow = slow.next;
                    head = head.next;
                }
                return slow;
            }
        }
        return null;
    }


    public boolean isHappy(int n) {
            int slow = n;
            int fast = n;

            do {
                slow = getAns(slow);
                fast = getAns(getAns(fast));
            }while (slow != fast);

            if(slow == 1) return true;
            return false;
    }

    private int getAns(int num){
        int ans = 0;
            while(num > 0){
                    int rem = num % 10;
                    ans += (rem * rem);
                    num /=10;
            }
            return ans;
    }


    Map<Integer,Boolean> isHappyMap;
    public boolean isHappy2(int n) {
        isHappyMap = new HashMap<>();
        return isHappyHelper(n);
    }

    private boolean isHappyHelper(int num){
        if(num == 1) return true;
        if(isHappyMap.containsKey(num)) return false;

        isHappyMap.put(num,true);
        return isHappyHelper(getAns(num));
    }


    List<Integer> nums;
    int[] numbers;
    public ListNode removeZeroSumSublists(ListNode head) {
        constructAL(head,0);
        numbers = removeAllZeroSumSubsequences(numbers);
        return constructLL();
    }

    private void remove(){
        int i = 0;
        int j = 1;
        while(true){
            if(j == nums.size()) break;

            if(nums.get(i) + nums.get(j) == 0){
                nums.remove(i);
                nums.remove(--j);

                i = 0;
                j = 1;
            }else{
                i++;
                j++;
            }
        }
    }

    private void remove2(){
        int i = 0;
        int j = 1;
        int sum = 0;

        while(true){
            if(nums.size() == 1) break;
            if(j == nums.size()) {
                i++;
                j = i+1;
                sum = nums.get(0);
            }
            if(i == nums.size()-1) break;

            sum += nums.get(j);

            if(sum == 0){
                nums.subList(i, j).clear();
            }


        }
    }

    public static int[] removeAllZeroSumSubsequences(int[] arr) {
        boolean hasZeroSumSubsequence = true;

        while (hasZeroSumSubsequence) {
            hasZeroSumSubsequence = false;
            HashMap<Integer, Integer> sumIndexMap = new HashMap<>();
            List<Integer> tempList = new ArrayList<>();
            int currentSum = 0;

            for (int i = 0; i < arr.length; i++) {
                currentSum += arr[i];

                if (currentSum == 0) {
                    // Found zero-sum from the beginning up to current index
                    tempList.clear();
                    sumIndexMap.clear();
                    hasZeroSumSubsequence = true;
                } else if (sumIndexMap.containsKey(currentSum)) {
                    // Found zero-sum subarray between last occurrence and current index
                    int start = sumIndexMap.get(currentSum) + 1;
                    for (int j = tempList.size() - 1; j >= start; j--) {
                        tempList.remove(j);
                    }
                    hasZeroSumSubsequence = true;
                } else {
                    // No zero-sum found, keep adding elements to the list
                    tempList.add(arr[i]);
                    sumIndexMap.put(currentSum, tempList.size() - 1);
                }
            }

            // Convert tempList back to array for the next iteration
            arr = tempList.stream().mapToInt(Integer::intValue).toArray();
        }

        return arr;
    }


    private void constructArrayList(ListNode head){
        ListNode node = head;

        while(node != null){
            nums.add(node.val);
            node = node.next;
        }
    }

    private void constructAL(ListNode head, int cnt){
        if(head == null){
            numbers = new int[cnt];
            return;
        }

        constructAL(head.next,cnt+1);
        numbers[cnt] = head.val;
    }

    private ListNode constructLL(){
        ListNode head = new ListNode(0);
        var node = head;
        for (int num : numbers){
            node.next = new ListNode(num);
            node = node.next;
        }
        return head.next;
    }
    public static void main(String[] args) {
            var obj = new LeetCode();

            var head = new ListNode(1,new ListNode(1,new ListNode(0,new ListNode(6,new ListNode(5)))));
////        var head = new ListNode(0,new ListNode(1,new ListNode(2)));
//
//
////        System.out.println(obj.sortList(head));
//
//            var ans = obj.reverseKGroup(head,3);
//        System.out.println(ans);
//        var head = new Node2(3);
//        head.random = null;
//        var n2 = new Node2(3);
//        head.next = n2;
//        n2.random = head;
//        var n3 = new Node2(3);
//        n2.next = n3;
//        n3.random = null;

//        var ans = obj.copyRandomList(head);
//        System.out.println(ans);

        obj.reverseEvenLengthGroups2(head);
    }


    public ListNode middleNode(ListNode head) {


        ListNode fast = head;
        ListNode midPrev = null;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            midPrev = midPrev == null ? head : midPrev.next;
        }
        var temp = midPrev.next;
        midPrev.next = null;
        return temp;
    }


    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        var mid = middleNode(head);

        var l = sortList(head);
        var r = sortList(mid);
        return mergeTwoLists(l,r);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode f = list1;
        ListNode s = list2;

        ListNode dummyHead = new ListNode();
        ListNode node = dummyHead;

        while (f != null && s != null) {
            if (f.val < s.val) {
                node.next = f;
                f = f.next;
                node = node.next;
            } else {
                node.next = s;
                s = s.next;
                node = node.next;
            }
        }

        if (f != null) {
            node.next = f;
        } else {
            node.next = s;
        }

        return dummyHead.next;
    }


    ListNode container;
    ListNode node;

    public ListNode reverseList(ListNode head) {
        container = new ListNode();
        node = container;
        reverseListHelper(head);
        return container.next;
    }

    private void reverseListHelper(ListNode head) {
        if(head == null) return;
        reverseListHelper(head.next);

        head.next = null;
        node.next = head;
        node = node.next;
    }


    public ListNode reverseListIterative(ListNode head) {
        var container = new ListNode();
        var node = head;
        while(node != null){
            var temp = node.next;
            node.next = container.next;
            container.next = node;
            node = temp;
        }

        return container.next;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {

        if(left == right) return head;

        ListNode current = head;
        ListNode prev = null;

        for (int i = 1; i < left; i++) {
            prev = current;
            current = current.next;
        }

        ListNode last = prev;
        ListNode newLast = current;

        // reverse from current
        var container = new ListNode();
        for(int i = 0 ; i < right - left + 1; i++ ){
            var temp = current.next;
            current.next = container.next;
            container.next = current;
            current = temp;
        }



        // set last = reversed head

        if(last != null) last.next = container.next;
        else head = container.next;

        newLast.next = current;

        return head;

    }


    public ListNode reverseListIterative(ListNode head, ListNode tail) {
        var container = new ListNode();
        var node = head;
        while(node != null && node != tail.next){
            var temp = node.next;
            node.next = container.next;
            container.next = node;
            node = temp;
        }

        return container.next;
    }


   String s1 = new String();
    String s2 = new String();
    public boolean isPalindrome(ListNode head) {
            return s1.equals(s2);
    }

    private void palindromeHelper(ListNode head){
        if(head == null) return;

        s1 += head.val;
        palindromeHelper(head.next);
        s2 += head.val;
    }


    public boolean isPalindrome2(ListNode head) {
        var mid = middleNode(head);
        var reversedHead = reverseList(mid);

        while(head!=null && reversedHead != null){
            if(head.val != reversedHead.val) break;

            head = head.next;
            reversedHead = reversedHead.next;
        }

        if(head == null) return true;
        return false;
    }

    ListNode nodePtr;
    boolean shutdown;
    public void reorderList(ListNode head) {
        var mid = middleNodeWithoutCut(head);
        nodePtr = head;
        shutdown = false;

        reorderHelper(head,mid);

    }

    private void reorderHelper(ListNode node, ListNode mid){
        if(node == null) return;
        reorderHelper(node.next,mid);
        if(shutdown) return;
        if(node == mid) {
            node.next = null;
            shutdown = true;
            return;
        }


        node.next = nodePtr.next;
        nodePtr.next = node;
        nodePtr = node.next;
    }

    public ListNode middleNodeWithoutCut(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode fast = head;
        ListNode midPrev = null;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            midPrev = midPrev == null ? head : midPrev.next;
        }
        return midPrev.next;
    }

    ListNode ptr;
    ListNode gHead;

    int cnt;
    boolean stop;

    public ListNode rotateRight(ListNode head, int k) {
        ptr = head;
        gHead = head;
        cnt = k;
        rotateHelper(head);
        return ptr;
    }

    private void rotateHelper(ListNode node){
        if(node == null) return;
        rotateHelper(node.next);
        if(stop) return;
        if(cnt == 0){
            node.next = null;
            stop = true;
            return;
        }



        node.next = ptr;
        ptr = node;
        cnt--;
    }

    private ListNode rotateHelper2(ListNode head, int num){
        if(head == null || head.next == null) return head;
        ListNode node = head;
        int len = 1;

        while (node.next != null) {
            node = node.next;
            len++;
        }

        node.next = head;
        node = head;
        int offset = len - (num % len);

        // System.out.println(offset);
        for (int i = 1; i < offset; i++) {
            node = node.next;
        }

        head = node.next;
        node.next = null;

        return head;
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        var len = getLength(head);

        ListNode current = head;
        ListNode prev = null;


        for (int j = 0; j+k <= len; j+=k) {

        ListNode last = prev;
        ListNode newLast = current;
        prev = current;

        // reverse from current
        var container = new ListNode();
        for(int i = 0 ; i < k && current != null; i++ ){
            var temp = current.next;
            current.next = container.next;
            container.next = current;
            current = temp;
        }


//        if(current == null) break;

        // set last = reversed head
        if(last != null) last.next = container.next;
        else head = container.next;

        newLast.next = current;

        }

        return head;
    }

    public int getLength(ListNode head) {
        ListNode node = head;
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }



    public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode node = head;
            int len = 0;
            while(node != null){
                len++;
                node = node.next;
            }

//            node = head;

            int index = len - n - 1;

            ListNode prev = null;
            ListNode current = head;

            if(index > 0){
                for (int i = 0; i < index; i++) {
                    prev = current;
                    current = current.next;
                }
            }

            if(prev == null){
                    if(current.next != null) head = current.next;
                    else return null;
            }else{
                prev.next = current.next;
            }

            return head;
    }

    public ListNode swapNodes(ListNode head, int k) {
            int len = 0;
            ListNode node = head;

            while (node != null){
                len++;
                node = node.next;
            }

            node = head;

            if(len > 2){
                int secondIndex = len - k + 1;
                    int index = Math.max(k, secondIndex);
                    ListNode fn = null;
                    ListNode sn = null;

                for (int i = 1; i <= index; i++) {
                    if(i == k){
                        fn = node;
                    }
                    if(i == secondIndex) sn = node;

                    node = node.next;
                }

                int tem = fn.val;
                fn.val = sn.val;
                sn.val = tem;

            }

            return head;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
                ListNode head = new ListNode();
                int carry = 0;
                while (l1 != null || l2 != null){
                    int sum = carry;

                    if(l1 != null) {
                        sum += l1.val;
                        l1 = l1.next;
                    }

                    if(l2 != null){
                        sum += l2.val;
                        l2 = l2.next;
                    }

                    head.next = new ListNode(sum % 10);
                    carry = sum / 10;
                }

                return head.next;
    }


    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode node = l1;
        ListNode head = null;

        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();

        while(node != null){
            s1.add(node);
            node = node.next;
        }
        node = l2;
        while(node != null){
            s2.add(node);
            node = node.next;
        }

        int carry = 0;
//        node = null;
        while(!s1.isEmpty() || !s2.isEmpty()){
            int sum = carry;

            if(!s1.isEmpty()){
                sum += s1.pop().val;
            }

            if(!s2.isEmpty()){
                sum += s2.pop().val;
            }

            head = new ListNode(sum % 10,head);
            carry = sum / 10;
        }

        if(carry > 0) head = new ListNode(carry,head);
        return head;
    }


    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };

    static class HeadTail{
        Node head;
        Node tail;
        HeadTail(Node h, Node t){
            head = h;
            tail = t;
        }
    }

    public Node flatten(Node head) {
        return flattener(head).head;
    }

    public HeadTail flattener(Node node){
        var head = node;
        Node prev = null;
        while(head != null){
            if(head.child != null){
                var ht = flattener(head.child);
                var temp = head.next;

                head.next = ht.head;
                ht.head.prev = head;

                ht.tail.next = temp;
                temp.prev = ht.tail;

                head = temp;
                prev = ht.tail;
            }else{
                prev = head;
                head = head.next;
            }
        }

        return new HeadTail(node,prev);
    }

    static class Node2 {
        public int val;
        public Node2 next;
        public Node2 random;

        Node2(){}
        Node2(int val){
            this.val = val;
        }
    }

    Map<Node2,Node2> mapping;
    public Node2 copyRandomList(Node2 head) {
        mapping = new HashMap<>();
        return copyRandomListHelper(head);
    }

    private Node2 copyRandomListHelper(Node2 head) {
        if(mapping.containsKey(head)) return mapping.get(head);
        var tempHead = new Node2(0);
        var temp = tempHead;

//        Node2 prev = null;
        while(head != null){
            if(mapping.containsKey(head)){
                temp.next = mapping.get(head);
            }else{
                temp.next = new Node2(head.val);
                if(head.random != null) {
                    temp.next.random = copyRandomListHelper(head.random);
                }
                mapping.put(head,temp.next);
            }

//            prev.next = temp.next;
//
//            prev = temp;
            temp = temp.next;
            head = head.next;
        }

        return tempHead.next;
    }

    public ListNode removeZeroSumSublists2(ListNode head) {
        int pSum=0;
        ListNode temp=new ListNode(0);
        temp.next=head;

        HashMap<Integer,ListNode> hm =new HashMap<>();
        hm.put(0,temp);

        while(head!=null){
            pSum+=head.val;

            if(hm.containsKey(pSum)){
                ListNode req=hm.get(pSum);
                ListNode delete =req;
                int traveller=pSum;
                while(delete!=head){
                    delete=delete.next;
                    traveller+=delete.val;
                    if(delete!=head){
                        hm.remove(traveller);
                    }
                    // You might think why have I pasted the same above condition again
                    //(if loop) because even though both are same but the code otherwise wont
                    //work for the test case of [0,0].
                }
                req.next=delete.next;

            }
            else{
                hm.put(pSum,head);
            }
            head=head.next;
        }
        return temp.next;
    }

    public ListNode reverseEvenLengthGroups(ListNode head) {
        int i = 1;
        ListNode node = head;
        ListNode prev = null;
        while(node != null){

            int j = 0;
            if(i % 2 == 0){
                ListNode temp = node;
                ListNode prevHead = node;
                ListNode newHead = null;
                    while(j < i && temp != null){
                        var nNext = temp.next;
                        if(newHead == null) newHead = temp;
                        else{
                            temp.next = newHead;
                            newHead = temp;
                        }

                        temp = nNext;
                        j++;
                    }
                    prevHead.next = temp;
                    prev.next = newHead;
                    node = temp;
                    prev = prevHead;
                    i++;

            }else{;
                var tempPrev = prev;
                var tempNode = node;

                while(j < i && node != null){
                    prev = node;
                    node = node.next;
                    j++;
                }
                if(node == null && j % 2 == 0 ){
                    prev = tempPrev;
                    node = tempNode;
                    i = j;
                }
                else i++;
            }

        }

        return head;
    }


    public ListNode reverseEvenLengthGroups2(ListNode head) {
        int i = 1;
        ListNode node = head;
        ListNode prev = null;
        while(node != null){
            int j = 0;
            int size = 0;
            ListNode tempPrev = null;
            ListNode temp = node;
            while(j < i && temp != null){
                tempPrev = temp;
                temp = temp.next;
                size++;
                j++;
            }

            if(size % 2 == 0){
                // reverse
                j = 0;
                temp = node;
                ListNode prevHead = node;
                ListNode newHead = null;
                while(j < i && temp != null){
                    var nNext = temp.next;
                    if(newHead == null) newHead = temp;
                    else{
                        temp.next = newHead;
                        newHead = temp;
                    }

                    temp = nNext;
                    j++;
                }
                prevHead.next = temp;
                prev.next = newHead;
                node = temp;
                prev = prevHead;
                i++;


            }else{
                prev = tempPrev;
                node = temp;
                i++;
            }
        }



        return head;
    }

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode lastOfSecond = null;
        ListNode f = null;
        ListNode s;

        ListNode node = list2;

        while(node.next != null){
            node = node.next;
        }

        lastOfSecond = node;

        node = list1;
        int i = 0;
        while(true){

            if(i == a-1){
                f = node;
            }else if(i == b+1){
                s = node;
                break;
            }

            node = node.next;
            i++;
        }

        if(f != null)
            f.next = list2;

        lastOfSecond.next = s;


        return list1;
    }


    public ListNode deleteMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prevSlow = null;

        while(fast != null && fast.next != null){

            fast = fast.next.next;
            prevSlow = slow;
            slow = slow.next;
        }

        if(prevSlow == null){
            return null;
        }else{
            prevSlow.next = slow.next;
            return head;
        }
    }


    int[] ans;
    public int[] nextLargerNodes(ListNode head) {

        nextLargerNodesHelper(head,0);
        return ans;
    }

    private void nextLargerNodesHelper(ListNode head, int index){
        if(head == null){
            ans = new int[index];
            return;
        }
        nextLargerNodesHelper(head.next,index+1);

        if(index + 1 == ans.length){
            return;
        }else{
            var n = head.next;
            if(n.val > head.val){
                ans[index] = n.val;
            }else{
                ans[index] = ans[index+1];
            }
        }
    }

    public ListNode oddEvenList(ListNode head) {
        ListNode o = new ListNode(0);
        ListNode o1 = o;
        ListNode e = new ListNode(0);
        ListNode e1 = e;
        int i =   1;

        while (head != null){
            if(i%2 == 0){
                e.next = head;
                e = e.next;
            }else{
                o.next = head;
                o = o.next;
            }
            head = head.next;
            i++;
        }

        o.next = e1.next;

        return o1.next;


    }


    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return new int[]{-1,-1};

        ListNode prev = head;
        ListNode curr = head.next;
        ListNode next = head.next.next;

        int prevIndex = -1;
        int startIndex = -1;
        int maxDist = -1;
        int minDist  = -1;
        int index = 0;
        while (curr.next != null){
            if(curr.val > prev.val && curr.val > next.val){
                if(startIndex == -1) startIndex = index;
                else{
                    minDist = Math.min(minDist,index-prevIndex);
                    maxDist = index - startIndex;
                }

                prevIndex = index;
            }

            if(curr.val < prev.val && curr.val < next.val){
                if(startIndex == -1) startIndex = index;
                else{
                    minDist = Math.min(minDist,index-prevIndex);
                    maxDist = index - startIndex;
                }

                prevIndex = index;
            }

            prev = curr;
            curr = curr.next;
            next = next.next;
            index++;
        }

        return new int[]{minDist,maxDist};
    }

    public ListNode mergeKLists(ListNode[] lists) {
            int size = lists.length;
            if(size == 0){
                return null;
            }else if(size == 1) return lists[0];
            else{
                ListNode prev = lists[0];
                ListNode curr = null;
                for (int i = 1; i < size ; i++) {
                    curr = lists[i];
                    prev = mergeTwoLists(prev,curr);
                }
                return prev;
            }
    }


}
