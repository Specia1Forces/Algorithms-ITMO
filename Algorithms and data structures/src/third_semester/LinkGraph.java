package third_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LinkGraph {
    public ArrayList<Integer> used;

    private final List<List<Integer>> listAdjacency;

    private final List<List<Integer>> listComponents = new ArrayList<>();

    private final List<Integer> components = new ArrayList<>();

    public LinkGraph(int vert) {
        used = new ArrayList<>(vert);
        for (int i = 0; i < vert; i++) {
            used.add(-1);

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
        listAdjacency.get(destination).add(source);
    }


    public void dfs(int v, int comp) {
        used.set(v, comp);
        components.add(v);
        for (int i = 0; i < listAdjacency.get(v).size(); i++) {
            if (used.get(listAdjacency.get(v).get(i)) == -1) {
                dfs(listAdjacency.get(v).get(i), comp);
            }
        }
    }

    public void findConnectedComponents() {
        for (int v = 0; v < listAdjacency.size(); v++) {
            if (used.get(v) == -1) {
                dfs(v, v);
            }
            if (!components.isEmpty()) {
                listComponents.add(new ArrayList<>(components));
                //System.out.println(components);
                components.clear();
            }
        }
    }

    public void linkComponents() {
        System.out.println(listComponents.size() - 1);
        for (int v = 1; v < listComponents.size(); v++) {
            System.out.print((listComponents.get(v - 1).getFirst() + 1) + " " + (listComponents.get(v).getFirst() + 1));
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] verticesAndSizeEdges = reader.readLine().split(" ");
        int vertices = Integer.parseInt(verticesAndSizeEdges[0]);
        int sizeEdges = Integer.parseInt(verticesAndSizeEdges[1]);

        LinkGraph LinkGraph = new LinkGraph(vertices);

        for (int i = 0; i < sizeEdges; i++) {
            String[] edge = reader.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            LinkGraph.addEdge(u, v);
        }

        LinkGraph.findConnectedComponents();

        //connectivityComponents.printGraph();

        LinkGraph.linkComponents();


    }
}
