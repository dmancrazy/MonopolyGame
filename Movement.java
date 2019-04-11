import java.util.*;

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

	public void moveToken(Player player, List<Square> board) {
		board.get(player.getPosition()).getOccupants().remove(player.getIcon());
		player.changePosition((roll1 + roll2)+ player.getPosition());
		board.get(player.getPosition()).getOccupants().add(player.getIcon());
	}
}
