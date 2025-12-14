package re.forestier.edu;

import re.forestier.edu.rpg.*;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AffichageTest {

    @Test
    @DisplayName("Test TestAfficherJoueur")
    public void testAfficherJoueur() {
        ArrayList<Item> inventory = new ArrayList<>();
        inventory.add(new Item("Épée", "Arme de base", 2, 10));
        inventory.add(new Item("Potion", "Soin", 1, 5));
        player testPlayer = new player("John", "Avatar", "ARCHER", 100, inventory);
        String result = Affichage.afficherJoueur(testPlayer);
        assertTrue(result.contains("Épée"), "L'item 'Épée' devrait être dans le résultat");
        assertTrue(result.contains("Potion"), "L'item 'Potion' devrait être dans le résultat");
        assertTrue(result.contains("Inventaire"), "La section 'Inventaire' devrait être présente");
    }

    @Test
    void testAffichageMarkdown() {
        player p = new player(
                "Florian",
                "Gnognak le Barbare",
                "DWARF",
                0,
                new ArrayList<>()
        );

        String result = AffichageMarkdown.render(p);

        String expected = """
                # Player : Florian

                ## Informations générales
                * **Classe** : DWARF
                * **Niveau** : 1
                * **XP** : 0
                * **Or** : 0

                ## Statistiques
                * **ALC** : 4
                * **ATK** : 3
                * **INT** : 1
                """;

        assertEquals(expected.trim(), result.trim());
    }


}
