package third_semester;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Graph {
    private final int vertices; // Количество вершин
    private final List<List<Integer>> adjacencyList; // Список смежностей

    // Конструктор
    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);

        // Инициализация списка смежностей
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    // Метод для добавления ребра в граф
    public void addEdge(int source, int destination) {
        // Добавляем ребро от source к destination
        adjacencyList.get(source).add(destination);
        // Добавляем ребро от destination к source (для неориентированного графа)
        adjacencyList.get(destination).add(source);
    }

    // Метод для отображения списка смежностей
    public void printGraph() {
        System.out.println("Список смежностей графа:");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Вершина " + i + ": ");
            for (Integer edge : adjacencyList.get(i)) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Читаем количество вершин и количество рёбер
        int vertices = scanner.nextInt();
        int edges = scanner.nextInt();

        Graph graph = new Graph(vertices); // Создаем граф с заданным количеством вершин

        // Читаем рёбра
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph.addEdge(source, destination);
        }

        graph.printGraph(); // Выводим список смежностей

        scanner.close(); // Закрываем сканер
    }
}

