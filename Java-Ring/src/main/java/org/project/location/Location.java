package org.project.location;

import org.project.entity.enemies.*;
import org.project.object.armors.Armor;
import org.project.object.armors.NoArmor;
import org.project.object.weapons.Fist;
import org.project.object.weapons.Weapon;

import java.util.ArrayList;

public class Location {
    private String name;
    private String description;
    private int enemyMaxLevel;
    private int numEnemies;
    private Armor armorDrop = new NoArmor(); // Default: No armor
    private Weapon weaponDrop = new Fist();  // Default: No weapon

    private ArrayList<Location> connections;
    private ArrayList<Enemy> enemies;

    public Location(String name, String description, int enemyMaxLevel, int numEnemies) {
        this.name = name;
        this.description = description;
        this.enemyMaxLevel = enemyMaxLevel;
        this.numEnemies = numEnemies;
        this.connections = new ArrayList<>();
        this.enemies = new ArrayList<>();
        for (int i = 0; i < numEnemies; i++) {
            enemies.add(spawnRandomEnemy(this.enemyMaxLevel));
        }
    }



    private Enemy spawnRandomEnemy(int enemyMaxLevel) {
        int rand = (int) (Math.random() * enemyMaxLevel) + 1;

        switch (rand) {
            case 1: return new Skeleton();
            case 2: return new Goblin();
            case 3: return new Zombie();
            case 4: return new Golem();
            case 5: return new Drakola();
            case 6: return new BlackKnight();
            case 7: return new Dragon();
            default: return new Skeleton(); // Fallback in case of errors
        }

    }


    public void resetLocation() {
        enemies.clear();

        for (int i = 0; i < numEnemies; i++) {
            enemies.add(spawnRandomEnemy(this.enemyMaxLevel)); // Spawn fresh enemies
        }
    }

    public void addConnection(Location location) {
        if (connections == null) {
            connections = new ArrayList<>();
        }

        if (!connections.contains(location)) {
            connections.add(location);
            location.addConnection(this); // Ensure bidirectional movement
        }
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public Location getConnectedLocation(String locationName) {
        for (Location loc : connections) {
            if (loc.getName().equalsIgnoreCase(locationName)) {
                return loc;
            }
        }
        return null; // No matching location found
    }

    public ArrayList<Location> getConnections() {
        return connections;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Armor getArmorDrop() {
        return armorDrop;
    }

    public void setArmorDrop(Armor armor) {
        this.armorDrop = armor;
    }

    public void removeArmor() {
        this.armorDrop = new NoArmor();
    }

    public Weapon getWeaponDrop() {
        return weaponDrop;
    }

    public void setWeaponDrop(Weapon weapon) {
        this.weaponDrop = weapon;
    }

    public void removeWeapon() {
        this.weaponDrop = new Fist();
    }
}
