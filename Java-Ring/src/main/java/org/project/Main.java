package org.project;

import org.project.entity.players.Knight;
import org.project.entity.players.Player;
import org.project.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Location> locations = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        Location ancientRuins = new Location("Ancient Ruins", "Crumbling stone ruins with hidden dangers.", 3, 2);
        Location darkForest = new Location("Dark Forest", "A foggy, eerie forest filled with lurking creatures.", 2, 3);
        Location undergroundCrypt = new Location("Underground Crypt", "Dark tunnels where skeletons and undead roam.", 4, 3);
        Location frozenWasteland = new Location("Frozen Wasteland", "An icy tundra where even breathing feels like a battle.", 5, 2);
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

        System.out.println("🌟 Welcome, traveler. Choose your path:");
        System.out.println("1. KNIGHT of light ⚔️ - A strong warrior with heavy armor.");
        System.out.println("2. Wizard 🧙 - A master of spells and magic.");
        System.out.println("3. Assassin 🗡️ - A swift and deadly shadow.");
        System.out.print("Enter your choice: ");

        Player player = null;
        int choice = scanner.nextInt();
        switch (choice) {
//            case 2:
//                player = new Wizard("Wizard", 80, 100, null, null);
//                break;
//            case 3:
//                player = new Assassin("Assassin", 90, 60, null, null);
//                break;
            default:
                System.out.println("❌ Invalid choice! Defaulting to Knight.");
                player = new Knight("The Knight of Light", 100, 50, null, null);
        }
        while (running) {
            System.out.println("\nWelcome to the " + currentLocation.getName()+"\n     \""+currentLocation.getDescription() + "\".");
            System.out.println("\nWhat do you want to do?");
            System.out.println("1. Move to another location 🚶");
            System.out.println("2. Look for enemies to fight ⚔️");
            System.out.println("3. Exit the game ❌");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 3) {
                System.out.println("\nThe cold claims another soul as your journey ends...");
                break;
            } else if (choice == 2) {
                System.out.println("\n⚔️ You prepare your weapon, scanning the wasteland for enemies...");
                fight(); // Placeholder function
            } else if (choice == 1) {
                // Show available locations
                System.out.println("\nAvailable Paths:");
                for (Location loc : currentLocation.getConnections()) {
                    System.out.println("  ➝ " + loc.getName());
                }
                System.out.print("Enter the name of the location you wish to travel to: ");

                String destination = scanner.nextLine();
                Location nextLocation = currentLocation.getConnectedLocation(destination);

                if (nextLocation != null) {
                    currentLocation = nextLocation;
                    System.out.println("\n📖 As you step forward, the air around you shifts...");
                    System.out.println("You arrive at **" + currentLocation.getName() + "**.");
                    System.out.println(currentLocation.getDescription());
                } else {
                    System.out.println("\n❌ There is no path leading there. Choose wisely.");
                }
            } else {
                System.out.println("❌ Invalid choice! Try again.");
            }

        }
    }
}