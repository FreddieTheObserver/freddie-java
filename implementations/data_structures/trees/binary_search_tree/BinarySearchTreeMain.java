package data_structures.trees.binary_search_tree;

import java.util.Arrays;

public class BinarySearchTreeMain {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int[] values = {8, 3, 10, 1, 6, 14, 4, 7, 13, 6};
        int[] removals = {1, 14, 3};

        for (int value : values) {
            bst.root = bst.insert(bst.root, value);
        }

        System.out.println("Inserted values: " + Arrays.toString(values));
        System.out.println("In-order traversal: " + Arrays.toString(inOrderValues(bst.root)));
        System.out.println("Search 7: " + bst.search(bst.root, 7));
        System.out.println("Search 2: " + bst.search(bst.root, 2));

        for (int value : removals) {
            bst.root = bst.remove(bst.root, value);
            System.out.println("After removing " + value + ": "
                    + Arrays.toString(inOrderValues(bst.root)));
        }
    }

    private static int[] inOrderValues(TreeNode node) {
        int[] values = new int[countNodes(node)];
        collectInOrder(node, values, new int[]{0});
        return values;
    }

    private static int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    private static void collectInOrder(TreeNode node, int[] values, int[] index) {
        if (node == null) {
            return;
        }

        collectInOrder(node.left, values, index);
        values[index[0]++] = node.value;
        collectInOrder(node.right, values, index);
    }
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

    public boolean searchRecur(TreeNode node, int value) {
        if (node == null) {
            return false;
        }
        if (node.value == value) {
            return true;
        }

        if (value < node.value) {
            return searchRecur(node.left, value);
        } else {
            return searchRecur(node.right, value);
        }
    }

    public boolean searchIte(TreeNode node, int value) {
        while (node != null) {
            if (node.value == value) {
                return true;
            }

            if (value < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
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
