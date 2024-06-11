/*
* Student Name: Gamage Sarini Hasangee
* IIT No: 20221215
* UoW No: w1998761
* */

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args){

        try {
            System.out.println("----------- Sliding Puzzle -----------");

            System.out.println(" ");

            //Read the map from the input file using the MapParser
            MapParser parser = new MapParser("C:\\\\Users\\\\ASUS\\\\Desktop\\\\AlgoViva\\\\w1998761_20221215_AlgoCW_Implementation\\\\w1998761_AlgoCW_Implementation\\\\PuzzleSolver\\\\src\\\\slidingPuzzle.txt");
            char[][] map = parser.getMap();
            MapParser.Point start = parser.getStart();
            MapParser.Point finish = parser.getFinish();

            // Create a graph
            Graph graph = new Graph(map);

            // Find the shortest path using Dijkstra's algorithm
            List<String> shortestPath = SlidingPuzzlePath.findPath(map, start, finish);

            // Display the shortest path
            if (!shortestPath.isEmpty()) {
                System.out.println("Shortest Path:");
                System.out.println(" ");
                for (int i = 0; i < shortestPath.size(); i++) {
                    System.out.println((i + 1) + ". " + shortestPath.get(i));
                }
            } else {
                System.out.println("No path found!");
            }

        } catch (IOException e) {
            System.err.println("Error reading the map file: " + e.getMessage());
        }
    }
}