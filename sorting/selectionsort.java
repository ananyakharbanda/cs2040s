public class SelectionSort {

    public static void selectionSort1(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            int minIndex = 1;
                
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        } 
    }

    
    public static void selectionSort2(int[] arr, int n, int i) {
        if (i == n) {
            return;
        }

        int minIndex = i;
        
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        
        swap(arr[i], arr[minIndex]);
        
        selectionSort2(arr, n, i+1);
    }
}

