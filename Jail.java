public class Jail {
	private Dice dice = new Dice();
	private int roll1;
	private int roll2;

	public String rollForJail(Player player) {
		roll1 = dice.roll();
		roll2 = dice.roll();
		if (roll1 == roll2) {
			player.setJail(false);
			return player.getName() + " rolled two " + roll1 + "'s. They are now free from jail.\n";
		}
		else {
			player.addJailCount(1);
			return player.getName() + " rolled a " + roll1 + " and a " + roll2 + " so they must try again next time. " +
				"Their jail count is now " + player.getJailCount() + ".\n";
		}			
	}

	public void payBail(Player player) {
		player.changeBalance(-100);
		player.setJail(false);
	}

	public String payBailMessage(Player player) {
		return (player.getName() + " is free and their new balance is $" + player.getBalance() +".");
	}

	public String botInJail(Player theBot) {
		if (theBot.getJailCount() == 3) {
			payBail(theBot);
			return "The bot has spent 3 turns in jail and was forced to pay bail. Their new balance is $" + theBot.getBalance() + ".\n";
		}
		if (theBot.getBalance() > 100 ) {
			payBail(theBot);
			return("Bot has freed themself from jail, by paying the bail amount of $100.\n");
		}
		else {
			return rollForJail(theBot);
		}
	}

	public String forcedBail(Player player) {
		payBail(player);
		return (player.getName() + " has spent 3 turns in jail and was forced to pay bail. Their new balance is $" + player.getBalance() + ".");
	}

}