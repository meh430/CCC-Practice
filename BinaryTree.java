import java.util.ArrayList;

public class BinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(17);
        root.insert(100);
        root.insert(50);
        root.insert(25);
        root.insert(24);
        root.insert(13);
        root.insert(9);
        root.insert(2);

        root.printInOrder();
        root = new BinaryTree().balanceTree(root);
        System.out.println("");
        root.printInOrder();
    }

    void storeNodes(ArrayList<TreeNode> allNodes, TreeNode root) {
        if (root == null) {
            return;
        }

        storeNodes(allNodes, root.left);
        allNodes.add(root);
        storeNodes(allNodes, root.right);
    }

    TreeNode balanceTree(TreeNode root) {
        ArrayList<TreeNode> allNodes = new ArrayList<>();
        storeNodes(allNodes, root);
        int N = allNodes.size();
        return balanceTreeHelper(allNodes, 0, N - 1);
    }

    TreeNode balanceTreeHelper(ArrayList<TreeNode> allNodes, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode middleNode = allNodes.get(mid);
        middleNode.left = balanceTreeHelper(allNodes, start, mid - 1);
        middleNode.right = balanceTreeHelper(allNodes, mid + 1, end);

        return middleNode;
    }

}

class TreeNode {
    int nodeNum;
    TreeNode left, right;

    TreeNode(int nodeNum) {
        left = null;
        right = null;
        this.nodeNum = nodeNum;
    }

    void insert(int value) {
        if (value < this.nodeNum) {
            if (this.left == null) {
                this.left = new TreeNode(value);
            } else {
                this.left.insert(value);
            }
        } else {
            if (this.right == null) {
                this.right = new TreeNode(value);
            } else {
                this.right.insert(value);
            }
        }
    }

    boolean contains(int value) {
        if (value == nodeNum) {
            return true;
        } else if (value < this.nodeNum) {
            if (this.left == null) {
                return false;
            } else {
                return this.left.contains(value);
            }
        } else {
            if (this.right == null) {
                return false;
            } else {
                return this.right.contains(value);
            }
        }
    }

    void printInOrder() {
        if (left != null)
            left.printInOrder();

        System.out.print(nodeNum + " ");

        if (right != null)
            right.printInOrder();
    }
}
