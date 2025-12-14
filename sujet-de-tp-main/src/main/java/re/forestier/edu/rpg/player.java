package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class player {

    public String playerName;
    public String Avatar_name;
    private Avatar AvatarClass;
    public Integer money;
    public int healthpoints;
    public int currenthealthpoints;
    public int xp;
    public HashMap<String, Integer> abilities;
    public List<Item> inventory = new ArrayList<>();
    public final int maxWeight = 20; // Limite de poids

    public player(String playerName, String avatar_name, String avatarClass, int money, List<Item> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF") && !avatarClass.equals("GOBLIN")) {
            return;
        }
        this.playerName = playerName;
        this.Avatar_name = avatar_name;
        AvatarFactory factory = new AvatarFactory();
        this.AvatarClass = factory.createAvatar(avatarClass);
        this.money = money;
        this.xp = 0;
        this.inventory = inventory;
        this.abilities = this.AvatarClass.getAbilitiesForLevel(1);

    }
    public Avatar getAvatar() {
        return this.AvatarClass;
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
    public boolean addItem(Item item) {
        return GestionItem.addItem(this, item);
    }

    public void sellItem(Item item) {
        GestionItem.sell(this, item);
    }

    public boolean hasItem(String itemName) {
        return GestionItem.containsItemName(this, itemName);
    }
}