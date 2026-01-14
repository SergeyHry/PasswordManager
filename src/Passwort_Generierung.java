import java.lang.Math;

public class Passwort_Generierung {
    public static String Generierung () {
        int endWithString = 122; // 'z'
        int startWithString = 65; // 'A'
        int count = 0;
        StringBuilder Passwort =  new StringBuilder();

        while (count < 6) {
            int randomInt = (int) (Math.random() * (endWithString - startWithString + 1)) + startWithString;

            if (randomInt > 90 && randomInt < 97) {
                continue;
            }

            Passwort.append((char) randomInt);
            count++;
        }
        int endWithSymbole =64;
        int startWithSymbole = 33;
        int count2 = 0;
        while(count2 <6) {
            int RandomInt = (int) (Math.random() * (endWithSymbole - startWithSymbole + 1)) + startWithSymbole;
            Passwort.append((char)(RandomInt));
            count2++;
        }
        return Passwort.toString();


    }
    public static void main(String[] args) {

        Generierung();


    }}


