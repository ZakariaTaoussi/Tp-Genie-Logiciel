package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;

public class UpdatePlayer {

    private final static String[] objectList = {"Lookout Ring : Prevents surprise attacks","Scroll of Stupidity : INT-2 when applied to an enemy", "Draupnir : Increases XP gained by 100%", "Magic Charm : Magic +10 for 5 rounds", "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?", "Combat Edge : Well, that's an edge", "Holy Elixir : Recover your HP"
    };


    public static boolean addXp(player player, int xp) {
        int currentLevel = player.retrieveLevel();
        player.xp += xp;
        int newLevel = player.retrieveLevel();
        AvatarFactory avatar = new AvatarFactory();
        Avatar avatar1 = avatar.createAvatar(player.getAvatarClass());

        if (newLevel != currentLevel) {
            Random random = new Random();
            player.inventory.add(objectList[random.nextInt(objectList.length)]);
            HashMap<String, Integer> abilities = avatar1.getAbilitiesForLevel(newLevel);
            abilities.forEach((ability, level) -> {
                player.abilities.put(ability, level);
            });
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
            final String avatarClass = player.getAvatarClass();

            switch (avatarClass) {
                case "DWARF":
                    player.currenthealthpoints += 1;
                    if (player.inventory.contains("Holy Elixir")) {
                        player.currenthealthpoints += 1;
                    }
                    break;

                case "ARCHER":
                    player.currenthealthpoints += 1;
                    if (player.inventory.contains("Magic Bow")) {
                        player.currenthealthpoints += player.currenthealthpoints / 8 - 1;
                    }
                    break;

                case "ADVENTURER":
                    player.currenthealthpoints += 2;
                    if (player.retrieveLevel() < 3) {
                        player.currenthealthpoints -= 1;
                    }
                    break;
            }
        }
    }
}