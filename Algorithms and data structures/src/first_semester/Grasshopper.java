package first_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Grasshopper {
    List<Integer> dp = new ArrayList<>();

    public int decisionDp(List<Character> coordinates) {
        // Инициализируем dp значениями -1
        for (int i = 0; i < coordinates.size(); i++) {
            dp.add(-1);
        }
        dp.set(0, 0); // Начинаем с первой клетки

        for (int i = 1; i < coordinates.size(); i++) {
            if (coordinates.get(i) == 'w') {
                dp.set(i, -1); // нельзя использовать
                continue;
            }
            int sum = dpCalculation(i);
            if (coordinates.get(i) == '"' && sum != -1) {
                dp.set(i, 1 + sum);
            } else {
                dp.set(i, sum);
            }
        }
        return dp.getLast();
    }

    private int dpCalculation(int i) {
        int sum = 0;
        int count = 0;
        if ((i - 1) >= 0 && dp.get(i - 1) != -1) {
            sum = Math.max(sum, dp.get(i - 1));
            count++;
        }
        if ((i - 3) >= 0 && dp.get(i - 3) != -1) {
            sum = Math.max(sum, dp.get(i - 3));
            count++;
        }
        if ((i - 5) >= 0 && dp.get(i - 5) != -1) {
            sum = Math.max(sum, dp.get(i - 5));
            count++;
        }

        if (count == 0) {
            sum = -1;
        }
        return sum;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        String input = reader.readLine();

        // Преобразуем строку в список символов
        List<Character> coordinates = new ArrayList<>();
        for (char c : input.toCharArray()) {
            coordinates.add(c);
        }

        Grasshopper grasshopper = new Grasshopper();
        int result = grasshopper.decisionDp(coordinates);

        System.out.println(result);
    }
}
