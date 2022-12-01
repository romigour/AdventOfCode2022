import util.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(1);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {

        List<Integer> values = new ArrayList<>();
        values.add(0);
        for (String input: inputs) {
            if ("".equals(input)) {
                values.add(0);
            } else {
                values.set(values.size() - 1, values.get(values.size() - 1) + Integer.parseInt(input));
            }
        }

        return Collections.max(values);
    }

    private static int reponse2(List<String> inputs) {

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

        return (max1 + max2 + max3);
    }
}
