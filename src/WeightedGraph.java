import java.util.List;

public interface WeightedGraph {
    void addVertex(String vertex);
    void addEdge(String from, String to, long weight);
    List<String> getShortestPath(String start, String end);
    List<String> getCheapestPath(String start, String end);
    void printGraph();
    List<String> getVertices();
    List<Edge> getEdges(String node);

    class Edge {
        String destination;
        long weight;

        public Edge(String destination, long weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return destination + "(" + weight + ")";
        }
    }
}
