import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class outlines the interaction a player will have with each square and the game in general.
 * @author Group 6
 * @version 1.0
 * @since 2019-02-25
 */
public class Move{

  // initializes dice rolls, current position, and the board
    private int roll1;
    private int roll2;
    private int doubles=0;
    private Square current;
    private MonopolyConfiguration config;
    // set proper parameters.

    /**
     * This method sets the current position as the first square on the configured board
     * @param c A setup for the monopoly board
     */
    public Move(MonopolyConfiguration c) {
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
    public void roll(Player player) {
		Scanner kb = new Scanner(System.in);
		String payCheck;
		Random rand = new Random();
		String check;
		current = config.getBoard().get(player.getPosition());

///////////If the Player is in Jail///////////////////////////////////////////////////////////////////////////////////////////////
		if (player.getJail() == true) {
			if (player.getName().equals("bot")) {
				if (player.getBalance() > 100 ) {
					player.changeBalance(-100);
					player.setJail(false);
					System.out.println("Bot has freed themself from jail.");
				}
			}

			else {
				System.out.println("you are in jail. You can roll doubles or pay 100$ to get out");
				System.out.println("Will you pay 100$? Y for yes, N for no");
				payCheck = kb.next();
				if (payCheck.toUpperCase().equals("Y")) {
					player.changeBalance(-100);
					player.setJail(false);
					System.out.println("you are free and your new balance is $" + player.getBalance() +".");
				}
				else {
					roll1 = rand.nextInt(6)+1;
					roll2 = rand.nextInt(6)+1;
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////If the Player is not in Jail/////////////////////////////////////////////////////////////////////////////////
		else {
			if (!player.getName().equals("bot")) {
				System.out.println("Please type roll to roll the 2 dice. ");
				check = kb.next();
				if (!check.toUpperCase().equals("ROLL")) {
						System.out.println("\nYou didn't type in roll correctly, but I rolled for you anyways c:");
				}
			}
			roll1 = rand.nextInt(6) + 1;
			roll2 = rand.nextInt(6) + 1;
			System.out.println(player.getName() + " rolled a " + roll1 + " and a " + roll2 + ", so I move them " +
			(roll1 + roll2) + " spaces.\n");
			current.getOccupants().remove(player.getIcon());
			player.changePosition((roll1 + roll2)+ player.getPosition());
			current = config.getBoard().get(player.getPosition());
			current.getOccupants().add(player.getIcon());
			config.printBoard();
			check = kb.next();
			if (player.getPassedGo() == true) {
				player.setPassedGo(false);
				player.changeBalance(200);
				System.out.println(player.getName() + " passed the [go] square, they will recieve $200 and their new balance is $" +
					player.getBalance() +".");
			}
			if (player.getPosition() == 30) {
				player.setJail(true);
				current.getOccupants().remove(player.getIcon());
				player.changePosition(10);
				current = config.getBoard().get(player.getPosition());
				current.getOccupants().add(player.getIcon());
				System.out.println("Go To Jail ! You are now moved to jail. Next turn you will start in in jail " +
					" you must roll double or pay bail to get out.");
				check = kb.next();
			}

			else {
				System.out.println(player.getName()+ "'s current position is [" + current.getName() + "]");
				if (current.getType().equals("property") || current.getType().equals("rail")) {
					if(current.getOwned() == false) {
						if (player.getBalance() <= current.getCost()) {
							System.out.println(player.getName() +  " does not have enough money to purchase this property, " +
								"if they do purchase it, they lose the game.");
							check = kb.next();
						}
						if (player.getName().equals("bot")) {
							if (player.getBalance() > current.getCost()) {
								buyProperty(player, current);
								check = kb.next();
							}
						}
						else {
							System.out.println("would " + player.getName() + " like to buy " + current.getName() + " for $" +
								current.getCost() + ". They have $" + player.getBalance() + " in their account.");
							System.out.println("press y for yes");
							check = kb.next();
							if (check.toUpperCase().equals("Y")){
								buyProperty(player, current);
								check = kb.next();
							}
						}
					}
					else if (current.getOwner() != player) {
						System.out.print(player.getName() + " has landed on the " + current.getName() +
							" property and must pay $" + current.getRent() + " to the property owner, which is " +
							current.getOwner().getName() + ".\n" + player.getName() + " has $" +
							player.getBalance() + " in their account." );
						check = kb.next();
						payRent(player, current);
						check = kb.next();
					}
					else if (current.getOwner() == player) {
						System.out.print(player.getName() + " has landed on their own property, which is [" +
							current.getName() + "] so nothing happens.");
						check=kb.next();
					}
				}
			}
		}
	}

  /**
   * This method gives ownership of a property to a player and tells the user their new balance
   * @param p A player
   * @param property A property being bought by a player
   */
	public void buyProperty(Player p, Square property) {
		p.changeBalance(-1*current.getCost());
		property.setOwned(true);
		property.setOwner(p);
		p.addPropertiesOwned(property);
		System.out.println(p.getName() + " has purchased " + property.getName() + " for $" + property.getCost() +
		". Their new balance is $" + p.getBalance());
	}

  /**
   * When a player stays on another player's property, this method transfers rent from the player to that another player
   * @param p A player paying the rent or the payer
   * @param property Property owned by the payee (p1)
   */
	public void payRent(Player p, Square property) {
		p.changeBalance(-1*property.getRent());
		System.out.println("\n" + p.getName() + " spent $" + property.getRent() + " to spend the night at " +
			property.getName() + " and their new balance is $" + p.getBalance() + ".\n");
		property.getOwner().changeBalance(property.getRent());
		System.out.println(property.getOwner().getName() + " recieved $" + property.getRent() + " as rent from " +
			p.getName() +". Their new balance is $" + property.getOwner().getBalance() + ".");
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
