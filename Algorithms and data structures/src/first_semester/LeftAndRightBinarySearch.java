package first_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class LeftAndRightBinarySearch {
    public int rightBinSearch(ArrayList<Integer> array, int element) { // правосторонний бин
        int left = -1;
        int right = array.size();

        while (left < right - 1) {
            int mid = (left + right) / 2;

            if (array.get(mid) <= element) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public int leftBinSearch(ArrayList<Integer> array, int element) { // левосторонний бин
        int left = -1;
        int right = array.size();
        while (left < right - 1) {
            int mid = (left + right) / 2;
            if (array.get(mid) < element) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return right;
    }

    public void binSearch(ArrayList<Integer> array1, ArrayList<Integer> array2) {

        for (int i = 0; i < array2.size(); i++) {
            int left = leftBinSearch(array1, array2.get(i));
            int right = rightBinSearch(array1, array2.get(i));
            boolean flag = false;
            if (left >= 0 && left < array1.size()) {
                if (Objects.equals(array1.get(left), array2.get(i))) {
                    System.out.print(left + 1 + " ");
                    flag = true;
                }
            }

            if (right >= 0 && right < array1.size()) {
                if (Objects.equals(array1.get(right), array2.get(i))) {
                    System.out.print(right + 1 + " ");
                    flag = true;
                }
            }
            if (!flag) {
                System.out.print(0);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        String[] inputNAndM = reader.readLine().split(" ");
        int N = Integer.parseInt(inputNAndM[0]);
        int M = Integer.parseInt(inputNAndM[1]);


        ArrayList<Integer> array1 = new ArrayList<>();
        String[] firstList = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            array1.add(Integer.parseInt(firstList[i]));
        }


        ArrayList<Integer> array2 = new ArrayList<>();
        String[] secondList = reader.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            array2.add(Integer.parseInt(secondList[i]));
        }

        LeftAndRightBinarySearch search = new LeftAndRightBinarySearch();
        search.binSearch(array1, array2);
    }
}
