public class Jail {
	private Dice dice = new Dice();
	private int roll1;
	private int roll2;

	public String rollForJail(Player player) {
		roll1 = dice.roll();
		roll2 = dice.roll();
		if (roll1 == roll2) {
			player.setJail(false);
			return player.getName() + " rolled 2" + roll1 + "'s. They are now free from jail.";
		}
		else {
			player.addJailCount(1);
			return player.getName() + " rolled a " + roll1 + " and a " + roll2 + " so they must try again next time. " +
				"Their jail count is now " + player.getJailCount() + ".";
		}			
	}

	public void payBail(Player player) {
		player.changeBalance(-100);
		player.setJail(false);
	}

	public String botInJail(Player theBot) {
		if (theBot.getBalance() > 100 ) {
			payBail(theBot);
			return("Bot has freed themself from jail, by paying the bail amount of $100.");
		}
		else {
			return rollForJail(theBot);
		}
	}

}