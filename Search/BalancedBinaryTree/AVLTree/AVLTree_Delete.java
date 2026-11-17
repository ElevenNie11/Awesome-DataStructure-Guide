package Search.BalancedBinaryTree.AVLTree;

// @Author: Shiyi Nie(ElevenNie11)
// @Date: 2026-11-17
// @Content: AVL Tree-Delete
// @Helper: HelperFunctions are same to AVLTree_Insert.java

class TreeNode{
    int value;
    int height;
    TreeNode left;
    TreeNode right;

    TreeNode(int value){
        this.value = value;
        this.height = 1;
    }
}

// 辅助函数都和【AVL树的插入操作】是一样的
public class AVLTree_Delete{
    // 辅助函数01：获取每个节点的树高度TreeHeight
    public static int getHeight(TreeNode node){
        if(node == null){       // 空树高度为0
            return 0;
        }else{
            return node.height;
        }
    }

    // 辅助函数02：获取每个节点的平衡因子BalancedFactor(BF)
    public static int getBF(TreeNode node){
        if(node == null){
            return 0;
        }else{
            return getHeight(node.left) - getHeight(node.right);
        }
    }

    // 辅助函数03：更新每个节点的树高
    public static void updateHeight(TreeNode node){
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    // 辅助函数04：左旋
    public static TreeNode leftRotate(TreeNode root){
        TreeNode newRoot = root.right;
        TreeNode rotateNode = newRoot.left;     

        newRoot.left = root;
        root.right = rotateNode;    

        updateHeight(root);         // 先更新原根节点的高度
        updateHeight(newRoot);      // 再更新新根节点的高度

        return newRoot;
    }

    // 辅助函数05：右旋
    public static TreeNode rightRotate(TreeNode root){
        TreeNode newRoot = root.left;
        TreeNode rotateNode = newRoot.right;

        newRoot.right = root;
        root.left = rotateNode;

        updateHeight(root);
        updateHeight(newRoot);

        return newRoot;
    }

    // ---------------------------------------------------------------
    // 【关键逻辑】：删除目标值，并在递归回溯时更新高度+恢复AVL平衡
    public static TreeNode deleteRecursion(TreeNode root, int value){
        if(root == null){
            return null;
        }

        if(value < root.value){
            root.left = deleteRecursion(root.left, value);
        }else if(value > root.value){
            root.right = deleteRecursion(root.right, value);
        }else{
           // 待补充程序
        }

        updateHeight(root);
        // int BF = getBF(root);

        // 待补充程序

        return root;
    }

    // 打印中序遍历结果
    public static void printInorder(TreeNode root) {
        if(root == null){
            return ;
        }
        printInorder(root.left);
        System.out.print(root.value + " ");
        printInorder(root.right);
    }


    // 测试AVL树的【删除】操作
    public static void main(String[] args) {
        
    }
}