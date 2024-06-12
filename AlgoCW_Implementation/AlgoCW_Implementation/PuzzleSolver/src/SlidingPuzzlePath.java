import java.util.*;

public class SlidingPuzzlePath {
    // Constants representing directions and corresponding moves
    public static final int[] DIRECTION_X = {-1, 1, 0, 0};
    public static final int[] DIRECTION_Y = {0, 0, -1, 1};
    public static final String[] MOVES = {"Move up", "Move down", "Move left", "Move right"};

    // Method to find a path from start to finish on a given map
    public static List<String> findPath(char[][] map, MapParser.Point start, MapParser.Point finish) {
        // Dimensions of the map
        int m = map.length;
        int n = map[0].length;

        // Priority queue to store nodes to be evaluated, and maps to track parents and gScores
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(node -> node.gScore));
        Map<Node, Node> parentMap = new HashMap<>();
        Map<Node, Integer> gScore = new HashMap<>();

        // Initialize start node
        Node startNode = new Node(start.x, start.y);
        startNode.gScore = 0; // The cost from start to start is 0
        gScore.put(startNode, 0);

        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            // If the current node is the finish node, reconstruct the path and return it
            if (current.x == finish.x && current.y == finish.y) {
                return reconstructPath(parentMap, current);
            }

            // Explore neighbors
            for (int dir = 0; dir < 4; dir++) {
                Node neighbor = slide(map, current, dir, m, n);
                if (neighbor == null) continue;

                // Calculate tentative gScore for the neighbor
                int tentativeGScore = gScore.getOrDefault(current, Integer.MAX_VALUE) + 1;
                if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    // Update parent and gScore for the neighbor
                    parentMap.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    neighbor.gScore = tentativeGScore; // Update the neighbor's cost
                    // Add the neighbor to the open set if it's not already there
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return Collections.emptyList();                // If no path is found, return an empty list
    }

    // Method to simulate sliding in a direction on the map
    private static Node slide(char[][] map, Node start, int dir, int m, int n) {
        int x = start.x;
        int y = start.y;
        // Slide until encountering an obstacle or edge
        while (true) {
            int nx = x + DIRECTION_X[dir];
            int ny = y + DIRECTION_Y[dir];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n || map[nx][ny] == '0') break;
            x = nx;
            y = ny;
        }
        if (x == start.x && y == start.y) return null; // No movement
        return new Node(x, y);          // Otherwise, return the new node after sliding
    }

    // Method to reconstruct the path from finish node to start node
    private static List<String> reconstructPath(Map<Node, Node> parentMap, Node finish) {
        LinkedList<String> path = new LinkedList<>();
        Node step = finish;
        // Traverse parent map to construct the path
        while (parentMap.containsKey(step)) {
            Node parent = parentMap.get(step);
            path.addFirst(MOVES[getDirection(parent, step)] + " to (" + (step.y + 1) + "," + (step.x + 1) + ")");       // Add move to the path
            step = parent;
        }
        path.addFirst("Start at (" + (step.y + 1) + "," + (step.x + 1) + ")");          // Add start and finish points to the path
        path.add("Done!");
        return path;
    }

    // Method to determine the direction of movement from parent to current node
    private static int getDirection(Node parent, Node current) {
        int dx = current.x - parent.x;
        int dy = current.y - parent.y;
        if (dx == -1) return 0; // Move up
        else if (dx == 1) return 1; // Move down
        else if (dy == -1) return 2; // Move left
        else return 3; // Move right
    }
}

