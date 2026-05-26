package first_semester;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class PriorityQueueWithDeletion {
    private int[] heap;
    private int heapSize = 0;

    PriorityQueueWithDeletion(int n) {
        heap = new int[n + 1];

    }

    public int siftUp(int i) {

        if (heap[i] <= heap[i / 2]) {
            return i;
        }

        while (i > 1 && heap[i] > heap[i / 2]) { // пока сын больше отца , если отец и сын равны, то двигаться дальше не надо
            swap(i, i / 2); //  i/2 потому что наверх поднимаемся
            i = i / 2;
        }

        return i;
    }

    public int siftDown(int i) {
        while (2 * i <= heapSize) {
            int l = 2 * i;
            int r = 2 * i + 1;

            int u;
            if (r <= heapSize && heap[r] > heap[l]) { // выбираем найбольший
                u = r;
            } else {
                u = l;
            }

            if (heap[i] >= heap[u]) { // если отец и сын равны, то не нужно дальше двигаться вниз
                break;
            }

            swap(i, u);
            i = u;

        }
        return i;
    }

    public int add(int val) {
        if (heapSize + 1 != heap.length) {
            heapSize = heapSize + 1;
            heap[heapSize] = val;
            return siftUp(heapSize);
        }
        return -1;
    }

    public Pair getMax() {
        if (heapSize >= 1) {
            int temp = heap[1];
            heap[1] = heap[heapSize];
            heapSize = heapSize - 1;
            int idx = siftDown(1);
            if (heapSize == 0) {
                idx = 0;
            }
            return new Pair(idx, temp);
        }
        return new Pair(-1, 0);
    }

    public int delete(int i) {
        if (i < 1 || i > heapSize) {
            return -1; // элемента с таким индексом нет
        }
        int removed = heap[i];
        if (i == heapSize) {
            // Удаляем последний элемент – просто уменьшаем размер
            heapSize--;
            return removed;
        }
        // Переносим последний элемент на место i
        heap[i] = heap[heapSize];
        heapSize--;
        // Восстанавливаем кучу
        if (i > 1 && heap[i] > heap[i / 2]) {
            // Новый элемент больше родителя – просеиваем вверх
            siftUp(i);
        } else {
            // Иначе – вниз
            siftDown(i);
        }
        return removed;
        /*
        if (i <= 0 && i > heapSize) {
            return -1;
        }
        if (heapSize == 1) {
            heapSize = 0;
            return heap[1];
        }

        int temp = heap[1];
        heap[1] = heap[i];
        heap[i] = temp;

        temp = heap[1];
        heap[1] = heap[heapSize];
        heapSize = heapSize - 1;

        siftDown(1);
        siftUp(i);
        return temp;

         */
    }


    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public record Pair(int idx, int val) {
    }

    public void print() {
        for (int i = 1; i <= heapSize; i++) {
            System.out.print(heap[i] + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        String[] firstLine = reader.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);

        PriorityQueueWithDeletion pq = new PriorityQueueWithDeletion(N);

        for (int i = 0; i < M; i++) {
            String[] query = reader.readLine().split(" ");
            int queryType = Integer.parseInt(query[0]);

            switch (queryType) {
                case 1: // Извлечь максимальный элемент
                    Pair result = pq.getMax();
                    if (result.idx == -1) {
                        writer.println(-1);
                    } else {
                        writer.println(result.idx + " " + result.val);
                    }

                    break;

                case 2: // Добавить элемент
                    int valueToAdd = Integer.parseInt(query[1]);
                    int indexAdded = pq.add(valueToAdd);
                    writer.println(indexAdded);
                    break;

                case 3: // Удалить элемент по индексу
                    int indexToDelete = Integer.parseInt(query[1]);
                    int deletedValue = pq.delete(indexToDelete);
                    writer.println(deletedValue);
                    break;

                default:
                    break;
            }
        }
        writer.flush();
        pq.print();
        writer.close();
        reader.close();
    }
}
