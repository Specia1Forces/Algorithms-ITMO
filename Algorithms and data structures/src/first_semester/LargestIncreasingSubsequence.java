package first_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LargestIncreasingSubsequence {

    int[] dp;

    public List<Integer> subsequence(int[] array) {
        dp = new int[array.length];
        int[] p = new int[array.length];
        dp[0] = 1;
        p[0] = 0;


        for (int i = 1; i < array.length; i++) {
            int maxIdx = maxSub(array, i);
            if (array[maxIdx] < array[i]) {
                dp[i] = dp[maxIdx] + 1;
                p[i] = maxIdx;
            } else {
                dp[i] = 1;
                p[i] = i;
            }

            /*
            if ((dp[maxIdx] + 1) > 1) {
                dp[i] = dp[maxIdx] + 1;
                p[i] = maxIdx;
            }
             */
        }
        
        int max = dp[0];
        int finalMaxIdx = 0;
        int maxElement = array[0];
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] == max) {
                if (array[i] >= maxElement) {
                    max = dp[i];
                    finalMaxIdx = i;
                    maxElement = array[i];
                }
            } else if (dp[i] > max) {
                max = dp[i];
                finalMaxIdx = i;
                maxElement = array[i];
            }
        }
       
        int cur = finalMaxIdx;
        list.add(cur);
        while (cur != p[cur]) {
            cur = p[cur];
            list.add(cur);
        }

        return list.reversed();
    }

    private int maxSub(int[] array, int idx) {
        int maxIdx = 0;
        int max = dp[maxIdx];
        for (int j = 1; j < idx; j++) {
            if (array[j] < array[idx]) {
                if (dp[j] >= max) {
                    max = dp[j];
                    maxIdx = j;
                }
            }
        }
        return maxIdx;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine().trim());

        String[] input = reader.readLine().trim().split(" ");
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(input[i]);
        }

        LargestIncreasingSubsequence lisCalculator = new LargestIncreasingSubsequence();



        List<Integer> lis = lisCalculator.subsequence(array);

        System.out.println(lis.size()); // Длина наибольшей возрастающей подпоследовательности
        for (int num : lis) {
            System.out.print(array[num] + " "); // Самая наибольшая возрастающая подпоследовательность
        }


    }
}
