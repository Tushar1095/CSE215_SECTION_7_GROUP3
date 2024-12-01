import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private Player player;
    private Game game;
    private JTextArea gameTextArea;
    private JTextField inputField;
    private JButton submitButton;

    public GameWindow() {
        setTitle("Dungeon Adventure");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gameTextArea = new JTextArea();
        gameTextArea.setEditable(false);
        gameTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(gameTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        submitButton = new JButton("Submit");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        initializeGame();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleInput();
            }
        });

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleInput();
            }
        });
    }

    private void initializeGame() {
        player = new Player("Hero", 100);
        game = new Game();
        game.start(player);

        gameTextArea.setText("Welcome to Dungeon Adventure!\n\n" +
                "You find yourself in a mysterious dungeon...\n" +
                "Explore the rooms, fight monsters, collect treasures, and survive!\n\n" +
                "Commands:\n" +
                "- Move: Type '1' (North), '2' (South), '3' (East), or '4' (West).\n" +
                "- Inventory: Type 'inventory' to view your items.\n" +
                "- Quit: Type 'quit' to end the game.\n\n" +
                "Your health: " + player.getHealth() + "\nType a command and press Enter.");
    }

    private void handleInput() {
        if (player.isGameOver()) return;

        String input = inputField.getText().trim().toLowerCase();
        inputField.setText("");

        if (input.equals("quit")) {
            gameTextArea.append("\nYou chose to quit the game. Goodbye!\n");
            inputField.setEnabled(false);
            submitButton.setEnabled(false);
            return;
        }

        if (input.equals("inventory")) {
            gameTextArea.append("\nYour Inventory:\n" + player.getInventory() + "\n");
            return;
        }

        try {
            int choice = Integer.parseInt(input);
            if (choice < 1 || choice > 4) throw new IllegalArgumentException("Invalid direction!");

            String result = game.processChoice(player, choice);
            gameTextArea.append("\n" + result + "\n");

            if (player.isGameOver()) {
                gameTextArea.append("\nGame Over! Thank you for playing.\n");
                inputField.setEnabled(false);
                submitButton.setEnabled(false);
            } else {
                gameTextArea.append("\nYour health: " + player.getHealth() + "\n" +
                        "Type another command (move, inventory, quit).\n");
            }
        } catch (Exception ex) {
            gameTextArea.append("\nInvalid input: " + ex.getMessage() + "\n");
        }
    }
}

