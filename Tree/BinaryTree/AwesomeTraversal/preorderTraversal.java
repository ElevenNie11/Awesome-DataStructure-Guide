package Tree.BinaryTree.AwesomeTraversal;

public class preorderTraversal {
    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value){
            this.value = value;
        }
    }

    // 前序遍历：根、左、右
    public static void traverse(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.value + " ");
        traverse(node.left);
        traverse(node.right);
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
