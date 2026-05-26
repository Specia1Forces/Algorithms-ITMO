package third_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bridge {
    public ArrayList<Boolean> used;

    private final List<List<Pair>> listAdjacency;
    private final List<Integer> timeIn;
    private final List<Integer> fup;

    Set<Integer> set = new TreeSet<>();

    private int dfsTimer = 0;

    public Bridge(int vert) {
        listAdjacency = new ArrayList<>(vert);
        timeIn = new ArrayList<>(vert);
        used = new ArrayList<>();
        fup = new ArrayList<>();
        for (int i = 0; i < vert; i++) {
            listAdjacency.add(new ArrayList<>());
            timeIn.add(0);
            used.add(false);
            fup.add(Integer.MAX_VALUE);
        }
    }

    public void addEdge(int source, int destination, int edgeId) {
        source = source - 1;
        destination = destination - 1;
        // Добавляем ребро от source к destination
        // Добавляем ребро от destination к source


        listAdjacency.get(source).add(new Pair(destination, edgeId));
        listAdjacency.get(destination).add(new Pair(source, edgeId));
    }

    private static class Pair {
        int to;
        int edgeId;

        Pair(int to, int edgeId) {
            this.to = to;
            this.edgeId = edgeId;
        }
    }

    public void dfs(int v, int parentId) {
        used.set(v, true);
        int tempTimer = dfsTimer++;
        timeIn.set(v, tempTimer);
        fup.set(v, tempTimer);//Время fup[v] равно минимуму из времени захода в саму вершину
        for (Pair edge : listAdjacency.get(v)) {
            int to = edge.to;          // соседняя вершина
            int edgeId = edge.edgeId;  // номер ребра во входе
            if (edgeId == parentId) { //боремся так с кратными ребрами
                continue;
            }
            if (used.get(to) == false) {
                dfs(to, edgeId);
                fup.set(v, Math.min(fup.get(v), fup.get(to)));
                // fup[to] для каждой вершины to, являющейся непосредственным сыном v в дереве поиска
                if (fup.get(to) > timeIn.get(v)) { //fup[to] > tin[v], то это ребро является мостом; в противном случае оно мостом не является.
                    // cохраняем номер, pair
                    set.add(edgeId);
                }
            } else {
                fup.set(v, Math.min(fup.get(v), timeIn.get(to))); //времён захода в каждую вершину p, являющуюся концом некоторого обратного ребра (v,p)
            }
        }
    }

    public void findBridge() {
        for (int v = 0; v < listAdjacency.size(); v++) {
            if (used.get(v) == false) {
                dfs(v, -1);
            }
        }
    }

    public void print() {
        System.out.println(set.size());
        for (int id : set) {
            System.out.print(id + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]); // Количество вершин
        int m = Integer.parseInt(input[1]); // Количество рёбер

        Bridge bridgeFinder = new Bridge(n);

        for (int i = 0; i < m; i++) {
            input = reader.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            bridgeFinder.addEdge(u, v, i + 1);
        }

        bridgeFinder.findBridge();
        bridgeFinder.print();
    }
}
