package first_semester;

import java.io.*;
import java.util.*;

public class QueueStore {

    // теперь возвращаем List<Long>, а не Integer — чтобы не было переполнения
    public List<Long> decision(List<String> stringList) {

        List<Long> results = new ArrayList<>();
        // ёмкость очереди сверху ограничиваем количеством строк
        QueueHelp queue = new QueueHelp(stringList.size());

        for (String s : stringList) {
            String[] parser = s.split(" ");
            if (parser.length == 0) continue;

            if ("1".equals(parser[0])) {
                long a = Long.parseLong(parser[1]);
                queue.push(a);

            } else if ("2".equals(parser[0])) {
                long x = Long.parseLong(parser[1]);
                long y = Long.parseLong(parser[2]);
                queue.addAnger(x, y);

            } else if ("3".equals(parser[0])) {
                results.add(queue.pop());
            }
        }

        return results;
    }

    // Внутренняя структура очереди переделана под O(1)
    private static class QueueHelp {

        private final long[] base;  // базовая агрессия
        private int head = 0;       // индекс первого
        private int tail = 0;       // индекс следующей свободной позиции
        private long addAll = 0;    // глобальная добавка всем через y
        private long firstExtra = 0;// доп. добавка текущему первому

        public QueueHelp(int capacity) {
            base = new long[capacity];
        }

        // 1 a — добавить в конец очереди
        public void push(long a) {
            // новый человек не должен получать прошлые y-запросы
            base[tail++] = a - addAll;
        }

        // 3 — первый уходит, вернуть его итоговую агрессию
        public long pop() {
            long res = base[head++] + addAll + firstExtra;
            // новый первый не наследует спец-добавку предыдущего
            firstExtra = 0;


            return res;
        }

        // 2 x y — ссора с кассиром
        public void addAnger(long x, long y) {
            int size = tail - head;
            if (size == 1) {
                // один человек: только он получает +x
                firstExtra += x;
            } else {
                // всем по y
                addAll += y;
                // текущему первому дополнительно (x - y)
                firstExtra += (x - y);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine().trim());
        QueueStore queueStore = new QueueStore();
        List<String> inputList = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            inputList.add(reader.readLine());
        }

        List<Long> results = queueStore.decision(inputList);

        StringBuilder sb = new StringBuilder();
        for (Long result : results) {
            sb.append(result).append('\n');
        }
        System.out.print(sb);
    }
}
