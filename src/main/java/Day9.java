import util.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9 {

    public static void main(String[] args) throws Exception {

        List<String> inputs = FileReader.read(9);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {

        List<Position> positionsQueue = new ArrayList<>();
        Position tete = new Position(0, 0);
        Position queue = new Position(0, 0);
        positionsQueue.add(queue.clone());
        List<Move> moves = new ArrayList<>();
        for (String input: inputs) {
            String[] moveArray = input.split(" ");
            Move move = new Move(moveArray[0], Integer.parseInt(moveArray[1]));
            moves.add(move);
        }

        for (Move move: moves) {
            if (move.direction.equals("R")) {
                // Right
                for (int i = 0; i < move.distance; i++) {
                    tete.x++;
                    if (calcDistance(tete, queue) > 1) {
                        if (tete.y != queue.y) {
                            queue.y = tete.y;
                            queue.x++;
                        } else {
                            queue.x++;
                        }
                        addPosition(positionsQueue, queue);
                    }
                }
            } else if (move.direction.equals("L")){
                // Left
                for (int i = 0; i < move.distance; i++) {
                    tete.x--;
                    if (calcDistance(tete, queue) > 1) {
                        if (tete.y != queue.y) {
                            queue.y = tete.y;
                            queue.x--;
                        } else {
                            queue.x--;
                        }
                        addPosition(positionsQueue, queue);
                    }
                }
            } else if (move.direction.equals("D")){
                // Down
                for (int i = 0; i < move.distance; i++) {
                    tete.y--;
                    if (calcDistance(tete, queue) > 1) {
                        if (tete.x != queue.x) {
                            queue.x = tete.x;
                            queue.y--;
                        } else {
                            queue.y--;
                        }
                        addPosition(positionsQueue, queue);
                    }
                }
            } else if (move.direction.equals("U")){
                // Up
                for (int i = 0; i < move.distance; i++) {
                    tete.y++;
                    if (calcDistance(tete, queue) > 1) {
                        if (tete.x != queue.x) {
                            queue.x = tete.x;
                            queue.y++;
                        } else {
                            queue.y++;
                        }
                        addPosition(positionsQueue, queue);
                    }
                }
            } else {
                throw new IllegalArgumentException();
            }
        }


        return positionsQueue.size();
    }


    private static int reponse2(List<String> inputs) {
        List<Position> positionsQueue = new ArrayList<>();
        List<Position> teteEtQueues = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            teteEtQueues.add(new Position(0, 0));
        }
        positionsQueue.add(teteEtQueues.get(9).clone());
        List<Move> moves = new ArrayList<>();
        for (String input : inputs) {
            String[] moveArray = input.split(" ");
            Move move = new Move(moveArray[0], Integer.parseInt(moveArray[1]));
            moves.add(move);
        }

        for (Move move : moves) {
            if (move.direction.equals("R")) {
                // Right
                for (int i = 0; i < move.distance; i++) {
                    teteEtQueues.get(0).x++;
                    for (int j = 1; j < 10; j++) {
                        if (calcDistance(teteEtQueues.get(j-1), teteEtQueues.get(j)) > 1) {
                            if (teteEtQueues.get(j-1).y != teteEtQueues.get(j).y) {
                                teteEtQueues.get(j).y = teteEtQueues.get(j-1).y > teteEtQueues.get(j).y ? teteEtQueues.get(j).y + 1 : teteEtQueues.get(j).y - 1;
                                if (teteEtQueues.get(j-1).x != teteEtQueues.get(j).x) {
                                    teteEtQueues.get(j).x = teteEtQueues.get(j-1).x > teteEtQueues.get(j).x ? teteEtQueues.get(j).x + 1 : teteEtQueues.get(j).x - 1;
                                }
                            } else {
                                teteEtQueues.get(j).x++;
                            }
                            if (j == 9) {
                                addPosition(positionsQueue, teteEtQueues.get(j));
                            }
                        }
                    }
                }
            } else if (move.direction.equals("L")) {
                // Left
                for (int i = 0; i < move.distance; i++) {
                    teteEtQueues.get(0).x--;
                    for (int j = 1; j < 10; j++) {
                        if (calcDistance(teteEtQueues.get(j-1), teteEtQueues.get(j)) > 1) {
                            if (teteEtQueues.get(j-1).y != teteEtQueues.get(j).y) {
                                teteEtQueues.get(j).y = teteEtQueues.get(j-1).y > teteEtQueues.get(j).y ? teteEtQueues.get(j).y + 1 : teteEtQueues.get(j).y - 1;
                                if (teteEtQueues.get(j-1).x != teteEtQueues.get(j).x) {
                                    teteEtQueues.get(j).x = teteEtQueues.get(j-1).x > teteEtQueues.get(j).x ? teteEtQueues.get(j).x + 1 : teteEtQueues.get(j).x - 1;
                                }
                            } else {
                                teteEtQueues.get(j).x--;
                            }
                            if (j == 9) {
                                addPosition(positionsQueue, teteEtQueues.get(j));
                            }
                        }
                    }
                }
            } else if (move.direction.equals("D")) {
                // Down
                for (int i = 0; i < move.distance; i++) {

                    teteEtQueues.get(0).y--;
                    for (int j = 1; j < 10; j++) {
                        if (calcDistance(teteEtQueues.get(j-1), teteEtQueues.get(j)) > 1) {
                            if (teteEtQueues.get(j-1).x != teteEtQueues.get(j).x) {
                                teteEtQueues.get(j).x = teteEtQueues.get(j-1).x > teteEtQueues.get(j).x ? teteEtQueues.get(j).x + 1 : teteEtQueues.get(j).x - 1;
                                if (teteEtQueues.get(j-1).y != teteEtQueues.get(j).y) {
                                    teteEtQueues.get(j).y = teteEtQueues.get(j-1).y > teteEtQueues.get(j).y ? teteEtQueues.get(j).y + 1 : teteEtQueues.get(j).y - 1;
                                }
                            } else {
                                teteEtQueues.get(j).y--;
                            }
                            if (j == 9) {
                                addPosition(positionsQueue, teteEtQueues.get(j));
                            }
                        }
                    }
                }
            } else if (move.direction.equals("U")) {
                // Up
                for (int i = 0; i < move.distance; i++) {
                    teteEtQueues.get(0).y++;
                    for (int j = 1; j < 10; j++) {
                        if (calcDistance(teteEtQueues.get(j-1), teteEtQueues.get(j)) > 1) {
                            if (teteEtQueues.get(j-1).x != teteEtQueues.get(j).x) {
                                teteEtQueues.get(j).x = teteEtQueues.get(j-1).x > teteEtQueues.get(j).x ? teteEtQueues.get(j).x + 1 : teteEtQueues.get(j).x - 1;
                                if (teteEtQueues.get(j-1).y != teteEtQueues.get(j).y) {
                                    teteEtQueues.get(j).y = teteEtQueues.get(j-1).y > teteEtQueues.get(j).y ? teteEtQueues.get(j).y + 1 : teteEtQueues.get(j).y - 1;
                                }
                            } else {
                                teteEtQueues.get(j).y++;
                            }
                            if (j == 9) {
                                addPosition(positionsQueue, teteEtQueues.get(j));
                            }
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        return positionsQueue.size();
    }

    private static void print(List<Position> positions) {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            String line = "";
            for (int j = 0; j < 26; j++) {
                String point = ".";
                for (int p = 0; p < positions.size(); p++) {
                    if ((positions.get(p).x + 15) == j && (positions.get(p).y + 11) == i && ".".equals(point)) {
                        point = "" + p;
                    }
                }
                line = line + point;
            }
            lines.add(line);
        }

        Collections.reverse(lines);
        lines.forEach(System.out::println);
        System.out.println();
        System.out.println("################################");
        System.out.println();
    }

    private static int calcDistance(Position tete, Position queue) {
        return (int) Math.sqrt((queue.y-tete.y) * (queue.y-tete.y) + (queue.x-tete.x) * (queue.x-tete.x));
    }

    private static void addPosition(List<Position> positions, Position queue) {
        if (positions.stream().noneMatch(p -> p.x == queue.x && p.y == queue.y)) {
            positions.add(queue.clone());
        }
    }

    static class Move {
        String direction;
        int distance;

        public Move(String direction, int distance) {
            this.direction = direction;
            this.distance = distance;
        }
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position clone() {
            return new Position(this.x, this.y);
        }
    }
}
