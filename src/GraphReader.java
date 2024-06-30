import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphReader {

    public static WeightedGraph readGraphFromFile(String filePath) {
        WeightedGraph graph = new WeightedGraphImpl();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String vertex1 = parts[0];
                    String vertex2 = parts[1];
                    int weight = Integer.parseInt(parts[2]);

                    graph.addVertex(vertex1);
                    graph.addVertex(vertex2);
                    graph.addEdge(vertex1, vertex2, weight);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}
