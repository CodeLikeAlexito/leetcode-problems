import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

public class Application {

  public static void main(String[] args) {
//    System.out.println(findDuplicates(new int[] {1, 2, 3, 2, 1, 4, 5, 4}));
//    System.out.println(firstNonRepeatingChar("leetcode"));
    String originalInput = "7540510f-1065-4504-a953-da4d01427a9e:1fd39098-f83e-4de4-9702-930fb6961ea4";
    String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
    System.out.println(encodedString);
  }

  public static Character firstNonRepeatingChar(String str) {
    Map<Character, Integer> hashMap = new LinkedHashMap<>();
    for (char ch : str.toCharArray()) {
      if(hashMap.containsKey(ch)) {
        hashMap.put(ch, hashMap.get(ch) + 1);
      } else {
        hashMap.put(ch, 1);
      }
    }

    for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
      if(entry.getValue() == 1) {
        return entry.getKey();
      }
    }

    return null;
  }

  public static List<Integer> findDuplicates(int[] nums) {
    List<Integer> duplicates = new ArrayList<>();
    HashMap<Integer, Boolean> hashMap = new HashMap<>();

    for (int num : nums) {
      if(hashMap.containsKey(num) && !duplicates.contains(num)) {
        duplicates.add(num);
      } else {
        hashMap.put(num, true);
      }
    }

    return duplicates;
  }

  public static boolean itemInCommon(int[] array1, int[] array2) {
    HashMap<Integer, Boolean> hashMap = new HashMap<>();
    for (int num : array1) {
      hashMap.put(num, true);
    }

    for (int nmbr : array2) {
      if(hashMap.get(nmbr) != null) return true;
    }

    return false;
  }

