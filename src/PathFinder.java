import java.util.List;
import java.util.Random;

public class PathFinder {
    public static void findAndPrintShortestPath(WeightedGraph graph) {
        List<String> vertices = graph.getVertices();
        if (vertices.size() < 2) {
            System.out.println("Not enough vertices in the graph.");
            return;
        }

        Random rand = new Random();
        String start = vertices.get(rand.nextInt(vertices.size()));
        String end = vertices.get(rand.nextInt(vertices.size()));
        while (end.equals(start)) {
            end = vertices.get(rand.nextInt(vertices.size()));
        }

        System.out.println("Finding shortest path from " + start + " to " + end);
        List<String> path = graph.getCheapestPath(start, end);
        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Shortest path: " + path);
        }
    }
}
