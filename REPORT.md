# **Java-Ring: A Turn-Based RPG in Java**

A **Souls-like inspired** text-based RPG designed to reinforce **Object-Oriented Programming (OOP)** concepts.

## **Description**

Java-Ring is a **terminal-based turn-based RPG** where players select warrior classes, battle against various monsters, explore different locations, and collect weapons and armors. The game is designed for **educational purposes** to strengthen **OOP principles** like **inheritance, polymorphism, and interfaces**.

The game features:
- **Turn-based combat** where players and enemies take turns attacking.
- **Multiple warrior classes** (Knight, Wizard, Assassin, Cleric) with unique abilities.
- **Different enemy types** (Skeleton, Goblin, Dragon, and more) with distinct attack patterns.
- **Weapon and armor system** to equip and switch gear during combat.
- **Inventory system** for collecting consumables, weapons, and armors.
- **Defensive mechanics** to block and reduce damage.
- **Exploration system** to move between locations and find hidden gear.

## **Getting Started**

### **Dependencies**
- **Java 17+** (or any compatible Java version)
- **Gradle** for building the project
- **Windows/Linux/MacOS** (compatible with all platforms)

### **Installing**
1. Clone the repository:
   ```
   git clone https:
   ```
2. Navigate to the project folder:
   ```
   cd Java-Ring
   ```
3. Build the project using **Gradle**:
   ```
   gradle build
   ```
4. Run the game:
   ```
   gradle run
   ```

## **Executing the Program**
### **How to Play**
1. **Choose a class** (Knight, Wizard, Assassin, Cleric).
2. **Start the game** in the **Frozen Wasteland**.
3. **Explore different locations**, fight enemies, and collect items.
4. **Engage in turn-based combat**:
    - Select enemies to attack.
    - Choose between different attack types.
    - Use **defensive moves**, potions, and special abilities.
    - Switch weapons for strategic advantages.
5. **Defeat enemies** to clear locations.
6. **Find and equip weapons and armors** hidden in different areas.
7. **Survive and progress** through different enemy encounters.

## **Features Implemented So Far**
**Character selection**: Players can choose from multiple classes.  
**Turn-based combat system**: Players and enemies take turns attacking.  
**Super abilities for each class**: Unique powerful moves with cooldowns.  
**Enemies with distinct attack patterns**: Skeletons resurrect, Goblins attack sneakily, and Dragons have multi-target attacks.  
**Exploration and movement**: Players can navigate between locations.  
**Weapon switching during combat**: Players can change weapons mid-fight.  
**Armor and weapon finding system**: Players can discover and equip gear.  
**Defensive mechanics**: Players can block attacks and reduce damage.  
**Enemy spawning system**: Stronger enemies appear in high-level locations.

## **Upcoming Features**
**Dragon’s armor-bypass attack** (ignores shields and armor).  
**More enemies** (Golem, Drakola, Black Knight).  
**Weapon-specific abilities** (e.g., Dagger for speed, Mace for stuns).  
**Burn and Stun effects** (Fire attacks will apply burn damage, stuns will skip enemy turns).  
**Multiplayer mode** (Later implementation).  
**PvP Mode** (Player vs Player fights).

## **Help**
If you encounter issues, try running:
```
gradle clean build
```
For debugging, enable **stack traces**:
```
gradle run --stacktrace
```

## **Authors**
- **Sadeghizad**
- Contributors: Open for contributions!

## **Version History**
* **0.3**
    - Implemented **defensive mechanics**, **armor system**, and **weapon switching**.
    - Added **Cleric class** with healing abilities.
    - Improved **enemy AI** to make decisions dynamically.

* **0.2**
    - Implemented **super abilities** for different warrior classes.
    - Added **Goblin, Skeleton, and Dragon** as enemies.

* **0.1**
    - Basic **turn-based combat** system.
    - Initial **player classes (Knight, Wizard, Assassin)**.
    - **Exploration and movement** between locations.

## **License**
This project is licensed under the **MIT License** – see the **LICENSE.md** file for details.

## **Acknowledgments**
- **Souls-like games** for inspiration.
- **Java & OOP Principles** for structuring the game.
- **GitHub community** for resources and templates.

**The game is still evolving—stay tuned for new updates!** 🚀