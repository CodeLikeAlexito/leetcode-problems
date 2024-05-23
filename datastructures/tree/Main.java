package datastructures.tree;

public class Main {

  public static void main(String[] args) {
    BinarySearchTree binarySearchTree = new BinarySearchTree();
    binarySearchTree.rInsert(10);
    binarySearchTree.rInsert(11);
    binarySearchTree.rInsert(11512);
    binarySearchTree.rInsert(65);
    System.out.println(binarySearchTree.root.value);
  }

}
