import java.util.List;

public class Main {
    public static void main(String[] args) {

        String filePath = "stations.txt";
        WeightedGraph graph = GraphReader.readGraphFromFile(filePath);


        graph.printGraph();


        PathFinder.findAndPrintShortestPath(graph);


        String startStation = "S Schöneweide Bhf (Berlin)";
        String[] targetStations = {
                "60068201511", "60066102852", "60053301433", "60120003653"
        };

        for (String endStation : targetStations) {
            List<String> shortestPath = graph.getShortestPath(startStation, endStation);
            int travelTime = calculateTravelTime(graph, shortestPath);
            System.out.println("Shortest travel time from " + startStation + " to " + endStation + ": " + travelTime);
        }
    }


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
