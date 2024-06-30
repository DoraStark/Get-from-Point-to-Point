import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphReader {
    public static WeightedGraph readGraphFromFile(String filePath) {
        WeightedGraphImpl graph = new WeightedGraphImpl();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    System.err.println("Incorrect line format: " + line);
                    continue; // пропустить некорректные строки
                }
                String stationName = parts[0].trim();
                String stationId = parts[1].trim();

                // Добавить вершину в граф (если это необходимо)
                graph.addVertex(stationId);

                // Если нужно добавить ребра, это должно быть сделано отдельно, так как в текущем формате ребра не указаны
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}
