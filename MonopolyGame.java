import java.util.*;

/**
 * This class effectively functions as the main menu of the game and where the game will run from.
 * @author Group 6
 * @verions 1.0
 * @since 2019-02-25
 */
public class MonopolyGame {

	//initializes lists of human players, computer players, the board, and move
	private ArrayList<Player> players = new ArrayList<Player>();
	private MonopolyConfiguration config = new MonopolyConfiguration();
	private ArrayList<Square> board = BoardMaker.DefaultBoard();
	private Movement movement = new Movement();
	private MonopolyActions actions = new MonopolyActions();
	private Jail jail = new Jail();
	private boolean quit = false;
	private Scanner sc = new Scanner(System.in);
	private String check = "";
	private Messages messages = new Messages();
	private int numberOfHumans;
	private EndGame endGame = new EndGame();
	/**
	 * Prompts the user for basic specifications of the game with a brief intro
	 * The user is prompted for number of human players with optional bot player (max 1)
	 * For each players, a name is prompted
	 */
	public void setup() {
		System.out.println("How many human players would you like to have? (Please enter a number between 1 and 4) ");
		try {
			check = sc.nextLine();
			numberOfHumans = Integer.parseInt(check);
			while (numberOfHumans < 1 || numberOfHumans > 4) {
				System.out.println("Please enter a number between 1 and 4 ");
				check = sc.nextLine();
				numberOfHumans = Integer.parseInt(check);
			}
		} 
		catch (NumberFormatException e) {
				System.out.println("Please enter a number between 1 and 4, if you fail to type an integer again I'll assume you play alone. ");
				try {
					check = sc.nextLine();
					numberOfHumans = Integer.parseInt(check);
					while (numberOfHumans < 1 || numberOfHumans > 4) {
						System.out.println("Please enter a number between 1 and 4 ");
						check = sc.nextLine();
					numberOfHumans = Integer.parseInt(check);
					}
				}
				catch (NumberFormatException e2) {
					System.out.println("That's it you're playing alone. ");
					numberOfHumans = 1;
				}

		}

		System.out.println("You have chosen to play with " + numberOfHumans + " human players");
		for (int i = 0; i < numberOfHumans; i++) {
			System.out.println("\nPlease enter player " + (i+1) + "'s name: ");
			check = sc.nextLine();
			System.out.println("Player " + (i+1) + "'s name is " + check);
			players.add(new Human(check, "" + (i+1) ));
		}
		if (numberOfHumans == 1) {
			System.out.println("\nI'm adding in a bot because it is lonely to play by yourself." +
				" \n\nPlease type anything to continue.");
			players.add(new Bot("bot", "B"));
			check = sc.nextLine();
		}
		else if (numberOfHumans < 4) {
			System.out.println("\nWould you like to add a bot? (Type y for yes) ");
			try {
				check = sc.nextLine();
				if ( check.toUpperCase().equals("Y") || check.toUpperCase().equals("YES") ) {
					//bots.add(new Player("bot", "B"));
					players.add(new Bot("bot", "B"));
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
		System.out.println(messages.welcomeMessage(players));
		for (int i = 0; i < players.size(); i++) {
			board.get(0).getOccupants().add(players.get(i).getIcon());
		}
		config.printBoard(board);
		check = sc.nextLine();

		/////////////////////////Running//////////////////////////////////////
		int pTurn = 0;
		while (!quit) {
			while (pTurn < players.size()) {
				if (players.get(pTurn) instanceof Bot) {
					botTurn(players.get(pTurn));
				}
				else {
					humanTurn(players.get(pTurn));
				}
				if (endGame.isLoser(players.get(pTurn))) {
					System.out.println(endGame.eliminationMessage(players.get(pTurn), players));
					if (endGame.isGameOver(players)) {
						System.out.println(endGame.victoryMessage(players));
						System.exit(0);
					}
				}
				else {
					pTurn ++;
				}
			}
			pTurn = 0;
		}
	}

	public void humanTurn(Player human) {
		if (human.getJail()) {
			assessJail(human);
			return;
		}
		System.out.println("\n" + human.getName() + " you have $" +
		human.getBalance() + " in your account and you are currently at " +
			"the square [" + board.get(human.getPosition()).getName() + "]. Please type anything to roll.");
		check = sc.nextLine();
		System.out.println(movement.rollResults(human));
		movement.moveToken(human, board);
		config.printBoard(board);
		check = sc.nextLine();
		System.out.println(human.getName()+ "'s current position is [" + board.get(human.getPosition()).getName() + "]");
		System.out.print(actions.passGoMoney(human));
		System.out.println(actions.actionType(human, board.get(human.getPosition())));
		check = sc.next();
		System.out.println(actions.actionConfirmation(human, board, check));
		check = sc.nextLine();
	}

	public void botTurn(Player theBot) {
		if (theBot.getJail()) {
			assessJail(theBot);
			return;
		}
		System.out.print("\nIt is now the bots turn, they will now roll the dice." +
			" The bot has $" + theBot.getBalance() + " and is at position [" +
			board.get(theBot.getPosition()).getName() + "]");
		check = sc.nextLine();
		check = sc.nextLine();
		System.out.println(movement.rollResults(theBot));
		movement.moveToken(theBot, board);
		config.printBoard(board);
		check = sc.nextLine();
		System.out.println(theBot.getName()+ "'s current position is [" + board.get(theBot.getPosition()).getName() + "]");
		System.out.print(actions.passGoMoney(theBot));
		check = sc.nextLine();
		System.out.println(actions.actionConfirmation(theBot, board, "y"));
	}

	public void assessJail(Player player) {
		if (player.getName().equals("bot")) {
			System.out.println(jail.botInJail(player));
			
		}
		else {
			if (player.getJailCount() == 3) {
				System.out.println(jail.forcedBail(player));
				return;
			}
			System.out.println(messages.jailStartMessage(player));
			check = sc.next();
			if (check.toUpperCase().equals("Y") || check.toUpperCase().equals("YES")) {
				jail.payBail(player);
				System.out.println(jail.payBailMessage(player));
			}
			else {
				System.out.println(jail.rollForJail(player));
			}	
		}	
	}
	
}
