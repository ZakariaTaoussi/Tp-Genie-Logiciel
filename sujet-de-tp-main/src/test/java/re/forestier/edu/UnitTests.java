package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class UnitTests {

    @Test
    public void testPlayerCreationAndXp() {
        player testPlayer = new player("zakaria", "taoussi", "DWARF", 26, new ArrayList<>());
        testPlayer.inventory.add("Epée");
        testPlayer.addMoney(400);
        UpdatePlayer.addXp(testPlayer, 15);
        assertEquals(426, testPlayer.money);
        assertEquals(15, testPlayer.getXp());
        assertEquals("DWARF", testPlayer.getAvatarClass());
        assertFalse(testPlayer.inventory.isEmpty());
    }

    @Test
    @DisplayName("Test Retrieve Level")
    public void testRetrievelvl3() {
        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        UpdatePlayer.addXp(p, 29);
        p.retrieveLevel();
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
        UpdatePlayer.addXp(p1, 57);  // 150 est supérieur à 111
        assertEquals(4, p1.retrieveLevel());
        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        UpdatePlayer.addXp(p, 100);  // 100 est entre 57 et 111
        assertEquals(4, p.retrieveLevel());
    }


    @Test
    @DisplayName("Test Retrieve Level 2")
    public void testRetrieveLevel2() {
        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        UpdatePlayer.addXp(p, 10);  // 150 est supérieur à 111
        assertEquals(2, p.retrieveLevel());
        player p1 = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        UpdatePlayer.addXp(p, 9);  // 150 est supérieur à 111
        assertEquals(1, p1.retrieveLevel());
    }


    @Test
    @DisplayName("Test REmove Money")
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
        player p3 = new player("Zakaria", "Taoussi", "AUTRE", 52, new ArrayList<>());
        assertNull(p3.getAvatarClass());


    }

    @Test
    @DisplayName("test getXP")
    public void testXP() {
        player p2 = new player("Zakaria", "Taoussi", "DWARF", 52, new ArrayList<>());
        assertEquals(0, p2.getXp());

    }


    @Test

    public void testAddXp_ResteMemeNiveau() {

        ArrayList<String> inventory = new ArrayList<>();
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
        ArrayList<String> inventory = new ArrayList<>();
        player p = new player("John", "Avatar", "ARCHER", 100, new ArrayList<>());
        int currentLevel = p.retrieveLevel();
        boolean result = UpdatePlayer.addXp(p, 200); // ça fera passer p.xp > 10 → lvl up
        int newLevel = p.retrieveLevel();
        assertTrue(newLevel > currentLevel); // le joueur a monté de niveau
        assertTrue(result);                  // couvre le return true
        assertFalse(p.inventory.isEmpty());  // vérifie qu'un objet a été ajouté
    }

    @Test
    @DisplayName("Test TestAfficherJoueur")
    public void testAfficherJoueur() {
        ArrayList<String> inventory = new ArrayList<>();
        inventory.add("Épée");
        inventory.add("Potion");
        player testPlayer = new player("John", "Avatar", "ARCHER", 100, inventory);
        Affichage affichage = new Affichage();
        String result = affichage.afficherJoueur(testPlayer);
        assertTrue(result.contains("Épée"), "L'item 'Épée' devrait être dans le résultat");
        assertTrue(result.contains("Potion"), "L'item 'Potion' devrait être dans le résultat");
        assertTrue(result.contains("Inventaire"), "La section 'Inventaire' devrait être présente");
        assert result != null;
    }


    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    /// /
    @Test

    public void testUpdatePlayer_ClassInstantiation() {
        // Test pour couvrir la déclaration de classe
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
        assertTrue(p.retrieveLevel() < 3, "Le niveau devrait être < 3");
        UpdatePlayer.majFinDeTour(p);
        assertEquals(41, p.currenthealthpoints, "ADVENTURER niveau < 3 devrait gagner +1 HP net");
    }

    @Test
    public void testMajFinDeTour_ADVENTURER_LowHP_Level3OrAbove() {
        player p = new player("TestPlayer", "TestAvatar", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 40;
        p.healthpoints = 100;
        UpdatePlayer.addXp(p, 27);
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
        p.inventory.add("Magic Bow");
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
        p.inventory.add("Holy Elixir");
        UpdatePlayer.majFinDeTour(p);
        assertEquals(26, p.currenthealthpoints, "DWARF avec Holy Elixir devrait récupérer +2 HP");
    }
    @Test
    public void testMajFinDeTour_DWARF_WithNoHolyElixir() {
        // Préparer le joueur DWARF sans Holy Elixir
        player p = new player("TestPlayer", "TestAvatar", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 24;
        p.healthpoints = 50;

        // Vérifier que l'inventaire ne contient PAS "Holy Elixir"
        assertFalse(p.inventory.contains("Holy Elixir"), "L'inventaire ne doit pas contenir Holy Elixir");

        // Appeler la méthode
        UpdatePlayer.majFinDeTour(p);

        // Comme expliqué : DWARF sans Holy Elixir récupère +1 => 24 -> 25
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

        boolean niveauAugmente = UpdatePlayer.addXp(joueur, 27); // 27 XP -> niveau 3

        assertThat(niveauAugmente, is(true));
        assertThat(joueur.retrieveLevel(), is(3));
        assertThat(joueur.inventory.size(), is(tailleInventaireAvant + 1));

        // Vérifie les valeurs de certaines compétences au niveau 3
        assertThat(joueur.abilities.get("ATK"), is(5));
        assertThat(joueur.abilities.get("ALC"), is(1));
    }
    @Test
    public void testAffichePlayerKO() {
        player p1 = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        p1.currenthealthpoints = 0;

        // Préparer la capture de la sortie console
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UpdatePlayer.majFinDeTour(p1);
        assertTrue(outContent.toString().contains("Le joueur est KO !"));

    }
}

