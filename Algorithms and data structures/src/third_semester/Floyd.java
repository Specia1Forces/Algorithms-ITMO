package third_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Floyd {

    int[][] graph;
    int[][] dp;

    public Floyd(int n) {

        graph = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            graph[i][i] = 0;
            dp[i][i] = 0;
        }
    }

    public void addEdge(int i, int j, int weight) {
        if (weight < dp[i][j]) {
            dp[i][j] = weight;
        }
    }

    public void floydDel() {

        for (int k = 0; k < graph.length; k++) {
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp.length; j++) {
                    if (dp[i][k] < Integer.MAX_VALUE && dp[k][j] < Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) {
                    dp[i][j] = 30000;
                }
            }
        }


    }

    public void print() {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

// Чтение количества вершин и ребер
        String[] firstLine = reader.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);

        Floyd floyd = new Floyd(N);

        // Чтение ребер
        for (int i = 0; i < M; i++) {
            String[] edgeData = reader.readLine().split(" ");
            int u = Integer.parseInt(edgeData[0]) - 1; // Приводим к индексу от 0
            int v = Integer.parseInt(edgeData[1]) - 1; // Приводим к индексу от 0
            int weight = Integer.parseInt(edgeData[2]);
            floyd.addEdge(u, v, weight);
        }

        // Запуск алгоритма Флойда-Уоршелла
        floyd.floydDel();

        // Вывод матрицы кратчайших расстояний
        floyd.print();

    }

}
