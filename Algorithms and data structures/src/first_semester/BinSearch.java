package first_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class BinSearch {

    public int binSearch(ArrayList<Integer> array, int element) { // левосторонний бин
        int left = -1;
        int right = array.size();

        while (right > left + 1) {
            int mid = (left + right) / 2;

            if (array.get(mid) >= element) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //System.out.println("Введите количество элементов в массиве:");
        String[] inputNAndK = reader.readLine().split(" ");

        int n = Integer.parseInt(inputNAndK[0]);
        int k = Integer.parseInt(inputNAndK[1]);

        ArrayList<Integer> array1 = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();

        //System.out.println("Введите элементы массива:");
        String[] input1 = reader.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            array1.add(Integer.parseInt(input1[i]));
        }

        String[] input2 = reader.readLine().split(" ");


        for (int i = 0; i < k; i++) {
            array2.add(Integer.parseInt(input2[i]));
        }


        BinSearch bs = new BinSearch();

        int findElement = 0;
        for (int i = 0; i < array2.size(); i++) {
            findElement = bs.binSearch(array1, array2.get(i));

            if (findElement >= 0 && findElement < array1.size()) {
                if (Objects.equals(array1.get(findElement), array2.get(i))) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }

            }

            else {
                System.out.println("NO");

            }



        }

    }
}
