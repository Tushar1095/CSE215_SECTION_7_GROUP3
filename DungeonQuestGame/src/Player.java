import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private int gold;
    private boolean gameOver;
    private List<String> inventory;

    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        this.gold = 0;
        this.inventory = new ArrayList<>();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addGold(int amount) {
        gold += amount;
        inventory.add(amount + " gold");
    }

    public String getInventory() {
        return inventory.isEmpty() ? "Your inventory is empty." : String.join(", ", inventory);
    }

    public void clearInventory() {
        inventory.clear();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void takeDamage(int amount) {
        health -= amount;
    }
}

