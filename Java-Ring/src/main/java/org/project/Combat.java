package org.project;

import org.project.entity.enemies.Enemy;
import org.project.entity.enemies.Skeleton;
import org.project.entity.players.Player;
import org.project.location.Location;
import org.project.object.armors.NoArmor;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Combat {
    private static final Random random = new Random();

    public static void startFight(Player player, Location currentLocation, Scanner scanner) {
        List<Enemy> enemies = currentLocation.getEnemies();
        if (enemies == null || enemies.isEmpty()) {
            System.out.println("\n⚔️ You scan the area, but no enemies remain.");
            return;
        }

        System.out.println("\n⚔️ Battle begins!");

        // **Combat Loop** (Runs until player dies or all enemies are defeated)
        while (player.getHp() > 0 && !enemies.isEmpty()) {
            // Display enemies with health bars
            System.out.println("\n🛡️ Enemies in battle:");
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                System.out.println((i + 1) + "️⃣ " + enemy.getEnemyType() + " - ❤️ " + enemy.getHp() + "/" + enemy.getMaxHP());
            }

            // **Ask player to attack**
            int enemyChoice;
            Enemy selectedEnemy = null;
            while (true) {
                System.out.print("\nEnter the number of the enemy you wish to attack: ");
                enemyChoice = scanner.nextInt() - 1;
                scanner.nextLine();

                if (enemyChoice >= 0 && enemyChoice < enemies.size()) {
                    selectedEnemy = enemies.get(enemyChoice);
                    break;
                } else {
                    System.out.println("\n❌ Invalid choice! Try again.");
                }
            }

            playerAttack(player, selectedEnemy, scanner);

            // **Remove defeated enemies (Including Resurrection for Skeletons)**
            enemies.removeIf(enemy -> {
                if (enemy.getHp() <= 0) {
                    if (enemy instanceof Skeleton) {
                        Skeleton skeleton = (Skeleton) enemy;
                        if (!skeleton.hasResurrected()) {
                            skeleton.resurrect();
                            System.out.println("\n☠️ The " + skeleton.getEnemyType() + " pulls itself back together!");
                            return false; // **Skeleton stays in fight**
                        }
                    }
                    return true; // **Other enemies are permanently removed**
                }
                return false;
            });

            // **Check if all enemies are defeated**
            if (enemies.isEmpty()) {
                System.out.println("\n🏆 Victory! The area is now safe.");

                // 30% chance to repair armor
                if (Math.random() < 0.3) { // 30% probability
                    if (player.getArmor() != null && player.getArmor().isBroke()) {
                        System.out.println("\n🔧 You find materials to repair your armor!");
                        player.getArmor().repair();
                        System.out.println("🛡️ Your armor has been partially restored.");
                    } else if (player.getArmor() != null && !(currentLocation.getArmorDrop() instanceof NoArmor)) {
                        System.out.println("\n🔧 You find repair materials, but you have no armor to repair.");
                    }
                }

                break;
            }


            // **Regenerate FP & MP, Reduce Cooldowns**
            player.regainFP(5);
            player.fillMana(3);
            player.reduceSuperCooldown(); // **Reduce cooldown every turn**

            for (Enemy enemy : enemies) {
                enemy.regainFP(3);
                enemy.fillMana(2);
                enemy.reduceSuperCooldown();
            }

            // **Enemy Turn**
            enemyTurn(enemies, player);

            // **Check if the player has been defeated**
            if (player.getHp() <= 0) {
                System.out.println("\n💀 You have fallen in battle...");
                player.die();
                return;
            }
        }
    }


    private static void playerAttack(Player player, Enemy enemy, Scanner scanner) {
        System.out.println("\n👤 Player Stats:");
        System.out.println("❤️ HP: " + player.getHp() + "/" + player.getMaxHP());
        System.out.println("⚡ FP: " + player.getFp() + "/" + player.getMaxFP());
        System.out.println("🔮 MP: " + player.getMp() + "/" + player.getMaxMP());
        System.out.println("⏳ Super Ability Cooldown: " + player.getSuperAbilityCooldown() + " turns");
        System.out.println("⚔️ Weapon: " + player.getWeapon());
        System.out.println("🛡️ Armor: " + (player.getArmor() != null ? player.getArmor() : "No Armor"));
        System.out.println("🩹 Flask: " + (player.getFlasks()));
        System.out.println("\n⚔️ Choose your action:");
        System.out.println("1️⃣ Normal Attack (Low cost)");
        System.out.println("2️⃣ Heavy Attack / Charged Magic (High cost)");
        System.out.println("3️⃣ Super Ability (Requires charge)");
        System.out.println("4️⃣ Weapon Ability (Requires charge)");
        System.out.println("5️⃣ Use HP Flask 🩹");
        System.out.println("6️⃣ Defend 🛡️ (Reduce damage from next attack)");

        System.out.print("Enter your choice: ");
        boolean attackSuccessful = false;
        while (!attackSuccessful){
            boolean isDefending = false; // Track if player is defending
            int attackChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline


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
                        player.attack(enemy);
                        player.useStamina(15); // Heavy attack costs 15 FP
                        attackSuccessful = true;
                    } else {
                        System.out.println("\n❌ Not enough FP for a heavy attack!");
                    }
                    break;

                case 3:
                    if (player.getSuperAbilityCooldown() == 0 && player.getFp() >= 25 && player.getMp() >= 20) {
                        System.out.println("\n⚡ You unleash your ultimate power!");
                        enemy.takeDamage(player.getWeapon().getDamage() * 2);
                        player.useStamina(25);
                        player.useMana(20);
                        player.setSuperAbilityCooldown(3); // Set cooldown
                        attackSuccessful = true;
                    } else if (player.getSuperAbilityCooldown() > 0) {
                        System.out.println("\n❌ Your super ability is on cooldown for " + player.getSuperAbilityCooldown() + " more turns!");
                    } else {
                        System.out.println("\n❌ Not enough resources(fp/mp) for a super ability!");
                    }
                    break;
                case 4:
                    if (player.getWeapon().getAbilityCharge() >= 3 && player.getFp() >= 20 && player.getMp() >= 25) {
                        System.out.println("\n⚡ You activate your weapon power!");
                        player.abilityAttack(enemy);
                        player.useStamina(20);
                        player.useMana(25);
                        attackSuccessful = true;
                    } else if (player.getWeapon().getAbilityCharge() > 0) {
                        System.out.println("\n❌ Your weapon ability is not gathered yet you need " + Math.min(3-player.getWeapon().getAbilityCharge(),1) + " more attacks to charge it!");
                    } else {
                        System.out.println("\n❌ Not enough resources(fp/mp) for a weapon ability!");
                    }
                    break;
                case 5:
                    if (player.hasFlask()) {
                        player.useFlask();
                        attackSuccessful = true;
                    } else {
                        System.out.println("\n❌ You have no flasks left!");
                    }
                    break;

                case 6:
                    if (player.getFp() >= 10) { // Defending costs stamina
                        System.out.println("\n🛡️ You brace for impact, preparing to defend the next attack!");
                        player.defend();
                        attackSuccessful = true;
                    } else {
                        System.out.println("\n❌ Not enough FP to defend!");
                    }
                    break;

                    default:
                        System.out.println("\n❌ Invalid attack choice!");
            }
        }
    }

    private static void enemyTurn(List<Enemy> enemies, Player player) {
        if (enemies.isEmpty()) return;

        Enemy attackingEnemy = enemies.get(random.nextInt(enemies.size()));
        System.out.println("\n⚠️ " + attackingEnemy.getEnemyType() + " prepares an attack...");

        boolean attackSuccess=false;
        while (!attackSuccess){
            int enemyAction = random.nextInt(3); // 0 = Normal, 1 = Heavy/Super, 2 = Buff

            switch (enemyAction) {
                case 0:
                    System.out.println("⚔️ " + attackingEnemy.getEnemyType() + " attacks you!");
                    attackingEnemy.attack(player);
                    attackSuccess=true;
                    break;

                case 1:
                    if (attackingEnemy.getSuperAbilityCooldown() == 0 && attackingEnemy.getFp() >= 15) {
                        System.out.println("💥 " + attackingEnemy.getEnemyType() + " unleashes a devastating attack!");
                        player.takeDamage(attackingEnemy.getWeapon().getDamage() * 2);
                        attackingEnemy.useStamina(15);
                        attackingEnemy.setSuperAbilityCooldown(3);
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
                case 3:
                    if (attackingEnemy.getFp() >= 10) { // Defending costs stamina
                        attackingEnemy.defend();
                        attackSuccess = true;
                    } else {
                        System.out.println(attackingEnemy.getEnemyType() + " tried to defend but lacked stamina!");
                    }
                    break;
            }
        }
    }
}
