package re.forestier.edu.rpg;

import java.util.HashMap;

public interface Avatar {
    String getType();
    int HealthRegen(player player);
    HashMap<String, Integer> getAbilitiesForLevel(int level);
}
