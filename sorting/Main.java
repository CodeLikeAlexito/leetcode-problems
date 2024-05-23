package sorting;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
//    int[] array = {4, 2, 6, 5, 1, 3};
//    SortingAlgorithms.insertionSort(array);
//    System.out.println(Arrays.toString(array));
//    int[] array1 = {1, 3, 7, 8};
//    int[] array2 = {2, 4, 5, 6};

//    System.out.println(Arrays.toString(SortingAlgorithms.merge(array1, array2)));
    int[] originalArray = {3, 1, 4, 2};
    int[] sortedArray = SortingAlgorithms.mergeSort(originalArray);

    System.out.println(Arrays.toString(originalArray));
    System.out.println(Arrays.toString(sortedArray));
  }
}
