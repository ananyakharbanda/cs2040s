class UnboundedKnapsack {
    public static int[][] sol = new int[1000][1000];

    public static int solve(int[][] ks, int n, int C) {
        if (n == 0 || C == 0) {
            return 0;
        } else if (sol[n][C] != -1) {
            return sol[n][C];
        } else if (C < ks[n-1][0]) {
            sol[n][C] = solve(ks, n-1, C);
        } else {
            sol[n][C] = Math.max(solve(ks, n, C-ks[n-1][0]) + ks[n-1][1], solve(ks, n-1, C));
        }
        return sol[n][C];
    }
    
    public static void main(String[] args) {
        // KnapsackPair ks1 = new KnapsackPair(2, 3);
        // KnapsackPair ks2 = new KnapsackPair(3, 4);
        // KnapsackPair ks3 = new KnapsackPair(4, 5);
        // KnapsackPair ks4 = new KnapsackPair(5, 8);
        // KnapsackPair ks5 = new KnapsackPair(9, 10);
        
        int[][] ks = {{1, 10}, {2, 15}, {3, 40}}; 
        
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                sol[i][j] = -1;
            }
        }
        // System.out.println(Knapsack.solve(ks, 5, 10));
        System.out.println(UnboundedKnapsack.solve(ks, 3, 6));
    }
}   
       
