package Search.BalancedBinaryTree.AVLTree;

// @Author: Shiyi Nie(ElevenNie11)
// @Date: 2026-11-16
// @Content: AVL Tree-Search

class TreeNode{
    int value;
    int height;                   // 在本程序的【查找逻辑中是用不上树高的】（只不过在AVL树的概念中涉及到树高和平衡因子，故在此处加上）
    TreeNode left;
    TreeNode right;
    TreeNode(int value){
        this.value = value;
        this.height = 1;         // 树的高度TreeHeight默认为1
    }
}

public class AVLTree {
    public static TreeNode searchRecursion(TreeNode root, int target){
        if(root == null){
            return null;
        }
        if(root.value == target){
            return root;
        }else if(target < root.value){
            return searchRecursion(root.left, target);
        }else{
            return searchRecursion(root.right, target);
        }
    }
    public static void main(String[] args){
        // 构造一棵平衡的AVL树
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(10);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(15);
        root.right.left = new TreeNode(25);
        root.right.right = new TreeNode(35);

        // 查找逻辑不使用节点的树高，因此高度值只在示例树中设置为正确值
        root.height = 3;
        root.left.height = 2;
        root.right.height = 2;

        // 检测两个值：15 和 22
        int[] targets = {15, 22};
        for(int target : targets){
            TreeNode result = searchRecursion(root, target);
            if(result != null){
                System.out.println("在AVL树中找到目标值: " + result.value);
            }else{
                System.out.println("目标值 " + target + " 不存在于此AVL树中");
            }
        }
    }
}
