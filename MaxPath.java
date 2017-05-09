import java.util.*;

public class MaxPath {
    private Graph _graph;

    /**
     * I took a few liberties with the way inputs would be handled; I imagined that all of the input would be coming
     * from parameters passed into an instance creation. This was mainly for inputs such as the Edge labels as I was un-
     * sure whether to use Regex to read a possible String passed in line-by-line. In the end, I chose to represent the
     * inputs as an array of Strings which I'm not too sure was the intended configuration.
     */
    public static void main(String[] args) {
        String[] cities = {"S1", "A", "B", "C", "S2"};
        int[] M = {5, 15, 16, -1, 0};
        String[] edgelabels = {"S1 A -2", "S1 B -3", "A C -1", "A B -9", "B S2 -8", "C S2 -1"};
        MaxPath test = new MaxPath(5, 6, cities, M, edgelabels);
        int sum = test.traverse();
        System.out.println(sum);
    }

    /**
     * To represent the graph I created Vertex, Edge, and Graph classes that are relatively barebones. Most of the work
     * is done in the actual creation of a MaxPath instance in order to populate the graph.
     */
    private class Vertex {
        private int _money;
        private String _name;

        Vertex(String name, int money) {
            _name = name;
            _money = money;
        }

        public String name() {
            return _name;
        }

        public int money() {
            return _money;
        }
    }

    private class Edge {
        private int _cost;
        private Vertex _src;
        private Vertex _dst;

        Edge(Vertex src, Vertex dst, int cost) {
            _src = src;
            _dst = dst;
            _cost = cost;
        }

        public Vertex src() {
            return _src;
        }

        public Vertex dst() {
            return _dst;
        }

        public int cost() {
            return _cost;
        }
    }

    private class Graph {
        private HashMap<String, Vertex> _vertices = new HashMap<>();
        private ArrayList<Edge> _edges = new ArrayList<>();

        Graph(HashMap<String, Vertex> vertices, ArrayList<Edge> edges) {
            _vertices = vertices;
            _edges = edges;
        }

        public HashMap<String, Vertex> vertices() {
            return _vertices;
        }

        public ArrayList<Edge> edges() {
            return _edges;
        }
    }

    /**
     * This is the creation of the instance of the Class I ended up using in order to create the given graph to prepare
     * for traversal. Again, I took a few liberties in interpreting how the input would be given and ended up using a
     * scanner in order to deal with the edge label inputs.
     */

    MaxPath(int numcities, int numedges, String[] cities, int[] M, String[] edgelabels) {
        HashMap<String, Vertex> vertices = new HashMap<>();
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < numcities; i++) {
            vertices.put(cities[i], new Vertex(cities[i], M[i]));
        }
        for (int i = 0; i < numedges; i++) {
            Scanner scanner = new Scanner(edgelabels[i]);
            String src = scanner.next();
            String dst = scanner.next();
            int cost = Integer.parseInt(scanner.next());
            edges.add(new Edge(vertices.get(src), vertices.get(dst), cost));
        }
        _graph = new Graph(vertices, edges);
    }

    /**
     * This traversal doesn't really resemble typical ones such as Depth, Breadth, Djikstra's or Kruskal's. I figured
     * that Kruskal's would be the best algorithm to implement and switch from the normal Minimal spanning to Maximal
     * spanning; however I ran into complications I couldn't sort out in my head when dealing with the fringe and com-
     * parator and how to go about implementing these things when indices of data strucutes couldn't respresent the ver-
     * tices. As such, I instead chose to take advantage of the fact that S1 and S2 were always the source and destina-
     * tion and used a vertex pointer to accomplish this traversal. One issue I know this algorithm would run into are
     * cases where multiple paths from the "current" vertex are equivalent and I'd have to analyze further. Kruskal's
     * would likely account for that but it was a bit confusing for me to wrap my head around the implementation.
     */

    public int traverse() {
        int sum = 0;
        HashMap<String, Vertex> vertices = _graph.vertices();
        Vertex current = vertices.get("S1");
        while (current != vertices.get("S2")) {
            int gain = Integer.MIN_VALUE;
            for (Edge edge : _graph.edges()) {
                if (edge.src() == current) {
                    int curr = edge.cost() + edge.dst().money();
                    if (curr > gain) {
                        gain = curr;
                        current = edge.dst();
                    }
                }
            }
            sum += gain;
        }
        return sum + vertices.get("S1").money() + vertices.get("S2").money();
    }
}
