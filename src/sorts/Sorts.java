package sorts;

/*
 * This Java application provides implementations for numerous
 * comparison sort algorithms.
 *
 * @creator DeAndre Sellers
 * @created 02018.11.13
 * @deasells
 */

public class Sorts {
   private static int[][] arrays = { { 3, 4, 1, 7, 2, 5, 9, 12, 6, 15 },
                                     { 12, 6, 15, 9, 5, 2, 7, 1, 4, 3 },
                                     { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                                     { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 }, };
   private static int[] copy;
   private static int[] a;
   private static int ncompares;
   private static int nswaps;

   public static void main(String[] argv) {
      for (int i = 0; i < arrays.length; i++)
         justDoIt(i);
   }

   private static void justDoIt(int idx) {
      a = arrays[idx];
      A("ShellShort()", true); ShellSort(); A("ShellShort()", false);
      A("bubbleSort()", true); bubbleSort(); A("bubbleSort()", false);
      A("SelectionSort()", true); SelectionSort(); A("SelectionSort()", false);
      A("ExchangeSort()", true); ExchangeSort(); A("ExchangeSort()", false);
      A("QuickSort()", true); QuickSort(0, a.length-1); A("QuickSort()", false);
   }

   private static void A(String sort, boolean begin) {
      if (begin) {
         copy = new int[a.length];
         System.arraycopy(a, 0, copy, 0, a.length);
         System.out.println("beginning " + sort + "...");
         ncompares = nswaps = 0;
         A();
      } else {
         System.out.println("ending " + sort + "...");
         System.out.println("*** #compares: "+ncompares+"; #swaps: "+nswaps);
         A();
         System.arraycopy(copy, 0, a, 0, copy.length);
      }
   }

   private static void A() {
      for (int i = 0; i < a.length; i++)
         System.out.print(a[i] + "  ");
      System.out.println();
   }
   private static boolean compare(int i1, int i2) {
      ncompares++;
      return a[i1] > a[i2];
   }

   private static void swap(int i1, int i2) {
      int tmp = a[i1];
      a[i1] = a[i2];
      a[i2] = tmp;
      nswaps++;
   }

   private static void P(int i1, int i2) {
      System.out.print("\ta[" + i1 + "]=" + a[i1] + "; a[" + 
                        i2 + "]=" + a[i2]);
   }
   
   /* 
    * foo() was renamed ShellSort because it uses gaps and ShellSort is also
    * referred to as the diminishing gap sort. It is also given away by it
    * comparing far-apart elements in the beginning and the interval between
    * compared elements is gradually decreased to one. 
    */
   private static void ShellSort() {
      int gap, i, j, n = a.length;
      for (gap = n/2; gap > 0; gap /= 2) {
         for (i = gap; i < n; i++) {
            for (j = i-gap; j >= 0; j -= gap) {
               P(j, j+gap);
               if (!compare(j, j+gap)) {
                  System.out.println();
                  break;
               }
               swap(j, j+gap);
               System.out.println(" [swapped]");
            }
         }
      }
   }
   
   /* 
    * goo() was renamed bubbleSort() because it begins with elements 0 and 1
    * and it moves over one position to the right. It is also given away by
    * this array (10  9  8  7  6  5  4  3  2  1). This array is sorted in 
    * descending order and in bubble sort it had to make the most swaps.
    */
   private static void bubbleSort() {
      int end_i = a.length-1;
      while (end_i > 0) {
         int last_i = 0;
         for (int next_i = 0; next_i < end_i; next_i++) {
            P(next_i, next_i+1);
            if (compare(next_i, next_i+1)) {
               swap(next_i, next_i+1);
               System.out.println(" [swapped]");
               last_i = next_i;
            } else
               System.out.println();
         }
         end_i = last_i;
      }
   }

   /* 
    * moo() was renamed SelectionSort because if you look at the output of each
    * time the method is called it only chooses one element on each pass to be
    * correctly positioned. It's given away by using the variables named cur_i,
    * small_i and next_i. If we look at the code you see that we are scanning 
    * through the array and comparing to find the smallest element. The last 
    * if statement is our swapping of the smallest item with the item at index 
    * 0 or index up to N-1.
    */
   private static void SelectionSort() {
      int cur_i, limit, small_i, next_i, n = a.length;
      for (cur_i = 0, limit = n-1; cur_i < limit; cur_i++) {
         small_i = cur_i;
         for (next_i = cur_i+1; next_i < n; next_i++) {
            P(small_i, next_i);
            System.out.println();
            if (compare(small_i, next_i))
               small_i = next_i;
         }
         if (small_i != cur_i) {
            swap(small_i, cur_i);
            System.out.println(" [swapped]");
         }
      }
   }

   /* 
    * boo() was renamed ExchangeSort because it starts with the item at index 0
    * and is compared with each subsequent item in the last at index 1, 2, ...,
    * N-1. Also, when the number at the subsequent item is smaller than the 
    * element at index 0, they are swapped. This sort was mostly given away by
    * it starting with the item at index and comparing it to each subsequent
    * item.
    */
   private static void ExchangeSort() {
      int cur_i, limit, next_i, n = a.length;
      for (cur_i = 0, limit = n-1; cur_i < limit; cur_i++) {
         for (next_i = cur_i+1; next_i < n; next_i++) {
            P(cur_i, next_i);
            if (compare(cur_i, next_i)) {
               swap(cur_i, next_i);
               System.out.println(" [swapped]");
            } else
               System.out.println();
         }
      }
   }

   /*
    * poo() was renamed QuickSort because we have a check in the beginning when
    * left is greater or equal than the right we do nothing. Also, QuickSort is
    * recursive and this is shown at the bottom when it calls itself. The line
    * with "swap(left, (left + right) / 2);" is showing us choosing the pivot,
    * the pivot should never be the first or last element but the middle is a 
    * reasonable point. The use of left and right hints to a disjoint of the
    * elements and this is one of the key features to QuickSort. 
    * 
    * poo() logic copied from "The C Programming Language"
    * by Kernighan and Ritchie.
    */
   private static void QuickSort(int left, int right) {
      int i, last;
      if (left >= right) return;
      swap(left, (left + right) / 2);
      last = left;
      for (i = left+1; i <= right; i++) {
         P(i, left);
         if (compare(i, left)) {
            if ((last+1) == i) 
               ++last;
            else {
               swap(++last, i);
               System.out.print(" [swapped]");
            }
         }
         System.out.println();
      }
      if (left != last)
         swap(left, last);
      QuickSort(left, last-1);
      QuickSort(last+1, right);
   }
}           