package re.forestier.edu.rpg;

public class UpdatePlayer {

    public static boolean addXp(player player, int xp) {
        return ExperienceManager.addXp(player, xp);
    }

    public static void majFinDeTour(player player) {
        HealthManager.majFinDeTour(player);
    }
}