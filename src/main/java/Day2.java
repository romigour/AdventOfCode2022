import util.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day2 {

    public static void main(String[] args) throws Exception {

        List<String> inputs = FileReader.read(2);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) throws Exception {
        int result = 0;
        for (String input: inputs.stream().filter(s -> !"".equals(s)).toList()) {
            String[] play = input.replaceAll("[AX]", "1")
                    .replaceAll("[BY]", "2")
                    .replaceAll("[CZ]", "3")
                    .split(" ");

            result += score(Integer.parseInt(play[0]), Integer.parseInt(play[1]));
        }

        return result;
    }

    private static int reponse2(List<String> inputs) {
        int result = 0;
        for (String input: inputs.stream().filter(s -> !"".equals(s)).toList()) {

            String[] play = input.replace("A", "1")
                    .replace("B", "2")
                    .replace("C", "3")
                    .replace("X", "0")
                    .replace("Y", "3")
                    .replace("Z", "6")
                    .split(" ");

            result += score2(Integer.parseInt(play[0]), Integer.parseInt(play[1]));
        }

        return result;
    }

    private static int score2(int opp, int result) {
        int me;
        if (result == 0) {
            me = opp - 1 > 0 ? opp - 1 : 3;
        } else if (result == 3) {
            me = opp;
        } else {
            me = opp + 1 < 4 ? opp + 1 : 1;
        }
        return me + result;
    }

    private static int score(int opp, int me) throws Exception{
        int diff = me - opp;
        if (diff == 0)
            return 3 + me;
        if (diff == 1)
            return 6 + me;
        if (diff == 2)
            return me;
        if (diff == -2)
            return 6 + me;
        if (diff == -1)
            return me;
        throw new Exception("CA MARCHE PAS ICI!!!");
    }
}
