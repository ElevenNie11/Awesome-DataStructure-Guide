package Search.BalancedBinaryTree.RedBlackTree;
import Search.BalancedBinaryTree.RedBlackTree.Rotation.TreeNode;

public class RedBlackTree_Insert {
    // 节点颜色（用枚举enum来表示）
    public enum COLOR{
        black,
        red
    }


    // 辅助函数01：获取节点颜色，空节点视为黑色
    public static COLOR getColor(TreeNode node){
        if(node == null){
            return COLOR.black;
        }else{
            return node.color;
        }
    }


    // 辅助函数02：左旋
    public static TreeNode leftRotate(TreeNode node, TreeNode root){
        if(node == null || node.right == null){
            return root;
        }
        TreeNode right = node.right;
        node.right = right.left;
        if(right.left != null){
            right.left.parent = node;
        }
        right.parent = node.parent;
        if(node.parent == null){
            root = right;
        }else if(node == node.parent.left){
            node.parent.left = right;
        }else{
            node.parent.right = right;
        }
        right.left = node;
        node.parent = right;
        return root;
    }


    // 辅助函数03：右旋
    public static TreeNode rightRotate(TreeNode node, TreeNode root){
        if(node == null || node.left == null){
            return root;
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


    // 【关键逻辑】：插入新值并修复红黑树性质
    public static TreeNode insert(TreeNode root, int value){
        TreeNode parent = null;
        TreeNode curr = root;

        // 【找位置】按照二叉搜索树的规则查找插入位置curr
        while(curr != null){
            parent = curr;
            if(value < curr.value){
                curr = curr.left;
            }else if(value > curr.value){
                curr = curr.right;
            }else{
                return root;                // curr = curr.value 不插入重复值
            }
        }

        TreeNode newNode = new TreeNode(value);
        newNode.parent = parent;
        if(parent == null){
            root = newNode;                 // 空树：让新节点成为根节点
        }else if(value < parent.value){
            parent.left = newNode;
        }else{
            parent.right = newNode;
        }

        root = fixViolation(root, newNode); // 新节点默认为红色，插入后检查并修复红黑树
        root.color = COLOR.black;           // 根节点必须为黑色
        return root;
    }


    // 辅助函数04：修复好树的平衡
    // 在修复过程种我们会使用到失衡节点的【uncle parent grandparent】
    public static TreeNode fixViolation(TreeNode root, TreeNode node){
        // 预备插入的新节点都默认为红色
         while(node != root && getColor(node.parent) == COLOR.red){
            TreeNode parent = node.parent;
            TreeNode grandparent = parent.parent;
            TreeNode uncle;

            // 最外层分类：先按"父节点在祖父的左边还是右边"分类
            if(parent == grandparent.left){
                // 那么叔叔节点就将是祖父节点的右孩子
                uncle = grandparent.right;
                // 场景1：uncle是红的，只是recolor就够了，不用旋转
                if(getColor(uncle) == COLOR.red){
                    parent.color = COLOR.black;
                    uncle.color = COLOR.black;
                    grandparent.color = COLOR.red;
                    // 父和叔都变黑了，祖父变红，冲突上移动
                    node = grandparent;
                }else{  // uncle是黑的
                    if(node == parent.right){
                        // 场景2：uncle是黑的，且node在parent右边（triangle类型）
                        // rotate parent(left rotate)
                        root = leftRotate(parent, root);
                        grandparent = parent.parent;
                        parent = node;  // 左旋parent以后，node上升了一层
                    }else{
                        // 场景3：uncle 是黑的，且已经是line类型
                        // 右旋grandparent然后再recolor
                        root = rightRotate(grandparent, root);
                        parent.color = COLOR.red;
                        grandparent.color = COLOR.black; 
                    }
                }
            }else{
                uncle = grandparent.left;
                if(getColor(uncle) == COLOR.red){
                    parent.color = COLOR.black;
                    uncle.color = COLOR.black;
                    grandparent.color = COLOR.red;
                    node = grandparent;
                }else{
                    if(node == parent.right){
                        root = rightRotate(parent, root);
                        grandparent = parent.parent;
                        parent = node;
                    }else{
                        root = leftRotate(grandparent, root);
                        parent.color = COLOR.red;
                        grandparent.color = COLOR.black; 
                    }
                }
            }
        }
        return root;
    }


    // 辅助函数05：打印红黑树的中序遍历结果
    public static void printInorder(TreeNode root){
        if(root == null){
            return ;
        }
        printInorder(root.left);
        System.out.print(root.value + (root.color == COLOR.red ? "(红) " : "(黑) "));
        printInorder(root.right);
    }

    // 测试红黑树的插入操作
    // 【目的】：从空树开始，通过一步步插入，创建一棵红黑树
    public static void main(String[] args){
        TreeNode root = null;
        int[] values = {8, 5, 15, 12, 19, 10, 9, 13, 23};
        for(int value: values){
            root = insert(root, value);
        }
        System.out.print("插入各值以后的红黑树中序遍历结果为: ");
        printInorder(root);
        System.out.println();
        System.out.println("根节点的值: " + root.value);
        System.out.println("根节点颜色: " + (root.color == COLOR.red ? "红色" : "黑色"));
    }
}
