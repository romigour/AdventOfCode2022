import util.FileReader;

import java.util.*;

public class Day6 {

    public static void main(String[] args) throws Exception {

        List<String> inputs = FileReader.read(6);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {
        int result = -1;

        String input = inputs.get(0);
        for (int i = 0; i < input.length(); i++) {
            String start = input.substring(i, i + 4);
            if (isGood(start)) {
                return i + 4;
            }
        }
        return result;
    }

    private static int reponse2(List<String> inputs) {
        int result = -1;

        String input = inputs.get(0);
        for (int i = 0; i < input.length(); i++) {
            String start = input.substring(i, i + 14);
            if (isGood(start)) {
                return i + 14;
            }
        }
        return result;
    }

    private static boolean isGood(String chaine) {
        for (int i = 0; i < chaine.length(); i++) {
            for (int j = 0; j < chaine.length(); j++) {
                if (i != j && chaine.charAt(i) == chaine.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

}
