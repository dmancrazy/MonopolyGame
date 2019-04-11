import java.util.*;

public class Messages {
	
	public String welcomeMessage(List<Player> players) {
		return ("\n\n\n\nWelcome to Monopoly!!!!!\nThere are " +
			players.size() + " players in this game." +
			" Each player starts with $1500\nin their bank account and the goal of the game" +
			" is to have every\nother player completely run out of money," +
			" only then someone will win.\nEveryone will start on the" +
			" Go! square.\n\nThe first player to move will be " +
			players.get(0).getName() +". Good luck and have fun!!");
	}

	public String turnStartMessage(Player player, List<Square> board) {
		return (player.getName() + " you have $" +
		player.getBalance() + " in your account and you are currently at " +
			"the square [" + board.get(player.getPosition()).getName() + "]. Please type anything to roll.");
	}

	public String landingMessage(Player player, List<Square> board) {
		return player.getName()+ "'s current position is [" + board.get(player.getPosition()).getName() + "]";
	}

	public String jailStartMessage(Player player) {
		return (player.getName() + " is in jail. You can roll doubles or pay 100$ to get out \nWill you pay 100$?");
	}
	/**
	public String rollMessage(Player player) {
		if (player.getName().equals("bot")) {
			return "It is the bot's turn, they have a balance of " + player.getBalance();
		}
		else {
			return "It is " + player.getName() + "'s turn, you have $" + player.getBalance() + " in your account, please type anything to roll";
		}
	}
	*/

}
