package re.forestier.edu.rpg;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    @DisplayName("addItem should add item when weight limit not exceeded")
    void testAddItemSuccess() {
        player p = new player("Test", "Avatar", "ARCHER", 50, new ArrayList<>());
        Item sword = new Item("Sword", "Basic sword", 5, 10);

        boolean result = GestionItem.addItem(p, sword);

        assertTrue(result);
        assertEquals(1, p.inventory.size());
        assertTrue(p.inventory.contains(sword));
    }

    @Test
    @DisplayName("addItem should fail when weight limit exceeded")
    void testAddItemTooHeavy() {
        player p = new player("Test", "Avatar", "ARCHER", 50, new ArrayList<>());
        Item heavy1 = new Item("Armor", "Heavy armor", 18, 50);
        Item heavy2 = new Item("Shield", "Too heavy", 5, 30);

        assertTrue(GestionItem.addItem(p, heavy1));
        boolean result = GestionItem.addItem(p, heavy2);

        assertFalse(result);
        assertEquals(1, p.inventory.size());
    }

    @Test
    @DisplayName("calculateCurrentWeight should return total weight of inventory")
    void testCalculateCurrentWeight() {
        player p = new player("Test", "Avatar", "ARCHER", 50, new ArrayList<>());
        p.inventory.add(new Item("Item1", "desc", 3, 10));
        p.inventory.add(new Item("Item2", "desc", 7, 20));

        int weight = GestionItem.calculateCurrentWeight(p);

        assertEquals(10, weight);
    }

    @Test
    @DisplayName("containsItemName should return true when item exists")
    void testContainsItemNameTrue() {
        player p = new player("Test", "Avatar", "ARCHER", 50, new ArrayList<>());
        Item potion = new Item("Potion", "Heal", 1, 5);
        p.inventory.add(potion);

        assertTrue(GestionItem.containsItemName(p, "Potion"));
    }

    @Test
    @DisplayName("containsItemName should return false when item does not exist")
    void testContainsItemNameFalse() {
        player p = new player("Test", "Avatar", "ARCHER", 50, new ArrayList<>());

        assertFalse(GestionItem.containsItemName(p, "Potion"));
    }

    @Test
    @DisplayName("sell should remove item and add money")
    void testSellItemSuccess() {
        player p = new player("Test", "Avatar", "ARCHER", 50, new ArrayList<>());
        Item potion = new Item("Potion", "Heal", 1, 20);
        p.inventory.add(potion);

        GestionItem.sell(p, potion);

        assertFalse(p.inventory.contains(potion));
        assertEquals(70, p.money); // 50 + 20
    }

    @Test
    @DisplayName("sell should do nothing if item not in inventory")
    void testSellItemNotInInventory() {
        player p = new player("Test", "Avatar", "ARCHER", 50, new ArrayList<>());
        Item potion = new Item("Potion", "Heal", 1, 20);

        GestionItem.sell(p, potion);

        assertEquals(50, p.money);
        assertTrue(p.inventory.isEmpty());
    }
}
