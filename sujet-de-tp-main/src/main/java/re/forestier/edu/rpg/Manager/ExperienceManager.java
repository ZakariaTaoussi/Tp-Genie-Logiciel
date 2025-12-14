package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;


public class ExperienceManager {

    private static final AvatarFactory AVATAR_FACTORY = new AvatarFactory();
    private static final Random RANDOM = new Random();

    // MISE A JOUR : Tableau d'Item
    private final static Item[] OBJECT_LIST = {
            new Item("Lookout Ring", "Prevents surprise attacks", 1, 50),
            new Item("Scroll of Stupidity", "INT-2 when applied to an enemy", 1, 30),
            new Item("Draupnir", "Increases XP gained by 100%", 5, 200),
            new Item("Magic Charm", "Magic +10 for 5 rounds", 1, 20),
            new Item("Rune Staff of Curse", "May burn your ennemies...", 8, 100),
            new Item("Combat Edge", "Well, that's an edge", 3, 40),
            new Item("Holy Elixir", "Recover your HP", 2, 25),
            new Item("Magic Bow", "Magical bow for Archers", 4, 150)
    };

    public static boolean addXp(player player, int xp) {
        int currentLevel = player.retrieveLevel();
        player.xp += xp;
        int targetLevel = player.retrieveLevel();

        boolean hasLeveledUp = false;

        while (currentLevel < targetLevel) {
            currentLevel++;
            handleLevelUp(player, currentLevel);
            hasLeveledUp = true;
        }
        return hasLeveledUp;
    }

    private static void handleLevelUp(player player, int newLevel) {
        grantRandomItem(player);
        updateAbilities(player, newLevel);
    }

    private static void grantRandomItem(player player) {
        Item randomItem = OBJECT_LIST[RANDOM.nextInt(OBJECT_LIST.length)];
        // Appel délégué (qui va appeler GestionItem)
        player.addItem(randomItem);
    }

    private static void updateAbilities(player player, int level) {
        Avatar avatar = AVATAR_FACTORY.createAvatar(player.getAvatarClass());
        HashMap<String, Integer> newAbilities = avatar.getAbilitiesForLevel(level);
        player.abilities.putAll(newAbilities);
    }
}