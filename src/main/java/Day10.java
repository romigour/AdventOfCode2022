import util.FileReader;

import java.util.ArrayList;
import java.util.List;

public class Day10 {

    static int cycle = 0;
    static int x = 0;
    static int result = 0;

    static List<String> sprites  = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        List<String> inputs = FileReader.read(10);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {
        cycle = 0;
        x = 1;

        for (String input: inputs) {
            if ("noop".equals(input)) {
                // 1 cycle
                upCycle();
            } else {
                String[] inputArray = input.split(" ");
                if ("addx".equals(inputArray[0])) {
                    // 2 cycle
                    for (int i = 0; i < 2; i++) {
                        upCycle();

                    }
                    x += Integer.parseInt(inputArray[1]);
                }
            }
        }

        return result;
    }

    private static void upCycle() {
        cycle++;

        //20th, 60th, 100th, 140th, 180th, and 220th
        if (cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220) {
            result += cycle * x;
        }
    }
    private static int reponse2(List<String> inputs) {
        cycle = 0;
        x = 1;
        for (int i = 0; i < 6; i++) {
            sprites.add("");
        }

        for (String input: inputs) {
            if ("noop".equals(input)) {
                // 1 cycle
                draw();
                upCycle();
            } else {
                String[] inputArray = input.split(" ");
                if ("addx".equals(inputArray[0])) {
                    // 2 cycle
                    for (int i = 0; i < 2; i++) {
                        draw();
                        upCycle();
                    }
                    x += Integer.parseInt(inputArray[1]);
                }
            }
        }

        sprites.forEach(System.out::println);
        return result;
    }

    private static void draw() {
        if (cycle < 40) {
            if ((x - 1) == cycle || (x + 1) == cycle || x == cycle) {
                sprites.set(0, sprites.get(0) + "#");
            } else {
                sprites.set(0, sprites.get(0) + ".");
            }
        } else if (cycle < 80) {
            if ((x - 1) == (cycle - 40) || (x + 1) == (cycle - 40) || x == (cycle - 40)) {
                sprites.set(1, sprites.get(1) + "#");
            } else {
                sprites.set(1, sprites.get(1) + ".");
            }
        } else if (cycle < 120) {
            if ((x - 1) == (cycle - 80) || (x + 1) == (cycle - 80) || x == (cycle - 80)) {
                sprites.set(2, sprites.get(2) + "#");
            } else {
                sprites.set(2, sprites.get(2) + ".");
            }
        } else if (cycle < 160) {
            if ((x - 1) == (cycle - 120) || (x + 1) == (cycle - 120) || x == (cycle - 120)) {
                sprites.set(3, sprites.get(3) + "#");
            } else {
                sprites.set(3, sprites.get(3) + ".");
            }
        } else if (cycle < 200) {
            if ((x - 1) == (cycle - 160) || (x + 1) == (cycle - 160) || x == (cycle - 160)) {
                sprites.set(4, sprites.get(4) + "#");
            } else {
                sprites.set(4, sprites.get(4) + ".");
            }
        } else if (cycle < 240) {
            if ((x - 1) == (cycle - 200) || (x + 1) == (cycle - 200) || x == (cycle - 200)) {
                sprites.set(5, sprites.get(5) + "#");
            } else {
                sprites.set(5, sprites.get(5) + ".");
            }
        }
    }
}
