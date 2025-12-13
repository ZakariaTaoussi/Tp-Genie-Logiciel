package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

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
        this.abilities = this.AvatarClass.getAbilitiesForLevel(1);

    }

    public String getAvatarClass() {
        return (AvatarClass != null) ? AvatarClass.getType() : null;
    }

    public int getXp() {
        return this.xp;
    }


    public int retrieveLevel() {
        return LevelSystem.calculateLevel(this.xp);
    }


    public void addMoney(int amount) {
        this.money = WalletManager.add(this.money, amount);
    }


    public void removeMoney(int amount) throws IllegalArgumentException {
        this.money = WalletManager.remove(this.money, amount);

    }
}