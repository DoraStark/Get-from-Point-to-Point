import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class GraphReader {
    public static WeightedGraph readGraphFromFile(String filePath) {
        WeightedGraphImpl graph = new WeightedGraphImpl();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Random random = new Random();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    System.err.println("Incorrect line format: " + line);
                    continue; // пропустить некорректные строки
                }
                String stationName = parts[0].trim();
                String stationId = parts[1].trim();
                graph.addVertex(stationId);

                // Добавление ребер со случайным весом к предыдущей станции для тестирования
                // В реальном сценарии вам нужно будет определить, как добавлять ребра и их веса
                if (graph.getVertices().size() > 1) {
                    String[] vertices = graph.getVertices().toArray(new String[0]);
                    String previousStation = vertices[vertices.length - 2];
                    int weight = random.nextInt(100) + 1; // Случайный вес от 1 до 100
                    graph.addEdge(previousStation, stationId, weight);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}
