import java.util.Random;

public class Game {
    private final Random random = new Random();

    public void start(Player player) {
        player.setHealth(100);
        player.clearInventory();
        player.setGameOver(false);
    }

    public String processChoice(Player player, int choice) {
        String[] rooms = {"a dark cave", "an ancient library", "a damp cellar", "a glowing chamber"};
        String room = rooms[random.nextInt(rooms.length)];

        int event = random.nextInt(5); // 0 = treasure, 1 = goblin, 2 = trap, 3 = dragon, 4 = boss
        String result;

        switch (event) {
            case 0 -> {
                int gold = random.nextInt(50) + 10;
                player.addGold(gold);
                result = "You entered " + room + " and found a chest with " + gold + " gold!";
            }
            case 1 -> {
                int damage = random.nextInt(20) + 10;
                player.takeDamage(damage);
                result = "A goblin attacked you in " + room + "! Health decreased by " + damage + ".";
            }
            case 2 -> {
                int trapDamage = random.nextInt(30) + 10;
                player.takeDamage(trapDamage);
                result = "You triggered a trap in " + room + "! Health decreased by " + trapDamage + ".";
            }
            case 3 -> {
                int dragonDamage = random.nextInt(40) + 20;
                player.takeDamage(dragonDamage);
                result = "A dragon appeared in " + room + " and attacked you! Health decreased by " + dragonDamage + ".";
            }
            case 4 -> {
                int bossDamage = random.nextInt(50) + 30;
                player.takeDamage(bossDamage);
                result = "You faced a boss in " + room + "! Health decreased by " + bossDamage + ".";
            }
            default -> throw new IllegalStateException("Unexpected event: " + event);
        }

        if (player.getHealth() <= 0) {
            player.setGameOver(true);
            result += "\nYou have no health left. The game is over.";
        }

        return result;
    }
}
