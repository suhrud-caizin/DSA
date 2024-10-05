package trees;

import node.Node;

import java.util.*;

public class BFS {
    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i=0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(currentLevel);
        }
        return result;

    }

    public List<List<Integer>> levelOrderBottom(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i=0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(0,currentLevel);
        }
        return result;

    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        boolean reverse = false;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i=0; i < levelSize; i++) {

                if(!reverse){
                TreeNode currentNode = queue.pollFirst();
                currentLevel.add(currentNode.val);
                if (currentNode.left != null) {
                    queue.addLast(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.addLast(currentNode.right);
                }
                }else {
                    TreeNode currentNode = queue.pollLast();
                    currentLevel.add(currentNode.val);

                    if (currentNode.right != null) {
                        queue.addFirst(currentNode.right);
                    }
                    if (currentNode.left != null) {
                        queue.addFirst(currentNode.left);
                    }
                }
            }
            result.add(currentLevel);
            reverse = !reverse;
        }
        return result;

    }

    public TreeNode levelOrderSuccessorOfNode(TreeNode root, Integer val){
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
                TreeNode currentNode = queue.poll();

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            if(currentNode.val == val){
                break;
            }
        }

        return queue.peek();
    }

    public List<Integer> rightSideView(TreeNode root){
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            for (int i=0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if(i == levelSize -1) result.add(currentNode.val);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        return result;

    }

    public Node connect(Node root) {
        if (root == null)
            return null;
        Node leftmost = root;
        while (leftmost.left != null) {
            Node current = leftmost;
            while (current != null) {

                current.left.next = current.right;
                if (current.next != null)
                    current.right.next = current.next.left;
                current = current.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null)
            return null;
        Node leftmost = root;
        while (leftmost.left != null) {
            Node current = leftmost;
            while (current != null) {

                current.left.next = current.right;
                if (current.next != null)
                    current.right.next = current.next.left;
                current = current.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        TreeNode first = findNode(root, x);
        TreeNode second = findNode(root, y);

        return level(root, first,0) == level(root, second,0) && (!isSibling(root,first,second));
    }

    private TreeNode findNode(TreeNode node, int x) {
        if(node == null ) return null;
        if(node.val == x) return node;

        TreeNode t = findNode(node.left,x);
        if(t!= null) return t;
        return findNode(node.right,x);
    }

    private boolean isSibling(TreeNode root , TreeNode x, TreeNode y) {
        if(root == null) return false;
        return ((root.left == x && root.right == y) ||(root.right == x && root.left == y)) || isSibling(root.left,x,y) || isSibling(root.right,x,y);
    }

    private Integer level(TreeNode node, TreeNode x,Integer level) {
       if(node == null ) return 0;
       if(node == x) return  level;
       int l = level(node.left,x,level+1);
       if(l != 0) return l;
       return level(node.right,x,level+1);
    }

    public long kthLargestLevelSum(TreeNode root, int k) {
        Queue<TreeNode> q = new LinkedList<>();
//        List<Long> l = new ArrayList<>();
        PriorityQueue<Long> l = new PriorityQueue<>();
        q.offer(root);

        while(!q.isEmpty()){
            long sum = 0;
            for (int i = 0; i < q.size(); i++) {
                TreeNode node = q.poll();
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
                sum += node.val;
            }
            if(l.size()<k) l.offer(sum);
            else if (l.peek() < sum) {
                l.poll();
                l.offer(sum);
            }
//            l.add(sum);
        }

        return l.size() < k ? -1 : l.peek();
    }


}
