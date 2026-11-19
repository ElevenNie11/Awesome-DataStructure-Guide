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
        TreeNode current = root;

        // 按照二叉搜索树的规则查找插入位置
        while(current != null){
            parent = current;
            if(value < current.value){
                current = current.left;
            }else if(value > current.value){
                current = current.right;
            }else{
                return root;        // 不插入重复值
            }
        }

        TreeNode newNode = new TreeNode(value);
        newNode.parent = parent;
        if(parent == null){
            root = newNode;         // 空树，新节点成为根节点
        }else if(value < parent.value){
            parent.left = newNode;
        }else{
            parent.right = newNode;
        }

        // 新节点默认为红色，插入后检查并修复红黑树
        root = fixAfterInsert(root, newNode);
        root.color = COLOR.black;   // 根节点必须为黑色
        return root;
    }

    // 辅助函数02：插入后的颜色调整与旋转
    public static TreeNode fixAfterInsert(TreeNode root, TreeNode node){
        while(node != root && getColor(node.parent) == COLOR.red){
            TreeNode parent = node.parent;
            TreeNode grandparent = parent.parent;

            // 父节点是祖父节点的左孩子
            if(parent == grandparent.left){
                TreeNode uncle = grandparent.right;

                // 情况一：叔叔节点为红色，父亲和叔叔变黑，祖父变红
                if(getColor(uncle) == COLOR.red){
                    parent.color = COLOR.black;
                    uncle.color = COLOR.black;
                    grandparent.color = COLOR.red;
                    node = grandparent;             // 向上继续检查
                }else{
                    // 情况二：LR，先对父节点左旋，转换为LL
                    if(node == parent.right){
                        node = parent;
                        root = Rotation.leftRotate(node, root);
                        parent = node.parent;
                        grandparent = parent.parent;
                    }

                    // 情况三：LL，父节点变黑、祖父变红，再对祖父右旋
                    parent.color = COLOR.black;
                    grandparent.color = COLOR.red;
                    root = Rotation.rightRotate(grandparent, root);
                }
            }else{
                TreeNode uncle = grandparent.left;

                // 情况一：叔叔节点为红色，父亲和叔叔变黑，祖父变红
                if(getColor(uncle) == COLOR.red){
                    parent.color = COLOR.black;
                    uncle.color = COLOR.black;
                    grandparent.color = COLOR.red;
                    node = grandparent;             // 向上继续检查
                }else{
                    // 情况二：RL，先对父节点右旋，转换为RR
                    if(node == parent.left){
                        node = parent;
                        root = Rotation.rightRotate(node, root);
                        parent = node.parent;
                        grandparent = parent.parent;
                    }

                    // 情况三：RR，父节点变黑、祖父变红，再对祖父左旋
                    parent.color = COLOR.black;
                    grandparent.color = COLOR.red;
                    root = Rotation.leftRotate(grandparent, root);
                }
            }
        }

        return root;
    }

    // 打印红黑树的中序遍历结果
    public static void printInorder(TreeNode root){
        if(root == null){
            return ;
        }
        // 递归
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
