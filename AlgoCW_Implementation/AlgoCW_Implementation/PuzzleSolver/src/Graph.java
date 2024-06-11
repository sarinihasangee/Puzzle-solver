/*
 * Student Name: Gamage Sarini Hasangee
 * IIT No: 20221215
 * UoW No: w1998761
 * */

public class Graph {
    // Private fields to store the nodes, start, and finish nodes of the graph
    private Node[][] node;
    private Node start;
    private Node finish;

    public Graph(char[][] map) {
        // Determine the height and width of the map
        int height = map.length;
        int width = map[0].length;
        // Initialize the node array with the same dimensions as the map
        node = new Node[height][width];

        // Iterate through the map to create nodes for non-zero elements
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] != '0') {
                    node[i][j] = new Node(i, j);
                    if (map[i][j] == 'S') {
                        start = node[i][j];
                    }
                    if (map[i][j] == 'F') {
                        finish = node[i][j];
                    }
                }
            }
        }

        // Connect nodes that are adjacent and reachable
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (node[i][j] != null) {
                    connectNodes(i, j, width, height);
                }
            }
        }
    }

    // Method to connect adjacent nodes
    private void connectNodes(int i, int j, int width, int height) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // Iterate through possible movements
        for (int d = 0; d < 4; d++) {
            int px = j;
            int py = i;

            // Move until encountering an edge
            while (true) {
                int qx = px + dx[d];
                int qy = py + dy[d];

                if (qx < 0 || qy < 0 || qx >= width || qy >= height || node[qy][qx] == null) {
                    break;
                }

                px = qx;
                py = qy;
            }

            // Add an edge between the original node and the final position after movement
            if (px != j || py != i) {
                node[i][j].addEdge(node[py][px]);
            }
        }
    }

    // Getter method to retrieve the start node
    public Node getStart() {
        return start;
    }

    // Getter method to retrieve the finish node
    public Node getFinish() {
        return finish;
    }

}


