package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
//j ai pas pu changé le Nom de la classe player -> Player car je risque de cassé les test
public class player {

    public String playerName;
    public String Avatar_name;
    private Avatar AvatarClass;
    public Integer money;
    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;
    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;
    private static final int SEUIL_NIVEAU_2 = 10;
    private static final int SEUIL_NIVEAU_3 = 27;
    private static final int SEUIL_NIVEAU_4 = 57;
    private static final int SEUIL_NIVEAU_5 = 111;

    public player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<String> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF") ) {
            return;
        }
        this.playerName = playerName;
        this.Avatar_name = avatar_name;
        AvatarFactory factory = new AvatarFactory();
        this.AvatarClass = factory.createAvatar(avatarClass);
        this.money = money;
        this.inventory = inventory;
        if (this.AvatarClass != null) {
            this.abilities = this.AvatarClass.getAbilitiesForLevel(1);
        } else {
            this.abilities = new HashMap<>();
        }
    }

    public String getAvatarClass() {
        return (AvatarClass != null) ? AvatarClass.getType() : null;
    }

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }
        money -= amount;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public int retrieveLevel() {
        if (xp < SEUIL_NIVEAU_2) return 1;
        if (xp < SEUIL_NIVEAU_3) return 2;
        if (xp < SEUIL_NIVEAU_4) return 3;
        if (xp < SEUIL_NIVEAU_5) return 4;
        return 5;
    }

    public int getXp() {
        return this.xp;
    }

}