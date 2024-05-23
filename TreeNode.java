public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  public TreeNode addRecursive(TreeNode current, int value) {
    if (current == null) {
      return new TreeNode(value);
    }

    if (value < current.val) {
      current.left = addRecursive(current.left, value);
    } else if (value > current.val) {
      current.right = addRecursive(current.right, value);
    } else {
      // value already exists
      return current;
    }

    return current;
  }

}
