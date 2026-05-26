package third_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TopSort {
    public ArrayList<Integer> used;

    private final List<List<Integer>> listAdjacency;

    private final List<Integer> topSort;
    public ArrayList<Integer> colour;
    public int cycle = 0;


    public TopSort(int vert) {
        topSort = new ArrayList<>();

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
        //listAdjacency.get(destination).add(source);
    }


    public boolean dfs(int v) {
        colour.set(v, 0);

        for (int i = 0; i < listAdjacency.get(v).size(); i++) {
            if (colour.get(listAdjacency.get(v).get(i)) == -1) {

                if (dfs(listAdjacency.get(v).get(i))) {
                    return true;
                }
            } else if (colour.get(listAdjacency.get(v).get(i)) == 0) {
                cycle = -1;
                return true;
            }
        }
        colour.set(v, 1);
        topSort.add(v);
        return false;
    }

    public void topSort() {
        boolean flag = false;
        for (int v = 0; v < listAdjacency.size(); v++) {
            if (colour.get(v) == -1) {
                flag = dfs(v);
            }
            if (flag) {
                break;
            }
        }
    }

    public void print() {
        if (cycle == -1) {
            System.out.println(-1);
            return;
        }
        for (int i = topSort.size() - 1; i >= 0; i--) {
            System.out.print(topSort.get(i) + 1);
            System.out.print(" ");
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] verticesAndSizeEdges = reader.readLine().split(" ");
        int vertices = Integer.parseInt(verticesAndSizeEdges[0]);
        int sizeEdges = Integer.parseInt(verticesAndSizeEdges[1]);

        TopSort topSort = new TopSort(vertices);

        for (int i = 0; i < sizeEdges; i++) {
            String[] edge = reader.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            topSort.addEdge(u, v);
        }

        topSort.topSort();
        topSort.print();


    }
}
