/**
 * This class controls every interaction the player has with the jail
 */
public class Jail {
	private Dice dice = new Dice();
	private int roll1;
	private int roll2;

	/**
	 * This method rolls a dice for a player in jail, can set the player free
	 * and counts how many times the player has stayed in jail.
	 * If the player rolls a double, the player is freed.
	 * If the player fails to roll a double, stayed in jail count increases by 1
	 * @param player The player in Jail
	 * @return A message saying wheter the player rolled a double or not, and if they are free or not
	 */
	public String rollForJail(Player player) {
		roll1 = dice.roll();
		roll2 = dice.roll();
		if (roll1 == roll2) {
			player.setJail(false);
			return player.getName() + " rolled two " + roll1 + "'s. They are now free from jail.\n";
		}
		else {
			player.addJailCount(1);
			return player.getName() + " rolled a " + roll1 + " and a " + roll2 + " so they must try again\nnext time. " +
				"Their jail count is now " + player.getJailCount() + ".\n";
		}
	}

	/**
	 * This method takes 100 off of a player's balance if they decide to pay bail to be free
	 * @param player The player paying the bail
	 */
	public void payBail(Player player) {
		player.changeBalance(-100);
		player.setJail(false);
	}

	/**
	 * This method prints a message if the player paid bail
	 * @return A message saying a player is free and their updated balance
	 */
	public String payBailMessage(Player player) {
		return (player.getName() + " is free and their new balance is $" + player.getBalance() +".");
	}

	/**
	 * This method deals with what happens when a bot is in jail. If the bot has enough money, it will always pay bail
	 * If the bot has been in jail for 3 turns or if it has over $100, it is forced to pay bail
	 * @return A message stating if they were forced to pay bail or rolled dice, and their new balance if they pay
	 */
	public String botInJail(Player theBot) {
		if (theBot.getJailCount() == 3) {
			payBail(theBot);
			return "The bot has spent 3 turns in jail and was forced to pay bail.\nTheir new balance is $" + theBot.getBalance() + ".\n";
		}
		if (theBot.getBalance() > 100 ) {
			payBail(theBot);
			return("Bot has freed themself from jail, by paying the bail amount of $100.\n");
		}
		else {
			return rollForJail(theBot);
		}
	}

	/**
	 * This message deals with what happens if a human player has stayed in jail for 3 turns
	 * The human player is forced to pay bail
	 * @return A message stating they were forced to pay bail, and their new balance
	 */
	public String forcedBail(Player player) {
		payBail(player);
		return (player.getName() + " has spent 3 turns in jail and was forced to pay bail.\nTheir new balance is $" + player.getBalance() + ".");
	}
}
