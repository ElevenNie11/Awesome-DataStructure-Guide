package Search.BalancedBinaryTree.RedBlackTree;

class TreeNode{
    int value;
    TreeNode left, right, parent;

    TreeNode(int value){
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

// 红黑树旋转操作：旋转只改指针，不改颜色
public class Rotation {
    // 左旋
    //        node                      right
    //       /    \                    /     \
    //      a    right     --->      node     c
    //           /   \              /   \
    //          b     c            a     b
    public static TreeNode leftRotate(TreeNode node, TreeNode root){
        if(node == null){
            return null;
        }
        TreeNode right = node.right;   // 先把node的右孩子存下来，因为下一步node.right就被改掉了
        node.right = right.left;       // 把right的左子树b过继给node当右子树（b的取值正好落在node与right之间）
        if(right.left != null){
            right.left.parent = node;  // b原来认right做父亲，现在改认node
        }
        right.parent = node.parent;    //  让right顶替node原来的位置，先接好它和"祖父"这一头
        if(node.parent == null){
            // node本来就是根，right上位后成为新的根
            root = right;
        }else if(node == node.parent.left){
            // node原本是左孩子，那么right也挂到左边
            node.parent.left = right;
        }else{
            // node原本是右孩子，那么right也挂到右边
            node.parent.right = right;
        }

        // 最后把父子关系反转，node成为right的左孩子
        right.left = node;
        node.parent = right;

        return root;
    }

    // 右旋
    // 右旋和左旋完全对称，把 left 和 right 对调即可
    //           node                  left
    //          /    \                /    \
    //        left    c    --->      a     node
    //       /   \                        /   \
    //      a     b                      b     c
    public static TreeNode rightRotate(TreeNode node, TreeNode root){
        if(node == null){
            return null;
        }
        TreeNode left = node.left;
        node.left = left.right;
        if(left.right != null){
            left.right.parent = node;
        }
        left.parent = node.parent;
        if(node.parent == null){
            root = left;
        }else if(node == node.parent.left){
            node.parent.left = left;
        }else{
            node.parent.right = left;
        }

        left.right = node;
        node.parent = left;
        
        return root;
    }
}
