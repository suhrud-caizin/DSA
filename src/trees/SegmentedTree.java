package trees;

public class SegmentedTree {
    SegmentedTreeNode root;

    public SegmentedTree(int[] data) {
        root = createSegmentedTree(data, 0, data.length - 1);
    }

    private SegmentedTreeNode createSegmentedTree(int[] data, int start, int end) {
        if (start == end) {
            var node = new SegmentedTreeNode(start, end);
            node.value = data[start];
            return node;
        }
        int mid = (start + end) / 2;
        var node = new SegmentedTreeNode(start, end);
        node.left = createSegmentedTree(data, start, mid);
        node.right = createSegmentedTree(data, mid + 1, end);
        node.value = node.left.value + node.right.value;
        return node;

    }

    public int query(int qsi, int qei) {
        return query(root, qsi, qei);
    }

    public void update(int index, int value){
        root.value = update(root,index,value);
    }

    private int update(SegmentedTreeNode node, int index, int value){
        if(index >= node.start && index <= node.end){
                if(index == node.start && index == node.end){
                    node.value = value;
                    return value;
                }
            node.value = update(node.left,index,value) + update(node.right,index,value);
        }

        return node.value;
    }

    private int query(SegmentedTreeNode node, int qsi, int qei) {
        if(node.start >= qsi && node.end <= qei){       // node is completely within query range
            return node.value;
        } else if(node.start > qei || node.end < qsi) {  // completely out of range
            return 0;
        }else{
            // partial OR complete overlap
            return query(node.left,qsi,qei) + query(node.right,qsi, qei);
        }
    }
}







class SegmentedTreeNode {
    int value;
    int start;
    int end;
    SegmentedTreeNode left;
    SegmentedTreeNode right;

    public SegmentedTreeNode(int start, int end){
        this.start = start;
        this.end = end;

    }

}
