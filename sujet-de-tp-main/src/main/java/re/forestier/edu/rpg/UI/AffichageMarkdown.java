package re.forestier.edu.rpg;

public class AffichageMarkdown {

    public static String render(player p) {
        StringBuilder md = new StringBuilder();

        // Titre
        md.append("# Player : ").append(p.playerName).append("\n\n");

        // Informations générales (CORRECTION : é au lieu de é=)
        md.append("## Informations générales\n");
        md.append("* **Classe** : ").append(p.getAvatar().getType()).append("\n");
        md.append("* **Niveau** : ").append(p.retrieveLevel()).append("\n");
        md.append("* **XP** : ").append(p.xp).append("\n");
        md.append("* **Or** : ").append(p.money).append("\n\n");

        // Statistiques (ordre stable)
        md.append("## Statistiques\n");
        p.abilities.keySet().stream()
                .sorted()
                .forEach(key ->
                        md.append("* **")
                                .append(key)
                                .append("** : ")
                                .append(p.abilities.get(key))
                                .append("\n")
                );

        return md.toString();
    }
}