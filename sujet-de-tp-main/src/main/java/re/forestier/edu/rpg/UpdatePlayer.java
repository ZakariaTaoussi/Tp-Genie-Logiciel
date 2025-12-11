package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;

public class UpdatePlayer {


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
        Avatar avatar = AVATAR_FACTORY.createAvatar(player.getAvatarClass());
        if (newLevel != currentLevel) {
            player.inventory.add(OBJECT_LIST[RANDOM.nextInt(OBJECT_LIST.length)]);
            HashMap<String, Integer> abilities = avatar.getAbilitiesForLevel(newLevel);
            player.abilities.putAll(abilities);

            return true;
        }
        return false;
    }


    public static void majFinDeTour(player player) {
        if(player.currenthealthpoints == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }

        if (player.currenthealthpoints >= player.healthpoints) {
            player.currenthealthpoints = player.healthpoints;
            return;
        }
        if(player.currenthealthpoints < player.healthpoints / 2) {
            Avatar currentAvatar = AVATAR_FACTORY.createAvatar(player.getAvatarClass());

            if (currentAvatar != null) {
                int regenAmount = currentAvatar.HealthRegen(player);
                player.currenthealthpoints += regenAmount;
            }
        }
    }
}