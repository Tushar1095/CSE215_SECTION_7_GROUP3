# CSE215_SECTION_7_GROUP3
Project on Text-Based RPG Game
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private Room currentRoom;
    private boolean gameOver;

    public Dungeon() {
        currentRoom = new Room("Starting Room");
        gameOver = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void printCurrentRoomInfo() {
        System.out.println("You are in the room: " + currentRoom.getName());
    }

    public void printActions() {
        System.out.println("Choose an action:");
        System.out.println("1. Move to another room");
        System.out.println("2. Attack enemy");
        System.out.println("3. Collect treasure");
    }

    public void processAction(String action) {
        // Process the player's action
        switch (action) {
            case "1":
                movePlayer();
                break;
            case "2":
                attackEnemy();
                break;
            case "3":
                collectTreasure();
                break;
            default:
                System.out.println("Invalid action!");
                break;
        }
    }

    private void movePlayer() {
        System.out.println("Enter the room number you want to move to:");
        Scanner scanner = new Scanner(System.in);
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Clear the scanner buffer

        currentRoom = new Room("Room " + roomNumber);
    }

    private void attackEnemy() {
        Room currentRoom = getCurrentRoom();
        List<Enemy> enemies = currentRoom.getEnemies();

        if (enemies.isEmpty()) {
            System.out.println("There are no enemies in this room!");
            return;
        }

        System.out.println("Available enemies to attack:");
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println((i + 1) + ". " + enemies.get(i).getName());
        }

        System.out.println("Choose the number of the enemy to attack:");
        Scanner scanner = new Scanner(System.in);
        int enemyNumber = scanner.nextInt();
        scanner.nextLine(); // Clear the scanner buffer

        if (enemyNumber < 1 || enemyNumber > enemies.size()) {
            System.out.println("Invalid enemy number!");
            return;
        }

        Enemy enemy = enemies.get(enemyNumber - 1);
        Player player = new Player(100, 10); // Assuming there is a Player class

        int damageDealt = player.attack(enemy);
        enemy.reduceHealth(damageDealt);

        System.out.println("You attacked the enemy " + enemy.getName() + " and dealt " + damageDealt + " damage.");

        if (enemy.isDefeated()) {
            currentRoom.removeEnemy(enemy);
            System.out.println("You defeated the enemy " + enemy.getName() + "!");
        }
    }

    private Room getCurrentRoom() {
        return this.currentRoom;
    }

    private void collectTreasure() {
        Room currentRoom = getCurrentRoom();
        List<Treasure> treasures = currentRoom.getTreasures();

        if (treasures.isEmpty()) {
            System.out.println("There are no treasures in this room!");
            return;
        }

        // Display the available treasures to collect
        System.out.println("Available treasures to collect:");
        for (int i = 0; i < treasures.size(); i++) {
            System.out.println((i + 1) + ". " + treasures.get(i).getName());
        }

        System.out.println("Choose the number of the treasure to collect:");
        Scanner scanner = new Scanner(System.in);
        int treasureNumber = scanner.nextInt();
        scanner.nextLine(); // Clear the scanner buffer

        if (treasureNumber < 1 || treasureNumber > treasures.size()) {
            System.out.println("Invalid treasure number!");
            return;
        }

        Treasure treasure = treasures.get(treasureNumber - 1);
        // Code to collect the treasure should be added here
    }

    public void printGameOverMessage() {
        System.out.println("Game over! Thank you for playing!");  
        
    }
}

