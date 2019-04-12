package Actions;

import java.util.*;
import GamePieces.*;

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

	/**
	 * This method rolls a dice for a player and states how many squares they moved
	 * @param player The player rolling the dice
	 * @return A message stating what they rolled and how many squares they moved
	 */
	public String rollResults(Player player) {
		roll1 = dice.roll();
		roll2 = dice.roll();
		return (player.getName() + " rolled a " + roll1 + " and a " + roll2 + ", so I move them " +
		(roll1 + roll2) + " spaces.");
	}

	/**
	 * This method moves the player's position and their token depending on what they rolled
	 * @param player The player being moved
	 * @param board The game board 
	 */
	public void moveToken(Player player, List<Square> board) {
		board.get(player.getPosition()).getOccupants().remove(player.getIcon());
		player.changePosition((roll1 + roll2)+ player.getPosition());
		board.get(player.getPosition()).getOccupants().add(player.getIcon());
	}
}
