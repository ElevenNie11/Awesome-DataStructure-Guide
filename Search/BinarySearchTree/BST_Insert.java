package Search.BinarySearchTree;

// @Author: Shiyi Nie(ElevenNie11)
// @Date: 2026-11-16
// @Content: BST-Insert Operation
// 【须知】
// @本程序复用了同一级目录中的【BST_Search.java】public类：TreeNode
// class TreeNode{
//     int value;
//     TreeNode left;
//     TreeNode right;
//     TreeNode(int value){
//         this.value = value;
//         this.left = null;
//         this.right = null;
//     }
// }


// 【正式开始】：从一棵空树开始，一步步插入value值，从而构造起一整棵BST二叉搜索树
public class BST_Insert {
    // 【插入方式01】：循环迭代iteration
    // 将新值插入二叉查找树，返回根节点
    public static TreeNode Insert_iteration(TreeNode root, int value){
        TreeNode newNode = new TreeNode(value);    // 创建待插入的新节点
        // 这是一个空树，新节点就是根节点
        if(root == null){
            return newNode;
        }
        TreeNode current = root;                   // 从根节点开始查找插入位置
        while(true){
            if(value == current.value){
                return root;                       // 不插入重复值
            }
            // 应该进到左子树
            if(value < current.value){
                if(current.left == null){
                    current.left = newNode;        // 左侧为空，将新节点插入左子树
                    return root;
                }
                current = current.left;            // 继续查找左子树
            }else{ // 应该进到右子树
                if(current.right == null){
                    current.right = newNode;       // 右侧为空，将新节点插入右子树
                    return root;
                }
                current = current.right;           // 继续查找右子树
            }
        }
    }

    // 【插入方式02】：递归recursion
    // 将新值插入二叉查找树，返回根节点
    public static TreeNode Insert_recursion(TreeNode root, int value){
        if(root == null){
            return new TreeNode(value);                        // 找到空位置，创建新节点
        }
        if(value < root.value){
            root.left = Insert_recursion(root.left, value);    // 插入左子树
        }else if(value > root.value){
            root.right = Insert_recursion(root.right, value);  // 插入右子树
        }
        return root;                                           // 相等时不插入重复值，并返回当前根节点
    }

    // 测试BST的插入操作
    public static void main(String[] args){
        TreeNode root = null;                        // 从空树开始
        int[] values = {8, 3, 10, 1, 6, 14};

        // 【迭代法】逐个插入节点
        for(int value : values){
            root = Insert_recursion(root, value);
        }
        System.out.println("迭代法BST插入完成,根节点的值为:" + root.value);

        // 【递归法】逐个插入节点
        for(int value : values){
            root = Insert_recursion(root, value);
        }
        System.out.println("递归法BST插入完成,根节点的值为:" + root.value);
    }
}
