package TreesRev;


import node.SegmentNode;
import trees.SegmentedTree;

// performing a query on range
// time complexity O(log n)
public class SegmentTrees {
    SegmentNode root;
    public SegmentTrees(int[] arr){
        this.root  = constructTree(arr,0, arr.length-1);
    }
    SegmentNode constructTree(int[] arr, int start, int end){

        if(start == end){
            return new SegmentNode(arr[start],start,end);
        }

        int mid = (start + end) / 2;
        SegmentNode l = constructTree(arr,start,mid);
        SegmentNode r = constructTree(arr,mid+1,end);


        int val = l.val + r.val;


        return new SegmentNode(
                val,
                l,
                r,
                start,
                end
        );
    }

    public int query(int start, int end){
        return queryHelper(root, start, end);
    }

    int queryHelper(SegmentNode root, int start, int end){
            if(root == null) return 0;

            if(root.start >= start && root.end <= end){
                return root.val;
            }

            if(root.start > end || root.end < start){
                return 0;
            }

            return queryHelper(root.left,start,end) + queryHelper(root.right,start,end);
    }

    public void update(int index, int val){
        updateHelper(index,val,root);
    }

    SegmentNode updateHelper(int index, int val, SegmentNode root){
        if(index == root.start && index == root.end){
            root.val = val;
            return root;
        }

        if(index >= root.start && index <= root.end){
            var l = updateHelper(index,val,root.left);
            var r = updateHelper(index,val,root.right);

            root.val = l.val + r.val;
        }

        return root;
    }
    public static void main(String[] args) {
        var temp = new SegmentTrees(new int[]{3,8,7,6,-2, -8, 4, 9});
        System.out.println(temp.query(2,6));
        temp.update(7,10);
        System.out.println(temp);
    }

}
