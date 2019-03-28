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
 
	public String rollResults(Player player) {
		roll1 = dice.roll();
		roll2 = dice.roll();
		return (player.getName() + " rolled a " + roll1 + " and a " + roll2 + ", so I move them " +
		(roll1 + roll2) + " spaces.");
	}

	public void moveToken(Player player, ArrayList<Square> board) {
		board.get(player.getPosition()).getOccupants().remove(player.getIcon());
		player.changePosition((roll1 + roll2)+ player.getPosition());
		board.get(player.getPosition()).getOccupants().add(player.getIcon());
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
