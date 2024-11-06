# CSE215_SECTION_7_GROUP3
Project on Text-Based RPG Game
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DungeonQuestGame {
    private Player player;
    private Room currentRoom;
    private List<Room> rooms;

    public DungeonQuestGame() {
        player = new Player(100, 10);
        rooms = createRooms();
        currentRoom = rooms.get(0); // Start in the first room
    }

    public static void main(String[] args) {
        DungeonQuestGame game = new DungeonQuestGame();
        game.startGame();
    }

    public void startGame() {
        System.out.println("Welcome to Dungeon Quest!");

        boolean gameRunning = true;

        while (gameRunning) {
            System.out.println("\nYou are in: " + currentRoom.getName());

            if (!currentRoom.getEnemies().isEmpty()) {
                System.out.println("Enemies found!");
                attackEnemy();
            }

            if (!currentRoom.getTreasures().isEmpty()) {
                System.out.println("Treasures found!");
                collectTreasure();
            }

            if (currentRoom.hasTrap()) {
                System.out.println("You triggered a trap!");
                int trapDamage = currentRoom.activateTrap();
                player.reduceHealth(trapDamage);
                System.out.println("You took " + trapDamage + " damage from the trap!");
            }

            if (player.isDefeated()) {
                System.out.println("You were defeated! Game Over.");
                gameRunning = false;
                continue;
            }

            if (currentRoom == rooms.get(rooms.size() - 1)) {
                System.out.println("You reached the final room and completed the game! Congratulations!");
                gameRunning = false;
                continue;
            }

            System.out.println("Choose your next move:");
            System.out.println("1. Move to an adjacent room");
            System.out.println("2. View player status");
            System.out.println("3. Quit the game");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Choose a direction (north, south, east, west):");
                    String direction = scanner.nextLine().toLowerCase();
                    movePlayer(direction);
                    break;
                case 2:
                    showPlayerStatus();
                    break;
                case 3:
                    System.out.println("You quit the game. Game Over.");
                    gameRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void movePlayer(String direction) {
        Room nextRoom = currentRoom.getExits().get(direction);

        if (nextRoom == null) {
            System.out.println("No room in that direction.");
        } else {
            currentRoom = nextRoom;
            System.out.println("You entered: " + currentRoom.getName());

            int trapDamage = currentRoom.activateTrap();
            if (trapDamage > 0) {
                System.out.println("You triggered a trap and took " + trapDamage + " damage!");
                player.reduceHealth(trapDamage);
            }
        }
    }

    private void showPlayerStatus() {
        System.out.println("Player Status:");
        System.out.println("Name: " + player.getName());
        System.out.println("Health: " + player.getHealth());
    }

    private List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();

        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");

        room1.setExit("east", room2);
        room2.setExit("west", room1);
        room2.setExit("south", room3);
        room3.setExit("north", room2);

        Enemy enemy1 = new Enemy("Goblin", 20, 5);
        room2.addEnemy(enemy1);

        Treasure treasure1 = new Treasure("Healing Potion", 30);
        room1.addTreasure(treasure1);

        Trap trap1 = new Trap("Spike Trap", 10);
        room3.setTrap(trap1);

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        return rooms;
    }

    private void attackEnemy() {
        List<Enemy> enemies = currentRoom.getEnemies();
        int enemyCount = enemies.size();

        System.out.println("Choose an enemy to attack:");

        for (int i = 0; i < enemyCount; i++) {
            Enemy enemy = enemies.get(i);
            System.out.println((i + 1) + ". " + enemy.getName() + " (HP: " + enemy.getHealth() + ")");
        }

        Scanner scanner = new Scanner(System.in);
        int enemyChoice = scanner.nextInt();
        scanner.nextLine();

        if (enemyChoice >= 1 && enemyChoice <= enemyCount) {
            Enemy selectedEnemy = enemies.get(enemyChoice - 1);

            int playerDamage = player.attack(selectedEnemy);
            int enemyDamage = selectedEnemy.attack();

            System.out.println("You attacked " + selectedEnemy.getName() + " and dealt " + playerDamage + " damage.");
            System.out.println(selectedEnemy.getName() + " counterattacked and dealt " + enemyDamage + " damage.");

            selectedEnemy.reduceHealth(playerDamage);
            player.reduceHealth(enemyDamage);

            if (selectedEnemy.isDefeated()) {
                System.out.println("You defeated " + selectedEnemy.getName() + "!");
                enemies.remove(selectedEnemy);
            }

            if (player.isDefeated()) {
                System.out.println("You were defeated! Game Over.");
            }
        } else {
            System.out.println("Invalid choice. Try again.");
        }
    }

    private void collectTreasure() {
        List<Treasure> treasures = currentRoom.getTreasures();
        int treasureCount = treasures.size();

        System.out.println("Choose a treasure to collect:");

        for (int i = 0; i < treasureCount; i++) {
            Treasure treasure = treasures.get(i);
            System.out.println((i + 1) + ". " + treasure.getName() + " (" + treasure.getValue() + " points)");
        }

        Scanner scanner = new Scanner(System.in);
        int treasureChoice = scanner.nextInt();
        scanner.nextLine();

        if (treasureChoice >= 1 && treasureChoice <= treasureCount) {
            Treasure selectedTreasure = treasures.get(treasureChoice - 1);

            player.collectTreasure(selectedTreasure);
            System.out.println("You collected " + selectedTreasure.getName() + "!");

            treasures.remove(selectedTreasure);
        } else {
            System.out.println("Invalid choice. Try again.");
        }
    }
}

// Enemy class
class Enemy {
    private String name;
    private int health;
    private int attackDamage;

    public Enemy(String name, int health, int attackDamage) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int attack() {
        return this.attackDamage;
    }

    public void reduceHealth(int damage) { 
        this.health -= damage;
    }

    public boolean isDefeated() {
        return this.health <= 0;
    }
}

// Player class
class Player {
    private String name;
    private int health;
    private int attackDamage;

    public Player(int health, int attackDamage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        this.name = scanner.nextLine();
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int attack(Enemy enemy) {
        int damageDealt = this.attackDamage;
        enemy.reduceHealth(damageDealt);
        return damageDealt;
    }

    public void reduceHealth(int damage) {
        this.health -= damage;
    }

    public boolean isDefeated() {
        return this.health <= 0;
    }

    public void collectTreasure(Treasure treasure) {
        System.out.println("Collected " + treasure.getName() + " worth " + treasure.getValue() + " points.");
    }
}

// Room class
class Room {
    private String name;
    private Map<String, Room> exits;
    private List<Enemy> enemies;
    private List<Treasure> treasures;
    private Trap trap;

    public Room(String name) {
        this.name = name;
        this.exits = new HashMap<>();
        this.enemies = new ArrayList<>();
        this.treasures = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public Map<String, Room> getExits() {
        return this.exits;
    }

    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    public List<Treasure> getTreasures() {
        return this.treasures;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addTreasure(Treasure treasure) {
        treasures.add(treasure);
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public void setTrap(Trap trap) {
        this.trap = trap;
    }

    public boolean hasTrap() {
        return this.trap != null;
    }

    public int activateTrap() {
        return hasTrap() ? trap.getDamage() : 0;
    }
}

// Treasure class
class Treasure {
    private String name;
    private int value;

    public Treasure(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }
}

// Trap class
class Trap {
    private String name;
    private int damage;

    public Trap(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }
}

