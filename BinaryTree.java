import com.sun.source.tree.Tree;

public class BinaryTree {

  public class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
      this.value = value;
      right = null;
      left = null;
    }
  }

  Node root;

  BinaryTree (int key) {
    this.root = new Node(key);
  }

  BinaryTree () {
    root = null;
  }

  private Node addRecursive(Node current, int value) {
    if (current == null) {
      return new Node(value);
    }

    if (value < current.value) {
      current.left = addRecursive(current.left, value);
    } else if (value > current.value) {
      current.right = addRecursive(current.right, value);
    } else {
      // value already exists
      return current;
    }

    return current;
  }

  public void add(int value) {
    root = addRecursive(root, value);
  }

}
