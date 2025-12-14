package re.forestier.edu;

import re.forestier.edu.rpg.player;
import re.forestier.edu.rpg.Item;
import re.forestier.edu.rpg.WalletManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerCreationAndXp() {
        player testPlayer = new player("zakaria", "taoussi", "DWARF", 26, new ArrayList<>());
        testPlayer.addItem(new Item("Epée", "Une épée de test", 2, 10));
        testPlayer.addMoney(400);
        assertEquals(426, testPlayer.money);
        assertEquals(0, testPlayer.xp);
        assertEquals("DWARF", testPlayer.getAvatarClass());
        assertFalse(testPlayer.inventory.isEmpty());
    }

    @Test
    @DisplayName("test getXP")
    public void testXP() {
        player p2 = new player("Zakaria", "Taoussi", "DWARF", 52, new ArrayList<>());
        assertEquals(0, p2.xp);
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
        player p3 = new player("Zakaria", "Taoussi", "AUTRE", 52, new ArrayList<>());
        assertNull(p3.getAvatarClass(), "Une classe inconnue devrait résulter en null");
    }

    @Test
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("Une exception aurait dû être levée");
    }

}
