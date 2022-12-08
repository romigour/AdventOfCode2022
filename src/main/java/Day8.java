import util.FileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day8 {

    public static void main(String[] args) throws Exception {

        List<String> inputs = FileReader.read(8);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {
        int result = 0;
        int[][] jungle = new int[inputs.size()][inputs.get(0).length()];

        for (int y = 0; y < inputs.size(); y++) {
            String[] inputArray = inputs.get(y).split("");
            for (int x = 0; x < inputArray.length; x++) {
                jungle[y][x] = Integer.parseInt(inputArray[x]);
            }
        }


        for (int y = 0; y < jungle.length; y++) {
            for (int x = 0; x < jungle[y].length; x++) {

                int arbre = jungle[y][x];

                boolean isVisible = true;

                // Gauche
                int a = x - 1;
                int b = y;
                while (a >= 0 && isVisible) {
                    if (arbre <= jungle[b][a])
                        isVisible = false;
                    a--;
                }
                if (isVisible) {
                    result++;
                    continue;
                }

                // Droite
                a = x + 1;
                b = y;
                isVisible = true;
                while (a < jungle[y].length && isVisible) {
                    if (arbre <= jungle[b][a])
                        isVisible = false;
                    a++;
                }
                if (isVisible) {
                    result++;
                    continue;
                }


                // Haut
                a = x;
                b = y - 1;
                isVisible = true;
                while (b >= 0 && isVisible) {
                    if (arbre <= jungle[b][a])
                        isVisible = false;
                    b--;
                }
                if (isVisible) {
                    result++;
                    continue;
                }

                // Bas
                a = x;
                b = y + 1;
                isVisible = true;
                while (b < jungle.length && isVisible) {
                    if (arbre <= jungle[b][a])
                        isVisible = false;
                    b++;
                }
                if (isVisible) {
                    result++;
                }
            }
        }

        return result;
    }

    private static int reponse2(List<String> inputs) {

        int result = 0;
        int[][] jungle = new int[inputs.size()][inputs.get(0).length()];

        for (int y = 0; y < inputs.size(); y++) {
            String[] inputArray = inputs.get(y).split("");
            for (int x = 0; x < inputArray.length; x++) {
                jungle[y][x] = Integer.parseInt(inputArray[x]);
            }
        }

        for (int y = 0; y < jungle.length; y++) {
            for (int x = 0; x < jungle[y].length; x++) {

                int arbre = jungle[y][x];

                int gauche = 0;
                int droite = 0;
                int haut = 0;
                int bas = 0;

                boolean isVisible = true;

                // Gauche
                int a = x - 1;
                int b = y;
                while (a >= 0 && isVisible) {
                    if (arbre <= jungle[b][a])
                        isVisible = false;
                    a--;
                    gauche++;
                }

                // Droite
                a = x + 1;
                b = y;
                isVisible = true;
                while (a < jungle[y].length && isVisible) {
                    if (arbre <= jungle[b][a])
                        isVisible = false;
                    a++;
                    droite++;
                }

                // Haut
                a = x;
                b = y - 1;
                isVisible = true;
                while (b >= 0 && isVisible) {
                    if (arbre <= jungle[b][a])
                        isVisible = false;
                    b--;
                    haut++;
                }

                // Bas
                a = x;
                b = y + 1;
                isVisible = true;
                while (b < jungle.length && isVisible) {
                    if (arbre <= jungle[b][a])
                        isVisible = false;
                    b++;
                    bas++;
                }

                int val = gauche * droite * haut * bas;
                if (val > result) {
                    result = val;
                }
            }
        }

        return result;
    }
}
