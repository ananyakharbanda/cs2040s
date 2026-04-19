import java.util.List;
import java.util.ArrayList;

class LIS {
    
    public static int calc(ArrayList<Integer> arr) {
        int size = arr.size();
        ArrayList<Integer> solution = new ArrayList<>(size);

        for (int x = 0; x < size; x++) {
            solution.add(-1);
        } 
        
        int max = 0; 
        for (int i = 0; i < size; i++) {
            max = Math.max(max, calcHelper(arr, solution, i));
        } 
        
        return max;
    }
    
    private static int calcHelper(ArrayList<Integer> arr, ArrayList<Integer> solution, int i) {
        if (i == 0) {
            return 1;
        }
            
        if (solution.get(i) != -1) {
            return solution.get(i);
        }

        int val = 1;

        for (int j = i - 1; j >= 0; j--) {
            if (arr.get(j) < arr.get(i)) {
                val = Math.max(calcHelper(arr, solution, j) + 1, val);
            }
        }
        solution.set(i, val);
 
        return val;
    }

    public static void main(String[] args) {
        
        List<ArrayList<Integer>> tests = List.of(
            new ArrayList<>(List.of(8, 9, 1, 2, 6)),           // 3
            new ArrayList<>(List.of(1, 2, 3, 4, 5)),           // 5
            new ArrayList<>(List.of(5, 4, 3, 2, 1)),           // 1
            new ArrayList<>(List.of(7, 7, 7, 7)),              // 1
            new ArrayList<>(List.of(10, 9, 2, 5, 3, 7, 101, 18)), // 4
            new ArrayList<>(List.of(0, 1, 0, 3, 2, 3)),        // 4
            new ArrayList<>(List.of(-1, 3, 4, 5, 2, 2, 2, 2)), // 4
            new ArrayList<>(List.of(42)),                      // 1
            new ArrayList<>(List.of()),                        // 0
            new ArrayList<>(List.of(1, 3, 2, 4, 3, 5)),        // 4
            new ArrayList<>(List.of(3, 4, -1, 0, 6, 2, 3))     // 4
        );

        int[] expected = {
            3, 5, 1, 1, 4, 4, 4, 1, 0, 4, 4
        };

        for (int i = 0; i < tests.size(); i++) {
            ArrayList<Integer> arr = tests.get(i);
            int result = LIS.calc(arr); // your function

            System.out.println("Test " + (i + 1) + ": " + arr);
            System.out.println("Expected: " + expected[i] + ", Got: " + result);

            if (result == expected[i]) {
                System.out.println("✅ PASS\n");
            } else {
                System.out.println("❌ FAIL\n");
            }
        }
    }
}
