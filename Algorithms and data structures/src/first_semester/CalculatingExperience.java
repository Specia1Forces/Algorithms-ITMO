package first_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CalculatingExperience {

    List<Integer> parent;
    List<Integer> experience;
    List<Integer> rank;

    public CalculatingExperience(int n) {
        parent = new ArrayList<>();
        experience = new ArrayList<>();
        rank = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            parent.add(i);
            experience.add(0);
            rank.add(0);
        }
    }


    public int findSet(int v) {
        if (v == parent.get(v)) {
            return v;
        }
        parent.set(v, findSet(parent.get(v)));
        return parent.get(v);
    }

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

    public void add(int a, int exp) {
        a = findSet(a - 1);
        for (int i = 0; i < parent.size(); i++) {
            if (findSet(i) == a && parent.get(i) == a) {
                experience.set(i, experience.get(i) + exp);
            }
        }
    }

    public int get(int v) {
        v = v - 1;
        return experience.get(v);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken()); // количество игроков
        int m = Integer.parseInt(st.nextToken()); // количество запросов

        CalculatingExperience ce = new CalculatingExperience(n);
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < m; i++) {
            String command = reader.readLine().trim();
            st = new StringTokenizer(command);
            String action = st.nextToken();

            switch (action) {
                case "join":
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    ce.unionSet(x, y);
                    break;

                case "add":
                    int playerId = Integer.parseInt(st.nextToken());
                    int value = Integer.parseInt(st.nextToken());
                    ce.add(playerId, value);
                    break;

                case "get":
                    output.append(ce.get(Integer.parseInt(st.nextToken()))).append("\n");
                    break;
            }
        }

        System.out.print(output);
        reader.close();
    }
}

/*

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CalculatingExperience {

    private final int[] parent;
    private final int[] rank;
    private final int[] clan;   // общий опыт клана (по корню)
    private final int[] diff;   // смещение игрока относительно корня

    public CalculatingExperience(int n) {
        parent = new int[n];
        rank = new int[n];
        clan = new int[n];
        diff = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            clan[i] = 0;
            diff[i] = 0;
        }
    }

    private int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        int p = parent[v];
        int root = find(p);
        diff[v] += diff[p];  // накапливаем смещение до нового родителя (корня)
        parent[v] = root;
        return root;
    }

    public void join(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a == b) return;

        // union by rank
        if (rank[a] < rank[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        parent[b] = a;
        // сделать опыт всех игроков клана b корректным относительно нового корня a
        diff[b] = clan[b] - clan[a];

        if (rank[a] == rank[b]) {
            rank[a]++;
        }
    }

    public void add(int x, int v) {
        int r = find(x);
        clan[r] += v;
    }

    public int get(int x) {
        int r = find(x);
        return clan[r] + diff[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken()); // количество игроков
        int m = Integer.parseInt(st.nextToken()); // количество запросов

        CalculatingExperience ce = new CalculatingExperience(n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String line = reader.readLine();
            if (line == null || line.isEmpty()) {
                i--;
                continue;
            }
            st = new StringTokenizer(line);
            String cmd = st.nextToken();

            switch (cmd) {
                case "join": {
                    int x = Integer.parseInt(st.nextToken()) - 1; // в 0‑based
                    int y = Integer.parseInt(st.nextToken()) - 1;
                    ce.join(x, y);
                    break;
                }
                case "add": {
                    int x = Integer.parseInt(st.nextToken()) - 1;
                    int v = Integer.parseInt(st.nextToken());
                    ce.add(x, v);
                    break;
                }
                case "get": {
                    int x = Integer.parseInt(st.nextToken()) - 1;
                    sb.append(ce.get(x)).append('\n');
                    break;
                }
            }
        }

        out.print(sb.toString());
        out.flush();
    }
}
 */
