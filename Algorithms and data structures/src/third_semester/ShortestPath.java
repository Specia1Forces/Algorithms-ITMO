package third_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class ShortestPath {
    public ArrayList<Boolean> used;

    private final List<List<Pair>> listAdjacency;

    private List<Integer> topSort;
    private int[] dp;


    public ShortestPath(int vert) {
        topSort = new ArrayList<>();
        used = new ArrayList<>();

        dp = new int[vert];
        listAdjacency = new ArrayList<>(vert);
        for (int i = 0; i < vert; i++) {
            dp[i] = Integer.MAX_VALUE;
            used.add(false);
            listAdjacency.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        source = source - 1;
        destination = destination - 1;
        // Добавляем ребро от source к destination
        listAdjacency.get(source).add(new Pair(destination, weight));
        // Добавляем ребро от destination к source

    }

    private static class Pair {
        int vertex;
        int weight;

        Pair(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }


    public void dfs(int v) {
        used.set(v, true);

        for (int i = 0; i < listAdjacency.get(v).size(); i++) {
            if (used.get(listAdjacency.get(v).get(i).vertex) == false) {
                dfs(listAdjacency.get(v).get(i).vertex);
            }
        }
        topSort.add(v);
    }

    public void topSort(int s) {
        dfs(s);
        Collections.reverse(topSort);
    }


    public void shortPath(int s, int v) {
        topSort(s);
        dp[s] = 0;
        for (int i = 0; i < topSort.size(); i++) {

            for (int j = 0; j < listAdjacency.get(topSort.get(i)).size(); j++) {
                dp[listAdjacency.get(topSort.get(i)).get(j).vertex] = Math.min(dp[listAdjacency.get(topSort.get(i)).get(j).vertex],
                        dp[topSort.get(i)] + listAdjacency.get(topSort.get(i)).get(j).weight);
            }
        }

        if (dp[v] == Integer.MAX_VALUE) {
            System.out.println("Unreachable");
        } else {
            System.out.println(dp[v]);
        }
    }

    public void print() {
        for (int i = topSort.size() - 1; i >= 0; i--) {
            System.out.print(topSort.get(i) + 1);
            System.out.print(" ");
        }
    }

    public void printGraph() {
        System.out.println("Список смежностей графа:");
        for (int i = 0; i < listAdjacency.size(); i++) {
            System.out.print("Вершина " + i + ": ");
            for (Pair edge : listAdjacency.get(i)) {
                System.out.print(edge.vertex + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken()); // количество вершин
        int m = Integer.parseInt(st.nextToken()); // количество дуг
        int s = Integer.parseInt(st.nextToken()); // стартовая вершина (1-based)
        int t = Integer.parseInt(st.nextToken()); // конечная вершина (1-based)
        ShortestPath graph = new ShortestPath(n);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int b = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.addEdge(b, e, w);
        }
       //graph.printGraph();
        // внутри класса индексация 0-based, но метод shortPath ожидает уже 0-based индексы
        graph.shortPath(s - 1, t - 1);
    }
}
