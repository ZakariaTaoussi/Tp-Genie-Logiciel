
package re.forestier.edu.rpg;

public class WalletManager {

    public static int add(int current, int amount) {
        return current + amount;
    }

    public static int remove(int current, int amount) throws IllegalArgumentException {
        if (current < amount) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }
        return current - amount;
    }
}