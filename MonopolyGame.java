import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class effectively functions as the main menu of the game and where the game will run from.
 * @author Group 6
 * @verions 1.0
 * @since 2019-02-25
 */
public class MonopolyGame {

	//initializes lists of human players, computer players, the board, and move
	private ArrayList<Player> humans = new ArrayList<Player>();
	private ArrayList<Player> bots = new ArrayList<Player>();
	private MonopolyConfiguration config = new MonopolyConfiguration();
	private ArrayList<Square> board = BoardMaker.DefaultBoard();
	private Movement movement = new Movement();
	private MonopolyActions actions = new MonopolyActions();
	private Jail jail = new Jail();
	private boolean quit = false;
	private Scanner sc = new Scanner(System.in);
	private String check = "";
	//private Houses houseFunction = new Houses(config);
	private Messages messages = new Messages();

	/**
	 * Prompts the user for basic specifications of the game with a brief intro
	 * The user is prompted for number of human players with optional bot player (max 1)
	 * For each players, a name is prompted
	 */
	public void setup() {
		System.out.println("How many human players would you like to have? (Please enter a number between 1 and 4) ");
			int numberOfHumans = sc.nextInt();
			while (numberOfHumans < 1 || numberOfHumans > 4) {
				System.out.println("Please enter a number between 1 and 4 ");
				numberOfHumans = sc.nextInt();
			}
		System.out.println("You have chosen to play with " + numberOfHumans + " human players");
		for (int i = 0; i < numberOfHumans; i++) {
			System.out.println("\nPlease enter player " + (i+1) + "'s name: ");
			check = sc.next();
			System.out.println("Player " + (i+1) + "'s name is " + check);
			humans.add(new Player(check, "" + (i+1) ));
		}
		if (numberOfHumans == 1) {
			System.out.println("\nI'm adding in a bot because it is lonely to play by yourself." +
				" \n\nPlease type anything to continue.");
			bots.add(new Player("bot", "B"));
			check = sc.next();
		}
		else if (numberOfHumans < 4) {
			System.out.println("\nWould you like to add a bot? (Type y for yes) ");
			try {
				check = sc.next();
				if ( check.toUpperCase().equals("Y") ) {
					bots.add(new Player("bot", "B"));
					System.out.println("\nOk a bot has been added");
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
	public void play() {
		messages.welcomeMessage(humans, bots);
		for (int i = 0; i < humans.size(); i++) {
			board.get(0).getOccupants().add(humans.get(i).getIcon());
		}
		for (int i = 0; i <bots.size(); i++) {
			board.get(0).getOccupants().add(bots.get(i).getIcon());
		}
		config.printBoard(board);
		check = sc.next();

		while (!quit) {
			//Must change this to a while loop since after 1 player loses, someones turn will get skipped
			for (int i= 0; i < humans.size(); i++) {
				humanTurn(humans.get(i));
				if (quit) {
					break;
				}
			}
			if (quit) {
				break;
			}
			for (int i= 0; i < bots.size(); i++) {
				botTurn(bots.get(i));
			}
			if (quit) {
				break;
			}
		}
	}

	/**
	* This method checks if a player's balance gets to $0 in which the player will lose
	*/
	public void checkLoser(Player player) {
		Player loser;
		if (player.getBalance() == 0) {
			loser = player;
			System.out.println("Player " + loser.getName() + " has run out of money and is now eliminated from the game.");
			while (!loser.getPropertiesOwned().isEmpty()) {
				loser.getPropertiesOwned().get(0).setOwned(false);
				System.out.println(loser.getPropertiesOwned().get(0).getName() + " is now up for grabs.");
				loser.getPropertiesOwned().remove(0);	
			}
			if (loser.getName() == "bot") {
				bots.remove(loser);
			}
			else {
				humans.remove(loser);
			}
			System.out.println("\nThe number of humans remaining is " + humans.size() + ".");
			System.out.println("The number of bots remaining is " + bots.size() + ".");
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


	public void humanTurn(Player human) {
		System.out.println("\n" + human.getName() + " you have $" +
		human.getBalance() + " in your account and you are currently at " +
			"the square [" + board.get(human.getPosition()).getName() + "]");
		assessJail(human);
		System.out.println(messages.rollMessage(human));
		check = sc.next();
		System.out.println(movement.rollResults(human));
		movement.moveToken(human, board);
		config.printBoard(board);
		check = sc.next();
		System.out.println(human.getName()+ "'s current position is [" + board.get(human.getPosition()).getName() + "]");
		System.out.print(actions.passGoMoney(human));
		System.out.println(actions.actionType(human, board.get(human.getPosition())));
		check = sc.next();
		System.out.println(actions.actionConfirmation(human, board, check));

		checkLoser(human);
	}

	public void botTurn(Player theBot) {
		System.out.print("\nIt is now the bots turn, they will now roll the dice." +
			" The bot has $" + theBot.getBalance() + " and is at position [" +
			board.get(theBot.getPosition()).getName() + "]");
		check = sc.next();
		assessJail(theBot);
		System.out.println(messages.rollMessage(theBot));
		check = sc.next();
		System.out.println(movement.rollResults(theBot));
		movement.moveToken(theBot, board);
		config.printBoard(board);
		check = sc.next();
		System.out.println(theBot.getName()+ "'s current position is [" + board.get(theBot.getPosition()).getName() + "]");
		System.out.print(actions.passGoMoney(theBot));
		//System.out.println(actions.actionType(theBot, board.get(theBot.getPosition())));
		check = sc.next();
		System.out.println(actions.actionConfirmation(theBot, board, "y"));

		checkLoser(theBot);
	}

	public void assessJail(Player player) {
		if (player.getName().equals("bot")) {
			if (player.getJail() == true) {
				System.out.println(jail.botInJail(player));
			}
		}
		else {
			if (player.getJail() == true) {
				System.out.println("you are in jail. You can roll doubles or pay 100$ to get out");
				System.out.println("Will you pay 100$? Y for yes, N for no");
				check = sc.next();
				if (check.toUpperCase().equals("Y")) {
					jail.payBail(player);
					System.out.println("you are free and your new balance is $" + player.getBalance() +".");
				}
				else {
					System.out.println(jail.rollForJail(player));
				}
			}	
		}	
	}
	
}
