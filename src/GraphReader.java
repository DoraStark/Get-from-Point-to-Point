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
                if (parts.length < 2) {
                    System.err.println("Incorrect line format: " + line);
                    continue; // пропустить некорректные строки
                }
                String from = parts[0].trim();
                String to = parts[1].trim();
                long weight = (parts.length == 3) ? Long.parseLong(parts[2].trim()) : 1L; // Установить вес по умолчанию, если он отсутствует

                graph.addVertex(from);
                graph.addVertex(to);
                graph.addEdge(from, to, weight);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}
