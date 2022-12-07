import util.FileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day7 {

    public static void main(String[] args) throws Exception {

        List<String> inputs = FileReader.read(7);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {
        int result = 0;

        Disque disque = new Disque();

        traitement(disque, inputs);

        for (Repertoire rep: disque.repertoireKeep) {
            result += rep.size;
        }

        return result;
    }

    private static int reponse2(List<String> inputs) {

        Disque disque = new Disque();

        traitement(disque, inputs);

        int repSizeToDelete = Integer.MAX_VALUE;
        int sizeFree = 70000000 - disque.racine.size;
        int sizeNeeding = 30000000 - sizeFree;
        for (Repertoire rep: disque.repertoireKeep2) {
            if (repSizeToDelete > rep.size && rep.size > sizeNeeding) {
                repSizeToDelete = rep.size;
            }
        }

        return repSizeToDelete;
    }

    private static void traitement(Disque disque, List<String> inputs) {
        for (String input: inputs) {
            String[] lineArray = input.split(" ");


            if ("$".equals(lineArray[0])) {
                if ("cd".equals(lineArray[1]) && !"/".equals(lineArray[2])) {
                    // cd
                    if ("..".equals(lineArray[2])) {
                        disque.current = disque.current.parent;
                    } else {
                        disque.current = disque.current.repertoires.stream()
                                .filter(repertoire -> repertoire.name.equals(lineArray[2])).findFirst().orElseThrow();
                    }
                } else {
                    // ls ou cd /
                }
                // Commande
            } else if ("dir".equals(lineArray[0])) {
                // Répertoire
                Repertoire repertoire = new Repertoire();
                repertoire.name = lineArray[1];
                repertoire.parent = disque.current;
                disque.current.repertoires.add(repertoire);
            } else {
                // Fichier
                Fichier fichier = new Fichier();
                fichier.size = Integer.parseInt(lineArray[0]);
                fichier.name = lineArray[1];
                fichier.parent = disque.current;
                addFileSizeInHierarchy(disque, disque.current, fichier.size);
                disque.current.fichiers.add(fichier);
            }
        }
    }

    private static void addFileSizeInHierarchy(Disque disque, Repertoire currentRep, int sizeFile) {
        Repertoire current = currentRep;
        while (current != null) {
            current.size += sizeFile;

            // part 1
            if (current.size <= 100000) {
                disque.repertoireKeep.add(current);
            } else {
                disque.repertoireKeep.remove(current);
            }

            // part 2
            disque.repertoireKeep2.add(current);

            current = current.parent;
        }
    }



    static class Disque {
        Repertoire racine;
        Repertoire current;

        Set<Repertoire> repertoireKeep = new HashSet<>();
        Set<Repertoire> repertoireKeep2 = new HashSet<>();
        public Disque() {
            racine = new Repertoire();
            racine.name = "/";
            current = racine;
        }
    }
    static class Repertoire {
        String name;
        Repertoire parent;
        int size;
        List<Repertoire> repertoires = new ArrayList<>();
        List<Fichier> fichiers = new ArrayList<>();

        public Repertoire() {}
    }

    static class Fichier {
        int size;
        String name;
        Repertoire parent;

        public Fichier() {}
    }
}
