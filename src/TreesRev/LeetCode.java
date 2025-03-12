package TreesRev;

import com.sun.source.tree.Tree;
import node.Node;
import trees.TreeNode;

import java.util.*;

public class LeetCode {

    public Node connect(Node root) {
        if(root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                var node = queue.poll();
                if(i != levelSize -1)
                    node.next = queue.peek();

                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }

            }
        }

        return root;
    }

    public Node connect2(Node root) {
        if(root == null) return null;
        Queue<List<Node>> queue = new LinkedList<>();
        queue.offer(List.of(root));

        while (!queue.isEmpty()){
            var level = queue.poll();
            var size = level.size();
            List<Node> levelList = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                var node = level.get(i);

                if(i < size-1){
                    node.next = level.get(i+1);
                }

                if(node.left != null){
                    levelList.add(node.left);
                }

                if(node.right != null){
                    levelList.add(node.right);
                }

            }

            queue.offer(levelList);
        }

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, inorder, 0, preorder.length - 1);
    }

    TreeNode buildTreeHelper(int[] preorder, int[] inorder, int start, int end) {
        // if(index == inorder.length) return null;
        if(start > end ) return null;
        int ptr = indexOf(inorder, preorder[0]);

        var leftInorder = Arrays.copyOfRange(inorder,start,ptr);
        var rightInorder = Arrays.copyOfRange(inorder, ptr+1, end+1);

        TreeNode l = null,r = null;
        if(leftInorder.length != 0){
            l = buildTreeHelper(Arrays.copyOfRange(preorder,1,preorder.length),leftInorder,start,ptr-1);
        }

        if(rightInorder.length != 0){
            r = buildTreeHelper(Arrays.copyOfRange(preorder,1,preorder.length),rightInorder,ptr+1,end);
        }
        return new TreeNode(preorder[0], l, r);
    }

    int indexOf(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val)
                return i;
        }

        return -1;
    }


//    Q1[IMP]
//    sum is given
//    calculate how many subpaths makes this sum

    int numberOfSubPaths(TreeNode root, int sum){
        return numberOfSubPaths(root,sum,new ArrayList<>());
    }

    int numberOfSubPaths(TreeNode root, int sum, List<Integer> path){
        if(root == null) return 0;

        int levelSum = 0;
        int levelCnt = 0;

        path.add(root.val);

        // while loop check
        // check sum add cnt
        ListIterator<Integer> iterator = path.listIterator(path.size());
        while(iterator.hasPrevious()){
            levelSum += iterator.previous();
            if(levelSum == sum){
                levelCnt++;
            }
        }

        levelCnt += numberOfSubPaths(root.left,sum,path);
        levelCnt += numberOfSubPaths(root.right,sum,path);

        path.remove(path.size()-1);
        return  levelCnt;
    }

    boolean pathExists(TreeNode root, List<Integer> path){
        return pathExists(root, path, 0);
    }

    boolean pathExists(TreeNode root, List<Integer> path, int index){
        if(index == path.size()) return true;
        if(root == null || root.val != path.get(index)) return false;
        return pathExists(root.left,path,index+1) || pathExists(root.right, path, index+1);
    }

    void dfsUsingStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            var current = stack.pop();
            System.out.println(current.val);

            if(current.right != null)
                stack.push(current.right);
            if(current.left != null)
                stack.push(current.left);
        }

    }

    void dfsUsingStackInorder(TreeNode root){
        TreeNode current  = root;
        Stack<TreeNode> stack = new Stack<>();

        while (current != null || !stack.isEmpty()){

            while (current != null){
                stack.push(current);
                current = current.left;
            }

            var node = stack.pop();
            System.out.println(node.val);
            current = node.right;
        }

    }

    void dfsUsingStackPostorder(TreeNode root){
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        s1.push(root);

        while (!s1.isEmpty()){
            var node = s1.pop();
            s2.push(node);

            if(node.left != null)
                s1.push(node.left);
            if(node.right != null)
                s1.push(node.right);
        }

        while (!s2.isEmpty()){

            System.out.println(s2.pop().val);
        }
    }


    void serializeHelper(TreeNode root, List<String> list){
        if(root == null){
            list.add(null);
            return;
        }

        list.add(String.valueOf(root.val));
        serializeHelper(root.left, list);
        serializeHelper(root.right, list);
    }

    public String serialize(TreeNode root) {
        List<String> list = new ArrayList<>();
        serializeHelper(root, list);
        return list.toString();
    }


    public static List<String> parseStringToList(String str) {
        // Remove brackets and split by comma
        str = str.replaceAll("[\\[\\]]", "").trim();
        if (str.isEmpty()) return new ArrayList<>(); // Handle empty case

        return Arrays.asList(str.split("\\s*,\\s*")); // Split and trim spaces

    }

    int index = 0;
    TreeNode deserializeHelper(List<String> list){
        if(list.get(index).equals("null")) {
            index++;
            return null;
        }

        return new TreeNode(
                Integer.parseInt(list.get(index++)),
                deserializeHelper(list),
                deserializeHelper(list)
        );
    }


