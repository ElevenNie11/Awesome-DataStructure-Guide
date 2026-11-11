package Tree.BinaryTree.AwesomeTraverse;

public class traverse_middle {
    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value){
            this.value = value;
        }
    }

    // 中序遍历：左、根、右
    public static void traverse(Node node){
        if(node == null){
            return;
        }
        traverse(node.left);
        System.out.print(node.value + " ");
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
