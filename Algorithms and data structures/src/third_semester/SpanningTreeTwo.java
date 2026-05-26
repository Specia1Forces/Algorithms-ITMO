package third_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SpanningTreeTwo {

    private final List<Edge> edges;

    List<Integer> parent;
    List<Integer> rank;

    long minSum = 0;

    public SpanningTreeTwo(int vert, List<String> listEdge) {
        parent = new ArrayList<>();
        rank = new ArrayList<>();
        for (int i = 0; i < vert; i++) {
            parent.add(i);
            rank.add(0);
        }

        edges = new ArrayList<>();
        for (String string : listEdge) {
            String[] strEdg = string.split(" ");
            edges.add(new Edge(Integer.parseInt(strEdg[0]) - 1, Integer.parseInt(strEdg[1]) - 1, Integer.parseInt(strEdg[2])));
        }
    }

    public static class Edge {
        int u;
        int v;
        int weight;

        Edge(int u, int v, int weight) {// a b
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public int findSet(int v) {
        if (v == parent.get(v)) {
            return v;
        }
        parent.set(v, findSet(parent.get(v)));
        return parent.get(v);
    }

    public boolean unionSet(int a, int b) {
        a = findSet(a);
        b = findSet(b);

        if (a != b) {
            if (rank.get(a) < rank.get(b)) {
                int temp = a;
                a = b;
                b = temp;
            }
            parent.set(b, a);
            if (Objects.equals(rank.get(a), rank.get(b))) {
                rank.set(a, rank.get(a) + 1);
            }
            return true;
        }
        return false;
    }

    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public void kruskal() {
        EdgeComparator edgeComparator = new EdgeComparator();
        edges.sort(edgeComparator);

        for (Edge edge : edges) {
            if (unionSet(edge.u, edge.v)) {
                minSum = minSum + edge.weight;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Чтение количества вершин и рёбер
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]); // количество вершин
        int m = Integer.parseInt(firstLine[1]); // количество рёбер

        List<String> listEdge = new ArrayList<>();

        // Чтение рёбер
        for (int i = 0; i < m; i++) {
            listEdge.add(reader.readLine());
        }

        // Создание экземпляра класса и выполнение алгоритма Краскала
        SpanningTreeTwo spanningTree = new SpanningTreeTwo(n, listEdge);
        spanningTree.kruskal();

        // Вывод результата
        System.out.println(spanningTree.minSum);

    }

}
