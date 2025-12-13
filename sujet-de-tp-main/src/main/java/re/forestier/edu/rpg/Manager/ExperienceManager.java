package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;


public class ExperienceManager {

    private static final AvatarFactory AVATAR_FACTORY = new AvatarFactory();
    private static final Random RANDOM = new Random();

    private final static String[] OBJECT_LIST = {
            "Lookout Ring : Prevents surprise attacks",
            "Scroll of Stupidity : INT-2 when applied to an enemy",
            "Draupnir : Increases XP gained by 100%",
            "Magic Charm : Magic +10 for 5 rounds",
            "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?",
            "Combat Edge : Well, that's an edge",
            "Holy Elixir : Recover your HP"
    };



    public static boolean addXp(player player, int xp) {
        int currentLevel = player.retrieveLevel();
        player.xp += xp;
        int newLevel = player.retrieveLevel();

        if (hasLeveledUp(currentLevel, newLevel)) {
            handleLevelUp(player, newLevel);
            return true;
        }
        return false;
    }


    private static boolean hasLeveledUp(int oldLevel, int newLevel) {
        return newLevel != oldLevel;
    }


    private static void handleLevelUp(player player, int newLevel) {
        grantRandomItem(player);
        updateAbilities(player, newLevel);
    }


    private static void grantRandomItem(player player) {
        String randomItem = OBJECT_LIST[RANDOM.nextInt(OBJECT_LIST.length)];
        player.inventory.add(randomItem);
    }


    private static void updateAbilities(player player, int level) {
        Avatar avatar = AVATAR_FACTORY.createAvatar(player.getAvatarClass());
        HashMap<String, Integer> newAbilities = avatar.getAbilitiesForLevel(level);
        player.abilities.putAll(newAbilities);
    }
}