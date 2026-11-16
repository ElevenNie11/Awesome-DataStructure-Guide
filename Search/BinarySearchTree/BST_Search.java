package Search.BinarySearchTree;

class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class BST_Search {
    // 【查找方式01】：循环迭代iteration
    // 在二叉查找树中查找目标值，找到则返回对应节点，未找到则返回null
    public static TreeNode Search_iteration(TreeNode root, int target){
        TreeNode current = root;    // 从根节点开始查找

        // 【重要逻辑】：利用二叉查找树【左小右大】的性质缩小查找范围
        while(current != null){
            if(current.value == target){
                return current;            // 找到目标值，返回对应节点
            }
            if(target < current.value){
                current = current.left;    // 目标值较小，继续查找左子树
            }else{
                current = current.right;   // 目标值较大，继续查找右子树
            }
        }
        return null;                       // 遍历到空节点仍未找到
    }

    // 【查找方式02】：递归recursion
    public static TreeNode Search_recursion(TreeNode root, int target){
        if(root == null || root.value == target){
            return root;                                 // 空树或找到目标值时，返回当前节点
        }else if(target < root.value){
            return Search_recursion(root.left, target);  // 继续查找左子树
        }else if(target > root.value){
            return Search_recursion(root.right, target); // 继续查找右子树
        }else{
            return null;
        }
    }

    // 测试BST的查找操作
    public static void main(String[] args){
        // 构造一棵二叉搜索树BST
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);

        int target = 6;
        TreeNode iterationResult = Search_iteration(root, target);
        TreeNode recursionResult = Search_recursion(root, target);

        if(iterationResult != null){
            System.out.println("循环查找：已经找到目标值 " + iterationResult.value + " 是存在的");
        }else{
            System.out.println("循环查找：目标值不存在");
        }

        if(recursionResult != null){
            System.out.println("递归查找：找到目标值 " + recursionResult.value + " 是存在的");
        }else{
            System.out.println("递归查找：目标值不存在");
        }
    }
}
