package Actions;

import java.util.*;
import GamePieces.*;

/*
 * This class deals with the general messages that are printed during different stages of the game
 */
public class Messages {

	/**
	 * This method prints out a welcome message at the beginning or the menu of the game
	 * @param players The list of players in game
	 * @return A message stating number of players, their starting balance and basic rules
	 */
	public String welcomeMessage(List<Player> players) {
		return ("\n\nWelcome to Monopoly!!!!!\nThere are " +
			players.size() + " players in this game." +
			" Each player starts with $1500\nin their bank account and the goal of the game" +
			" is to have every\nother player completely run out of money," +
			" only then someone will win.\nEveryone will start on the" +
			" Go! square.\n\nThe first player to move will be " +
			players.get(0).getName() +". Good luck and have fun!!");
	}

	/**
	 * This method prints out who's turn it is, their current balance and position
	 * @param player The player whose turn is next
	 * @param board The current state of the Monopoly board
	 * @return A message stating a player's turn, balance, and current position
	 */
	public String turnStartMessage(Player player, List<Square> board) {
		return (player.getName() + " you have $" +
		player.getBalance() + " in your account and you are\ncurrently at " +
			"the square [" + board.get(player.getPosition()).getName() + "]. Please type anything to roll.");
	}

	/**
	 * This method prints the message that appears when a player lands on a new square
	 * @param player The player who just had his turn
	 * @param board The current state of the Monopoly board
	 * @return A message stating where a player has landed after a turn
	 */
	public String landingMessage(Player player, List<Square> board) {
		return player.getName()+ "'s current position is [" + board.get(player.getPosition()).getName() + "]";
	}

	/**
	 * This method prints a message when a player starts his turn in jail
	 * @param player The player in jail
	 * @return A message stating the player is in jail and asks for roll or pay bail
	 */
	public String jailStartMessage(Player player) {
		return (player.getName() + " is in jail. You can roll doubles or\npay 100$ to get out. Will you pay 100$?");
	}

}