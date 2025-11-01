package psm;

public class PasswordEntry {

    private String website;
    private String login;
    private String password;

    public PasswordEntry(String website, String login, String password) {
        this.website = website;
        this.login = login;
        this.password = password;
    }

    public String getWebsite() {
        return website;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return website + ";" + login + ";" + password;
    }

    public static PasswordEntry fromString(String line) {
        String[] parts = line.split(";");
        return new PasswordEntry(parts[0], parts[1], parts[2]);
    }
}
