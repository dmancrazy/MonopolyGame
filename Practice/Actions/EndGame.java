package Actions;

import GamePieces.*;
import java.util.*;

public class EndGame {

	/**
	 * This method states who has been eliminated from the game, removes them from the list of players 
	 * and allows the loser player's previously
	 * owned properties to be owned by players still in the game
	 * @param player The player who has lost
	 * @param players The list of players in game
	 * @return sb A message that states who lost, then, which property now can be bought agian, and remaining number of players
	 */
	public String eliminationMessage(Player player, List<Player> players) {
		Player loser = player;
		StringBuilder sb = new StringBuilder();
		sb.append(loser.getName() + " has run out of money and is eliminated.\n");
		while (!loser.getPropertiesOwned().isEmpty()) {
			loser.getPropertiesOwned().get(0).setOwned(false);
			sb.append(loser.getPropertiesOwned().get(0).getName() + " is now up for grabs.\n");
			loser.getPropertiesOwned().remove(0);
		}
		players.remove(loser);
		sb.append("The number of players remaining is " + players.size() + ".");
		return sb.toString();
	}

	/**
	 * This method returns that the game is over when there is only 1 player left in game
	 * @param players The list of players currently ingame
	 * @return true if only 1 player remains, false otherwise
	 */
	public boolean isGameOver(List<Player> players) {
		if (players.size() == 1) {
			return true;
		}
		return false;
	}

	/**
	 * This method states who has won when only 1 player is left in game
	 * @param players The list of players in the game
	 * @return A message saying whether a bot or a human player won the game
	 */
	public String victoryMessage(List<Player> players) {
		if (players.get(0).getName().equals("bot")) {
			return("The bot wins the Game!!!");
		}
		else {
			return("\n\nPlayer " + players.get(0).getName() + " wins the Game!!!\n\n");
		}
	}

	/**
	* This method checks if a player's balance gets to $0 in which the player will lose
	* @param player The loser player
	* @return true if the player has $0 in balance, false otherwise
	*/
	public boolean isLoser(Player player) {
		if (player.getBalance() == 0) {
			return true;
		}
		return false;
	}
}
