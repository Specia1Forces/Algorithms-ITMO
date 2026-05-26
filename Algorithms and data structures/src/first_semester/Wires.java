package first_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Wires {

    public int binSearch(ArrayList<Integer> arrayL, int k) { // левосторонний бин
        int left = -1;
        int right = 0;
        for (int len : arrayL) {
            if (len > right) right = len + 1;
        }

        boolean flag = false;
        while (left < right - 1) {
            int mid = (left + right) / 2;

            if (isRightSegment(arrayL, k, mid)) {
                left = mid;
                flag = true;
            } else {
                right = mid;
            }
        }

        if (!flag) {
            return 0;
        }

        return  left;

    }

    public boolean isRightSegment(ArrayList<Integer> arrayL, int k, int mid) {
        if (mid == 0) {
            return false;
        }

        long count = 0;
        for (int temp : arrayL) {
            temp = temp / mid;
            count = count + temp;
        }

        if (count >= k) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputNAndK = reader.readLine().split(" ");
        int n = Integer.parseInt(inputNAndK[0]);
        int k = Integer.parseInt(inputNAndK[1]);

        ArrayList<Integer> arrayL = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int length = Integer.parseInt(reader.readLine());
            arrayL.add(length);
        }

        Wires wires = new Wires();
        int result = wires.binSearch(arrayL, k);

        System.out.println(result);

    }
}

