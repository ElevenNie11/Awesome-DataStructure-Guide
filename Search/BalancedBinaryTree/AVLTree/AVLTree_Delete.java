package Search.BalancedBinaryTree.AVLTree;

// @Author: Shiyi Nie(ElevenNie11)
// @Date: 2026-11-17
// @Content: AVL Tree-Delete
// @Helper: HelperFunctions are same to AVLTree_Insert.java

// 辅助函数都和【AVL树的插入操作】是一样的
public class AVLTree_Delete{
    // 当前类内部定义TreeNode，避免与同包其他文件中的TreeNode重名
    // 为什么定义成静态类，简明原因可见Search/BeforeStartYouBetterRead.md文档
    static class TreeNode{
        int value;
        int height;
        TreeNode left;
        TreeNode right;

        TreeNode(int value){
            this.value = value;
            this.height = 1;         // 树的高度TreeHeight默认为1
        }
    }

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
            return null;        // 没有找到目标值，返回空节点
        }

        if(value < root.value){
            root.left = deleteRecursion(root.left, value);
        }else if(value > root.value){
            root.right = deleteRecursion(root.right, value);
        }else{
            // 情况1：待删除节点是叶子节点，或只有一个孩子
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }

            // 情况2：待删除节点有两个孩子
            // 找到右子树中的最小节点，用它替换当前节点的值
            TreeNode successor = minNode(root.right);
            root.value = successor.value;

            // 从右子树中删除已经移到当前位置的中序后继
            root.right = deleteRecursion(root.right, successor.value);
        }

        // 删除以后先更新当前节点的树高，再检查平衡因子
        updateHeight(root);
        int BF = getBF(root);

        // LL：左子树过高，且左孩子BF大于等于0，进行一次右旋
        if(BF > 1 && getBF(root.left) >= 0){
            return rightRotate(root);
        }
        // LR：左子树过高，且左孩子BF小于0，先左旋再右旋
        if(BF > 1 && getBF(root.left) < 0){
            root.left = leftRotate(root.left);    // 先对左孩子左旋
            return rightRotate(root);             // 再对失衡节点右旋
        }
        // RR：右子树过高，且右孩子BF小于等于0，进行一次左旋
        if(BF < -1 && getBF(root.right) <= 0){
            return leftRotate(root);
        }
        // RL：右子树过高，且右孩子BF大于0，先右旋再左旋
        if(BF < -1 && getBF(root.right) > 0){
            root.right = rightRotate(root.right); // 先对右孩子右旋
            return leftRotate(root);              // 再对失衡节点左旋
        }

        // 当前节点仍然平衡，无需旋转
        return root;
    }

    // 辅助函数06：寻找以root为根的子树中的最小节点
    public static TreeNode minNode(TreeNode root){
        TreeNode current = root;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }

    // 打印中序遍历结果
    public static void printInorder(TreeNode root){
        if(root == null){
            return ;
        }
        printInorder(root.left);
        System.out.print(root.value + " ");
        printInorder(root.right);
    }

    // 测试AVL树的【删除】操作
    public static void main(String[] args){
        // 直接构造一棵合法的AVL树，每个节点的BF都属于[-1, 0, 1]
        //              30
        //            /    \
        //          21      40
        //         /  \      \
        //       10   25      50
        TreeNode root = new TreeNode(30);
        root.left = new TreeNode(21);
        root.right = new TreeNode(40);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(25);
        root.right.right = new TreeNode(50);

        // 设置各节点的初始树高
        root.left.height = 2;
        root.right.height = 2;
        root.height = 3;

        System.out.print("删除前的中序遍历结果为: ");
        printInorder(root);
        System.out.println();

        int deleteValue = 30;
        root = deleteRecursion(root, deleteValue);

        System.out.print("删除 " + deleteValue + " 后的中序遍历结果为: ");
        printInorder(root);
        System.out.println();
        System.out.println("删除后的根节点为: " + root.value);
    }
}
