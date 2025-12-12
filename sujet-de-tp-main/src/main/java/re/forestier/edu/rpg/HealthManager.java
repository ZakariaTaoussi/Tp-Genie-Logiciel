package re.forestier.edu.rpg;


public class HealthManager {

    private static final AvatarFactory AVATAR_FACTORY = new AvatarFactory();


    public static void majFinDeTour(player player) {
        Avatar currentAvatar = AVATAR_FACTORY.createAvatar(player.getAvatarClass());
        if (player.currenthealthpoints == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }

        if (player.currenthealthpoints >= player.healthpoints) {
            player.currenthealthpoints = player.healthpoints;
            return;
        }

        if (player.currenthealthpoints < player.healthpoints / 2) {
                int regenAmount = currentAvatar.HealthRegen(player);
                player.currenthealthpoints += regenAmount;
            }
        }
    }
