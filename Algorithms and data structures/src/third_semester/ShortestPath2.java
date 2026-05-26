package third_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShortestPath2 {
    public ArrayList<Integer> used;

    private final List<List<Pair>> listAdjacency;
    private final List<Integer> listD;


    public ShortestPath2(int vert) {
        used = new ArrayList<>(vert);
        listD = new ArrayList<>(vert);
        for (int i = 0; i < vert; i++) {
            used.add(-1);
            listD.add(Integer.MAX_VALUE);
        }
        listD.set(0, 0);
        listAdjacency = new ArrayList<>(vert);
        for (int i = 0; i < vert; i++) {
            listAdjacency.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        source = source - 1;
        destination = destination - 1;
        // Добавляем ребро от source к destination
        listAdjacency.get(source).add(new Pair(destination, weight));
        // Добавляем ребро от destination к source
        listAdjacency.get(destination).add(new Pair(source, weight));
    }

    public void dijkstraAlgorithm() {
        for (int i = 0; i < listD.size(); i++) {
            int minIdx = minD();

            if (listD.get(minIdx) == Integer.MAX_VALUE) {
                break;
            }



            for (int to = 0; to < listAdjacency.get(minIdx).size(); to++) {
                int min = Math.min(listD.get(listAdjacency.get(minIdx).get(to).vertex), listD.get(minIdx) + listAdjacency.get(minIdx).get(to).weight);
                listD.set(listAdjacency.get(minIdx).get(to).vertex, min);
            }

        }
    }

    public void print() {
        for (int i = 0; i < listD.size(); i++) {
            System.out.print(listD.get(i) + " ");
        }
    }

    public int minD() {
        // int min = listD.getFirst();
        int idxMin = -1;
        for (int i = 0; i < listD.size(); i++) {
            if (used.get(i) == -1 && (idxMin == -1 || listD.get(i) < listD.get(idxMin))) {
                //System.out.println(i);
                idxMin = i;
            }
        }
        used.set(idxMin, 1);
        return idxMin;
    }

    private static class Pair {
        int vertex;
        int weight;

        Pair(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //System.out.println("Введите количество вершин и рёбер:");
        String[] firstLine = reader.readLine().split(" ");
        int vert = Integer.parseInt(firstLine[0]);
        int edges = Integer.parseInt(firstLine[1]);

        ShortestPath2 graph = new ShortestPath2(vert);

        //System.out.println("Введите рёбра (source destination):");
        for (int i = 0; i < edges; i++) {
            String[] edgeLine = reader.readLine().split(" ");
            int source = Integer.parseInt(edgeLine[0]);
            int destination = Integer.parseInt(edgeLine[1]);
            int weight = Integer.parseInt(edgeLine[2]); // Чтение веса ребра
            graph.addEdge(source, destination, weight);
        }
        graph.dijkstraAlgorithm(); // Запуск алгоритма Дейкстры
        graph.print();

    }
}
