package first_semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class LevenshteinDistance {


    public int levenshtein(String str1, String str2) {
        char[] charStr1 = str1.toCharArray(); // строки
        char[] charStr2 = str2.toCharArray();  // столбцы

        int[][] dmArray = new int[charStr1.length + 1][charStr2.length + 1];

        for (int i = 1; i <= charStr1.length; i++) {
            dmArray[i][0] = i;
        }

        for (int j = 1; j <= charStr2.length; j++) {
            dmArray[0][j] = j;
        }

        for (int i = 1; i < dmArray.length; i++) {
            for (int j = 1; j < dmArray[0].length; j++) {
                int min1 = dmArray[i][j - 1] + 1;
                int min2 = dmArray[i - 1][j] + 1;
                int min3 = dmArray[i - 1][j - 1] + equal(charStr1[i - 1], charStr2[j - 1]);

                dmArray[i][j] = Math.min(min1, Math.min(min2, min3));
            }
        }


        //print(dmArray);
        return dmArray[charStr1.length][charStr2.length];
    }

    private int equal(char char1, char char2) {
        if (char1 == char2) {
            return 0;
        }
        return 1;

    }

    public void print(int[][] dmArray) {
        for (int i = 0; i < dmArray.length; i++) {
            for (int j = 0; j < dmArray[0].length; j++) {
                System.out.print(dmArray[i][j]);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String str1 = reader.readLine().trim();

        String str2 = reader.readLine().trim();

        LevenshteinDistance ld = new LevenshteinDistance();

        int distance = ld.levenshtein(str1, str2);

        System.out.println(distance);
    }
}
