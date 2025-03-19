package org.project;

import org.project.entity.players.*;
import org.project.location.Location;
import org.project.object.armors.KnightArmor;
import org.project.object.armors.NoArmor;
import org.project.object.weapons.Dagger;
import org.project.object.weapons.Mace;
import org.project.object.weapons.Staff;
import org.project.object.weapons.Sword;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Location> locations = new ArrayList<>();
        Location ancientRuins = new Location("Ancient Ruins", "Crumbling stone ruins with hidden dangers.", 5, 2);
        Location darkForest = new Location("Dark Forest", "A foggy, eerie forest filled with lurking creatures.", 6, 3);
        Location undergroundCrypt = new Location("Underground Crypt", "Dark tunnels where skeletons and undead roam.", 4, 3);
        Location frozenWasteland = new Location("Frozen Wasteland", "An icy tundra where even breathing feels like a battle.", 2, 2);
        Location bloodstainedChapel = new Location("Bloodstained Chapel", "A haunted church with cursed spirits.", 3, 3);
        Location thunderPeak = new Location("Thunder Peak", "A mountaintop constantly struck by lightning.", 4, 2);
        Location ironPrison = new Location("Iron Prison", "A high-security dungeon for legendary warriors.", 5, 3);

        ironPrison.addConnection(darkForest);
        ironPrison.addConnection(undergroundCrypt);
        ironPrison.addConnection(bloodstainedChapel);
        ironPrison.addConnection(thunderPeak);
        ancientRuins.addConnection(darkForest);
        undergroundCrypt.addConnection(frozenWasteland);
        bloodstainedChapel.addConnection(thunderPeak);
        Location currentLocation = frozenWasteland;

        locations.add(ancientRuins);
        locations.add(darkForest);
        locations.add(undergroundCrypt);
        locations.add(frozenWasteland);
        locations.add(bloodstainedChapel);
        locations.add(thunderPeak);
        locations.add(ironPrison);

        int armorLocationIndex = (int) (Math.random() * locations.size());
        System.out.println(armorLocationIndex); // my cheat
        locations.get(armorLocationIndex).setArmorDrop(new KnightArmor());

        System.out.println("🌟 Welcome, traveler. Choose your path:");
        System.out.println("1. KNIGHT of Light ⚔️ - A strong warrior with heavy armor.");
        System.out.println("2. WIZARD 🧙 - A master of spells and magic.");
        System.out.println("3. ASSASSIN 🗡️ - A swift and deadly shadow.");
        System.out.println("4. CLERIC ✨ - A holy warrior who heals and blesses allies.");
        System.out.print("Enter your choice: ");

        Player player = null;
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                player = new Knight("The Knight of Light", 100, 50, 30, 4, 3, new Sword(), new NoArmor());
                break;
            case 2:
                player = new Wizard("The Arcane Master", 80, 40, 60, 3, 3, new Staff(), new NoArmor());
                break;
            case 3:
                player = new Assassin("The Silent Blade", 90, 50, 40, 3, 3, new Dagger(), new NoArmor());
                break;
            case 4:
                player = new Cleric("The Divine Guardian", 110, 40, 50, 4, 3, new Mace(), new NoArmor());
                break;
            default:
                System.out.println("❌ Invalid choice! Defaulting to Knight.");
                player = new Knight("The Knight of Light", 100, 50, 30, 4, 3, new Sword(), new NoArmor());
        }

        while (player.isAlive()) {
            System.out.println("\nWelcome to the " + currentLocation.getName()+"\n     \""+currentLocation.getDescription() + "\".");
            System.out.println("\nWhat do you want to do?");
            System.out.println("1. Move to another location 🚶");
            System.out.println("2. Look for enemies to fight ⚔️");
            System.out.println("3. Search for armor 🏺");
            System.out.println("4. Exit the game ❌");

            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
switch (choice) {
    case 1:
        System.out.println("\nAvailable Paths:");
        for (Location loc : currentLocation.getConnections()) {
            System.out.println("  ➝ " + loc.getName());
        }
        System.out.print("Enter the name of the location you wish to travel to: ");

        String destination = scanner.nextLine();
        Location nextLocation = currentLocation.getConnectedLocation(destination);

        if (nextLocation != null) {
            currentLocation.resetLocation();
            System.out.println("Enemies coming back as you go outside the area.");
            currentLocation = nextLocation;
            System.out.println("\n📖 As you step forward, the air around you shifts...");
            System.out.println("You arrive at **" + currentLocation.getName() + "**.");
            System.out.println(currentLocation.getDescription());
        } else {
            System.out.println("\n❌ There is no path leading there. Choose wisely.");
        }
        break;
    case 2:
        System.out.println("\n⚔️ You prepare your weapon, scanning the wasteland for enemies...");
        Combat.startFight(player, currentLocation, scanner);
        break;
    case 4:
        System.out.println("\nThe cold claims another soul as your journey ends...");
        break;
    case 3:
        if (currentLocation.getArmorDrop() instanceof NoArmor) {
            System.out.println("\n🏺 You search the area... but find nothing.");
        } else {
            System.out.println("\n🏺 You found " + currentLocation.getArmorDrop() + "!");
            System.out.println("Do you want to equip it? (yes/no)");

            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                player.equipArmor(currentLocation.getArmorDrop());
                currentLocation.removeArmor(); // Armor is no longer available in this location
            } else {
                System.out.println("❌ You leave the armor behind.");
            }
        }
        break;
    default:
        System.out.println("❌ Invalid choice! Try again.");

}


        }
    }
}