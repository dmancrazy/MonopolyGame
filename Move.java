import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

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
    public void roll(Player player){
		Scanner kb = new Scanner(System.in);
		String payCheck;
		Random rand = new Random();
		String check;
		current = config.getBoard().get(player.getPosition());

///////////If the Player is in Jail///////////////////////////////////////////////////////////////////////////////////////////////
		if (player.getJail() == true) {
			 System.out.println("you are in jail. You can roll doubles or pay 100$ to get out");
			 System.out.println("Will you pay 100$? Y for yes, N for no");
			 payCheck = kb.next();
			 if (payCheck.toUpperCase().equals("Y")) {
				 player.setBalance(player.getBalance()-100);
				 player.setJail(false);
				 System.out.println("you are free");
			 }
			 else{
				 roll1 = rand.nextInt(6)+1;
				 roll2 = rand.nextInt(6)+1;
				 if (roll1 == roll2){
					 System.out.println("Congratulations! You rolled 2" + roll1 + "'s");
					 player.setJail(false);
				 }
				 else{
					 System.out.println("Unfortunately you rolled a" + roll1 + "and a" + roll2);
					 player.addJailCount(1);
				 }
			 }
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////If the Player is not in Jail/////////////////////////////////////////////////////////////////////////////////
		else{
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
			(roll1 + roll2) + " spaces.\n\n");
			current.getOccupants().remove(player.getOccupantValue());
			player.changePosition((roll1 + roll2)+ player.getPosition());
			current = config.getBoard().get(player.getPosition());
			current.getOccupants().add(player.getOccupantValue());
			config.printBoard();
			check = kb.next();
			if (player.getPosition() == 30) {
				player.setJail(true);
				current.getOccupants().remove(player.getOccupantValue());
				player.changePosition(10);
				current = config.getBoard().get(player.getPosition());
				current.getOccupants().add(player.getOccupantValue());
				System.out.println("Go To Jail ! You are now moved to jail, you must roll double or pay bail to get out.");
				config.printBoard();
				check = kb.next();
			}
			// make sure this is right
			else{
				System.out.println(player.getName()+ "'s current position is [" + current.getName() + "]");
				check = kb.next();
				//if(current.getOwned() == false){
					//if (player.getBalance() < current.getCost()){
						//System.out.println("you do not have enough money to purchase this property");
					//}
					//else{
						//System.out.println("would you like to buy" + current.getName());
						//System.out.println("press y for yes or n for n");
						//check = kb.next();
						//if (check.equals("y")){
						//	player.setBalance(player.getBalance()-current.getCost());
						//
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
		}
	}
}



			    // public ifDoubles()

				// alot of this can probably be broken into separate functions, like a jailcheck and jail roll
				// doubles can probably be checked in the play function everytime the loop runs.