//    public List<List<Integer>> verticalTraversal(TreeNode root) {
//            grid = new TreeMap<>();
//            verticalTraversalHelper(root);
//
//
//    }


    static class TreeNodeIndexed{
        TreeNode node;
        int i;
        int j;

        public TreeNodeIndexed(TreeNode node, int i, int j){
            this.node = node;
            this.i = i;
            this.j = j;
        }
    }
    List<List<Integer>> verticalTraversalHelper(TreeNode root){
        if(root == null) return new ArrayList<>();
        Map<Integer,List<TreeNodeIndexed>> grid = new TreeMap<>();

            Queue<TreeNodeIndexed> queue = new LinkedList<>();
            queue.offer(new TreeNodeIndexed(root,0,0));

           while(!queue.isEmpty()){
               int size = queue.size();
               for (int k = 0; k < size; k++) {
                   var node = queue.poll();

                   if(!grid.containsKey(node.j)){
                       grid.put(node.j,new ArrayList<>());
                   }

                   grid.get(node.j).add(node);

                   if(node.node.left != null) queue.offer(new TreeNodeIndexed(node.node.left,node.i+1,node.j-1));

                   if(node.node.right != null) queue.offer(new TreeNodeIndexed(node.node.right,node.i+1, node.j+1));


               }
           }

        // Process the TreeMap and sort values within each column
        List<List<Integer>> result = new ArrayList<>();
        for (List<TreeNodeIndexed> nodes : grid.values()) {
            // Sort by row first, then by value if same row
            nodes.sort((a, b) -> a.i == b.i ? Integer.compare(a.node.val, b.node.val) : Integer.compare(a.i, b.i));

            // Extract values for the final result
            List<Integer> column = new ArrayList<>();
            for (TreeNodeIndexed node : nodes) {
                column.add(node.node.val);
            }
            result.add(column);

        }

        return result;

    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> visited = new HashSet<>();
        HashSet<String> wordSet = new HashSet<>(wordList);

        if(!wordSet.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int level = 0;

        while (!queue.isEmpty()){
            level++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                for (int j = 0; j < current.length(); j++) {
                    char[] currentArr = current.toCharArray();

                    for (char ch = 'a'; ch <= 'z' ; ch++) {
                        currentArr[j] = ch;
                        String temp = new String(currentArr);

                        if(temp.equals(endWord)){
                            return level;
                        }else if(wordSet.contains(temp) && !visited.contains(temp)){
                            queue.offer(temp);
                            visited.add(temp);
                        }
                    }
                }
            }
        }

        return 0;
    }
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);

        // Intermediate nodes
        TreeNode node2 = new TreeNode(2, node1, node3);
        TreeNode node6 = new TreeNode(6, node5, node7);

        // Root node
        TreeNode root = new TreeNode(4, node2, node6);

        System.out.println(
        new LeetCode().serialize(root));

       var l =  new LeetCode().parseStringToList(new LeetCode().serialize(root));
        System.out.println(l);


        var r = new LeetCode().deserializeHelper(l);

        System.out.println(r);
    }
}
