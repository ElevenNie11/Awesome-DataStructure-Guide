package Tree.BinaryTree;
import java.util.*;

// 判断一棵树是否为二叉树
public class isBinaryTree {
    public static boolean isBinaryT(TreeNode root){
        // 空树也是二叉树
        if(root == null){
            return true;
        }
        
        Set<TreeNode> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.empty()){
            TreeNode node = stack.pop();

            // 节点重复出现说明存在环或多个父节点，不符合树的结构
            if(!visited.add(node)){
                return false;
            }

            // 二叉树中每个节点最多有两个孩子
            if(node.children.size() > 2){
                return false;
            }

            for(TreeNode child : node.children){
                if(child == null){
                    return false;
                }
                stack.push(child);
            }
        }

        return true;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.addChild(left);
        root.addChild(right);
        left.addChild(new TreeNode(4));

        System.out.println(isBinaryT(root));
    }
}

class TreeNode {
    int value;
    ArrayList<TreeNode> children;

    public TreeNode(int value){
        this.value = value;
        children = new ArrayList<>();
    }

    public void addChild(TreeNode child){
        children.add(child);
    }
}
