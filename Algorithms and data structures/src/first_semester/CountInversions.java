package first_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CountInversions {

    private long countInversions = 0;

    public long getCountInversions() {
        return countInversions;
    }

    public ArrayList<Integer> sort(ArrayList<Integer> array) {
        int size = array.size();

        if (size == 1) {
            return array;
        }

        ArrayList<Integer> array1 = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();

        for (int i = 0; i < size / 2; i++) {
            array1.add(array.get(i));
        }

        for (int i = size / 2; i < size; i++) {
            array2.add(array.get(i));
        }

        array1 = sort(array1);
        array2 = sort(array2);

        return mergeSort(array1, array2);
    }

    public ArrayList<Integer> mergeSort(ArrayList<Integer> array1, ArrayList<Integer> array2) {
        int size3 = array1.size() + array2.size();
        ArrayList<Integer> array3 = new ArrayList<>(size3);

        int j = 0;
        int k = 0;

        for (int i = 0; i < size3; i++) {
            if (j >= array1.size()) {
                array3.add(i, array2.get(k));
                k++;
                continue;
            }

            if (k >= array2.size()) {
                array3.add(i, array1.get(j));
                j++;
                continue;
            }


            if (array1.get(j) <= array2.get(k)) {
                array3.add(i, array1.get(j));
                j++;
            } else {
                array3.add(i, array2.get(k));
                countInversions += (array1.size() - j);
                k++;
            }
        }

        return array3;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        ArrayList<Integer> array = new ArrayList<>(n);

        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            array.add(Integer.parseInt(input[i]));
        }

        CountInversions ci = new CountInversions();
        ci.sort(array);


        System.out.println(ci.getCountInversions());
    }
}
/*
public class CountInversions {

    private int countInversions = 0;

    public int getCountInversions() {
        return countInversions;
    }

    public ArrayList<Integer> sort(ArrayList<Integer> array) {

        int size = array.size();

        if (size == 1) {

            return array;
        }

        ArrayList<Integer> array1 = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();

        for (int i = 0; i < size / 2; i++) {
            array1.add(array.get(i));
        }

        for (int i = size / 2; i < size; i++) {

            array2.add(array.get(i));
        }

        array1 = sort(array1);
        array2 = sort(array2);

        return mergeSort(array1, array2);
    }


    public ArrayList<Integer> mergeSort(ArrayList<Integer> array1, ArrayList<Integer> array2) {
        int size3 = array1.size() + array2.size();
        ArrayList<Integer> array3 = new ArrayList<>(size3);

        int j = 0;
        int k = 0;

        for (int i = 0; i < size3; i++) {


            if (j >= array1.size()) {
                array3.add(i, array2.get(k));
                k++;
                continue;
            }
            if (k >= array2.size()) {
                array3.add(i, array1.get(j));
                j++;
                continue;
            }


            if (array1.get(j) <= array2.get(k)) {
                array3.add(i, array1.get(j));
                j++;
            } else {
                array3.add(i, array2.get(k));
                countInversions += (array1.size() - j);
                k++;
            }

        }
        return array3;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //System.out.println("Введите количество элементов в массиве:");
        int n = Integer.parseInt(reader.readLine());

        ArrayList<Integer> array = new ArrayList<>();

        //System.out.println("Введите элементы массива:");
        String[] input = reader.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            array.add(Integer.parseInt(input[i]));
        }

        CountInversions countInversions = new CountInversions();

        ArrayList<Integer> sortedArray = countInversions.sort(array);

        System.out.println(countInversions.getCountInversions());




    }
}

 */
