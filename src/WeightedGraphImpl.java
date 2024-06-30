import java.util.*;

public class WeightedGraphImpl implements WeightedGraph {
    private Map<String, List<Edge>> adjacencyList;

    public WeightedGraphImpl() {
        adjacencyList = new HashMap<>();
    }

    @Override
    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void addEdge(String from, String to, int weight) {
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
    }

    @Override
    public List<Edge> getEdges(String node) {
        return adjacencyList.getOrDefault(node, new ArrayList<>());
    }

    @Override
    public List<String> getShortestPath(String start, String end) {
        // Реализация поиска кратчайшего пути (например, алгоритм Дейкстры)
        return Arrays.asList(start, end); // Замените на реальную реализацию
    }

    @Override
    public List<String> getCheapestPath(String start, String end) {
        // Реализация поиска самого дешевого пути (например, алгоритм Беллмана-Форда)
        return Arrays.asList(start, end); // Замените на реальную реализацию
    }

    @Override
    public void printGraph() {
        // Печать графа для отладки
        for (Map.Entry<String, List<Edge>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    @Override
    public List<String> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    public static class Edge {
        String destination;
        int weight;

        public Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return destination + "(" + weight + ")";
        }
    }
}
