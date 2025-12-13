package re.forestier.edu.rpg;

public class AvatarFactory {
    public static Avatar createAvatar(String type) {
        switch(type) {
            case "ARCHER": return new Archer();
            case "ADVENTURER": return new Adventurer();
            case "DWARF": return new Dwarf();
            default:
                return null;
        }
    }
}
