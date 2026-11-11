package Tree.BinaryTree.AwesomeTraversal;

public class postoderTraversal {
    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value){
            this.value = value;
        }
    }

    // 后序遍历：左、右、根
    public static void traverse(Node node){
        if(node == null){
            return;
        }
        traverse(node.left);
        traverse(node.right);
        System.out.print(node.value + " ");
    }

    public static void main(String[] args){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        traverse(root);
    }
}
