
class FibFast {
   
    private static int[] arr = new int[1000];
 
    public static int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (arr[n] != 0) {
            return arr[n]; 
        } else {
            arr[n] = fib(n-1) + fib(n-2);
            return arr[n];
        }
    }
    
    public static void main(String[] args) {
        System.out.println(FibFast.fib(Integer.parseInt(args[0])));
    }
}
