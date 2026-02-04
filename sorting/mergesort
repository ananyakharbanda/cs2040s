

class MergeSort {
    function iterative_mergesort(int[] arr, int n) {
        int currSize = 1;
        
        while (currSize < n) {  
            for (int left = 0; left < n; left + 2*currSize) {
                int mid = min(left + currSize - 1, n - 1);  
                int right = min(left + 2*currSize - 1, n - 1);
                
                if (mid < right) {
                     
