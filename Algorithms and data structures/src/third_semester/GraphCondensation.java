package third_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GraphCondensation {
    public ArrayList<Integer> used;

    private final List<List<Integer>> listAdjacency;
    private final List<List<Integer>> listTransportAdjacency;

    public List<Integer> listOrder;
    public List<Integer> componets;
    int tout = 0;

    public GraphCondensation(int vert) {
        listOrder = new ArrayList<>();
        componets = new ArrayList<>();
        used = new ArrayList<>(vert);
        for (int i = 0; i < vert; i++) {
            used.add(-1);
            componets.add(-1);
        }
        listAdjacency = new ArrayList<>(vert);
        listTransportAdjacency = new ArrayList<>(vert);
        for (int i = 0; i < vert; i++) {
            listAdjacency.add(new ArrayList<>());
            listTransportAdjacency.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        source = source - 1;
        destination = destination - 1;
        // Добавляем ребро от source к destination
        listAdjacency.get(source).add(destination);
        listTransportAdjacency.get(destination).add(source);

    }


    public void dfs1(int v) {
        used.set(v, 0);
        //components.add(v);
        for (int i = 0; i < listAdjacency.get(v).size(); i++) {
            if (used.get(listAdjacency.get(v).get(i)) == -1) {
                dfs1(listAdjacency.get(v).get(i));
            }
        }
        listOrder.add(v);
    }

    public void dfs2(int v, int comp) {
        used.set(v, 0);
        componets.set(v, comp);
        //components.add(v);
        for (int i = 0; i < listTransportAdjacency.get(v).size(); i++) {
            if (used.get(listTransportAdjacency.get(v).get(i)) == -1) {
                dfs2(listTransportAdjacency.get(v).get(i), comp);
            }
        }
        //listOrder.add(v);
    }

    public int condensation() {
        for (int v = 0; v < listAdjacency.size(); v++) {
            if (used.get(v) == -1) {
                dfs1(v);
            }
        }


        for (int i = 0; i < used.size(); i++) {
            used.set(i, -1);
        }

        int comp = 1;
        for (int i = listOrder.size() - 1; i >= 0; i--) {
            if (used.get(listOrder.get(i)) == -1) {
                dfs2(listOrder.get(i), comp);
                comp++;
            }
        }

        return comp - 1;
    }

    public void printComponets() {
        for (Integer componet : componets) {
            System.out.print(componet + " ");
        }

    }


    public void printGraph() {
        System.out.println("Список смежностей графа:");
        for (int i = 0; i < listAdjacency.size(); i++) {
            System.out.print("Вершина " + (i + 1) + ": ");
            for (Integer edge : listAdjacency.get(i)) {
                System.out.print(edge + 1 + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //System.out.println("Введите количество вершин и рёбер:");
        String[] firstLine = reader.readLine().split(" ");
        int vert = Integer.parseInt(firstLine[0]);
        int edges = Integer.parseInt(firstLine[1]);

        GraphCondensation graph = new GraphCondensation(vert);

        //System.out.println("Введите рёбра (source destination):");
        for (int i = 0; i < edges; i++) {
            String[] edgeLine = reader.readLine().split(" ");
            int source = Integer.parseInt(edgeLine[0]);
            int destination = Integer.parseInt(edgeLine[1]);
            graph.addEdge(source, destination);
        }

        //graph.printGraph();

        System.out.println(graph.condensation());
        graph.printComponets();
        //graph.printComponents();
    }
}
