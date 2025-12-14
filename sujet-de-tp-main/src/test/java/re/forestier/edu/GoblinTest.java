package re.forestier.edu.rpg;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GoblinTest {

    @Test
    @DisplayName("Goblin type should be GOBLIN")
    void testGetType() {
        Goblin goblin = new Goblin();
        assertEquals("GOBLIN", goblin.getType());
    }

    @Test
    @DisplayName("Goblin health regeneration should always be 1")
    void testHealthRegen() {
        Goblin goblin = new Goblin();
        player p = new player("Test", "Avatar", "GOBLIN", 50, new ArrayList<>());

        int regen = goblin.HealthRegen(p);
        assertEquals(1, regen);
    }

    @Test
    @DisplayName("Goblin abilities at level 1")
    void testAbilitiesLevel1() {
        Goblin goblin = new Goblin();
        HashMap<String, Integer> stats = goblin.getAbilitiesForLevel(1);

        assertEquals(3, stats.size());
        assertEquals(2, stats.get("INT"));
        assertEquals(2, stats.get("ATK"));
        assertEquals(1, stats.get("ALC"));
    }

    @Test
    @DisplayName("Goblin abilities at level 2")
    void testAbilitiesLevel2() {
        Goblin goblin = new Goblin();
        HashMap<String, Integer> stats = goblin.getAbilitiesForLevel(2);

        assertEquals(2, stats.size());
        assertEquals(3, stats.get("ATK"));
        assertEquals(4, stats.get("ALC"));
    }

    @Test
    @DisplayName("Goblin abilities at level 3")
    void testAbilitiesLevel3() {
        Goblin goblin = new Goblin();
        HashMap<String, Integer> stats = goblin.getAbilitiesForLevel(3);

        assertEquals(1, stats.size());
        assertEquals(1, stats.get("VIS"));
    }

    @Test
    @DisplayName("Goblin abilities at level 4")
    void testAbilitiesLevel4() {
        Goblin goblin = new Goblin();
        HashMap<String, Integer> stats = goblin.getAbilitiesForLevel(4);

        assertEquals(1, stats.size());
        assertEquals(1, stats.get("DEF"));
    }

    @Test
    @DisplayName("Goblin abilities at level 5")
    void testAbilitiesLevel5() {
        Goblin goblin = new Goblin();
        HashMap<String, Integer> stats = goblin.getAbilitiesForLevel(5);

        assertEquals(2, stats.size());
        assertEquals(2, stats.get("DEF"));
        assertEquals(4, stats.get("ATK"));
    }

    @Test
    @DisplayName("Goblin abilities for invalid level should be empty")
    void testAbilitiesInvalidLevel() {
        Goblin goblin = new Goblin();
        HashMap<String, Integer> stats = goblin.getAbilitiesForLevel(0);

        assertNotNull(stats);
        assertTrue(stats.isEmpty());
    }
}
