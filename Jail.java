import java.util.Scanner;

public class Jail {
	private Scanner kb = new Scanner(System.in);
	private Dice dice = new Dice();
	private int roll1;
	private int roll2;
	private String check;
	
	public void nightInJail(Player player) {
		if (player.getName().equals("bot")) {
			if (player.getBalance() > 100 ) {
				player.changeBalance(-100);
				player.setJail(false);
				System.out.println("Bot has freed themself from jail, by paying the bail amount of $100.");
			}
		}

		else {
			System.out.println("you are in jail. You can roll doubles or pay 100$ to get out");
			System.out.println("Will you pay 100$? Y for yes, N for no");
			check = kb.next();
			if (check.toUpperCase().equals("Y")) {
				player.changeBalance(-100);
				player.setJail(false);
				System.out.println("you are free and your new balance is $" + player.getBalance() +".");
			}
			else {
				roll1 = dice.roll();
				roll2 = dice.roll();
				if (roll1 == roll2) {
					System.out.println("Congratulations! You rolled 2" + roll1 + "'s.");
					player.setJail(false);
				}
				else {
					System.out.println("Unfortunately you rolled a " + roll1 + " and a " + roll2 + ", which is not a double.");
					player.addJailCount(1);
				}
			}
		}
	}

	public void arrest(Player player) {
		// removing parameters  Square current, MonopolyConfiguration config
		//current.getOccupants().remove(player.getIcon());
		player.setJail(true);
		player.changePosition(10);
		//current = config.getBoard().get(player.getPosition());
		//current.getOccupants().add(player.getIcon());
		//System.out.println("Go To Jail ! You are now moved to jail. Next turn you will start in in jail " +
		//" you must roll double or pay bail to get out.");
		//check = kb.next();
	}

	/*public void justVisiting(Player player) {
		System.out.println(player.getName() + " is just visiting jail today.");
	}*/
}