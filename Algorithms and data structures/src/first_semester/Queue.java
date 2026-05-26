package first_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;

public class Queue {
    Node head = null;
    Node tail = null;
    int size = 0;

    public void push(int n) {
        if (tail == null) {
            tail = head = new Node(n, null);
            size++;
        } else {
            Node cur = tail;
            tail = new Node(n, null);
            tail.addNext(cur);
            cur.addPrev(tail);
            size++;
        }
        System.out.println("ok");
    }

    public int pop() {
        Node cur = head;
        int n = cur.getData();
        head = cur.prev;
        //head.deleteNext();
        if (head == null) { // Если очередь стала пустой
            tail = null; // Обнуляем хвост
        }
        size--;

        return n;

    }

    public int front() {
        Node cur = head;
        return cur.getData();
    }


    public void clear() {
        head = tail = null;
        size = 0;
        System.out.println("ok");
    }

    public void exitS() {
        System.out.println("bye");
        exit(0);
    }

    public int size() {
        return size;
    }

    private class Node {
        private int data;
        private Node next;
        private Node prev;

        Node(int data, Node prev) {
            this.data = data;
            this.prev = prev;
            this.next = null;
        }

        public int getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public void addNext(Node next) {
            this.next = next;
        }

        public void addPrev(Node prev) {
            this.prev = prev;
        }

        public void deleteNext() {
            this.next = null;
        }


    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Queue queue = new Queue(); // Создаем экземпляр Queue

        while (true) {
            String command = reader.readLine();
            String[] parts = command.split(" ");

            switch (parts[0]) {
                case "push":
                    if (parts.length == 2) { // Проверяем, что есть второй элемент
                        try {
                            int n = Integer.parseInt(parts[1]);
                            queue.push(n);
                        } catch (NumberFormatException e) {
                            System.out.println("error");
                        }
                    } else {
                        System.out.println("error");
                    }
                    break;

                case "pop":
                    try {
                        System.out.println(queue.pop());
                    } catch (IllegalStateException e) {
                        System.out.println("error");
                    }
                    break;

                case "front":
                    try {
                        System.out.println(queue.front());
                    } catch (IllegalStateException e) {
                        System.out.println("error");
                    }
                    break;

                case "size":
                    System.out.println(queue.size());
                    break;

                case "clear":
                    queue.clear();
                    break;

                case "exit":
                    queue.exitS();
                    break;

                default:
                    System.out.println("Unknown command");
            }
        }
    }
}