import java.util.ArrayList;

public class Messages {
	
	public void welcomeMessage(ArrayList<Player> humans, ArrayList<Player> bots) {
		System.out.println("\n\n\n\nWelcome to Monopoly!!!!!\nThere are " +
			(humans.size() + bots.size()) + " players in this game." +
			" Each player starts with $1500\nin their bank account and the goal of the game" +
			" is to have every\nother player completely run out of money," +
			" only then someone will win.\nEveryone will start on the" +
			" Go! square.\n\nThe first player to move will be " +
			humans.get(0).getName() +". Good luck and have fun!!");
	}

	public String rollMessage(Player player) {
		if (player.getName().equals("bot")) {
			return "It is the bot's turn, they have a balance of " + player.getBalance();
		}
		else {
			return "It is " + player.getName() + "'s turn, you have " + player.getBalance() + " in your account, please type anything to roll";
		}
	}

}
