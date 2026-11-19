package Search.BalancedBinaryTree.RedBlackTree;

public class RedBlackTree_Insert {
    // 节点颜色（用枚举enum来表示）
    public enum COLOR{
        black,
        red
    }
    // 红黑树节点定义（做成静态类避免编辑器报错）
    static class TreeNode{
        int value;
        COLOR color;
        TreeNode left, right, parent;

        TreeNode(int value){
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
            // 新插入的节点默认为红色
            this.color = COLOR.red;
        }
    }

    // 辅助函数01：获取节点颜色，空节点视为黑色
    public static COLOR getColor(TreeNode node){
        if(node == null){
            return COLOR.black;
        }else{
            return node.color;
        }
    }

}
