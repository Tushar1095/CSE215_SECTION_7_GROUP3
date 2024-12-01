# Dungeon Quest Game

**Dungeon Quest** is a text-based adventure game developed in **Java** using **Java Swing** for the graphical user interface (GUI). It combines **Object-Oriented Programming (OOP)** principles and random event-driven gameplay to create an interactive and engaging dungeon exploration experience. Players navigate through a dungeon, battling enemies, collecting treasures, and avoiding traps while managing their health.

## Technologies and Techniques Used

- **Java**: Used for the core logic and GUI implementation.
- **Java Swing**: Utilized to design the graphical user interface.
- **Object-Oriented Programming (OOP)**: The game structure is designed around core OOP principles such as:
  - **Encapsulation**: Data is hidden within classes to ensure that it is only accessible through well-defined methods.
  - **Inheritance**: Shared behaviors are abstracted into base classes and inherited by specialized classes.
  - **Polymorphism**: The game’s random events and player actions are dynamically processed through polymorphic methods.
- **Exception Handling**: Ensures the game can handle invalid user inputs and prevents crashes by catching errors gracefully.
  
## How to Use

1. **Clone the repository** to your local machine:
   ```bash
   git clone https://github.com/yourusername/dungeon-quest.git
   2. **Open the project** in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. **Navigate to the `Main.java`** file.
4. **Run the project**: This will launch the game window.
5. **Follow the instructions** displayed in the game window to play the game.

## Game Instructions

Once the game starts, you'll be presented with a series of choices. Here’s how to play:

- **Choose a direction** (North, South, East, West) by typing the corresponding number (1, 2, 3, or 4) and pressing Enter.
- **Random events** will occur as you explore the dungeon. These events could be:
  - **Treasures** that increase your health.
  - **Enemies** (e.g., goblins or dragons) that will decrease your health.
  - **Traps** that will end the game.
- The game continues until either you **lose all your health** or **win by reaching 130 health**.

## Key Features

- **Randomized Events**: The game includes randomized outcomes (treasure, enemies, traps) each time the player enters a room.
- **Health Management**: Players need to monitor their health and make strategic decisions to ensure survival.
- **Interactive GUI**: A user-friendly interface is built using **Java Swing**, where players can type their choices, view results, and see updates on their health.
- **Object-Oriented Design**: The game structure is based on classes, methods, and attributes encapsulated within different objects, following OOP principles.
- **Exception Handling**: The game ensures that invalid inputs are handled gracefully without crashing, providing a smooth gaming experience.

## Possible Challenges

- **Java Development Environment**: Ensure that you have **Java Development Kit (JDK)** installed and properly configured on your machine.
- **Dependencies**: The game doesn't rely on any external libraries or dependencies.
- **Error Handling**: The game properly handles invalid inputs, but if you'd like to extend the game, further error handling might be necessary for additional features.

## Future Enhancements

- **Multiplayer Mode**: Allow multiple players to explore the dungeon together or compete against each other.
- **Advanced Graphics**: Replace text-based outcomes with more dynamic, graphic-based events.
- **Additional Features**: Include additional game mechanics like inventory management, more complex combat, and special abilities for the player.

## Contribution Guidelines

Contributions are welcome! Feel free to submit a **pull request** or **open an issue** if you have suggestions or encounter problems. When contributing, make sure to:
- Follow the existing coding style.
- Test your changes thoroughly before submitting.

## License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

