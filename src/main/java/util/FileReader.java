package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileReader {

    public static List<String> read(int day) throws IOException {
        List<String> inputs = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("src\\main\\resources\\puzzle" + day + ".txt"), "UTF-8"))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                inputs.add(line);
            }
            return inputs;
        } finally {
            System.out.println("Erreur dans le parsing du fichier puzzle" + day + ".txt");
        }
    }
}
