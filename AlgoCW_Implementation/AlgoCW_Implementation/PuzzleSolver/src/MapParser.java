import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapParser {
    private char[][] map;
    private Point start;
    private Point finish;
    private int height;
    private int width;

    public MapParser(String filePath) throws IOException{
        parserFile(filePath);
    }

    // Method to parse the map file
    public void parserFile(String filePath) throws IOException{
        List<String> lines = new ArrayList<>();
        // Read lines from the file and store them in a list
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                lines.add(line);
            }
        }

        //Determine the height and the width of the map
        height = lines.size();
        width = lines.get(0).length();
        // Initialize the map array with the determined dimensions
        map = new char[height][width];

        // Iterate through the lines to populate the map array
        for(int i = 0; i < height; i++){
            map[i] = lines.get(i).toCharArray();
            for(int j = 0; j < width; j++){
                char c = map[i][j];
                // Identify start and finish points
                if (c == 'S') {
                    start = new Point(i, j);
                } else if (c == 'F') {
                    finish = new Point(i,j);
                }
            }
        }
    }

    // Getter method to retrieve the parsed map
    public char[][] getMap() {
        return map;
    }

    // Getter method to retrieve the start point
    public Point getStart(){
        return start;
    }

    // Getter method to retrieve the finish point
    public Point getFinish() {
        return finish;
    }


    // Static nested class representing a point on the map
    public static class Point{
        public final int x;
        public final int y;

        // Constructor to initialize the point with coordinates
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
