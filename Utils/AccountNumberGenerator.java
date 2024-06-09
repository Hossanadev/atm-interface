package Utils;

import java.util.Random;

public class AccountNumberGenerator {
    public static int generate() {
        Random rand = new Random();
        return rand.nextInt(999999);
    }
}