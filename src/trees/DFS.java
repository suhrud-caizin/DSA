package trees;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DFS {
    int maxDepth;
    int diameter;

    int smallestNodeCounter;

    TreeNode lca;

    private TreeNode maxDepth(TreeNode node, int level) {
        if (node == null) return null;

        var left = maxDepth(node.left, level + 1);
        var right = maxDepth(node.right, level + 1);

        if (left == null && right == null) {
            maxDepth = Integer.max(maxDepth, level);
        }

        return node;
    }

    public int getMaxDepth(TreeNode root) {
        maxDepth(root, 0);
        return maxDepth;
    }

    public TreeNode invert(TreeNode root) {
        if (root == null) return null;
        var left = invert(root.left);
        var right = invert(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    public int diameter(TreeNode root) {
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        diameter = Math.max(diameter, leftHeight + rightHeight + 2);
        return diameter;
    }

    private int height(TreeNode node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        int dia = leftHeight + rightHeight + 1;
        diameter = Math.max(diameter, dia);

        return Math.max(leftHeight, rightHeight) + 1;

    }

    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                var temp = root.left;
                while (temp.right != null) temp = temp.right;
                temp.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root.left, root.val, null) && isValidBST(root.right, null, root.val);
    }

    private boolean isValidBST(TreeNode root, Integer high, Integer low) {
        if (root == null) return true;

        boolean tmp = false;
        if (high != null) tmp = (high > root.val);
        if (low != null) tmp = (low < root.val);

        return tmp && isValidBST(root.left, root.val, low) && isValidBST(root.right, high, root.val);

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        isLCA(root, p, q);
        return lca;
    }


    public boolean isLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean left = isLCA(root.left, p, q);
        boolean right = isLCA(root.right, p, q);

        boolean rootMatch = root == p || root == q;
        if ((left && right) || (rootMatch && (left || right))) lca = root;

        if (rootMatch) return true;
        return left || right;

    }

    public int kthSmallest(TreeNode root, int k) {
        var kthNode = kthSmallestHelper(root, k);
        if (kthNode != null) return kthNode.val;
        return 0;
    }

    private TreeNode kthSmallestHelper(TreeNode root, int k) {
        if (root == null) return null;
        kthSmallestHelper(root.left, k);
        smallestNodeCounter++;
        if (smallestNodeCounter == k) return root;
        kthSmallestHelper(root.right, k);


        return null;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        if (preorder.length == 1 && inorder.length == 1) return new TreeNode(preorder[0]);

        int rootVal = preorder[0];
        int rootIndex;
        for (rootIndex = 0; rootIndex < inorder.length; rootIndex++) {
            if (inorder[rootIndex] == rootVal) {
                break;
            }
        }

        var root = new TreeNode(rootVal);
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, rootIndex + 1), Arrays.copyOfRange(inorder, 0, rootIndex));
        root.right = buildTree(Arrays.copyOfRange(preorder, rootIndex + 1, preorder.length), Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length));
        return root;
    }

    public TreeNode buildTree2(int[] postorder, int[] inorder) {
        if (postorder.length == 0) return null;
        if (postorder.length == 1) return new TreeNode(postorder[0]);

        int rootVal = postorder[postorder.length - 1];
        int rootIndex;
        for (rootIndex = 0; rootIndex < inorder.length; rootIndex++) {
            if (inorder[rootIndex] == rootVal) {
                break;
            }
        }

        var root = new TreeNode(rootVal);
        root.left = buildTree(Arrays.copyOfRange(postorder, 0, rootIndex), Arrays.copyOfRange(inorder, 0, rootIndex));
        root.right = buildTree(Arrays.copyOfRange(postorder, rootIndex, postorder.length), Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length));
        return root;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasPathSum(root, targetSum, 0);
    }

    private boolean hasPathSum(TreeNode root, int targetSum, int currentSum) {
        if (root.left == null && root.right == null) return targetSum == currentSum;
        System.out.println();
        var leftSumMatch = hasPathSum(root.left, targetSum, currentSum + root.val);
        if (leftSumMatch) return true;
        return hasPathSum(root.right, targetSum, currentSum + root.val);
    }

//    public int sumNumbers(trees.TreeNode root) {
//
//    }

    int rootSum;

    private void sumNumbers(TreeNode root, String currentSum) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            // System.out.println(currentSum);
            rootSum += Integer.parseInt(currentSum);
        }
        var temp = currentSum.concat(Integer.toString(root.val));
        sumNumbers(root.left, temp);
        sumNumbers(root.right, temp);
    }


    int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return maxPathSum;
    }

    private int helper(TreeNode root) {
        if (root == null) return 0;

        int leftmax = helper(root.left);
        int rightmax = helper(root.right);

        leftmax = Math.max(0, leftmax);
        rightmax = Math.max(0, rightmax);

        int maxAtNode = root.val + leftmax + rightmax;

        if (maxAtNode > maxPathSum) maxPathSum = maxAtNode;
        return Math.max(leftmax, rightmax) + root.val;
    }


    int luv = 0;

    public int longestUnivaluePath(TreeNode root) {
        wrapper(root);
        return luv;
    }

    public void wrapper(TreeNode root) {
        if (root == null) return;
        luf(root);
        wrapper(root.left);
        wrapper(root.right);
    }

    private void luf(TreeNode root) {
        var left = uv(root.left, root.val);
        var right = uv(root.right, root.val);

        System.out.println(left + " " + right + " ");
        luv = Integer.max(left + right, luv);
    }

    private int uv(TreeNode root, int val) {
        if (root == null) return 0;
        if (root.val != val) return 0;
        return Integer.max(uv(root.left, root.val), uv(root.right, root.val)) + 1;
    }

    private int lpHelper(TreeNode root, int val) {
        if (root == null) return 0;

        int leftmax = lpHelper(root.left, root.val);
        int rightmax = lpHelper(root.right, root.val);


        int maxAtNode = leftmax + rightmax;
        if (maxAtNode > luv) luv = maxAtNode;

        if (root.val != val) return 0;
        return Math.max(leftmax, rightmax) + 1;
    }

    List<Integer> inorder = new ArrayList<>();

