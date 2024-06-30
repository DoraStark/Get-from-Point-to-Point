import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PathFinder {
    public static void findAndPrintShortestPath(WeightedGraph graph) {

        List<String> vertices = new ArrayList<>(graph.getVertices());
        Random random = new Random();
        String start = vertices.get(random.nextInt(vertices.size()));
        String end = vertices.get(random.nextInt(vertices.size()));

        List<String> path = graph.getShortestPath(start, end);
        System.out.println("Shortest path from " + start + " to " + end + ": " + path);
    }
}
