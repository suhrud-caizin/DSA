package linkedlist.Questions;


public class BrowserHistory {
    private Node curr;
    public BrowserHistory(String homepage) {
        curr = new Node(homepage);
    }

    public void visit(String url) {
                var node = new Node(url);
                curr.next = node;
                node.prev = curr;
                curr = curr.next;
    }

    public String back(int steps) {

            while (curr.prev != null && steps > 0){
                curr = curr.prev;
                --steps;
            }
            return curr.val;
    }

    public String forward(int steps) {
                while(curr.next != null && steps > 0){
                    curr = curr.next;
                    --steps;
                }

                return curr.val;
    }

    class Node{
        Node next;
        Node prev;
        String val;

        Node(String val){
            this.val = val;
        }
    }
}

