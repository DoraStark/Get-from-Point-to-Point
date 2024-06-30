import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class WeightedGraphImpl implements WeightedGraph {
    private Map<String, List<Edge>> adjacencyList;

    public WeightedGraphImpl() {
        adjacencyList = new HashMap<>();
    }

    @Override
    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new LinkedList<>());
    }

    @Override
    public void addEdge(String from, String to, int weight) {
        adjacencyList.get(from).add(new Edge(to, weight));
    }

    @Override
    public List<String> getShortestPath(String start, String end) {
        // Реализуйте алгоритм Дейкстры здесь
        return dijkstra(start, end);
    }

    @Override
    public List<String> getCheapestPath(String start, String end) {
        // Можно использовать ту же реализацию алгоритма Дейкстры, так как он предназначен для взвешенных графов
        return dijkstra(start, end);
    }

    private List<String> dijkstra(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for (String vertex : adjacencyList.keySet()) {
            if (vertex.equals(start)) {
                distances.put(vertex, 0);
                priorityQueue.add(new Node(vertex, 0));
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
                priorityQueue.add(new Node(vertex, Integer.MAX_VALUE));
            }
            previous.put(vertex, null);
        }

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            String currentVertex = currentNode.vertex;

            if (currentVertex.equals(end)) {
                List<String> path = new LinkedList<>();
                while (previous.get(currentVertex) != null) {
                    path.add(0, currentVertex);
                    currentVertex = previous.get(currentVertex);
                }
                path.add(0, start);
                return path;
            }

            for (Edge edge : adjacencyList.get(currentVertex)) {
                int alt = distances.get(currentVertex) + edge.weight;
                if (alt < distances.get(edge.destination)) {
                    distances.put(edge.destination, alt);
                    previous.put(edge.destination, currentVertex);
                    priorityQueue.add(new Node(edge.destination, alt));
                }
            }
        }

        return new LinkedList<>();
    }

    @Override
    public void printGraph() {
        for (String vertex : adjacencyList.keySet()) {
            System.out.print(vertex + " -> ");
            for (Edge edge : adjacencyList.get(vertex)) {
                System.out.print(edge.destination + "(" + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    @Override
    public List<String> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    private class Edge {
        String destination;
        int weight;

        Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    private class Node implements Comparable<Node> {
        String vertex;
        int distance;

        Node(String vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}
