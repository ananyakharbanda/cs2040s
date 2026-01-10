
class Edge {
    public int destNode;
    public int weight;

    public Edge(int d, int w)
    {
        this.destNode = d;
        this.weight = w;
    }
}

class WeightedGraph {
    private List<List<Edge>> g;
    private ArrayList<Integer> inMst;
    private ArrayList<Integer> notInMst;
    private static final int NO_PARENT = -1;
    
    public int[] parent;
    
    public WeightedGraph(List<List<List<Integer>>> g) {
        this.g = g;
        this.inMst = new ArrayList<>();
        this.notInMst = new ArrayList<>();

        for(int i = 0 ; i < this.g.size(); i++)
        {
            this.notInMst.add(i);
        }
    }
    
    public int[] primMST(int n) {
        parent = new int[n];
        Arrays.fill(parent, NO_PARENT);
        primMSThelper();
        return this.parent;
    }
    
    private void primMSThelper() {

        while(notInMst.size() > 0)
        {
            int curr = notInMst.get(0);
            List<Edge> vertexAdjacencyList = this.g.get(curr); 

            PriorityQueue<Edge> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));

            for(Edge e : vertexAdjacencyList)
            {
                minHeap.add(e);            
            } 

            Edge min = minHeap.get();

            parent[min.destNode - 1] = curr;

            inMst.add(min.destNode - 1);

            notInMst.remove(0);
        }
    }
}

class WeightMain
{
    public static void main(String[] args)
    {

        Edge 1_2 = new Edge(2,3);
        Edge 1_4 = new Edge(4,4);
`
        Edge 2_1 = new Edge(1,3);
        Edge 2_3 = new Edge(3,3);
        Edge 2_4 = new Edge(4,4);

        Edge 3_2 = new Edge(2,3);
        Edge 3_4 = new Edge(4,2);
        Edge 3_5 = new Edge(5,4);

        Edge 4_1 = new Edge(1,4);
        Edge 4_2 = new Edge(2,4);
        Edge 4_3 = new Edge(3,2);
        Edge 4_5 = new Edge(5,5);
        Edge 4_6 = new Edge(6,3);

        Edge 5_3 = new Edge(3,4);
        Edge 5_4 = new Edge(4,5);
        Edge 5_6 = new Edge(6,4);

        Edge 6_4 = new Edge(4,3);
        Edge 6_5 = new Edge(5,4);

        List<Edge> al1 = new ArrayList<>(List.of(1_2, 1_4));
        List<Edge> al2 = new ArrayList<>(List.of(2_1, 2_3, 2_4));
        List<Edge> al3 = new ArrayList<>(List.of(3_2, 3_4, 3_5));
        List<Edge> al4 = new ArrayList<>(List.of(4_1, 4_2, 4_3, 4_5, 4_6));
        List<Edge> al5 = new ArrayList<>(List.of(5_3, 5_4, 5_6));
        List<Edge> al6 = new ArrayList<>(List.of(6_4, 6_5));

        List<List<Edge> gr = new ArrayList<>();
        gr.add(al1);
        gr.add(al2);
        gr.add(al3);
        gr.add(al4);
        gr.add(al5);
        gr.add(al6);

        WeightedGraph g = new WeightedGraph(gr);
        g.
    }
}
