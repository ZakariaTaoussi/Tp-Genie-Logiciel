package re.forestier.edu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import re.forestier.edu.rpg.player;
import re.forestier.edu.rpg.Item;
import re.forestier.edu.rpg.UpdatePlayer;

import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class UpdatePlayerTest {

    @Test
    public void testAddXp_ResteMemeNiveau() {
        ArrayList<Item> inventory = new ArrayList<>();
        player testPlayer = new player("John", "Avatar", "ARCHER", 100, inventory);
        int currentLevel = testPlayer.retrieveLevel();
        boolean result = UpdatePlayer.addXp(testPlayer, 5);
        int newLevel = testPlayer.retrieveLevel();
        assertEquals(currentLevel, newLevel, "Le niveau doit rester le même");
        assertFalse(result, "La méthode doit retourner false quand pas de montée de niveau");
    }

    @Test
    void testAddXpLevelUp() {
        player p = new player("John", "Avatar", "ARCHER", 100, new ArrayList<>());
        int currentLevel = p.retrieveLevel();
        boolean result = UpdatePlayer.addXp(p, 200);
        int newLevel = p.retrieveLevel();
        assertTrue(newLevel > currentLevel);
        assertTrue(result);
        assertFalse(p.inventory.isEmpty());
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
    public void testUpdatePlayer_ClassInstantiation() {
        UpdatePlayer updatePlayer = new UpdatePlayer();
        assertNotNull(updatePlayer, "UpdatePlayer devrait pouvoir être instancié");
    }

    // --- Tests majFinDeTour ---
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
    public void TestMajFinDeTourArcher_NoMagicBow() {
        player p = new player("TestPlayer", "TestAvatar", "ARCHER", 100, new ArrayList<>());
        p.currenthealthpoints = 47;
        p.healthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(48, p.currenthealthpoints, "ARCHER sans Magic Bow devrait récupérer +1 HP");
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
    public void testMajFinDeTour_DWARF_WithHolyElixir() {
        player p = new player("TestPlayer", "TestAvatar", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 24;
        p.healthpoints = 50;
        Item elixir = new Item("Holy Elixir", "Test Item", 0, 0);
        p.addItem(elixir);
        UpdatePlayer.majFinDeTour(p);
        assertEquals(26, p.currenthealthpoints, "DWARF avec Holy Elixir devrait récupérer +2 HP");
    }

    @Test
    public void testMajFinDeTour_DWARF_WithNoHolyElixir() {
        player p = new player("TestPlayer", "TestAvatar", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 24;
        p.healthpoints = 50;
        assertFalse(p.hasItem("Holy Elixir"), "L'inventaire ne doit pas contenir Holy Elixir");
        UpdatePlayer.majFinDeTour(p);
        assertEquals(25, p.currenthealthpoints, "DWARF sans Holy Elixir doit récupérer +1 HP");
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
