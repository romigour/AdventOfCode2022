import util.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdventOfCode {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(1);

        System.out.println(traitement(inputs));
    }

    private static String traitement(List<String> inputs) {

        int sumGlobal = 0;
        int maxValue = 0;
        int currentValue = 0;
        List<Integer> values = new ArrayList<>();
        values.add(0);
        for (String input: inputs) {
            if ("".equals(input)) {
                values.add(0);
            } else {
                values.set(values.size() - 1, values.get(values.size() - 1) + Integer.parseInt(input));
            }
        }

        Integer max1 = Collections.max(values);
        values.remove(max1);

        Integer max2 = Collections.max(values);
        values.remove(max2);

        Integer max3 = Collections.max(values);
        values.remove(max3);

        return "" + (max1 + max2 + max3);
    }
}
