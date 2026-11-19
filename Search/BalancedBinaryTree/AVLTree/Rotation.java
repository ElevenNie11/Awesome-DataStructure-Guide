package Search.BalancedBinaryTree.AVLTree;

public class Rotation {
    // 辅助函数01：获取每个节点的树高度TreeHeight
    public static int getHeight(TreeNode node){
        if(node == null){       // 空树高度为0
            return 0;
        }else{
            return node.height;
        }
    }

    // 辅助函数02：更新每个节点的树高
    public static void updateHeight(TreeNode node){
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    // 左旋转：返回旋转后该子树的新根
    //        node                      right
    //       /    \                    /     \
    //      a    right     --->      node     c
    //           /   \              /   \
    //          b     c            a     b
    public static TreeNode leftRotate(TreeNode node){
        // 01. 先把要node的右孩子存下来，后面node.right会被改掉
        TreeNode right = node.right;
        // 02. 把right的左子树b过继给node当右子树（b的取值正好落在node与right之间）
        node.right = right.left;
        // 03. 父子关系反转，node降为【right的左孩子】
        right.left = node;
        // 04. 先更新原根节点的高度，再更新新根节点的高度
        updateHeight(node);
        updateHeight(right);
        // 05. 把新的子树根交还给调用方
        return right;
    }


    
    // 右旋转：返回旋转后该子树的新根
    //           node                  left
    //          /    \                /    \
    //        left    c    --->      a     node
    //       /   \                        /   \
    //      a     b                      b     c
    public static TreeNode rightRotate(TreeNode node){
        TreeNode left = node.left;
        node.left = left.right;
        left.right = node;        // 父子关系反转，node降为【left的右孩子】
        updateHeight(node);
        updateHeight(left);
        return left;
    }

}
