package third_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ConnectivityComponents {
    public ArrayList<Integer> used;

    private final List<List<Integer>> listAdjacency;

    private final List<List<Integer>> ListComponents = new ArrayList<>();

    private final List<Integer> components = new ArrayList<>();

    public ConnectivityComponents(int vert) {
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
                ListComponents.add(new ArrayList<>(components));
                //System.out.println(components);
                components.clear();
            }
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

    public void printConnectedComponents() {
        System.out.println(ListComponents.size());
        for (int i = 0; i < ListComponents.size(); i++) {
            System.out.println(ListComponents.get(i).size());
            for (int j = 0; j < ListComponents.get(i).size(); j++) {
               System.out.print(ListComponents.get(i).get(j) + 1 + " ");
            }
            System.out.println();
        }
    }

    /*
    public void printConnectedComponents() {
        System.out.println("Количество компонент связности: " + hashAdjacency.size());

        Set<Integer> set = hashAdjacency.keySet();

        for (Integer comp : set) {
            System.out.print("Компонента " + comp + ": ");

            // Для хранения вершин текущей компоненты
            List<Integer> componentVertices = new ArrayList<>();

            for (int v = 0; v < used.size(); v++) {
                if (used.get(v) == comp) {
                    componentVertices.add(v + 1); // Добавляем 1 для отображения индексации с 1
                    used.set(v, -1); // Помечаем вершину как посещенную
                }
            }

            // Выводим вершины текущей компоненты
            System.out.println(componentVertices);
        }
    }


     */

    /*
    public void printConnectedComponents() {
        //System.out.println(used.toString());
        System.out.println(hashAdjacency.size());
        //System.out.println(hashAdjacency.toString());

        Set<Integer> set = hashAdjacency.keySet();

        for (Integer use : set) {
            System.out.println(hashAdjacency.get(use));

            for (int v = 0; v < listAdjacency.size(); v++) {
                if (Objects.equals(used.get(v), use)) {
                    System.out.print(v + 1 + " ");
                    used.set(v, -1);
                } else {
                    continue;
                }
                //System.out.println( "Cписок смежности для v =" +v +"lIST");
                //System.out.println( listAdjacency.get(v));
                for (int u = 0; u < listAdjacency.get(v).size(); u++) {
                    if (Objects.equals(used.get(listAdjacency.get(v).get(u)), use)) {
                       // System.out.print(" я ТУТ ");
                        System.out.print(listAdjacency.get(v).get(u) + 1 + " ");
                        used.set(listAdjacency.get(v).get(u), -1);
                    }
                }

            }

            System.out.println();


        }

    }

     */


    public static void main(String[] args) throws IOException {
        /*
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        int sizeEdges = sc.nextInt();

        ConnectivityComponents c = new ConnectivityComponents(vertices);

        for (int i = 0; i < sizeEdges; i++) {
            c.addEdge(sc.nextInt(), sc.nextInt());
        }

         */

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] verticesAndSizeEdges = reader.readLine().split(" ");
        int vertices = Integer.parseInt(verticesAndSizeEdges[0]);
        int sizeEdges = Integer.parseInt(verticesAndSizeEdges[1]);

        ConnectivityComponents connectivityComponents = new ConnectivityComponents(vertices);

        for (int i = 0; i < sizeEdges; i++) {
            String[] edge = reader.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            connectivityComponents.addEdge(u, v);
        }

        connectivityComponents.findConnectedComponents();

        //connectivityComponents.printGraph();

        connectivityComponents.printConnectedComponents();


    }
}
