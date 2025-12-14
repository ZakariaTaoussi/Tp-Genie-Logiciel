package re.forestier.edu.rpg;

public class GestionItem {

    public static boolean addItem(player player, Item item) {
        int currentWeight = calculateCurrentWeight(player);

        if (currentWeight + item.getWeight() > player.maxWeight) {
            System.out.println("Inventaire trop lourd ! Impossible d'ajouter : " + item.getName());
            return false;
        }

        player.inventory.add(item);
        System.out.println("Objet ajouté : " + item.getName());
        return true;
    }

    public static void sell(player player, Item item) {
        if (player.inventory.contains(item)) {
            player.inventory.remove(item);


            try {
                player.money = WalletManager.add(player.money, item.getValue());
                System.out.println("Vendu : " + item.getName() + " pour " + item.getValue() + "$");
            } catch (Exception e) {
                System.out.println("Erreur lors de la transaction.");
            }
        } else {
            System.out.println("Objet non trouvé !");
        }
    }


    public static int calculateCurrentWeight(player player) {
        int total = 0;
        for (Item i : player.inventory) {
            total += i.getWeight();
        }
        return total;
    }

    public static boolean containsItemName(player player, String itemName) {
        for (Item i : player.inventory) {
            if (i.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }
}