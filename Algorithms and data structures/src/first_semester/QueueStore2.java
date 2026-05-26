package first_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class QueueStore2 {

    public List<Long> decision(List<String> stringList) {
        List<Long> results = new ArrayList<>();
        QueueHelp queue = new QueueHelp();
        for (String s : stringList) {
            String[] parser = s.split(" ");
            if (Objects.equals(parser[0], "1")) {
                queue.push(Integer.parseInt(parser[1]));
            } else if (Objects.equals(parser[0], "2")) {
                queue.addAnger(Integer.parseInt(parser[1]), Integer.parseInt(parser[2]));
            } else if (Objects.equals(parser[0], "3")) {
                results.add(queue.pop());
            }
        }
        return results;
    }


    private static class QueueHelp {

        private long addAll = 0;    // глобальная добавка всем через y
        private long firstExtra = 0;// доп. добавка текущему первому
        private Node head = null;
        private Node tail = null;
        int size = 0;

        public void push(int n) {
            if (tail == null) {
                tail = head = new Node(n, null);
                size++;
            } else {
                Node cur = tail;
                tail = new Node(n - addAll, null);
                tail.addNext(cur);
                cur.addPrev(tail);
                size++;
            }
        }

        public long pop() {
            Node cur = head;
            long n = cur.getData();
            head = cur.prev;
            //head.deleteNext();
            if (head == null) { // Если очередь стала пустой
                tail = null; // Обнуляем хвост
            }
            size--;
            // прибавляем

            long res = n + addAll + firstExtra;
            firstExtra = 0;

            if (size == 0) {
                addAll = 0;

            }
            return res;

        }




        public void addAnger(int x, int y) {
            if (size == 1) {
                firstExtra += x;
            } else {

                addAll += y;

                firstExtra += (x - y);
            }

        }



        private static class Node {
            private long data;
            private Node next;
            private Node prev;

            Node(long data, Node prev) {
                this.data = data;
                this.prev = prev;
                this.next = null;
            }

            public long getData() {
                return data;
            }



            public void addNext(Node next) {
                this.next = next;
            }

            public void addPrev(Node prev) {
                this.prev = prev;
            }


        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        QueueStore2 queueStore2 = new QueueStore2();
        List<String> inputList = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            inputList.add(line);
        }

        List<Long> results = queueStore2.decision(inputList);

        for (Long result : results) {
            System.out.println(result);
        }
    }
}

