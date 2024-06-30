import java.util.List;

public class Main {
    public static void main(String[] args) {

        String filePath = "stations.txt";
        WeightedGraph graph = GraphReader.readGraphFromFile(filePath);


        graph.printGraph();


        String startStation = "S Schöneweide Bhf (Berlin)"; // используйте идентификатор станции
        String[] targetStations = {
                "60068201511", "60066102852", "60053301433", "60120003653"
        };

        for (String endStation : targetStations) {
            List<String> path = graph.getCheapestPath(startStation, endStation);
            long travelTime = calculateTravelTime(graph, path);
            System.out.println("Shortest travel time from " + startStation + " to " + endStation + ": " + travelTime);
        }
    }

    private static long calculateTravelTime(WeightedGraph graph, List<String> path) {
        long totalTime = 0;
        if (path == null || path.size() < 2) return totalTime;

        for (int i = 0; i < path.size() - 1; i++) {
            String from = path.get(i);
            String to = path.get(i + 1);
            for (WeightedGraph.Edge edge : graph.getEdges(from)) {
                if (edge.destination.equals(to)) {
                    totalTime += edge.weight;
                    break;
                }
            }
        }
        return totalTime;
    }
}
