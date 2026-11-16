package Search.BinarySearchTree;

// @Author: Shiyi Nie(ElevenNie11)
// @Date: 2026-11-16
// @Content: BST-Insert Operation

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

// 递归删除操作：删除二叉搜索树中的目标值，返回删除后的根节点
// 假设树中不存放重复值
public class BST_Delete {
    public static TreeNode Delete_recursion(TreeNode root, int target){
        if(root == null){
            return null;
        }
        if(target < root.value){        // 在左子树中删除
            root.left = Delete_recursion(root.left, target);
        }else if(target > root.value){  // 在右子树中删除
            root.right = Delete_recursion(root.right, target);
        }else{ 
            // else：找到target了，开始准备删除（分3种情况）：
            // 情况01：目标节点没有左子树
            if(root.left == null){
                return root.right;
            }
            // 情况02：目标节点没有右子树
            if(root.right == null){
                return root.left;
            }
            // 情况03：目标节点有左、右子树
            // 找到右子树中最小的节点
            TreeNode sucessor = root.right;
            // 【从右孩子successor = root.right开始】，一直沿着【左指针successor = successor.left】走，就能找到它
            while(sucessor.left != null){
                sucessor = sucessor.left;
            }
            // 后继节点原本还留在右子树里：所以要递归把它删除，并把更新后的右子树重新接回root.right
            root.value = sucessor.value;
            root.right = Delete_recursion(root.right, sucessor.value);
        }
        return root;
    }

    // 中序遍历打印顺序结果
    public static void printInorder(TreeNode node){
        if(node == null){
            return ;
        }
        printInorder(node.left);
        System.out.print(node.value + " ");
        printInorder(node.right);
    }

    // 测试BST删除操作
    public static void main(String[] args){
        // 构造一棵二叉搜索树BST
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);

        // 目标节点为8（需要被删除）
        int target = 8;
        System.out.println("删除前: ");
        printInorder(root);             // 示例输出：1 3 6 8 10 14 
        System.out.println();
        // 调用BST删除函数
        Delete_recursion(root, target);
        System.out.println("删除后: ");  // 示例输出：1 3 6 10 14 
        printInorder(root);
    }
}
