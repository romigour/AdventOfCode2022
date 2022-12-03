import util.FileReader;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

public class Day3 {

    public static void main(String[] args) throws Exception {

        List<String> inputs = FileReader.read(3);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {
        int result = 0;
        for (String input: inputs.stream().filter(s -> !"".equals(s)).toList()) {
            List<String> containers = List.of(input.split("(?<=\\G.{" + (input.length() / 2) + "})"));

            List<String> container1 = new ArrayList<>(List.of(containers.get(0).split("")));
            List<String> container2 = new ArrayList<>(List.of(containers.get(1).split("")));

            char l = container1.stream().filter(container2::contains)
                    .map(s -> s.charAt(0)).findFirst().orElseThrow();
            if (isUpperCase(l)) {
                l = toLowerCase(l);
                result += (int) l - 'a' + 1 + 26;
            } else {
                result += (int) l - 'a' + 1;
            }
        }

        return result;
    }

    private static int reponse2(List<String> inputs) {
        int result = 0;
        for (int i = 0; i < inputs.size(); i = i + 3) {
            List<String> container1 = new ArrayList<>(List.of(inputs.get(i).split("")));
            List<String> container2 = new ArrayList<>(List.of(inputs.get(i+1).split("")));
            List<String> container3 = new ArrayList<>(List.of(inputs.get(i+2).split("")));

            char l = container1.stream()
                    .filter(container2::contains)
                    .filter(container3::contains)
                    .map(s -> s.charAt(0))
                    .findFirst()
                    .orElseThrow();
            if (isUpperCase(l)) {
                l = toLowerCase(l);
                result += (int) l - 'a' + 1 + 26;
            } else {
                result += (int) l - 'a' + 1;
            }
        }

        return result;
    }
}
