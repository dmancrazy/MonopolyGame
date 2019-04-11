import java.util.*;

public class EndGame {
	
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

	public boolean isGameOver(List<Player> players) {
		if (players.size() == 1) {
			return true;
		}
		return false;
	}

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
	*/
	public boolean isLoser(Player player) {
		if (player.getBalance() == 0) {
			return true;
		}
		return false;
	}
}