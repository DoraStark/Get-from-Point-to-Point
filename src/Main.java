import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Чтение графа из файла
        String filePath = "../stations.txt"; // путь к вашему файлу, возможно, нужно изменить
        WeightedGraph graph = GraphReader.readGraphFromFile(filePath);

        // Печать графа для проверки
        graph.printGraph();

        // Нахождение и печать кратчайшего пути
        PathFinder.findAndPrintShortestPath(graph);

        // Рассчитываем кратчайшие времена путешествия от станции "S Schöneweide Bhf (Berlin)"
        String startStation = "060192001006"; // используйте идентификатор станции
        String[] targetStations = {
                "06068201511", "06066102852", "06053301433", "060120003653"
        };

        for (String endStation : targetStations) {
            List<String> shortestPath = graph.getShortestPath(startStation, endStation);
            int travelTime = calculateTravelTime(graph, shortestPath);
            System.out.println("Shortest travel time from " + startStation + " to " + endStation + ": " + travelTime);
        }
    }

    // Вспомогательный метод для расчета времени путешествия
    private static int calculateTravelTime(WeightedGraph graph, List<String> path) {
        int totalTime = 0;
        if (path == null || path.size() < 2) return totalTime;

        for (int i = 0; i < path.size() - 1; i++) {
            String from = path.get(i);
            String to = path.get(i + 1);
            for (WeightedGraphImpl.Edge edge : ((WeightedGraphImpl) graph).getEdges(from)) {
                if (edge.destination.equals(to)) {
                    totalTime += edge.weight;
                    break;
                }
            }
        }
        return totalTime;
    }
}
