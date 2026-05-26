package third_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NegativeCycle {
    int[][] graph;
    long[][] dp;

    public NegativeCycle(int n) {

        graph = new int[n][n];
        dp = new long[n][n];
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

        dp[i][j] = weight;

    }

    public void floydDel() {

        boolean flag = false;
        for (int k = 0; k < graph.length; k++) {
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp.length; j++) {
                    if (dp[i][k] < Integer.MAX_VALUE && dp[k][j] < Integer.MAX_VALUE) {
                        //long min = dp[i][k] + dp[k][j];
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                    /*
                    if (i == j && dp[i][j] < 0) {
                        flag = true;
                    }

                     */
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            if (dp[i][i] < 0) {
                flag = true;
                break;
            }
        }

        if (flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
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

        NegativeCycle negativeCycle = new NegativeCycle(N);

        // Чтение ребер
        for (int i = 0; i < N; i++) {
            String[] edgeData = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(edgeData[j]) != 100000) {
                    negativeCycle.addEdge(i, j, Integer.parseInt(edgeData[j]));
                }
            }
        }

        // Запуск алгоритма Флойда-Уоршелла
        negativeCycle.floydDel();

        // Вывод матрицы кратчайших расстояний
        //  floyd.print();

    }
}
