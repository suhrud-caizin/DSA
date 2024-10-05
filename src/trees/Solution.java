package trees;

import java.util.ArrayList;
import java.util.List;



     public class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {

            var l = new ArrayList<Integer>();
            if(root.left != null)
                   l .addAll(inorderTraversal(root.left));

            l.add(root.val);

            if(root.right != null)
                l.addAll(inorderTraversal(root.right));

            return l;


        }


    }
