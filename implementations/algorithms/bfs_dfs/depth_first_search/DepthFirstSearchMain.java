package algorithms.bfs_dfs.depth_first_search;

public class DepthFirstSearchMain {
    public static void main(String[] args) {
        DepthFirstSearch dfs = new DepthFirstSearch();
        int[] values = {1, 2, 3, 4, 5, 6};

        TreeNode root = new TreeNode(values[0]);
        root.left = new TreeNode(values[1]);
        root.right = new TreeNode(values[2]);
        root.left.left = new TreeNode(values[3]);
        root.left.right = new TreeNode(values[4]);
        root.right.right = new TreeNode(values[5]);

        System.out.print("In-order: ");
        dfs.inorder(root);
        System.out.println();

        System.out.print("Pre-order: ");
        dfs.preorder(root);
        System.out.println();

        System.out.print("Post-order: ");
        dfs.postorder(root);
        System.out.println();

        TreeNode singleNode = new TreeNode(42);
        System.out.print("Single node (pre-order): ");
        dfs.preorder(singleNode);
        System.out.println();

        System.out.print("Empty tree (in-order): ");
        dfs.inorder(null);
        System.out.println();
    }
}

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}

class DepthFirstSearch {

    public void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public void preorder(TreeNode root) {
        if (root == null) return;

        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public void postorder(TreeNode root) {
        if (root == null) return;

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }
}
