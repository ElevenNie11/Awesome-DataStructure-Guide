package Search.BalancedBinaryTree.AVLTree;

public class AVLTree_Insert01 {
    // 获取节点高度，空节点的高度为0
    private static int getHeight(TreeNode node){
        if(node == null){
            return 0;
        }else{
            return node.height;
        }
    }

    // 更新节点高度
    private static void updateHeight(TreeNode node){
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    // 计算平衡因子：左子树高度减去右子树高度
    private static int getBalanceFactor(TreeNode node){
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }
//--------------




    // 对以root为根的子树进行右旋
    private static TreeNode rightRotate(TreeNode root){
        TreeNode newRoot = root.left;
        TreeNode movedSubtree = newRoot.right;

        newRoot.right = root;
        root.left = movedSubtree;

        updateHeight(root);       // 先更新原根节点的高度
        updateHeight(newRoot);    // 再更新新根节点的高度
        return newRoot;
    }

    // 对以root为根的子树进行左旋
    private static TreeNode leftRotate(TreeNode root){
        TreeNode newRoot = root.right;
        TreeNode movedSubtree = newRoot.left;

        newRoot.left = root;
        root.right = movedSubtree;

        updateHeight(root);       // 先更新原根节点的高度
        updateHeight(newRoot);    // 再更新新根节点的高度
        return newRoot;
    }

//------------------------



    // 插入新值并调整平衡，返回调整后的子树根节点
    public static TreeNode insertRecursion(TreeNode root, int value){
        if(root == null){
            return new TreeNode(value);    // 找到插入位置，创建新节点
        }

        if(value < root.value){
            root.left = insertRecursion(root.left, value);    // 插入左子树
        }else if(value > root.value){
            root.right = insertRecursion(root.right, value);  // 插入右子树
        }else{
            return root;    // 不插入重复值
        }

        updateHeight(root);    // 插入后更新当前节点高度
        int balanceFactor = getBalanceFactor(root);

        // LL：左子树的左侧过高，进行一次右旋
        if(balanceFactor > 1 && value < root.left.value){
            return rightRotate(root);
        }
        // RR：右子树的右侧过高，进行一次左旋
        if(balanceFactor < -1 && value > root.right.value){
            return leftRotate(root);
        }
        // LR：左子树的右侧过高，先左旋再右旋
        if(balanceFactor > 1 && value > root.left.value){
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        // RL：右子树的左侧过高，先右旋再左旋
        if(balanceFactor < -1 && value < root.right.value){
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;    // 当前子树平衡，无需旋转
    }





    //==================
    // 中序遍历，按从小到大的顺序打印节点
    private static void printInOrder(TreeNode root){
        if(root == null){
            return;
        }
        printInOrder(root.left);
        System.out.print(root.value + " ");
        printInOrder(root.right);
    }

    // 测试AVL树的插入操作
    public static void main(String[] args){
        TreeNode root = null;    // 从空树开始
        int[] values = {10, 20, 30, 40, 50, 25};

        for(int value : values){
            root = insertRecursion(root, value);    // 每次插入一个值并自动调整平衡
        }

        System.out.print("插入后的AVL树中序遍历结果为:");
        printInOrder(root);
        System.out.println();
        System.out.println("根节点值: " + root.value);
        System.out.println("树的高度: " + root.height);
    }
}
