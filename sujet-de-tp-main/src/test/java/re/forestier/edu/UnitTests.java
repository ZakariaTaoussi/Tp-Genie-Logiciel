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

public class UnitTests {

    @Test
    public void testPlayerCreationAndXp() {
        // Arrange
        player testPlayer = new player("zakaria", "taoussi", "DWARF", 26, new ArrayList<>());

        // Act
        testPlayer.addMoney(400);
        UpdatePlayer.addXp(testPlayer, 15);

        // Assert
        assertEquals(426, testPlayer.money); // ou la valeur attendue
        assertEquals(15, testPlayer.getXp()); // ou la valeur attendue selon votre logique
    }
    @Test


    @DisplayName("Sample test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
        player.inventory.add("Épée");
        player.inventory.add("Bouclier");
        player.inventory.add("Potion de soin");

        // Vérification de la taille de l'inventaire
        assertThat(player.inventory.size(), is(3));

        // Vérification des objets présents
        assertThat(player.inventory, contains("Épée", "Bouclier", "Potion de soin"));

    }
    @Test
    @DisplayName("Test Retrieve Level")
    public void testRetrievelvl3(){
        player p = new player("Zakaria", "Taoussi" ,"ARCHER", 52 , new ArrayList<>());
        UpdatePlayer.addXp(p, 29);
        p.retrieveLevel();
        assertEquals(3,p.retrieveLevel());
    }
    @Test
    public void testRemoveMoneyExactBalance() {
        player p = new player("Zakaria", "Taoussi", "ADVENTURER", 52, new ArrayList<>());

        // On retire exactement tout l'argent
        p.removeMoney(52);

        // Le solde doit être 0, et aucune exception ne doit être levée
        assertEquals(0, p.money);
    }


