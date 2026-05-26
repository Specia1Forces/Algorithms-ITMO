package third_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GraphColoring {

    public ArrayList<Integer> colour;
    public ArrayList<Integer> visited;

    private final List<List<Integer>> listAdjacency;


    private Boolean isColoring = true;

    int countWhite = 0;
    int countBlack = 0;

    public GraphColoring(int vert) {
        visited = new ArrayList<>();
        for (int i = 0; i < vert; i++) {
            visited.add(0);
        }

        colour = new ArrayList<>(vert);
        for (int i = 0; i < vert; i++) {
            colour.add(-1);

        }
        listAdjacency = new ArrayList<>(vert);
        for (int i = 0; i < vert; i++) {
            listAdjacency.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        source = source - 1;
        destination = destination - 1;
        // Добавляем ребро от source к destination
        listAdjacency.get(source).add(destination);
        // Добавляем ребро от destination к source
        listAdjacency.get(destination).add(source);
    }


    public void dfs(int v, int col) {
        col = (col + 1) % 2;
        colour.set(v, col);

        for (int i = 0; i < listAdjacency.get(v).size(); i++) {

            if (colour.get(listAdjacency.get(v).get(i)) == -1) {
                dfs(listAdjacency.get(v).get(i), col);
            } else if (colour.get(listAdjacency.get(v).get(i)) == col) {
                isColoring = false;
            }
        }

    }

    public void colourGraph() {
        for (int v = 0; v < listAdjacency.size(); v++) {
            if (colour.get(v) == -1) {
                dfs(v, -1);
            }

        }

    }

    void printResult() {
        if (!isColoring) {
            System.out.print("-1");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < colour.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(colour.get(i) + 1); // 0 -> 1, 1 -> 2
        }
        System.out.println(sb);

        /*
        for (Integer integer : colour) {
            if (integer == 0) {
                countWhite++;
            } else if (integer == 1) {
                countBlack++;
            }
        }

        for (int i = 0; i < countWhite; i++) {
            System.out.print("1 ");
        }
        for (int i = 0; i < countBlack; i++) {
            System.out.print("2 ");
        }

         */
    }


    public void printGraph() {
        System.out.println("Список смежностей графа:");
        for (int i = 0; i < listAdjacency.size(); i++) {
            System.out.print("Вершина " + i + ": ");
            for (Integer edge : listAdjacency.get(i)) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] verticesAndSizeEdges = reader.readLine().split(" ");
        int vertices = Integer.parseInt(verticesAndSizeEdges[0]);
        int sizeEdges = Integer.parseInt(verticesAndSizeEdges[1]);

        GraphColoring graphColoring = new GraphColoring(vertices);

        for (int i = 0; i < sizeEdges; i++) {
            String[] edge = reader.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            graphColoring.addEdge(u, v);
        }

        //graphColoring.printGraph();
        graphColoring.colourGraph();
        graphColoring.printResult();

    }
}
