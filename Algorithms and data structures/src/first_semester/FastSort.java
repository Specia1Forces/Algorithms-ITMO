package first_semester;

import java.util.ArrayList;
import java.util.List;

public class FastSort {

    void fast_sort(List<Integer> a) {
        Short sizeMode = Short.MAX_VALUE - 1;
        int sizeVector = a.size();
        List<Integer> bArray = new ArrayList<>();
        for (int i = 0; i < sizeMode; i++) {
            bArray.add((0));
        }

        for (int i = 0; i < sizeVector; i++) {
            bArray.set(a.get(i) % sizeMode, a.get(i));
        }

        int k = 0;


    }
}
