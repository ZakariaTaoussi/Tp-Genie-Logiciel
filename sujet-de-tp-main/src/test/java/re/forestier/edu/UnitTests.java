package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class UnitTests {
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
    @DisplayName("Test ADDXP")

    public void TestAddXP_noLevelUp() {
        // Créer un joueur avec 50 XP
        player p = new player("Zakaria", "Taoussi", "ARCHER", 50, new ArrayList<>());

    }
    @Test
    public void testMajFinDeTour_AdventurerNiveau3() {
        // Création d'un joueur ADVENTURER
        player p = new player("Zakaria", "Taoussi", "ADVENTURER", 25, new ArrayList<>());

        // Configuration des points de vie
        p.healthpoints = 100;
        p.currenthealthpoints = 49; // < 50 (la moitié)

        // Donner de l'XP pour atteindre niveau 3
        UpdatePlayer.addXp(p, 29);
        assertEquals(3, p.retrieveLevel());

        // Test de la méthode
        UpdatePlayer.majFinDeTour(p);

        // Vérification : 49 + 2 = 51 (pas de -1 car niveau >= 3)
        assertEquals(51, p.currenthealthpoints);
    }
    @Test
    public void testMajFinDeTour_AdventurerNiveau2() {
        // Création d'un joueur ADVENTURER
        player p = new player("Zakaria", "Taoussi", "ADVENTURER", 25, new ArrayList<>());

        // Configuration des points de vie
        p.healthpoints = 100;
        p.currenthealthpoints = 49; // < 50 (la moitié)

        // Donner de l'XP pour atteindre niveau 3
        UpdatePlayer.addXp(p, 15);
        assertEquals(2, p.retrieveLevel());

        // Test de la méthode
        UpdatePlayer.majFinDeTour(p);

        // Vérification : 49 + 2 = 51 (pas de -1 car niveau >= 3)
        assertEquals(50, p.currenthealthpoints);
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
    @DisplayName("Test Retrieve Level 4")
    public void testRetrieveLevel4() {
        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        UpdatePlayer.addXp(p, 100);  // 100 est entre 57 et 111
        assertEquals(4, p.retrieveLevel());
    }

    @Test
    @DisplayName("Test Retrieve Level 5")
    public void testRetrieveLevel5() {
        player p = new player("Zakaria", "Taoussi", "ARCHER", 52, new ArrayList<>());
        UpdatePlayer.addXp(p, 150);  // 150 est supérieur à 111
        assertEquals(5, p.retrieveLevel());
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
    public void testMajFinDeTour_JoueurKO() {
        // Création d'un joueur
        player p = new player("Zakaria", "Taoussi", "ARCHER", 25, new ArrayList<>());

        // Configuration des points de vie
        p.healthpoints = 100;
        p.currenthealthpoints = 0;

        // Test de la méthode
        UpdatePlayer.majFinDeTour(p);

        // Vérification que les PV restent à 0 (joueur KO)
        assert p.currenthealthpoints == 0;
    }
    @Test
    public void testMajFinDeTour_JoueurPasKO() {
        // Création d'un joueur
        player p = new player("Zakaria", "Taoussi", "ARCHER", 25, new ArrayList<>());

        // Configuration des points de vie - joueur PAS KO
        p.healthpoints = 100;
        p.currenthealthpoints = 50; // > 0, donc pas KO

        // Test de la méthode
        UpdatePlayer.majFinDeTour(p);

        // Vérification que le joueur n'est pas KO (PV > 0)
        assert p.currenthealthpoints > 0;
    }

    @Test

    public void testAddXp_ResteMemeNiveau() {
        // Création d'un joueur
        ArrayList<String> inventory = new ArrayList<>();
        player testPlayer = new player("John", "Avatar", "ARCHER", 100, inventory);

        // Le joueur est niveau 1 avec 0 XP
        int currentLevel = testPlayer.retrieveLevel();

        // Ajouter un peu d'XP (pas assez pour monter de niveau)
        // Pour rester niveau 1, il faut moins de 10 XP (voir votre méthode retrieveLevel)
        boolean result = UpdatePlayer.addXp(testPlayer, 5);

        // Vérifier que le niveau n'a pas changé
        int newLevel = testPlayer.retrieveLevel();

        assertEquals(currentLevel, newLevel, "Le niveau doit rester le même");
        assertFalse(result, "La méthode doit retourner false quand pas de montée de niveau");
    }
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

}

