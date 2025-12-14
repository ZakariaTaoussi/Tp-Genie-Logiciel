package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Map;

public class Dwarf implements Avatar {

    private static final String TYPE = "DWARF";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public int HealthRegen(player player) {
        int baseRegen = 1;

        boolean hasElixir = player.hasItem("Holy Elixir");
        if (hasElixir) {
            baseRegen += 1;
        }

        return baseRegen;
    }

    @Override
    public HashMap<String, Integer> getAbilitiesForLevel(int level) {
        HashMap<String, Integer> stats = new HashMap<>();

        switch (level) {
            case 1 -> {
                stats.put("ALC", 4);
                stats.put("INT", 1);
                stats.put("ATK", 3);
            }
            case 2 -> {
                stats.put("DEF", 1);
                stats.put("ALC", 5);
            }
            case 3 -> stats.put("ATK", 4);
            case 4 -> stats.put("DEF", 2);
            case 5 -> stats.put("CHA", 1);
            default -> {

            }
        }

        return stats;
    }
}
