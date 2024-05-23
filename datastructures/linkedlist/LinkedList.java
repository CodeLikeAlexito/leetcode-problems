package datastructures.linkedlist;

public class LinkedList {

  private Node head;
  private Node tail;
  private int length;

  class Node {

    private int value;
    private Node next;

    public Node(int value) {
      this.value = value;
    }
  }

  public LinkedList(int value) {
    Node newNode = new Node(value);
    head = newNode;
    tail = newNode;
    length = 1;
  }

  public void printList() {
    Node temp = head;
    while (temp != null) {
      System.out.println(temp.value);
      temp = temp.next;
    }
  }

  public void getHead() {
    if (head == null) {
      System.out.println("Head: null");
    } else {
      System.out.println("Head: " + head.value);
    }
  }

  public void getTail() {
    if (head == null) {
      System.out.println("Tail: null");
    } else {
      System.out.println("Tail: " + tail.value);
    }
  }

  public void getLength() {
    System.out.println("Length: " + length);
  }

  public void append(int value) {
    Node nextNode = new Node(value);
    if (length == 0) {
      head = nextNode;
      tail = nextNode;
    } else {
      tail.next = nextNode;
      tail = nextNode;
    }
    length++;
  }

  public Node removeLast() {
    Node temp = head;
    Node pre = head;
    if (length == 0) {
      System.out.println("List is empty, no elements to remove");
      return null;
    }

    while (temp.next != null) {
      pre = temp;
      temp = temp.next;
    }
    tail = pre;
    tail.next = null;
    length--;

    if (length == 0) {
      head = null;
      tail = null;
    }

    return temp;
  }

  public void prepend(int value) {
    // Add at the beginning of the linked list

    Node newNode = new Node(value);

    if (length == 0) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.next = head;
      head = newNode;
    }

    length++;

  }

  public Node removeFirst() {

    if (length == 0) {
      return null;
    }

    Node temp = head;
    head = head.next;
    temp.next = null;

    length--;

    // If we have only 1 node
    if (length == 0) {
      tail = null;
    }

    return temp;
  }

  public Node get(int index) {

    if (index < 0 || index >= length) {
      return null;
    }

    Node temp = head;
    for (int i = 0; i < index; i++) {
      temp = temp.next;
    }

    return temp;
  }

  public boolean set(int index, int value) {
    Node temp = this.get(index);

    if (temp != null) {
      temp.value = value;
      return true;
    }

    return false;
  }

  public boolean insert(int index, int value) {
    if (index < 0 || index > length)
      return false;

    if (index == 0) {
      prepend(value);
      return true;
    }

    if (index == length) {
      append(value);
      return true;
    }

    Node newNode = new Node(value);
    Node temp = this.get(index - 1);
    newNode.next = temp.next;
    temp.next = newNode;
    length++;

    return true;
  }

  public Node remove(int index) {
    if (index < 0 || index >= length)
      return null;
    if (index == 0)
      return this.removeFirst();
    if (index == length - 1)
      return this.removeLast();

    Node prev = this.get(index - 1);
    Node temp = prev.next;
    prev.next = temp.next;
    temp.next = null;
    length--;

    return temp;

  }

  public void reverse() {
    Node temp = head;
    head = tail;
    tail = temp;

    Node after = temp.next;
    Node before = null;

    for (int i = 0; i < length; i++) {
      after = temp.next;
      temp.next = before;
      before = temp;
      temp = after;
    }
  }

  public void makeEmpty() {
    head = null;
    tail = null;
  }

  public Node findMiddleNode() {
    // if length is odd number return middle node
    // if length is even number return return the second middle node

    int len = 0;
    Node temp = head;
    while (temp != null) {
      len++;
      temp = temp.next;
    }
    int midIndex = len / 2;

    if (midIndex < 0 || midIndex >= len) {
      return null;
    }

    Node tmp = head;
    for (int i = 0; i < midIndex; i++) {
      tmp = tmp.next;
    }

    return tmp;

  }

  public Node findMiddle() {
    Node slow = head;
    Node fast = head;

    while (fast.next.next != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  public boolean hasLoop() {
    if (head == null)
      return false;
    Node slow = head;
    Node fast = head;

    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (fast == slow) {
        return true;
      }
    }

    return false;
  }

  public Node findKthFromEnd(int k) {
    Node slow = head;
    Node fast = head;

    // Move the fast pointer k nodes ahead
    for (int i = 0; i < k; i++) {
      if (fast == null) // Edge case: list has fewer than k nodes
        return null;
      fast = fast.next;
    }

    // Move both pointers simultaneously until fast reaches the end
    while (fast != null) {
      slow = slow.next;
      fast = fast.next;
    }

    // At this point, slow points to the kth node from the end
    return slow;
  }
}
