public class BinaryTree {
    public static void main (String[]args) {
        TreeNode root = new TreeNode(17);
        root.insert(5);
        root.insert(22);
        root.insert(30);
        root.insert(24);
        root.insert(13);
        root.insert(69);
        root.insert(44);

        root.printInOrder();

        System.out.println(root.contains(24));
    }
}

class TreeNode {
    int nodeNum;
    private TreeNode left, right;

    TreeNode(int nodeNum) {
        left = null;
        right = null;
        this.nodeNum = nodeNum;
    }

    void insert(int value) {
        if(value < this.nodeNum) {
            if(this.left == null) {
                this.left = new TreeNode(value);
            } else {
                this.left.insert(value);
            }
        } else {
            if(this.right == null) {
                this.right = new TreeNode(value);
            } else {
                this.right.insert(value);
            }
        }
    }

    boolean contains(int value) {
        if(value == nodeNum) {
            return true;
        } else if(value < this.nodeNum) {
            if(this.left == null) {
                return false;
            } else {
                return this.left.contains(value);
            }
        } else {
            if(this.right == null) {
                return false;
            } else {
                return this.right.contains(value);
            }
        }
    }

    void printInOrder() {
        if(left != null)
            left.printInOrder();

        System.out.print(nodeNum + " ");

        if(right != null)
            right.printInOrder();
    }
}
