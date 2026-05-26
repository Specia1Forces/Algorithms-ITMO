package third_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CycleSearch {
    public ArrayList<Integer> visited;
    public ArrayList<Integer> colour;
    public ArrayList<Integer> parents;
    public ArrayList<Integer> cycle = new ArrayList<>();
    public int end = -1;
    public int begin = -1;

    private final List<List<Integer>> listAdjacency;


    public CycleSearch(int vert) {
        visited = new ArrayList<>();
        parents = new ArrayList<>();
        for (int i = 0; i < vert; i++) {
            visited.add(0);
            parents.add(-1);
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
    }

    public boolean dfs(int v) {
        // System.out.println(v);
        colour.set(v, 0);

        for (int i = 0; i < listAdjacency.get(v).size(); i++) {
            if (colour.get(listAdjacency.get(v).get(i)) == -1) {
                parents.set(listAdjacency.get(v).get(i), v);
                if (dfs(listAdjacency.get(v).get(i))) {
                    return true;
                }
            } else if (colour.get(listAdjacency.get(v).get(i)) == 0) {
                begin = listAdjacency.get(v).get(i);
                end = v;
                return true;
            }
        }
        colour.set(v, 1);
        return false;
    }

    public void cycleGraph() {
        boolean flag = false;
        for (int v = 0; v < listAdjacency.size(); v++) {
            if (colour.get(v) == -1) {
                 parents.set(v, v);
                flag = dfs(v);
            }
            if (flag) {
                break;
            }
        }

        /*
        System.out.println(parents);
        System.out.println("end" + end);
        System.out.println("beg" + begin);


         */


        if (end == -1) {
            System.out.println(-1);
            return;
        } else {
            int cur = end;

            while (true) {
                if (cur == begin) {
                    cycle.add(cur);
                    break;
                }
                cycle.add(cur);
                cur = parents.get(cur);

            }
        }
        /*
        if (end == -1) {
            System.out.print(-1);
            return;
        } else {
            int cur = end;
            cycle.add(begin);
            //cycle.add(cur);
            while (cur != begin) {
                cycle.add(cur);
                cur = parents.get(cur);
            }
        }

         */

        System.out.println(cycle.size());



        for (int i = cycle.size() - 1; i >= 0; i--) {
            System.out.print((cycle.get(i) + 1) + " ");
        }


    }


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] verticesAndSizeEdges = reader.readLine().split(" ");
        int vertices = Integer.parseInt(verticesAndSizeEdges[0]);
        int sizeEdges = Integer.parseInt(verticesAndSizeEdges[1]);

        CycleSearch cycleSearch = new CycleSearch(vertices);

        for (int i = 0; i < sizeEdges; i++) {
            String[] edge = reader.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            cycleSearch.addEdge(u, v);
        }

        cycleSearch.cycleGraph();


    }

}
