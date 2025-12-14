package re.forestier.edu.rpg;

import java.util.HashMap;

public class Goblin implements Avatar {

    private static final String TYPE = "GOBLIN";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public int HealthRegen(player player) {
        return 1;
    }

    @Override
    public HashMap<String, Integer> getAbilitiesForLevel(int level) {
        HashMap<String, Integer> stats = new HashMap<>();

        switch (level) {
            case 1 -> {
                stats.put("INT", 2);
                stats.put("ATK", 2);
                stats.put("ALC", 1);
            }
            case 2 -> {
                stats.put("ATK", 3);
                stats.put("ALC", 4);
            }
            case 3 -> stats.put("VIS", 1);
            case 4 -> stats.put("DEF", 1);
            case 5 -> {
                stats.put("DEF", 2);
                stats.put("ATK", 4);
            }
            default -> {

            }
        }

        return stats;
    }
}