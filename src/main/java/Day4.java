import util.FileReader;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

public class Day4 {

    public static void main(String[] args) throws Exception {

        List<String> inputs = FileReader.read(4);

        System.out.println(reponse1(inputs));
//        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {
        int result = 0;
        for (String input: inputs.stream().filter(s -> !"".equals(s)).toList()) {

        }

        return result;
    }

    private static int reponse2(List<String> inputs) {
        int result = 0;
        for (int i = 0; i < inputs.size(); i = i + 3) {

        }

        return result;
    }
}
