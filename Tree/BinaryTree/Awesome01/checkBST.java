package Tree.BinaryTree.Awesome01;
// 一个节点
class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
    }
}

// 判断一棵树是否为二叉搜索树
public class checkBST {
    public static void inorderTraversal(TreeNode node){
        if(node == null){
            return ;
        }
        inorderTraversal(node.left);
        System.out.print(node.value + " ");
        inorderTraversal(node.right);
    }
    // public static boolean isBinaryTree(){
        
    // }
}
