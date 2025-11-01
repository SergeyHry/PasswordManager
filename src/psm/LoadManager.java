package psm;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoadManager {

    public static List<PasswordEntry> load() {
        List<PasswordEntry> list = new ArrayList<>();

        try {
            for (String line : Files.readAllLines(Paths.get("passwords.txt"))) {
                if (line != null && line.trim().length() > 0) {  // <-- Java 8 kompatibel
                    list.add(PasswordEntry.fromString(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Keine gespeicherte Datei gefunden.");
        }

        return list;
    }
}
