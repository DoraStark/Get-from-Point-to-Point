import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PathFinder {
    public static void findAndPrintShortestPath(WeightedGraph graph) {

        List<String> vertices = new ArrayList<>(graph.getVertices());
        if (vertices.size() < 2) {
            System.out.println("Graph doesn't have enough vertices.");
            return;
        }

        Random random = new Random();
        String start = vertices.get(random.nextInt(vertices.size()));
        String end;
        do {
            end = vertices.get(random.nextInt(vertices.size()));
        } while (start.equals(end));

        List<String> path = graph.getShortestPath(start, end);
        System.out.println("Shirtest way to " + start + " till " + end + ": " + path);
    }
}
