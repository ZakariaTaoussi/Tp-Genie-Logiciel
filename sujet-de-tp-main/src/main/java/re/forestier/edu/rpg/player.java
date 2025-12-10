package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class player {
    public String playerName;
    public String Avatar_name;
    private Avatar AvatarClass;

    public Integer money;


    public int level;
    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;
    private static final int SEUIL_NIVEAU_2 = 10;
    private static final int SEUIL_NIVEAU_3 = 27;
    private static final int SEUIL_NIVEAU_4 = 57;
    private static final int SEUIL_NIVEAU_5 = 111;

    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;
    public player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<String> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF") ) {
            return;
        }

        this.playerName = playerName;
        Avatar_name = avatar_name;

        this.AvatarClass = AvatarFactory.createAvatar(avatarClass);
        this.money = Integer.valueOf(money);
        this.inventory = inventory;
        this.abilities = this.AvatarClass.getAbilitiesForLevel(1);
    }

    public String getAvatarClass() {
        return (AvatarClass != null) ? AvatarClass.getType() : null;
    }

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }

        money = Integer.parseInt(money.toString()) - amount;
    }
    public void addMoney(int amount) {
        var value = Integer.valueOf(amount);
        money = money + (value != null ? value : 0);
    }
    public int retrieveLevel() {
        if (xp < SEUIL_NIVEAU_2) return 1;
        if (xp < SEUIL_NIVEAU_3) return 2;
        if (xp < SEUIL_NIVEAU_4) return 3;
        if (xp < SEUIL_NIVEAU_5) return 4;
        return 5; // Si on a dépassé tous les seuils
    }

    public int getXp() {
        return this.xp;
    }

}