import util.FileReader;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11 {
    static long result = 0;
    static List<Monkey> monkeys = new ArrayList<>();
    static Map<Integer, Integer> compteur = new HashMap<>();

    public static void main(String[] args) throws Exception {

        List<String> inputs = FileReader.read(11);

        System.out.println(reponse1(inputs));
//        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {

        // parsing
        parsing(inputs);

        // traitement
        for (int i = 0; i < 20; i++) {
            traitement(true);
        }

        List<Integer> listResult = new ArrayList<>(compteur.values());
        Collections.sort(listResult, Collections.reverseOrder());

        return listResult.get(0) * listResult.get(1);
    }

    private static long reponse2(List<String> inputs) {

        // parsing
        parsing(inputs);

        // traitement
        for (int i = 0; i < 10000; i++) {
            traitement(false);
        }

        List<Integer> listResult = new ArrayList<>(compteur.values());
        Collections.sort(listResult, Collections.reverseOrder());

        return (long) listResult.get(0) * listResult.get(1);
    }

    static void traitement(boolean divByThree) {
        for (int m = 0; m < monkeys.size(); m++) {
            for (int i = 0; i <  monkeys.get(m).items.size(); i++) {
                // step 1 : worry level
                monkeys.get(m).items.set(i, doOperation(monkeys.get(m).items.get(i), monkeys.get(m)));

                // step 2: division by 3
                long val = divByThree ? monkeys.get(m).items.get(i) / 3 : monkeys.get(m).items.get(i);
                monkeys.get(m).items.set(i, val);
                // step 3: le test
                if (val % monkeys.get(m).valueTest == 0) {
                    // true
                    monkeys.get(monkeys.get(m).monkeyIfTrue).items.add(val);
                } else {
                    // false
                    monkeys.get(monkeys.get(m).monkeyIfFalse).items.add(val);
                }

                // Compteur
                compteur.merge(m, 1, Integer::sum);
            }
            monkeys.get(m).items.clear();
        }
    }

    private static void parsing(List<String> inputs) {
        for (int i = 0; i < inputs.size(); i = i + 7) {
            Monkey monkey = new Monkey();

            // id
            String[] arrayLine = inputs.get(i).split(" ");
            String[] arrayValues = arrayLine[1].split("");
            monkey.id = Integer.parseInt(arrayValues[0]);

            // items
            String values = inputs.get(i + 1).replace("  Starting items: ", "");
            arrayValues = values.split(", ");
            for (String s: arrayValues) {
                monkey.items.add(Long.parseLong(s));
            }

            // Operation
            values = inputs.get(i + 2).replace("  Operation: new = ", "");
            arrayValues = values.split(" ");
            monkey.ope = Operateur.getOperateur(arrayValues[1]);
            monkey.valueOpe = arrayValues[2].equals("old") ? -1 : Integer.parseInt(arrayValues[2]);

            // Test
            values = inputs.get(i + 3).replace("  Test: divisible by ", "");
            monkey.valueTest = Integer.parseInt(values);

            // Si true
            values = inputs.get(i + 4).replace("    If true: throw to monkey ", "");
            monkey.monkeyIfTrue = Integer.parseInt(values);

            // Si false
            values = inputs.get(i + 5).replace("    If false: throw to monkey ", "");
            monkey.monkeyIfFalse = Integer.parseInt(values);

            monkeys.add(monkey);
        }
    }

    private static long doOperation(long item, Monkey monkey) {
        switch (monkey.ope) {
            case PLUS -> {
                if (monkey.valueOpe > 0) {
                    return item + monkey.valueOpe;
                } else {
                    return item + item;
                }
            }
            case MOINS -> {
                if (monkey.valueOpe > 0) {
                    return item - monkey.valueOpe;
                } else {
                    return 0;
                }
            }
            case MULTIPLIER -> {
                if (monkey.valueOpe > 0) {
                    return item * monkey.valueOpe;
                } else {
                    return item * item;
                }
            }
            case DIVISER -> {
                if (monkey.valueOpe > 0) {
                    return item / monkey.valueOpe;
                } else {
                    return 1;
                }
            }
        }
        throw new ArithmeticException();
    }

    static class Monkey {
        long id;
        List<Long> items = new ArrayList<>();
        Operateur ope;
        long valueOpe;
        long valueTest;
        int monkeyIfTrue;
        int monkeyIfFalse;

        public Monkey() {

        }
    }

    enum Operateur {
        PLUS,
        MOINS,
        MULTIPLIER,
        DIVISER;

        public static Operateur getOperateur(String s) {
            return switch (s) {
                case "+" -> PLUS;
                case "-" -> MOINS;
                case "*" -> MULTIPLIER;
                case "/" -> DIVISER;
                default -> PLUS;
            };
        }
    }
}