//  public static String reverseVowels(String s) {
//    StringBuilder builder = new StringBuilder(s.length());
//    for(int i = 0; i < builder.length(); i++) {
//
//    }
//  }

  public static void decodeBase64EncodedString() {
    // Sample Base64 encoded string
    String base64EncodedString = "ODkxZjZiMTItOThkZS00Yzc3LWJiMzEtYmQ1NDQzMTA2MmM0OmQyYTBkNTQ5LTRmOTUtNDJhMi1iMGQ2LWM2NWVmMTBmYjNmOQ==";

    // Decoding the Base64 encoded string
    byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedString);

    // Converting the decoded bytes to string
    String decodedString = new String(decodedBytes);

    // Printing the decoded string
    System.out.println("Decoded string: " + decodedString);
  }

  public static boolean canPlaceFlowers(int[] flowerbed, int n) {
    int count = 0;
    int length = flowerbed.length;

    for (int i = 0; i < length; i++) {
      if (flowerbed[i] == 0 &&
          (i == 0 || flowerbed[i - 1] == 0) &&  // Check left adjacent plot
          (i == length - 1 || flowerbed[i + 1] == 0)) { // Check right adjacent plot
        flowerbed[i] = 1; // Plant flower
        count++; // Increment count of planted flowers
      }
    }

    return count >= n;
  }

  public static int largestAltitude(int[] gain) {
    List<Integer> altitudes = new ArrayList<>();
    altitudes.add(0);

    for (int g : gain) {
      int altitude = altitudes.get(altitudes.size() - 1) + g;
      altitudes.add(altitude);
    }

    return altitudes.stream()
        .mapToInt(Integer::intValue)
        .max()
        .getAsInt();
  }

  public static int pivotIndex(int[] nums) {
    if (nums.length == 0) {
      return -1; // Handle empty array case
    }

    int totalSum = Arrays.stream(nums).sum();
    int leftSum = 0;

    for (int i = 0; i < nums.length; i++) {
      if (leftSum == totalSum - leftSum - nums[i]) {
        return i;
      }
      leftSum += nums[i];
    }

    return -1; // If no pivot index found
  }

  public static void moveZeroes(int[] nums) {
    // 0, 1, 0, 3, 12 -> 1, 3, 12, 0, 0

    int left = 0; // Pointer to keep track of non-zero elements
    int right = 0; // Pointer to traverse the array

    // Traverse the array
    while (right < nums.length) {
      // If the element at right pointer is non-zero
      if (nums[right] != 0) {
        // Swap non-zero element with the left pointer
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;

        // Move left pointer forward
        left++;
      }
      // Move right pointer forward
      right++;
    }

    for (int num : nums) {
      System.out.printf("%d, ", num);
    }

  }

  public static boolean leafSimilar(BinaryTree.Node root1, BinaryTree.Node root2) {
    List<Integer> root1LeafNodes = BinaryTreeTraversal.returnLeafNodesPreorderTraversal(root1);
    List<Integer> root2LeafNodes = BinaryTreeTraversal.returnLeafNodesPreorderTraversal(root1);

    return root1LeafNodes.equals(root2LeafNodes);
  }

  public static int[] twoSum2(int[] nums, int target) {
    int[] indices = new int[2];

    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          System.out.println("Pair found: (" + nums[i] + ", " + nums[j] + ")");
          indices[0] = i;
          indices[1] = j;
        }
      }
    }

    return indices;
  }

  public static int findPeakElement(int[] nums) {
    int left = 0;
    int right = nums.length - 1;

    while(left <= right) {
      int midIndex = (left + right) / 2;
      int midNumber = nums[midIndex];

      if((midIndex == 0 || midNumber > nums[midIndex - 1]) &&
        (midIndex == nums.length - 1 || midNumber > nums[midIndex + 1])) {
        return midIndex;
      } else if (midNumber < nums[midIndex - 1]) {
        right = midIndex - 1;
      } else {
        left = midIndex + 1;
      }
    }

// If the loop terminates without finding a peak, it means there's no peak in the array.
// Return any valid index to indicate that no peak was found.
    return -1;

  }

  public static int[] successfulPairsBS(int[] spells, int[] potions, long success) {
    Arrays.sort(spells);
    Arrays.sort(potions);
    int[] pairs = new int[spells.length];

    for(int i = 0; i < spells.length; i++) {
      int low = 0;
      int high = potions.length - 1;

      while (low <= high) {
        int midIndex = (low + high) / 2;
        int possibleResult = spells[i] * potions[midIndex];

        if(possibleResult >= success) {
          pairs[i] = high - low + 1;
          high = midIndex - 1;
        } else {
          low = midIndex + 1;
        }
      }

    }

    return pairs;

  }

  public static int[] successfulPairs(int[] spells, int[] potions, long success) {
    // spells[i] * potions[i] >= success
    int[] pairs = new int[spells.length];
    for(int i = 0; i < spells.length; i++) {
      for (int j = 0; j < potions.length; j++) {
        if(spells[i] * potions[j] >= success) {
          pairs[i]++;
        }
      }
    }

    return pairs;
  }

  // {1, 3, 4, 7, 9, 12, 15}
  public static int binarySearch(int[] array, int numberToFind) {
    int low = 0;
    int high = array.length - 1;

    while(low <= high) {
      int midIndex = (low + high) / 2;
      int midNumber = array[midIndex];

      if(midNumber == numberToFind) {
        return midNumber;
      }

      if(numberToFind < midNumber) {
        high = midIndex - 1;
      } else {
        low = midIndex + 1;
      }
    }

    return -1;
  }

  public static int guessNumber(int n) {
    int low = 1;
    int high = n;

    while(low <= high) {
      int midIndex = (low + high) / 2;
      int result = 5; // guess(midIndex)

      if(result == 0) {
        return midIndex;
      } else if (result == -1) {
        high = midIndex - 1;
      } else {
        low = midIndex + 1;
      }
    }

    return -1;
  }

  public static ListNode deleteMiddle(ListNode head) {
    ListNode current = head;
    ListNode slow = current;
    ListNode fast = current;
    ListNode previous = null;

    if(head == null || head.next == null) {
      return null;
    }

    while(fast != null && fast.next != null) {
      previous = slow;
      slow = slow.next;
      fast = fast.next.next;
    }

    previous.next = slow.next;


    return head;
  }

  public ListNode reverseList(ListNode head) {
    ListNode current = head;
    ListNode previous = null;
    ListNode next;

    while(current != null) {
      next = current.next;
      current.next = previous;
      previous = current;
      current = next;
    }

    head = previous;

    return head;
  }

  public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
    List<Integer> firstArrList = new ArrayList<>();
    List<Integer> secondArrList = new ArrayList<>();
    Set<Integer> firstSet = new HashSet<>();
    Set<Integer> secondSet = new HashSet<>();

    // 1, 2 ,3 : 2, 4, 6

    for (int num : nums1) {
      firstSet.add(num);
    }

    for (int num : nums2) {
      secondSet.add(num);
    }


    for (Integer element : firstSet) {
      if(!secondSet.contains(element)) {
        firstArrList.add(element);
      }
    }

    for (Integer el : secondSet) {
      if(!firstSet.contains(el)) {
        secondArrList.add(el);
      }
    }

    List<List<Integer>> res = new ArrayList<>();
    res.add(firstArrList);
    res.add(secondArrList);

    return res;

  }

  public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
    List<Boolean> result = new ArrayList<>(candies.length);

    int maxCandy = Integer.MIN_VALUE;
    for (int candy : candies) {
      if (candy > maxCandy) {
        maxCandy = candy;
      }
    }

    for (int candy : candies) {
      if (candy + extraCandies >= maxCandy) {
        result.add(true);
      } else {
        result.add(false);
      }
    }

    return result;
  }

  public static String gcdOfStrings(String str1, String str2) {

    str1.contains(str2);

    return null;
  }

  public static String mergeAlternately(String word1, String word2) {
    StringBuilder result = new StringBuilder();

    String appendAtEnd = word1.length() > word2.length() ?
      word1.substring(word2.length()) : word2.substring(word1.length());

    int lengthOfStr = Math.min(word1.length(), word2.length());

    for (int i = 0; i < lengthOfStr; i++) {
      result.append(word1.toCharArray()[i]);
      result.append(word2.toCharArray()[i]);
    }

    return result.append(appendAtEnd).toString();
  }

  public static List<String> summaryRanges(int[] nums) {
    // [0, 1, 2, 4, 5, 7] -> ["0->2","4->5","7"]
    List<String> list = new ArrayList<>();
    if (nums.length == 0) return list; // Handling empty array case
    int pivot = nums[0];
    int start = nums[0];
    for (int i = 1; i < nums.length; i++) {
      int difference = nums[i] - pivot;
      if (difference > 1 || i == nums.length - 1) {
        if (start == pivot) {
          list.add(Integer.toString(start));
        } else {
          String str = String.format("%d->%d", start, pivot);
          list.add(str);
        }
        start = nums[i];
      }
      pivot = nums[i];
    }
    if (start == pivot) {
      list.add(Integer.toString(start));
    } else {
      String str = String.format("%d->%d", start, pivot);
      list.add(str);
    }
    return list;
  }

  public static boolean isSubsequence(String s, String t) {
    int sPointer = 0;
    int tPointer = 0;

    while (sPointer < s.length() && tPointer < t.length()) {
      if (s.charAt(sPointer) == t.charAt(tPointer)) {
        sPointer++;
      }
      tPointer++;
    }

    return sPointer == s.length();
  }

  public static boolean isAnagram(String s, String t) {
    Map<Character, Integer> sMap = new HashMap<>();

    for (char ch : s.toCharArray()) {
      if(sMap.containsKey(ch)) {
        sMap.put(ch, sMap.get(ch) + 1);
      } else {
        sMap.put(ch, 1);
      }
    }

    Map<Character, Integer> tMap = new HashMap<>();
    for (char ch : t.toCharArray()) {
      if(tMap.containsKey(ch)) {
        tMap.put(ch, tMap.get(ch) + 1);
      } else {
        tMap.put(ch, 1);
      }
    }

    if(sMap.size() != tMap.size()) {
      return false;
    }

    for (Character key : sMap.keySet()) {
      if (!tMap.containsKey(key)) {
        return false;
      }
      if (!sMap.get(key).equals(tMap.get(key))) {
        return false;
      }
    }

    return true;
  }

  public static boolean isPalindrome(String s) {
    String result = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    StringBuilder stringBuilder = new StringBuilder(result);
    String reversed = stringBuilder.reverse().toString();

    return reversed.equals(result);
  }

  public static boolean wordPattern(String pattern, String s) {
    String[] words = s.split(" ");

    if(pattern.length() != words.length) {
      return false;
    }

    Map<Character, String> charToWord = new HashMap<>();
    Map<String, Character> wordToChar = new HashMap<>();

    for (int i = 0; i < pattern.length(); i++) {
      char ch = pattern.charAt(i);
      String word = words[i];

      if (!charToWord.containsKey(ch)) {
        charToWord.put(ch, word);  // Map character to word
      } else {
        if (!charToWord.get(ch).equals(word)) {
          return false;  // Mismatch in mapping, return false
        }
      }

      if (!wordToChar.containsKey(word)) {
        wordToChar.put(word, ch);  // Map word to character
      } else {
        if (wordToChar.get(word) != ch) {
          return false;  // Mismatch in mapping, return false
        }
      }
    }

    return true;

  }

  public static String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }

    String prefix = strs[0]; // Start with the first string as potential common prefix

    for (int i = 1; i < strs.length; i++) {
      while (strs[i].indexOf(prefix) != 0) { // Check if prefix is not a prefix of current string
        prefix = prefix.substring(0, prefix.length() - 1); // Reduce prefix length by 1 character
        if (prefix.isEmpty()) {
          return ""; // If prefix becomes empty, no common prefix exists
        }
      }
    }

    return prefix;
  }

  public static List<List<String>> generateSubsets(String[] arr) {
    List<List<String>> subsets = new ArrayList<>();
    List<String> subset = new ArrayList<>();

    generateSubsetsHelper(arr, 0, subset, subsets);

    return subsets;
  }

  private static void generateSubsetsHelper(String[] arr, int index, List<String> subset, List<List<String>> subsets) {
    if (index == arr.length) {
      subsets.add(new ArrayList<>(subset));
      return;
    }

    subset.add(arr[index]);
    generateSubsetsHelper(arr, index + 1, subset, subsets);
    subset.remove(subset.size() - 1);

    generateSubsetsHelper(arr, index + 1, subset, subsets);
  }

  public static int strStr(String haystack, String needle) {
    return haystack.contains(needle) ? haystack.indexOf(needle) : -1;
  }

  public static boolean containsNearbyDuplicate(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(nums[i])) {
        int prevIndex = map.get(nums[i]);
        if (i - prevIndex <= k) {
          return true;
        }
      }
      map.put(nums[i], i);
    }

    return false;
  }

  public static boolean isIsomorphic(String s, String t) {

    if (s.length() != t.length()) {
      return false;
    }

    Map<Character, Character> mapping = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      char charS = s.charAt(i);
      char charT = t.charAt(i);

      if (mapping.containsKey(charS)) {
        if (mapping.get(charS) != charT) {
          return false; // Mapping conflicts with previous mappings
        }
      } else {
        if (mapping.containsValue(charT)) {
          return false; // One-to-one mapping violated
        }
        mapping.put(charS, charT);
      }
    }

    return true;
  }

  public static int[] plusOne1(int[] digits) {
    BigInteger number = BigInteger.ZERO;
    for (int j : digits) {
      number = number.multiply(BigInteger.TEN).add(BigInteger.valueOf(j));
    }
    number = number.add(BigInteger.ONE);

    int newLength = number.toString().length();

    int[] result = new int[newLength];

    int i = newLength;
    while (!number.equals(BigInteger.ZERO)) {
      BigInteger[] divRem = number.divideAndRemainder(BigInteger.TEN);
      int digit = divRem[1].intValue();
      result[--i] = digit;
      number = divRem[0];
    }

    return result;
  }

  public static int[] plusOne(int[] digits) {
    int number = 0;
    for (int j : digits) {
      number = number * 10 + j;
    }
    number += 1;

    int newLength = digits[digits.length - 1] == 9 ? digits.length + 1 : digits.length;
    int[] result = new int[newLength];

    int i = newLength;
    while (number > 0) {
      int digit = number % 10;
      result[--i] = digit;
      number /= 10;
    }

    return result;
  }

  public static boolean isPalindrome(int x) {
    int originalNumber = x;
    int reversedNumber = 0;

    while (x > 0) {
      int digit = x % 10;
      reversedNumber = reversedNumber * 10 + digit;
      x /= 10;
    }

    return reversedNumber == originalNumber;
  }

  public static boolean isHappy(int n) {

    Set<Integer> seen = new HashSet<>();

    while (n != 1 && !seen.contains(n)) {
      seen.add(n);

      int sum = 0;
      while (n > 0) {
        int digit = n % 10;
        sum += digit * digit;
        n /= 10;
      }
      n = sum;
    }

    return n == 1;
  }

  public static boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    char[] charArr = s.toCharArray();

    Map<Character, Character> mappings = new HashMap<>();
    mappings.put(')', '(');
    mappings.put(']', '[');
    mappings.put('}', '{');


    for (char c : charArr) {
      if(c == '(' || c == '[' || c == '{') {
        stack.push(c);
      }

      if(c == ')' || c == ']' || c == '}') {
        if(stack.isEmpty()) {
          return false;
        }
        if(stack.pop() != mappings.get(c)) {
          return false;
        }
      }
    }

    return stack.isEmpty();
  }

  public static int[] twoSum1(int[] nums, int target) {
    int[] indices = new int[2];
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];
      if(map.containsKey(complement)) {
        indices[0] = map.get(complement);
        indices[1] = i;
      }
      map.put(nums[i], i);
    }

    return indices;
  }

  public static int[] twoSum(int[] nums, int target) {
    int[] indices = new int[2];

    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          System.out.println("Pair found: (" + nums[i] + ", " + nums[j] + ")");
          indices[0] = i;
          indices[1] = j;
        }
      }
    }

    return indices;
  }

  /*
  for (Map.Entry<Character, Integer> entry : ransomMap.entrySet()) {
      Character key = entry.getKey();
      Integer value = entry.getValue();
      System.out.println(key + ": " + value);
    }

    System.out.println("---------------------------------");
   */

  public static boolean canConstruct(String ransomNote, String magazine) {
    HashMap<Character, Integer> magazineMap = new HashMap<>();
    char[] ransomMagazineStr = magazine.toCharArray();

    for (int i = 0; i < ransomMagazineStr.length; i++) {
      if(magazineMap.containsKey(ransomMagazineStr[i])) {
        magazineMap.put(ransomMagazineStr[i], magazineMap.get(ransomMagazineStr[i]) + 1);
      } else {
        magazineMap.put(ransomMagazineStr[i], 1);
      }
    }

    char[] ransomeCharStr = ransomNote.toCharArray();

    for (int i = 0; i < ransomeCharStr.length; i++) {
      if(magazineMap.containsKey(ransomeCharStr[i])) {
        int occurences = magazineMap.get(ransomeCharStr[i]);
        if(occurences > 0) {
          magazineMap.put(ransomeCharStr[i], occurences - 1);
        } else {
          return false;
        }
      } else {
        return false;
      }
    }

    return true;
  }

  public static int romanToInt(String s) {
    Map<Character, Integer> romanValues = new HashMap<>();
    romanValues.put('I', 1);
    romanValues.put('V', 5);
    romanValues.put('X', 10);
    romanValues.put('L', 50);
    romanValues.put('C', 100);
    romanValues.put('D', 500);
    romanValues.put('M', 1000);

    int result = 0;
    int prevValue = 0;

    for (int i = s.length() - 1; i >= 0; i--) {
      int currentValue = romanValues.get(s.charAt(i));
      if (currentValue < prevValue) {
        result -= currentValue;
      } else {
        result += currentValue;
      }
      prevValue = currentValue;
    }

    return result;
  }

  public int lengthOfLastWord(String s) {
    String reversedString = new StringBuilder(s).reverse().toString();
    String[] wordsReversedString = reversedString.trim().split("\\s+");
    return wordsReversedString[0].length();
  }


  public boolean hasPathSum(TreeNode root, int targetSum) {
    if(root == null) {
      return false;
    }

    if (root.left == null && root.right == null && targetSum - root.val == 0) {
      return true;
    }

    return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
  }

  public boolean isSymmetric(TreeNode root) {
    return isMirror(root, root);
  }

  private boolean isMirror(TreeNode t1, TreeNode t2) {
    if (t1 == null && t2 == null) {
      return true;
    }
    if (t1 == null || t2 == null) {
      return false;
    }
    return (t1.val == t2.val) && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
  }
  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }

    TreeNode temp = root.left;
    root.left = invertTree(root.right);
    root.right = invertTree(temp);

    return root;
  }


  public static boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }
    if (p == null || q == null) {
      return false;
    }
    if (p.val != q.val) {
      return false;
    }
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }

  public static int maxDepth(TreeNode root) {
    if(root == null) {
      return 0;
    }

    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);

    return Math.max(leftDepth, rightDepth) + 1;
  }

  public static int maxProfit(int[] prices) {
    int maxProfit = 0;
    int buyPrice = Integer.MAX_VALUE;

    for (int i = 0; i < prices.length; i++) {
      if (prices[i] < buyPrice) {
        buyPrice = prices[i]; // Update buy price if we find a lower price
      } else if (prices[i] - buyPrice > maxProfit) {
        maxProfit = prices[i] - buyPrice; // Update max profit if selling at current price yields a higher profit
      }
    }

    return maxProfit;

  }

  public static int majorityElement(int[] nums) {
    // majority element appears more than n/2 where n is nums lenght
    int n = nums.length;
    int appearance = n/2;

    HashMap<Integer, Integer> hashMap = new HashMap<>();
    for (int i = 0; i < n; i++) {
      if(hashMap.containsKey(nums[i])){
        hashMap.put(nums[i], hashMap.get(i) + 1);
      } else {
        hashMap.put(nums[i], 1);
      }
    }

    for (int num : hashMap.keySet()) {
      if(num > appearance) {
        return num;
      }
    }

    return 0;
  }

  public static int removeElement(int[] nums, int val) {
    int k = 0; // Initialize a pointer to keep track of the position to place elements not equal to val

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != val) {
        nums[k] = nums[i]; // Place the element at position k if it's not equal to val
        k++; // Move the pointer to the next position
      }
    }

    return k; // k now represents the count of elements not equal to val
  }

  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    // Create a new array to store the merged result
    int[] merged = new int[m + n];

    // Pointers for nums1 and nums2
    int index1 = 0, index2 = 0;

    // Pointer for the merged array
    int mergeIndex = 0;

    // Merge arrays while both have elements
    while (index1 < m && index2 < n) {
      if (nums1[index1] < nums2[index2]) {
        merged[mergeIndex++] = nums1[index1++];
      } else {
        merged[mergeIndex++] = nums2[index2++];
      }
    }

    System.out.println("Merged array 1: " + Arrays.toString(merged));
    System.out.println("Index 1:" + index1);

    // Copy remaining elements from nums1
    while (index1 < m) {
      merged[mergeIndex++] = nums1[index1++];
    }

    System.out.println("Merged array 2: " + Arrays.toString(merged));
    System.out.println("Index 2: " + index2);
    System.out.println("Merged index : " + mergeIndex);

    // Copy remaining elements from nums2
    while (index2 < n) {
      merged[mergeIndex++] = nums2[index2++];
    }

    System.out.println("Merged array 3: " + Arrays.toString(merged));

    // Copy the merged array back to nums1
    for (int i = 0; i < m + n; i++) {
      nums1[i] = merged[i];
    }

    System.out.println("Merged array 4: " + Arrays.toString(merged));
  }

  public static void merge1(int[] nums1, int m, int[] nums2, int n) {
    int index1 = m - 1; // 2
    int index2 = n - 1; // 2
    int mergeIndex = m + n - 1; // 5

    while (index1 >= 0 && index2 >= 0) {
      if (nums1[index1] > nums2[index2]) {
        nums1[mergeIndex--] = nums1[index1--];
      } else {
        nums1[mergeIndex--] = nums2[index2--];
      }
    }

    // If there are remaining elements in nums2, copy them to nums1
    while (index2 >= 0) {
      nums1[mergeIndex--] = nums2[index2--];
    }
  }

  private static class Node {
    Node left, right;
    int data;

    Node(int newData) {
      left = right = null;
      data = newData;
    }
  }

  private static Node insert(Node node, int data) {
    if (node==null) {
      node = new Node(data);
    }
    else {
      if (data <= node.data) {
        node.left = insert(node.left, data);
      }
      else {
        node.right = insert(node.right, data);
      }
    }
    return(node);
  }

  public static int countDuplicate(List<Integer> numbers) {
    // Write your code here
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int number : numbers) {
      if (map.containsKey(number)) {
        map.put(number, map.get(number) + 1);
      } else {
        map.put(number, 1);
      }
    }

    int duplicates = 0;
    for (int count : map.values()) {
      if (count > 1) {
        duplicates++;
      }
    }

    return duplicates;
  }

  public static int priceCheck(List<String> products, List<Float> productPrices, List<String> productSold, List<Float> soldPrice) {
    int mistakes = 0;
    HashMap<String, Float> originalPrices = new HashMap<>();
    for (int i = 0; i < products.size(); i++) {
      String product = products.get(i);
      Float price = productPrices.get(i);
      originalPrices.put(product, price);
    }

    for (int i = 0; i < productSold.size(); i++) {
      String soldProduct = productSold.get(i);
      Float soldProductPrice = soldPrice.get(i);

      if (originalPrices.containsKey(soldProduct)) {
        Float originalPrice = originalPrices.get(soldProduct);

        if (!soldProductPrice.equals(originalPrice)) {
          mistakes++;
        }
      } else {
        mistakes++;
      }
    }


    return mistakes;

  }

  private static int isPresent(Node root, int val){
    while (!Objects.isNull(root)) {
      if (root.data == val) {
        return 1;
      } else if (val < root.data) {
        root = root.left;
      } else {
        root = root.right;
      }
    }
    return 0;
  }

  private static void printInOrder(Node node) {
    if (node != null) {
      printInOrder(node.left);
      System.out.print(node.data + " ");
      printInOrder(node.right);
    }
  }
}
