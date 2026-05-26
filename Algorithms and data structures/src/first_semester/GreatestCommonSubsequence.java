package first_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GreatestCommonSubsequence {

    public void greatestCommonSubsequence(int[] arr1, int[] arr2) {
        int[][] lcs = new int[arr1.length + 1][arr2.length + 1];

        for (int i = 0; i <= arr1.length; i++) {
            lcs[i][0] = 0;
        }


        for (int j = 0; j <= arr2.length; j++) {
            lcs[0][j] = 0;
        }

        for (int i = 1; i < lcs.length; i++) {
            for (int j = 1; j < lcs[0].length; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }

            }
        }
        List<Integer> list = new ArrayList<>();
        int i = lcs.length - 1;
        int j = lcs[0].length - 1;
        while (i > 0 && j > 0) {
            if (arr1[i - 1] == arr2[j - 1]) { //arr1[i - 1] == arr2[j - 1]
                list.add(arr1[i - 1]);
                i--;
                j--;
            } else {
                if (lcs[i][j] == lcs[i - 1][j]) {
                    i--;
                } else {
                    j--;
                }
            }
        }

        list = list.reversed();
        System.out.println(list.size());
        for (int k = 0; k < list.size(); k++) {
            System.out.print(list.get(k) + " ");
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine().trim());
        String[] firstSequenceInput = reader.readLine().trim().split(" ");
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(firstSequenceInput[i]);
        }

        int m = Integer.parseInt(reader.readLine().trim());
        String[] secondSequenceInput = reader.readLine().trim().split(" ");
        int[] arr2 = new int[m];
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(secondSequenceInput[i]);
        }


        GreatestCommonSubsequence gcs = new GreatestCommonSubsequence();

        gcs.greatestCommonSubsequence(arr1, arr2);


    }
}
