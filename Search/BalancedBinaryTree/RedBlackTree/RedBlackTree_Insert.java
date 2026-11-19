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
         while(node != root && getColor(node.parent) == COLOR.red){
            TreeNode parent = node.parent;
            TreeNode grandparent = parent.parent;
            TreeNode uncle;

            // 先按"父节点在祖父的左边还是右边"分成互为镜像的两大支
            if(parent == grandparent.left){
                uncle = grandparent.right;

                if(getColor(uncle) == COLOR.red){
                    // 场景2：uncle 是红的，光变色就够了，不用旋转
                    parent.color = COLOR.black;
                    uncle.color = COLOR.black;
                    grandparent.color = COLOR.red;
                    // 父和叔都变黑了，冲突上移动
                    node = grandparent;
                }else{
                    if(node == parent.right){
                        // 场景3：uncle是黑的，且node在parent右边（triangle类型）
                        // 先左旋 parent，把折线掰成line类型
                        node = parent;
                        root = leftRotate(node, root);
                        // 转完 node 已经下沉一层，parent/grandparent 必须重新取
                        parent = node.parent;
                        grandparent = parent.parent;
                    }
                    // 场景4：uncle 是黑的，且已经是"左-左"直线
                    // 父染黑、祖父染红，再右旋祖父，让父节点顶上来
                    parent.color = COLOR.black;
                    grandparent.color = COLOR.red;
                    root = rightRotate(grandparent, root);
                }
            }else{
                // 镜像：父节点在祖父的右边
                uncle = grandparent.left;

                if(getColor(uncle) == COLOR.red){
                    // 场景2的镜像：uncle 红，变色 + 上移
                    parent.color = COLOR.black;
                    uncle.color = COLOR.black;
                    grandparent.color = COLOR.red;
                    node = grandparent;
                }else{
                    if(node == parent.left){
                        // 场景3的镜像：左-右"折线"，先右旋 parent 掰成"右-右"直线
                        node = parent;
                        root = rightRotate(node, root);
                        parent = node.parent;
                        grandparent = parent.parent;
                    }
                    // 场景4的镜像：父染黑、祖父染红，再左旋祖父
                    parent.color = COLOR.black;
                    grandparent.color = COLOR.red;
                    root = leftRotate(grandparent, root);
                }
            }
        }
        // 场景1的收尾：把根染黑（insert 末尾已经有一句 root.color = COLOR.black，这里直接返回）
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
