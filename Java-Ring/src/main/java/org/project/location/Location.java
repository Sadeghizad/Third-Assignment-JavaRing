package org.project.location;

import org.project.entity.enemies.Enemy;
import org.project.entity.enemies.Skeleton;

import java.util.ArrayList;

public class Location {
    private String name;
    private String description;
    private int enemyMaxLevel;
    private int numEnemies;

    private ArrayList<Location> connections;
    private ArrayList<Enemy> enemies;

    public Location(String name, String description, int enemyMaxLevel, int numEnemies) {
        this.name = name;
        this.enemyMaxLevel = enemyMaxLevel;
        this.numEnemies = numEnemies;
        this.description = description;
    }

    private Enemy spawnRandomEnemy(int enemyMaxLevel) {
        int rand = (int) (Math.random() * enemyMaxLevel);

        switch (rand) {
//            case 0:
//                return new Goblin();
            default:
                return new Skeleton();
        }
    }


    public void resetLocation() {
        enemies.clear();

        for (int i = 0; i < numEnemies; i++) {
            enemies.add(spawnRandomEnemy(this.enemyMaxLevel)); // Spawn fresh enemies
        }
    }

    public void addConnection(Location location) {
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
}
