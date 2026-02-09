public class InsertionSort {

    // Swap-based insertion sort. More intuitive but performs extra writes.

    public static int[] insertionSort1(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 1 && arr[j - 1] > arr[j]; j--) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
            }
        }
        return arr;
    }

    // Shift-based insertion sort (standard textbook version).  More efficient because it shifts instead of swapping repeatedly.

    public static int[] insertionSort2(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
        return arr;
    }

    // Recursive insertion sort

    public static int[] insertionSort3(int[] arr, int n) {
        if (n <= 1) {
            return arr;
        }

        insertionSort3(arr, n - 1);

        int key = arr[n - 1];
        int j = n - 2;

        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }

        arr[j + 1] = key;
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {90, 4, 23, 56, 52, 47, 88, 22, 11, 12};

        System.out.println("Original:");
        printArray(arr.clone());

        System.out.println("\nSwap-based:");
        printArray(insertionSort1(arr.clone(), arr.length));

        System.out.println("\nShift-based:");
        printArray(insertionSort2(arr.clone(), arr.length));

        System.out.println("\nRecursive:");
        printArray(insertionSort3(arr.clone(), arr.length));
    }
}
