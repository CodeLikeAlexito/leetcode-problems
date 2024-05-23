import java.util.ArrayList;
import java.util.List;

public class BinaryTreeTraversal {

  static List<Integer> preorderList = new ArrayList<>();
  static List<Integer> leafNodes = new ArrayList<>();

  public static List<Integer> returnLeafNodesPreorderTraversal(BinaryTree.Node root) {
    if(root != null) {
      preorderList.add(root.value);
      if(root.left == null && root.right == null) {
        leafNodes.add(root.value);
      }
      returnLeafNodesPreorderTraversal(root.left);
      returnLeafNodesPreorderTraversal(root.right);
    }

    return leafNodes;
  }

}
