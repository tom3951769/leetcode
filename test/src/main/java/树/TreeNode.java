package 树;

public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int val;
    //树的初始层数
    public static int level;

    public TreeNode(int val) {
        this.val = val;
    }

    //返回二叉树的深度
    public static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }



    /**
     * 求树的第n层有多少个节点
     * @param root
     * @param n
     * @return
     */
    public static int getTreeCount(TreeNode root,int n){
        if(n <=0 || root == null){
            return 0;
        }
        System.out.println(root.val);
        if(n == 1) return 1;
        int right = getTreeCount(root.right,n-1);
        int left = getTreeCount(root.left,n-1);
        return left + right;
    }


    /**
     * 搜索scanNode在TreeNode的第几层,前提是scanNode一定是子节点
     * @param root
     * @param scanNode
     * @return
     */
    public static int scanNodes(TreeNode root, TreeNode scanNode) {
        int d1, d2;
        if (root == null) return 0;
        if (root == scanNode) return 1;
        d1 = scanNodes(root.left, scanNode);
        d2 = scanNodes(root.right, scanNode);
        if (d1 > 0 || d2 > 0) return 1 + Math.max(d1, d2);
        return 0;
    }

//        if (root == null || root.val == scanNode.val) return TreeNode.level;
//
//        scanNodes(root.left, scanNode);
//        scanNodes(root.right, scanNode);
//        TreeNode.level++;
//        return TreeNode.level;

}
