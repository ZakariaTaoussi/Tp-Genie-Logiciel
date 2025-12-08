package re.forestier.edu.rpg;

import java.util.Map;

public interface Avatar {
    String getType();
    int HealthRegen(player player);
    Map<String, Integer> getAbilitiesForLevel(int level);
}