//    public List<List<Integer>> closestNodes(trees.TreeNode root, List<Integer> queries) {
//        inorder(root);
//        List<List<Integer>> result = new ArrayList<>(queries.size());
//        int end = inorder.size() - 1;
//        for (int query : queries) {
//            result.add(findSmallestOrLargest(query, 0, end, -1, -1));
//        }
//        return result;
//    }

    // private List<Integer> findSmallestOrLargest(int query) {
    //     int start = 0, end = inorder.size() - 1;
    //     int smallest = -1, largest = -1;
    //     while (start <= end) {
    //         int mid = (start + end) / 2;
    //         int midVal = inorder.get(mid);
    //         if (midVal < query) {
    //             smallest = midVal;
    //             start = mid + 1;
    //         } else if (midVal > query) {
    //             end = mid - 1;
    //             largest = midVal;
    //         } else {
    //             return Arrays.asList(query, query);
    //         }
    //     }
    //     return Arrays.asList(smallest, largest);
    // }

    private void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        inorder.add(root.val);
        inorder(root.right);
    }

    int width;

    public int widthOfBinaryTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        Map<TreeNode,Integer> m = new HashMap<>();

        m.put(root,1);
        q.add(root);
        while(!q.isEmpty()){
            int qLength = q.size();
            int[] myArray = new int[qLength];
            for (int i = 0; i < qLength; i++) {
                var iTreeNode = q.poll();

                myArray[i] = m.get(iTreeNode);
                if(iTreeNode.left!=null) {
                    q.add(iTreeNode.left);
                    m.put(iTreeNode.left, m.get(iTreeNode) * 2);
                }

                if(iTreeNode.right!=null) {
                    q.add(iTreeNode.right);
                    m.put(iTreeNode.right, (m.get(iTreeNode) * 2)+1);
                }


            }
            var w = myArray[qLength-1] - myArray[0] + 1;
            if(w> width) width = w;
        }
        return width;
    }

    int highestScore;
    int freq;
    int totalSize;
    public int countHighestScoreNodes(int[] parents) {
        totalSize = parents.length;
        TreeNode root = buildTree(parents);
        countHighestScoreNodes(root);
        return freq;
    }

    long maxProduct = 1;
    int totalSum;
    final int MOD = 1000000007;
    public int maxProduct(TreeNode root) {
        calcSum(root);
        maxProductHelper(root);
        return (int) (maxProduct%MOD);
    }

    private void calcSum(TreeNode root){
        if(root == null) return;
        totalSum+= root.val;
        calcSum(root.left);
        calcSum(root.right);
    }

    private int maxProductHelper(TreeNode root){
        if(root == null) return 0;
        int left = maxProductHelper(root.left);
        int right = maxProductHelper(root.right);
        int stSum =  left +right +root.val;
        int upper = totalSum - stSum;

        long prod = 1;
        if(upper > 0 ) prod *= upper;
        if(stSum > 0 ) prod *= stSum;


        maxProduct = Math.max(prod,maxProduct);

        return stSum;
    }

    private TreeNode buildTree(int[] parents){
        TreeNode root = new TreeNode(0);
        var list = findIndexes(parents,0);
        while(!list.isEmpty()){
            var tmp = new ArrayList<Integer>();
            list.forEach(l->{
                tmp.addAll(findIndexes(parents,l));
            });
            list = tmp;
        }
        return root;
    }
    private int countHighestScoreNodes(TreeNode root){
        if(root == null) return 0;
        int left = countHighestScoreNodes(root.left);
        int right = countHighestScoreNodes(root.right);
        int upper = totalSize - left -right -1;

        int score = 1;
        if(left > 0 ) score *= left;
        if(right > 0 ) score *= right;
        if(upper > 0 ) score *= upper;

        if(score> highestScore){
            highestScore = score;
            freq = 1;
        } else if (score == highestScore) {
            freq++;
        }

        return left+right+1;
    }

    public List<Integer> findIndexes(int[] array, int target) {
        return IntStream.range(0, array.length)
                .filter(i -> array[i] == target)
                .boxed()
                .collect(Collectors.toList());
    }

    public int pathSum(TreeNode root, int targetSum) {
        return 0;
    }
}

