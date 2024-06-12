import java.util.ArrayList;
import java.util.List;

public class Node {
    public int x;
    public int y;
    public int f;

    public int gScore; // Cost from the start node
    public Node parent; // Parent node in the shortest path
    public List<Node> neighbors; // List of neighboring nodes


    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.f = 0;
        this.gScore = Integer.MAX_VALUE;
        this.parent = null;
        this.neighbors = new ArrayList<>();
    }

    // Method to add an edge (connection) to a neighboring node
    public void addEdge(Node neighbor) {
        this.neighbors.add(neighbor);
    }

    // Method to get the neighboring nodes of this node
    public List<Node> getNeighbors() {
        return this.neighbors;
    }

}

