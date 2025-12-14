package re.forestier.edu.rpg;

public class AvatarFactory {
    public static Avatar createAvatar(String type) {
        if (type == null) return null;

        switch(type) {
            case "ARCHER": return new Archer();
            case "ADVENTURER": return new Adventurer();
            case "DWARF": return new Dwarf();

            case "GOBLIN": return new Goblin();

            default:
                return null;
        }
    }
}