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
        return dijkstra(start, end);
    }

    @Override
    public List<String> getCheapestPath(String start, String end) {
        // Реализация поиска самого дешевого пути (например, алгоритм Беллмана-Форда)
        return dijkstra(start, end);
    }

    private List<String> dijkstra(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (String vertex : adjacencyList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
            previous.put(vertex, null);
        }
        distances.put(start, 0);
        pq.add(start);

        while (!pq.isEmpty()) {
            String current = pq.poll();
            if (current.equals(end)) break;

            for (Edge edge : getEdges(current)) {
                int newDist = distances.get(current) + edge.weight;
                if (newDist < distances.get(edge.destination)) {
                    distances.put(edge.destination, newDist);
                    previous.put(edge.destination, current);
                    pq.add(edge.destination);
                }
            }
        }

        List<String> path = new LinkedList<>();
        for (String at = end; at != null; at = previous.get(at)) {
            path.add(0, at);
        }

        if (path.size() == 1 && !path.get(0).equals(start)) {
            return Collections.emptyList(); // No path found
        }
        return path;
    }

    @Override
    public void printGraph() {
        for (Map.Entry<String, List<Edge>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    @Override
    public List<String> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }
}
