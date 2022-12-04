import util.FileReader;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

public class Day4 {

    public static void main(String[] args) throws Exception {

        List<String> inputs = FileReader.read(4);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {
        int result = 0;
        for (String input: inputs.stream().filter(s -> !"".equals(s)).toList()) {
            String[] pairs = input.split(",");

            String[] pair1 = pairs[0].split("-");
            String[] pair2 = pairs[1].split("-");

            if ((Integer.parseInt(pair1[0]) <= Integer.parseInt(pair2[0]) &&
                    Integer.parseInt(pair1[1]) >= Integer.parseInt(pair2[1])) ||
                (Integer.parseInt(pair1[0]) >= Integer.parseInt(pair2[0]) &&
                        Integer.parseInt(pair1[1]) <= Integer.parseInt(pair2[1]))) {
                result += 1;
            }
        }

        return result;
    }

    private static int reponse2(List<String> inputs) {
        int result = 0;
        for (String input: inputs.stream().filter(s -> !"".equals(s)).toList()) {
            String[] pairs = input.split(",");

            String[] pair1 = pairs[0].split("-");
            String[] pair2 = pairs[1].split("-");

            if ((Integer.parseInt(pair1[0]) <= Integer.parseInt(pair2[0]) &&
                    Integer.parseInt(pair1[1]) >= Integer.parseInt(pair2[0])) ||
                    (Integer.parseInt(pair1[0]) >= Integer.parseInt(pair2[0]) &&
                            Integer.parseInt(pair1[0]) <= Integer.parseInt(pair2[1]))) {
                result += 1;
            }
        }

        return result;
    }
}
