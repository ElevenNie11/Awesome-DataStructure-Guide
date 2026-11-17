package Search.BalancedBinaryTree.AVLTree;

// @Author: Shiyi Nie(ElevenNie11)
// @Date: 2026-11-17
// @Content: AVL Tree-Insert

class TreeNode{
    int value;
    int height;
    TreeNode left;
    TreeNode right;
    TreeNode(int value){
        this.value = value;
        this.height = 1;         // 树的高度TreeHeight默认为1
    }
}

public class AVLTree_Insert {
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

    // 【关键逻辑】：插入新值并调整平衡
    public static TreeNode insertRecursion(TreeNode root, int value){
        if(root == null){
            return new TreeNode(value);
        }

        if(value < root.value){
            root.left = insertRecursion(root.left, value);
        }else if(value > root.value){
            root.right = insertRecursion(root.right, value);
        }else{
            return root;    // 不插入重复值
        }

        updateHeight(root); // 插入后更新当前节点高度
        int BF = getBF(root);

        // 【不平衡的4种情况】
        // AVL树的前提之一是它是一棵BST
        // LL：左子树的左侧过高，进行一次右旋
        if(BF > 1 && value < root.left.value){
            return rightRotate(root);
        }
        // RR：右子树的右侧过高，进行一次左旋
        if(BF < -1 && value > root.right.value){
            return leftRotate(root);
        }
        // LR：左子树的右侧过高，先左旋再右旋
        if(BF > 1 && value > root.left.value){
            root.left = leftRotate(root.left);    // 先对左孩子左旋
            return rightRotate(root);             // 再对失衡节点右旋
        }
        // RL：右子树的左侧过高，先右旋再左旋
        if(BF < -1 && value < root.right.value){
            root.right = rightRotate(root.right); // 先对右孩子右旋
            return leftRotate(root);             // 再对失衡节点左旋
        }

        // 当前子树平衡，无需旋转
        return root;
    }

    // 打印AVL树的中序遍历结果
    public static void printInorder(TreeNode root){
        if(root == null){
            return ;
        }
        // 递归
        printInorder(root.left);
        System.out.print(root.value + " ");
        printInorder(root.right);
    }

    // 测试AVL树的递归插入操作
    // 【目的】：从空树开始，通过一步步插入，创建一棵AVL树
    public static void main(String[] args){
        TreeNode root = null;
        int[] values = {10, 21, 40, 30, 50, 25};
        for(int value: values){
            root = insertRecursion(root, value);
        }
        System.out.print("插入各值以后的AVL树中序遍历结果为: ");
        printInorder(root);
        System.out.println();
        System.out.println("根节点的值: " + root.value);
        System.out.println("根节点的高度: " + root.height);
        // 示例输出的结果是：10 21 25 30 40 50
        // 根节点：30
        // 高度：3
    }
}
