import util.FileReader;

import java.util.*;

public class Day11 {
    static int result = 0;
    static List<Monkey> monkeys = new ArrayList<>();
    static Map<Integer, Integer> compteur = new HashMap<>();

    public static void main(String[] args) throws Exception {

        List<String> inputs = FileReader.read(11);

        System.out.println(reponse1(inputs));
//        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {

        // parsing
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
                monkey.items.add(Integer.parseInt(s));
                compteur.merge(monkey.id, 1, Integer::sum);
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

        // traitement
        for (int i = 0; i < 20; i++) {
            traitement();
        }

        // Mais POURQUOI je suis obligé de faire ça ?
        for (Map.Entry<Integer, Integer> entry : compteur.entrySet()) {
            entry.setValue(entry.getValue() - monkeys.get(entry.getKey()).items.size());
        }

        List<Integer> listResult = new ArrayList<>(compteur.values());
        Collections.sort(listResult, Collections.reverseOrder());

        return listResult.get(0) * listResult.get(1);
    }

    private static int reponse2(List<String> inputs) {

        for (String input: inputs) {

        }
        return result;
    }

    static void traitement() {
        for (int m = 0; m < monkeys.size(); m++) {
            for (int i = 0; i <  monkeys.get(m).items.size(); i++) {
                // step 1 : worry level
                monkeys.get(m).items.set(i, doOperation(monkeys.get(m).items.get(i), monkeys.get(m)));

                // step 2: division by 3
                monkeys.get(m).items.set(i, Math.round(monkeys.get(m).items.get(i) / 3));

                // step 3: le test
                int val = monkeys.get(m).items.get(i);
                if (val % monkeys.get(m).valueTest == 0) {
                    // true
                    monkeys.get(monkeys.get(m).monkeyIfTrue).items.add(val);
                    compteur.merge(monkeys.get(m).monkeyIfTrue, 1, Integer::sum);
                } else {
                    // false
                    monkeys.get(monkeys.get(m).monkeyIfFalse).items.add(val);
                    compteur.merge(monkeys.get(m).monkeyIfFalse, 1, Integer::sum);

                }
            }
            monkeys.get(m).items.clear();
        }
    }

    private static int doOperation(int item, Monkey monkey) {
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
        int id;
        List<Integer> items = new ArrayList<>();
        Operateur ope;
        int valueOpe;
        int valueTest;
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
