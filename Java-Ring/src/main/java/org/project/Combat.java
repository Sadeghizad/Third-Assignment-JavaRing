package org.project;

import org.project.entity.enemies.Enemy;
import org.project.entity.players.Player;
import org.project.location.Location;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Combat {
    private static final Random random = new Random();

    public static void startFight(Player player, Location currentLocation, Scanner scanner) {
        List<Enemy> enemies = currentLocation.getEnemies();

        if (enemies.isEmpty()) {
            System.out.println("\n⚔️ You scan the area, but no enemies remain.");
            return;
        }

        System.out.println("\n⚔️ Battle begins! Choose an enemy to attack:");

        // Show enemies with health bars
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            System.out.println((i + 1) + "️⃣ " + enemy.getEnemyType() + " - ❤️ " + enemy.getHp() + "/" + enemy.getMaxHp());
        }

        System.out.print("Enter the number of the enemy you wish to attack: ");
        int enemyChoice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (enemyChoice < 0 || enemyChoice >= enemies.size()) {
            System.out.println("\n❌ Invalid choice!");
            return;
        }

        Enemy selectedEnemy = enemies.get(enemyChoice);
        playerAttack(player, selectedEnemy, scanner);


        // Remove defeated enemies
        enemies.removeIf(enemy -> enemy.getHp() <= 0);

        if (enemies.isEmpty()) {
            System.out.println("\n🏆 Victory! The area is now safe.");
        }
        // Enemy's turn if the player successfully attacked
        if (selectedEnemy.getHp() > 0) {
            enemyTurn(enemies, player);
        }
    }

    private static void playerAttack(Player player, Enemy enemy, Scanner scanner) {
        System.out.println("\n⚔️ Choose your attack:");
        System.out.println("1️⃣ Normal Attack (Low cost)");
        System.out.println("2️⃣ Heavy Attack / Charged Magic (High cost)");
        System.out.println("3️⃣ Super Ability (Requires charge)");
        System.out.print("Enter your choice: ");
        int attackChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean attackSuccessful = false;

        switch (attackChoice) {
            case 1:
                if (player.getFp() >= 5) {
                    player.attack(enemy);
                    player.useStamina(5); // Normal attack costs 5 FP
                    attackSuccessful = true;
                } else {
                    System.out.println("\n❌ Not enough FP for a normal attack!");
                }
                break;

            case 2:
                if (player.getFp() >= 15) {
                    player.attack(enemy);
                    player.useStamina(15); // Heavy attack costs 15 FP
                    attackSuccessful = true;
                } else {
                    System.out.println("\n❌ Not enough FP for a heavy attack!");
                }
                break;

            case 3:
                if (player.getFp() >= 25 && player.getMp() >= 20) {
                    System.out.println("\n⚡ You unleash your ultimate power!");
                    enemy.takeDamage(player.getWeapon().getDamage() * 2);
                    player.useStamina(25);
                    player.useMana(20);
                    attackSuccessful = true;
                } else {
                    System.out.println("\n❌ Not enough resources for a super ability!");
                }
                break;

            default:
                System.out.println("\n❌ Invalid attack choice!");
        }
    }

    private static void enemyTurn(List<Enemy> enemies, Player player) {
        if (enemies.isEmpty()) return;

        Enemy attackingEnemy = enemies.get(random.nextInt(enemies.size()));
        System.out.println("\n⚠️ " + attackingEnemy.getEnemyType() + " prepares an attack...");

        int enemyAction = random.nextInt(3); // 0 = Normal, 1 = Heavy/Super, 2 = Buff

        switch (enemyAction) {
            case 0:
                System.out.println("⚔️ " + attackingEnemy.getEnemyType() + " attacks you!");
                attackingEnemy.attack(player);
                break;

            case 1:
                if (attackingEnemy.getFp() >= 15) {
                    System.out.println("💥 " + attackingEnemy.getEnemyType() + " uses a heavy attack!");
                    player.takeDamage(attackingEnemy.getWeapon().getDamage() * 2);
                    attackingEnemy.useStamina(15);
                } else {
                    System.out.println(attackingEnemy.getEnemyType() + " tried to use a heavy attack but lacked stamina!");
                }
                break;

            case 2:
                if (attackingEnemy.getMp() >= 10 && !attackingEnemy.getEnemyType().equals("Skeleton")) {
                    System.out.println("🔮 " + attackingEnemy.getEnemyType() + " buffs itself!");
                    attackingEnemy.heal(10);
                    attackingEnemy.useMana(10);
                } else {
                    System.out.println(attackingEnemy.getEnemyType() + " attempted a buff but lacked MP!");
                }
                break;
        }
    }
}
