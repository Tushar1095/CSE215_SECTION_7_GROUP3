# CSE215_SECTION_7_GROUP3
Project on Text-Based RPG Game
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Dungeon {
    private Room currentRoom;
    private boolean gameOver;
    
    public Dungeon() {
       
      
        currentRoom = new Room("Sala Inicial");
        gameOver = false;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public void printCurrentRoomInfo() {
    
        System.out.println("Você está na sala: " + currentRoom.getName());
    }
    
    public void printActions() {

        System.out.println("Escolha uma ação:");
        System.out.println("1. Mover para outra sala");
        System.out.println("2. Atacar inimigo");
        System.out.println("3. Coletar tesouro");

    }
    
    public void processAction(String action) {
        // Processa a ação do jogador
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
                System.out.println("Ação inválida!");
                break;
        }
    }
    

    // ...

    private void movePlayer() {
        System.out.println("Digite o número da sala para onde você deseja se mover:");
        Scanner scanner = new Scanner(System.in);
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner


        currentRoom = new Room("Sala " + roomNumber);
    }

	private void attackEnemy() {
		Room currentRoom = getCurrentRoom();
		List<Enemy> enemies = currentRoom.getEnemies();

		if (enemies.isEmpty()) {
			System.out.println("Não há inimigos nesta sala!");
			return;
		}

		
		System.out.println("Inimigos disponíveis para atacar:");
		for (int i = 0; i < enemies.size(); i++) {
			System.out.println((i + 1) + ". " + enemies.get(i).getName());
		}

		System.out.println("Escolha o número do inimigo para atacar:");
		Scanner scanner = new Scanner(System.in);
		int enemyNumber = scanner.nextInt();
		scanner.nextLine(); // Limpa o buffer do scanner

		if (enemyNumber < 1 || enemyNumber > enemies.size()) {
			System.out.println("Número de inimigo inválido!");
			return;
		}

		Enemy enemy = enemies.get(enemyNumber - 1);
		Player player = new Player(100,10); // Supondo que existe uma classe Player

		
		int damageDealt = player.attack(enemy);
		enemy.reduceHealth(damageDealt);

		System.out.println("Você atacou o inimigo " + enemy.getName() + " e causou " + damageDealt + " de dano.");

		if (enemy.isDefeated()) {
			currentRoom.removeEnemy(enemy);
			System.out.println("Você derrotou o inimigo " + enemy.getName() + "!");
		}
	}

    private Room getCurrentRoom() {
        return this.currentRoom;
    }


    private void collectTreasure() {
        Room currentRoom = getCurrentRoom();
        List<Treasure> treasures = currentRoom.getTreasures();

        if (treasures.isEmpty()) {
            System.out.println("Não há tesouros nesta sala!");
            return;
        }

        // Exibe os tesouros disponíveis para coletar
        System.out.println("Tesouros disponíveis para coletar:");
        for (int i = 0; i < treasures.size(); i++) {
            System.out.println((i + 1) + ". " + treasures.get(i).getName());
        }

        System.out.println("Escolha o número do tesouro para coletar:");
        Scanner scanner = new Scanner(System.in);
        int treasureNumber = scanner.nextInt();
        scanner.nextLine();
        if (treasureNumber < 1 || treasureNumber > treasures.size()) {
            System.out.println("Número de tesouro inválido!");
            return;
        }

        Treasure treasure = treasures.get(treasureNumber - 1);
        
    }
    
    // ...

    public void printGameOverMessage() {
     
        System.out.println("Fim de jogo! Obrigado por jogar!");
    }
}
