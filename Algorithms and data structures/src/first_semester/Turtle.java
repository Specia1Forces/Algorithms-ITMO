package first_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Turtle {

    List<ArrayList<Integer>> dp = new ArrayList<>();
    List<Character> path = new ArrayList<>();

    public void decision(ArrayList<ArrayList<Integer>> arrayCoordination) {
        int rows = arrayCoordination.size();
        int cols = arrayCoordination.getFirst().size();

        for (int i = 0; i < rows; i++) { // строки
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) { // столбцы
                row.add(0); // Заполняем нулями
            }
            dp.add(row);
        }

        dp.getFirst().set(0, arrayCoordination.getFirst().getFirst());


        for (int i = 1; i < cols; i++) {
            dp.getFirst().set(i, dp.getFirst().get(i - 1) + arrayCoordination.getFirst().get(i));
        }

        for (int i = 1; i < rows; i++) {
            dp.get(i).set(0, dp.get(i - 1).getFirst() + arrayCoordination.get(i).getFirst());
        }

        int temp;
        for (int i = 1; i < rows; i++) { // строки
            for (int j = 1; j < cols; j++) { // столбцы
                temp = arrayCoordination.get(i).get(j);
                dp.get(i).set(j, Math.max(temp + dp.get(i).get(j - 1), temp + dp.get(i - 1).get(j)));
            }
        }

        /*
        for (int i = 0; i < rows; i++) { // строки
            for (int j = 0; j < cols; j++) { // столбцы
                System.out.print(dp.get(i).get(j) + " ");
            }
            System.out.println();
        }

         */

        int i = rows - 1;
        int j = cols - 1;
        while (i >= 0 || j >= 0) {
            temp = arrayCoordination.get(i).get(j);
            /*
            if (i == 0 && j == 0) {
                break;
            }

             */
            if (i == 0 && j == 0) {
                break;
            }

            if (i > 0 && j > 0) {
                if (dp.get(i).get(j) - temp == dp.get(i).get(j - 1)) {
                    path.add('R');
                    //System.out.println(dp.get(i).get(j - 1));
                    j--;
                } else if (dp.get(i).get(j) - temp == dp.get(i - 1).get(j)) {
                    path.add('D');
                    //System.out.println(dp.get(i - 1).get(j));
                    i--;
                }
            } else {
                if (i == 0) {
                    path.add('R');
                    j--;
                } else {
                    path.add('D');
                    i--;
                }

            }
        }

        //dp.get(i).set(j, Math.max(temp + dp.get(i).get(j - 1), temp + dp.get(i - 1).get(j)));


        path = path.reversed();

    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Считываем размеры поля
        String[] sizes = reader.readLine().split(" ");
        int N = Integer.parseInt(sizes[0]);
        int M = Integer.parseInt(sizes[1]);

        // Инициализируем массив для хранения значений клеток
        ArrayList<ArrayList<Integer>> arrayCoordination = new ArrayList<>();

        // Считываем значения клеток
        for (int i = 0; i < N; i++) {
            String[] line = reader.readLine().split(" ");
            ArrayList<Integer> row = new ArrayList<>();
            for (String value : line) {
                row.add(Integer.parseInt(value));
            }
            arrayCoordination.add(row);
        }

        // Создаем объект черепахи и запускаем решение
        Turtle turtle = new Turtle();
        turtle.decision(arrayCoordination);

        // Выводим результат (максимальное количество монет)
        System.out.println(turtle.dp.get(N - 1).get(M - 1));
        for (int i = 0; i < turtle.path.size(); i++) {
            System.out.print(turtle.path.get(i));
        }
    }
}
