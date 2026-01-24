import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

class UFDS {
    private int[] parent;
    private int[] rank;
    private Map<Integer, Integer> mappings;

    public UFDS(int[] elements) {
        int size = elements.length;
        this.parent = new int[size];
        this.rank = new int[size];
        this.mappings = new HashMap<Integer, Integer>(); 
        
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            this.rank[i] = 1;
            this.mappings.put(elements[i], i);
        }
    }
    
    public int find(int x) {
        Integer val = this.mappings.get(x);
        if (val == null) {
            return -1;
        }
        
        int toFind = val.intValue();

        while(this.parent[toFind] != toFind) {
            int temp = toFind;
            toFind = this.parent[toFind];
            this.parent[temp] = this.parent[toFind];
        }
        return this.parent[toFind];
    }
    
    public void union(int val1, int val2) {
        int root1 = find(val1);
        int root2 = find(val2);
    
        if (root1 == -1 || root2 == -1) {
            System.out.println("values not found");
            return;
        } 
        
        if (root1 == root2) {
            System.out.println("same parents already");
            return;
        }
        
        int rank1 = this.rank[root1];
        int rank2 = this.rank[root2];
            
        if (rank1 >= rank2) {
            this.parent[root2] = root1;
            this.rank[root1] = this.rank[root1] + this.rank[root2];
            this.rank[root2] = 0;
        } else {
            this.parent[root1] = root2;
            this.rank[root2] = this.rank[root2] + this.rank[root1];
            this.rank[root1] = 0;
        }
    }   
    
    public void print() {
        System.out.println("------------------------------");   
        System.out.println(Arrays.toString(this.parent));
        System.out.println(Arrays.toString(this.rank));   
        System.out.println("------------------------------");   
    }
    
    public boolean isSameSet(int val1, int val2) {
        return find(val1) == find(val2);   
    } 
} 


class UFDStester {
    public static void main(String[] args) {
        int[] elements1 = {7, 9, 2, 4, 1, 6};
        UFDS ufds1 = new UFDS(elements1);
        ufds1.print();
        ufds1.union(7, 9);
        ufds1.print();
        ufds1.union(7, 1);
        ufds1.print();
        ufds1.union(2, 4);
        ufds1.print();
        ufds1.union(4, 6);
        ufds1.print();
        ufds1.union(2, 6);
        ufds1.print();
    }   
}    
