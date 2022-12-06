import util.FileReader;

import java.util.*;

public class Day5 {

    public static void main(String[] args) throws Exception {

        List<String> inputs = FileReader.read(5);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static String reponse1(List<String> inputs) {
        StringBuilder result = new StringBuilder();
        Map<Integer, List<String>> piles = new HashMap<>();
        List<Move> moves = new ArrayList<>();
        boolean isMove = false;
        for (String input: inputs) {
            if ("".equals(input)) {
                isMove = true;
            } else if (isMove) {
                String[] moveArray = input.split(" ");
                moves.add(new Move(Integer.parseInt(moveArray[1]), Integer.parseInt(moveArray[3]), Integer.parseInt(moveArray[5])));
            } else {
                for (int i = 0; i < 9; i++) {
                    if (input.length() < 1 + i*4)
                        break;
                    piles.computeIfAbsent(i, k -> new Stack<>());
                    String caisse = input.substring(1 + i*4, 2 + i*4);
                    if (!" ".equals(caisse)) {
                        piles.get(i).add(caisse);
                    }
                }
            }
        }

        for (List<String> pile: piles.values()) {
            pile.remove(pile.size() - 1);
        }

        for (Move move: moves) {
            for (int i = 0; i < move.quantity; i++) {
                String caisse = piles.get(move.source - 1).get(0);
                piles.get(move.source - 1).remove(caisse);

                List<String> existInPile = new ArrayList<>(piles.get(move.cible - 1));
                piles.get(move.cible - 1).clear();
                piles.get(move.cible - 1).add(caisse);
                piles.get(move.cible - 1).addAll(existInPile);
            }
        }

        for (List<String> pile: piles.values()) {
            result.append(pile.get(0));
        }
        return result.toString();
    }

    private static String reponse2(List<String> inputs) {
        StringBuilder result = new StringBuilder();
        Map<Integer, List<String>> piles = new HashMap<>();
        List<Move> moves = new ArrayList<>();
        boolean isMove = false;
        for (String input: inputs) {
            if ("".equals(input)) {
                isMove = true;
            } else if (isMove) {
                String[] moveArray = input.split(" ");
                moves.add(new Move(Integer.parseInt(moveArray[1]), Integer.parseInt(moveArray[3]), Integer.parseInt(moveArray[5])));
            } else {
                for (int i = 0; i < 9; i++) {
                    if (input.length() < 1 + i*4)
                        break;
                    piles.computeIfAbsent(i, k -> new Stack<>());
                    String caisse = input.substring(1 + i*4, 2 + i*4);
                    if (!" ".equals(caisse)) {
                        piles.get(i).add(caisse);
                    }
                }
            }
        }

        for (List<String> pile: piles.values()) {
            pile.remove(pile.size() - 1);
        }

        for (Move move: moves) {
            List<String> caissesMove = new ArrayList<>();
            for (int i = 0; i < move.quantity; i++) {
                caissesMove.add(piles.get(move.source - 1).get(0));
                piles.get(move.source - 1).remove(caissesMove.get(caissesMove.size() - 1));
            }

            List<String> existInPile = new ArrayList<>(piles.get(move.cible - 1));
            piles.get(move.cible - 1).clear();
            piles.get(move.cible - 1).addAll(caissesMove);
            piles.get(move.cible - 1).addAll(existInPile);
        }

        for (List<String> pile: piles.values()) {
            result.append(pile.get(0));
        }
        return result.toString();
    }

    static class Move {
        int quantity;
        int source;
        int cible;

        public Move(int quantity, int source, int cible) {
            this.quantity = quantity;
            this.source = source;
            this.cible = cible;
        }
    }
}
