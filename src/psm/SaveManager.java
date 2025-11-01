package psm;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveManager {

    public static void save(List<PasswordEntry> entries) {
        try (FileWriter fw = new FileWriter("passwords.txt")) {
            for (PasswordEntry p : entries) {
                fw.write(p.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
