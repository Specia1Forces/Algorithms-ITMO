package third_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FordBellman {
    private final List<Integer> listD;
    private final List<Edge> edges;

    public FordBellman(int vert, List<String> listEdge) {
        listD = new ArrayList<>(vert);
        listD.add(0);
        for (int i = 1; i < vert; i++) {
            listD.add(Integer.MAX_VALUE);
        }
        edges = new ArrayList<>();
        for (String string : listEdge) {
            String[] strEdg = string.split(" ");
            edges.add(new Edge(Integer.parseInt(strEdg[0]) - 1, Integer.parseInt(strEdg[1]) - 1, Integer.parseInt(strEdg[2])));
        }

    }

    private static class Edge {
        int u;
        int v;
        int weight;

        Edge(int u, int v, int weight) {// a b
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public void fordBellman() {
        for (int i = 0; i < listD.size(); i++) {
            for (Edge edge : edges) {
                if (listD.get(edge.u) < Integer.MAX_VALUE) {
                    listD.set(edge.v, Math.min(listD.get(edge.v), listD.get(edge.u) + edge.weight));
                }
            }
        }

        for (int i = 0; i < listD.size(); i++) {
            if (listD.get(i) == Integer.MAX_VALUE) {
                listD.set(i, 30000);
            }

        }

    }

    public void print() {
        for (Integer integer : listD) {
            System.out.println(integer + " ");
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Чтение количества вершин и рёбер
        String[] firstLine = reader.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);

        // Чтение рёбер
        List<String> edgesList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            edgesList.add(reader.readLine());
        }

        // Создание экземпляра алгоритма
        FordBellman fordBellman = new FordBellman(N, edgesList);

        // Запуск алгоритма
        fordBellman.fordBellman();

        // Вывод результата
        fordBellman.print();
    }

}
