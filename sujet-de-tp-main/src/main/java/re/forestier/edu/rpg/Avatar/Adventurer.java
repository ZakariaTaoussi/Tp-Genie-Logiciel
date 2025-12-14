package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Map;

public class Adventurer implements Avatar {

    private static final String TYPE = "ADVENTURER";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public int HealthRegen(player player) {
        int baseRegen = 2;

        if (player.retrieveLevel() < 3) {
            baseRegen--;
        }

        return baseRegen;
    }

    @Override
    public HashMap<String, Integer> getAbilitiesForLevel(int level) {
        HashMap<String, Integer> stats = new HashMap<>();

        switch (level) {
            case 1 -> {
                stats.put("INT", 1);
                stats.put("DEF", 1);
                stats.put("ATK", 3);
                stats.put("CHA", 2);
            }
            case 2 -> {
                stats.put("INT", 2);
                stats.put("CHA", 3);
            }
            case 3 -> {
                stats.put("ATK", 5);
                stats.put("ALC", 1);
            }
            case 4 -> stats.put("DEF", 3);
            case 5 -> {
                stats.put("VIS", 1);
                stats.put("DEF", 4);
            }
            default -> {
            }
        }

        return stats;
    }
}
