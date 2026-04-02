import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Stack {
    public ArrayList<Integer> data;
    
    public Stack() {
        this.data = new ArrayList<Integer>();
    }
    
    public void push(int i) {
        this.data.add(i);
    }   
    
    public boolean isEmpty() {
        return this.data.size() == 0;
    }
    
    public int pop() {
        int out = this.data.get(this.data.size() - 1);
        this.data.remove(this.data.size() - 1);
        return out;
    }
}

class Queue {
    
class Graph {
    private List<List<Integer>> g;
    private int n;
        
    public Graph(int n) {
        this.n = n;
        this.g = new ArrayList<>();
    
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
    }
        
    public void addEdge(int u, int v) {
        g.get(u).add(v);    
        g.get(v).add(u);
    }
    
    public List<Integer> getNeighbours(int u) {
        return g.get(u);
    }
    
    public void dfs(int start) {
        boolean[] visited = new boolean[n];
        Stack stack = new Stack();
        
        stack.push(start);
        
        while (!stack.isEmpty()) {
            int node = stakck.pop();
            
            if (visited[node]) {
                continue;
            }
            
            visited[node] = true;
            System.out.println(node);   
            
            for(int nb : g.get(node)) {
                stack.push(nb);
            }
        }
    } 
