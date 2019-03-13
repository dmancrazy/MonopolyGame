import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class outlines the interaction a player will have with each square and the game in general.
 * @author Group 6
 * @version 1.0
 * @since 2019-02-25
 */
public class Movement{

  // initializes dice rolls, current position, and the board
	private Dice dice = new Dice();
	private int roll1;
	private int roll2;
    private int doubles=0;
    private Square current;
    private MonopolyConfiguration config;
    private Jail jail = new Jail();
    private Scanner kb = new Scanner(System.in);
    private String check;
    private Action action = new Action();
    private Banker banker = new Banker();
    // set proper parameters.

    /**
     * This method sets the current position as the first square on the configured board
     * @param c A setup for the monopoly board
     */
    public Movement(MonopolyConfiguration c) {
    	config = c;
    	current = config.getBoard().get(0);
    }

    /**
     * This method rolls the dice  for a player and moves them accordingly
     * Checks if the player is in jail or not
     * If the player is in jail, prompts for either payment or the chance of a double roll for a chance to be free
     * If not, the player is simply moved normally and states their new current positions
     * @param player A certain player
     */
    public void move(Player player) {
		current = config.getBoard().get(player.getPosition());
		if (player.getJail() == true) {
			jail.nightInJail(player);
		}

/////////////////////If the Player is not in Jail/////////////////////////////////////////////////////////////////////////////////
		else {
			rollMessage(player);

			moveToken(player);

			if (player.getPassedGo() == true) {
				action.passGoMoney(player);
			}
			if (player.getPosition() == 30) {
				jail.arrest(player, current, config);
			}

			if (player.getPosition() == 10) {
				jail.justVisiting(player);
			}
				
			if (current.getType().equals("property") || current.getType().equals("rail")) {
				if(current.getOwned() == false) {
					banker.viewNewProperty(player, current);
				}

				else if (current.getOwner() != player) {
					banker.stayAtRivalProperty(player, current);
				}
				else if (current.getOwner() == player) {
					System.out.print(player.getName() + " has landed on their own property, which is [" +
						current.getName() + "] so nothing happens.");
					check=kb.next();
				}
			}
		}
	}


	public void moveToken(Player player) {
		roll1 = dice.roll();
		roll2 = dice.roll();
		System.out.println(player.getName() + " rolled a " + roll1 + " and a " + roll2 + ", so I move them " +
		(roll1 + roll2) + " spaces.\n");
		current.getOccupants().remove(player.getIcon());
		player.changePosition((roll1 + roll2)+ player.getPosition());
		current = config.getBoard().get(player.getPosition());
		current.getOccupants().add(player.getIcon());
		config.printBoard();
		check = kb.next();
		System.out.println(player.getName()+ "'s current position is [" + current.getName() + "]");
	}

	public void rollMessage(Player player) {
		if (!player.getName().equals("bot")) {
			System.out.println("Please type roll to roll the 2 dice. ");
			check = kb.next();
			if (!check.toUpperCase().equals("ROLL")) {
				System.out.println("\nYou didn't type in roll correctly, but I rolled for you anyways c:");
			}
		}
	}

					//}
				//}
				//gotta set property owners and get property owners, and houses count too
				// we should set a house multiplier for each property too.
				// make sure the payee variable under the square property references the legit player in the list
				// a deposit and withdraw method would be nice


				//else{
					//System.out.println("that property is owned by" + current.getOwner());
					//player.setBalance(player.getBalance()-(current.getRent()));
					//current.getOwner().setBalance(current.getOwner().getBalance()+(current.getRent()));
					//// a printLN to say how much was payed
				//}

}



			    // public ifDoubles()

				// alot of this can probably be broken into separate functions, like a jailcheck and jail roll
				// doubles can probably be checked in the play function everytime the loop runs.
