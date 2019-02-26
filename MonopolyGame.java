import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class MonopolyGame {

	//initializes lists of human players, computer players, the board, and move
	private static ArrayList<Player> humans;
	private static ArrayList<Player> bots;
	private static MonopolyConfiguration config;
	private static Move move;
	private static boolean quit = false;

/**
 * Effectively the menu of the game
 * The user is prompted for number of human players with optional bot player (max 1)
 * For each players, a name is prompted
 */
	public static void setup() {
		humans = new ArrayList<Player>();
		bots = new ArrayList<Player>();
		config = new MonopolyConfiguration();
		move = new Move(config);
		Scanner sc = new Scanner(System.in);
		String check;
		System.out.println("How many human players would you like to have? (Please enter a number between 1 and 4) ");
			int humNum = sc.nextInt();
			while (humNum < 1 || humNum > 4) {
				System.out.println("Please enter a number between 1 and 4 ");
				humNum = sc.nextInt();
			}
		System.out.println("You have chosen to play with " + humNum + " human players");
		String pOne ="";
		for (int i = 0; i < humNum; i++) {
			System.out.println("\nPlease enter player " + (i+1) + "'s name: ");
			pOne = sc.next();
			System.out.println("Player " + (i+1) + "'s name is " + pOne);
			humans.add(new Player(pOne, "" + (i+1) ));
		}
		if (humNum == 1) {
			System.out.println("\nI'm adding in a bot because it is lonely to play by yourself." +
				" \n\nPlease type anything to continue.");
			bots.add(new Player("bot", "B"));
			check = sc.next();
		}
		else if (humNum < 4) {
			System.out.println("\nWould you like to add a bot? (Type y for yes) ");
			try {
				String botOrNo = sc.next();
				if ( botOrNo.equals("Y") || botOrNo.equals("y") ) {
					bots.add(new Player("bot", "B"));
				}
				else {
					System.out.println("\nNo bots are added.");
				}
			}
			catch (Exception e) {
				System.out.println("\nI'll assume that means you don't want a bot playing with you.");
			}
		}



	}

	/**
	 * This method adds in the tokens for each players and gives a brief intro
	 * During the game, this will output a player's name, balance, and position
	 * A prompt for roll will also occur and a player will be moved accordingly
	 */
	public static void play() {
		Scanner sc = new Scanner(System.in);
		String check;
		System.out.println("\n\n\n\nWelcome to Monopoly!!!!!\nThere are " +
			(humans.size() + bots.size()) + " players in this game." +
			" Each player starts with $1500\nin their bank account and the goal of the game" +
			" is to have every\nother player completely run out of money," +
			" only then someone will win.\nEveryone will start on the" +
			" Go! square.\nThe first player to move will be " +
			humans.get(0).getName() +". Good luck and have fun!!\n");


		for (int i = 0; i < humans.size(); i++) {
			config.getBoard().get(0).getOccupants().add(humans.get(i).getOccupantValue());
		}
		for (int i = 0; i <bots.size(); i++) {
			config.getBoard().get(0).getOccupants().add(bots.get(i).getOccupantValue());
		}
		config.printBoard();
		System.out.println("Please type anything to continue.");
		check = sc.next();


		while (!quit) {
			for (int i= 0; i < humans.size(); i++) {
				System.out.println("\n" +humans.get(i).getName() + " you have $" +
					humans.get(i).getBalance() + " in your account and you are currently at " +
					"the square [" + config.getBoard().get(humans.get(i).getPosition()).getName() + "]");
				move.roll(humans.get(i));
				checkLosers();
				if (quit) {
					break;
				}
			}

			for (int i= 0; i < bots.size(); i++) {
				if (quit) {
					break;
				}
				System.out.print("\nIt is now the bots turn, they will now roll the dice." +
					" The bot has $" + bots.get(i).getBalance() + " and is at position [" +
					config.getBoard().get(bots.get(i).getPosition()).getName() + "]");
				check = sc.next();
				move.roll(bots.get(0));
				checkLosers();
			}



		}
	}
	/**
	 * This method checks if a player's balance gets to $0 in which the player will lose
	 */
	public static void checkLosers() {
		Player loser;
		for (int i= 0; i < humans.size(); i++) {
			if (humans.get(i).getBalance() == 0) {
				loser = humans.get(i);
				System.out.println("Player " + loser.getName() + " has run out of money and is now eliminated from the game.");
				humans.remove(loser);
				for (int j = 0; j < loser.getPropertiesOwned().size(); j++) {
					loser.getPropertiesOwned().get(j).setOwned(false);
					System.out.println(loser.getPropertiesOwned().get(j).getName() + " is now up for grabs.");
				}
				System.out.println("\nThe number of humans remaining is " + humans.size() + ".");
				System.out.println("The number of bots remaining is " + bots.size() + ".");
			}
		}
		for (int i= 0; i < bots.size(); i++) {
			if (bots.get(i).getBalance() == 0) {
				loser = bots.get(i);
				System.out.println("Player " + loser.getName() + " has run out of money and is now eliminated from the game.");
				bots.remove(loser);
				for (int j = 0; j < loser.getPropertiesOwned().size(); j++) {
					loser.getPropertiesOwned().get(j).setOwned(false);
					System.out.println(loser.getPropertiesOwned().get(j).getName() + " is now up for grabs.");
				}
				System.out.println("\nThe number of humans remaining is " + humans.size() + ".");
				System.out.println("The number of bots remaining is " + bots.size() + ".");
			}
		}
		if ((humans.size() == 1) && (bots.size() == 0)) {
			System.out.print("\n\nPlayer " + humans.get(0).getName() + " wins the Game!!!\n\n");
			quit = true;
		}
		if ((bots.size() == 1) && (humans.size() == 0)) {
			System.out.print("The bot wins the Game!!!");
			quit = true;
		}
	}

	// initializes the game
	public static void main(String[] args) {
		setup();
		play();
	}
}
