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
    public ArrayList<Integer> data;

    public Queue() {
        this.data = new ArrayList<Integer>();
    }
    
    public void offer(int i) {
        this.data.add(i);
    }
    
    public boolean isEmpty() {
        return this.data.size() == 0;
    }
    
    public int poll() {
        int out = this.data.get(0);
        this.data.remove(0);
        return out;
    }
}
        
    
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
            int node = stack.pop();
            
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
    
    public void bfs(int start) {
        boolean[] visited = new boolean[n];
        Queue queue = new Queue();
        
        queue.offer(start);
    
        while (!queue.isEmpty()) {
            int node = queue.poll();    
            
            if (visited[node]) {
                continue;
            }
    
            visited[node] = true;
            System.out.println(node);
            
            for (int nb : g.get(node)) {
                queue.offer(nb);
            }
        }
    }
    
    public void betterBFS(int start) {
        boolean[] visited = new boolean[n];
        Queue queue = new Queue();
        
        visited[start] = true;
        queue.offer(start); 
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.println(node);
            
            for (int nb : g.get(node)) {
                if (!visited[nb]) {
                    visited[nb] = true;
                    queue.offer(nb);
                }
            }
        }
    }
} 

public class Traversal {
    public static void main(String[] args) {

        // create graph with 6 nodes
        Graph g = new Graph(6);

        // add edges
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);

        System.out.println("DFS starting from 0:");
        g.dfs(0);

        System.out.println("\nBFS starting from 0:");
        g.bfs(0);

        System.out.println("\nBetter BFS starting from 0:");
        g.betterBFS(0);
    }
}
