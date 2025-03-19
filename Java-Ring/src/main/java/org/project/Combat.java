package org.project;

import org.project.entity.enemies.BlackKnight;
import org.project.entity.enemies.Enemy;
import org.project.entity.enemies.Skeleton;
import org.project.entity.players.Player;
import org.project.location.Location;
import org.project.object.armors.NoArmor;
import org.project.object.weapons.Weapon;

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

        
        while (player.getHp() > 0 && !enemies.isEmpty()) {
            
            System.out.println("\n🛡️ Enemies in battle:");
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                System.out.println((i + 1) + "️⃣ " + enemy.getEnemyType() + " - ❤️ " + enemy.getHp() + "/" + enemy.getMaxHP());
            }

            
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

            if (player.isStunned()) {
                System.out.println("⚡ You are stunned and cannot move this turn!");
                player.reduceStunDuration();
            } else {
                playerAttack(player, selectedEnemy, scanner);
            }

            
            enemies.removeIf(enemy -> {
                if (enemy.getHp() <= 0) {
                    if (enemy instanceof Skeleton) {
                        Skeleton skeleton = (Skeleton) enemy;
                        if (!skeleton.hasResurrected()) {
                            skeleton.resurrect();
                            System.out.println("\n☠️ The " + skeleton.getEnemyType() + " pulls itself back together!");
                            return false; 
                        }
                    }
                    if (enemy instanceof BlackKnight) {
                        BlackKnight blackKnight = (BlackKnight) enemy;
                        if (!BlackKnight.isReaperDropped()) {
                            System.out.println("\n💀 The Black Knight collapses, his grip on the **Reaper** loosening...");
                            System.out.println("🪦 You may now claim **The Reaper**.");
                            BlackKnight.ReaperDropped();
                        }
                        return true;

                    }
                    return true; 
                }
                return false;
            });

            
            if (enemies.isEmpty()) {
                System.out.println("\n🏆 Victory! The area is now safe.");

                
                if (Math.random() < 0.3) { 
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


            
            player.handleBurnEffect();
            player.regainFP(5);
            player.fillMana(3);
            player.reduceSuperCooldown();
            for (Enemy enemy : enemies) {
                enemy.handleBurnEffect();
                enemy.regainFP(3);
                enemy.fillMana(2);
                enemy.reduceSuperCooldown();
            }

            
            enemyTurn(enemies, player);

            
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
        System.out.println("7️⃣ switching weapon ⚔️ (this will have a secret glitch 😈)");

        System.out.print("Enter your choice: ");
        boolean attackSuccessful = false;
        while (!attackSuccessful){
            boolean isDefending = false; 
            int attackChoice = scanner.nextInt();
            scanner.nextLine(); 


            switch (attackChoice) {
                case 1:
                    if (player.getFp() >= 5) {
                        player.attack(enemy);
                        player.useStamina(5); 
                        attackSuccessful = true;
                    } else {
                        System.out.println("\n❌ Not enough FP for a normal attack!");
                    }
                    break;

                case 2:
                    if (player.getFp() >= 15) {
                        player.attack(enemy);
                        player.attack(enemy);
                        player.useStamina(15); 
                        attackSuccessful = true;
                    } else {
                        System.out.println("\n❌ Not enough FP for a heavy attack!");
                    }
                    break;

                case 3:
                    if (player.getSuperAbilityCooldown() == 0 && player.getFp() >= 25 && player.getMp() >= 20) {
                        player.SuperAbility(enemy);
                        attackSuccessful = true;
                    } else if (player.getSuperAbilityCooldown() > 0) {
                        System.out.println("\n❌ Your super ability is on cooldown for " + player.getSuperAbilityCooldown() + " more turns!");
                    } else {
                        System.out.println("\n❌ Not enough resources(fp/mp) for a super ability!");
                    }
                    break;
                case 4:
                    if ( player.getFp() >= 20 && player.getMp() >= 25) {
                        System.out.println("\n⚡ You activate your weapon power!");
                        player.abilityAttack(enemy);
                        player.useStamina(20);
                        player.useMana(25);
                        attackSuccessful = true;
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
                    if (player.getFp() >= 10) { 
                        System.out.println("\n🛡️ You brace for impact, preparing to defend the next attack!");
                        player.defend();
                        attackSuccessful = true;
                    } else {
                        System.out.println("\n❌ Not enough FP to defend!");
                    }
                    break;

                    default:
                        System.out.println("\n❌ Invalid attack choice!");
                case 7:
                    System.out.println("\n🗡️ Choose a weapon to equip:");
                    List<Weapon> inventory = player.getWeaponInventory();
                    for (int i = 0; i < inventory.size(); i++) {
                        System.out.println((i + 1) + "️⃣ " + inventory.get(i));
                    }
                    while (!player.changed) {


                    System.out.print("Enter your choice: ");
                    int weaponChoice = scanner.nextInt() - 1;
                    scanner.nextLine();

                    player.switchWeapon(weaponChoice);
                    }
                    player.changed=false;
                    break;
            }
        }
    }

    private static void enemyTurn(List<Enemy> enemies, Player player) {
        if (enemies.isEmpty()) return;

        Enemy attackingEnemy = enemies.get(random.nextInt(enemies.size()));
        System.out.println("\n⚠️ " + attackingEnemy.getEnemyType() + " prepares an attack...");

        boolean attackSuccess=false;
        while (!attackSuccess){
            int enemyAction = random.nextInt(3); 

            switch (enemyAction) {
                case 0:
                    System.out.println("⚔️ " + attackingEnemy.getEnemyType() + " attacks you!");
                    attackingEnemy.attack(player);
                    attackSuccess=true;
                    break;

                case 1:
                    if (attackingEnemy.getSuperAbilityCooldown() == 0) {
                        System.out.println("💥 " + attackingEnemy.getEnemyType() + " unleashes a devastating attack!");
                        attackingEnemy.abilityAttack(Player.getPlayers());
                        attackSuccess=true;

                    } else {
                        System.out.println(attackingEnemy.getEnemyType() + " tried to use a heavy attack but lacked stamina!");
                    }
                    break;

                case 2:
                    if (attackingEnemy.getWeapon().getAbilityCharge() >= 3 && player.getFp() >= 20 && player.getMp() >= 25) {
                        System.out.println("\n⚡ "+attackingEnemy.getEnemyType()+" activate his weapon power!");
                        attackingEnemy.getWeapon().useAbility(player);
                        attackSuccess = true;
                    }
                    break;
                case 3:
                    if (attackingEnemy.getFp() >= 10) { 
                        attackingEnemy.defend();
                        attackSuccess = true;
                    }
                    break;
            }
        }
    }
}
