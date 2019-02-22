import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class MonopolyGame {
	private static ArrayList<HumanPlayer> humans;
	private static ArrayList<ComputerPlayer> bots;
	private static MonopolyConfiguration config;

	public static void setup() {
		humans = new ArrayList<HumanPlayer>();
		bots = new ArrayList<ComputerPlayer>();
		config = new MonopolyConfiguration();
		Scanner sc = new Scanner(System.in);
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
			humans.add(new HumanPlayer(pOne));
		}
		if (humNum == 1) {
			System.out.println("\nI'm adding in a bot because it is lonely to play by yourself.");
			bots.add(new ComputerPlayer("bot"));
		}
		else if (humNum < 4) {
			System.out.println("\nWould you like to add a bot? (Type y for yes) ");
			try {
				String botOrNo = sc.next();
				if ( botOrNo.equals("Y") || botOrNo.equals("y") ) {
					bots.add(new ComputerPlayer(pOne));
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
	public static void play() {
		boolean quit = false;
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n\n\nWelcome to Monopoly!!!!!\nThere are " + 
			(humans.size() + bots.size()) + " players in this game." +
			" Each player starts with $1500\nin their bank account and the goal of the game" +
			" is to have every\nother player completely run out of money," +
			" only then someone will win. \nThe first player to move will be " + 
			humans.get(0).getName() +". Good luck and have fun!!\n\n\n\n" );

		int firstRoll;
		int secondRoll;
		Random rand = new Random();
		String rollConfirm;

		while (!quit) {
			for (int i= 0; i < humans.size(); i++) {
				System.out.println(humans.get(i).getName() + " you have $" + 
					humans.get(i).getBalance() + " in your account. Please type roll " +
					"to roll the 2 dice. ");
				rollConfirm = sc.nextLine();
				if (!rollConfirm.equals("roll")) {
					System.out.println("\nYou didn't type in roll correctly, but I rolled for you anyways c:");
				}
				firstRoll = rand.nextInt(6) + 1;
				secondRoll = rand.nextInt(6) + 1;
				System.out.println("You rolled a " + firstRoll + " and a " + secondRoll + ", so I move you " +
					(firstRoll + secondRoll) + " spaces.\n\n");
				humans.get(i).move(firstRoll + secondRoll);
				MonopolyConfiguration.printBoard();


			}
			if (bots.size() != 0) {
				System.out.print("It is now the bots turn, they will now roll the dice."
					+ " Please type roll so the bot can roll their dice. ");
				rollConfirm = sc.nextLine();
				if (!rollConfirm.equals("roll")) {
					System.out.println("\nYou didn't type in roll correctly, but I rolled for the bot anyways");
				}
				firstRoll = rand.nextInt(6) + 1;
				secondRoll = rand.nextInt(6) + 1;
				System.out.println("The bot rolled a " + firstRoll + " and a " + secondRoll + ", so I move them " +
					(firstRoll + secondRoll) + " spaces.");
				bots.get(0).move(firstRoll + secondRoll);
				MonopolyConfiguration.printBoard();
			}
			


		}
	}

	public static void main(String[] args) {
		setup();
		play();
	}
}