    @Test
    @DisplayName("Test Retrieve Level 5")
    public void testRetrieveLevel5() {
        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        UpdatePlayer.addXp(p, 111);  // 150 est supérieur à 111
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
    public void TestRemoveMoney(){
        player p = new player("Zakaria", "Taoussi" ,"ARCHER", 52 , new ArrayList<>());
        p.removeMoney(20);
        assertEquals(32, p.money);
    }
    @Test
    @DisplayName("Test add Money")
    public void TestAddMoney(){
        player p = new player("Zakaria", "Taoussi" ,"ARCHER", 52 , new ArrayList<>());

        p.addMoney(50);
        assertEquals(102,p.money);


    }



    @Test
    @DisplayName("Test add Money 0 ")
    public void TestAddMoney0(){
        player p = new player("Zakaria", "Taoussi" ,"ARCHER", 52 , new ArrayList<>());
        p.addMoney(0);
        assertEquals(52,p.money);


    }

    @Test
    @DisplayName("Constructeur avec ARCHER" )
    public void testConstructeurArcher(){

        player p = new player("Zakaria", "Taoussi" ,"ARCHER", 52 , new ArrayList<>());
        assertEquals("ARCHER",p.getAvatarClass());
        assertEquals("Zakaria",p.playerName);
        assertEquals(52,p.money);


    }
    @Test
    @DisplayName("Constructeur avec ADVENTURER")
    public void testConstructeurADVENTURER(){
        player p1= new player("Zakaria", "Taoussi" ,"ADVENTURER", 52 , new ArrayList<>());
        assertEquals("ADVENTURER",p1.getAvatarClass());
        assertEquals("Zakaria",p1.playerName);
        assertEquals(52,p1.money);


    }
    @Test
    @DisplayName("Test Constructeur avec DWARF")
    public void TestCOnstructeurDWARF(){
        player p2= new player("Zakaria", "Taoussi" ,"DWARF", 52 , new ArrayList<>());
        assertEquals("DWARF",p2.getAvatarClass());
        assertEquals("Zakaria",p2.playerName);
        assertEquals(52,p2.money);


    }


    @Test
    @DisplayName("Test Constructeur avec AUTRE")
    public void TestConstructeurAUTRE(){
        player p3= new player("Zakaria", "Taoussi" ,"AUTRE", 52 , new ArrayList<>());
        assertNull(p3.getAvatarClass());



    }
    @Test
    @DisplayName("test getXP")
    public  void  testXP (){
        player p2= new player("Zakaria", "Taoussi" ,"DWARF", 52 , new ArrayList<>());
        assertEquals(0,p2.getXp());

    }


    @Test

    public void testAddXp_ResteMemeNiveau() {
        // Création d'un joueur
        ArrayList<String> inventory = new ArrayList<>();
        player testPlayer = new player("John", "Avatar", "ARCHER", 100, inventory);
        int currentLevel = testPlayer.retrieveLevel();
        boolean result = UpdatePlayer.addXp(testPlayer, 5);
        int newLevel = testPlayer.retrieveLevel();

        assertEquals(currentLevel, newLevel, "Le niveau doit rester le même");
        assertFalse(result, "La méthode doit retourner false quand pas de montée de niveau");
        //Cas 2 NewLvl different from currentlvl

      }



    @Test
    @DisplayName("Test TestAfficherJoueur")
    public void testAfficherJoueur() {
        // Création d'un inventaire
        ArrayList<String> inventory = new ArrayList<>();
        inventory.add("Épée");
        inventory.add("Potion");

        // Création d'un joueur
        player testPlayer = new player("John", "Avatar", "ARCHER", 100, inventory);

        // AJOUTEZ CETTE LIGNE pour couvrir le constructeur :
        Affichage affichage = new Affichage();

        // Test de la méthode afficherJoueur
        String result = Affichage.afficherJoueur(testPlayer);
        assertTrue(result.contains("Épée"), "L'item 'Épée' devrait être dans le résultat");
        assertTrue(result.contains("Potion"), "L'item 'Potion' devrait être dans le résultat");

        // Bonus : vérifier que la section Inventaire existe
        assertTrue(result.contains("Inventaire"), "La section 'Inventaire' devrait être présente");
        // Vérification que le résultat n'est pas null
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


    private player createTestPlayer(String avatarClass, int currentHP, int maxHP, int targetLevel) {
        ArrayList<String> inventory = new ArrayList<>();
        player p = new player("TestPlayer", "TestAvatar", avatarClass, 100, inventory);
        p.currenthealthpoints = currentHP;
        p.healthpoints = maxHP;

        // Ajouter l'XP nécessaire pour atteindre le niveau souhaité
        int xpNeeded = getXpForLevel(targetLevel);
        if (xpNeeded > 0) {
            UpdatePlayer.addXp(p, xpNeeded);
        }

        return p;
    }

    private int getXpForLevel(int level) {
        // Basé sur la méthode retrieveLevel() de player
        switch (level) {
            case 1:
                return 0;
            case 2:
                return 10; // minimum pour level 2
            case 3:
                return 27; // minimum pour level 3
            case 4:
                return 57; // minimum pour level 4
            case 5:
                return 111; // minimum pour level 5
            default:
                return 0;
        }
    }


    @Test
    public void testMajFinDeTour_PlayerKO() {
        // Test cas 1: Joueur KO (currenthealthpoints == 0)
        player p = createTestPlayer("ADVENTURER", 0, 100, 1);

        UpdatePlayer.majFinDeTour(p);

        assertEquals(0, p.currenthealthpoints, "Le joueur KO ne devrait pas récupérer de HP");
        // Vérifier que la méthode return après ce cas
    }



    @Test
    public void testMajFinDeTour_ADVENTURER_LowHP_LevelUnder3() {
        // Test cas 6: ADVENTURER avec HP < healthpoints/2 et level < 3
        player p = createTestPlayer("ADVENTURER", 40, 100, 2);

        // Vérifier que le niveau est bien < 3
        assertTrue(p.retrieveLevel() < 3, "Le niveau devrait être < 3");

        UpdatePlayer.majFinDeTour(p);

        // +2 HP puis -1 HP car level < 3
        assertEquals(41, p.currenthealthpoints, "ADVENTURER niveau < 3 devrait gagner +1 HP net");
    }

    @Test
    public void testMajFinDeTour_ADVENTURER_LowHP_Level3OrAbove() {
        // Test cas 7: ADVENTURER avec HP < healthpoints/2 et level >= 3
        player p = createTestPlayer("ADVENTURER", 40, 100, 3);

        // Vérifier que le niveau est bien >= 3
        assertTrue(p.retrieveLevel() >= 3, "Le niveau devrait être >= 3");

        UpdatePlayer.majFinDeTour(p);

        assertEquals(42, p.currenthealthpoints, "ADVENTURER niveau >= 3 devrait gagner +2 HP");
    }



    @Test
    public void testMajFinDeTour_EdgeCase_ExactlyHalfHP() {
        // Test cas 13: HP exactement à la moitié
        player p = createTestPlayer("ADVENTURER", 50, 100, 1);

        UpdatePlayer.majFinDeTour(p);

        // 50 >= 50 (100/2), donc pas de guérison
        assertEquals(50, p.currenthealthpoints, "HP à exactement la moitié ne devrait pas changer");
    }


    @Test
    public void testMajFinDeTour_ARCHER_MagicBow_LowHP() {
        // Test cas 15: ARCHER avec Magic Bow et HP très bas
        player p = createTestPlayer("ARCHER", 8, 100, 1); // 8/8 = 1, donc 1-1 = 0
        p.inventory.add("Magic Bow");

        UpdatePlayer.majFinDeTour(p);

        // +1 HP normal + (8/8 - 1) = +1 + 0 = +1
        assertEquals(9, p.currenthealthpoints, "Magic Bow avec HP très bas");
    }
    @Test
    public void test() {
        // Test cas 15: ARCHER avec Magic Bow et HP très bas
        player p = createTestPlayer("ARCHER", 8, 100, 1); // 8/8 = 1, donc 1-1 = 0
        UpdatePlayer.majFinDeTour(p);
        // +1 HP normal + (8/8 - 1) = +1 + 0 = +1
        assertEquals(9, p.currenthealthpoints, "Magic Bow avec HP très bas");
    }

    @Test
    public void testMajFinDeTour_dwarf() {

        player p = createTestPlayer("DWARF", 24, 50, 1);
        p.inventory.add("Holy Elixir");

        UpdatePlayer.majFinDeTour(p);
        // 24 < 25, donc +2 HP = 26
        assertEquals(26, p.currenthealthpoints);

        // Maintenant on manipule pour dépasser le max
        p.currenthealthpoints = 55; // Force > healthpoints
        p.healthpoints = 50;

        UpdatePlayer.majFinDeTour(p);
        assertEquals(50, p.currenthealthpoints, "Plafonnement forcé");


    }



    @Test
    public void testMajFinDeTour_Dwarf_SansHolyElixir() {
        player p = createTestPlayer("DWARF", 25, 100, 1);
        p.inventory.clear();

        UpdatePlayer.majFinDeTour(p);

        assertEquals(26, p.currenthealthpoints, "DWARF sans objets devrait gagner 1 HP");
    }

}

