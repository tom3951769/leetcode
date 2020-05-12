
import javafx.scene.chart.Chart;
import 树.TreeNode;

import java.util.Arrays;
import java.util.HashMap;

public class LeetCodeSolution {

    //树的初始层数
    public int level=1;

    public static void main(String[] args) {
        TreeNode rootTree = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode leftChildTree = new TreeNode(4);
        TreeNode rightChildTree = new TreeNode(5);
        TreeNode rightChileTreeLeft = new TreeNode(6);
        TreeNode rightChileTreeRight = new TreeNode(7);
        TreeNode rightChileTreeLeft1 = new TreeNode(8);
        TreeNode rightChileTreeRight1 = new TreeNode(9);
        TreeNode rightChileTreeLeft2 = new TreeNode(10);
        TreeNode rightChileTreeRight2= new TreeNode(11);

        rightChileTreeLeft.left=rightChileTreeLeft1;
        rightChileTreeLeft.right=rightChileTreeRight1;
        rightChileTreeRight.left=rightChileTreeLeft2;
        rightChileTreeRight.right=rightChileTreeRight2;
        left.left=leftChildTree;
        left.right=rightChildTree;
        right.left=rightChileTreeLeft;
        right.right=rightChileTreeRight;
        rootTree.left=left;
        rootTree.right=right;

        int b = TreeNode.scanNodes(rootTree, leftChildTree);
        int c=TreeNode.getTreeCount(rootTree,4);
//        char[][] ma = new char[][]{new char[]{'1', '1', '1', '1'}, new char[]{'1', '1', '1', '1'}, new char[]{'1', '1', '1', '1'}};
//        int size = maximalRectangle(ma);
        int a = 0;

    }
    //236. 二叉树的最近公共祖先   dfs 深度优先搜索思想+ 递归
    TreeNode tree;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root,p,q);
        return tree;
    }
    public Boolean dfs(TreeNode root,TreeNode p,TreeNode q)
    {
        if(root==null)return false;
        Boolean lson=dfs(root.left,p,q);
        Boolean rson=dfs(root.right,p,q);
        if((lson&&rson)||((root==p||root==q)&&(lson||rson)))
        {
            tree=root;
        }
        if((lson||rson)||(root==p||root==q))return true;
        return false;
    }


    //最大矩形面积
    public static int maximalRectangle(char[][] matrix) {
        //最大矩形面积,长度,宽度
        int length = 1, broad = 1;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        //行
        int rows = matrix.length;
        //列
        int columns = matrix[0].length;
        //动态规划数组
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1;
                    if (j > 0) {
                        length += dp[i][j - 1] == 1 ? 1 : 0;
                    }
                    if (i > 0) {
                        broad += dp[i - 1][j] == 1 ? 1 : 0;
                    }
                } else {
                    break;
                }
            }
        }
        if (length > 0 && broad > 0) return (length / broad) * (broad / length);
        return 0;
    }

    //221.最大正方形  利用动态规划思路
    public static int maximalSquare(char[][] matrix) {
        int maxSize = 0, rows = matrix.length, columns = matrix[0].length;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return maxSize;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1;
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                }
                maxSize = Math.max(maxSize, dp[i][j]);
            }
        }
        return maxSize * maxSize;
    }


    //572. 另一个树的子树  递归，二叉树
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        } else if (s.val == t.val && isSameTree(s, t)) {
            return true;
        } else {
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        }
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        return (p == q) || (p != null && q != null && p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }

    public static int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int minvalue = Integer.MAX_VALUE;
        if (root.left != null) {
            TreeNode right = root.left;
            while (right.right != null) {
                right = right.right;
            }
            minvalue = root.val - right.val;
        }
        if (root.right != null) {
            TreeNode left = root.right;
            while (left.left != null) {
                left = left.left;
            }
            minvalue = Math.min(left.val - root.val, minvalue);
        }
        return Math.min(minvalue, Math.min(getMinimumDifference(root.left), getMinimumDifference(root.right)));
    }


    //532. 数组中的K-diff数对  排序 + 遍历+ 双指针 执行用时 : 4 ms , 在所有 java 提交中击败了 100.00% 的用户
    public static int findPairs(int[] nums, int k) {
        if (k < 0) return 0;
        Arrays.sort(nums);
        int start = 0, count = 0, prev = 0x7fffffff;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[start] > k || prev == nums[start]) {
                if (++start != i) {
                    i--;
                }
            } else if (nums[i] - nums[start] == k) {
                prev = nums[start++];
                count++;
            }
        }
        return count;
    }


    //1013.将数组分成和相等的三个部分  双指针思想
    public static boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int i : A) {
            sum += i;
        }
        if (sum % 3 != 0) {
            // 总和不是3的倍数，直接返回false
            return false;
        }
        // 使用双指针,从数组两头开始一起找，节约时间
        int left = 0;
        int leftSum = A[left];
        int right = A.length - 1;
        int rightSum = A[right];
        // 使用left + 1 < right 的原因，防止只能将数组分成两个部分
        // 例如：[1,-1,1,-1]，使用left < right作为判断条件就会出错
        while (left + 1 < right) {
            if (leftSum == sum / 3 && rightSum == sum / 3) {
                // 左右两边都等于 sum/3 ，中间也一定等于
                return true;
            }
            if (leftSum != sum / 3) {
                // left = 0赋予了初值，应该先left++，在leftSum += A[left];
                leftSum += A[++left];
            }
            if (rightSum != sum / 3) {
                // right = A.length - 1 赋予了初值，应该先right--，在rightSum += A[right];
                rightSum += A[--right];
            }
        }
        return false;
    }


    //167.两数之和   双指针,缩减搜索空间思想
    public static int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                return new int[]{i + 1, j + 1};
            }
        }
        return new int[]{-1, -1};
    }

    //1010.总持续时间可被 60 整除的歌曲  ,递推思想,一边统计余数出现次数,一边统计满足条件的组合个数
    public static int numPairsDivisibleBy60(int[] time) {
        int[] bucket = new int[60];
        int cnt = 0;
        for (int t : time) {
            int mod = t % 60;
            int remain = mod == 0 ? 0 : 60 - mod;
            cnt += bucket[remain];
            bucket[mod]++;
        }
        return cnt;
    }
}
