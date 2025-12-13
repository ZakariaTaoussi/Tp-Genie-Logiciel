package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Map;

public class Archer implements Avatar {

    private static final String TYPE = "ARCHER";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public int HealthRegen(player player) {
        int baseRegen = 1;

        boolean hasMagicBow = player.inventory.contains("Magic Bow");

        if (hasMagicBow) {
            int bonus = ((player.currenthealthpoints + 1) / 8) - 1;
            baseRegen += bonus;
        }

        return baseRegen;
    }

    @Override
    public HashMap<String, Integer> getAbilitiesForLevel(int level) {

        HashMap<String, Integer> stats = new HashMap<>();

        switch (level) {
            case 1 -> {
                stats.put("INT", 1);
                stats.put("ATK", 3);
                stats.put("CHA", 1);
                stats.put("VIS", 3);
            }
            case 2 -> {
                stats.put("DEF", 1);
                stats.put("CHA", 2);
            }
            case 3 -> stats.put("ATK", 3);
            case 4 -> stats.put("DEF", 2);
            case 5 -> stats.put("ATK", 4);
            default -> {
                // rien
            }
        }

        return stats;
    }
}
