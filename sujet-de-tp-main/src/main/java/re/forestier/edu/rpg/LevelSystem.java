package re.forestier.edu.rpg;

/**
 * Responsabilité : Calculer le niveau en fonction de l'XP
 */
public class LevelSystem {

    private static final int SEUIL_NIVEAU_2 = 10;
    private static final int SEUIL_NIVEAU_3 = 27;
    private static final int SEUIL_NIVEAU_4 = 57;
    private static final int SEUIL_NIVEAU_5 = 111;

    public static int calculateLevel(int xp) {
        if (xp < SEUIL_NIVEAU_2) return 1;
        if (xp < SEUIL_NIVEAU_3) return 2;
        if (xp < SEUIL_NIVEAU_4) return 3;
        if (xp < SEUIL_NIVEAU_5) return 4;
        return 5;
    }
}