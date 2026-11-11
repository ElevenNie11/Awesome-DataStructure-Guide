package Tree.BinaryTree.Awesome01;

// 一个节点
class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value){
        this.value = value;
    }
}

// 判断一棵树是否为二叉搜索树BST
public class checkBST {
    // 中序遍历展示BST从小到大的升序排列
    public static void inorderTraversal(TreeNode node){
        if(node == null){
            return ;
        }
        inorderTraversal(node.left);
        System.out.print(node.value + " ");
        inorderTraversal(node.right);
    }

    // 判断是否为BST
    public static boolean isBST(TreeNode root){
        return checkRange(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // 辅助函数：检查当前节点是否处于合法的取值范围内
    // 递归检查完左右子树
    private static boolean checkRange(TreeNode node, long min, long max){
        if(node == null){
            return true;
        }

        // 当前节点的值必须严格大于下界且严格小于上界
        if(node.value <= min || node.value >= max){
            return false;
        }

        // 左子树所有节点都必须小于当前节点
        // 右子树所有节点都必须大于当前节点
        // min是左子树的叶子节点
        // max是右子树的叶子节点
        return checkRange(node.left, min, node.value) && checkRange(node.right, node.value, max);
    }

    public static void main(String[] args){
        // 创建一棵合法的BST
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(9);
        root.right.left = new TreeNode(14);
        root.right.right = new TreeNode(16);

        System.out.print("合法BST的中序遍历结果: ");  // 7 8 9 10 12 14 15 16
        inorderTraversal(root);
        System.out.println();
        System.out.println("是否为BST: " + isBST(root));

        // 节点12虽然大于父节点5，但它位于根节点10的左子树中，因此不是BST
        TreeNode invalidRoot = new TreeNode(10);
        invalidRoot.left = new TreeNode(5);
        invalidRoot.right = new TreeNode(15);
        invalidRoot.left.right = new TreeNode(12);

        System.out.println("非法树是否为BST: " + isBST(invalidRoot));
    }
}
