package first_semester;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SegmentsWithLargeAmount {

    private int[] prefixSum;
    private int[] array;
    private long count = 0;

    public SegmentsWithLargeAmount(int[] array, int n) {
        this.array = array;
        prefixSum = new int[n];
        prefixSum[0] = array[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = array[i] + prefixSum[i - 1];
        }
    }

    public void sumK(long k) {


        for (int i = 0; i < prefixSum.length; i++) {
            if (array[i] >= k) {
                count++;
            }
        }

        Arrays.sort(prefixSum);

        for (int i = 0; i < prefixSum.length; i++) {
            for (int j = i + 1; j < prefixSum.length; j++) {
                long segmentSum = prefixSum[j] - (i == 0 ? 0 : prefixSum[i - 1]);
                if (segmentSum >= k) {
                    count++;
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        long k = Long.parseLong(firstLine[1]);

        String[] secondLine = reader.readLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(secondLine[i]);
        }

        SegmentsWithLargeAmount segments = new SegmentsWithLargeAmount(array, n);
        segments.sumK(k);

        System.out.println(segments.count);

    }
}
