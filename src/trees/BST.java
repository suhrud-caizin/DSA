package trees;

public class BST {
    private Node root;

    void populate(int[] data){
//        for (int i = 0; i < data.length; i++) {
//            root = populate(root,data[i]);
//        }

            for (int i = 0; i < 1000; i++) {
            root = populate(root, i);
        }
    }

    void populateSorted(int[] data){
        populateSorted(0, data.length,data );
    }

    private void populateSorted(int start, int end, int[]data){
        if(start>=end){return;}

        int mid = (end+start)/2;
        root = populate(root,data[mid]);

        populateSorted(start, mid, data);
        populateSorted(mid+1, end, data);

    }


    private Node populate(Node node, int value){
        if(node == null){
            return new Node(value);
        }
        if(value< node.Value()){
            node.left = populate(node.left,value);
        }else{

            node.right = populate(node.right,value);

        }

        node.height = Math.max(height(node.left),height(node.right))+1;
        return rotate(node);
    }

    public int height(){
        if(root == null) return -1;
        return root.height;
    }
    private int height(Node node){
        if(node == null) return -1;
        return node.height;
    }

    public void display(){
//        inorder(root);
        preorder(root);
//        postorder(root);
    }
    private void inorder(Node node){
        if(node == null ) return;
        System.out.println(node.Value());
        inorder(node.left);
        inorder(node.right);

    }

    private void preorder(Node node){
        if(node == null ) return;
        preorder(node.left);
        System.out.println(node.Value() +"  "+ node.height);
        preorder(node.right);

    }

    private void postorder(Node node){
        if(node == null ) return;
        postorder(node.left);
        postorder(node.right);
        System.out.println(node.Value());

    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if( node == null) return true;
        return Math.abs(height(node.left)-height(node.right))<=1 && (isBalanced(node.left) && isBalanced(node.right));
    }

    private Node rotate(Node node){
        if(height(node.left) - height(node.right) > 1){
            // left heavy case

            if(height(node.left.left) > height(node.left.right)){
                // left-left case
                 node = rightRotate(node);

            }

            if(height(node.left.right) > height(node.left.left)){
                // left right case
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }

        }

        if(height(node.right) - height(node.left) > 1){
            // right heavy case
            if(height(node.right.right) > height(node.right.left)){
                // right-right case

                node = leftRotate(node);
            }

            if(height(node.right.left) > height(node.right.right)){
                // right left case
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }
        }

        return node;
    }

    private Node leftRotate(Node node) {
        Node p = node.right;
        Node t = p.left;
         node.right = t;
         p.left = node;

        node.height = Math.max(height(node.left), height(node.right))+1;
        p.height = Math.max(height(p.left), height(p.right))+1;
        return p;
    }

    private Node rightRotate(Node node) {
        Node c  = node.left;
        Node t =  c.right;

        node.left = t;
        c.right = node;

        node.height = Math.max(height(node.left), height(node.right))+1;
        c.height = Math.max(height(c.left), height(c.right))+1;
        return c;
    }
}

class Node{
    private final int value;

    Node(int value){
        this.value = value;
    }
    Node left;
    Node right;
    int height;

    int Value(){
        return this.value;
    }

    @Override
    public String toString() {
        return "trees.Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                ", height=" + height +
                '}';
    }
}