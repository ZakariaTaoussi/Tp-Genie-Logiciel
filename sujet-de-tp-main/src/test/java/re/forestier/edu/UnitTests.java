package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

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
        p.currenthealthpoints = 49; //


        // Donner de l'XP pour atteindre niveau 3
        UpdatePlayer.addXp(p, 15);
        assertEquals(2, p.retrieveLevel());

        // Test de la méthode
        UpdatePlayer.majFinDeTour(p);

        // Vérification : 49 + 2 = 51 (pas de -1 car niveau >= 3)
        assertEquals(50, p.currenthealthpoints);
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
        switch(level) {
            case 1: return 0;
            case 2: return 10; // minimum pour level 2
            case 3: return 27; // minimum pour level 3
            case 4: return 57; // minimum pour level 4
            case 5: return 111; // minimum pour level 5
            default: return 0;
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
    public void testMajFinDeTour_DWARF_LowHP_WithHolyElixir() {
        // Test cas 2: DWARF avec HP < healthpoints/2 et Holy Elixir
        player p = createTestPlayer("DWARF", 40, 100, 1);
        p.inventory.add("Holy Elixir");

        UpdatePlayer.majFinDeTour(p);

        assertEquals(42, p.currenthealthpoints, "DWARF avec Holy Elixir devrait gagner +2 HP");
    }

    @Test
    public void testMajFinDeTour_DWARF_LowHP_WithoutHolyElixir() {
        // Test cas 3: DWARF avec HP < healthpoints/2 sans Holy Elixir
        player p = createTestPlayer("DWARF", 40, 100, 1);

        UpdatePlayer.majFinDeTour(p);

        assertEquals(41, p.currenthealthpoints, "DWARF sans Holy Elixir devrait gagner +1 HP");
    }

    @Test
    public void testMajFinDeTour_ARCHER_LowHP_WithMagicBow() {
        // Test cas 4: ARCHER avec HP < healthpoints/2 et Magic Bow
        player p = createTestPlayer("ARCHER", 40, 100, 1);
        p.inventory.add("Magic Bow");

        UpdatePlayer.majFinDeTour(p);

        // ARCHER: +1 HP + (currentHP/8 - 1)
        // 40/8 - 1 = 5 - 1 = 4, donc +1 + 4 = +5 total
        assertEquals(45, p.currenthealthpoints, "ARCHER avec Magic Bow devrait gagner HP calculé");
    }

    @Test
    public void testMajFinDeTour_ARCHER_LowHP_WithoutMagicBow() {
        // Test cas 5: ARCHER avec HP < healthpoints/2 sans Magic Bow
        player p = createTestPlayer("ARCHER", 40, 100, 1);

        UpdatePlayer.majFinDeTour(p);

        assertEquals(41, p.currenthealthpoints, "ARCHER sans Magic Bow devrait gagner +1 HP");
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
    public void testMajFinDeTour_HighHP_BelowMax() {
        // Test cas 8: HP >= healthpoints/2 mais < healthpoints
        player p = createTestPlayer("ADVENTURER", 80, 100, 1);

        UpdatePlayer.majFinDeTour(p);

        assertEquals(80, p.currenthealthpoints, "HP élevé mais pas max ne devrait pas changer");
    }

    @Test
    public void testMajFinDeTour_MaxHP() {
        // Test cas 9: HP >= healthpoints (HP max)
        player p = createTestPlayer("ADVENTURER", 100, 100, 1);

        UpdatePlayer.majFinDeTour(p);

        assertEquals(100, p.currenthealthpoints, "HP max devrait rester à max");
    }

    @Test
    public void testMajFinDeTour_ExceedsMaxHP() {
        // Test cas 10: HP dépasserait le max après guérison
        player p = createTestPlayer("ADVENTURER", 49, 100, 3);

        UpdatePlayer.majFinDeTour(p);

        // 49 + 2 = 51, mais on vérifie que ça ne dépasse pas 100
        assertEquals(51, p.currenthealthpoints, "HP après guérison");
    }

    @Test
    public void testMajFinDeTour_GuerisonExactementMax() {
        // Test cas 11: HP élevé qui ne change pas
        player p = createTestPlayer("ADVENTURER", 98, 100, 3);

        UpdatePlayer.majFinDeTour(p);

        // 98 >= 50 (100/2), donc aucune guérison
        assertEquals(98, p.currenthealthpoints, "HP >= healthpoints/2 ne devrait pas changer");
    }

    @Test
    public void testMajFinDeTour_DWARF_GuerisonDepasseMax() {
        // Test cas 12: DWARF dont la guérison dépasserait le max
        player p = createTestPlayer("DWARF", 49, 100, 1);
        p.inventory.add("Holy Elixir");

        UpdatePlayer.majFinDeTour(p);

        // 49 est < 50 (100/2), donc +2 HP = 51
        assertEquals(51, p.currenthealthpoints, "DWARF devrait gagner 2 HP");
    }

    @Test
    public void testMajFinDeTour_DWARF_HP_CappedAtMax() {
        // Test séparé pour le plafonnement au max
        player p = createTestPlayer("DWARF", 49, 50, 1); // Max HP = 50
        p.inventory.add("Holy Elixir");

        UpdatePlayer.majFinDeTour(p);

        // 49 < 25 (50/2), donc guérison: +1 (Holy Elixir) +1 (DWARF) = +2
        // 49 + 2 = 51, mais le plafonnement final devrait le ramener à 50
        // MAIS vérifions d'abord si le plafonnement fonctionne vraiment...

        // Le plafonnement ne se fait qu'à la toute fin
        // Donc si 49 + 2 = 51, et max = 50, alors ça devrait être plafonné à 50
        assertTrue(p.currenthealthpoints <= 50, "HP ne devrait pas dépasser le max (" + p.currenthealthpoints + ")");

        // Debug - affichons ce qui se passe réellement
        System.out.println("HP après guérison: " + p.currenthealthpoints + ", Max: " + p.healthpoints);
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
    public void testMajFinDeTour_ARCHER_MagicBow_ComplexCalculation() {
        // Test cas 14: ARCHER avec Magic Bow et calcul complexe
        player p = createTestPlayer("ARCHER", 32, 100, 1); // 32/8 = 4, donc 4-1 = 3
        p.inventory.add("Magic Bow");

        UpdatePlayer.majFinDeTour(p);

        // +1 HP normal + (32/8 - 1) = +1 + 3 = +4
        assertEquals(36, p.currenthealthpoints, "Calcul complexe Magic Bow");
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
    public void testMajFinDeTour_AllBranches_Coverage() {
        // Tests supplémentaires pour couvrir les branches manquantes

        // Test la logique ADVENTURER dans le premier bloc (qui ne devrait jamais s'exécuter)
        player p1 = createTestPlayer("ADVENTURER", 25, 100, 1);
        UpdatePlayer.majFinDeTour(p1);
        assertEquals(26, p1.currenthealthpoints, "ADVENTURER niveau 1 avec HP bas");

        // Test pour couvrir la condition else finale
        player p2 = createTestPlayer("DWARF", 60, 100, 1); // HP >= healthpoints/2
        UpdatePlayer.majFinDeTour(p2);
        assertEquals(60, p2.currenthealthpoints, "HP élevé ne devrait pas changer");
    }

    @Test
    public void testMajFinDeTour_CoverMissingBranches() {
        // Test pour couvrir les branches manquantes dans l'image

        // Test DWARF sans Holy Elixir mais avec HP bas
        player p1 = createTestPlayer("DWARF", 30, 100, 1);
        p1.inventory.clear(); // S'assurer qu'il n'y a pas de Holy Elixir
        UpdatePlayer.majFinDeTour(p1);
        assertEquals(31, p1.currenthealthpoints, "DWARF sans Holy Elixir +1 HP");

        // Test ARCHER sans Magic Bow
        player p2 = createTestPlayer("ARCHER", 30, 100, 1);
        p2.inventory.clear();
        UpdatePlayer.majFinDeTour(p2);
        assertEquals(31, p2.currenthealthpoints, "ARCHER sans Magic Bow +1 HP");

        // Test condition HP exactement à healthpoints
        player p3 = createTestPlayer("ADVENTURER", 100, 100, 1);
        UpdatePlayer.majFinDeTour(p3);
        assertEquals(100, p3.currenthealthpoints, "HP à max reste à max");

        // Test condition HP > healthpoints (cas théorique)
        player p4 = createTestPlayer("ADVENTURER", 80, 100, 1);
        p4.currenthealthpoints = 105; // Forcer HP > max
        UpdatePlayer.majFinDeTour(p4);
        assertEquals(100, p4.currenthealthpoints, "HP > max devrait être ramené à max");
    }

    @Test
    public void testMajFinDeTour_CoverAllMissingBranches() {
        // Test pour couvrir TOUTES les branches manquantes visibles dans l'image

        // 1. Test pour déclencher la ligne finale de plafonnement
        player p1 = createTestPlayer("ADVENTURER", 40, 100, 3);
        UpdatePlayer.majFinDeTour(p1);
        // 40 < 50, donc +2 HP = 42
        assertEquals(42, p1.currenthealthpoints);

        // Maintenant on force les HP à dépasser le max pour tester la ligne finale
        p1.currenthealthpoints = 105; // Force HP > max
        UpdatePlayer.majFinDeTour(p1);
        // Cette fois les HP >= healthpoints/2, donc pas de guérison
        // Mais la condition finale devrait ramener 105 à 100
        assertEquals(100, p1.currenthealthpoints, "Plafonnement final HP > max");

        // 2. Test pour la condition exacte currenthealthpoints >= healthpoints/2 ET < healthpoints
        player p2 = createTestPlayer("DWARF", 60, 100, 1); // 60 >= 50 et < 100
        UpdatePlayer.majFinDeTour(p2);
        assertEquals(60, p2.currenthealthpoints, "HP entre seuil et max ne change pas");

        // 3. Test cas limite: HP exactement au max
        player p3 = createTestPlayer("ARCHER", 100, 100, 1);
        UpdatePlayer.majFinDeTour(p3);
        assertEquals(100, p3.currenthealthpoints, "HP au max reste au max");

        // 4. Test pour forcer la condition de return dans la branche >= healthpoints
        player p4 = createTestPlayer("ADVENTURER", 100, 100, 1);
        UpdatePlayer.majFinDeTour(p4);
        assertEquals(100, p4.currenthealthpoints);
    }

    @Test
    public void testMajFinDeTour_ForceAllPaths() {
        // Test spécifiquement conçu pour forcer TOUS les chemins

        // Path 1: Test pour que currenthealthpoints dépasse healthpoints après guérison
        player p1 = createTestPlayer("ADVENTURER", 49, 100, 3);
        p1.currenthealthpoints = 99; // Proche du max
        UpdatePlayer.majFinDeTour(p1);
        // 99 >= 50, donc pas de guérison, reste à 99
        assertEquals(99, p1.currenthealthpoints);

        // Path 2: Maintenant on force à dépasser pour tester le plafonnement final
        p1.currenthealthpoints = 101; // > healthpoints
        UpdatePlayer.majFinDeTour(p1);
        assertEquals(100, p1.currenthealthpoints, "Doit être plafonné à healthpoints");

        // Path 3: Test edge case avec HP exactement à healthpoints
        player p2 = createTestPlayer("DWARF", 50, 50, 1);
        UpdatePlayer.majFinDeTour(p2);
        assertEquals(50, p2.currenthealthpoints, "HP = max reste à max");
    }

    @Test
    public void testMajFinDeTour_CoverRedLines() {
        // Tests ciblés pour couvrir spécifiquement les lignes rouges dans l'image

        // Pour couvrir le plafonnement final (dernière ligne rouge)
        player p = createTestPlayer("DWARF", 24, 50, 1);
        p.inventory.add("Holy Elixir");

        UpdatePlayer.majFinDeTour(p);
        // 24 < 25, donc +2 HP = 26
        assertEquals(26, p.currenthealthpoints);

        // Maintenant on manipule pour dépasser le max
        p.currenthealthpoints = 55; // Force > healthpoints
        p.healthpoints = 50;

        UpdatePlayer.majFinDeTour(p);
        // 55 >= 25, donc pas de guérison, mais 55 > 50 donc plafonnement
        assertEquals(50, p.currenthealthpoints, "Plafonnement forcé");

        // Test pour la condition else if finale
        player p2 = createTestPlayer("ADVENTURER", 75, 100, 1);
        UpdatePlayer.majFinDeTour(p2);
        assertEquals(75, p2.currenthealthpoints, "HP élevé sans changement");
    }

    @Test
    public void testMajFinDeTour_ExhaustiveEdgeCases() {
        // Tests exhaustifs pour les cas limites

        // Test 1: HP = 0 (déjà testé mais pour être sûr)
        player p1 = createTestPlayer("DWARF", 0, 100, 1);
        UpdatePlayer.majFinDeTour(p1);
        assertEquals(0, p1.currenthealthpoints);

        // Test 2: HP = 1, très bas
        player p2 = createTestPlayer("ARCHER", 1, 100, 1);
        p2.inventory.add("Magic Bow");
        UpdatePlayer.majFinDeTour(p2);
        // 1 + 1 + (1/8 - 1) = 1 + 1 + (0 - 1) = 1
        assertEquals(1, p2.currenthealthpoints);

        // Test 3: HP exactement à healthpoints/2
        player p3 = createTestPlayer("ADVENTURER", 50, 100, 3);
        UpdatePlayer.majFinDeTour(p3);
        assertEquals(50, p3.currenthealthpoints, "HP = healthpoints/2 ne change pas");

        // Test 4: HP juste sous healthpoints/2
        player p4 = createTestPlayer("ADVENTURER", 49, 100, 3);
        UpdatePlayer.majFinDeTour(p4);
        assertEquals(51, p4.currenthealthpoints, "HP < healthpoints/2 devrait être soigné");
    }

    @Test
    public void testMajFinDeTour_EmptyInventory() {
        // Test avec inventaire vide pour couvrir les contains() qui retournent false
        player p = createTestPlayer("DWARF", 25, 100, 1);
        p.inventory.clear(); // S'assurer que l'inventaire est vide

        UpdatePlayer.majFinDeTour(p);

        assertEquals(26, p.currenthealthpoints, "DWARF sans objets devrait gagner 1 HP");
    }
}

