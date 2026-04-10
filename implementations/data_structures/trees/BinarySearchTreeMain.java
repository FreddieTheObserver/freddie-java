package data_structures.trees;

public class BinarySearchTreeMain {

}

class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(TreeNode root) {
        this.root = root;
    }

    public boolean search(TreeNode node, int value) {
        if (node == null) {
            return false;
        }
        if (node.value == value) {
            return true;
        }

        if (value < node.value) {
            return search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }

    public TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val > root.value) {
            root.right = insert(root.right, val);
        } else if (val < root.value) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    public TreeNode minValueNode(TreeNode root) {
        TreeNode curr = root;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    public TreeNode remove(TreeNode root, int value) {
        if (root == null) return null;

        if (value > root.value) {
            root.right = remove(root.right, value);
        } else if (value < root.value) {
            root.left = remove(root.left, value);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                TreeNode minNode = minValueNode(root.right);
                root.value = minNode.value;
                root.right = remove(root.right, minNode.value);
            }
        }
        return root;
    }
}
