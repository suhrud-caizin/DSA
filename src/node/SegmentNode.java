package node;

public class SegmentNode {
    public int val;
    public SegmentNode left;
    public SegmentNode right;
    public int start;
    public int end;

    public SegmentNode(int val, SegmentNode left, SegmentNode right, int s, int e){
        this.val = val;
        this.left = left;
        this.right = right;
        this.start = s;
        this.end = e;
    }

    public SegmentNode(int val, int s, int e){
        this.val = val;
        this.start = s;
        this.end = e;
    }
}
