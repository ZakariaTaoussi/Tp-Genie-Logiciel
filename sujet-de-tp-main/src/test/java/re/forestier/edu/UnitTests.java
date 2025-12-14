/*package re.forestier.edu; // ou votre package actuel

// --- AJOUTEZ CES IMPORTS ---
import re.forestier.edu.rpg.Item;
import re.forestier.edu.rpg.player;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Affichage;
// ---------------------------

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {

    @Test
    public void testPlayerCreationAndXp() {
        // Correction : new List<Item>() est impossible (List est une interface), on met new ArrayList<>()
        player testPlayer = new player("zakaria", "taoussi", "DWARF", 26, new ArrayList<>());

        // Correction : On ajoute un vrai Item, pas une String
        testPlayer.addItem(new Item("Epée", "Une épée de test", 2, 10));

        testPlayer.addMoney(400);
        UpdatePlayer.addXp(testPlayer, 15);

        assertEquals(426, testPlayer.money);
        assertEquals(15, testPlayer.xp); // Utilisation directe du champ ou getter si dispo
        assertEquals("DWARF", testPlayer.getAvatarClass());
        assertFalse(testPlayer.inventory.isEmpty());
    }

    @Test
    @DisplayName("Test Retrieve Level")
    public void testRetrievelvl3() {
        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        UpdatePlayer.addXp(p, 29);
        assertEquals(3, p.retrieveLevel());
    }

    @Test
    public void testRemoveMoneyExactBalance() {
        player p = new player("Zakaria", "Taoussi", "ADVENTURER", 52, new ArrayList<>());
        p.removeMoney(52);
        assertEquals(0, p.money);
    }

    @Test
    @DisplayName("Test Retrieve Level 5")
    public void testRetrieveLevel5() {
        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        UpdatePlayer.addXp(p, 111);
        assertEquals(5, p.retrieveLevel());
    }

    @Test
    @DisplayName("Test Retrieve Level 4")
    public void testRetrieveLevel4() {
        player p1 = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        UpdatePlayer.addXp(p1, 57);
        assertEquals(4, p1.retrieveLevel());

        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        UpdatePlayer.addXp(p, 100);
        assertEquals(4, p.retrieveLevel());
    }

    @Test
    @DisplayName("Test Retrieve Level 2")
    public void testRetrieveLevel2() {
        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        UpdatePlayer.addXp(p, 10);
        assertEquals(2, p.retrieveLevel());

        player p1 = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        UpdatePlayer.addXp(p1, 9);
        assertEquals(1, p1.retrieveLevel());
    }

    @Test
    @DisplayName("Test Remove Money")
    public void TestRemoveMoney() {
        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        p.removeMoney(20);
        assertEquals(32, p.money);
    }

    @Test
    @DisplayName("Test add Money")
    public void TestAddMoney() {
        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        p.addMoney(50);
        assertEquals(102, p.money);
    }

    @Test
    @DisplayName("Test add Money 0 ")
    public void TestAddMoney0() {
        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        p.addMoney(0);
        assertEquals(52, p.money);
    }

    @Test
    @DisplayName("Constructeur avec ARCHER")
    public void testConstructeurArcher() {
        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        assertEquals("ARCHER", p.getAvatarClass());
        assertEquals("Zakaria", p.playerName);
        assertEquals(52, p.money);
    }

    @Test
    @DisplayName("Constructeur avec ADVENTURER")
    public void testConstructeurADVENTURER() {
        player p1 = new player("Zakaria", "Taoussi", "ADVENTURER", 52, new ArrayList<>());
        assertEquals("ADVENTURER", p1.getAvatarClass());
        assertEquals("Zakaria", p1.playerName);
        assertEquals(52, p1.money);
    }

    @Test
    @DisplayName("Test Constructeur avec DWARF")
    public void TestCOnstructeurDWARF() {
        player p2 = new player("Zakaria", "Taoussi", "DWARF", 52, new ArrayList<>());
        assertEquals("DWARF", p2.getAvatarClass());
        assertEquals("Zakaria", p2.playerName);
        assertEquals(52, p2.money);
    }

    @Test
    @DisplayName("Test Constructeur avec AUTRE")
    public void TestConstructeurAUTRE() {
        // On crée un joueur avec une classe invalide "AUTRE"
        player p3 = new player("Zakaria", "Taoussi", "AUTRE", 52, new ArrayList<>());
        assertNull(p3.getAvatarClass(), "Une classe inconnue devrait résulter en null");
    }
    @Test
    @DisplayName("test getXP")
    public void testXP() {
        player p2 = new player("Zakaria", "Taoussi", "DWARF", 52, new ArrayList<>());
        // Si vous n'avez pas de getter getXp(), utilisez p2.xp directement
        assertEquals(0, p2.xp);
    }

    @Test
    public void testAddXp_ResteMemeNiveau() {
        // Correction : ArrayList<Item>
        ArrayList<Item> inventory = new ArrayList<>();
        player testPlayer = new player("John", "Avatar", "ARCHER", 100, inventory);
        int currentLevel = testPlayer.retrieveLevel();
        boolean result = UpdatePlayer.addXp(testPlayer, 5);
        int newLevel = testPlayer.retrieveLevel();
        assertEquals(currentLevel, newLevel, "Le niveau doit rester le même");
        assertFalse(result, "La méthode doit retourner false quand pas de montée de niveau");
    }

    @Test
    @DisplayName("addXp returns true on level-up")
    void testAddXpLevelUp() {
        player p = new player("John", "Avatar", "ARCHER", 100, new ArrayList<>());
        int currentLevel = p.retrieveLevel();
        boolean result = UpdatePlayer.addXp(p, 200); // XP > 10 -> level up
        int newLevel = p.retrieveLevel();
        assertTrue(newLevel > currentLevel);
        assertTrue(result);
        assertFalse(p.inventory.isEmpty());  // vérifie qu'un objet a été ajouté
    }

    @Test
    @DisplayName("Test TestAfficherJoueur")
    public void testAfficherJoueur() {
        // Correction complète pour utiliser Item
        ArrayList<Item> inventory = new ArrayList<>();
        inventory.add(new Item("Épée", "Arme de base", 2, 10));
        inventory.add(new Item("Potion", "Soin", 1, 5));

        player testPlayer = new player("John", "Avatar", "ARCHER", 100, inventory);

        // Affichage est supposé static ou instanciable selon votre code
        // Si Affichage a des méthodes statiques : Affichage.afficherJoueur(testPlayer)
        // Sinon :
        Affichage affichage = new Affichage();
        String result = Affichage.afficherJoueur(testPlayer); // Supposant static d'après votre code initial

        assertTrue(result.contains("Épée"), "L'item 'Épée' devrait être dans le résultat");
        assertTrue(result.contains("Potion"), "L'item 'Potion' devrait être dans le résultat");
        assertTrue(result.contains("Inventaire"), "La section 'Inventaire' devrait être présente");
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return; // Test passe
        }
        fail("Une exception aurait dû être levée");
    }

    @Test
    public void testUpdatePlayer_ClassInstantiation() {
        UpdatePlayer updatePlayer = new UpdatePlayer();
        assertNotNull(updatePlayer, "UpdatePlayer devrait pouvoir être instancié");
    }

    @Test
    public void testMajFinDeTour_PlayerKOAdventurer() {
        player p = new player("TestPlayer", "TestAvatar", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 0;
        p.healthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(0, p.currenthealthpoints, "Le joueur KO ne devrait pas récupérer de HP");
    }

    @Test
    public void testMajFinDeTour_PlayerKODWARF() {
        player p = new player("TestPlayer", "TestAvatar", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 0;
        p.healthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(0, p.currenthealthpoints, "Le joueur KO ne devrait pas récupérer de HP");
    }

    @Test
    public void testMajFinDeTour_PlayerKOARCHER() {
        player p = new player("TestPlayer", "TestAvatar", "ARCHER", 100, new ArrayList<>());
        p.currenthealthpoints = 0;
        p.healthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(0, p.currenthealthpoints, "Le joueur KO ne devrait pas récupérer de HP");
    }

    @Test
    public void testMajFinDeTour_ADVENTURER_LowHP_LevelUnder3() {
        player p = new player("TestPlayer", "TestAvatar", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 40;
        p.healthpoints = 100;
        UpdatePlayer.addXp(p, 10);
        // Niveau 2
        assertTrue(p.retrieveLevel() < 3, "Le niveau devrait être < 3");
        UpdatePlayer.majFinDeTour(p);
        assertEquals(41, p.currenthealthpoints, "ADVENTURER niveau < 3 devrait gagner +1 HP net");
    }

    @Test
    public void testMajFinDeTour_ADVENTURER_LowHP_Level3OrAbove() {
        player p = new player("TestPlayer", "TestAvatar", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 40;
        p.healthpoints = 100;
        UpdatePlayer.addXp(p, 27); // Niveau 3
        assertTrue(p.retrieveLevel() >= 3, "Le niveau devrait être >= 3");
        UpdatePlayer.majFinDeTour(p);
        assertEquals(42, p.currenthealthpoints, "ADVENTURER niveau >= 3 devrait gagner +2 HP");
    }

    @Test
    public void testMajFinDeTour_EdgeCase_ExactlyHalfHP() {
        player p = new player("TestPlayer", "TestAvatar", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 50;
        p.healthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(50, p.currenthealthpoints, "HP à exactement la moitié ne devrait pas changer");
    }

    @Test
    public void testMajFinDeTour_ARCHER_MagicBow() {
        player p = new player("TestPlayer", "TestAvatar", "ARCHER", 100, new ArrayList<>());
        p.currenthealthpoints = 47;
        p.healthpoints = 100;
        Item bow = new Item("Magic Bow", "Test Item", 1, 100);
        p.addItem(bow);
        UpdatePlayer.majFinDeTour(p);
        assertEquals(53, p.currenthealthpoints, "ARCHER avec Magic Bow devrait récupérer correctement ses HP");
    }

    @Test
    public void TestMajFinDeTourArcher_NoMagicBow() {
        player p = new player("TestPlayer", "TestAvatar", "ARCHER", 100, new ArrayList<>());
        p.currenthealthpoints = 47;
        p.healthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(48, p.currenthealthpoints, "ARCHER sans Magic Bow devrait récupérer +1 HP");
    }

    @Test
    public void testMajFinDeTour_DWARF_WithHolyElixir() {
        player p = new player("TestPlayer", "TestAvatar", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 24;
        p.healthpoints = 50;

        // Correction : Ajout Item
        Item elixir = new Item("Holy Elixir", "Test Item", 0, 0);
        p.addItem(elixir);

        UpdatePlayer.majFinDeTour(p);
        assertEquals(26, p.currenthealthpoints, "DWARF avec Holy Elixir devrait récupérer +2 HP");
    }

    @Test
    public void testMajFinDeTour_DWARF_WithNoHolyElixir() {
        player p = new player("TestPlayer", "TestAvatar", "DWARF", 100, new ArrayList<Item>());
        p.currenthealthpoints = 24;
        p.healthpoints = 50;

        // Correction : hasItem au lieu de contains
        assertFalse(p.hasItem("Holy Elixir"), "L'inventaire ne doit pas contenir Holy Elixir");

        UpdatePlayer.majFinDeTour(p);
        assertEquals(25, p.currenthealthpoints, "DWARF sans Holy Elixir doit récupérer +1 HP");
    }

    @Test
    public void TestHpSupMaxHp() {
        player p1 = new player("TestPlayer", "TestAvatar", "DWARF", 100, new ArrayList<>());
        p1.currenthealthpoints = 51;
        p1.healthpoints = 50;
        UpdatePlayer.majFinDeTour(p1);
        assertEquals(50, p1.currenthealthpoints, "Les HP ne doivent pas dépasser le maximum");

        player p2 = new player("TestPlayer2", "TestAvatar2", "DWARF", 100, new ArrayList<>());
        p2.currenthealthpoints = 50;
        p2.healthpoints = 50;
        UpdatePlayer.majFinDeTour(p2);
        assertEquals(50, p2.currenthealthpoints, "HP déjà au maximum ne doivent pas changer");
    }

    @Test
    void shouldLevelUpDirectlyToLevel3() {
        player joueur = new player("Zakaria", "test", "ADVENTURER", 100, new ArrayList<>());
        int tailleInventaireAvant = joueur.inventory.size();
        boolean niveauAugmente = UpdatePlayer.addXp(joueur, 27);
        assertTrue(niveauAugmente);
        assertEquals(3, joueur.retrieveLevel());
        assertTrue(joueur.inventory.size() >= tailleInventaireAvant + 1);
        assertEquals(5, joueur.abilities.get("ATK"));
        assertEquals(1, joueur.abilities.get("ALC"));
    }

    @Test
    public void testAffichePlayerKO() {
        player p1 = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        p1.currenthealthpoints = 0;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        UpdatePlayer.majFinDeTour(p1);
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Le joueur est KO !"));
    }


}


*/