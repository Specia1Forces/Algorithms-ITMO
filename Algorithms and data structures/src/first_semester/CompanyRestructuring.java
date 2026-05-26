package first_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompanyRestructuring {
    private ArrayList[] lst;
    List<Integer> parent;
    //List<Integer> rank;

    public CompanyRestructuring(int n) {
        parent = new ArrayList<>();
        lst = new ArrayList[n];
        // rank = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            parent.add(i);
            lst[i] = new ArrayList<>();
            //  rank.add(0);
        }
    }

    public int findSet(int v) {
        if (v == parent.get(v)) {
            return v;
        }
        parent.set(v, findSet(parent.get(v)));
        return parent.get(v);
    }



    /*
    public void unionSet(int a, int b) {
        a = a - 1;
        b = b - 1;
        a = findSet(a);
        b = findSet(b);

        if (a != b) {
            if (rank.get(a) < rank.get(b)) {
                int temp = a;
                a = b;
                b = temp;
            }
            parent.set(b, a);
            if (Objects.equals(rank.get(a), rank.get(b))) {
                rank.set(a, rank.get(a) + 1);
            }
        }
    }

     */

    /*
    public void unionSetList(int a, int b) {



        int i = a + 1, j = b;
        int mod = 0;
        while ((i - j) <= 0) {
            if (mod % 2 == 0) {
                if (!Objects.equals(parent.get(a - 1), parent.get(j - 1))) {
                    unionSet(a, j);
                }
                j--;
                mod = 1;
            } else {
                if (!Objects.equals(parent.get(a - 1), parent.get(i - 1))) {
                    unionSet(a, i);
                }
                i++;
                mod = 0;
            }
        }
    }
    */

    public void unionSetList(int a, int b) {



        int i = a + 1, j = b;
        int mod = 0;
        while ((i - j) <= 0) {
            if (mod % 2 == 0) {
                if (!Objects.equals(parent.get(a - 1), parent.get(j - 1))) {
                    unionSet(a, j);
                }
                j--;
                mod = 1;
            } else {
                if (!Objects.equals(parent.get(a - 1), parent.get(i - 1))) {
                    unionSet(a, i);
                }
                i++;
                mod = 0;
            }
        }
    }

    public void unionSet(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a != b) {
            if (lst[a].size() < lst[b].size()) {
                int temp = a;
                a = b;
                b = temp;
            }
            while (!lst[b].isEmpty()) {
                int v = (int) lst[b].remove(lst[b].size() - 1);
                parent.set(v, a);
                lst[a].add(v);
            }
        }
    }


    /*
    public void unionSet2(int a, int b) {
        a = a - 1;
        b = b - 1;
        a = findSet(a);
        b = findSet(b);

        if (a != b) {
            if (rank.get(a) < rank.get(b)) {
                int temp = a;
                a = b;
                b = temp;
            }
            parent.set(b, a);
            if (Objects.equals(rank.get(a), rank.get(b))) {
                rank.set(a, rank.get(a) + 1);
            }
        }
    }

     */


    public boolean employeeWorksDepartment(int a, int b) {
        a = a - 1;
        b = b - 1;
        a = findSet(a);
        b = findSet(b);
        return a == b;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]); // Количество сотрудников
        int q = Integer.parseInt(firstLine[1]); // Количество запросов

        CompanyRestructuring company = new CompanyRestructuring(n);

        for (int i = 0; i < q; i++) {
            String[] query = reader.readLine().split(" ");
            int type = Integer.parseInt(query[0]);
            int x = Integer.parseInt(query[1]);
            int y = Integer.parseInt(query[2]);

            switch (type) {
                case 1:
                    // Объединение двух сотрудников в одну группу
                    company.unionSet(x, y);
                    break;
                case 2:
                    // Объединение группы сотрудников от x до y
                    company.unionSetList(x, y);
                    break;
                case 3:
                    // Проверка, работают ли сотрудники x и y в одном департаменте
                    boolean result = company.employeeWorksDepartment(x, y);
                    System.out.println(result ? "YES" : "NO");
                    break;
                default:
                    System.out.println("Invalid query type");
                    break;
            }
        }
    }
}
